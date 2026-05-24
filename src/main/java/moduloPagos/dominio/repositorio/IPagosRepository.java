package moduloPagos.dominio.repositorio;

import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.Pago;

import java.time.LocalDateTime;
import java.util.List;

public interface IPagosRepository {

    List<Pago> obtenerPagosCliente(String ciCliente);

    List<Carga> obtenerCargasCliente(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Cliente buscarCliente(String ciCliente);

    void altaCliente(Cliente cliente);
}
