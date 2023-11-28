package ar.com.jg.view.accessories;

import ar.com.jg.model.Especialidad;
import ar.com.jg.model.ReporteIncidencia;
import ar.com.jg.model.Tecnico;
import ar.com.jg.services.EspecialidadService;
import ar.com.jg.services.EspecialidadServiceImpl;
import ar.com.jg.services.ReporteIncidenciaService;
import ar.com.jg.services.ReporteIncidenciaServiceImpl;
import ar.com.jg.utility.RequestFocusListener;
import jakarta.persistence.EntityManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class MostrarReportes extends JPanel {

    public MostrarReportes() {

    }

}
