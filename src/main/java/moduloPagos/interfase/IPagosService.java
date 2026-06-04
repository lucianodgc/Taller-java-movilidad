package moduloPagos.interfase;

import moduloPagos.dominio.Pago;
import moduloPagos.dominio.TipoMedioPago;
import moduloPagos.interfase.dto.CargaDTO;
import moduloPagos.interfase.dto.PagoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IPagosService {
    void pagarCarga(PagoDTO pagoDTO);

    List<Pago> consultarPagos(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void altaCliente(String ciCliente);

    void altaCarga(CargaDTO cargaDTO);

    void altaMedioPago(String ciCliente, String referencia, TipoMedioPago tipoMedioPago);
}
