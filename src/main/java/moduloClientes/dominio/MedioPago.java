package moduloClientes.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_medios_pago")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pago", discriminatorType = DiscriminatorType.STRING)
public abstract class MedioPago {
    @Id
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula", nullable = false)
    private Cliente cliente;

    protected MedioPago() {
    }

    public MedioPago(String referencia, Cliente cliente) {
        this.referencia = referencia;
        this.cliente = cliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
