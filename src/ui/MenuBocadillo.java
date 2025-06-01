package ui;

import data.GesData;
import modelos.Bocadillo;
import servicios.ServiciosBocadillo;
import utiles.Validaciones;

import java.io.File;
import java.util.Scanner;



public class MenuBocadillo {


    public static void mostrar() {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("*** GESTIÓN DE BOCADILLOS ***");
            System.out.println("1. Listar bocadillos");
            System.out.println("2. Buscar bocadillo");
            System.out.println("3. Añadir bocadillo");
            System.out.println("4. Eliminar bocadillo");
            System.out.println("5. Modificar bocadillo");
            System.out.println("6. Volver al menú anterior");
            System.out.print("Selecciona una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    File archivo = new File("src/persistencia/Bocadillo.dat");
                    if (!archivo.exists() || archivo.length() == 0) {
                        GesData.cargarBocadillo();   // Carga datos iniciales solo si no hay archivo o está vacío
                        ServiciosBocadillo.volcarListas();  // Guarda esos datos iniciales en archivo
                    }

                    Validaciones val = new Validaciones();
                    ServiciosBocadillo serviciosObtener = new ServiciosBocadillo();

                    for (Bocadillo b : serviciosObtener.obtenerTodos()) {
                        System.out.println(b.getId() + " - " + b.getNombre());
                    }

                    break;

                case 2:
                    // Buscar bocadillo POR NOMBRE
                    System.out.print("Introduce Nombre del bocadillo: ");
                    String nombreBuscar = sc.nextLine();
                    ServiciosBocadillo servicioBuscar = new ServiciosBocadillo();

                    Bocadillo encontrado = servicioBuscar.buscar(nombreBuscar);
                    if (encontrado != null) {
                        encontrado.mostrar_info();
                    } else {
                        System.out.println("Bocadillo no encontrado.");
                    }
                    break;

                case 3:
                    Validaciones validaciones = new Validaciones();
                    ServiciosBocadillo servicioInsertar = new ServiciosBocadillo();


                    servicioInsertar.obtenerTodos(); // Esto llena GesData.listar_pedido

                    // Ahora genera el nuevo ID tomando el ultimo del .dat
                    int idNuevo = Validaciones.generarNuevoIdBocadillo();
                    System.out.print("Nombre: ");
                    String nombreNuevo = sc.nextLine();

                    if (servicioInsertar.buscar(nombreNuevo) != null) {
                        System.out.println("Ya existe un bocadillo con ese nombre.");
                        break;
                    }

                    System.out.print("Descripción: ");
                    String descNuevo = sc.nextLine();

                    System.out.print("Ingredientes (separados por coma): ");
                    String ingredientesInput = sc.nextLine();
                    String[] ingredientes = ingredientesInput.split(",");

                    System.out.print("Alérgenos (separados por coma): ");
                    String alergenosInput = sc.nextLine();
                    String[] alergenos = alergenosInput.split(",");

                    System.out.print("Ciudad popular: ");
                    String ciudadPopular = sc.nextLine();

                    System.out.print("Curiosidad: ");
                    String curiosidad = sc.nextLine();

                    Validaciones val1 = new Validaciones();
                    double precio = val1.leerPrecioConValidacion(sc, "Precio: ");

                    System.out.print("¿Es frío? (true/false): ");
                    boolean esFrio = sc.nextBoolean();
                    sc.nextLine();

                    Bocadillo nuevoBocadillo = new Bocadillo(
                            idNuevo,
                            nombreNuevo,
                            descNuevo,
                            ingredientes,
                            alergenos,
                            ciudadPopular,
                            curiosidad,
                            precio,
                            esFrio
                    );

                    if (servicioInsertar.insertar(nuevoBocadillo)) {
                        servicioInsertar.volcarListas();  // Guarda lista actualizada en archivo
                        System.out.println("Bocadillo añadido correctamente.");
                    } else {
                        System.out.println("Error al añadir el bocadillo.");
                    }
                    break;


                case 4:

                    ServiciosBocadillo servicioEliminar = new ServiciosBocadillo();

                    System.out.print("Introduce NOMBRE del bocadillo a eliminar: ");
                    String nombreEliminar = sc.nextLine();

                    if (servicioEliminar.eliminar(nombreEliminar)) {
                        System.out.println("Bocadillo eliminado correctamente.");
                    } else {
                        System.out.println("Bocadillo no encontrado.");
                    }
                    break;

                case 5:
                    // Código de interacción con el usuario para modificar bocadillo

                    ServiciosBocadillo servicioModificar = new ServiciosBocadillo();

                    System.out.print("Introduce NOMBRE del bocadillo a modificar: ");
                    String nombreOriginal = sc.nextLine();

                    Bocadillo bocadilloModificar = servicioModificar.buscar(nombreOriginal);
                    if (bocadilloModificar == null) {
                        System.out.println("Bocadillo no encontrado.");
                        break;
                    }

                    System.out.print("Nuevo nombre (actual: " + bocadilloModificar.getNombre() + "): ");
                    String nombreMod = sc.nextLine();
                    if (nombreMod.isEmpty()) nombreMod = bocadilloModificar.getNombreNuevo();


                    System.out.print("Nueva descripción (actual: " + bocadilloModificar.getDescripcion() + "): ");
                    String descMod = sc.nextLine();
                    if (descMod.isEmpty()) descMod = bocadilloModificar.getDescripcion();

                    System.out.print("Nuevos ingredientes (separados por coma, actual: ");
                    if (bocadilloModificar.getIngredientes() != null) {
                        System.out.print(String.join(", ", bocadilloModificar.getIngredientes()));
                    }
                    System.out.print("): ");
                    String ingModInput = sc.nextLine();
                    String[] ingredientesMod = ingModInput.isEmpty() ? bocadilloModificar.getIngredientes() : ingModInput.split(",");

                    System.out.print("Nuevos alérgenos (separados por coma, actual: ");
                    if (bocadilloModificar.getAlergenos() != null) {
                        System.out.print(String.join(", ", bocadilloModificar.getAlergenos()));
                    }
                    System.out.print("): ");
                    String alerModInput = sc.nextLine();
                    String[] alergenosMod = alerModInput.isEmpty() ? bocadilloModificar.getAlergenos() : alerModInput.split(",");

                    System.out.print("Nueva ciudad popular (actual: " + bocadilloModificar.getCiudad_popular() + "): ");
                    String ciudadMod = sc.nextLine();
                    if (ciudadMod.isEmpty()) ciudadMod = bocadilloModificar.getCiudad_popular();

                    System.out.print("Nueva curiosidad (actual: " + bocadilloModificar.getCuriosidad() + "): ");
                    String curiosidadMod = sc.nextLine();
                    if (curiosidadMod.isEmpty()) curiosidadMod = bocadilloModificar.getCuriosidad();


                    Validaciones vali = new Validaciones();
                    double precioMod;
                    while (true) {
                        System.out.print("Nuevo precio (actual: " + bocadilloModificar.getPrecio() + "): ");
                        String precioModStr = sc.nextLine().trim();

                        if (precioModStr.isEmpty()) {
                            // Mantener el precio actual si el usuario no ingresa nada
                            precioMod = bocadilloModificar.getPrecio();
                            break;
                        }

                        // Validar que solo tenga dígitos y una coma
                        if (vali.validarDecimalConComa(precioModStr)) {
                            // Reemplazar coma por punto para parsear
                            precioModStr = precioModStr.replace(',', '.');
                            try {
                                precioMod = Double.parseDouble(precioModStr);
                                break; // Salir del ciclo si todo está bien
                            } catch (NumberFormatException e) {
                                System.out.println("Error al convertir el número, intenta de nuevo.");
                            }
                        } else {
                            System.out.println("Formato inválido. Solo números y una coma permitidos.");
                        }
                    }

                    System.out.print("¿Es frío? (true/false, actual: " + bocadilloModificar.getEsFrio() + "): ");
                    String esFrioStr = sc.nextLine();
                    boolean esFrioMod = esFrioStr.isEmpty() ? bocadilloModificar.getEsFrio() : Boolean.parseBoolean(esFrioStr);

                    Bocadillo actualizadoBocadillo = new Bocadillo(
                            nombreOriginal,
                            descMod,
                            ingredientesMod,
                            alergenosMod,
                            ciudadMod,
                            curiosidadMod,
                            precioMod,
                            esFrioMod,
                            nombreMod
                    );


                    if (servicioModificar.modificar(actualizadoBocadillo)) {
                        System.out.println("Bocadillo modificado correctamente.");
                    } else {
                        System.out.println("Error al modificar el bocadillo.");
                    }

                    break;

                case 6:
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}