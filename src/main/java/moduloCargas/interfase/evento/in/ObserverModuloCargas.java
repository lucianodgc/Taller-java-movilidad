package moduloCargas.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloCargas.interfase.ICargasService;
import moduloClientes.interfase.evento.out.ClientesNuevoCliente;
import moduloClientes.interfase.evento.out.ClientesNuevoMedioPago;

@ApplicationScoped
public class ObserverModuloCargas {

    @Inject
    ICargasService cargaService;

    public void accept(@Observes ClientesNuevoCliente event) {
        cargaService.altaCliente(event.getCedula());
    }

    public void accept(@Observes ClientesNuevoMedioPago event) {
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO(event.getReferencia(), event.getCiCliente());
        cargaService.altaMedioPago(medioPagoDTO);
    }
}
