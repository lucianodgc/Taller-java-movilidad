package moduloPagos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "MedioPagoPagos")
public class MedioPago {
    @Id
    private String referencia;
    @ManyToOne
    private Cliente cliente;

    public MedioPago() {
    }

    public MedioPago(String referencia, Cliente cliente) {
        this.referencia = referencia;
        this.cliente = cliente;
    }
}
