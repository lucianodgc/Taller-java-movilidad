package moduloPagos.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "ClientePagos")
public class Cliente {
    @Id
    private String cedula;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioPago> mediosDePago = new ArrayList<>();
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pago> pagos = new ArrayList<>();
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

    public List<MedioPago> getMediosDePago() {
        return mediosDePago;
    }

    public void agregarPago(Pago pago) {
        pagos.add(pago);
    }

    public void agregarCarga(Carga carga) {
        cargas.add(carga);
    }

    public void agregarMedioPago(MedioPago medioPago) {
        mediosDePago.add(medioPago);
    }
}
