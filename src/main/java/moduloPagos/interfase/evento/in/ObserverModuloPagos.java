package moduloPagos.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloCargas.interfase.evento.out.CargasNuevaCarga;
import moduloClientes.interfase.evento.out.ClientesNuevoCliente;
import moduloClientes.interfase.evento.out.ClientesNuevoMedioPago;
import moduloPagos.interfase.CargaDTO;
import moduloPagos.interfase.IPagosService;
import moduloPagos.interfase.MedioPagoDTO;

@ApplicationScoped
public class ObserverModuloPagos {

    @Inject
    IPagosService pagosService;

    public void accept(@Observes ClientesNuevoCliente event) {
        pagosService.altaCliente(event.getCedula());
    }

    public void accept(@Observes CargasNuevaCarga event) {
        CargaDTO cargaDTO = new CargaDTO(event.getId(), event.getCiCliente(), event.getFechaInicio(), event.getFechaFin());
        pagosService.altaCarga(cargaDTO);
    }

    public void accept(@Observes ClientesNuevoMedioPago event) {
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(event.getReferencia(), event.getCiCliente());
        pagosService.altaMedioPago(medioPagoDTO);
    }
}
