package ui;

import modelos.Administrador;
import modelos.Alumno;
import modelos.Cocina;
import modelos.Usuario;
import servicios.ServiciosPedido;
import servicios.ServiciosUsuario;

import java.time.LocalDate;
import java.util.Scanner;

import java.util.Scanner;
import java.time.LocalDate;

public class MenuAdministrador {

    /**
     * Esta clase se encarga de mostrar las opciones del Menu de Administrador
     * Usa un submenu de la clase MenuUsuario, algunos metodos utilizan obtenerDatos y volcarLista
     * con el fin de obtener los ultimos registros del .dat y guardar en el archivo,
     */
    public static void mostrar() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---- MENU ADMINISTRADOR ----");
            System.out.println("1. Gestión Usuarios");
            System.out.println("2. Gestión Bocadillos");
            System.out.println("3. Informes");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    MenuUsuario.mostrar();  // Submenú para CRUD usuarios
                    break;
                case 2:
                    MenuBocadillo.mostrar();  // Submenú para CRUD bocadillos
                    break;

                case 3:
                    ServiciosPedido pedido = new ServiciosPedido();
                    pedido.volcarListas();
                    pedido.bocadilloMasPedido();
                    break;
                case 4:
                    return;  // salir
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

}
