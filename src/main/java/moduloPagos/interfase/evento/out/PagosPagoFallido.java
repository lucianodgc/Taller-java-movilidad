package moduloPagos.interfase.evento.out;

public class PagosPagoFallido {
    private String ciCliente;
    private float monto;
    private String tipoMedioPago;
    private String error;
    
    public PagosPagoFallido(String ciCliente, float monto, String tipoMedioPago, String error) {
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.tipoMedioPago = tipoMedioPago;
        this.error = error;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public float getMonto() {
        return monto;
    }

    public String getTipoMedioPago() {
        return tipoMedioPago;
    }

    public String getError() {
        return error;
    }

}
