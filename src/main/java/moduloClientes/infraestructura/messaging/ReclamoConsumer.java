package moduloClientes.infraestructura.messaging;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import moduloClientes.interfase.IClientesService;
import moduloClientes.interfase.evento.out.PublicadorEvento;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/ReclamosQueue"),
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
                @ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue = "30")
        }
)
public class ReclamoConsumer implements MessageListener {

    @Inject
    private IClientesService clientesService;

    @Inject
    private PublicadorEvento evento;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String body = ((TextMessage) message).getText();

                ReclamoMessage reclamo = ReclamoMessage.buildFromJson(body);

                String etiqueta = llamarAlLLM(reclamo.comentario());

                clientesService.guardarReclamoProcesado(reclamo.ciCliente(), reclamo.comentario(), etiqueta);

                if ("NEGATIVO".equals(etiqueta)) {
                    evento.publicarReclamoNegativo();
                }
            }
        } catch (Exception e) {
            System.err.println("Error procesando el reclamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String llamarAlLLM(String comentario) {
        try {
            String prompt = "Eres un sistema automático de clasificación de reclamos de transporte y movilidad. "
                    + "Clasifica el siguiente comentario usando estrictamente una de estas tres palabras: POSITIVO, NEGATIVO o NEUTRO. "
                    + "No agregues introducciones, ni explicaciones, ni puntos. Responde solo la palabra.\n\n"
                    + "Comentario: " + comentario;


            JsonObject requestJson = Json.createObjectBuilder()
                    .add("model", "llama2")
                    .add("prompt", prompt)
                    .add("stream", false)
                    .build();

            String requestBody = requestJson.toString();

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofMinutes(3))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            try (JsonReader reader = Json.createReader(new StringReader(response.body()))) {
                JsonObject jsonResponse = reader.readObject();
                String respuestaIA = jsonResponse.getString("response").toUpperCase();


                if (respuestaIA.contains("POSITIVO")) return "POSITIVO";
                if (respuestaIA.contains("PROBLEMATICO") || respuestaIA.contains("PROBLEMÁTICO") || respuestaIA.contains("NEGATIVO")) return "NEGATIVO";
                if (respuestaIA.contains("NEUTRO")) return "NEUTRO";

                return "DESCONOCIDO";
            }

        } catch (Exception e) {
            System.err.println("Error comunicándose con Ollama/Llama2: " + e.getMessage());
            return "ERROR";
        }
    }
}