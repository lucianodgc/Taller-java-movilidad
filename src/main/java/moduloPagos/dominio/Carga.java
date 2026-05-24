package moduloPagos.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "CargaPagos")
@Table(name = "Pagos_Carga")
public class Carga {
    @Id
    private Long id;
    @ManyToOne
    private Cliente cliente;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    protected Carga() {
    }

    public Carga(Long id, Cliente cliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
}
