package moduloClientes.dominio;

import jakarta.persistence.*;

@Entity(name = "ClienteProfesionalClientes")
@DiscriminatorValue("PROFESIONAL")
public class ClienteProfesional extends Cliente{
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_profesional")
    private TipoProfesional tipo;
    @Column(name = "porcentaje_descuento")
    private float porcentajeDescuento;

    protected ClienteProfesional() {
    }

    public ClienteProfesional(String cedula, String nombreCompleto, String telefono, String contraseña, TipoProfesional tipo, float descuento) {
        super(cedula, nombreCompleto, telefono, contraseña);
        this.tipo = tipo;
        this.porcentajeDescuento = descuento;
    }

    public TipoProfesional getTipo() {
        return tipo;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
