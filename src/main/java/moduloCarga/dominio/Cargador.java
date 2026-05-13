package moduloCarga.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cargador {
    private TipoCargador tipo;
    private Boolean tieneCable;
    private TipoConector tipoConector;
    private EstadoCargador estado;
    private LocalDateTime tiempoEstimadoFinalizacion;
    private Date fechaEstimadaReparacion;
    private int potenciaMinima;
    private List<Carga> cargas = new ArrayList<>();

    public Cargador() {
    }

    public TipoCargador getTipo() {
        return tipo;
    }

    public Boolean getTieneCable() {
        return tieneCable;
    }

    public int getPotenciaMinima() {
        return potenciaMinima;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public LocalDateTime getTiempoEstimadoFinalizacion() {
        return tiempoEstimadoFinalizacion;
    }

    public EstadoCargador getEstado() {
        return estado;
    }

    public Date getFechaEstimadaReparacion() {
        return fechaEstimadaReparacion;
    }

}
