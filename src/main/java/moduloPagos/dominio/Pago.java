package moduloPagos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class Pago {
    @Id
    private Long id;
    private String ciCliente;
    private Float monto;
    private LocalDateTime fechaPago;
    @OneToOne
    private MedioPago medioPago;

    protected Pago() {
    }

    public Pago(String ciCliente, Float monto, LocalDateTime fechaPago, MedioPago medioPago) {
        this.ciCliente = ciCliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.medioPago = medioPago;
    }

    public Long getId() {
        return id;
    }
}
