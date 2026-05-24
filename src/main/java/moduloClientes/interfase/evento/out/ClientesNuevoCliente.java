package moduloClientes.interfase.evento.out;

public class ClientesNuevoCliente {
    private String cedula;

    public ClientesNuevoCliente(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }
}
