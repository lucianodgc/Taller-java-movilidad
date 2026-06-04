package moduloCargas.interfase.evento.out;

import moduloPagos.dominio.TipoMedioPago;

import java.time.LocalDateTime;

public class CargasNuevaCarga {
    private Long id;
    private String ciCliente;
    private float importeTotal;
    private String referenciaMedioPago;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public CargasNuevaCarga(Long id, String ciCliente, float importeTotal, String referenciaMedioPago, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.ciCliente = ciCliente;
        this.importeTotal = importeTotal;
        this.referenciaMedioPago = referenciaMedioPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public String getReferenciaMedioPago() {
        return referenciaMedioPago;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
}
