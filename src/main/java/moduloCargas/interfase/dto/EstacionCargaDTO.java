package moduloCargas.interfase.dto;

public class EstacionCargaDTO {
    private String descripcion;
    private String calle;
    private String departamento;
    private Double longitud;
    private Double latitud;

    public EstacionCargaDTO() {
    }

    public EstacionCargaDTO(String descripcion, String calle, String departamento, Double longitud, Double latitud) {
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

    public Double getLongitud() {
        return longitud;
    }

    public Double getLatitud() {
        return latitud;
    }
}
