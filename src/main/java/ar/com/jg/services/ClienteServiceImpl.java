package ar.com.jg.services;

import ar.com.jg.model.Cliente;
import ar.com.jg.repositories.ClienteRepository;
import ar.com.jg.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{

    private EntityManager em;
    private CrudRepository<Cliente> cr;

    public ClienteServiceImpl(EntityManager em) {

        this.em = em;
        cr = new ClienteRepository(em);

    }

    @Override
    public List<Cliente> listarClientes() {

        return cr.listar();

    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {

        return Optional.ofNullable(cr.buscarPorId(id));

    }

    @Override
    public void guardarCliente(Cliente cliente) {

        try {

            em.getTransaction().begin();
            cr.guardar(cliente);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarCliente(long id) {

        try {

            em.getTransaction().begin();
            cr.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
