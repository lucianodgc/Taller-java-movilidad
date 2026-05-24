package moduloPagos.infraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import moduloPagos.dominio.Carga;
import moduloPagos.dominio.Cliente;
import moduloPagos.dominio.Pago;
import moduloPagos.dominio.repositorio.IPagosRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PagosRepositoryImpl implements IPagosRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Pago> obtenerPagosCliente(String ciCliente) {
        String sql = "SELECT p FROM PagoPagos p WHERE p.cliente.cedula = :ci";
        try {
            return em.createQuery(sql, Pago.class).setParameter("ci", ciCliente).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Carga> obtenerCargasCliente(String ciCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String sql = "SELECT c FROM CargaPagos c WHERE c.cliente.cedula = :ci " + "AND c.fechaInicio BETWEEN :inicio AND :fin " + "ORDER BY c.fechaInicio DESC";
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

    @Override
    public void altaCliente(Cliente cliente) {
        if (cliente.getCedula() == null) {
            em.persist(cliente);
        } else {
            em.merge(cliente);
        }
    }
}
