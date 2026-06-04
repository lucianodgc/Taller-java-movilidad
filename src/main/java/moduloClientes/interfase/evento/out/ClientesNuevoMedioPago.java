package moduloClientes.interfase.evento.out;

public class ClientesNuevoMedioPago {
    private String referencia;
    private String ciCliente;
    private String tipo;

    public ClientesNuevoMedioPago(String referencia, String ciCliente, String tipo) {
        this.referencia = referencia;
        this.ciCliente = ciCliente;
        this.tipo = tipo;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public String getTipo() {
        return tipo;
    }
}
