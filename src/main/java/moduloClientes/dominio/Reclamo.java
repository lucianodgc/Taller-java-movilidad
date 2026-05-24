package moduloClientes.dominio;

import jakarta.persistence.*;

@Entity(name = "ReclamoClientes")
@Table(name = "Clientes_Reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Cliente cliente;
    String comentario;

    protected Reclamo() {
    }

    public Reclamo(Cliente cliente, String comentario) {
        this.cliente = cliente;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getComentario() {
        return comentario;
    }
}
