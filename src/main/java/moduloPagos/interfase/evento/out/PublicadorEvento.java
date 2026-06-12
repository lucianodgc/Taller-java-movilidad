package moduloPagos.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
    
    @Inject
    private Event<PagosPagoRealizado> pagoRealizadoEvent;

    @Inject
    private Event<PagosPagoFallido> pagoFallidoEvent;

    public void publicarPagoRealizado(String ciCliente, float monto, String tipoMedioPago) {
        pagoRealizadoEvent.fire(new PagosPagoRealizado(ciCliente, monto, tipoMedioPago));
    }

    public void publicarPagoFallido(String ciCLiente, float monto, String tipoMedioPago, String error) {
        pagoFallidoEvent.fire(new PagosPagoFallido(ciCLiente, monto, tipoMedioPago, error));
    }
}
