package moduloMonitoreo.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloCargas.interfase.evento.out.CargasCargaIniciada;
import moduloCargas.interfase.evento.out.CargasNuevaCarga;
import moduloMonitoreo.infraestructura.RegistradorDeMetricas;
import moduloPagos.interfase.evento.out.PagosPagoFallido;
import moduloPagos.interfase.evento.out.PagosPagoRealizado;

@ApplicationScoped
public class observerModuloMonitoreo {
    
    @Inject
    private RegistradorDeMetricas registradorDeMetricas;

    public void accept(@Observes CargasCargaIniciada event) {
        registradorDeMetricas.cargaIniciada();
    }

    public void accept(@Observes CargasNuevaCarga event) {
        registradorDeMetricas.cargaFinalizada();
    }

    public void accept(@Observes PagosPagoRealizado event) {
        registradorDeMetricas.pagoRealizado(event.getTipoMedioPago());
    }

    public void accept(@Observes PagosPagoFallido event) {
        registradorDeMetricas.pagoFallido(event.getTipoMedioPago());
    }
}
