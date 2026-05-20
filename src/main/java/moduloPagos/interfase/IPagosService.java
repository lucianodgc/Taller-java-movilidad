package moduloPagos.interfase;

import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;

import java.time.LocalTime;
import java.util.List;

public interface IPagosService {
    void pagarCarga(Cliente cliente, float importe, MedioPago medioPago);

    List<Pago> consultarPagos(Cliente cliente, LocalTime fechaInicio, LocalTime fechaFin);


}
