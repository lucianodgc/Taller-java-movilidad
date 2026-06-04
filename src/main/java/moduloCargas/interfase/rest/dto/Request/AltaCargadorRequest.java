package moduloCargas.interfase.rest.dto.Request;

import moduloCargas.dominio.TipoCargador;
import moduloCargas.dominio.TipoConector;

public class AltaCargadorRequest {
    private TipoCargador tipoCargador;
    private TipoConector tipoConector;
    private int potenciaMinima;
    private boolean tieneCable;

    public AltaCargadorRequest() {
    }

    public TipoCargador getTipoCargador() {
        return tipoCargador;
    }

    public void setTipoCargador(TipoCargador tipoCargador) {
        this.tipoCargador = tipoCargador;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(TipoConector tipoConector) {
        this.tipoConector = tipoConector;
    }

    public int getPotenciaMinima() {
        return potenciaMinima;
    }

    public void setPotenciaMinima(int potenciaMinima) {
        this.potenciaMinima = potenciaMinima;
    }

    public boolean isTieneCable() {
        return tieneCable;
    }

    public void setTieneCable(boolean tieneCable) {
        this.tieneCable = tieneCable;
    }
}
