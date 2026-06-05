package moduloClientes.infraestructura.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import moduloClientes.dominio.Cliente;
import moduloClientes.dominio.MedioPago;
import moduloClientes.dominio.Reclamo;
import moduloClientes.dominio.repositorio.IClienteRepository;

import java.util.List;

@ApplicationScoped
public class ClienteRepositoryImpl implements IClienteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void guardarCliente(Cliente cliente) {
        if (cliente.getCedula() == null) {
            em.persist(cliente);
        } else {
            em.merge(cliente);
        }
    }

    @Override
    public Cliente buscarPorCedula(String ciCliente) {
        return em.find(Cliente.class, ciCliente);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        String sql = "SELECT c FROM ClienteClientes c";
        return em.createQuery(sql, Cliente.class).getResultList();
    }

    @Override
    public void guardarReclamo(Reclamo reclamo) {
        if (reclamo.getId() == null) {
            em.persist(reclamo);
        } else {
            em.merge(reclamo);
        }
    }

    @Override
    public Cliente buscarClienteConMediosPago(String ci) {
        return em.createQuery(
                        "SELECT c FROM ClienteClientes c LEFT JOIN FETCH c.mediosDePago WHERE c.cedula = :ci",
                        Cliente.class)
                .setParameter("ci", ci)
                .getSingleResult();
    }
}
