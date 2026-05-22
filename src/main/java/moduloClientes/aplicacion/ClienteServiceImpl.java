package moduloClientes.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloClientes.dominio.*;
import moduloClientes.dominio.repositorio.IClienteRepository;
import moduloClientes.interfase.ClienteDTO;
import moduloClientes.interfase.IClienteService;
import moduloClientes.interfase.MedioPagoDTO;
import moduloClientes.interfase.ReclamoDTO;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class ClienteServiceImpl implements IClienteService {

    @Inject
    private IClienteRepository clienteRepository;

    @Override
    public void registrarCliente(ClienteDTO dataCliente) {
        Cliente cliente;

        if (dataCliente.getTipo().equals(TipoCliente.COMUN)) {
            cliente = new ClienteComun(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), dataCliente.getContraseña());

        } else {
            cliente = new ClienteProfesional(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), dataCliente.getContraseña(), dataCliente.getTipoProfesional(), dataCliente.getDescuento());
        }
        clienteRepository.guardarCliente(cliente);
    }

    @Override
    public void altaMedioPago(String ciCliente, MedioPagoDTO dataMedioPago) {
        Cliente cliente = clienteRepository.buscarPorCedula(ciCliente);
        if (cliente == null) throw new RuntimeException("Cliente no existe");

        String referencia = UUID.randomUUID().toString();

        MedioPago medio;
        if (dataMedioPago.getTipo().equals(TipoMedioPago.TARJETA)) {
            medio = new Tarjeta(referencia , cliente, dataMedioPago.getNumero(), dataMedioPago.getFechaVencimiento(), dataMedioPago.getDigitoVerificacion(), dataMedioPago.getTipoTarjeta());
        } else {
            medio = new CuentaUTE(referencia, cliente, dataMedioPago.getNumeroCuenta());
        }
        cliente.agregarMediosDePago(medio);
        clienteRepository.guardarMedioPago(medio);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.obtenerClientes();
    }

    @Override
    public void realizarReclamo(ReclamoDTO dataReclamo) {
        Cliente cliente = clienteRepository.buscarPorCedula(dataReclamo.getCliente().getCedula());
        if (cliente == null) throw new RuntimeException("Cliente no existe");

        Reclamo reclamo = new Reclamo(dataReclamo.getCliente(), dataReclamo.getComentario());
        cliente.agregarReclamo(reclamo);
        clienteRepository.guardarReclamo(reclamo);
    }

    @Override
    public Cliente obtenerCliente(String ciCliente) {
        return clienteRepository.buscarPorCedula(ciCliente);
    }
}
