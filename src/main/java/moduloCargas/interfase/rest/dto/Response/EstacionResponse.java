package moduloCargas.interfase.rest.dto.Response;

import java.util.ArrayList;
import java.util.List;

public class EstacionResponse {
    private Long id;
    private String descripcion;
    private String calle;
    private String departamento;
    private List<CargadorResponse> cargadores = new ArrayList<>();

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

    public List<CargadorResponse> getCargadores() {
        return cargadores;
    }

    public void agregarCargador(CargadorResponse cargador) {
        this.cargadores.add(cargador);
    }
}
