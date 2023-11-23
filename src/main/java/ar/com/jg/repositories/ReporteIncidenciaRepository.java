package ar.com.jg.repositories;

import ar.com.jg.model.ReporteIncidencia;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReporteIncidenciaRepository implements CrudRepository<ReporteIncidencia>{

    private EntityManager em;

    public ReporteIncidenciaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ReporteIncidencia> listar() {

        return em.createQuery("from ReporteIncidencia",ReporteIncidencia.class).getResultList();

    }

    @Override
    public ReporteIncidencia buscarPorId(Long id) {

        return em.find(ReporteIncidencia.class, id);

    }

    @Override
    public void guardar(ReporteIncidencia reporteIncidencia) {

        if(reporteIncidencia.getId() != null && reporteIncidencia.getId() > 0){

            em.merge(reporteIncidencia);

        }else{

            em.persist(reporteIncidencia);

        }

    }

    @Override
    public void eliminar(Long id) {

        em.remove(buscarPorId(id));

    }

}
