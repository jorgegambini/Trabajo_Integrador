package ar.com.jg.services;

import ar.com.jg.model.ReporteIncidencia;
import java.util.List;
import java.util.Optional;


public interface ReporteIncidenciaService {

    List<ReporteIncidencia> listarReportesIncidencia();
    Optional<ReporteIncidencia> buscarReporteIncidenciaPorId(Long id);
    void guardarReporteIncidencia(ReporteIncidencia reporteIncidencia);
    void eliminarReporteIncidencia(long id);

}
