package moduloPagos.infraestructura.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.Pago;
import moduloPagos.dominio.repositorio.IPagosRepository;

import java.time.LocalDate;
import java.util.List;

public class PagosRepositoryImpl implements IPagosRepository {

    EntityManager em;

    @Override
    public void guardarPago(Pago pago) {
        if (pago.getId() == null) {
            em.persist(pago);
        } else {
            em.merge(pago);
        }
    }

    @Override
    public List<Pago> obtenerPagosCliente(String ciCliente) {
        String sql = "SELECT p FROM Pago p WHERE p.ciCliente = :ci";
        try {
            return em.createQuery(sql, Pago.class).setParameter("ci", ciCliente).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Carga> obtenerCargasCliente(String ciCliente, LocalDate fechaInicio, LocalDate fechaFin) {
        String sql = "SELECT c FROM Carga c WHERE c.ciCliente = :ci " + "AND c.fechaInicio BETWEEN :inicio AND :fin " + "ORDER BY c.fechaInicio DESC";
        try {
            return em.createQuery(sql, Carga.class)
                    .setParameter("ci", ciCliente)
                    .setParameter("inicio", fechaInicio)
                    .setParameter("fin", fechaFin)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Cliente buscarCliente(String ciCliente) {
        return em.find(Cliente.class, ciCliente);
    }
}
