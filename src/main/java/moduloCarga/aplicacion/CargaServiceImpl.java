package moduloCarga.aplicacion;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloCarga.dominio.*;
import moduloCarga.dominio.repositorio.ICargaRepository;
import moduloCarga.interfase.CargadorDTO;
import moduloCarga.interfase.EstacionCargaDTO;
import moduloCarga.interfase.ICargaService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class CargaServiceImpl implements ICargaService {

    @Inject
    private ICargaRepository cargaRepository;

    @Override
    public void iniciarCarga(String ciCliente, String idCargador, String referenciaMedioPago) {

        Cargador cargador = cargaRepository.buscarCargador(idCargador);
        if (cargador == null || cargador.getEstado() != EstadoCargador.DISPONIBLE) {
            throw new IllegalStateException("Cargador no disponible.");
        }

        Cliente cliente = cargaRepository.buscarCliente(ciCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no existe.");
        }

        boolean medioValido = cargaRepository.existeMedioPago(ciCliente, referenciaMedioPago);
        if (!medioValido) {
            throw new IllegalArgumentException("Medio de pago no asociado al cliente.");
        }

        cargador.setEstado(EstadoCargador.OCUPADO);
        cargaRepository.guardarCargador(cargador);
        Carga nuevaCarga = new Carga(ciCliente, cargador, LocalDateTime.now(), EstadoCarga.ACTIVA);
        nuevaCarga.setReferenciaMedioPago(referenciaMedioPago);
        cargador.agregarCargas(nuevaCarga);

        cargaRepository.guardarCarga(nuevaCarga);
    }

    @Override
    public Carga verCargaActual(String ciCliente) {
        Cliente cliente = cargaRepository.buscarCliente(ciCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no existe.");
        }
        return cargaRepository.buscarCargaActiva(ciCliente);
    }

    @Override
    public List<Carga> verHistorico(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Carga> historico = cargaRepository.buscarHistoricoCliente(ciCliente, fechaInicio, fechaFin);
        if (historico == null || historico.isEmpty()) {
            return new ArrayList<>();
        }
        return historico;
    }

    @Override
    public void finalizarCarga(String idCargador, int carga, LocalTime recargo) {

        Carga cargaActiva = cargaRepository.buscarCargaActivaPorCargador(idCargador);
        if (cargaActiva == null) {
            throw new IllegalStateException("No hay ninguna carga activa registrada para este cargador.");
        }

        Cargador cargador = cargaActiva.getCargador();
        if (cargador == null) {
            throw new IllegalArgumentException("El cargador no existe.");
        }

        float PRECIO_POR_KWH = 15.0f;
        float importeEnergia = carga * PRECIO_POR_KWH;

        int minutosDemora = (recargo.getHour() * 60) + recargo.getMinute();

        float PRECIO_POR_MINUTO_DEMORA = 8.0f;
        float costoDemora = minutosDemora * PRECIO_POR_MINUTO_DEMORA;


        cargaActiva.setFechaFin(LocalDateTime.now());
        cargaActiva.setEstado(EstadoCarga.FINALIZADA);
        cargaActiva.setRecargaPorDemora(costoDemora);
        cargaActiva.setImporteTotal(importeEnergia + costoDemora);
        cargaActiva.setPorcentajeAvance(100);

        cargador.setEstado(EstadoCargador.DISPONIBLE);

        cargaRepository.guardarCarga(cargaActiva);
        cargaRepository.guardarCargador(cargador);
    }

    @Override
    public void altaEstacion(EstacionCargaDTO estacionCarga) {

        EstacionCarga nuevaEstacion = new EstacionCarga(
                estacionCarga.getDescripcion(),
                estacionCarga.getCalle(),
                estacionCarga.getDepartamento(),
                estacionCarga.getLongitud(),
                estacionCarga.getLatitud());

        cargaRepository.guardarEstacion(nuevaEstacion);
    }

    @Override
    public void altaCargador(CargadorDTO cargadorDTO) {
        if (cargadorDTO == null) {
            throw new IllegalArgumentException("Los datos del cargador no pueden ser nulos.");
        }

        EstacionCarga estacion = cargaRepository.buscarEstacion(cargadorDTO.getIdEstacion());
        if (estacion == null) {
            throw new IllegalArgumentException("La estación de carga no existe.");
        }

        Cargador nuevoCargador = new Cargador(
                cargadorDTO.getTipoCargador(),
                cargadorDTO.isTieneCable(),
                cargadorDTO.getTipoConector(),
                estacion
        );
        if (cargadorDTO.getTipoCargador().equals(TipoCargador.RAPIDO)) {
            nuevoCargador.setPotenciaMinima(cargadorDTO.getPotenciaMinima());
        }
        if (cargadorDTO.getEstadoCargador().equals(EstadoCargador.FUERA_DE_SERVICIO)) {
            nuevoCargador.setFechaEstimadaReparacion(cargadorDTO.getFechaEstimadaReparacion());
        }
        nuevoCargador.setEstado(EstadoCargador.DISPONIBLE);
        estacion.agregarCargador(nuevoCargador);

        cargaRepository.guardarCargador(nuevoCargador);
    }

    @Override
    public List<EstacionCarga> obtenerEstaciones() {
        List<EstacionCarga> estaciones = cargaRepository.buscarEstaciones();

        if (estaciones == null || estaciones.isEmpty()) {
            return new ArrayList<>();
        }
        return estaciones;
    }
}
