package moduloCargas.dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ClienteCargas")
@Table(name = "Cargas_Cliente")
public class Cliente {
    @Id
    private String cedula;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioPago> mediosDePago = new ArrayList<>();
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carga> cargas = new ArrayList<>();

    protected Cliente() {
    }

    public Cliente(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void agregarCarga(Carga carga) {
        cargas.add(carga);
    }

    public List<MedioPago> getMediosDePago() {
        return mediosDePago;
    }

    public void agregarMedioPago(MedioPago medioPago) {
        mediosDePago.add(medioPago);
    }
}
