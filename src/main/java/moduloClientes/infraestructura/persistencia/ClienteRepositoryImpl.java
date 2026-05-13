package moduloClientes.infraestructura.persistencia;

import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.repositorio.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    private ArrayList<Cliente> clientes = new ArrayList<>();

    public void guardar(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarPorCedula(String cedula) {
        return clientes.stream()
                .filter(c -> c.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> obtenerTodos() {
        return clientes;
    }

}
