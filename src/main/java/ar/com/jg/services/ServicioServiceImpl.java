package ar.com.jg.services;

import ar.com.jg.model.Servicio;
import ar.com.jg.repositories.ServicioRepository;
import ar.com.jg.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ServicioServiceImpl implements ServicioService{

    private EntityManager em;
    private CrudRepository<Servicio> sr;

    public ServicioServiceImpl(EntityManager em) {

        this.em = em;
        sr = new ServicioRepository(em);

    }

    @Override
    public List<Servicio> listarServicios() {

        return sr.listar();

    }

    @Override
    public Optional<Servicio> buscarServicioPorId(Long id) {

        return Optional.ofNullable(sr.buscarPorId(id));

    }

    @Override
    public void guardarServicio(Servicio servicio) {

        try {

            em.getTransaction().begin();
            sr.guardar(servicio);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarServicio(long id) {

        try {

            em.getTransaction().begin();
            sr.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
