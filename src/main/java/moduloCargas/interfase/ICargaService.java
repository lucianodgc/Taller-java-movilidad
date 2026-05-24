package moduloCargas.interfase;

import moduloCargas.dominio.*;
import moduloPagos.interfase.CargaDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ICargaService {
    void iniciarCarga(String ciCliente, String idCargador, String referenciaMedioPago);

    Carga verCargaActual(String ciCliente);

    List<Carga> verHistorico(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void finalizarCarga(String idCargador, int carga, LocalTime recargo);

    void altaEstacion(EstacionCargaDTO estacionCarga);

    void altaCargador(CargadorDTO cargadorDTO);

    List<EstacionCarga> obtenerEstaciones();

    void altaCliente(String ciCliente);

    void altaMedioPago(MedioPagoDTO medioPagoDTO);
}
