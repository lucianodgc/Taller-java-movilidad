package moduloPagos.interfase;

import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.MedioPago;
import moduloPagos.dominio.Pago;

import java.time.LocalDate;
import java.util.List;

public interface IPagosService {
    void pagarCarga(String ciCliente, float importe, MedioPago medioPago);

    List<Pago> consultarPagos(PagoDTO dataPago, LocalDate fechaInicio, LocalDate fechaFin);


}
