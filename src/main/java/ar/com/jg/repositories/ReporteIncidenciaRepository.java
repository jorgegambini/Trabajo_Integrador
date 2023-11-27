package ar.com.jg.repositories;

import ar.com.jg.model.ReporteIncidencia;
import ar.com.jg.model.enums.EstadoProblema;
import ar.com.jg.utility.RandomString;
import ar.com.jg.view.accessories.MostrarLabel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;


public class ReporteIncidenciaRepository implements CrudRepository<ReporteIncidencia> {

    private EntityManager em;

    public ReporteIncidenciaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ReporteIncidencia> listar() {

        return em.createQuery("from ReporteIncidencia", ReporteIncidencia.class).getResultList();

    }

    public List<ReporteIncidencia> listarPorRangoFecha(LocalDate fechaDesde, LocalDate fechaHasta) {

        EstadoProblema estadoProblema = EstadoProblema.Resuelto;

        return em.createQuery("from ReporteIncidencia r where r.estadoProblema = :estadoProblema and r.fechaAlta between :fechaDesde and :fechaHasta", ReporteIncidencia.class).setParameter("estadoProblema", estadoProblema).setParameter("fechaDesde", fechaDesde).setParameter("fechaHasta", fechaHasta).getResultList();

    }

    @Override
    public ReporteIncidencia buscarPorId(Long id) {

        return em.find(ReporteIncidencia.class, id);

    }

    public ReporteIncidencia buscarPorCodigo(String codigoReporte) {

        EstadoProblema estadoProblema1 = EstadoProblema.Cancelado;
        EstadoProblema estadoProblema2 = EstadoProblema.Resuelto;

        try {

            return em.createQuery("from ReporteIncidencia r where r.codigoReporte like :codigoReporte and r.estadoProblema != :estadoProblema1 and r.estadoProblema != :estadoProblema2", ReporteIncidencia.class).setParameter("codigoReporte", codigoReporte).setParameter("estadoProblema1", estadoProblema1).setParameter("estadoProblema2", estadoProblema2).getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    @Override
    public void guardar(ReporteIncidencia reporteIncidencia) {

        String reportCode = RandomString.getAlphaNumericString(10);
        String lblReporte = "";

        if (reporteIncidencia.getId() != null && reporteIncidencia.getId() > 0) {

            String estado = reporteIncidencia.getEstadoProblema().name();

            reporteIncidencia.setCodigoReporte(reportCode);

            em.merge(reporteIncidencia);

            if (estado.equals("Cancelado")) {

                lblReporte = "<html><div style=\"text-align: center;\">El Reporte de Incidencia se ha " + estado + " correctamente.</div><br>" +
                        "<div style=\"text-align: center;\"> <h1 style=\"font-size: 24px; color: red;\">" + reportCode + "</h1></div></html>";

                MostrarLabel mostrarLabel = new MostrarLabel(lblReporte, 350, 50);
                JOptionPane.showOptionDialog(null, mostrarLabel, "Reporte Incidencia Actualizado", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

            } else {

                JOptionPane.showMessageDialog(null, "El Reporte Incidencia se ha actualizado correctamente.");

            }



        } else {

            reporteIncidencia.setCodigoReporte(reportCode);

            em.persist(reporteIncidencia);

            lblReporte = "<html><div style=\"text-align: center;\">El Reporte de Incidencia se ha ingresado correctamente.</div><br>" +
                    "<div style=\"text-align: center;\"> <h1 style=\"font-size: 24px; color: red;\">" + reportCode + "</h1></div></html>";

            MostrarLabel mostrarLabel = new MostrarLabel(lblReporte, 320, 50);
            JOptionPane.showOptionDialog(null, mostrarLabel, "Reporte Incidencia Generado", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

        }

    }

    @Override
    public void eliminar(Long id) {

        em.remove(buscarPorId(id));

    }

}
