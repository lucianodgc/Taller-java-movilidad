package moduloClientes.interfase.rest.Request;

public class RealizarReclamoRequest {
    private String comentario;

    public RealizarReclamoRequest() {
    }

    public RealizarReclamoRequest(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
