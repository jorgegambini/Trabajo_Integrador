package ar.com.jg.repositories;

import ar.com.jg.model.OperadorMesaAyuda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OperadorMesaAyudaRepository implements CrudRepository<OperadorMesaAyuda>{

    private EntityManager em;

    public OperadorMesaAyudaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OperadorMesaAyuda> listar() {

        return em.createQuery("from OperadorMesaAyuda",OperadorMesaAyuda.class).getResultList();

    }

    @Override
    public OperadorMesaAyuda buscarPorId(Long id) {

        return em.find(OperadorMesaAyuda.class, id);

    }

    public OperadorMesaAyuda login(String user, String pass) {

        try {

            return em.createQuery("select o from OperadorMesaAyuda o where o.usuario = :user and o.password = :pass", OperadorMesaAyuda.class).setParameter("user", user).setParameter("pass", pass).getSingleResult();

        }catch (NoResultException e){

            return null;

        }

    }

    @Override
    public void guardar(OperadorMesaAyuda operadorMesaAyuda) {

        if(operadorMesaAyuda.getId() != null && operadorMesaAyuda.getId() > 0){

            em.merge(operadorMesaAyuda);

        }else{

            em.persist(operadorMesaAyuda);

        }

    }

    @Override
    public void eliminar(Long id) {

        em.remove(buscarPorId(id));

    }

}