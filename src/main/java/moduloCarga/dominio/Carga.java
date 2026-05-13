package moduloCarga.dominio;

import java.time.LocalDateTime;
import java.util.Date;

public class Carga {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float importeTotal;
    private float recargaPorDemora;
    private int porcentajeAvance;
    private LocalDateTime horaEstimada;
    private EstadoCarga estado;

    public Carga() {
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public float getRecargaPorDemora() {
        return recargaPorDemora;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public LocalDateTime getHoraEstimada() {
        return horaEstimada;
    }

    public EstadoCarga getEstado() {
        return estado;
    }
}
