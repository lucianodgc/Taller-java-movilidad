package moduloClientes.interfase;

import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.MedioPago;

import java.util.List;

public interface IClienteService {
    void registrarCliente(ClienteDTO cliente);

    void altaMedioPago(Cliente cliente, MedioPago medioPago);

    List<Cliente> obtenerClientes();

    void realizarReclamo();

    Cliente obtenerCliente(String cedula);
}
