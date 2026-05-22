package moduloCarga.interfase;

import moduloCarga.dominio.EstadoCargador;
import moduloCarga.dominio.TipoCargador;
import moduloCarga.dominio.TipoConector;

import java.time.LocalDate;

public class CargadorDTO {
    private TipoCargador tipoCargador;
    private TipoConector tipoConector;
    private int potenciaMinima;
    private boolean tieneCable;
    private String idEstacion;
    private EstadoCargador estadoCargador;
    private LocalDate fechaEstimadaReparacion;

    public CargadorDTO(TipoCargador tipoCargador, TipoConector tipoConector, int potenciaMinima, boolean tieneCable, String idEstacion) {
        this.tipoCargador = tipoCargador;
        this.tipoConector = tipoConector;
        this.potenciaMinima = potenciaMinima;
        this.tieneCable = tieneCable;
        this.idEstacion = idEstacion;
    }

    public TipoCargador getTipoCargador() {
        return tipoCargador;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public int getPotenciaMinima() {
        return potenciaMinima;
    }

    public boolean isTieneCable() {
        return tieneCable;
    }

    public String getIdEstacion() {
        return idEstacion;
    }

    public EstadoCargador getEstadoCargador() {
        return estadoCargador;
    }

    public LocalDate getFechaEstimadaReparacion() {
        return fechaEstimadaReparacion;
    }
}
