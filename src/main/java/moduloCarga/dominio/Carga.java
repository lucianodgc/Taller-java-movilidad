package moduloCarga.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciCliente;
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

    public Carga(String ciCliente, Cargador cargador, LocalDateTime fechaInicio, EstadoCarga estado) {
        this.ciCliente = ciCliente;
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

    public void setReferenciaMedioPago(String referenciaMedioPago) {
        this.referenciaMedioPago = referenciaMedioPago;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
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
