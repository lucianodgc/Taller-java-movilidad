package moduloCargas.dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "EstacionCargaCargas")
@Table(name = "Cargas_EstacionCarga")
public class EstacionCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String calle;
    private String departamento;
    private int longitud;
    private int latitud;
    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cargador> cargadores = new ArrayList<>();

    protected EstacionCarga() {
    }

    public EstacionCarga(String descripcion, String calle, String departamento, int longitud, int latitud) {
        this.descripcion = descripcion;
        this.calle = calle;
        this.departamento = departamento;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Long getId() {
        return id;
    }

    public void agregarCargador(Cargador cargador) {
        cargadores.add(cargador);
    }
}
