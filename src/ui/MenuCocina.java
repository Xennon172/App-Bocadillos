package ui;

import data.GesData;
import servicios.ServiciosPedido;

import java.io.File;
import java.util.Scanner;

public class MenuCocina {

    /**
     * Esta clase se encarga de mostrar las opciones del Menu de Cocina
     * Usa un submenu de la clase MenuUsuario, algunos metodos utilizan obtenerDatos y volcarLista
     * con el fin de obtener los ultimos registros del .dat y guardar en el archivo,
     */

    public static void mostrar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(">> MENU COCINA <<");
            System.out.println("1. Ver pedidos");
            System.out.println("2. Cerrar sesión");
            System.out.print("> ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número del 1 al 2.");
                sc.nextLine(); // Limpiar el buffer en caso de error
                continue;
            }

            switch (opcion) {
                case 1:
                    ServiciosPedido pedido = new ServiciosPedido();
                    File archivo = new File("src/persistencia/Pedido.dat");
                    if (!archivo.exists() || archivo.length() == 0) {
                        // LIMPIAR antes de cargar datos iniciales
                        GesData.listarPedido.clear();
                        GesData.inicializar_pedidos();  // Carga datos iniciales en memoria
                        pedido.volcarListas();          // Guarda en archivo
                    }

                    pedido.obtenerTodos();
                    pedido.verPedidos();
                    break;

                case 2:
                    System.out.println("Cerrando sesión...");
                    return;  // Salir del menú

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }


}