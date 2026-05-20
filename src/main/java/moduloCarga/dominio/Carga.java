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
    private EstadoCarga estado;

    public Carga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public void setCiCliente(String ciCliente) {
        this.ciCliente = ciCliente;
    }

    public Cargador getCargador() {
        return cargador;
    }

    public void setCargador(Cargador cargador) {
        this.cargador = cargador;
    }

    public String getReferenciaMedioPago() {
        return referenciaMedioPago;
    }

    public void setReferenciaMedioPago(String referenciaMedioPago) {
        this.referenciaMedioPago = referenciaMedioPago;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public float getRecargaPorDemora() {
        return recargaPorDemora;
    }

    public void setRecargaPorDemora(float recargaPorDemora) {
        this.recargaPorDemora = recargaPorDemora;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public LocalDateTime getHoraEstimada() {
        return horaEstimada;
    }

    public void setHoraEstimada(LocalDateTime horaEstimada) {
        this.horaEstimada = horaEstimada;
    }

    public EstadoCarga getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarga estado) {
        this.estado = estado;
    }
}
