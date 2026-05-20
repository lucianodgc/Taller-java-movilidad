package moduloCarga.dominio;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {
    @Id
    private String cedula;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MedioPago> mediosDePago = new ArrayList<>();

    public Cliente() {
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

}
