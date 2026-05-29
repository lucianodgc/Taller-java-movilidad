package moduloClientes.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloClientes.dominio.*;
import moduloClientes.dominio.repositorio.IClienteRepository;
import moduloClientes.interfase.dto.ClienteDTO;
import moduloClientes.interfase.IClientesService;
import moduloClientes.interfase.dto.MedioPagoDTO;
import moduloClientes.interfase.evento.out.PublicadorEvento;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class ClientesServiceImpl implements IClientesService {

    @Inject
    private IClienteRepository clienteRepository;

    @Inject
    private PublicadorEvento evento;

    private Cliente buscarClienteOExcepcion(String ciCliente) {
        Cliente cliente = clienteRepository.buscarPorCedula(ciCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe.");
        }
        return cliente;
    }

    @Override
    public void registrarCliente(ClienteDTO dataCliente) {
        Cliente cliente;

        if (dataCliente.getTipo().equals(TipoCliente.COMUN)) {
            cliente = new ClienteComun(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), dataCliente.getContraseña());

        } else {
            cliente = new ClienteProfesional(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), dataCliente.getContraseña(), dataCliente.getTipoProfesional(), dataCliente.getDescuento());
        }
        clienteRepository.guardarCliente(cliente);
        evento.publicarNuevoCliente(dataCliente.getCedula());
    }

    @Override
    public void altaMedioPago(String ciCliente, MedioPagoDTO dataMedioPago) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        String referencia = UUID.randomUUID().toString();

        MedioPago medio;
        if (dataMedioPago.getTipo().equals(TipoMedioPago.TARJETA)) {
            medio = new Tarjeta(referencia , cliente, dataMedioPago.getNumero(), dataMedioPago.getFechaVencimiento(), dataMedioPago.getDigitoVerificacion(), dataMedioPago.getTipoTarjeta());
        } else {
            medio = new CuentaUTE(referencia, cliente, dataMedioPago.getNumeroCuenta());
        }
        cliente.agregarMediosDePago(medio);
        clienteRepository.guardarMedioPago(medio);
        evento.publicarNuevoMedioPago(medio.getReferencia(), ciCliente);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.obtenerClientes();
    }

    @Override
    public void realizarReclamo(String ciCliente, String comentario) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        Reclamo reclamo = new Reclamo(cliente, comentario);
        cliente.agregarReclamo(reclamo);
        clienteRepository.guardarReclamo(reclamo);
    }

    @Override
    public Cliente obtenerCliente(String ciCliente) {
        return buscarClienteOExcepcion(ciCliente);
    }
}
