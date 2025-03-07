import java.util.Scanner;



public class Menu {
    Validaciones validaciones = new Validaciones();
    Registro registro = new Registro();
    public Menu () {

    }

    public void menu_inicial(Usuario[] array_usuarios, Bocadillo[] array_bocadillos, Pedido[] array_pedidos, Calendario[] array_calendarios) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;

        do {
            System.out.println("=====================================");
            System.out.println("=      (*) APP BOCADILLOS (*)       =");
            System.out.println("=====================================");
            System.out.println("            ¡Bienvenido!             ");
            System.out.println("-------------------------------------");
            System.out.println(" Inicia sesión para acceder a la App ");
            System.out.println("=====================================");
            System.out.println("     1. Iniciar Sesión");
            System.out.println("                                     ");
            System.out.println("     2. Salir");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();
            switch (seleccion) {
                case "1":
                    menu_login(array_usuarios, array_bocadillos, array_pedidos, array_calendarios);
                    break;
                case "2":
                    System.out.println("=====================================");
                    System.out.println("=         Hasta la próxima          =");
                    System.out.println("=====================================");
                    break;
                default:
                    System.out.println("Opcion no valida. Selecciona del 1 o 2.");
            }

        } while (!seleccion.equals("2"));
        System.exit(0);
    }

    public void menu_login(Usuario[] array_usuarios, Bocadillo[] array_bocadillos, Pedido[] array_pedidos, Calendario[] array_calendarios) {
        Usuario usuario_logueado;

        do {

            System.out.println("=====================");
            System.out.println("       LOGIN");
            System.out.println("=====================");
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduce nombre usuario");
            String nombre = entrada.nextLine().trim();
            System.out.println("Introduce contraseña usuario");
            String contrasena = entrada.nextLine().trim();

            usuario_logueado = validaciones.hacer_login(nombre, contrasena, array_usuarios);

            if (usuario_logueado == null) {
                System.out.println("Usuario o contraseña incorrectos");
            }

        } while (usuario_logueado == null);

        // Según el rol, mostramos el menú correspondiente
        switch (usuario_logueado.getRol()) {
            case 1:
                menu_administrador(array_usuarios);
                break;
            case 2:
                menu_cocina(array_pedidos);
                break;
            case 3:
                menu_alumno(array_bocadillos, array_calendarios);
                break;
        }
    }

    public void menu_administrador(Usuario[] array_usuarios){

        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;

        while (!salir) {
            System.out.println("=====================================");
            System.out.println("=           ADMINISTRADOR           =");
            System.out.println("=====================================");
            System.out.println("=        Gestionar Usuarios         =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Usuarios");
            System.out.println("     2. Gestión Usuarios");
            System.out.println("     3. Salir.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    listar_usuarios(array_usuarios, "Listado de usuarios:");
                    break;
                case "2":
                    registro.menu();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 3.");
            }

        }

    }

    public void menu_alumno(Bocadillo[] array_bocadillos, Calendario[] array_calendarios) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;
        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=               Alumno              =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Bocadillos");
            System.out.println("     2. Listar Quincena");
            System.out.println("     3. Realizar Pedido");
            System.out.println("     4. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    listar_bocadillos(array_bocadillos, "Listado de bocadillos: ");
                    break;
                case "2":
                    listar_calendarios(array_calendarios, "Listado de bocadillos: ");
                    break;
                case "3":
                    //realizar_pedido();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 4.");
            }

        }
    }

    public void menu_cocina(Pedido[] array_pedidos) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;
        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=               Cocina              =");
            System.out.println("=====================================");
            System.out.println("     1. Consultar Pedidos.");
            System.out.println("     2. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    listar_pedidos(array_pedidos, "Listado de pedidos del día: ");
                    break;
                case "2":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 2.");
            }

        }
    }

    public void listar_bocadillos(Bocadillo[] array_bocadillos, String mensaje){
        System.out.println(mensaje);
        for (int i = 0; i< array_bocadillos.length; i++){
            array_bocadillos[i].mostrar_info();
        }
    }

    public void listar_pedidos(Pedido[] array_pedidos, String mensaje){
        System.out.println(mensaje);
        for (int i=0; i< array_pedidos.length; i++){
            array_pedidos[i].mostrar_info();
        }
    }

    public void listar_usuarios(Usuario[] array_usuario, String mensaje){
        System.out.println(mensaje);
        for (int i=0; i< array_usuario.length; i++){
            array_usuario[i].mostrar_info();
        }
    }

    public void listar_calendarios(Calendario[] array_calendario, String mensaje){
        System.out.println(mensaje);
        for (int i=0; i< array_calendario.length; i++){
            array_calendario[i].mostrar_info();
        }
    }

// TODO
//    public void gestionar_bocadillos(Bocadillo[] arrayBocadillos) {
//        Scanner entrada = new Scanner(System.in);
//        String seleccion;
//        boolean salir = false;
//        while(!salir) {
//            System.out.println("=====================================");
//            System.out.println("=               ALUMNO              =");
//            System.out.println("=====================================");
//            System.out.println("     1. Listar Bocadillos");
//            System.out.println("     2. Ver Curiosidades de un Bocadillo");
//            System.out.println("     3. Ver Ingredientes de un Bocadillo");
//            System.out.println("     4. Ver Alergenos de un Bocadillo");
//            System.out.println("     5. Realizar pedido");
//            System.out.println("     6. Volver al Menú.");
//            System.out.println("=====================================");
//            System.out.print("\n\tSelecciona una opción: ");
//
//            seleccion = entrada.nextLine().trim();
//
//            switch (seleccion) {
//                case "1":
//                    //listar_bocadillos(arrayBocadillos);
//                    break;
//                case "2":
//                    break;
//                case "3":
//                    break;
//                case "4":
//                    break;
//                case "5":
//                    break;
//                case "6":
//                    salir = true;
//                    break;
//                default:
//                    System.out.println("Opción no valida. Selecciona del 1 al 6.");
//            }
//
//        }
//    }

//    public void realizar_pedido(Usuario[] arrayUsuarios, Pedido pedido, Bocadillo[] arrayBocadillos) {
//        Scanner entrada = new Scanner(System.in);
//        String seleccion;
//        boolean salir = false;
//
//        while(!salir) {
//            System.out.println("=====================================");
//            System.out.println("=          Realizar Pedido          =");
//            System.out.println("=====================================");
//            System.out.println("     1. Seleccionar Usuario.");
//            System.out.println("     2. Elegir Bocadillo.");
//            System.out.println("     3. Confirmar Pedido.");
//            System.out.println("     4. Volver al Menú.");
//            System.out.println("=====================================");
//            System.out.print("\n\tSelecciona una opción: ");
//
//            seleccion = entrada.nextLine().trim();
//
//            switch (seleccion) {
//                case "1":
//                    seleccionar_usuario(arrayUsuarios, pedido);
//                    break;
//                case "2":
//                    seleccionar_bocadillo(arrayBocadillos, pedido);
//                    break;
//                case "3":
//                    confirmar_pedido(pedido);
//                    System.out.println(pedido.getIdPedido() + " " + pedido.getIdBocadillo() + " " + pedido.getIdUsuario());
//                    break;
//                case "4":
//                    salir = true;
//                    break;
//                default:
//                    System.out.println("Opción no valida. Selecciona del 1 al 4.");
//            }
//
//        }
//
//    }
//
//    public void consultar_pedido() {
//        Scanner entrada = new Scanner(System.in);
//        String seleccion;
//        boolean salir = false;
//
//        while (!salir) {
//            System.out.println("=====================================");
//            System.out.println("=         Consultar Pedidos         =");
//            System.out.println("=====================================");
//            System.out.println("     1. Mostrar Pedidos de un Usuario.");
//            System.out.println("     2. Marcar Pedido como Retirado.");
//            System.out.println("     3. Volver al Menú.");
//            System.out.println("=====================================");
//            System.out.print("\n\tSelecciona una opción: ");
//
//            seleccion = entrada.nextLine().trim();
//
//            switch (seleccion) {
//                case "1":
//                    break;
//                case "2":
//                    break;
//                case "3":
//                    salir = true;
//                    break;
//                default:
//                    System.out.println("Opción no valida. Selecciona del 1 al 3.");
//            }
//        }
//    }

//    public void seleccionar_usuario(Usuario[] arrayUsuarios, Pedido pedido) {
//        Scanner scanner = new Scanner(System.in);
//        listar_usuarios(arrayUsuarios, "Selecciona un usuario del listado:");
//        String idUsuarioStr = scanner.nextLine().trim();
//        int idUsuarioInt = Integer.valueOf(idUsuarioStr);
//        String idUsuarioCorregida = String.valueOf(idUsuarioInt - 1);
//        pedido.setIdUsuario(idUsuarioCorregida);
//    }
//
//    public void seleccionar_bocadillo(Bocadillo[] arrayBocadillos, Pedido pedido) {
//        Scanner scanner = new Scanner(System.in);
//        listar_bocadillos(arrayBocadillos, "Añade un bocadillo al pedido: ");
//        String idBocadilloStr = scanner.nextLine().trim();
//        int idBocadilloInt = Integer.valueOf(idBocadilloStr)-1;
//        pedido.setIdBocadillo(idBocadilloInt);
//    }
//
//    public void confirmar_pedido (Pedido pedido) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Resumen pedido:");
//        System.out.println("1. " + pedido.getIdUsuario() + " - " + pedido.getIdBocadillo());
//        System.out.println("OK? (y/n)");
//        String seleccion = scanner.nextLine().trim();
//        if (seleccion.equals("y")) {
//            pedido.setIdPedido(1);
//            return;
//        }
//        pedido.setIdUsuario("");
//        pedido.setIdBocadillo(0);
//        System.out.println("Pedido cancelado!");
//    }
}
