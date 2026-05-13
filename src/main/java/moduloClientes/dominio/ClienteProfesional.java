package moduloClientes.dominio;

public class ClienteProfesional extends Cliente{
    private TipoProfesional tipo;
    private float porcentajeDescuento;

    public ClienteProfesional(String cedula, String nombreCompleto, String telefono, String contraseña, TipoProfesional tipo, float descuento) {
        super(cedula, nombreCompleto, telefono, contraseña);
        this.tipo = tipo;
        this.porcentajeDescuento = descuento;
    }

    public float obtenerDescuento() {
        return this.porcentajeDescuento;
    }
}
