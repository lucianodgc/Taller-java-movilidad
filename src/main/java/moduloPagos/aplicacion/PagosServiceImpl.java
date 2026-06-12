package moduloPagos.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import moduloPagos.dominio.*;
import moduloPagos.dominio.repositorio.IPagoRepository;
import moduloPagos.interfase.dto.CargaDTO;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.dto.PagoDTO;
import moduloPagos.interfase.evento.out.PublicadorEvento;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class PagosServiceImpl implements IPagosService {

    @Inject
    IPagoRepository pagosRepository;

    @Inject
    PublicadorEvento publicadorEvento;

    private Cliente buscarClienteOExcepcion(String ciCliente) {
        Cliente cliente = pagosRepository.buscarCliente(ciCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe.");
        }
        return cliente;
    }

    @Override
    public void pagarCarga(PagoDTO pagoDTO) {
        Cliente cliente = buscarClienteOExcepcion(pagoDTO.getCliente());

        MedioPago medioPago = null;
        for (MedioPago mp : cliente.getMediosDePago()) {
            if (mp.getReferencia().equals(pagoDTO.getMedioPago())) {
                medioPago = mp;
                break;
            }
        }
        if (medioPago == null) {
            throw new RuntimeException("Medio de pago incorrecto");
        }

        Carga carga = pagosRepository.buscarCarga(pagoDTO.getIdCarga());
        Pago pago = new Pago(cliente, pagoDTO.getMonto(), LocalDateTime.now(), medioPago, carga);

        Client client = ClientBuilder.newClient();

        try {
            String urlMock;

            if (medioPago.getTipoMedioPago().equals(TipoMedioPago.TARJETA)) {
                urlMock = "http://localhost:8081/ServicioMedioPagoMock/api/pagos";
            } else {
                urlMock = "http://localhost:8082/FacturaUTEMock/api/pagos";
            }

            Response response = client.target(urlMock)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(pagoDTO));

            if (response.getStatus() == 200 || response.getStatus() == 201) {
                cliente.agregarPago(pago);
                publicadorEvento.publicarPagoRealizado(cliente.getCedula(), pagoDTO.getMonto(), medioPago.getTipoMedioPago().name());
            } else {
                String errorMsg = response.readEntity(String.class);
                publicadorEvento.publicarPagoFallido(cliente.getCedula(), pagoDTO.getMonto(), medioPago.getTipoMedioPago().name(), errorMsg);
                throw new IllegalStateException("Pago rechazado por el sistema externo: " + errorMsg);
            }
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            publicadorEvento.publicarPagoFallido(cliente.getCedula(), pagoDTO.getMonto(), medioPago.getTipoMedioPago().name(), e.getMessage());
            throw new RuntimeException("No se pudo conectar con el mock de pagos", e);
        } finally {
            client.close();
        }
    }

    @Override
    public List<Pago> consultarPagos(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        buscarClienteOExcepcion(ciCliente);
        List<Pago> pagos = pagosRepository.obtenerPagosCliente(ciCliente);
        List<Carga> cargas = pagosRepository.obtenerCargasCliente(ciCliente, fechaInicio, fechaFin);
        if (cargas.size() != pagos.size()) {
            throw new RuntimeException("La lista de pagos no coincide con la lista de cargas");
        }
        return pagos;
    }

    @Override
    public void altaCliente(String ciCliente) {
        Cliente cliente = new Cliente(ciCliente);
        pagosRepository.altaCliente(cliente);
    }

    @Override
    public void altaCarga(CargaDTO cargaDTO) {
        Cliente cliente = buscarClienteOExcepcion(cargaDTO.getCiCliente());
        Carga carga = new Carga(cargaDTO.getId(), cliente, cargaDTO.getFechaInicio(), cargaDTO.getFechaFin());
        cliente.agregarCarga(carga);
        pagosRepository.guardarCliente(cliente);
    }

    @Override
    public void altaMedioPago(String ciCliente, String referencia, TipoMedioPago tipo) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);
        MedioPago medioPago = new MedioPago(referencia, cliente, tipo);
        cliente.agregarMedioPago(medioPago);
    }

}
