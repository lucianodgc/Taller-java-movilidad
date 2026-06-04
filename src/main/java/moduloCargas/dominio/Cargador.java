package moduloCargas.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CargadorCargas")
@Table(name = "Cargas_Cargador")
public class Cargador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cargador")
    private TipoCargador tipoCargador;

    @Column(name = "tiene_cable")
    private Boolean tieneCable;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conector")
    private TipoConector tipoConector;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoCargador estado;

    @Column(name = "tiempo_estimado_finalizacion")
    private LocalDateTime tiempoEstimadoFinalizacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_estimada_reparacion")
    private LocalDate fechaEstimadaReparacion;

    @Column(name = "potencia_minima")
    private int potenciaMinima;

    @ManyToOne
    @JoinColumn(name = "id_estacion")
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

    public EstacionCarga getEstacion() {
        return estacion;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }
}
