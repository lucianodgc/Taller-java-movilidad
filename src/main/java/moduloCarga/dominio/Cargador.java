package moduloCarga.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cargador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoCargador tipo;
    private Boolean tieneCable;
    private TipoConector tipoConector;
    private EstadoCargador estado;
    private LocalDateTime tiempoEstimadoFinalizacion;
    private Date fechaEstimadaReparacion;
    private int potenciaMinima;

    @ManyToOne
    @JoinColumn(name = "idEstacion")
    private EstacionCarga estacion;

    @OneToMany(mappedBy = "cargador")
    private List<Carga> cargas = new ArrayList<>();

    public Cargador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCargador getTipo() {
        return tipo;
    }

    public void setTipo(TipoCargador tipo) {
        this.tipo = tipo;
    }

    public Boolean getTieneCable() {
        return tieneCable;
    }

    public void setTieneCable(Boolean tieneCable) {
        this.tieneCable = tieneCable;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(TipoConector tipoConector) {
        this.tipoConector = tipoConector;
    }

    public EstadoCargador getEstado() {
        return estado;
    }

    public void setEstado(EstadoCargador estado) {
        this.estado = estado;
    }

    public LocalDateTime getTiempoEstimadoFinalizacion() {
        return tiempoEstimadoFinalizacion;
    }

    public void setTiempoEstimadoFinalizacion(LocalDateTime tiempoEstimadoFinalizacion) {
        this.tiempoEstimadoFinalizacion = tiempoEstimadoFinalizacion;
    }

    public Date getFechaEstimadaReparacion() {
        return fechaEstimadaReparacion;
    }

    public void setFechaEstimadaReparacion(Date fechaEstimadaReparacion) {
        this.fechaEstimadaReparacion = fechaEstimadaReparacion;
    }

    public int getPotenciaMinima() {
        return potenciaMinima;
    }

    public void setPotenciaMinima(int potenciaMinima) {
        this.potenciaMinima = potenciaMinima;
    }

    public EstacionCarga getEstacion() {
        return estacion;
    }

    public void setEstacion(EstacionCarga estacion) {
        this.estacion = estacion;
    }

    public List<Carga> getCargas() {
        return cargas;
    }

    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }
}
