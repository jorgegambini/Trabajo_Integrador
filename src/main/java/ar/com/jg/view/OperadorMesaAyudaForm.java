package ar.com.jg.view;

import ar.com.jg.model.DatosContacto;
import ar.com.jg.model.OperadorMesaAyuda;
import ar.com.jg.services.DatosContactoService;
import ar.com.jg.services.DatosContactoServiceImpl;
import ar.com.jg.services.OperadorMesaAyudaService;
import ar.com.jg.services.OperadorMesaAyudaServiceImpl;
import jakarta.persistence.EntityManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;


public class OperadorMesaAyudaForm {

    private MenuForm menuForm;

    private IngresarTexto ingresarTexto;
    private IngresarNumero ingresarNumero;

    private int valorRetornado;
    private String opcionMenu;
    private String strID;
    private String strLegajo;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String strTelefono;
    private String strCelular;
    private String email;

    private OperadorMesaAyudaService os;
    OperadorMesaAyuda operadorMesaAyuda;
    private DatosContactoService ds;
    DatosContacto datosContacto;

    public OperadorMesaAyudaForm(EntityManager em) {

        os = new OperadorMesaAyudaServiceImpl(em);
        ds = new DatosContactoServiceImpl(em);

        init();

    }

    private void init() {

        String menuOperadorMesaAyuda = """
                <html>MENU OPCIONES:<br><br>
                               
                1 - INGRESAR UN OPERADOR MESA AYUDA.<br>
                2 - EDITAR UN OPERADOR MESA AYUDA.<br>
                3 - ELIMINAR UN OPERADOR MESA AYUDA.<br>
                4 - SALIR.<br><br></html>""";

        do {

            menuForm = new MenuForm(menuOperadorMesaAyuda, 300, 100);
            valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

            if (JOptionPane.OK_OPTION == valorRetornado) {

                opcionMenu = menuForm.getOption().trim();

                if (!validarMenuOpcion(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");

                switch (opcionMenu) {

                    case "1" -> {

                        strLegajo = "";
                        nombre = "";
                        apellido = "";
                        usuario = "";
                        password = "";
                        strTelefono = "";
                        strCelular = "";
                        email = "";

                        do{

                            ingresarNumero = new IngresarNumero("Legajo:", "",60,60,100);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Legajo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strLegajo = ingresarNumero.getNumero().trim();

                                if (!validarNumero(strLegajo)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Legajo Correcto.");

                                }else {

                                    do{

                                        ingresarTexto = new IngresarTexto("Nombre:", "",70,100,0);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Nombre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            nombre = ingresarTexto.getTexto().trim();

                                            if (!validarNombre(nombre)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese un Nombre Correcto.");

                                            }else {

                                                do{

                                                    ingresarTexto = new IngresarTexto("Apellido:", "",70,100,0);
                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Apellido", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                        apellido = ingresarTexto.getTexto().trim();

                                                        if (!validarNombre(apellido)) {

                                                            JOptionPane.showMessageDialog(null, "Ingrese un Apellido Correcto.");

                                                        }else {

                                                            do{

                                                                ingresarTexto = new IngresarTexto("Usuario:", "",70,100,0);
                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                    usuario = ingresarTexto.getTexto().trim();

                                                                    if (!validarTexto1(usuario)) {

                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Usuario Correcto.");

                                                                    }else {

                                                                        do{

                                                                            ingresarTexto = new IngresarTexto("Password:", "",70,100,0);
                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                password = ingresarTexto.getTexto().trim();

                                                                                if (!validarTexto1(password)) {

                                                                                    JOptionPane.showMessageDialog(null, "Ingrese un Password Correcto.");

                                                                                }else {

                                                                                    do{

                                                                                        ingresarNumero = new IngresarNumero("Telefono:", "0",70,100,0);
                                                                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Telefono", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                            strTelefono = ingresarNumero.getNumero().trim();

                                                                                            if (!validarTelefono(strTelefono)) {

                                                                                                JOptionPane.showMessageDialog(null, "Ingrese un Telefono Correcto.");

                                                                                            }else {

                                                                                                do{

                                                                                                    ingresarNumero = new IngresarNumero("Celular:", "0",70,100,0);
                                                                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Celular", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                        strCelular = ingresarNumero.getNumero().trim();

                                                                                                        if (!validarTelefono(strCelular)) {

                                                                                                            JOptionPane.showMessageDialog(null, "Ingrese un Celular Correcto.");

                                                                                                        }else {

                                                                                                            do{

                                                                                                                ingresarTexto = new IngresarTexto("Email:", "",70,150,0);
                                                                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Email", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                                    email = ingresarTexto.getTexto().trim();

                                                                                                                    if (!validarEmail(email)) {

                                                                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Email Correcto.");

                                                                                                                    }else {

                                                                                                                        datosContacto = new DatosContacto();
                                                                                                                        datosContacto.setTelefono(Long.valueOf(strTelefono));
                                                                                                                        datosContacto.setCelular(Long.valueOf(strCelular));
                                                                                                                        datosContacto.setEmail(email);
                                                                                                                        operadorMesaAyuda = new OperadorMesaAyuda();
                                                                                                                        operadorMesaAyuda.setLegajo(Integer.valueOf(strLegajo));
                                                                                                                        operadorMesaAyuda.setNombre(nombre);
                                                                                                                        operadorMesaAyuda.setApellido(apellido);
                                                                                                                        operadorMesaAyuda.setUsuario(usuario);
                                                                                                                        operadorMesaAyuda.setPassword(password);
                                                                                                                        operadorMesaAyuda.setDatosContacto(datosContacto);
                                                                                                                        os.guardarOperadorMesaAyuda(operadorMesaAyuda);

                                                                                                                        JOptionPane.showMessageDialog(null, "El Operador Mesa Ayuda se ha ingresado correctamente.");

                                                                                                                    }

                                                                                                                }

                                                                                                            }while (!validarEmail(email) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                                        }

                                                                                                    }

                                                                                                }while (!validarTelefono(strCelular) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                            }

                                                                                        }

                                                                                    }while (!validarTelefono(strTelefono) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                }

                                                                            }

                                                                        }while (!validarTexto1(password) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                    }

                                                                }

                                                            }while (!validarTexto1(usuario) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                        }

                                                    }

                                                }while (!validarNombre(apellido) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                            }

                                        }

                                    }while (!validarNombre(nombre) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                }

                            }

                        }while (!validarNumero(strLegajo) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }

                    case "2" -> {

                        strID = "";
                        strLegajo = "";
                        nombre = "";
                        apellido = "";
                        usuario = "";
                        password = "";
                        strTelefono = "";
                        strCelular = "";
                        email = "";

                        do{

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50,150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarNumero(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                }else if(os.buscarOperadorMesaAyudaPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    do {

                                        operadorMesaAyuda = os.buscarOperadorMesaAyudaPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                        datosContacto = operadorMesaAyuda.getDatosContacto();

                                        ingresarNumero = new IngresarNumero("Legajo:", operadorMesaAyuda.getLegajo().toString(),60,60,100);
                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Legajo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                            strLegajo = ingresarNumero.getNumero().trim();

                                            if (!validarNumero(strLegajo)) {

                                                JOptionPane.showMessageDialog(null, "Ingrese un Legajo Correcto.");

                                            }else {

                                                do{

                                                    ingresarTexto = new IngresarTexto("Nombre:", operadorMesaAyuda.getNombre(),70,100,0);
                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Nombre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                        nombre = ingresarTexto.getTexto().trim();

                                                        if (!validarNombre(nombre)) {

                                                            JOptionPane.showMessageDialog(null, "Ingrese un Nombre Correcto.");

                                                        }else {

                                                            do{

                                                                ingresarTexto = new IngresarTexto("Apellido:", operadorMesaAyuda.getApellido(),70,100,0);
                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Apellido", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                    apellido = ingresarTexto.getTexto().trim();

                                                                    if (!validarNombre(apellido)) {

                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Apellido Correcto.");

                                                                    }else {

                                                                        do{

                                                                            ingresarTexto = new IngresarTexto("Usuario:", operadorMesaAyuda.getUsuario(),70,100,0);
                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                usuario = ingresarTexto.getTexto().trim();

                                                                                if (!validarTexto1(usuario)) {

                                                                                    JOptionPane.showMessageDialog(null, "Ingrese un Usuario Correcto.");

                                                                                }else {

                                                                                    do{

                                                                                        ingresarTexto = new IngresarTexto("Password:", operadorMesaAyuda.getPassword(),70,100,0);
                                                                                        valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                        if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                            password = ingresarTexto.getTexto().trim();

                                                                                            if (!validarTexto1(password)) {

                                                                                                JOptionPane.showMessageDialog(null, "Ingrese un Password Correcto.");

                                                                                            }else {

                                                                                                do{

                                                                                                    ingresarNumero = new IngresarNumero("Telefono:", operadorMesaAyuda.getDatosContacto().getTelefono().toString(),70,100,0);
                                                                                                    valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Telefono", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                    if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                        strTelefono = ingresarNumero.getNumero().trim();

                                                                                                        if (!validarTelefono(strTelefono)) {

                                                                                                            JOptionPane.showMessageDialog(null, "Ingrese un Telefono Correcto.");

                                                                                                        }else {

                                                                                                            do{

                                                                                                                ingresarNumero = new IngresarNumero("Celular:", operadorMesaAyuda.getDatosContacto().getCelular().toString(),70,100,0);
                                                                                                                valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese Celular", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                                if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                                    strCelular = ingresarNumero.getNumero().trim();

                                                                                                                    if (!validarTelefono(strCelular)) {

                                                                                                                        JOptionPane.showMessageDialog(null, "Ingrese un Celular Correcto.");

                                                                                                                    }else {

                                                                                                                        do{

                                                                                                                            ingresarTexto = new IngresarTexto("Email:", operadorMesaAyuda.getDatosContacto().getEmail(),70,150,0);
                                                                                                                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarTexto, "Ingrese Email", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                                                                                                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                                                                                                                email = ingresarTexto.getTexto().trim();

                                                                                                                                if (!validarEmail(email)) {

                                                                                                                                    JOptionPane.showMessageDialog(null, "Ingrese un Email Correcto.");

                                                                                                                                }else {

                                                                                                                                    datosContacto.setTelefono(Long.valueOf(strTelefono));
                                                                                                                                    datosContacto.setCelular(Long.valueOf(strCelular));
                                                                                                                                    datosContacto.setEmail(email);
                                                                                                                                    operadorMesaAyuda.setLegajo(Integer.valueOf(strLegajo));
                                                                                                                                    operadorMesaAyuda.setNombre(nombre);
                                                                                                                                    operadorMesaAyuda.setApellido(apellido);
                                                                                                                                    operadorMesaAyuda.setUsuario(usuario);
                                                                                                                                    operadorMesaAyuda.setPassword(password);
                                                                                                                                    operadorMesaAyuda.setDatosContacto(datosContacto);
                                                                                                                                    os.guardarOperadorMesaAyuda(operadorMesaAyuda);

                                                                                                                                    JOptionPane.showMessageDialog(null, "El Operador Mesa Ayuda se ha modificado correctamente.");

                                                                                                                                }

                                                                                                                            }

                                                                                                                        }while (!validarEmail(email) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                                                    }

                                                                                                                }

                                                                                                            }while (!validarTelefono(strCelular) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                                        }

                                                                                                    }

                                                                                                }while (!validarTelefono(strTelefono) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                            }

                                                                                        }

                                                                                    }while (!validarTexto1(password) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                                }

                                                                            }

                                                                        }while (!validarTexto1(usuario) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                                    }

                                                                }

                                                            }while (!validarNombre(apellido) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                                        }

                                                    }

                                                }while (!validarNombre(nombre) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                            }

                                        }

                                    }while (!validarNumero(strLegajo) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                                }

                            }

                        }while (!validarNumero(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

                        valorRetornado = JOptionPane.CANCEL_OPTION;

                    }
                    case "3" -> {

                        strID = "";

                        do{

                            ingresarNumero = new IngresarNumero("ID:", "0", 20, 50,150);
                            valorRetornado = JOptionPane.showOptionDialog(null, ingresarNumero, "Ingrese ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                            if (JOptionPane.OK_OPTION == valorRetornado) {

                                strID = ingresarNumero.getNumero().trim();

                                if (!validarNumero(strID)) {

                                    JOptionPane.showMessageDialog(null, "Ingrese un Id Correcto.");

                                }else if(os.buscarOperadorMesaAyudaPorId(Long.valueOf(ingresarNumero.getNumero().trim())).isEmpty()){

                                    JOptionPane.showMessageDialog(null, "El Id ingresado no existe.");

                                }else{

                                    operadorMesaAyuda = os.buscarOperadorMesaAyudaPorId(Long.valueOf(ingresarNumero.getNumero().trim())).get();
                                    os.eliminarOperadorMesaAyuda(operadorMesaAyuda.getId());

                                    JOptionPane.showMessageDialog(null, "El Operador Mesa Ayuda se ha eliminado correctamente.");

                                }

                            }

                        }while (!validarNumero(strID) && (valorRetornado == JOptionPane.CLOSED_OPTION || valorRetornado == JOptionPane.OK_OPTION));

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

    private boolean validarNumero(String numero) {

        return numero.matches("^[1-9]\\d{0,8}$");

    }

    private boolean validarTelefono(String telefono) {

        return telefono.matches("^[1-9]\\d{9}$");

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
