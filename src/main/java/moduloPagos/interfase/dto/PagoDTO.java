package moduloPagos.interfase.dto;


import java.time.LocalDateTime;

public class PagoDTO {
    private String ciCliente;
    private Float monto;
    private LocalDateTime fechaPago;
    private String medioPago;

    public PagoDTO(String ciCliente, Float monto, LocalDateTime fechaPago, String medioPago) {
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
    }

    public String getCliente() {
        return ciCliente;
    }

    public Float getMonto() {
        return monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public String getMedioPago() {
        return medioPago;
    }
}
