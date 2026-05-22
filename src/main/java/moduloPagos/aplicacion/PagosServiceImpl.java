package moduloPagos.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;
import moduloPagos.dominio.repositorio.IPagosRepository;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.PagoDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class PagosServiceImpl implements IPagosService {

    @Inject
    IPagosRepository pagosRepository;

    @Override
    public void pagarCarga(String ciCliente, float importe, MedioPago medioPago) {
        Cliente cliente = pagosRepository.buscarCliente(ciCliente);
        if (cliente == null) throw new IllegalArgumentException("Cliente no existe.");

        Pago pago = new Pago(ciCliente, importe, LocalDateTime.now(), medioPago);
        cliente.agregarPago(pago);

        pagosRepository.guardarPago(pago);
    }

    @Override
    public List<Pago> consultarPagos(PagoDTO dataPago, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Pago> pagos = pagosRepository.obtenerPagosCliente(dataPago.getCliente().getCedula());
        List<Carga> cargas = pagosRepository.obtenerCargasCliente(dataPago.getCliente().getCedula(), fechaInicio, fechaFin);
        if (cargas.size() != pagos.size()) {
            throw new RuntimeException("La lista de pagos no coincide con la lista de cargas");
        }
        return pagos;
    }
}
