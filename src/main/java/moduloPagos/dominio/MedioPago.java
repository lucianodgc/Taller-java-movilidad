package moduloPagos.dominio;

import jakarta.persistence.*;

@Entity(name = "MedioPagoPagos")
@Table(name = "Pagos_MedioPago")
public class MedioPago {
    @Id
    @Column(name = "referencia")
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_medio_pago")
    private TipoMedioPago tipoMedioPago;

    public MedioPago() {
    }

    public MedioPago(String referencia, Cliente cliente, TipoMedioPago tipoMedioPago) {
        this.referencia = referencia;
        this.cliente = cliente;
        this.tipoMedioPago = tipoMedioPago;
    }

    public String getReferencia() {
        return referencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoMedioPago getTipoMedioPago() {
        return tipoMedioPago;
    }
}
