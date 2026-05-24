package moduloCargas.infraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import moduloCargas.dominio.*;
import moduloCargas.dominio.repositorio.ICargaRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class CargaRepositoryImpl implements ICargaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void altaCliente(Cliente cliente) {
        if (cliente.getCedula() == null) {
            em.persist(cliente);
        } else {
            em.merge(cliente);
        }
    }

    @Override
    public Cliente buscarCliente(String idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    public List<Carga> buscarHistoricoCliente(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String sql = "SELECT c FROM CargaCargas c WHERE c.cliente.cedula = :ci " + "AND c.fechaInicio BETWEEN :inicio AND :fin " + "ORDER BY c.fechaInicio DESC";

        return em.createQuery(sql, Carga.class)
                .setParameter("ci", ciCliente)
                .setParameter("inicio", fechaInicio)
                .setParameter("fin", fechaFin)
                .getResultList();
    }

    @Override
    public boolean existeMedioPago(String ciCliente, String referenciaMedioPago) {
        try {
            String sql = "SELECT COUNT(c) FROM ClienteCargas c JOIN c.mediosDePago m WHERE c.cedula = :ci AND m.referencia = :ref";

            Long conteo = em.createQuery(sql, Long.class)
                    .setParameter("ci", ciCliente)
                    .setParameter("ref", referenciaMedioPago)
                    .getSingleResult();

            return conteo > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void guardarCargador(Cargador cargador) {
        if (cargador.getId() == null) {
            em.persist(cargador);
        } else {
            em.merge(cargador);
        }
    }

    @Override
    public Carga buscarCargaActiva(String ciCliente) {
        String sql = "SELECT c FROM CargaCargas c WHERE c.cliente.cedula = :ci AND c.estado = :estado";
        try {
            return em.createQuery(sql, Carga.class).setParameter("ci", ciCliente).setParameter("estado", EstadoCarga.ACTIVA).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Carga buscarCargaActivaPorCargador(String idCargador) {
        String sql = "SELECT c FROM CargaCargas c WHERE c.cargador.id = :idCargador AND c.estado = :estado";
        try {
            return em.createQuery(sql, Carga.class).setParameter("idCargador", Long.valueOf(idCargador)).setParameter("estado", EstadoCarga.ACTIVA).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Cargador buscarCargador(String idCargador) {
        try {
            return em.find(Cargador.class, Long.valueOf(idCargador));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public EstacionCarga buscarEstacion(String idEstacion) {
        try {
            return em.find(EstacionCarga.class, Long.valueOf(idEstacion));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<EstacionCarga> buscarEstaciones() {
        String sql = "SELECT e FROM EstacionCargaCargas e";
        return em.createQuery(sql, EstacionCarga.class).getResultList();
    }

    @Override
    public void guardarCarga(Carga carga) {
        if (carga.getId() == null) {
            em.persist(carga);
        } else {
            em.merge(carga);
        }
    }

    @Override
    public void guardarEstacion(EstacionCarga estacion) {
        if (estacion.getId() == null) {
            em.persist(estacion);
        } else {
            em.merge(estacion);
        }
    }
}
