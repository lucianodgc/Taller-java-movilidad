package moduloPagos.dominio.repositorio;

import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.Pago;

import java.time.LocalDate;
import java.util.List;

public interface IPagosRepository {
    void guardarPago(Pago pago);

    List<Pago> obtenerPagosCliente(String ciCliente);

    List<Carga> obtenerCargasCliente(String ciCliente, LocalDate fechaInicio, LocalDate fechaFin);

    Cliente buscarCliente(String ciCliente);
}
