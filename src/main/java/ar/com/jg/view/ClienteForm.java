package ar.com.jg.view;

import ar.com.jg.model.DatosContacto;
import ar.com.jg.model.Servicio;
import ar.com.jg.model.Cliente;
import ar.com.jg.services.*;
import jakarta.persistence.EntityManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ClienteForm {

    private MenuForm menuForm;

    private IngresarTexto ingresarTexto;
    private IngresarNumero ingresarNumero;
    private IngresarServicio ingresarServicio;

    private int valorRetornado;
    private String opcionMenu;
    private String strID;
    private String strCUIT;
    private String razonSocial;
    private String strTelefono;
    private String strCelular;
    private String email;


    private ClienteService cs;
    Cliente cliente;
    private DatosContactoService ds;
    DatosContacto datosContacto;
    private ServicioService ss;
    Servicio servicio;

    public ClienteForm(EntityManager em) {

        cs = new ClienteServiceImpl(em);
        ds = new DatosContactoServiceImpl(em);
        ss = new ServicioServiceImpl(em);

        init();

    }

    private void init() {

        String menuOperadorMesaAyuda = """
                <html>MENU OPCIONES:<br><br>
                               
                1 - INGRESAR UN CLIENTE.<br>
                2 - EDITAR UN CLIENTE.<br>
                3 - ELIMINAR UN CLIENTE.<br>
                4 - SALIR.<br><br></html>""";

        do {

            menuForm = new MenuForm(menuOperadorMesaAyuda, 200, 0);
            valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

            if (JOptionPane.OK_OPTION == valorRetornado) {

                opcionMenu = menuForm.getOption().trim();

                if (!validarMenuOpcion(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");

                switch (opcionMenu) {

                    case "1" -> {

                        strCUIT = "";
                        razonSocial = "";
                        strTelefono = "";
                        strCelular = "";
                        email = "";

                        do {

                            ingresarNumero = new IngresarNumero("Legajo:", "", 60, 60, 100);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese CUIT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strCUIT = ingresarNumero.getNumero().trim();

                                if (!validarCUIT(strCUIT)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un CUIT Correcto.");

                                } else {

                                    do {

                                        ingresarTexto = new IngresarTexto("Razón Social:", "", 90, 150, 0);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Razón Social", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            razonSocial = ingresarTexto.getTexto().trim();

                                            if (!validarTexto1(razonSocial)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese una Razón Social Correcta.");

                                            } else {

                                                do {

                                                    ingresarNumero = new IngresarNumero("Telefono:", "0", 70, 100, 0);
                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Telefono", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                        strTelefono = ingresarNumero.getNumero().trim();

                                                        if (!validarTelefono(strTelefono)) {

                                                            JOptionPane.showMessageDialog(null, "Ingrese un Telefono Correcto.");

                                                        } else {

                                                            do {

                                                                ingresarNumero = new IngresarNumero("Celular:", "0", 70, 100, 0);
                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Celular", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                    strCelular = ingresarNumero.getNumero().trim();

                                                                    if (!validarTelefono(strCelular)) {

                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Celular Correcto.");

                                                                    } else {

                                                                        do {

                                                                            ingresarTexto = new IngresarTexto("Email:", "", 70, 150, 0);
                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Email", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                email = ingresarTexto.getTexto().trim();

                                                                                if (!validarEmail(email)) {

                                                                                    JOptionPane.showMessageDialog(null, "Ingrese un Email Correcto.");

                                                                                } else {

                                                                                    datosContacto = new DatosContacto();
                                                                                    datosContacto.setTelefono(Long.valueOf(strTelefono));
                                                                                    datosContacto.setCelular(Long.valueOf(strCelular));
                                                                                    datosContacto.setEmail(email);
                                                                                    cliente = new Cliente();
                                                                                    cliente.setCuit(Long.valueOf(strCUIT));
                                                                                    cliente.setRazonSocial(razonSocial);
                                                                                    cliente.setDatosContacto(datosContacto);

                                                                                    do {

                                                                                        do {

                                                                                            ingresarServicio = new IngresarServicio();
                                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarServicio, "Ingrese Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                cliente.addServicio(servicio);

                                                                                            }

                                                                                        } while (valorRetornado == JOptionPane.CLOSED_OPTION || (valorRetornado == JOptionPane.CANCEL_OPTION && cliente.getServicios().isEmpty()));

                                                                                        valorRetornado = JOptionPane.showOptionDialog(null, "Desea agregar otro Servicio?", "Ingrese Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                    } while (valorRetornado == JOptionPane.OK_OPTION);

                                                                                    cs.guardarCliente(cliente);

                                                                                    JOptionPane.showMessageDialog(null, "El Cliente se ha ingresado correctamente.");

                                                                                }

                                                                            }

                                                                        } while (!validarEmail(email) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                    }

                                                                }

                                                            } while (!validarTelefono(strCelular) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                        }

                                                    }

                                                } while (!validarTelefono(strTelefono) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                            }

                                        }

                                    } while (!validarTexto1(razonSocial) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                }

                            }

                        } while (!validarCUIT(strCUIT) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }

                    case "2" -> {

                        strCUIT = "";
                        razonSocial = "";
                        strTelefono = "";
                        strCelular = "";
                        email = "";

                        do {

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50, 150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarNumero(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                } else if (cs.buscarClientePorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()) {

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                } else {

                                    do {

                                        cliente = cs.buscarClientePorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                        datosContacto = cliente.getDatosContacto();

                                        ingresarNumero = new IngresarNumero("Legajo:", cliente.getCuit().toString(), 60, 60, 100);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese CUIT", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            strCUIT = ingresarNumero.getNumero().trim();

                                            if (!validarCUIT(strCUIT)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese un CUIT Correcto.");

                                            } else {

                                                do {

                                                    ingresarTexto = new IngresarTexto("Nombre:", cliente.getRazonSocial(), 90, 150, 0);
                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Razon Social", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                        razonSocial = ingresarTexto.getTexto().trim();

                                                        if (!validarTexto1(razonSocial)) {

                                                            JOptionPane.showMessageDialog(null, "Ingrese una Razón Social Correcta.");

                                                        } else {

                                                            do {

                                                                ingresarNumero = new IngresarNumero("Telefono:", cliente.getDatosContacto().getTelefono().toString(), 70, 100, 0);
                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Telefono", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                    strTelefono = ingresarNumero.getNumero().trim();

                                                                    if (!validarTelefono(strTelefono)) {

                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Telefono Correcto.");

                                                                    } else {

                                                                        do {

                                                                            ingresarNumero = new IngresarNumero("Celular:", cliente.getDatosContacto().getCelular().toString(), 70, 100, 0);
                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Celular", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                strCelular = ingresarNumero.getNumero().trim();

                                                                                if (!validarTelefono(strCelular)) {

                                                                                    JOptionPane.showMessageDialog(null, "Ingrese un Celular Correcto.");

                                                                                } else {

                                                                                    do {

                                                                                        ingresarTexto = new IngresarTexto("Email:", cliente.getDatosContacto().getEmail(), 70, 150, 0);
                                                                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Email", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                            email = ingresarTexto.getTexto().trim();

                                                                                            if (!validarEmail(email)) {

                                                                                                JOptionPane.showMessageDialog(null, "Ingrese un Email Correcto.");

                                                                                            } else {

                                                                                                datosContacto.setTelefono(Long.valueOf(strTelefono));
                                                                                                datosContacto.setCelular(Long.valueOf(strCelular));
                                                                                                datosContacto.setEmail(email);
                                                                                                cliente.setCuit(Long.valueOf(strCUIT));
                                                                                                cliente.setRazonSocial(razonSocial);
                                                                                                cliente.setDatosContacto(datosContacto);

                                                                                                valorRetornado = JOptionPane.showOptionDialog(null, "Desea recargar los Servicioss", "Ingrese Servicios", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                    cliente.getServicios().clear();

                                                                                                    do {

                                                                                                        do {

                                                                                                            ingresarServicio = new IngresarServicio();
                                                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarServicio, "Ingrese Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                                cliente.addServicio(servicio);

                                                                                                            }

                                                                                                        } while (valorRetornado == JOptionPane.CLOSED_OPTION || (valorRetornado == JOptionPane.CANCEL_OPTION && cliente.getServicios().isEmpty()));

                                                                                                        valorRetornado = JOptionPane.showOptionDialog(null, "Desea agregar otro Servicio?", "Ingrese Servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                    } while (valorRetornado == JOptionPane.OK_OPTION);

                                                                                                }

                                                                                                cs.guardarCliente(cliente);

                                                                                                JOptionPane.showMessageDialog(null, "El Cliente se ha modificado correctamente.");

                                                                                            }

                                                                                        }

                                                                                    } while (!validarEmail(email) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                }

                                                                            }

                                                                        } while (!validarTelefono(strCelular) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                    }

                                                                }

                                                            } while (!validarTelefono(strTelefono) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                        }

                                                    }

                                                } while (!validarTexto1(razonSocial) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                            }

                                        }

                                    } while (!validarCUIT(strCUIT) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                }

                            }

                        } while (!validarNumero(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }
                    case "3" -> {

                        strID = "";

                        do {

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50, 150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarNumero(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                } else if (cs.buscarClientePorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()) {

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                } else {

                                    cliente = cs.buscarClientePorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                    cs.eliminarCliente(cliente.getId());

                                    JOptionPane.showMessageDialog(null, "El Cliente se ha eliminado correctamente.");

                                }

                            }

                        } while (!validarNumero(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }

                    case "4" ->
                            valorRetornado = JOptionPane.showOptionDialog(null, "Está seguro que desea salir?", "Salir",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                }

            }

        } while (valorRetornado != JOptionPane.OK_OPTION || !validarMenuOpcion(opcionMenu));


    }

    private class IngresarTexto extends JPanel {

        private JTextField textoField;

        public IngresarTexto(String textLabel, String textField, int longLabel, int longField, int gap) {

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel textoLabel = new JLabel(textLabel);
            textoField = new JTextField();

            textoField.setText(textField);

            add(textoLabel, "split 2, width " + longLabel + ":" + longLabel + ":" + longLabel + ", growx");
            add(textoField, "width " + longField + ":" + longField + ":" + longField + ", pushx, gapright " + gap);

        }

        public String getTexto() {

            return textoField.getText();

        }

    }

    private class IngresarNumero extends JPanel {

        private JTextField numeroField;

        public IngresarNumero(String numLabel, String textField, int longLabel, int longField, int gap) {

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel numeroLabel = new JLabel(numLabel);
            numeroField = new JTextField();

            numeroField.setHorizontalAlignment(SwingConstants.RIGHT);
            numeroField.setText(textField);

            add(numeroLabel, "split 2, width " + longLabel + ":" + longLabel + ":" + longLabel + ", growx");
            add(numeroField, "width " + longField + ":" + longField + ":" + longField + ", pushx, gapright " + gap);

        }

        public String getNumero() {

            return numeroField.getText();

        }

    }

    private class IngresarServicio extends JPanel {

        private JComboBox comboField;

        public IngresarServicio() {

            setLayout(new MigLayout("wrap,fillx,insets 5 10 5 10", "[fill,50]"));

            JLabel textoLabel = new JLabel("Servicio:");
            comboField = new JComboBox();

            add(textoLabel, "split 2, width 90:90:90, growx");
            add(comboField, "width 150:150:150, pushx, gapright 0");

            comboField.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {

                        servicio = (Servicio) comboField.getSelectedItem();

                    }
                }
            });

            getEspecialidad();

        }

        private void getEspecialidad() {

            comboField.removeAllItems();

            ss.listarServicios().forEach(esp -> comboField.addItem(esp));

            if (comboField.getItemCount() > 0) {

                servicio = (Servicio) comboField.getSelectedItem();

            }

        }


    }

    private boolean validarMenuOpcion(String args) {
        return args.matches("^[1-4]$");
    }

    private boolean validarNumero(String numero) {

        return numero.matches("^[1-9]\\d{0,8}$");

    }

    private boolean validarTelefono(String telefono) {

        return telefono.matches("^[1-9]\\d{9}$");

    }

    private boolean validarCUIT(String cuit) {

        return cuit.matches("^[1-9]\\d{10}$");

    }

    private boolean validarEmail(String email) {

        return email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$");

    }

    private boolean validarTexto(String denominacion) {

        return denominacion.matches("^([A-ZÁÉÍÓÚÜÑ0-9]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]+))([ ][A-ZÁÉÍÓÚÜÑ0-9]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]*)){0,5}");

    }

    private boolean validarTexto1(String denominacion) {

        return denominacion.matches("^([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]+))([ ][A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]([A-ZÁÉÍÓÚÜÑa-záéíóüñ0-9]*)){0,5}");

    }

    private boolean validarNombre(String denominacion) {

        return denominacion.matches("^([A-ZÁÉÍÓÚÜÑ]([a-záéíóüñ]+))([ ][A-ZÁÉÍÓÚÜÑ]([a-záéíóüñ]*)){0,5}");

    }

}
