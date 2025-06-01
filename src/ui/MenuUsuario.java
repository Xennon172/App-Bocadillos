package ui;

import data.GesData;
import excepciones.DNIexcepcion;
import excepciones.EmailNoValidoExcepcion;
import modelos.Administrador;
import modelos.Alumno;
import modelos.Cocina;
import modelos.Usuario;
import servicios.ServiciosUsuario;
import utiles.Validaciones;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class MenuUsuario {

    /**
     * Esta clase se encarga de mostrar las opciones del Menu de Administrador
     * Usa un submenu de la clase MenuUsuario, algunos metodos utilizan obtenerDatos y volcarLista
     * con el fin de obtener los ultimos registros del .dat y guardar en el archivo,
     */
    public static void mostrar() {
        Scanner sc = new Scanner(System.in);
        ServiciosUsuario servicios = new ServiciosUsuario();
        Validaciones val = new Validaciones();

        while (true) {
            System.out.println("*** GESTIÓN DE USUARIOS ***");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Buscar usuario");
            System.out.println("3. Añadir usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Modificar usuario");
            System.out.println("6. Validar usuario");
            System.out.println("7. Volver al menú anterior");
            System.out.print("Selecciona una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    /* Primero se verifica si existe el archivo y si el mismo esta vacio para evitar errores
                       Luego se cargan los datos de la clase GesData
                       Se usa Volcar Datos que permite grabar o escribir en el archivo .dat
                       Finalmente se llama a ServiciosUsuarios donde esta el metodo que lee desde el .dat
                       y se imprime en pantalla, el metodo .getClass es la forma de denigrar el rol ya que
                       de lo contrario habria que crear un string parecido a rol llamado clase por eso
                       se usa el .getClass.getSimpleName
                    */
                    File archivo = new File("src/persistencia/Usuario.dat");
                    if (!archivo.exists() || archivo.length() == 0) {
                        GesData.cargar_usuarios();   // Carga datos iniciales solo si no hay archivo o está vacío
                        ServiciosUsuario.volcarListas();  // Guarda esos datos iniciales en archivo
                    }

                    servicios = new ServiciosUsuario();
                    for (Usuario u : servicios.obtenerTodos()) {
                        System.out.println(u.getUsuario() + " - " + u.getNombre() + " (" + u.getClass().getSimpleName() + ")");
                    }

                    break;


                case 2:
                    /* Se pide el usuario  como campo de filtro, se llama
                       a ServiciosUsuario y se usa el metodo buscar donde se hace
                       una conexion con el .dat y se pasa los ultimas registros al
                       arraylist para mantener la informacion actualizada, esto es
                       mucho mas sencillo que leer directamente el .dat, ya que
                       el .dat se escribe con serializacion

                       Adicionalemente se consulta si el usuario encontrado durante
                       la busqueda es de la clase que simula el tipo de usuario o rol
                       para obtener el resto de atributos, ya que cada clase tiene diferentes
                    */

                    System.out.print("Introduce nombre de usuario a buscar: ");
                    String userBuscar = sc.nextLine();
                    ServiciosUsuario serviciosUsuario = new ServiciosUsuario();
                    Usuario encontrado = serviciosUsuario.buscar(userBuscar);


                if (encontrado != null) {
                    System.out.println("Usuario encontrado:");
                    System.out.println("ID: " + encontrado.getId());
                    System.out.println("Usuario: " + encontrado.getUsuario());
                    System.out.println("Contraseña: " + encontrado.getPassword());
                    System.out.println("Nombre: " + encontrado.getNombre());
                    System.out.println("Apellidos: " + encontrado.getApellidos());
                    System.out.println("Fecha de nacimiento: " + encontrado.getFechaNacimiento());
                    System.out.println("Email: " + encontrado.getEmail());
                    System.out.println("Estado(Pendiente o Activo): " + encontrado.isEstado());


                     if (encontrado instanceof Alumno) {
                        Alumno alumno = (Alumno) encontrado;
                        System.out.println("Alergias: " + Arrays.toString(alumno.getAlergias()));
                        System.out.println("Curso: " + alumno.getCurso());
                        System.out.println("Numero de Expediente: " + alumno.getNumExpediente());

                    }

                } else {
                    System.out.println("Usuario no encontrado.");
                }

                break;

                case 3:

                /*
                Este código permite registrar un nuevo usuario en el sistema.

1. Primero pide y valida el DNI/NIE del usuario para asegurarse de que sea correcto. Si el DNI no es válido,
   se le pide que lo ingrese de nuevo hasta que sea válido.

2. Luego solicita que el usuario ingrese un nombre de usuario (username). Si ese nombre ya existe en el sistema,
   informa que el usuario ya existe y termina la operación.

3. Después, pide que el usuario seleccione el tipo de usuario que quiere registrar: Alumno, Cocina o Administrador.

4. Según el tipo seleccionado, pide los datos específicos para cada usuario:

   - Para Alumno: nombre, apellidos, email, contraseña (y confirmación), fecha de nacimiento, alergias, curso y número de expediente.
   - Para Cocina y Administrador: nombre, apellidos, email, contraseña (y confirmación), y fecha de nacimiento.

   En cada caso, se validan los datos que se ingresan, como el nombre, el email, y la fecha, asegurándose de que tengan el formato correcto. Si algún dato no es válido, se pide que se ingrese de nuevo.

5. Una vez que se recogen y validan todos los datos, se crea un nuevo objeto del tipo correspondiente (Alumno, Cocina o Administrador) con esos datos.

6. Luego se establece que el nuevo usuario está inicialmente inactivo (estado = false).

7. Finalmente, se intenta insertar el nuevo usuario en la lista de usuarios del sistema. Si la inserción es exitosa,
   se guarda la lista actualizada en el archivo para que los cambios sean permanentes, y se muestra un mensaje de éxito.
   Si no, se muestra un mensaje de error.

Este método se asegura de que solo se guarden usuarios con datos válidos y que no se dupliquen nombres de usuario.
*/



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
                        break;
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
                            String idAlumno = Validaciones.generarNuevoIdUsuario();


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
                            String idCocina = Validaciones.generarNuevoIdUsuario();


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
                            String idAdmistrador = Validaciones.generarNuevoIdUsuario();


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
                                    fechaNacimientoAdmin = LocalDate.of(ano, mes, dia);

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
                        break;

                case 4:

                    /*
                    Para eliminar un usuario filtramos por su nombre de usuario
                    removemos el registro de la lista de usuarios de GesData y para actualizar
                    el fichero .dat llamamos al metodo volcarListas();
                    */

                    ServiciosUsuario serviciosEliminar = new ServiciosUsuario();
                    System.out.print("Introduce el nombre de usuario a eliminar: ");
                    String usernameEliminar = sc.nextLine();
                    if (serviciosEliminar.eliminar(usernameEliminar)) {
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 5:
/*
Este método permite modificar los datos de un usuario ya registrado.

Primero, pide el nombre de usuario para saber a quién se va a modificar.
Luego, muestra cada dato actual (como el nombre, email, DNI, etc.) y pregunta si se quiere cambiar.
Si el usuario deja el campo en blanco, se mantiene el dato anterior.
Si escribe un nuevo valor, se valida y se actualiza.

Si el usuario es un Alumno, también se pueden cambiar sus alergias, curso y número de expediente.

Después de hacer los cambios, se guarda en el .dat.

En resumen: este método sirve para actualizar fácilmente la información de un usuario sin borrar su cuenta.
*/


                    System.out.print("Introduce el nombre de usuario del usuario a modificar: ");
                    String username = sc.nextLine();

                    Usuario usuario = servicios.buscar(username);

                    if (usuario == null) {
                        System.out.println("Usuario no encontrado.");
                        return;
                    }

                    System.out.println("Modificando usuario: " + usuario.getUsuario());

                    String nuevoDNI;
                    while (true) {
                        String actualDNI = (usuario.getDni() != null) ? usuario.getDni() : "no disponible";
                        System.out.print("Nuevo DNI/NIE (actual: " + actualDNI + ") o deja en blanco para mantener: ");
                        String entradaDNI = sc.nextLine().trim();

                        if (entradaDNI.isEmpty()) {
                            nuevoDNI = usuario.getDni();
                            break;
                        }

                        try {
                            val.validarDNI(entradaDNI);
                            nuevoDNI = entradaDNI;
                            break;
                        } catch (DNIexcepcion e) {
                            System.out.println(e.getMessage() + " Intenta de nuevo:");
                        }
                    }

                    String nuevoNombre;
                    while (true) {
                        String actualNombre = (usuario.getNombre() != null) ? usuario.getNombre() : "no disponible";
                        System.out.print("Nuevo nombre (actual: " + actualNombre + ") o deja en blanco para mantener: ");
                        String entradaNombre = sc.nextLine().trim();

                        if (entradaNombre.isEmpty()) {
                            nuevoNombre = usuario.getNombre();
                            break;
                        }

                        if (val.validarNombre(entradaNombre)) {
                            nuevoNombre = entradaNombre;
                            break;
                        } else {
                            System.out.println("Nombre inválido. Intenta de nuevo.");
                        }
                    }

                    String nuevosApellidos;
                    if (usuario instanceof Alumno || usuario instanceof Cocina || usuario instanceof Administrador) {
                        String actualApellidos = (usuario.getApellidos() != null) ? usuario.getApellidos() : "no disponible";
                        System.out.print("Nuevos apellidos (actual: " + actualApellidos + ") o deja en blanco para mantener: ");
                        String entradaApellidos = sc.nextLine().trim();
                        nuevosApellidos = entradaApellidos.isEmpty() ? usuario.getApellidos() : entradaApellidos;
                    } else {
                        nuevosApellidos = "";
                    }

                    String nuevoEmail;
                    while (true) {
                        String actualEmail = (usuario.getEmail() != null) ? usuario.getEmail() : "no disponible";
                        System.out.print("Nuevo email (actual: " + actualEmail + ") o deja en blanco para mantener: ");
                        String entradaEmail = sc.nextLine().trim();

                        if (entradaEmail.isEmpty()) {
                            nuevoEmail = usuario.getEmail();
                            break;
                        }

                        try {
                            val.validarEmailConExcepcion(entradaEmail);
                            nuevoEmail = entradaEmail;
                            break;
                        } catch (EmailNoValidoExcepcion e) {
                            System.out.println(e.getMessage() + " Intenta de nuevo:");
                        }
                    }

                    String nuevaPass;
                    while (true) {
                        System.out.print("Nueva contraseña (deja en blanco para mantener): ");
                        String pass = sc.nextLine().trim();

                        if (pass.isEmpty()) {
                            nuevaPass = usuario.getPassword();
                            break;
                        }
                        System.out.print("Confirma la contraseña: ");
                        String pass2 = sc.nextLine().trim();

                        if (val.verificarContrasenas(pass, pass2)) {
                            nuevaPass = pass;
                            break;
                        } else {
                            System.out.println("Las contraseñas no coinciden o no cumplen los requisitos.");
                        }
                    }

                    LocalDate nuevaFecha = null;
                    String fechaTexto = (usuario.getFechaNacimiento() != null) ? usuario.getFechaNacimiento().toString() : "no disponible";
                    System.out.println("Fecha de nacimiento actual: " + fechaTexto);
                    System.out.print("¿Deseas cambiar la fecha? (s/n): ");
                    String cambiarFecha = sc.nextLine().trim();
                    if (cambiarFecha.equalsIgnoreCase("s")) {
                        while (nuevaFecha == null) {
                            System.out.print("Nuevo día de nacimiento (1-31): ");
                            String diaStr = sc.nextLine();
                            System.out.print("Nuevo mes de nacimiento (1-12): ");
                            String mesStr = sc.nextLine();
                            System.out.print("Nuevo año de nacimiento (1900-2025): ");
                            String anoStr = sc.nextLine();

                            try {
                                int dia = Integer.parseInt(diaStr);
                                int mes = Integer.parseInt(mesStr);
                                int ano = Integer.parseInt(anoStr);

                                if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1900 || ano > 2025) {
                                    System.out.println("Fecha inválida. Intenta de nuevo.");
                                    continue;
                                }
                                if (!val.validarFecha(diaStr, mesStr, anoStr)) {
                                    continue;
                                }

                                nuevaFecha = LocalDate.of(ano, mes, dia);

                            } catch (Exception e) {
                                System.out.println("Entrada inválida, intenta de nuevo.");
                            }
                        }
                    } else {
                        nuevaFecha = usuario.getFechaNacimiento();
                    }

                    Usuario usuarioActualizado = null;

                    if (usuario instanceof Alumno) {
                        Alumno alumno = (Alumno) usuario;

                        System.out.print("Modificar alergias (separadas por coma) (actual: " + String.join(", ", alumno.getAlergias()) + ") o deja en blanco: ");
                        String alergiasStr = sc.nextLine();
                        String[] nuevasAlergias = alergiasStr.isEmpty() ? alumno.getAlergias() : alergiasStr.split("\\s*,\\s*");

                        System.out.print("Nuevo curso (actual: " + alumno.getCurso() + ") o deja en blanco: ");
                        String nuevoCurso = sc.nextLine();
                        if (nuevoCurso.isEmpty()) nuevoCurso = alumno.getCurso();

                        System.out.print("Nuevo número de expediente (actual: " + alumno.getNumExpediente() + ") o deja en blanco: ");
                        String nuevoExp = sc.nextLine();
                        if (nuevoExp.isEmpty()) nuevoExp = alumno.getNumExpediente();

                        usuarioActualizado = new Alumno(
                                alumno.getId(),
                                alumno.getUsuario(),
                                nuevoNombre,
                                nuevosApellidos,
                                nuevoEmail,
                                nuevaPass,
                                nuevaFecha,
                                nuevoDNI,
                                nuevasAlergias,
                                nuevoCurso,
                                nuevoExp
                        );

                    } else if (usuario instanceof Cocina) {
                        usuarioActualizado = new Cocina(
                                usuario.getId(),
                                usuario.getUsuario(),
                                nuevoNombre,
                                nuevosApellidos,
                                nuevoEmail,
                                nuevaPass,
                                nuevaFecha,
                                nuevoDNI
                        );

                    } else if (usuario instanceof Administrador) {
                        usuarioActualizado = new Administrador(
                                usuario.getUsuario(),
                                nuevoNombre,
                                nuevoEmail,
                                nuevaPass,
                                usuario.getId(),
                                nuevosApellidos,
                                nuevaFecha,
                                nuevoDNI
                        );
                    }

                    usuarioActualizado.setEstado(usuario.isEstado());

                    if (servicios.modificar(usuarioActualizado)) {
                        System.out.println("Usuario modificado correctamente.");
                    } else {
                        System.out.println("Error al modificar el usuario.");
                    }
                    break;

                case 6:
                    /*
                    Validar consiste en cambiar el estado del usuario de false a true y viceversa,
                    primero se hace una busqueda por nombre de usuario y luego se modifica
                    como el metodo de modificar tiene internamente volcarLista se guardara
                    automaticamente en el archivo

                     */
                    System.out.print("Introduce nombre de usuario a buscar: ");
                    String userBuscarValidar = sc.nextLine();

                    ServiciosUsuario serviciosUsuarioValidar = new ServiciosUsuario();
                    Usuario encontradoValidar = serviciosUsuarioValidar.buscar(userBuscarValidar);

                    if (encontradoValidar != null) {
                        System.out.println("Usuario encontrado: " + encontradoValidar.getUsuario());
                        boolean estadoActual = encontradoValidar.isEstado();
                        System.out.println("Estado actual: " + estadoActual);

                        // Cambiar el estado al contrario
                        encontradoValidar.setEstado(!estadoActual);

                        boolean actualizado = serviciosUsuarioValidar.modificar(encontradoValidar);
                        if (actualizado) {
                            System.out.println("Estado actualizado a: " + encontradoValidar.isEstado());
                        } else {
                            System.out.println("Error al actualizar el estado.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }

                    break;
                case 7:
                    // Volver
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
