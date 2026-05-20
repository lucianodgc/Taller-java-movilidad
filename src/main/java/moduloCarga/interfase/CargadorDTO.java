package moduloCarga.interfase;

public class CargadorDTO {
    private String tipo;
    private String tipoConector;
    private int potenciaMinima;
    private boolean tieneCable;
    private String idEstacion;

    public CargadorDTO(String tipo, String tipoConector, int potenciaMinima, boolean tieneCable, String idEstacion) {
        this.tipo = tipo;
        this.tipoConector = tipoConector;
        this.potenciaMinima = potenciaMinima;
        this.tieneCable = tieneCable;
        this.idEstacion = idEstacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoConector() {
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
