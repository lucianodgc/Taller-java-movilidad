package moduloCargas.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "CargaCargas")
@Table(name = "Cargas_carga")
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_cargador")
    private Cargador cargador;

    @Column(name = "referencia_medio_pago")
    private String referenciaMedioPago;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "importe_total")
    private float importeTotal;

    @Column(name = "recarga_por_demora")
    private float recargaPorDemora;

    @Column(name = "porcentaje_avance")
    private int porcentajeAvance;

    @Column(name = "hora_estimada")
    private LocalDateTime horaEstimada;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
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

    public String getReferenciaMedioPago() {
        return referenciaMedioPago;
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

    public float getImporteTotal() {
        return importeTotal;
    }
}
