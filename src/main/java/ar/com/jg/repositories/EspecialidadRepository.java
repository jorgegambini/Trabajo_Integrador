package ar.com.jg.repositories;

import ar.com.jg.model.Especialidad;
import ar.com.jg.model.Tecnico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class EspecialidadRepository implements CrudRepository<Especialidad>{

    private EntityManager em;

    public EspecialidadRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Especialidad> listar() {

        return em.createQuery("from Especialidad",Especialidad.class).getResultList();

    }

    public List<Especialidad> listarSinServicio() {

        return em.createQuery("from Especialidad e where e.servicio = null",Especialidad.class).getResultList();

    }

    @Override
    public Especialidad buscarPorId(Long id) {

        return em.find(Especialidad.class, id);

    }

    public Especialidad buscarPorDenominacion(String denominacion) {

        try {

            return em.createQuery("from Especialidad e where e.denominacion like :denominacion", Especialidad.class).setParameter("denominacion", denominacion).getSingleResult();

        }catch (NoResultException e){

            return null;

        }

    }

    @Override
    public void guardar(Especialidad especialidad) {

        if(especialidad.getId() != null && especialidad.getId() > 0){

            em.merge(especialidad);

        }else{

            em.persist(especialidad);

        }

    }

    @Override
    public void eliminar(Long id) {

        em.remove(buscarPorId(id));

    }

}

