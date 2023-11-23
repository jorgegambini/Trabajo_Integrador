package ar.com.jg.services;

import ar.com.jg.model.Especialidad;
import ar.com.jg.repositories.CrudRepository;
import ar.com.jg.repositories.EspecialidadRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class EspecialidadServiceImpl implements EspecialidadService{

    private EntityManager em;
    private CrudRepository<Especialidad> er;

    public EspecialidadServiceImpl(EntityManager em) {

        this.em = em;
        er = new EspecialidadRepository(em);

    }

    @Override
    public List<Especialidad> listarEspecialidades() {

        return er.listar();

    }

    public List<Especialidad> listarEspecialidadesSinServicio() {

        return ((EspecialidadRepository)er).listarSinServicio();

    }

    @Override
    public Optional<Especialidad> buscarEspecialidadPorId(Long id) {

        return Optional.ofNullable(er.buscarPorId(id));

    }

    public Optional<Especialidad> buscarEspecialidadPorDenominacion(String denominacion) {

        return Optional.ofNullable(((EspecialidadRepository)er).buscarPorDenominacion(denominacion));

    }

    @Override
    public void guardarEspecialidad(Especialidad especialidad) {

        try {

            em.getTransaction().begin();
            er.guardar(especialidad);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarEspecialidad(long id) {

        try {

            em.getTransaction().begin();
            er.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
