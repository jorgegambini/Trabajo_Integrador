package ar.com.jg.view;

import ar.com.jg.model.Especialidad;
import ar.com.jg.services.EspecialidadService;
import ar.com.jg.services.EspecialidadServiceImpl;
import jakarta.persistence.EntityManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;


public class EspecialidadForm {

    private MenuForm menuForm;

    private IngresarTexto ingresarTexto;
    private IngresarNumero ingresarNumero;

    private int valorRetornado;
    private String opcionMenu;
    private String strID;
    private String denominacion;

    private EspecialidadService es;
    Especialidad especialidad;

    public EspecialidadForm(EntityManager em) {

        es = new EspecialidadServiceImpl(em);

        init();

    }

    private void init() {

        String menuEspecialidad = """
                <html>MENU OPCIONES:<br><br>
                               
                1 - INGRESAR UNA ESPECIALIDAD.<br>
                2 - EDITAR UNA ESPECIALIDAD.<br>
                3 - ELIMINAR UNA ESPECIALIDAD.<br>
                4 - SALIR.<br><br></html>""";

        do {

            menuForm = new MenuForm(menuEspecialidad,250,50);
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

                                    especialidad = new Especialidad();
                                    especialidad.setDenominacion(denominacion);
                                    es.guardarEspecialidad(especialidad);

                                    JOptionPane.showMessageDialog(null, "La Especialidad se ha ingresado correctamente.");

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

                                }else if(es.buscarEspecialidadPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    do {

                                        especialidad = es.buscarEspecialidadPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();

                                        ingresarTexto = new IngresarTexto("Denominación", especialidad.getDenominacion(), 90, 100,0);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Denominacion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            denominacion = ingresarTexto.getTexto().trim();

                                            if (!validarDenominacion(denominacion)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese una Denominación Correcta.");

                                            } else {

                                                especialidad.setDenominacion(denominacion);
                                                es.guardarEspecialidad(especialidad);

                                                JOptionPane.showMessageDialog(null, "La Especialidad se ha modificado correctamente.");

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

                                }else if(es.buscarEspecialidadPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    especialidad = es.buscarEspecialidadPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                    es.eliminarEspecialidad(especialidad.getId());

                                    JOptionPane.showMessageDialog(null, "La Especialidad se ha eliminado correctamente.");

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
