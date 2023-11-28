package ar.com.jg.services;

import ar.com.jg.model.ReporteIncidencia;
import ar.com.jg.repositories.CrudRepository;
import ar.com.jg.repositories.ReporteIncidenciaRepository;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.time.LocalDate;
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
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

    @Override
    public void eliminarReporteIncidencia(long id) {

        try {

            em.getTransaction().begin();
            rr.eliminar(id);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "El Reporte de Incidencia se ha eliminado correctamente.");

        }catch (Exception ex){

            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }

}
