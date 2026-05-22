package moduloClientes.interfase;

import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.MedioPago;

import java.util.List;

public interface  IClienteService {
    void registrarCliente(ClienteDTO cliente);

    void altaMedioPago(String ciCliente, MedioPagoDTO medioPago);

    List<Cliente> obtenerClientes();

    Cliente obtenerCliente(String ciCliente);

    void realizarReclamo(ReclamoDTO dataReclamo);
}
