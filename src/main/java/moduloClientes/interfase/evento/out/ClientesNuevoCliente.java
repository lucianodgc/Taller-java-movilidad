package moduloClientes.interfase.evento.out;

public class ClientesNuevoCliente {
    private String cedula;

    private float descuento;

    public ClientesNuevoCliente(String cedula, float descuento) {
        this.cedula = cedula;
        this.descuento = descuento;
    }

    public String getCedula() {
        return cedula;
    }

    public float getDescuento() {
        return descuento;
    }
}
