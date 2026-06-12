package moduloCargas.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import moduloCargas.dominio.Carga;

@ApplicationScoped
public class PublicadorEvento {

    @Inject
    private Event<CargasNuevaCarga> nuevaCargaEvent;

    @Inject
    private Event<CargasCargaIniciada> cargaIniciadaEvent;

    public void publicarCargaIniciada(Carga carga) {
        CargasCargaIniciada evento = new CargasCargaIniciada(
            carga.getId(),
            carga.getCliente().getCedula(),
            carga.getFechaInicio()
        );
        cargaIniciadaEvent.fire(evento);
    }

    public void publicarNuevaCarga(Carga carga) {
        CargasNuevaCarga evento = new CargasNuevaCarga(
                carga.getId(),
                carga.getCliente().getCedula(),
                carga.getImporteTotal(),
                carga.getReferenciaMedioPago(),
                carga.getFechaInicio(),
                carga.getFechaFin()
        );
        nuevaCargaEvent.fire(evento);
    }
}
