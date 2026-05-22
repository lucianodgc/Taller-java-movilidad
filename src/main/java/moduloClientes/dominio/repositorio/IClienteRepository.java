package moduloClientes.dominio.repositorio;

import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.MedioPago;
import moduloClientes.dominio.Reclamo;

import java.util.List;

public interface IClienteRepository {
    void guardarCliente(Cliente cliente);
    Cliente buscarPorCedula(String cedula);
    List<Cliente> obtenerClientes();
    void guardarMedioPago(MedioPago medioPago);
    void guardarReclamo(Reclamo reclamo);
}
