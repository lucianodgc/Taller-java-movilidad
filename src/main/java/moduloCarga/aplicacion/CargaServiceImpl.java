package moduloCarga.aplicacion;

import jakarta.inject.Inject;
import moduloCarga.dominio.*;
import moduloCarga.dominio.repositorio.CargaRepository;
import moduloCarga.interfase.CargaService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CargaServiceImpl implements CargaService {

    @Inject
    private CargaRepository repository;

    @Override
    public void iniciarCarga(Cliente cliente, MedioPago medioPago) {

    }

    @Override
    public Carga verCargaActual(Cliente cliente) {
        return repository;
    }

    @Override
    public List<Carga> verHistorico(Cliente cliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return List.of();
    }

    @Override
    public void finalizarCarga(Cargador cargador, int carga, LocalTime recargo) {

    }

    @Override
    public void altaEstacion(EstacionCarga estacionCarga) {

    }

    @Override
    public void altaCargador(Cargador cargador) {

    }

    @Override
    public EstacionCarga obtenerEstaciones() {
        return null;
    }
}
