import java.util.Scanner;



public class Menu {

    private Bocadillo[] arrayBocadillos;
    private Usuario[] arrayUsuarios;

    public Menu () {

    }

    public Menu (Bocadillo[] arrayBocadillos){
        this.arrayBocadillos = arrayBocadillos;
    }
/*
    public Bocadillo[] getArrayBocadillos() {
        return arrayBocadillos;
    }

    public void setArrayBocadillos(Bocadillo[] arrayBocadillos) {
        this.arrayBocadillos = arrayBocadillos;
    }*/

    public void menuLogin(Usuario[] arrayUsuarios) {
        boolean loginCorrecto = false;
        do {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduce nombre usuario");
            String nombre = entrada.nextLine().trim();
            System.out.println("Introduce contraseña usuario");
            String contrasena = entrada.nextLine().trim();
            loginCorrecto = hacerLogin(nombre, contrasena, arrayUsuarios);
        } while (!loginCorrecto);
    }

    public boolean hacerLogin(String nombre, String contrasena, Usuario[] arrayUsuarios) {
        for(int i = 0; i <= arrayUsuarios.length; i++) {
            if (nombre.equals(arrayUsuarios[i].getNombre()) && contrasena.equals(arrayUsuarios[i].getContrasena())) {
                return true;
            }
        }
        return false;
    }
    public void menuInicial() {
        Scanner entrada = new Scanner(System.in);
    String seleccion;

        do {
        System.out.println("=====================================");
        System.out.println("=      (*) APP BOCADILLOS (*)       =");
        System.out.println("=====================================");
        System.out.println("            ¡Bienvenido!             ");
        System.out.println("Selecciona un Rol: Alumno, cocina o Administrador ");
        System.out.println("     1. Alumno");
        System.out.println("     2. Cocina");
        System.out.println("     3. Administrador");
        System.out.println("     4. Salir");
        System.out.println("=====================================");
        System.out.print("\n\tSelecciona una opción: ");

        seleccion = entrada.nextLine().trim();
        switch (seleccion) {
            case "1":
                menuAlumno();
                break;
            case "2":
                menuCocina();
                break;
            case "3":
                gestionarUsuarios();
                break;
            case "5":
                System.out.println("=====================================");
                System.out.println("=         Hasta la próxima          =");
                System.out.println("=====================================");
                break;
            default:
                System.out.println("Opcion no valida. Selecciona del 1 al 4.");
        }

    } while (!seleccion.equals("5"));
        System.exit(0);
    }

    public void menuAlumno() {
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
                    gestionarBocadillos(arrayBocadillos);
                    break;
                case "2":
                    //realizarPedido();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 3.");
            }

        }
    }

    public void menuCocina() {
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
                    gestionarBocadillos(arrayBocadillos);
                    break;
                case "2":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida. Selecciona del 1 al 2.");
            }

        }
    }


    public void gestionarUsuarios(){

        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;

        while (!salir) {
            System.out.println("=====================================");
            System.out.println("=        Gestionar Usuarios         =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Usuarios");
            System.out.println("     2. Añadir Nuevo Usuario");
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


    public void gestionarBocadillos(Bocadillo[] arrayBocadillos) {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;
        while(!salir) {
            System.out.println("=====================================");
            System.out.println("=        Gestionar Bocadillos       =");
            System.out.println("=====================================");
            System.out.println("     1. Listar Bocadillos");
            System.out.println("     2. Ver Curiosidades de un Bocadillo");
            System.out.println("     3. Ver Ingredientes de un Bocadillo");
            System.out.println("     4. Ver Alergenos de un Bocadillo");
            System.out.println("     5. Volver al Menú.");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    //listarBocadillos(arrayBocadillos);
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

    public void realizarPedido(Usuario[] arrayUsuarios, Pedido pedido, Bocadillo[] arrayBocadillos) {
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
                    seleccionarUsuario(arrayUsuarios, pedido);
                    break;
                case "2":
                    seleccionarBocadillo(arrayBocadillos, pedido);
                    break;
                case "3":
                    confirmarPedido(pedido);
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

    public void consultarPedido() {
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

    public void listarUsuarios(Usuario[] arrayUsuario){
        for (int i=0; i< arrayUsuario.length; i++){
            System.out.println("Selecciona un usuario de la lista:");
            System.out.println(i+1 + ". " + arrayUsuario[i].getUsuario());
        }
    }

    /**
     *
     * @param arrayUsuarios
     * @param pedido
     */
    public void seleccionarUsuario(Usuario[] arrayUsuarios, Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        listarUsuarios(arrayUsuarios);
        String idUsuarioStr = scanner.nextLine().trim();
        int idUsuarioInt = Integer.valueOf(idUsuarioStr);
        String idUsuarioCorregida = String.valueOf(idUsuarioInt - 1);
        pedido.setIdUsuario(idUsuarioCorregida);
    }

    public void seleccionarBocadillo(Bocadillo[] arrayBocadillos, Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        listarBocadillos(arrayBocadillos);
        String idBocadilloStr = scanner.nextLine().trim();
        int idBocadilloInt = Integer.valueOf(idBocadilloStr)-1;
        pedido.setIdBocadillo(idBocadilloInt);
    }

    public void confirmarPedido (Pedido pedido) {
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
