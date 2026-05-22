package moduloClientes.dominio;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("PROFESIONAL")
public class ClienteProfesional extends Cliente{
    @Enumerated(EnumType.STRING)
    private TipoProfesional tipo;
    private float porcentajeDescuento;

    protected ClienteProfesional() {
    }

    public ClienteProfesional(String cedula, String nombreCompleto, String telefono, String contraseña, TipoProfesional tipo, float descuento) {
        super(cedula, nombreCompleto, telefono, contraseña);
        this.tipo = tipo;
        this.porcentajeDescuento = descuento;
    }

    public float obtenerDescuento() {
        return this.porcentajeDescuento;
    }
}
