package moduloClientes.infraestructura.messaging;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;

import java.io.StringReader;
import java.io.StringWriter;

public record ReclamoMessage(
        String ciCliente,
        String comentario
) {
    public String toJson() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("ciCliente", this.ciCliente)
                .add("comentario", this.comentario)
                .build();

        StringWriter sw = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(sw)) {
            jsonWriter.write(jsonObject);
        }
        return sw.toString();
    }

    public static ReclamoMessage buildFromJson(String jsonReclamo) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonReclamo))) {
            JsonObject objeto = jsonReader.readObject();
            return new ReclamoMessage(
                    objeto.getString("ciCliente"),
                    objeto.getString("comentario")
            );
        }
    }
}