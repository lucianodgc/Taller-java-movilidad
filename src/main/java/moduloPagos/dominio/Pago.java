package moduloPagos.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "PagoPagos")
@Table(name = "Pagos_Pago")
public class Pago {
    @Id
    private Long id;
    @ManyToOne
    private Cliente cliente;
    private Float monto;
    private LocalDateTime fechaPago;
    @OneToOne
    private MedioPago medioPago;

    protected Pago() {
    }

    public Pago(Cliente cliente, Float monto, LocalDateTime fechaPago, MedioPago medioPago) {
        this.cliente = cliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
    }

    public Long getId() {
        return id;
    }

    public Float getMonto() {
        return monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }
}
