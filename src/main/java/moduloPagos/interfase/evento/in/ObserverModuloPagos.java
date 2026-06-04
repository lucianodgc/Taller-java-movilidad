package moduloPagos.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloCargas.interfase.evento.out.CargasNuevaCarga;
import moduloClientes.interfase.evento.out.ClientesNuevoCliente;
import moduloClientes.interfase.evento.out.ClientesNuevoMedioPago;
import moduloPagos.dominio.TipoMedioPago;
import moduloPagos.interfase.dto.CargaDTO;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.dto.PagoDTO;

import java.time.LocalDateTime;

@ApplicationScoped
public class ObserverModuloPagos {

    @Inject
    private IPagosService pagosService;

    public void accept(@Observes ClientesNuevoCliente event) {
        pagosService.altaCliente(event.getCedula());
    }

    public void accept(@Observes CargasNuevaCarga event) {
        CargaDTO cargaDTO = new CargaDTO(event.getId(), event.getCiCliente(), event.getFechaInicio(), event.getFechaFin());
        pagosService.altaCarga(cargaDTO);
        PagoDTO pagoDTO = new PagoDTO(event.getCiCliente(), event.getImporteTotal(), LocalDateTime.now(), event.getReferenciaMedioPago());
        pagosService.pagarCarga(pagoDTO);
    }

    public void accept(@Observes ClientesNuevoMedioPago event) {
        TipoMedioPago tipoEnum = TipoMedioPago.valueOf(event.getTipo());
        pagosService.altaMedioPago(event.getCiCliente(), event.getReferencia(), tipoEnum);
    }
}
