package moduloMonitoreo.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloCargas.interfase.evento.out.CargasCargaIniciada;
import moduloCargas.interfase.evento.out.CargasNuevaCarga;
import moduloMonitoreo.infraestructura.RegistradorDeMetricas;

@ApplicationScoped
public class ObserverModuloCargas {
    @Inject
    private RegistradorDeMetricas registradorDeMetricas;

    public void accept(@Observes CargasCargaIniciada event) {
        registradorDeMetricas.cargaIniciada();
    }

    public void accept(@Observes CargasNuevaCarga event) {
        registradorDeMetricas.cargaFinalizada();
    }
}
