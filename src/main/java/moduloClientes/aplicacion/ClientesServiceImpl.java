package moduloClientes.aplicacion;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import moduloClientes.dominio.*;
import moduloClientes.dominio.repositorio.IClienteRepository;
import moduloClientes.infraestructura.messaging.EnviarReclamoQueueUtil;
import moduloClientes.infraestructura.messaging.ReclamoMessage;
import moduloClientes.interfase.dto.ClienteDTO;
import moduloClientes.interfase.IClientesService;
import moduloClientes.interfase.dto.MedioPagoDTO;
import moduloClientes.interfase.evento.out.PublicadorEvento;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class ClientesServiceImpl implements IClientesService {

    @Inject
    private IClienteRepository clienteRepository;

    @Inject
    private PublicadorEvento evento;

    @Inject
    private EnviarReclamoQueueUtil enviarReclamoUtil;

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
        String claveHasheada = BCrypt.hashpw(dataCliente.getContraseña(), BCrypt.gensalt());

        if (dataCliente.getTipo().equals(TipoCliente.COMUN)) {
            cliente = new ClienteComun(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), claveHasheada);

        } else {
            cliente = new ClienteProfesional(dataCliente.getCedula(), dataCliente.getNombreCompleto(), dataCliente.getTelefono(), claveHasheada, dataCliente.getTipoProfesional(), dataCliente.getDescuento());
        }
        if (clienteRepository.buscarPorCedula(dataCliente.getCedula()) != null) {
            throw new IllegalArgumentException("El cliente ya existe.");
        }
        clienteRepository.guardarCliente(cliente);
        evento.publicarNuevoCliente(dataCliente.getCedula(), dataCliente.getDescuento());
    }

    @Override
    public void altaMedioPago(String ciCliente, MedioPagoDTO dataMedioPago) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        String referencia = UUID.randomUUID().toString();

        MedioPago medio;
        if (dataMedioPago.getTipo().equals(TipoMedioPago.TARJETA)) {
            if (cliente.getMediosDePago().stream()
                    .filter(mp -> mp instanceof Tarjeta)
                    .map(mp -> (Tarjeta) mp)
                    .anyMatch(t -> t.getNumero().equals(dataMedioPago.getNumero()))
            ) {
                throw new IllegalArgumentException("Este medio de pago ya existe.");
            }

            medio = new Tarjeta(referencia , cliente, dataMedioPago.getNumero(), dataMedioPago.getFechaVencimiento(), dataMedioPago.getDigitoVerificacion(), dataMedioPago.getTipoTarjeta());
        } else {
            if (cliente.getMediosDePago().stream()
                    .filter(mp -> mp instanceof CuentaUTE)
                    .map(mp -> (CuentaUTE) mp)
                    .anyMatch(t -> t.getNumeroCuenta().equals(dataMedioPago.getNumeroCuenta()))
            ) {
                throw new IllegalArgumentException("Este medio de pago ya existe.");
            }
            medio = new CuentaUTE(referencia, cliente, dataMedioPago.getNumeroCuenta());
        }

        cliente.agregarMediosDePago(medio);
        evento.publicarNuevoMedioPago(medio.getReferencia(), ciCliente, dataMedioPago.getTipo().name());
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.obtenerClientes();
    }

    @Override
    public void realizarReclamo(String ciCliente, String comentario) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        ReclamoMessage mensaje = new ReclamoMessage(ciCliente, comentario);

        enviarReclamoUtil.enviarMensaje(mensaje.toJson());
    }

    @Override
    public void guardarReclamoProcesado(String ciCliente, String comentario, String etiqueta) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        Reclamo reclamo = new Reclamo(cliente, comentario, etiqueta);
        cliente.agregarReclamo(reclamo);
        clienteRepository.guardarReclamo(reclamo);
    }

    @Override
    public List<MedioPago> obtenerMediosPagoCliente(String ciCliente) {
        Cliente cliente = clienteRepository.buscarClienteConMediosPago(ciCliente);
        return cliente.getMediosDePago();
    }
}
