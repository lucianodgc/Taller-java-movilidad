package moduloPagos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MedioPago {
    @Id
    private String referencia;
    @ManyToOne
    private Cliente cliente;
}
