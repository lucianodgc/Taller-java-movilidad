package moduloClientes.dominio.repositorio;

import moduloClientes.dominio.Cliente;

import java.util.List;

public interface IClienteRepository {
    void guardar(Cliente cliente);
    Cliente buscarPorCedula(String cedula);
    List<Cliente> obtenerTodos();
}
