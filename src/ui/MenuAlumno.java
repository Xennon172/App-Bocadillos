package ui;

import data.GesData;
import modelos.*;
import servicios.ServiciosPedido;
import utiles.Validaciones;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuAlumno {

    /**
     * Esta clase se encarga de mostrar las opciones del Menu de Alumno
     * Usa un submenu de la clase MenuUsuario, algunos metodos utilizan obtenerDatos y volcarLista
     * con el fin de obtener los ultimos registros del .dat y guardar en el archivo,
     */

    public static void mostrar() {
        ServiciosPedido serviciosPedido = new ServiciosPedido();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(">> MENU ALUMNO <<");
            System.out.println("1. Listar Bocadillos");
            System.out.println("2. Realizar Pedido");
            System.out.println("3. Ver Historial");
            System.out.println("4. Cancelar Bocadillo");
            System.out.println("5. Salir");
            System.out.print("> ");

            int opcion;

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número del 1 al 5.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:

                    ServiciosPedido servicioObtener = new ServiciosPedido();
                    servicioObtener.listarBocadillosDelDia();
                    break;
                case 2:
                    GesData.inicializarCalendarios(); // Cargar calendario con rotación
                    Validaciones validaciones = new Validaciones();

                    try {
                        Usuario usuarioActual = Sesion.getUsuarioActual();
                        if (usuarioActual == null) {
                            System.out.println("No hay usuario activo. Debes iniciar sesión primero.");
                            break;
                        }

                        String idUsuarioActual = usuarioActual.getId();
                        String nombreUsuario = usuarioActual.getNombre();  // Suponiendo que el usuario tiene un método getNombre()

                        LocalDate fechaPedido = null;

                        while (fechaPedido == null) {
                            System.out.print("Introduce el día del Pedido (1-31): ");
                            String diaStr = sc.nextLine();

                            System.out.print("Introduce el mes del Pedido (1-12): ");
                            String mesStr = sc.nextLine();

                            System.out.print("Introduce el año del Pedido (1900-2025): ");
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
                                if (!validaciones.validarFecha(diaStr, mesStr, anoStr)) {
                                    // Ya imprime mensaje dentro de validar_fecha
                                    continue;
                                }

                                // Si llegó aquí la fecha es válida, crear LocalDate
                                fechaPedido = LocalDate.of(ano, mes, dia);

                            } catch (NumberFormatException e) {
                                System.out.println("Por favor, ingresa solo números.");
                            } catch (Exception e) {
                                System.out.println("Error inesperado, intenta de nuevo.");
                            }
                        }


                        // Validar hora con formato HH:mm y asegurarse que esté en el turno de 08:00-10:30
                        LocalTime horaPedido;
                        while (true) {
                            System.out.print("Ingrese la hora del pedido (HH:mm): ");
                            String horaInput = sc.nextLine().trim();
                            try {
                                horaPedido = LocalTime.parse(horaInput);

                                // Validar que la hora esté dentro del turno de la mañana (08:00-10:30)
                                if (horaPedido.isBefore(LocalTime.of(8, 0)) || horaPedido.isAfter(LocalTime.of(10, 30))) {
                                    System.out.println("Solo se permiten horas entre 08:00-10:30.");
                                    continue;
                                }
                                break;
                            } catch (DateTimeParseException ex) {
                                System.out.println("Formato de hora inválido. Debe ser HH:mm");
                            }
                        }


                        ServiciosPedido serviciosPedidos = new ServiciosPedido();
                        Calendario calendario = serviciosPedidos.obtenerCalendarioParaFecha(fechaPedido);
                        if (calendario == null) {
                            System.out.println("No hay calendario activo para la fecha seleccionada.");
                            break;
                        }


                        serviciosPedido.obtenerTodos(); // Esto llena GesData.listar_pedido

                        int idNuevo = Validaciones.generarNuevoIdPedido();

                        // Pedido temporal para obtener bocadillos rotados del día
                        Pedido pedidoTemp = new Pedido(idNuevo, idUsuarioActual, -1, fechaPedido, horaPedido, "En Espera");

                        Bocadillo[] bocadillosDelDia = serviciosPedidos.obtenerBocadillosParaPedido(pedidoTemp, calendario);
                        if (bocadillosDelDia == null) {
                            System.out.println("No hay bocadillos disponibles para esta fecha.");
                            break;
                        }

                        // Mostrar los bocadillos disponibles
                        System.out.println("Bocadillos disponibles para el turno de 08:00 a 10:30:");
                        System.out.println("1 - " + bocadillosDelDia[0].getNombre() + " (Frío)");
                        System.out.println("2 - " + bocadillosDelDia[1].getNombre() + " (Caliente)");

                        // Permitir al usuario seleccionar un bocadillo
                        int seleccion = -1;
                        while (seleccion < 1 || seleccion > 2) {
                            System.out.print("Selecciona un bocadillo (1 o 2): ");
                            seleccion = Integer.parseInt(sc.nextLine().trim());
                            if (seleccion < 1 || seleccion > 2) {
                                System.out.println("Selección inválida. Por favor, elige 1 o 2.");
                            }
                        }

                        // Confirmar pedido
                        System.out.print("Confirma el pedido con el bocadillo seleccionado? (S/N): ");
                        String confirma = sc.nextLine().trim().toUpperCase();

                        if (!confirma.equals("S")) {
                            System.out.println("Pedido cancelado.");
                            break;
                        }

                        // Crear el pedido definitivo con el bocadillo seleccionado
                        int idBocadilloSeleccionado = seleccion == 1 ? bocadillosDelDia[0].getId() : bocadillosDelDia[1].getId();
                        Pedido pedido = new Pedido(idNuevo, idUsuarioActual, idBocadilloSeleccionado, fechaPedido, horaPedido, "En Espera");
                        pedido.setHoraPedido(horaPedido);

                        boolean creado = serviciosPedidos.crearPedidoConTurnos(pedido, calendario, bocadillosDelDia[seleccion - 1], horaPedido);

                        if (creado) {


                            // Guardar la lista actualizada en el archivo Pedido.dat
                            ServiciosPedido.volcarListas();

                            System.out.println("Perfecto " + nombreUsuario + ", tu pedido con ID: " + idNuevo + " se ha realizado correctamente.");
                        } else {
                            System.out.println("No se pudo crear el pedido.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error en los datos ingresados. Intenta de nuevo.");
                        e.printStackTrace();  // Para ayudarte a depurar errores reales
                    }

                    break;
                case 3:
                    Usuario usuarioActual = Sesion.getUsuarioActual();

                    String idUsuarioActual = usuarioActual.getId();
                    ServiciosPedido serviciosVerHistorial = new ServiciosPedido();
                    serviciosVerHistorial.obtenerTodos();

                    serviciosVerHistorial.verHistorialPedidos(idUsuarioActual);
                    break;
                case 4:
                    Usuario usuarioActual1 = Sesion.getUsuarioActual();
                    String idUsuarioActualizado = usuarioActual1.getId();

                    ServiciosPedido serviciosCancelarPedido = new ServiciosPedido();
                    serviciosCancelarPedido.obtenerTodos();
                    serviciosCancelarPedido.cancelarPedido(idUsuarioActualizado);

                    break;

                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Selecciona del 1 al 6.");
                    break;
            }
        }
    }

}
