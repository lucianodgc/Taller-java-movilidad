package moduloCarga.interfase;

import moduloCarga.dominio.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface CargaService {
    void iniciarCarga(Cliente cliente, MedioPago medioPago);

    Carga verCargaActual(Cliente cliente);

    List<Carga> verHistorico(Cliente cliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void finalizarCarga(Cargador cargador, int carga, LocalTime recargo);

    void altaEstacion(EstacionCarga estacionCarga);

    void altaCargador(Cargador cargador);

    EstacionCarga obtenerEstaciones();
}
