package moduloCarga.dominio;

import java.util.ArrayList;
import java.util.List;

public class EstacionCarga {
    private String descripcion;
    private String calle;
    private String departamento;
    private int longitud;
    private int latitud;
    private List<Cargador> cargadores = new ArrayList<>();

    public EstacionCarga() {
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
