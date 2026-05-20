package moduloClientes.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import moduloClientes.dominio.*;
import moduloClientes.dominio.repositorio.IClienteRepository;
import moduloClientes.interfase.ClienteDTO;
import moduloClientes.interfase.IClienteService;

import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements IClienteService {

    @Inject
    private IClienteRepository repository;

    @Override
    public void registrarCliente(ClienteDTO cliente) {
        if(cliente.getTipo().equals(TipoCliente.Comun)) {
            ClienteComun cli = new ClienteComun(cliente.getCedula(), cliente.getNombreCompleto(), cliente.getTelefono(), cliente.getContraseña());

            repository.guardar(cli);
        } else if(cliente.getTipo().equals(TipoCliente.Profesional)) {
            ClienteProfesional cli = new ClienteProfesional(cliente.getCedula(), cliente.getNombreCompleto(), cliente.getTelefono(), cliente.getContraseña(), cliente.getTipoProfesional(), cliente.getDescuento());

            repository.guardar(cli);
        }
    }

    @Override
    public void altaMedioPago(Cliente cliente, MedioPago medioPago) {
        Cliente cli = repository.buscarPorCedula(cliente.getCedula());
        if (cli != null)
            cli.agregarMedioPago(medioPago);
        else {
            throw new RuntimeException("Cliente es null");
        }
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return repository.obtenerTodos();
    }

    @Override
    public void realizarReclamo() {

    }

    @Override
    public Cliente obtenerCliente(String cedula) {
        return repository.buscarPorCedula(cedula);
    }
}
