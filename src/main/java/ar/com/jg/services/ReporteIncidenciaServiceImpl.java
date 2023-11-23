package ar.com.jg.services;

import ar.com.jg.model.ReporteIncidencia;
import ar.com.jg.repositories.ReporteIncidenciaRepository;
import ar.com.jg.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ReporteIncidenciaServiceImpl implements ReporteIncidenciaService{

    private EntityManager em;
    private CrudRepository<ReporteIncidencia> rr;

    public ReporteIncidenciaServiceImpl(EntityManager em) {

        this.em = em;
        rr = new ReporteIncidenciaRepository(em);

    }

    @Override
    public List<ReporteIncidencia> listarReportesIncidencia() {

        return rr.listar();

    }

    @Override
    public Optional<ReporteIncidencia> buscarReporteIncidenciaPorId(Long id) {

        return Optional.ofNullable(rr.buscarPorId(id));

    }

    @Override
    public void guardarReporteIncidencia(ReporteIncidencia reporteIncidencia) {

        try {

            em.getTransaction().begin();
            rr.guardar(reporteIncidencia);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminarReporteIncidencia(long id) {

        try {

            em.getTransaction().begin();
            rr.eliminar(id);
            em.getTransaction().commit();

        }catch (Exception ex){

            em.getTransaction().rollback();
            ex.printStackTrace(System.out);

        }

    }
}
