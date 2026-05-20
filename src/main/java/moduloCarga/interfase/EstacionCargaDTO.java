package moduloCarga.interfase;

public class EstacionCargaDTO {
    private String descripcion;
    private String calle;
    private String departamento;
    private int longitud;
    private int latitud;

    public EstacionCargaDTO(String descripcion, String calle, String departamento, int longitud, int latitud) {
        this.descripcion = descripcion;
        this.calle = calle;
        this.departamento = departamento;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCalle() {
        return calle;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getLongitud() {
        return longitud;
    }

    public int getLatitud() {
        return latitud;
    }
}
