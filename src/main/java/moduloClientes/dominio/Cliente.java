package moduloClientes.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String contraseña;
    private List<MedioPago> mediosDePago = new ArrayList<>();
    private List<Carga> cargas = new ArrayList<>();

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

    public void agregarMedioPago(MedioPago mediosDePago) {
        this.mediosDePago.add(mediosDePago);
    }

    public List<Carga> getCargas() {
        return cargas;
    }
}
