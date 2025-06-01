package data;

import modelos.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GesData {

    /**
     * Esta clase se encarga de almacenar toda la informacion inicial
     * Contiene la informacion de usuarios, bocadillos, pedidos, calendarios.
     * Incluye un metodo que rota los bocadillos en base a la fecha de inicio y fecha fin
     * tales fechas siempre corresponde al dia lunes inicio, y viernes final
     * @return Si un usuario ingresa una fecha fuera del limite establecido o de horas tendra
     * un mensaje de notificiacion.
     */

    // Listas globales

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public static ArrayList<Bocadillo> listaBocadillo = new ArrayList<>();
    public static ArrayList<Pedido> listarPedido = new ArrayList<>();
    public static Calendario[] calendario = new Calendario[30];

    public static ArrayList<Bocadillo> listaBocadilloCaliente = new ArrayList<>();
    public static ArrayList<Bocadillo> listaBocadilloFrio = new ArrayList<>();


    // Método para cargar usuarios
    public static void cargar_usuarios() {
        listaUsuarios.clear();

        listaUsuarios.add(new Alumno(
                "3",                            // id
                "alumno1",                     // usuario
                "Francisco",                   // nombre
                "Mateo Poveda",                // apellidos
                "francisco@email.com",         // email
                "Alumno12345",                 // password
                LocalDate.of(1987, 5, 12),    // fechaNacimiento
                "12345678A",                   // dni
                new String[]{"veneno"},        // alergias
                "2B12",                       // curso
                "EXP123456"                   // num_expediente
        ));

        listaUsuarios.add(new Alumno(
                "4",
                "alumno2",
                "María",
                "García López",
                "maria@email.com",
                "Alumno54321",
                LocalDate.of(1990, 8, 22),
                "23456789B",
                new String[]{"lacteos"},
                "3A10",
                "EXP654321"
        ));

        listaUsuarios.add(new Alumno(
                "5",
                "alumno3",
                "Carlos",
                "Fernández Martínez",
                "carlos@email.com",
                "Alumno67890",
                LocalDate.of(1992, 11, 5),
                "34567890C",
                new String[]{"gluten"},
                "1B15",
                "EXP987654"
        ));

        listaUsuarios.add(new Alumno(
                "6",
                "alumno4",
                "Lucía",
                "Ramírez Díaz",
                "lucia@email.com",
                "Alumno11223",
                LocalDate.of(1989, 3, 17),
                "45678901D",
                new String[]{},
                "2C08",
                "EXP112233"
        ));

        listaUsuarios.add(new Cocina(
                "7",
                "Bernarda",
                "Maria",
                "de los santos",
                "maria@email.com",
                "Cocina12345",
                LocalDate.of(1987, 1, 1),
                "56789012E"
        ));

        listaUsuarios.add(new Administrador(
                "8",
                "Felix",
                "Felix",
                "Martinez",
                "felix@elcampico.org",
                "Administrador12345",
                LocalDate.of(1987, 1, 1),
                "67890123F"
        ));
    }


    // Método para cargar bocadillos
    public static void cargarBocadillo() {
        // Vaciar listas antes de cargar para evitar duplicados si se llama más de una vez
        listaBocadillo.clear();
        listaBocadilloFrio.clear();
        listaBocadilloCaliente.clear();

        // Añadiendo bocadillos a la lista principal
        listaBocadillo.add(new Bocadillo(3, "Chivito", "Delicioso Bocadillo Mediterráneo",
                new String[]{"Lomo de Cerdo", "Bacon", "Tomate", "Queso Manchego", "Lechuga", "Huevo", "Mayonesa", "Aceite de Oliva"},
                new String[]{"gluten", "lacteos"}, "Valencia", "ninguna", 6.5, true));
        listaBocadillo.add(new Bocadillo(2, "Bocadillo de Lomo con Queso", "Maravilloso Bocadillo de lomo braseado con queso Chedar",
                new String[]{"lomo", "queso chedar", "tomate"}, new String[]{"gluten", "lacteos"}, "Albacete", "ninguna", 6, false));
        listaBocadillo.add(new Bocadillo(1, "Completo", "Bocadillo de grandes proporciones",
                new String[]{"huevo", "pechuga", "bacon", "lechuga", "tomate", "mayonesa"}, new String[]{"gluten", "lacteos"}, "La Murada", "ninguna", 5, false));
        listaBocadillo.add(new Bocadillo(4, "Tortilla", "Delicioso Bocadillo de Tortilla con cebolla",
                new String[]{"Patata", "Cebolla", "Huevo"}, new String[]{"gluten", "lacteos"}, "Murcia", "ninguna", 7.5, false));
        listaBocadillo.add(new Bocadillo(5, "Calamares", "Delicioso Bocadillo de Calamares con Mayonesa",
                new String[]{"Calamares", "Mayonesa"}, new String[]{"gluten", "lacteos"}, "Madrid", "ninguna", 6.5, false));
        listaBocadillo.add(new Bocadillo(6, "Atún con tomate", "Sabroso bocadillo de Atún de la rambla y tomates frescos",
                new String[]{"Atún", "tomate"}, new String[]{"gluten", "lacteos"}, "Benferri", "ninguna", 12.5, false));
        listaBocadillo.add(new Bocadillo(7, "Catalana", "Bocadillo de Jamón Serrano Ibérico",
                new String[]{"Jamon Serrano", "Queso Manchego", "Tomate", "Aceite"}, new String[]{"gluten", "lacteos"}, "Barcelona", "ninguna", 7.5, true));
        listaBocadillo.add(new Bocadillo(8, "Mortadelo", "Bocadillo de Mortadela con Olivas, un clásico",
                new String[]{"mortadela con olivas", "Aceite"}, new String[]{"gluten"}, "Desconocido", "ninguna", 3.5, true));
        listaBocadillo.add(new Bocadillo(9, "Lomo adobado", "Delicioso bocadillo de lomo adobado con queso y tomate en pan de cristal",
                new String[]{"Lomo adobado", "Queso", "Tomate"}, new String[]{"lacteos"}, "Abanilla", "Bocadillo preferido en la fiesta de la Santa Cruz", 5, false));
        listaBocadillo.add(new Bocadillo(10, "Caballa", "Bocadillo de caballa Gallega",
                new String[]{"Caballa", "Aceite de Oliva"}, new String[]{""}, "Lugo", "bocadillo muy demandado en el norte de España", 4, true));
        listaBocadillo.add(new Bocadillo(11, "Bocadillo Vegano", "Aguacate, tomate y lechuga fresca",
                new String[]{"Aguacate", "Tomate", "Lechuga"}, new String[]{"gluten"}, "Madrid", "Para veganos", 6.5, true));
        listaBocadillo.add(new Bocadillo(12, "Bocadillo de Queso Fresco", "Queso fresco con rúcula y tomate cherry",
                new String[]{"Queso fresco", "Rúcula", "Tomate cherry"}, new String[]{"lacteos"}, "Valencia", "Ideal para el verano", 5.5, true));



        // Clasificar bocadillos en frío y caliente
        for (Bocadillo b : listaBocadillo) {
            if (b.isEsFrio()) {
                listaBocadilloFrio.add(b);
            } else {
                listaBocadilloCaliente.add(b);
            }
        }
    }


    // Método para inicializar pedidos
    public static void inicializar_pedidos() {
        listarPedido.add(new Pedido(1, "3", 2, LocalDate.of(2025, 2, 1), LocalTime.of(8, 0),   "En Espera"));
        listarPedido.add(new Pedido(2, "4", 3, LocalDate.of(2025, 2, 1), LocalTime.of(8, 30), "En Preparación"));
        listarPedido.add(new Pedido(3, "5", 4, LocalDate.of(2025, 2, 1),LocalTime.of(9, 0), "Listo"));
        listarPedido.add(new Pedido(4, "5", 4, LocalDate.of(2025, 2, 1),LocalTime.of(9, 30), "Listo"));
        listarPedido.add(new Pedido(5, "6", 5, LocalDate.of(2025, 2, 1), LocalTime.of(10, 0), "Retirado"));
    }

    public static Calendario inicializarCalendarioConRotacion() {
        // Obtener la fecha de hoy
        LocalDate fechaHoy = LocalDate.now();

        // Obtener el lunes de la semana actual
        LocalDate fechaInicio = fechaHoy.with(DayOfWeek.MONDAY);
        // Obtener el viernes de la misma semana
        LocalDate fechaFin = fechaInicio.with(DayOfWeek.FRIDAY);

        // Listas de bocadillos fríos y calientes
        List<Bocadillo> listaBocadilloFrio = GesData.listaBocadilloFrio;
        List<Bocadillo> listaBocadilloCaliente = GesData.listaBocadilloCaliente;

        // Listas para rotación de bocadillos fríos y calientes
        List<Bocadillo> rotacionFrio = new ArrayList<>();
        List<Bocadillo> rotacionCaliente = new ArrayList<>();

        // Definir cuántos días estarán en el calendario (lunes a viernes)
        int dias = 5; // Lunes a viernes

        // Llenar las rotaciones
        for (int i = 0; i < dias; i++) {
            rotacionFrio.add(listaBocadilloFrio.get(i % listaBocadilloFrio.size()));
            rotacionCaliente.add(listaBocadilloCaliente.get(i % listaBocadilloCaliente.size()));
        }

        // Crear y retornar el calendario con rotación
        return new Calendario(
                1,                          // ID del calendario
                fechaInicio,                // Fecha de inicio: lunes de la semana actual
                fechaFin,                   // Fecha de fin: viernes de la misma semana
                rotacionFrio,               // Lista de bocadillos fríos para la semana
                rotacionCaliente            // Lista de bocadillos calientes para la semana
        );
    }



    public static void inicializarCalendarios() {

        GesData.calendario = new Calendario[30];

        GesData.calendario[0] = inicializarCalendarioConRotacion();
    }




}



