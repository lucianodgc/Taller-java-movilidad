package moduloClientes.dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ClienteClientes")
@Table(name = "Clientes_Cliente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
public abstract class Cliente {
    @Id
    private String cedula;
    @Column(nullable = false)
    private String nombreCompleto;
    private String telefono;
    @Column(nullable = false)
    private String contraseña;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedioPago> mediosDePago = new ArrayList<>();
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reclamo> reclamos;

    protected Cliente() {
    }

    public Cliente(String cedula, String nombreCompleto, String telefono, String contraseña) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public String getCedula() {
        return cedula;
    }

    public List<MedioPago> getMediosDePago() {
        return mediosDePago;
    }

    public void agregarMediosDePago(MedioPago medioDePago) {
        this.mediosDePago.add(medioDePago);
    }

    public void agregarReclamo(Reclamo reclamo) {
        this.reclamos.add(reclamo);
    }
}
