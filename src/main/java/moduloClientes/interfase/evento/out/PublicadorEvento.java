package moduloClientes.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
    @Inject
    private Event<ClientesNuevoCliente> nuevoClienteEvent;
    private Event<ClientesNuevoMedioPago> nuevoMedioPagoEvent;

    public void publicarNuevoCliente(String ciCliente) {
        ClientesNuevoCliente evento = new ClientesNuevoCliente(ciCliente);
        nuevoClienteEvent.fire(evento);
    }

    public void publicarNuevoMedioPago(String referencia, String ciCliente) {
        ClientesNuevoMedioPago evento = new ClientesNuevoMedioPago(referencia, ciCliente);
        nuevoMedioPagoEvent.fire(evento);
    }
}
