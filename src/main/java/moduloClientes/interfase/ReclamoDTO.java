package moduloClientes.interfase;

import moduloClientes.dominio.Cliente;

public class ReclamoDTO {
    private Cliente cliente;
    private String comentario;

    public ReclamoDTO(Cliente cliente, String comentario) {
        this.cliente = cliente;
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getComentario() {
        return comentario;
    }
}
