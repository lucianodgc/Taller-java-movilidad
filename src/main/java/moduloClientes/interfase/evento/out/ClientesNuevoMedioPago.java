package moduloClientes.interfase.evento.out;

public class ClientesNuevoMedioPago {
    private String referencia;
    private String ciCliente;

    public ClientesNuevoMedioPago(String referencia, String ciCliente) {
        this.referencia = referencia;
        this.ciCliente = ciCliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCiCliente() {
        return ciCliente;
    }
}
