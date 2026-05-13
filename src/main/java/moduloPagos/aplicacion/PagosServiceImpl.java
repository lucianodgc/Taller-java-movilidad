package moduloPagos.aplicacion;

import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;
import moduloPagos.interfase.PagosService;

import java.time.LocalTime;
import java.util.List;

public class PagosServiceImpl implements PagosService {
    @Override
    public void pagarCarga(Cliente cliente, float importe, MedioPago medioPago) {

    }

    @Override
    public List<Pago> consultarPagos(Cliente cliente, LocalTime fechaInicio, LocalTime fechaFin) {
        return List.of();
    }
}
