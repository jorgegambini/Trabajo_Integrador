package ar.com.jg.view;

import ar.com.jg.model.Especialidad;
import ar.com.jg.model.Servicio;
import ar.com.jg.services.EspecialidadService;
import ar.com.jg.services.EspecialidadServiceImpl;
import ar.com.jg.services.ServicioService;
import ar.com.jg.services.ServicioServiceImpl;
import jakarta.persistence.EntityManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ServicioForm {

    private MenuForm menuForm;

    private IngresarTexto ingresarTexto;
    private IngresarNumero ingresarNumero;
    private IngresarEspecialidad ingresarEspecialidad;

    private int valorRetornado;
    private String opcionMenu;
    private String strID;
    private String denominacion;

    private ServicioService ss;
    Servicio servicio;
    private EspecialidadService es;
    Especialidad especialidad;

    public ServicioForm(EntityManager em) {

        ss = new ServicioServiceImpl(em);
        es = new EspecialidadServiceImpl(em);

        init();

    }

    private void init() {

        String menuServicio = """
                <html>MENU OPCIONES:<br><br>
                               
                1 - INGRESAR UN SERVICIO.<br>
                2 - EDITAR UN SERVICIO.<br>
                3 - ELIMINAR UN SERVICIO.<br>
                4 - SALIR.<br><br></html>""";

        do {

            menuForm = new MenuForm(menuServicio, 200, 0);
            valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

            if (JOptionPane.OK_OPTION == valorRetornado) {

                opcionMenu = menuForm.getOption().trim();

                if (!validarMenuOpcion(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");

                switch (opcionMenu) {

                    case "1" -> {

                        denominacion = "";

                        do{

                            ingresarTexto = new IngresarTexto("Denominación", "",90,100,0);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Denominacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                denominacion = ingresarTexto.getTexto().trim();

                                if (!validarDenominacion(denominacion)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese una Denominación Correcta.");

                                }else {

                                    servicio = new Servicio();
                                    servicio.setDenominacion(denominacion);

                                    do
                                    {

                                        do
                                        {

                                            ingresarEspecialidad = new IngresarEspecialidad();
                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarEspecialidad, "Ingrese Especialidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                servicio.addEspecialidad(especialidad);

                                            }

                                        } while (valorRetornado == JOptionPane.CLOSED_OPTION || (valorRetornado == JOptionPane.CANCEL_OPTION && servicio.getEspecialidades().isEmpty()));

                                        valorRetornado = JOptionPane.showOptionDialog(null, "Desea agregar otra especialidad", "Ingrese Especialidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                    } while (valorRetornado == JOptionPane.OK_OPTION);

                                    ss.guardarServicio(servicio);

                                    JOptionPane.showMessageDialog(null, "El Servicio se ha ingresado correctamente.");

                                }

                            }

                        }while (!validarDenominacion(denominacion) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }

                    case "2" -> {

                        strID = "";
                        denominacion = "";

                        do{

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50,150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarId(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                }else if(ss.buscarServicioPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    do {

                                        servicio = ss.buscarServicioPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();

                                        ingresarTexto = new IngresarTexto("Denominación", servicio.getDenominacion(), 90, 100,0);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Denominacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            denominacion = ingresarTexto.getTexto().trim();

                                            if (!validarDenominacion(denominacion)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese una Denominación Correcta.");

                                            } else {

                                                servicio.setDenominacion(denominacion);

                                                valorRetornado = JOptionPane.showOptionDialog(null, "Desea recargar las Especialidades", "Ingrese Especialidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                    servicio.getEspecialidades().clear();

                                                    do
                                                    {

                                                        do
                                                        {

                                                            ingresarEspecialidad = new IngresarEspecialidad();
                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarEspecialidad, "Ingrese Especialidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                servicio.addEspecialidad(especialidad);

                                                            }

                                                        } while (valorRetornado == JOptionPane.CLOSED_OPTION || (valorRetornado == JOptionPane.CANCEL_OPTION && servicio.getEspecialidades().isEmpty()));

                                                        valorRetornado = JOptionPane.showOptionDialog(null, "Desea agregar otra especialidad", "Ingrese Especialidad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                    } while (valorRetornado == JOptionPane.OK_OPTION);

                                                }

                                                ss.guardarServicio(servicio);

                                                JOptionPane.showMessageDialog(null, "El Servicio se ha modificado correctamente.");

                                            }

                                        }

                                    }while (!validarDenominacion(denominacion) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                }

                            }

                        }while (!validarId(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }
                    case "3" -> {

                        strID = "";

                        do{

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50,150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarId(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                }else if(ss.buscarServicioPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    servicio = ss.buscarServicioPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                    ss.eliminarServicio(servicio.getId());

                                    JOptionPane.showMessageDialog(null, "El Servicio se ha eliminado correctamente.");

                                }

                            }

                        }while (!validarId(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }

                    case "4" ->
                            valorRetornado = JOptionPane.showOptionDialog(null, "Está seguro que desea salir?", "Salir",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                }

            }

        } while (valorRetornado != JOptionPane.OK_OPTION || !validarMenuOpcion(opcionMenu));


    }

    private class IngresarTexto extends JPanel{

        private JTextField textoField;

        public IngresarTexto(String textLabel, String textField, int longLabel, int longField, int gap){

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel textoLabel = new JLabel(textLabel);
            textoField = new JTextField();

            textoField.setText(textField);

            add(textoLabel, "split 2, width " + longLabel + ":" + longLabel + ":"+ longLabel + ", growx");
            add(textoField, "width " + longField + ":" + longField + ":"+ longField + ", pushx, gapright " + gap);

        }

        public String getTexto() {

            return textoField.getText();

        }

    }

    private class IngresarNumero extends JPanel{

        private JTextField numeroField;

        public IngresarNumero(String numLabel, String textField, int longLabel, int longField, int gap){

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel numeroLabel = new JLabel(numLabel);
            numeroField = new JTextField();

            numeroField.setHorizontalAlignment(SwingConstants.RIGHT);
            numeroField.setText(textField);

            add(numeroLabel, "split 2, width " + longLabel + ":" + longLabel + ":"+ longLabel + ", growx");
            add(numeroField, "width " + longField + ":" + longField + ":"+ longField + ", pushx, gapright " + gap);

        }

        public String getNumero() {

            return numeroField.getText();

        }

    }

    private class IngresarEspecialidad extends JPanel {

        private JComboBox comboField;

        public IngresarEspecialidad() {

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel textoLabel = new JLabel("Especialidad:");
            comboField = new JComboBox();

            add(textoLabel, "split 2, width 90:90:90, growx");
            add(comboField, "width 150:150:150, pushx, gapright 0");

            comboField.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {

                        especialidad = (Especialidad) comboField.getSelectedItem();

                    }
                }
            });

            getEspecialidad();

        }

        private void getEspecialidad() {

            comboField.removeAllItems();

            es.listarEspecialidades().forEach(esp -> comboField.addItem(esp));

            if (comboField.getItemCount() > 0) {

                especialidad = (Especialidad) comboField.getSelectedItem();

            }

        }


    }

    private boolean validarMenuOpcion(String args) {
        return args.matches("^[1-4]$");
    }

    private boolean validarId(String id) {

        return id.matches("^[1-9]\\d{0,8}$");

    }

    private boolean validarDenominacion(String denominacion) {

        return denominacion.matches("^([A-ZÁÉÍÓÚÜÑ]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]+))([ ][A-ZÁÉÍÓÚÜÑ]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]*)){0,5}");

    }

}
