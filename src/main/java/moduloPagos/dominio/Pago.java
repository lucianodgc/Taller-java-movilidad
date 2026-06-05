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

    @OneToOne
    @JoinColumn(name = "carga_id")
    private Carga carga;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @OneToOne
    @JoinColumn(name = "medio_pago_referencia")
    private MedioPago medioPago;

    protected Pago() {
    }

    public Pago(Cliente cliente, Float monto, LocalDateTime fechaPago, MedioPago medioPago, Carga carga) {
        this.cliente = cliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
        this.carga = carga;
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

    public Carga getCarga() {
        return carga;
    }
}
