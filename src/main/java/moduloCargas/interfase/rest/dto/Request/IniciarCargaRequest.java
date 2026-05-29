package moduloCargas.interfase.rest.dto.Request;

public class IniciarCargaRequest {
    private String ciCliente;
    private String idCargador;
    private String referenciaMedioPago;

    public IniciarCargaRequest() {
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public void setCiCliente(String ciCliente) {
        this.ciCliente = ciCliente;
    }

    public String getIdCargador() {
        return idCargador;
    }

    public void setIdCargador(String idCargador) {
        this.idCargador = idCargador;
    }

    public String getReferenciaMedioPago() {
        return referenciaMedioPago;
    }

    public void setReferenciaMedioPago(String referenciaMedioPago) {
        this.referenciaMedioPago = referenciaMedioPago;
    }
}
