package moduloClientes.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import moduloClientes.dominio.TipoMedioPago;

@ApplicationScoped
public class PublicadorEvento {
    @Inject
    private Event<ClientesNuevoCliente> nuevoClienteEvent;
    @Inject
    private Event<ClientesNuevoMedioPago> nuevoMedioPagoEvent;

    public void publicarNuevoCliente(String ciCliente) {
        ClientesNuevoCliente evento = new ClientesNuevoCliente(ciCliente);
        nuevoClienteEvent.fire(evento);
    }

    public void publicarNuevoMedioPago(String referencia, String ciCliente, String tipoMedioPago) {
        ClientesNuevoMedioPago evento = new ClientesNuevoMedioPago(referencia, ciCliente, tipoMedioPago);
        nuevoMedioPagoEvent.fire(evento);
    }
}
