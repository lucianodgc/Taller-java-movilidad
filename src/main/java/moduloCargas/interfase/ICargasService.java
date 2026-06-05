package moduloCargas.interfase;

import moduloCargas.dominio.*;
import moduloCargas.interfase.dto.CargadorDTO;
import moduloCargas.interfase.dto.EstacionCargaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ICargasService {
    void iniciarCarga(String idCargador, String ciCliente, String referencia);

    Carga verCargaActual(String ciCliente);

    List<Carga> verHistorico(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void finalizarCarga(String idCargador, int carga, LocalTime recargo);

    void altaEstacion(EstacionCargaDTO estacionCarga);

    void registrarFallaCargador(String idCargador, LocalDate fechaReparacion);

    void altaCargador(CargadorDTO cargadorDTO);

    List<EstacionCarga> obtenerEstaciones();

    void altaCliente(String ciCliente, float descuento);

    void altaMedioPago(String ciCliente, String referencia);
}
