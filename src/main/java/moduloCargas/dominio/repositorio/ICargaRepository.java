package moduloCargas.dominio.repositorio;

import moduloCargas.dominio.Carga;
import moduloCargas.dominio.Cargador;
import moduloCargas.dominio.Cliente;
import moduloCargas.dominio.EstacionCarga;

import java.time.LocalDateTime;
import java.util.List;

public interface ICargaRepository {
    void altaCliente(Cliente cliente);
    Cliente buscarCliente(String id);
    List<Carga> buscarHistoricoCliente(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    boolean existeMedioPago(String ciCliente, String referenciaMedioPago);

    Carga buscarCargaActivaPorCargador(String idCargador);
    void guardarCargador(Cargador cargador);
    Carga buscarCargaActiva(String ciCliente);
    Cargador buscarCargador(String id);
    EstacionCarga buscarEstacion(String idEstacion);
    List<EstacionCarga> buscarEstaciones();
    void guardarCarga(Carga nuevaCarga);
    void guardarEstacion(EstacionCarga estacion);

}
