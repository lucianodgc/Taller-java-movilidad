package moduloClientes.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMUN")
public class ClienteComun extends Cliente{

    protected ClienteComun() {
    }

    public ClienteComun(String cedula, String nombreCompleto, String telefono, String contraseña) {
        super(cedula, nombreCompleto, telefono, contraseña);
    }

}
