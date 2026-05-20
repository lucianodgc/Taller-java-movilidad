package moduloCarga.dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EstacionCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String calle;
    private String departamento;
    private int longitud;
    private int latitud;
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
    private List<Cargador> cargadores = new ArrayList<>();

    public EstacionCarga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public List<Cargador> getCargadores() {
        return cargadores;
    }

    public void setCargadores(List<Cargador> cargadores) {
        this.cargadores = cargadores;
    }
}
