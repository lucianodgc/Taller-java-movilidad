package moduloClientes.interfase;

import moduloClientes.dominio.Cliente;
import moduloClientes.interfase.dto.ClienteDTO;
import moduloClientes.interfase.dto.MedioPagoDTO;

import java.util.List;

public interface IClientesService {
    void registrarCliente(ClienteDTO cliente);

    void altaMedioPago(String ciCliente, MedioPagoDTO medioPago);

    List<Cliente> obtenerClientes();

    Cliente obtenerCliente(String ciCliente);

    void realizarReclamo(String ciCliente, String comentario);
}
