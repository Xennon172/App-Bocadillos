package app;

import autenticar.AuthService;
import data.GesData;
import modelos.*;
import ui.MenuAdministrador;
import ui.MenuAlumno;
import ui.MenuCocina;


    public class Main {


        /**
         * La clase Main se encarga de obtener los datos o informacion
         * del arraylist, y llamar a cada metodo que lo gestiona.
         * Se utiliza la clase de Autenticacion  para mostrar el menu principal
         * donde el usuario podra iniciar sesion, registrarse o recuperar su contraseña.
         * Cuando se verifique el tipo de usuario mediante la denigracion de su clase
         * se vera un menu correspondiente.
         * Cada condicional evalua la clase como un rol y muestra su menu adecuadamente.
         * @param args
         */

        public static void main(String[] args) {

            GesData.listarPedido.clear();  // Limpia la lista para evitar duplicados
            GesData.inicializar_pedidos();    // Luego carga los pedidos

            // Cargar los ArrayList desde GestorData
            GesData.cargar_usuarios();
            GesData.cargarBocadillo();
            while (true) {
                // Mostrar el login
                AuthService.mostrarLogin();


                Usuario u = Sesion.getUsuarioActual();

                if (u instanceof Alumno) {
                    MenuAlumno.mostrar();
                } else if (u instanceof Cocina) {
                    MenuCocina.mostrar();
                } else if (u instanceof Administrador) {
                    MenuAdministrador.mostrar();
                }
            }
        }
    }







