package moduloPagos.interfase.evento.out;

public class PagosPagoRealizado {
    private String ciCliente;
    private float monto;
    private String tipoMedioPago;
    
    public PagosPagoRealizado(String ciCliente, float monto, String tipoMedioPago) {
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.tipoMedioPago = tipoMedioPago;
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

    
}
 