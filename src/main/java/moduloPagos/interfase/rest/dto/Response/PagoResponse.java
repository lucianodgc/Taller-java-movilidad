package moduloPagos.interfase.rest.dto.Response;

import java.time.LocalDateTime;

public class PagoResponse {
    private Long id;
    private Float monto;
    private LocalDateTime fechaPago;
    private String referenciaMedioPago;
    private Long idCarga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReferenciaMedioPago() {
        return referenciaMedioPago;
    }

    public void setReferenciaMedioPago(String referenciaMedioPago) {
        this.referenciaMedioPago = referenciaMedioPago;
    }

    public Long getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Long idCarga) {
        this.idCarga = idCarga;
    }
}
