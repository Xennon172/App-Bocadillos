import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Registro {

    static String usuario_registrado, nombre_registrado, apellido_registrado, email_registrado,
            dni_registrado, fecha_registrada, contrasena_registrada1, respuesta_seguridad;

    static boolean bloqueado = false;
    static boolean registrado = false;

    static Validaciones validaciones = new Validaciones();
    static Usuario usuario = new Usuario();

    public Registro () {

    }
    /**
     * Método principal que inicia el programa
     */
    /**
     *
     * @param args
     */

    /** Método es el menú principal donde el usuario elige las opciones a realizar en el programa.
     */
    public void menu() {
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        boolean salir = false;

        while (!salir){
            System.out.println("=====================================");
            System.out.println("=          GESTIÓN USUARIOS           =");
            System.out.println("=====================================");
            System.out.println("     1. Registrar Nuevo Usuario");
            System.out.println("     2. Recuperación de Contraseña");
            System.out.println("     3. Desbloqueo de Usuario");
            System.out.println("     4. Salir");
            System.out.println("=====================================");
            System.out.print("\n\tSelecciona una opción: ");

            seleccion = entrada.nextLine().trim();

            switch (seleccion) {
                case "1":
                    registro();
                    break;
                case "2":
                    //recuperacion(true);
                    break;
                case "3":
                    //recuperacion(false);
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Selecciona del 1 al 5.");
            }
        }
        System.exit(0);
    }

    /**
     * Método que gestiona el proceso de login del usuario.
     */
//    public static void login() {
//        Scanner entrada = new Scanner(System.in);
//        String seleccion;
//        int captcha_generado;
//        String captcha_usuario;
//        int intentos_captcha = 0;
//        int intentos_login = 0;
//        boolean login_correcto = false;
//        boolean salir = false;
//
//        if (!registrado) {
//            System.out.println("No hay usuarios registrados");
//
//            while (!salir) {
//                System.out.println("=====================================");
//                System.out.println("=       Selecciona una opción       =");
//                System.out.println("=====================================");
//                System.out.println("     1. ¿Ir a registro?");
//                System.out.println("     2. Volver a menú");
//                System.out.println("=====================================");
//                System.out.print("\n\tSelecciona una opción: ");
//
//                seleccion = entrada.nextLine().trim();
//
//                switch (seleccion) {
//                    case "1":
//                        registro();
//                        break;
//                    case "2":
//                        salir = true;
//                        break;
//                    default:
//                        System.out.println("Opcion no valida. Selecciona del 1 al 2.");
//                }
//            }
//        }
//        comprobar_bloqueo();
//        do {
//            System.out.println("=====================================");
//            System.out.println("=               LOGIN               =");
//            System.out.println("=====================================");
//            System.out.println("Ingresa tu nombre de usuario:");
//            Scanner nombre = new Scanner(System.in);
//            String usuario = nombre.nextLine();
//            System.out.println("Ingresa tu contraseña:");
//            Scanner contrasena = new Scanner(System.in);
//            String contrasena_usuario = contrasena.nextLine().trim();
//
//            do {
//                captcha_generado = validaciones.generar_captcha();
//                System.out.println("CAPTCHA: " + captcha_generado);
//                System.out.println("Inserta el captcha:");
//                Scanner escaner_captcha = new Scanner(System.in);
//                captcha_usuario = escaner_captcha.nextLine().trim();
//
//                if (!validaciones.validar_captcha(captcha_generado, captcha_usuario)) {
//                    System.out.println("Captcha incorrecto");
//                    intentos_captcha++;
//                } else {
//                    registrado = true;
//                }
//
//                if (intentos_captcha == 3) {
//                    System.out.println("\n\t Has fallado 3 veces completando el captcha, ¿eres un bot?.\n USUARIO BLOQUEADO");
//                    bloqueado = true;
//                    //menu();
//                }
//
//
//            } while (!validaciones.validar_captcha(captcha_generado, captcha_usuario));
//
//
//            if (usuario.equalsIgnoreCase(usuario_registrado) && contrasena_usuario.equals(contrasena_registrada1)) {
//                System.out.println("Login Correcto." + "\n ¡Bienvenido " + usuario + "!");
//                System.out.println("\t    **     **  ");
//                System.out.println("\t  *****   *****");
//                System.out.println("\t  *************");
//                System.out.println("\t   *********** ");
//                System.out.println("\t    *********  ");
//                System.out.println("\t     *******   ");
//                System.out.println("\t      *****    ");
//                System.out.println("\t       ***     ");
//                System.out.println("\t        *      ");
//                login_correcto = true;
//            } else {
//                intentos_login++;
//                System.out.println("ERROR. Usuario o contraseña incorrectos.\n"
//                        + "(Asegurate que los datos sean correctos antes de introducilos)");
//            }
//
//        } while (intentos_login < 3 && !login_correcto);
//
//        if (!login_correcto) {
//            System.out.println("\n\t HAS FALLADO 3 VECES, ¿DE VERDAD?...\n USUARIO BLOQUEADO");
//            bloqueado = true;
//            //menu();
//        }
//    }

    /**
     * Método que se encarga del registro de usuario.
     */
    public static void registro() {
        comprobar_bloqueo();
        int captcha_generado;
        String captcha_usuario;
        int intentos = 0;

        System.out.println("=====================================");
        System.out.println("=             REGISTRO              =");
        System.out.println("=====================================");

        do {
            System.out.println("Introduce USUARIO (mínimo 5 caracteres):");
            Scanner escaner_usuario = new Scanner(System.in);
            usuario_registrado = escaner_usuario.nextLine().trim();
            if (usuario_registrado.length() < 5) {
                System.out.println("El nombre de usuario debe tener 5 de caracteres");
            }
        } while (!validaciones.validar_nombre(usuario_registrado) || usuario_registrado.length() < 5);

        usuario.setUsuario(usuario_registrado);

        do {
            System.out.println("Introduce NOMBRE:");
            Scanner escaner_nombre = new Scanner(System.in);
            nombre_registrado = escaner_nombre.nextLine().trim();
            if (nombre_registrado.isEmpty()) {
                System.out.println("El campo nombre no puede estar vacío");
            }
        } while (!validaciones.validar_nombre(nombre_registrado) || nombre_registrado.isEmpty());

        usuario.setNombre(nombre_registrado);

        do {
            System.out.println("Introduce APELLIDO:");
            Scanner escaner_apellido = new Scanner(System.in);
            apellido_registrado = escaner_apellido.nextLine();
            if (apellido_registrado.isEmpty()) {
                System.out.println("El campo apellido no puede estar vacío");
            }
        } while (!validaciones.validar_nombre(apellido_registrado) || apellido_registrado.isEmpty());

        usuario.setApellido(apellido_registrado);

        do {
            System.out.println("Introduce EMAIL:");
            Scanner escaner_email = new Scanner(System.in);
            email_registrado = escaner_email.nextLine();
            if (email_registrado.isEmpty()) {
                System.out.println("El campo email no puede estar vacío");
            }
        } while (!validaciones.validar_email(email_registrado) || email_registrado.isEmpty());

        usuario.setCorreo(email_registrado);

        do {
            System.out.println("Introduce DNI:");
            Scanner escaner_dni = new Scanner(System.in);
            dni_registrado = escaner_dni.nextLine().toUpperCase();
            if (!validaciones.validar_dni(dni_registrado)) {
                System.out.println("Documento NO valido");
            } else if (dni_registrado.isEmpty()) {
                System.out.println("El campo dni no puede estar vacío");
            }
        } while (!validaciones.validar_dni(dni_registrado) || dni_registrado.isEmpty());

        usuario.setDni(dni_registrado);

        String diaStr = "";
        String mesStr = "";
        String anoStr = "";
        do {
            System.out.println("Introduce tu fecha de nacimiento");
            System.out.println("Día: ");
            Scanner entrada_dia = new Scanner(System.in);
            diaStr = entrada_dia.nextLine();
            System.out.println("Mes: ");
            Scanner entrada_mes = new Scanner(System.in);
            mesStr = entrada_mes.nextLine();
            System.out.println("Año: ");
            Scanner entrada_ano = new Scanner(System.in);
            anoStr = entrada_ano.nextLine();

            fecha_registrada = diaStr + "/" + mesStr + "/" + anoStr;

        } while (!validaciones.validar_fecha(diaStr, mesStr, anoStr) || fecha_registrada.isEmpty());

        usuario.setFechaNacimiento(formatearFecha(fecha_registrada));

        do {
            Scanner entrada_rol = new Scanner(System.in);
            System.out.println("Selecciona el rol del usuario:");
            System.out.println("1. Admin");
            System.out.println("2. Cocina");
            System.out.println("3. Alumno");
            String rol_seleccionado = entrada_rol.nextLine();
            switch (rol_seleccionado) {
                case "1":
                    System.out.println("Admin seleccionado");
                    usuario.setRol(Integer.valueOf(rol_seleccionado));
                    break;
                case "2":
                    System.out.println("Admin seleccionado");
                    usuario.setRol(Integer.valueOf(rol_seleccionado));
                    break;
                case "3":
                    System.out.println("Admin seleccionado");
                    usuario.setRol(Integer.valueOf(rol_seleccionado));
                    break;
                default:
                    System.out.println("El rol seleccionado no es válido.");
            }
        } while (usuario.getRol() == 0);

        String contrasena = registrar_contrasena();
        usuario.setContrasena(contrasena);

        // Solamente añadimos alergias si el usuario es alumno
        if (usuario.getRol() == 3) {
            ArrayList<String> alergias = new ArrayList<>();
            boolean salir = false;
            do {
                System.out.println("Añade alergias: ");
                System.out.println("(pulsa 0 para salir)");
                Scanner entrada = new Scanner(System.in);
                String alergia = entrada.nextLine();
                if(!alergia.equals("0")) {
                    alergias.add(alergia);
                } else {
                    salir = true;
                }
            } while (!salir);
            usuario.setAlergias(alergias);
        }

        usuario.mostrarInfoUsuario();

        boolean color_validado = false;
        do {
            System.out.println("\t=== Para tu seguridad se te va a hacer una pregunta a modo de recuperacion de contraseña ===\n"
                    + "\t                               ¿CUAL ES TU COLOR FAVORITO?\n");
            Scanner escaner_respuesta_seguridad = new Scanner(System.in);
            respuesta_seguridad = escaner_respuesta_seguridad.nextLine();

            if (respuesta_seguridad.length() == 0) {
                System.out.println("Valor incorrecto,  el campo no debe estar vacío.");

            } else {
                for (int i = 0; i < respuesta_seguridad.length(); i++) {
                    if (!validaciones.es_letra(respuesta_seguridad.charAt(i))) {
                        color_validado = false;
                        System.out.println("Valor incorrecto,  inserta solo letras.");
                        break;
                    }
                    color_validado = true;
                }
            }

        } while (!color_validado);

        do {
            captcha_generado = validaciones.generar_captcha();
            System.out.println("CAPTCHA: " + captcha_generado);
            System.out.println("Inserta el captcha:");
            Scanner escaner_captcha = new Scanner(System.in);
            captcha_usuario = escaner_captcha.nextLine().trim();
            if (!validaciones.validar_captcha(captcha_generado, captcha_usuario)) {
                System.out.println("Captcha incorrecto");
                intentos++;
            } else {
                System.out.println("USUARIO REGISTRADO!");
                registrado = true;
            }
            if (intentos == 3) {
                System.out.println("\n\t Has fallado 3 veces completando el captcha, eres un bot?.");
                break;
            }
        } while (!validaciones.validar_captcha(captcha_generado, captcha_usuario));
    }

    /**
     * Método para recuperar la contraseña o desbloquear un usuario.
     *
     * @param recuperar_contrasena si se está recuperando la contraseña (true)
     *                             o desbloqueando el usuario (false).
     */
    public static void recuperacion(boolean recuperar_contrasena) {
        Scanner scanner = new Scanner(System.in);

        if (registrado && (bloqueado || recuperar_contrasena)) {

            System.out.println("=====================================");
            System.out.println("     COMPROBACIÓN DE SEGURIDAD       ");
            System.out.println("=====================================");

            System.out.print("Introduce nombre de usuario: ");
            String nombre_usuario = scanner.nextLine().trim();
            System.out.print("Pregunta de Seguridad: ¿Cuál es tu color favorito? ");
            String respuesta_usuario = scanner.nextLine().trim();

            if (respuesta_seguridad.equalsIgnoreCase(respuesta_usuario) && nombre_usuario.equalsIgnoreCase(usuario_registrado)) {
                System.out.println("\t**** RESPUESTA CORRECTA ****");

                if (bloqueado) {
                    bloqueado = false;
                }

                if (recuperar_contrasena) {
                    registrar_contrasena();
                }
            } else {
                System.out.println("\t*** RESPUESTA INCORRECTA **.");
                //menu();
            }
        } else {
            System.out.println("No hay usuarios registrados o bloqueados.");
        }
    }

    /**
     * Método que permite registrar una contraseña.
     */
   public static String registrar_contrasena() {
        String contrasena_registrada2;
        do {
            System.out.println("Introduce una Contraseña (con may, min, numero y caracter especial):");
            Scanner escaner_contrasena1 = new Scanner(System.in);
            contrasena_registrada1 = escaner_contrasena1.nextLine().trim();

            System.out.println("Repite la Contraseña:");
            Scanner escaner_contrasena2 = new Scanner(System.in);
            contrasena_registrada2 = escaner_contrasena2.nextLine().trim();

        } while (!validaciones.verificar_contrasenas(contrasena_registrada1, contrasena_registrada2));

       return contrasena_registrada2;
   }

    /**
     * Comprueba si el usuario está bloqueado y llama al proceso de recuperación si es necesario.
     */
    public static void comprobar_bloqueo() {
        if (bloqueado) {
            System.out.println("Usuario BLOQUEADO");
            //recuperacion(false);
        }
    }


    public static LocalDate formatearFecha(String fecha) {
            // Revisar formatter por que fecha quizas no lo necesite en localdate

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(fecha, formatter);

            System.out.println("Fecha convertida: " + localDate.format(formatter));

            return localDate;
        }



}
