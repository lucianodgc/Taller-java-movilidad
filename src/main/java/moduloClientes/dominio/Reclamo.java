package moduloClientes.dominio;

import jakarta.persistence.*;

@Entity(name = "ReclamoClientes")
@Table(name = "Clientes_Reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_cedula")
    Cliente cliente;
    @Column(name = "comentario")
    String comentario;
    @Column(name = "etiqueta")
    private String etiqueta;

    protected Reclamo() {
    }

    public Reclamo(Cliente cliente, String comentario, String etiqueta) {
        this.cliente = cliente;
        this.comentario = comentario;
        this.etiqueta = etiqueta;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
