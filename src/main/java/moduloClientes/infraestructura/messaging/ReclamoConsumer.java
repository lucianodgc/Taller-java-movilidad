package moduloClientes.infraestructura.messaging;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import moduloClientes.interfase.IClientesService;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/ReclamosQueue"),
                @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")
        }
)
public class ReclamoConsumer implements MessageListener {

    @Inject
    private IClientesService clientesService;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String body = ((TextMessage) message).getText();

                ReclamoMessage reclamo = ReclamoMessage.buildFromJson(body);

                String etiqueta = llamarAlLLM(reclamo.comentario());

                clientesService.guardarReclamoProcesado(reclamo.ciCliente(), reclamo.comentario(), etiqueta);
            }
        } catch (JMSException e) {
            System.err.println("Error procesando el reclamo: " + e.getMessage());
        }
    }

    private String llamarAlLLM(String comentario) {
        return "NEGATIVO";
    }
}