package moduloPagos.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;
import moduloPagos.dominio.repositorio.IPagosRepository;
import moduloPagos.interfase.CargaDTO;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.MedioPagoDTO;
import moduloPagos.interfase.PagoDTO;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class PagosServiceImpl implements IPagosService {

    @Inject
    IPagosRepository pagosRepository;

    @Override
    public void pagarCarga(PagoDTO pagoDTO) {
        Cliente cliente = pagosRepository.buscarCliente(pagoDTO.getCliente());
        if (cliente == null) throw new IllegalArgumentException("Cliente no existe.");

        Pago pago = new Pago(cliente, pagoDTO.getMonto(), LocalDateTime.now(), pagoDTO.getMedioPago());
        cliente.agregarPago(pago);
    }

    @Override
    public List<Pago> consultarPagos(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
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
        Cliente cliente = pagosRepository.buscarCliente(cargaDTO.getCiCliente());
        Carga carga = new Carga(cargaDTO.getId(), cliente, cargaDTO.getFechaInicio(), cargaDTO.getFechaFin());
        cliente.agregarCarga(carga);
    }

    @Override
    public void altaMedioPago(MedioPagoDTO medioPagoDTO) {
        Cliente cliente = pagosRepository.buscarCliente(medioPagoDTO.getCiCliente());
        MedioPago medioPago = new MedioPago(medioPagoDTO.getReferencia(), cliente);
        cliente.agregarMedioPago(medioPago);
    }
}
