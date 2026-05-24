package moduloPagos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity(name = "PagoPagos")
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
}
