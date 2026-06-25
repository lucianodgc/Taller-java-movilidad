package moduloCargas.aplicacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import moduloCargas.dominio.*;
import moduloCargas.dominio.repositorio.ICargaRepository;
import moduloCargas.interfase.dto.CargadorDTO;
import moduloCargas.interfase.dto.EstacionCargaDTO;
import moduloCargas.interfase.ICargasService;
import moduloCargas.interfase.evento.out.PublicadorEvento;
import moduloClientes.dominio.ClienteProfesional;
import moduloClientes.dominio.Tarjeta;
import moduloClientes.dominio.TipoMedioPago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Transactional
public class CargasServiceImpl implements ICargasService {

    @Inject
    private ICargaRepository cargaRepository;

    @Inject
    private PublicadorEvento evento;

    private Cliente buscarClienteOExcepcion(String ciCliente) {
        Cliente cliente = cargaRepository.buscarCliente(ciCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe.");
        }
        return cliente;
    }

    private Cargador buscarCargadorOExcepcion(String idCargador) {
        Cargador cargador = cargaRepository.buscarCargador(idCargador);
        if (cargador == null) {
            throw new IllegalArgumentException("El cargador no existe.");
        }
        return cargador;
    }

    private EstacionCarga buscarEstacionOExcepcion(String idEstacion) {
        EstacionCarga estacion = cargaRepository.buscarEstacion(idEstacion);
        if (estacion == null) {
            throw new IllegalArgumentException("La estación de carga no existe.");
        }
        return estacion;
    }

    @Override
    public void iniciarCarga(String idCargador, String ciCliente, String referencia) {
        Cargador cargador = buscarCargadorOExcepcion(idCargador);
        if (cargador.getEstado() != EstadoCargador.DISPONIBLE) {
            throw new IllegalStateException("El cargador no está disponible.");
        }

        Cliente cliente = buscarClienteOExcepcion(ciCliente);

        boolean medioValido = cargaRepository.existeMedioPago(ciCliente, referencia);
        if (!medioValido) {
            throw new IllegalArgumentException("El medio de pago no está asociado al cliente.");
        }

        cargador.setEstado(EstadoCargador.OCUPADO);
        cargaRepository.guardarCargador(cargador);

        Carga nuevaCarga = new Carga(cliente, cargador, LocalDateTime.now(), EstadoCarga.ACTIVA);
        nuevaCarga.setReferenciaMedioPago(referencia);
        cargador.agregarCargas(nuevaCarga);

        cargaRepository.guardarCarga(nuevaCarga);
        evento.publicarCargaIniciada(nuevaCarga);
    }

    @Override
    public Carga verCargaActual(String ciCliente) {
        buscarClienteOExcepcion(ciCliente);

        Carga carga = cargaRepository.buscarCargaActiva(ciCliente);
        if (carga == null) {
            throw new IllegalStateException("El cliente no tiene ninguna carga activa.");
        }
        return carga;
    }

    @Override
    public List<Carga> verHistorico(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        buscarClienteOExcepcion(ciCliente);

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

        Cargador cargador = cargaRepository.buscarCargador(cargaActiva.getCargador().getId().toString());
        if (cargador == null) {
            throw new IllegalArgumentException("El cargador no existe.");
        }

        float PRECIO_POR_KWH = 15.0f;
        float importeEnergia = carga * PRECIO_POR_KWH;

        int minutosDemora = (recargo.getHour() * 60) + recargo.getMinute();

        float PRECIO_POR_MINUTO_DEMORA = 8.0f;
        float costoDemora = minutosDemora * PRECIO_POR_MINUTO_DEMORA;

        float subtotal = importeEnergia + costoDemora;
        float importeTotal = subtotal;
        float porcentajeDescuento = cargaActiva.getCliente().getDescuento();

        if (porcentajeDescuento > 0) {
            float montoDescontado = subtotal * (porcentajeDescuento / 100f);
            importeTotal = subtotal - montoDescontado;
        }

        cargaActiva.setFechaFin(LocalDateTime.now());
        cargaActiva.setEstado(EstadoCarga.FINALIZADA);
        cargaActiva.setRecargaPorDemora(costoDemora);
        cargaActiva.setImporteTotal(importeTotal);
        cargaActiva.setPorcentajeAvance(100);

        cargador.setEstado(EstadoCargador.DISPONIBLE);

        cargaRepository.guardarCarga(cargaActiva);
        cargaRepository.guardarCargador(cargador);

        evento.publicarNuevaCarga(cargaActiva);
    }

    @Override
    public void altaEstacion(EstacionCargaDTO estacionCarga) {
        if (estacionCarga == null) {
            throw new IllegalArgumentException("Los datos de la estación no pueden ser nulos.");
        }

        List<EstacionCarga> estaciones = cargaRepository.buscarEstaciones();
        if (estaciones.stream().anyMatch(ec ->
                ec.getDescripcion().equals(estacionCarga.getDescripcion()) &&
                ec.getCalle().equals(estacionCarga.getCalle()) &&
                ec.getDepartamento().equals(estacionCarga.getDepartamento()) &&
                ec.getLongitud().equals(estacionCarga.getLongitud()) &&
                ec.getLatitud().equals(estacionCarga.getLatitud())
        ))
            throw new IllegalArgumentException("Esta estación de carga ya existe.");

        EstacionCarga nuevaEstacion = new EstacionCarga(
                estacionCarga.getDescripcion(),
                estacionCarga.getCalle(),
                estacionCarga.getDepartamento(),
                estacionCarga.getLongitud(),
                estacionCarga.getLatitud()
        );

        cargaRepository.guardarEstacion(nuevaEstacion);
    }

    @Override
    public void altaCargador(CargadorDTO cargadorDTO) {
        if (cargadorDTO == null) {
            throw new IllegalArgumentException("Los datos del cargador no pueden ser nulos.");
        }

        EstacionCarga estacion = buscarEstacionOExcepcion(cargadorDTO.getIdEstacion());

        if (estacion.getCargadores().stream().anyMatch(c ->
                c.getTieneCable() == cargadorDTO.isTieneCable() &&
                Objects.equals(c.getTipoConector(), cargadorDTO.getTipoConector()) &&
                Objects.equals(c.getTipoCargador(), cargadorDTO.getTipoCargador()) &&
                c.getPotenciaMinima() == cargadorDTO.getPotenciaMinima()
        ))
        throw new IllegalArgumentException("Este cargador ya existe.");

        Cargador nuevoCargador = new Cargador(
                cargadorDTO.getTipoCargador(),
                cargadorDTO.isTieneCable(),
                cargadorDTO.getTipoConector(),
                estacion
        );
        if (cargadorDTO.getTipoCargador().equals(TipoCargador.RAPIDO)) {
            nuevoCargador.setPotenciaMinima(cargadorDTO.getPotenciaMinima());
        }

        nuevoCargador.setEstado(EstadoCargador.DISPONIBLE);
        estacion.agregarCargador(nuevoCargador);

        cargaRepository.guardarCargador(nuevoCargador);
    }

    @Override
    public void registrarFallaCargador(String idCargador, LocalDate fechaReparacion) {
        Cargador cargador = buscarCargadorOExcepcion(idCargador);
        cargador.setEstado(EstadoCargador.FUERA_DE_SERVICIO);
        cargador.setFechaEstimadaReparacion(fechaReparacion);
    }

    @Override
    public List<EstacionCarga> obtenerEstaciones() {
        List<EstacionCarga> estaciones = cargaRepository.buscarEstaciones();

        if (estaciones == null || estaciones.isEmpty()) {
            return new ArrayList<>();
        }
        return estaciones;
    }

    @Override
    public void altaCliente(String ciCliente, float descuento) {
        Cliente cliente = new Cliente(ciCliente, descuento);
        cargaRepository.altaCliente(cliente);
    }

    @Override
    public void altaMedioPago(String ciCliente, String referencia) {
        Cliente cliente = buscarClienteOExcepcion(ciCliente);
        MedioPago medioPago = new MedioPago(referencia, cliente);
        cliente.agregarMedioPago(medioPago);
    }
}
