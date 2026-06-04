package moduloPagos.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "PagoPagos")
@Table(name = "Pagos_Pago")
public class Pago {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula")
    private Cliente cliente;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @OneToOne
    @JoinColumn(name = "medio_pago_referencia")
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
