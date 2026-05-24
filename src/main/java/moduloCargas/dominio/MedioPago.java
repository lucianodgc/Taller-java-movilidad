package moduloCargas.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "MedioPago-Cargas")
public class MedioPago {
    @Id
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "ciCliente")
    private Cliente cliente;

    public MedioPago() {}

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
