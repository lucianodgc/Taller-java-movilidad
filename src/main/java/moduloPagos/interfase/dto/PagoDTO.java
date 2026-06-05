package moduloPagos.interfase.dto;


import moduloPagos.dominio.Carga;

import java.time.LocalDateTime;

public class PagoDTO {
    private String ciCliente;
    private Float monto;
    private LocalDateTime fechaPago;
    private String medioPago;
    private Long idCarga;

    public PagoDTO(String ciCliente, Float monto, LocalDateTime fechaPago, String medioPago, Long idCarga) {
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
        this.idCarga = idCarga;
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

    public Long getIdCarga() {
        return idCarga;
    }
}
