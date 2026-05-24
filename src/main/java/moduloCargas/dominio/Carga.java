package moduloCargas.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "CargaCargas")
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idCargador")
    private Cargador cargador;
    private String referenciaMedioPago;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float importeTotal;
    private float recargaPorDemora;
    private int porcentajeAvance;
    private LocalDateTime horaEstimada;
    @Enumerated(EnumType.STRING)
    private EstadoCarga estado;

    protected Carga() {
    }

    public Carga(Cliente cliente, Cargador cargador, LocalDateTime fechaInicio, EstadoCarga estado) {
        this.cliente = cliente;
        this.cargador = cargador;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Cargador getCargador() {
        return cargador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public EstadoCarga getEstado() {
        return estado;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setReferenciaMedioPago(String referenciaMedioPago) {
        this.referenciaMedioPago = referenciaMedioPago;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setRecargaPorDemora(float recargaPorDemora) {
        this.recargaPorDemora = recargaPorDemora;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public void setHoraEstimada(LocalDateTime horaEstimada) {
        this.horaEstimada = horaEstimada;
    }

    public void setEstado(EstadoCarga estado) {
        this.estado = estado;
    }
}
