package moduloMonitoreo.interfase.evento.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import moduloClientes.interfase.evento.out.ClientesReclamoNegativo;
import moduloMonitoreo.infraestructura.RegistradorDeMetricas;

@ApplicationScoped
public class ObserverModuloClientes {
    @Inject
    private RegistradorDeMetricas registradorDeMetricas;

    public void accept(@Observes ClientesReclamoNegativo event) {
        registradorDeMetricas.reclamoNegativoProcesado();
    }
}
