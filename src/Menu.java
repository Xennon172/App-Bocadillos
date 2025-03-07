import java.util.Scanner;



public class Menu {

    private Bocadillo[] arrayBocadillos;
    private Usuario[] arrayUsuarios;
    Validaciones validaciones = new Validaciones();
    Registro registro = new Registro();
    public Menu () {

    }

    public Menu (Bocadillo[] arrayBocadillos){
        this.arrayBocadillos = arrayBocadillos;
    }

    public void menu_login(Usuario[] array_usuarios) {
        Usuario usuario_logueado = new Usuario();
        String input;
        do {
            System.out.println("=====================");
            System.out.println("       LOGIN");
            System.out.println("=====================");
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduce nombre usuario");
            input = entrada.nextLine().trim();
            String nombre = input;
            System.out.println("Introduce contraseña usuario");
            input = entrada.nextLine().trim();
            String contrasena = input;
            usuario_logueado = validaciones.hacer_login(nombre, contrasena, array_usuarios);

            if (usuario_logueado == null) {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } while (usuario_logueado == null);


        switch (usuario_logueado.getRol()) {
            case 1:
                menu_administrador(array_usuarios);
                break;
            case 2:
                menu_cocina();
                break;
            case 3:
                menu_alumno();
                break;
        }
    }



    public void menu_inicial(Usuario[] array_usuarios) {
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
                menu_login(array_usuarios);
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

    public void menu_alumno() {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;
        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=               Alumno              =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Bocadillos");
            System.out.println("     2. Realizar Pedido");
            System.out.println("     3. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    gestionar_bocadillos(arrayBocadillos);
                    break;
                case "2":
                    //realizar_pedido();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 3.");
            }

        }
    }

    public void menu_cocina() {
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
                    gestionar_bocadillos(arrayBocadillos);
                    break;
                case "2":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 2.");
            }

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


    public void gestionar_bocadillos(Bocadillo[] arrayBocadillos) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;
        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=               ALUMNO              =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Bocadillos");
            System.out.println("     2. Ver Curiosidades de un Bocadillo");
            System.out.println("     3. Ver Ingredientes de un Bocadillo");
            System.out.println("     4. Ver Alergenos de un Bocadillo");
            System.out.println("     5. Realizar pedido");
            System.out.println("     6. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    //listar_bocadillos(arrayBocadillos);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 6.");
            }

        }
    }

    public void realizar_pedido(Usuario[] arrayUsuarios, Pedido pedido, Bocadillo[] arrayBocadillos) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;

        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=          Realizar Pedido          =");
            System.out.println("=====================================");
            System.out.println("     1. Seleccionar Usuario.");
            System.out.println("     2. Elegir Bocadillo.");
            System.out.println("     3. Confirmar Pedido.");
            System.out.println("     4. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    seleccionar_usuario(arrayUsuarios, pedido);
                    break;
                case "2":
                    seleccionar_bocadillo(arrayBocadillos, pedido);
                    break;
                case "3":
                    confirmar_pedido(pedido);
                    System.out.println(pedido.getIdPedido() + " " + pedido.getIdBocadillo() + " " + pedido.getIdUsuario());
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 4.");
            }

        }

    }

    public void consultar_pedido() {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;

        while (!salir) {
            System.out.println("=====================================");
            System.out.println("=         Consultar Pedidos         =");
            System.out.println("=====================================");
            System.out.println("     1. Mostrar Pedidos de un Usuario.");
            System.out.println("     2. Marcar Pedido como Retirado.");
            System.out.println("     3. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 3.");
            }
        }
    }

    public void listarBocadillos(Bocadillo[] arrayBocadillos){
        for (int i=0; i< arrayBocadillos.length; i++){
            System.out.println("Selecciona un bocadillo de la lista:");
            System.out.println(i+1 + ". " + arrayBocadillos[i].getNombre() + " Precio:" + arrayBocadillos[i].getPrecio());
        }
    }

    public void listar_usuarios(Usuario[] arrayUsuario, String mensaje){
        System.out.println(mensaje);
        for (int i=0; i< arrayUsuario.length; i++){
            System.out.println(i+1 + ". " + arrayUsuario[i].getUsuario());
        }
    }

    /**
     *
     * @param arrayUsuarios
     * @param pedido
     */
    public void seleccionar_usuario(Usuario[] arrayUsuarios, Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        listar_usuarios(arrayUsuarios, "Selecciona un usuario del listado:");
        String idUsuarioStr = scanner.nextLine().trim();
        int idUsuarioInt = Integer.valueOf(idUsuarioStr);
        String idUsuarioCorregida = String.valueOf(idUsuarioInt - 1);
        pedido.setIdUsuario(idUsuarioCorregida);
    }

    public void seleccionar_bocadillo(Bocadillo[] arrayBocadillos, Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        listarBocadillos(arrayBocadillos);
        String idBocadilloStr = scanner.nextLine().trim();
        int idBocadilloInt = Integer.valueOf(idBocadilloStr)-1;
        pedido.setIdBocadillo(idBocadilloInt);
    }

    public void confirmar_pedido (Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Resumen pedido:");
        System.out.println("1. " + pedido.getIdUsuario() + " - " + pedido.getIdBocadillo());
        System.out.println("OK? (y/n)");
        String seleccion = scanner.nextLine().trim();
        if (seleccion.equals("y")) {
            pedido.setIdPedido(1);
            return;
        }
        pedido.setIdUsuario("");
        pedido.setIdBocadillo(0);
        System.out.println("Pedido cancelado!");
    }

}
