package ar.com.jg.main;

import ar.com.jg.services.OperadorMesaAyudaService;
import ar.com.jg.services.OperadorMesaAyudaServiceImpl;
import ar.com.jg.services.TecnicoService;
import ar.com.jg.services.TecnicoServiceImpl;
import ar.com.jg.utility.JPAUtil;
import ar.com.jg.view.*;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //obtenerConexion();

        EntityManager em = JPAUtil.getEntityManager();
        OperadorMesaAyudaService os = new OperadorMesaAyudaServiceImpl(em);
        TecnicoService ts = new TecnicoServiceImpl(em);

        LoginForm loginForm = null;
        MenuForm menuForm = null;

        int valorRetornado = 0;
        int cantidadIntendos = 0;

        String usuario = "";
        String password = "";
        String opcionMenu = "";

        String menuAdministrador = """
                <html>MENU OPCIONES:<br><br>
                                
                1 - ABM ESPECIALIDADES.<br>
                2 - ABM SERVICIOS.<br>
                3 - ABM OPERADORES MESA AYUDA.<br>
                4 - ABM TECNICOS.<br>
                5 - SALIR.<br><br></html>""";

        String menuOperador = """
                <html>MENU OPCIONES:<br><br>
                                
                1 - ABM CLIENTES.<br>
                2 - INGRESAR REPORTES INCIDENCIAS.<br>
                3 - CAMBIAR ESTADO REPORTE INCIDENCIAS.<br>
                4 - SALIR.<br><br></html>""";

        String menuTecnico = """
                <html>MENU OPCIONES:<br><br>
                               
                1 - SOLUCIONAR REPORTES INCIDENCIAS.<br>
                2 - CAMBIAR ESTADO REPORTE INCIDENCIAS.<br>
                3 - SALIR.<br><br></html>""";

        do {

            loginForm = new LoginForm();
            valorRetornado = JOptionPane.showOptionDialog(null, loginForm, "Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (JOptionPane.OK_OPTION == valorRetornado) {

                usuario = loginForm.getUserName().trim();
                password = new String(loginForm.getPassword()).trim();

                if (os.loginOperadorMesaAyuda(usuario, password).isPresent()) {

                    cantidadIntendos = 3;
                    JOptionPane.showMessageDialog(null, "Bienvenidos Operador: " + usuario);

                    do {

                        menuForm = new MenuForm(menuOperador, 350, 50);
                        valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

                        if (JOptionPane.OK_OPTION == valorRetornado) {

                            opcionMenu = menuForm.getOption().trim();

                            if(!validarMenuOpcionOperador(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");
                            else{

                                valorRetornado = JOptionPane.CANCEL_OPTION;

                                switch (opcionMenu){

                                    case "1" -> new ClienteForm(em);
                                    //case "2" -> .....;
                                    //case "3" -> .....;
                                    case "4" -> valorRetornado = JOptionPane.showOptionDialog(null, "Está seguro que desea salir?", "Salir",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                                }

                            }

                        }

                    } while (valorRetornado != JOptionPane.OK_OPTION || !validarMenuOpcionAdministrador(opcionMenu));

                } else if (ts.loginTecnico(usuario, password).isPresent()) {

                    cantidadIntendos = 3;
                    JOptionPane.showMessageDialog(null, "Bienvenidos Tecnico: " + usuario);

                    do {

                        menuForm = new MenuForm(menuTecnico, 350, 50);
                        valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

                        if (JOptionPane.OK_OPTION == valorRetornado) {

                            opcionMenu = menuForm.getOption().trim();

                            if(!validarMenuOpcionTecnico(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");
                            else{

                                valorRetornado = JOptionPane.CANCEL_OPTION;

                                switch (opcionMenu){

                                    //case "1" -> .....;
                                    //case "2" -> .....;
                                    case "3" -> valorRetornado = JOptionPane.showOptionDialog(null, "Está seguro que desea salir?", "Salir",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                                }



                            }

                        }

                    } while (valorRetornado != JOptionPane.OK_OPTION || !validarMenuOpcionAdministrador(opcionMenu));

                } else if (usuario.equals("admin") && password.equals("admin")) {

                    cantidadIntendos = 3;
                    JOptionPane.showMessageDialog(null, "Bienvenido Administrador");

                    do {

                        menuForm = new MenuForm(menuAdministrador, 250, 50);
                        valorRetornado = JOptionPane.showOptionDialog(null, menuForm, "Seleccione una Opcion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Aceptar"}, "Aceptar");

                        if (JOptionPane.OK_OPTION == valorRetornado) {

                            opcionMenu = menuForm.getOption().trim();

                            if(!validarMenuOpcionAdministrador(opcionMenu)) JOptionPane.showMessageDialog(null, "Ingrese una Opción Correcta.");
                            else{

                                valorRetornado = JOptionPane.CANCEL_OPTION;

                                switch (opcionMenu){

                                    case "1" -> new EspecialidadForm(em);
                                    case "2" -> new ServicioForm(em);
                                    case "3" -> new OperadorMesaAyudaForm(em);
                                    case "4" -> new TecnicoForm(em);
                                    case "5" -> valorRetornado = JOptionPane.showOptionDialog(null, "Está seguro que desea salir?", "Salir",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                                }



                            }

                        }

                    } while (valorRetornado != JOptionPane.OK_OPTION || !validarMenuOpcionAdministrador(opcionMenu));

                } else {

                    JOptionPane.showMessageDialog(null, "Usuario o Password Incorrectos");
                    cantidadIntendos += 1;

                }

            }

        } while (cantidadIntendos < 3 && JOptionPane.CANCEL_OPTION != valorRetornado);

        em.close();

    }

    static boolean validarMenuOpcionAdministrador(String args) {
        return args.matches("^[1-5]$");
    }

    static boolean validarMenuOpcionOperador(String args) {
        return args.matches("^[1-4]$");
    }

    static boolean validarMenuOpcionTecnico(String args) {
        return args.matches("^[1-3]$");
    }

    public static void obtenerConexion() {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");//mysql 8
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabajo_integrador?useTimezone=true&serverTimezone=UTC", "root", "@270Jorge571");
            if (con != null) {
                System.out.println("CONECTADO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
