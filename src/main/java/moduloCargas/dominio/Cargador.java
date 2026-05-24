package moduloCargas.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CargadorCargas")
public class Cargador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoCargador tipoCargador;
    private Boolean tieneCable;
    @Enumerated(EnumType.STRING)
    private TipoConector tipoConector;
    @Enumerated(EnumType.STRING)
    private EstadoCargador estado;
    private LocalDateTime tiempoEstimadoFinalizacion;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaEstimadaReparacion;
    private int potenciaMinima;

    @ManyToOne
    @JoinColumn(name = "idEstacion")
    private EstacionCarga estacion;

    @OneToMany(mappedBy = "cargador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carga> cargas = new ArrayList<>();

    protected Cargador() {
    }

    public Cargador(TipoCargador tipoCargador, Boolean tieneCable, TipoConector tipoConector, EstacionCarga estacion) {
        this.tipoCargador = tipoCargador;
        this.tieneCable = tieneCable;
        this.tipoConector = tipoConector;
        this.estacion = estacion;
    }

    public Long getId() {
        return id;
    }

    public EstadoCargador getEstado() {
        return estado;
    }

    public void setEstado(EstadoCargador estado) {
        this.estado = estado;
    }

    public void setTiempoEstimadoFinalizacion(LocalDateTime tiempoEstimadoFinalizacion) {
        this.tiempoEstimadoFinalizacion = tiempoEstimadoFinalizacion;
    }

    public void setFechaEstimadaReparacion(LocalDate fechaEstimadaReparacion) {
        this.fechaEstimadaReparacion = fechaEstimadaReparacion;
    }

    public void setPotenciaMinima(int potenciaMinima) {
        this.potenciaMinima = potenciaMinima;
    }

    public void agregarCargas(Carga carga) {
        this.cargas.add(carga);
    }
}
