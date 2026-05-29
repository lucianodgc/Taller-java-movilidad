package moduloPagos.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;
import moduloPagos.dominio.repositorio.IPagosRepository;
import moduloPagos.interfase.dto.CargaDTO;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.dto.PagoDTO;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class PagosServiceImpl implements IPagosService {

    @Inject
    IPagosRepository pagosRepository;

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

        Pago pago = new Pago(cliente, pagoDTO.getMonto(), LocalDateTime.now(), pagoDTO.getMedioPago());
        cliente.agregarPago(pago);
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
    }

    @Override
    public void altaMedioPago(String ciCliente, String referencia) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);
        MedioPago medioPago = new MedioPago(referencia, cliente);
        cliente.agregarMedioPago(medioPago);
    }
}
