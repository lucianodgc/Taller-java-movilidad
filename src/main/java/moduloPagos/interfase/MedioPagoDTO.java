package moduloPagos.interfase;

public class MedioPagoDTO {
    private String referencia;
    private String ciCliente;

    public MedioPagoDTO(String referencia, String ciCliente) {
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
