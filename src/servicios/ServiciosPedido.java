package servicios;

import data.GesData;
import modelos.*;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiciosPedido implements Crud<Pedido, Integer> {

    /**
     * Este método intenta insertar un nuevo pedido en la lista de pedido.
     * Primero verifica si ya existe un pedido con el mismo id (para evitar duplicados).
     * Si no existe, lo agrega a la lista y devuelve true indicando que se insertó correctamente.
     * Si ya existe, no lo agrega y devuelve false.
     * @param pedido
     * @return true si se agrega un pedido correctamente
     */

    @Override
    public boolean insertar(Pedido pedido) {
        if (buscar(pedido.getId()) == null) {
            GesData.listarPedido.add(pedido);
            return true;
        }
        return false;
    }

    /**
     * Este método busca un pedido existente por su id.
     * Si lo encuentra, lo reemplaza por el objeto actualizado en la lista.
     * Luego, guarda toda la lista en el archivo .dat para que los cambios no se pierdan.
     * Devuelve true si se modificó correctamente, o false si no se encontró el bocadillo.
     * @param pedido
     * @return true si el pedido se actualizo correctamente
     */
    @Override
    public boolean modificar(Pedido pedido) {
        Pedido existente = buscar(pedido.getId());
        if (existente != null) {
            existente.setIdUsuario(pedido.getIdUsuario());
            existente.setIdBocadillo(pedido.getIdBocadillo());
            existente.setFecha(pedido.getFecha());
            existente.setEstado(pedido.getEstado());
            volcarListas();

            return true;
        }
        return false;
    }

    /**
     * Este método guarda (vuelca) todos los pedidos actuales en el archivo Pedido.dat.
     * Recorre la lista de pedidos y los escribe uno por uno como objetos serializados.
     * Se utiliza para mantener los cambios guardados de forma permanente.
     * Si ocurre un error al escribir, lanza una excepción.
     */

    public static void volcarListas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/persistencia/Pedido.dat"))) {
            for (Pedido p : GesData.listarPedido) {
                oos.writeObject(p);
            }
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método lee (carga) todos los pedidos desde el archivo Pedido.dat.
     * Si el archivo no existe o está vacío, simplemente devuelve una lista vacía.
     * Si el archivo existe, recorre todos los objetos guardados (pedidos) y los carga en memoria.
     * Luego, actualiza la lista estática de GesData con esos pedidos cargados.
     * Devuelve la lista completa de pedidos disponibles en el sistema.
     */

    @Override
    public ArrayList<Pedido> obtenerTodos() {
        GesData.listarPedido.clear();  // Limpia la lista estática en GesData
        ArrayList<Pedido> lista = new ArrayList<>();
        File archivo = new File("src/persistencia/Pedido.dat");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("Archivo no existe o está vacío. Lista vacía.");
            return GesData.listarPedido; // Devuelve la lista estática vacía
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Pedido p = (Pedido) ois.readObject();
                    lista.add(p);
                } catch (EOFException eof) {
                    break; // Fin de archivo
                }
            }

            GesData.listarPedido.clear();
            GesData.listarPedido.addAll(lista);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return GesData.listarPedido;  // Devuelve la lista estática de GesData
    }

    /**
     * Este método busca un pedido por su id.
     * Primero, actualiza la lista de pedidos desde el archivo Pedido.dat para asegurarse de que esté al día.
     * Luego, recorre la lista y compara cada id pedido con el que se busca.
     * Si lo encuentra, lo devuelve. Si no lo encuentra, devuelve null.
     * @param id
     * @return true si encuentra el id del pedido
     */

    @Override
    public Pedido buscar(Integer id) {
        obtenerTodos();
        for (Pedido p : GesData.listarPedido) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    /**
     * Este método elimina un pedido de la lista si se encuentra registrado su ID.
     * Primero busca el bocadillo usando el método buscar().
     * Si lo encuentra, lo elimina de la lista y guarda los cambios en el archivo .dat usando volcarListas().
     * Devuelve true si el pedido fue eliminado correctamente, o false si no se encontró.
     * @param id
     * @return true si se elimina correctamente el pedido
     */
    @Override
    public boolean eliminar(Integer id) {
        Pedido p = buscar(id);
        if (p != null) {
            GesData.listarPedido.remove(id);
            volcarListas();  // Guarda los cambios en archivo
            return true;
        }
        return false;
    }


    /**
     * Este metodo permite obtener todos los pedidos realizados por el Alumno
     * Y cambiar su estado a Entregado, esto solo lo puede hacer un usuario de tipo
     * Cocinero, tambien puede cancelar el proceso.
     * Si se entrega un pedido se elimina de la lista y del archivo .dat
     */
    public void verPedidos() {
        ServiciosPedido serviciosPedido = new ServiciosPedido();
        // Recargar lista desde archivo para evitar duplicados y asegurar datos actualizados

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("--- LISTA DE PEDIDOS ---");
            for (int i = 0; i < GesData.listarPedido.size(); i++) {
                Pedido p = GesData.listarPedido.get(i);
                Bocadillo b = buscarBocadilloPorId(p.getIdBocadillo());
                Alumno alumno = (Alumno) buscarUsuarioPorId(p.getIdUsuario());

                if (b != null && alumno != null) {
                    System.out.printf("%d. Bocadillo de %s - %.2f€ [Alumno: %s %s - %s]%n",
                            i + 1,
                            b.getNombre(),
                            b.getPrecio(),
                            alumno.getNombre(),
                            alumno.getApellidos(),
                            p.getEstado()
                    );
                }
            }

            System.out.print("\nSelecciona el pedido a entregar o 0 para salir: ");
            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
                continue;
            }

            if (opcion == 0) {
                System.out.println("Saliendo del menú de pedidos...");
                return;  // Sale del método verPedidos y vuelve al que lo llamó
            }

            if (opcion > 0 && opcion <= GesData.listarPedido.size()) {
                GesData.listarPedido.remove(opcion - 1);
                ServiciosPedido.volcarListas();
                System.out.println("\nPedido entregado\n");
                break;  // Salir del while pero seguir en el método (o usar return si quieres salir)
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
        }


    /**
     * Este metodo permite buscar bocadillos mediante su id,
     * aqui se realiza un inner join similar a lo que ocurre en las bases de datos
     * @param id
     * @return true si encuentra el id en la lista
     */
    private Bocadillo buscarBocadilloPorId(int id) {
        for (Bocadillo b : GesData.listaBocadillo) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    /**
     * Este metodo permite buscar por usuario mediante su id,
     * aqui se realiza un inner join similar a lo que ocurre en las bases de datos
     * @param id
     * @return true si encuentra el de usuario en la lista
     */
    private Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : GesData.listaUsuarios) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    /**
     * Este metodo permite obtener un bocadillo caliente y frio solo dos
     * de Lunes a Viernes, por lo que una fecha en el calendario fuera
     * de ese rango no permitira mostrar tales bocadillos
     * @param pedido
     * @param calendario
     * @return true si hay una fecha de inicio correcta para obtener el bocadillo
     */

    public Bocadillo[] obtenerBocadillosParaPedido(Pedido pedido, Calendario calendario) {
        if (pedido == null || calendario == null) return null;

        LocalDate fechaPedido = pedido.getFecha();
        LocalDate inicio = calendario.getFechaInicio();
        LocalDate fin = calendario.getFechaFin();

        // Validar si la fecha del pedido está dentro del calendario
        if (fechaPedido.isBefore(inicio) || fechaPedido.isAfter(fin)) {
            return null; // fuera de rango
        }

        Period periodo = Period.between(inicio, fechaPedido);
        int diasDesdeInicio = periodo.getDays() + periodo.getMonths() * 30 + periodo.getYears() * 365;

        // Validar índice dentro del rango de la lista
        if (diasDesdeInicio < 0 ||
                diasDesdeInicio >= calendario.getQuincenaFrio().size() ||
                diasDesdeInicio >= calendario.getQuincenaCaliente().size()) {
            return null;
        }

        // Obtener bocadillos frío y caliente para el día
        Bocadillo frio = calendario.getQuincenaFrio().get(diasDesdeInicio);
        Bocadillo caliente = calendario.getQuincenaCaliente().get(diasDesdeInicio);

        return new Bocadillo[]{frio, caliente};
    }


    /**
     * Este metodo permite buscar bocadillos mediante su nombre,
     * Fue un metodo que no dio un buen resultado porque se necesitaba como id
     * para que funcionara
     */
    private Bocadillo buscarBocadilloPorNombre(String nombre) {

        if (nombre == null) return null;
        for (Bocadillo b : GesData.listaBocadillo) {
            if (b.getNombre().equalsIgnoreCase(nombre)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Este metodo trabaja en conjunto con insertar, incluye algunas validaciones
     * las fechas de inicio y fin no pueden estar vacias, la hora debe estar en un rango
     * adecuado de 8 a 10 am, que no haya seleccionado el mismo bocadillo dos veces, una vez
     * se comprueben todas las validaciones de ser correcto, se guardara en el archivo .dat
     * @param pedido
     * @param calendario
     * @param bocadilloSeleccionado
     * @param horaPedido
     * @return true si cumple las validaciones, se agrega correctamente el pedido al .dat
     */
    public boolean crearPedidoConTurnos(
            Pedido pedido,
            Calendario calendario,
            Bocadillo bocadilloSeleccionado,
            LocalTime horaPedido
    ) {
        // Verificar si GesData.calendario tiene un calendario para la fecha del pedido
        if (calendario == null) {
            calendario = obtenerCalendarioParaFecha(pedido.getFecha());
            if (calendario == null) {
                System.out.println("No hay calendario activo para la fecha seleccionada.");
                return false;
            }
        }

        // Validar que la hora esté dentro del turno de 08:00 a 10:30
        boolean turnoManana = !horaPedido.isBefore(LocalTime.of(8, 0)) && !horaPedido.isAfter(LocalTime.of(10, 30));

        if (!turnoManana) {
            System.out.println("Solo puedes pedir entre 08:00 y 10:30.");
            return false; // Retorna false si la hora no está dentro del rango permitido
        }

        // Obtener los bocadillos del día según el calendario
        Bocadillo[] bocadillosDelDia = obtenerBocadillosParaPedido(pedido, calendario);
        if (bocadillosDelDia == null) {
            System.out.println("No hay bocadillos para esta fecha.");
            return false;
        }

        // Validar que el usuario no haya pedido el mismo bocadillo en este turno
        for (Pedido p : GesData.listarPedido) {
            if (p.getIdUsuario().equals(pedido.getIdUsuario()) && p.getFecha().equals(pedido.getFecha())) {
                LocalTime horaP = p.getHoraPedido();
                boolean pTurnoManana = horaP != null &&
                        !horaP.isBefore(LocalTime.of(8, 0)) && !horaP.isAfter(LocalTime.of(10, 30));

                // Si el pedido es en el mismo turno y el bocadillo es el mismo, denegar el segundo pedido
                if (turnoManana && pTurnoManana) {
                    if (p.getIdBocadillo() == bocadilloSeleccionado.getId()) {
                        System.out.println("Ya has hecho un pedido para este turno de la mañana con el mismo bocadillo. Por favor, elige uno diferente.");
                        return false;
                    }
                }
            }
        }

        // Establecer el bocadillo seleccionado y la hora del pedido
        pedido.setIdBocadillo(bocadilloSeleccionado.getId());
        pedido.setHoraPedido(horaPedido);

        // Insertar el pedido
        return insertar(pedido);
    }


    /**
     * Permite obtener las fechas desde el calendario en GesData
     * Como las fechas no son estaticas sino dinamicas, se hace la
     * comprobacion de que siempre se realice un pedido de Lunes a Viernes.
     * @param fecha
     * @return las fechas de inicio y fin disponibles
     */
    public Calendario obtenerCalendarioParaFecha(LocalDate fecha) {
        for (int i = 0; i < GesData.calendario.length; i++) {
            Calendario c = GesData.calendario[i];
            if (c == null) continue; // por si acaso
            if ((fecha.isEqual(c.getFechaInicio()) || fecha.isAfter(c.getFechaInicio())) &&
                    (fecha.isEqual(c.getFechaFin()) || fecha.isBefore(c.getFechaFin()))) {
                return c;
            }
        }
        return null;
    }

    /**
     * Este metodo corresponde a un usuario Administrador para
     * ver un informe o reporte de los bocadillos que mas se solicitan en el Pedido.
     * Se obtiene la lista de pedidos, y se actualiza obteniendo los ultimos registros del fichero .dat
     * se cuentan y muestran en pantalla
     */
    public void bocadilloMasPedido() {
        // Mapa para almacenar la cantidad de veces que se ha pedido cada bocadillo
        Map<String, Integer> contadorBocadillos = new HashMap<>();

        // Recorremos todos los pedidos
        for (Pedido pedido : GesData.listarPedido) {
            // Obtener el bocadillo correspondiente al pedido
            Bocadillo bocadillo = buscarBocadilloPorId(pedido.getIdBocadillo());

            if (bocadillo != null) {
                // Contamos las veces que se ha pedido cada bocadillo
                contadorBocadillos.put(bocadillo.getNombre(),
                        contadorBocadillos.getOrDefault(bocadillo.getNombre(), 0) + 1);
            }
        }

        // Buscar el bocadillo con más pedidos
        String bocadilloMasPedido = null;
        int maxPedidos = 0;

        for (Map.Entry<String, Integer> entry : contadorBocadillos.entrySet()) {
            if (entry.getValue() > maxPedidos) {
                bocadilloMasPedido = entry.getKey();
                maxPedidos = entry.getValue();
            }
        }

        // Mostrar el informe del bocadillo más pedido
        if (bocadilloMasPedido != null) {
            System.out.println("El bocadillo más pedido es: " + bocadilloMasPedido);
            System.out.println("Cantidad de pedidos: " + maxPedidos);
        } else {
            System.out.println("No se han realizado pedidos.");
        }
    }

    /**
     * Este metodo carga los bacadillos para el usuario Alumno,
     * siempre y cuando la fecha o rango sea de Lunes a Viernes.
     */

    public void listarBocadillosDelDia() {
        // Inicializar calendario con rotación
        Calendario calendario = GesData.inicializarCalendarioConRotacion();

        // Obtener la fecha de hoy
        LocalDate fechaHoy = LocalDate.now();

        // Verificar que la fecha de hoy esté dentro del rango del calendario (lunes a viernes)
        if (fechaHoy.isBefore(calendario.getFechaInicio()) || fechaHoy.isAfter(calendario.getFechaFin())) {
            System.out.println("Hoy no hay bocadillos disponibles. El calendario es solo para lunes a viernes.");
            return;
        }

        // Obtener el día de la semana para mostrar el bocadillo correspondiente
        DayOfWeek diaSemana = fechaHoy.getDayOfWeek();
        int indice = diaSemana.getValue() - 1;  // Lunes = 0, Martes = 1, ..., Domingo = 6

        // Obtener los bocadillos del día
        Bocadillo bocadilloFrio = calendario.getQuincenaFrio().get(indice);
        Bocadillo bocadilloCaliente = calendario.getQuincenaCaliente().get(indice);

        // Mostrar los bocadillos disponibles
        System.out.println("Bocadillos disponibles para hoy (" + fechaHoy + "):");
        System.out.println("Bocadillo frío: " + bocadilloFrio.getNombre());
        System.out.println("Bocadillo caliente: " + bocadilloCaliente.getNombre());
    }


    /**
     * Muestra todos los pedidos del usuario que esta logueado
     * Esta conectado como una llave foranea con Bocadillo
     * @param idUsuario
     */

    public void verHistorialPedidos(String idUsuario) {
        if (GesData.listarPedido.isEmpty()) {
            System.out.println("No hay pedidos realizados aún.");
            return;
        }

        boolean pedidosEncontrados = false;

        for (Pedido pedido : GesData.listarPedido) {
            if (pedido.getIdUsuario().equals(idUsuario)) {
                System.out.println("ID Pedido: " + pedido.getId());
                System.out.println("Fecha de Pedido: " + pedido.getFecha());
                System.out.println("Hora de Pedido: " + pedido.getHoraPedido());
                System.out.println("Bocadillo: " + obtenerNombreBocadillo(pedido.getIdBocadillo()));
                System.out.println("Estado: " + pedido.getEstado());
                System.out.println("--------------------------------------");

                pedidosEncontrados = true;
            }
        }

        if (!pedidosEncontrados) {
            System.out.println("No se encontraron pedidos para el usuario con ID: " + idUsuario);
        }
    }


    /**
     * Permite obtener el nombre del bocadillo haciendo un filtro por ID
     * @param idBocadillo
     * @return true si encuentra el id del bocadillo
     */
    public String obtenerNombreBocadillo(int idBocadillo) {
        for (Bocadillo bocadillo : GesData.listaBocadillo) {
            if (bocadillo.getId() == idBocadillo) {
                return bocadillo.getNombre();
            }
        }
        return "Bocadillo no encontrado";  // Si el bocadillo no se encuentra
    }

    /**
     * Un usuario Alumno podra cancelar un pedido siempre y cuando se cumpla
     * que el estado del pedido tenga el valor Es Espera
     * @param idUsuario
     * @return true si encuentra un historial o lista de pedidas y se puede cancelar por el estado En Espera
     */
    public void cancelarPedido(String idUsuario) {
        // Ver historial de pedidos para el usuario
        boolean pedidosEncontrados = false;

        for (Pedido pedido : GesData.listarPedido) {
            if (pedido.getIdUsuario().equals(idUsuario)) {
                System.out.println("ID Pedido: " + pedido.getId());
                System.out.println("Fecha de Pedido: " + pedido.getFecha());
                System.out.println("Hora de Pedido: " + pedido.getHoraPedido());
                System.out.println("Bocadillo: " + obtenerNombreBocadillo(pedido.getIdBocadillo()));
                System.out.println("Estado: " + pedido.getEstado());
                System.out.println("--------------------------------------");

                pedidosEncontrados = true;
            }
        }

        if (!pedidosEncontrados) {
            System.out.println("No se encontraron pedidos para el usuario con ID: " + idUsuario);
            return;
        }

        // Pedir al usuario que ingrese el ID del pedido a cancelar
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del pedido que desea cancelar: ");
        int idPedidoSeleccionado = Integer.parseInt(sc.nextLine().trim());

        // Buscar el pedido seleccionado
        for (Pedido pedido : GesData.listarPedido) {
            if (pedido.getIdUsuario().equals(idUsuario) && pedido.getId() == idPedidoSeleccionado) {
                // Verificar el estado del pedido
                if (pedido.getEstado().equals("En Espera")) {
                    // Si el estado es "En Espera", proceder a cancelarlo
                    GesData.listarPedido.remove(pedido);
                    volcarListas();
                    System.out.println("El pedido con ID " + idPedidoSeleccionado + " fue cancelado con éxito.");
                    return;
                } else {
                    System.out.println("No se puede cancelar el pedido con ID " + idPedidoSeleccionado + ". El estado actual es: " + pedido.getEstado());
                    return;
                }
            }
        }

        // Si no se encuentra el pedido con el ID proporcionado
        System.out.println("No se encontró el pedido con el ID " + idPedidoSeleccionado + " para el usuario con ID " + idUsuario);
    }


}


