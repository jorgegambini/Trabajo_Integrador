package ar.com.jg.services;

import ar.com.jg.model.OperadorMesaAyuda;
import ar.com.jg.repositories.OperadorMesaAyudaRepository;
import ar.com.jg.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class OperadorMesaAyudaServiceImpl implements OperadorMesaAyudaService{

    private EntityManager em;
    private CrudRepository<OperadorMesaAyuda> or;

    public OperadorMesaAyudaServiceImpl(EntityManager em) {

        this.em = em;
        or = new OperadorMesaAyudaRepository(em);

    }

    @Override
    public List<OperadorMesaAyuda> listarOperadoresMesaAyuda() {

        return or.listar();

    }

    @Override
    public Optional<OperadorMesaAyuda> buscarOperadorMesaAyudaPorId(Long id) {

        return Optional.ofNullable(or.buscarPorId(id));

    }

    public Optional<OperadorMesaAyuda> loginOperadorMesaAyuda(String user, String pass) {

        return Optional.ofNullable(((OperadorMesaAyudaRepository)or).login(user, pass));

    }

    @Override
    public void guardarOperadorMesaAyuda(OperadorMesaAyuda operadorMesaAyuda) {

        try {

            em.getTransaction().begin();
            or.guardar(operadorMesaAyuda);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarOperadorMesaAyuda(long id) {

        try {

            em.getTransaction().begin();
            or.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
