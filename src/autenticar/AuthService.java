package autenticar;

import data.GesData;
import excepciones.DNIexcepcion;
import excepciones.EmailNoValidoExcepcion;
import modelos.*;
import servicios.ServiciosUsuario;
import utiles.Validaciones;

import java.time.LocalDate;
import java.util.Scanner;

public class AuthService {

    /**
     * Este método permite a un usuario iniciar sesión en el sistema.
     * Pide el nombre de usuario y la contraseña, y los compara con los datos guardados.
     * Estos datos no son lo del arraylist sino los del archivo .dat
     * Si el usuario y la contraseña son correctos, el login es exitoso.
     * Si no, pide los datos de nuevo.
     * Es importante tener cargado el dat sino habra un error de formato
     * Por eso hay una linea adicional comentada para hacer una prueba con los datos iniciales
     * y guardar o escribir en el .dat
     */

    public static Usuario login() {
        ServiciosUsuario servicios = new ServiciosUsuario();
        GesData.listaUsuarios = servicios.obtenerTodos(); //obtiene del fichero y lo añade al array
       // GesData.cargar_usuarios(); //solo lee los datos de array que estan en GesData

        Scanner sc = new Scanner(System.in);
        String username, contrasena;


        while (true) {
            System.out.print("Usuario: ");
            username = sc.nextLine();
            System.out.print("Contraseña: ");
            contrasena = sc.nextLine();

            for (Usuario usuario : GesData.listaUsuarios) {
                if (usuario != null && usuario.getUsuario().equals(username) && usuario.getPassword().equals(contrasena)) {
                    System.out.println("Inicio de sesión correcto. Bienvenido " + usuario.getNombre());
                    return usuario;
                }
            }

            System.out.println("El usuario o la contraseña son incorrectos, intenta de nuevo.");
        }
    }

    /**
     * Muestra un menú de opciones general o principal para el uusario
     * Dependiendo de la opción seleccionada, el usuario tendra que ingresar un conjunto de datos
     * desde el teclado.
     * Si el usuario selecciona iniciar sesion, debe ingresar su usuario y contraseña
     * se obtiene el rol del usuario y apartir de alli tendra un menu correspondiente.
     * Para cambiar la contraseña y registrarse funciona como un formulario simple.
     * @return Si un usuario inicia sesion correctamente debe devolver el rol (Alumno,Administrador,Cocina)
     */
    public static String mostrarLogin() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("*** BIENVENIDO AL SISTEMA DE BOCADILLOS ***");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Recuperar contraseña");
        System.out.println("4. Salir ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {
            case 1:
                Usuario usuario = login();  // Devuelve el objeto Usuario (Alumno, Cocina, Administrador)
                if (usuario != null) {
                    Sesion.iniciarSesion(usuario);
                    // Para obtener el "rol", devuelve el nombre simple de la clase
                    return usuario.getClass().getSimpleName();
                } else {
                    return null;
                }
            case 2:
                registro();
                Sesion.cerrarSesion();  // Limpiar sesión luego de registro
                return null;
            case 3:
                recuperarContraseña();
                Sesion.cerrarSesion();  // Limpiar sesión luego de recuperación
                return null;
            case 4:
                System.out.println("Saliendo del sistema...");
                Sesion.cerrarSesion();  // Opcional: limpiar sesión antes de salir
                System.exit(0);
            default:
                System.out.println("Opción no válida.");
                return null;
        }
    }


    /**
     * Permite a un usuario crear su perfil para iniciar sesion y tener su menu correspondiente
     * Se comprobara su fecha de nacimiento, nombres, apellidos, dni, correo.
     * Dependiendo del tipo de clase si es Administrador y Cocina los campos son iguales.
     * Si es Alumno ingresera adicionalmente su expediente, alergias y curso.
     * Algunos datos estan validados con la clase Validaciones y Excepciones personalizadas.
     * El ID y el Numero de Expedientes es autogenerico, evitando duplicaciones y sobreescribir el.dat
     * Se debe llamar al metodo volcarListas para guardar los datos del arraylist en el .dat.
     * @return Si un usuario se registra correctamente, saldra un mensaje exitoso y podra iniciar sesion.
     */
    public static void registro() {
        Validaciones val = new Validaciones();
        Scanner sc = new Scanner(System.in);
        ServiciosUsuario servicios = new ServiciosUsuario();

        // Pedir DNI para todos los usuarios
        String DNIUsuario;
        while (true) {
            System.out.print("Introduce el DNI/NIE: ");
            DNIUsuario = sc.nextLine();
            try {
                val.validarDNI(DNIUsuario);
                break;
            } catch (DNIexcepcion e) {
                System.out.println(e.getMessage() + " Intenta de nuevo:");
            }
        }

        System.out.print("Introduce el nombre de usuario: ");
        String usernameNuevo = sc.nextLine();
        if (servicios.buscar(usernameNuevo) != null) {
            System.out.println("El usuario ya existe.");
        }

        System.out.println("Selecciona el tipo de usuario:");
        System.out.println("1. Alumno");
        System.out.println("2. Cocina");
        System.out.println("3. Administrador");
        int tipoUsuario = sc.nextInt();
        sc.nextLine();

        Usuario nuevoUsuario = null;  // Variable para guardar el nuevo usuario

        switch (tipoUsuario) {
            case 1: // Alumno
                int contadorId = 8;
                int idNuevo = contadorId++;
                String idAlumno = ""+ idNuevo;

                System.out.print("Nombre completo: ");
                String nombreAlumno;
                do {
                    nombreAlumno = sc.nextLine();
                } while (!val.validarNombre(nombreAlumno));

                System.out.print("Apellidos: ");
                String apellidosAlumno = sc.nextLine();

                String emailAlumno;
                while (true) {
                    System.out.print("Email: ");
                    emailAlumno = sc.nextLine();
                    try {
                        val.validarEmailConExcepcion(emailAlumno);
                        break;
                    } catch (EmailNoValidoExcepcion e) {
                        System.out.println(e.getMessage() + " Intenta de nuevo:");
                    }
                }

                String passAlumno, passAlumno2;
                do {
                    System.out.print("Contraseña: ");
                    passAlumno = sc.nextLine();
                    System.out.print("Confirma la contraseña: ");
                    passAlumno2 = sc.nextLine();
                } while (!val.verificarContrasenas(passAlumno, passAlumno2));

                LocalDate fechaNacimientoAlumno = null;

                while (fechaNacimientoAlumno == null) {
                    System.out.print("Introduce el día de nacimiento (1-31): ");
                    String diaStr = sc.nextLine();

                    System.out.print("Introduce el mes de nacimiento (1-12): ");
                    String mesStr = sc.nextLine();

                    System.out.print("Introduce el año de nacimiento (1900-2025): ");
                    String anoStr = sc.nextLine();

                    // Validar solo que sean números (puedes usar try-catch)
                    try {
                        int dia = Integer.parseInt(diaStr);
                        int mes = Integer.parseInt(mesStr);
                        int ano = Integer.parseInt(anoStr);

                        // Validar rango básico para no tirar excepciones
                        if (dia < 1 || dia > 31) {
                            System.out.println("Día inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (mes < 1 || mes > 12) {
                            System.out.println("Mes inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (ano < 1900 || ano > 2025) {
                            System.out.println("Año inválido. Intenta de nuevo.");
                            continue;
                        }

                        // Usar tu método para validar fecha correcta (días según mes y año bisiesto)
                        if (!val.validarFecha(diaStr, mesStr, anoStr)) {
                            // Ya imprime mensaje dentro de validar_fecha
                            continue;
                        }

                        // Si llegó aquí la fecha es válida, crear LocalDate
                        fechaNacimientoAlumno = LocalDate.of(ano, mes, dia);

                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingresa solo números.");
                    } catch (Exception e) {
                        System.out.println("Error inesperado, intenta de nuevo.");
                    }
                }


                System.out.print("Alergias (separadas por coma): ");
                String[] alergias = sc.nextLine().split(",");

                System.out.print("Curso: ");
                String cursoAlumno = sc.nextLine();

                String numExpedienteAlumno = Validaciones.generarNumeroExpediente();


                // Crear objeto Alumno
                nuevoUsuario = new Alumno(
                        idAlumno,
                        usernameNuevo,
                        nombreAlumno,
                        apellidosAlumno,
                        emailAlumno,
                        passAlumno,
                        fechaNacimientoAlumno,
                        DNIUsuario,
                        alergias,
                        cursoAlumno,
                        numExpedienteAlumno
                );
                break;

            case 2: // Cocina
                int contadorIdCocina = 8;
                int idNuevoCocina = contadorIdCocina++;
                String idCocina = ""+ idNuevoCocina;


                System.out.print("Nombre completo: ");
                String nombreCocina;
                do {
                    nombreCocina = sc.nextLine();
                } while (!val.validarNombre(nombreCocina));

                System.out.print("Apellidos: ");
                String apellidosCocina = sc.nextLine();

                String emailCocina;
                do {
                    System.out.print("Email: ");
                    emailCocina = sc.nextLine();
                } while (!val.validarEmail(emailCocina));

                String passCocina, passCocina2;
                do {
                    System.out.print("Contraseña: ");
                    passCocina = sc.nextLine();
                    System.out.print("Confirma la contraseña: ");
                    passCocina2 = sc.nextLine();
                } while (!val.verificarContrasenas(passCocina, passCocina2));

                LocalDate fechaNacimientoCocina=null;

                while (fechaNacimientoCocina == null) {
                    System.out.print("Introduce el día de nacimiento (1-31): ");
                    String diaStr = sc.nextLine();

                    System.out.print("Introduce el mes de nacimiento (1-12): ");
                    String mesStr = sc.nextLine();

                    System.out.print("Introduce el año de nacimiento (1900-2025): ");
                    String anoStr = sc.nextLine();

                    // Validar solo que sean números (puedes usar try-catch)
                    try {
                        int dia = Integer.parseInt(diaStr);
                        int mes = Integer.parseInt(mesStr);
                        int ano = Integer.parseInt(anoStr);

                        // Validar rango básico para no tirar excepciones
                        if (dia < 1 || dia > 31) {
                            System.out.println("Día inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (mes < 1 || mes > 12) {
                            System.out.println("Mes inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (ano < 1900 || ano > 2025) {
                            System.out.println("Año inválido. Intenta de nuevo.");
                            continue;
                        }

                        // Usar tu método para validar fecha correcta (días según mes y año bisiesto)
                        if (!val.validarFecha(diaStr, mesStr, anoStr)) {
                            // Ya imprime mensaje dentro de validar_fecha
                            continue;
                        }

                        // Si llegó aquí la fecha es válida, crear LocalDate
                        fechaNacimientoCocina = LocalDate.of(ano, mes, dia);

                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingresa solo números.");
                    } catch (Exception e) {
                        System.out.println("Error inesperado, intenta de nuevo.");
                    }
                }


                // Crear objeto Cocina
                nuevoUsuario = new Cocina(
                        idCocina,
                        usernameNuevo,
                        nombreCocina,
                        apellidosCocina,
                        emailCocina,
                        passCocina,
                        fechaNacimientoCocina,
                        DNIUsuario
                );
                break;

            case 3: // Administrador
                int contadorIdAdministrador = 8;
                int idNuevoAdministrador = contadorIdAdministrador++;
                String idAdmistrador = ""+ idNuevoAdministrador;

                System.out.print("Nombre completo: ");
                String nombreAdmin;
                do {
                    nombreAdmin = sc.nextLine();
                } while (!val.validarNombre(nombreAdmin));

                System.out.print("Apellidos: ");
                String apellidosAdmin = sc.nextLine();

                String emailAdmin;
                do {
                    System.out.print("Email: ");
                    emailAdmin = sc.nextLine();
                } while (!val.validarEmail(emailAdmin));

                String passAdmin, passAdmin2;
                do {
                    System.out.print("Contraseña: ");
                    passAdmin = sc.nextLine();
                    System.out.print("Confirma la contraseña: ");
                    passAdmin2 = sc.nextLine();
                } while (!val.verificarContrasenas(passAdmin, passAdmin2));

                LocalDate fechaNacimientoAdmin = null;

                while (fechaNacimientoAdmin == null) {
                    System.out.print("Introduce el día de nacimiento (1-31): ");
                    String diaStr = sc.nextLine();

                    System.out.print("Introduce el mes de nacimiento (1-12): ");
                    String mesStr = sc.nextLine();

                    System.out.print("Introduce el año de nacimiento (1900-2025): ");
                    String anoStr = sc.nextLine();

                    // Validar solo que sean números (puedo usar try-catch)
                    try {
                        int dia = Integer.parseInt(diaStr);
                        int mes = Integer.parseInt(mesStr);
                        int ano = Integer.parseInt(anoStr);

                        // Validar rango básico para no tirar excepciones
                        if (dia < 1 || dia > 31) {
                            System.out.println("Día inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (mes < 1 || mes > 12) {
                            System.out.println("Mes inválido. Intenta de nuevo.");
                            continue;
                        }
                        if (ano < 1900 || ano > 2025) {
                            System.out.println("Año inválido. Intenta de nuevo.");
                            continue;
                        }

                        // Usar tu método para validar fecha correcta (días según mes y año bisiesto)
                        if (!val.validarFecha(diaStr, mesStr, anoStr)) {
                            // Ya imprime mensaje dentro de validar_fecha
                            continue;
                        }

                        // Si llegó aquí la fecha es válida, crear LocalDate
                        fechaNacimientoCocina = LocalDate.of(ano, mes, dia);

                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingresa solo números.");
                    } catch (Exception e) {
                        System.out.println("Error inesperado, intenta de nuevo.");
                    }
                }



                // Crear objeto Administrador
                nuevoUsuario = new Administrador(
                        usernameNuevo,
                        nombreAdmin,
                        emailAdmin,
                        passAdmin,
                        idAdmistrador,
                        apellidosAdmin,
                        fechaNacimientoAdmin,
                        DNIUsuario
                );
                break;

            default:
                System.out.println("Tipo de usuario inválido.");
                break;
        }

        if (nuevoUsuario != null) {
            nuevoUsuario.setEstado(false);
            // Insertar usuario nuevo
            if (servicios.insertar(nuevoUsuario)) {
                servicios.volcarListas();  // Guarda lista actualizada en archivo
                System.out.println("Usuario añadido correctamente.");

            } else {
                System.out.println("Error al añadir el usuario.");
            }
        }

    }

    /**
     * El usuario tendra que ingresar su usuario, y si existe dentro del archivo .dat
     * se pasara o actualizara el arraylist, sera verdadero la busqueda y se pedira corrspondientemente
     * una contraseña nueva, si no aparece tambien debe aparacer un mensaje notificando al usuario.
     * @return Si un usuario cambia su contraseña saldra un mensaje exitoso y podra iniciar sesion correctamente
     */
    public static void recuperarContraseña() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("**** RECUPERAR CONTRASEÑA ****");
        System.out.print("Introduce el nombre de usuario: ");
        String usernameRecuperar = entrada.nextLine();

        ServiciosUsuario serviciosContraseña = new ServiciosUsuario();

        Usuario usuario = serviciosContraseña.buscar(usernameRecuperar);
        if (usuario != null) {
            System.out.print("Introduce la nueva contraseña: ");
            String nuevaContraseña = entrada.nextLine();

            usuario.setPassword(nuevaContraseña);

            // Guarda el cambio en la lista y en el archivo
            if (serviciosContraseña.modificar(usuario)) {
                System.out.println("Contraseña actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la contraseña.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }



}
