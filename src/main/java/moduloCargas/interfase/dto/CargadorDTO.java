package moduloCargas.interfase.dto;

import moduloCargas.dominio.EstadoCargador;
import moduloCargas.dominio.TipoCargador;
import moduloCargas.dominio.TipoConector;

import java.time.LocalDate;

public class CargadorDTO {
    private TipoCargador tipoCargador;
    private TipoConector tipoConector;
    private int potenciaMinima;
    private boolean tieneCable;
    private String idEstacion;

    protected CargadorDTO() {
    }

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
}
