package servicios;

import data.GesData;
import modelos.Bocadillo;

import java.io.*;
import java.util.ArrayList;

public class ServiciosBocadillo implements Crud<Bocadillo, String> {

    /**
     * Este método intenta insertar un nuevo bocadillo  en la lista de bocadillo.
     * Primero verifica si ya existe un bocadillo con el mismo nombre (para evitar duplicados).
     * Si no existe, lo agrega a la lista y devuelve true indicando que se insertó correctamente.
     * Si ya existe, no lo agrega y devuelve false.
     * Adicionalmente, dependiendo de lo que ingrese el usuario lo añade a una lista independiente
     * si es frio o caliente, esto con el fin de controlar la rotacion de bocadillos
     * @param nuevo
     * @return true si el bocadillo es agregado correctamnente
     */

    @Override
    public boolean insertar(Bocadillo nuevo) {
        if (buscar(nuevo.getNombre()) == null) {
            GesData.listaBocadillo.add(nuevo);

            // Añadir también a la lista fría o caliente según corresponda
            if (nuevo.isEsFrio()) {
                GesData.listaBocadilloFrio.add(nuevo);
            } else {
                GesData.listaBocadilloCaliente.add(nuevo);
            }

            return true;
        }
        return false;
    }

    /**
     * Este método busca un bocadillo existente por su nombre.
     * Si lo encuentra, lo reemplaza por el objeto actualizado en la lista.
     * Luego, guarda toda la lista en el archivo .dat para que los cambios no se pierdan.
     * Devuelve true si se modificó correctamente, o false si no se encontró el bocadillo.
     * @param entidadModificada
     * @return true si un bocadillo se actualiza correctamente
     */

    @Override
    public boolean modificar(Bocadillo entidadModificada) {
        // Buscar usando el nombre original (entidadModificada.getNombre())
        Bocadillo existente = buscar(entidadModificada.getNombre());
        if (existente != null) {
            // Actualiza el nombre con el nuevo nombre
            existente.setNombre(entidadModificada.getNombreNuevo());
            existente.setDescripcion(entidadModificada.getDescripcion());
            existente.setIngredientes(entidadModificada.getIngredientes());
            existente.setAlergenos(entidadModificada.getAlergenos());
            existente.setCiudad_popular(entidadModificada.getCiudad_popular());
            existente.setCuriosidad(entidadModificada.getCuriosidad());
            existente.setPrecio(entidadModificada.getPrecio());
            existente.setEsFrio(entidadModificada.isEsFrio());
            volcarListas();
            return true;
        }
        return false;
    }

    /**
     * Este método guarda (vuelca) todos los bocadillos actuales en el archivo Bocadillo.dat.
     * Recorre la lista de bocadillos y los escribe uno por uno como objetos serializados.
     * Se utiliza para mantener los cambios guardados de forma permanente.
     * Si ocurre un error al escribir, lanza una excepción.
     */


    public static void volcarListas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/persistencia/Bocadillo.dat"))) {
            for (Bocadillo b : GesData.listaBocadillo) {
                oos.writeObject(b);
            }
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método lee (carga) todos los bocadillos desde el archivo Bocadillo.dat.
     * Si el archivo no existe o está vacío, simplemente devuelve una lista vacía.
     * Si el archivo existe, recorre todos los objetos guardados (bocadillos) y los carga en memoria.
     * Luego, actualiza la lista estática de GesData con esos bocadillos cargados.
     * Devuelve la lista completa de bocadillos disponibles en el sistema.
     */

    @Override
    public ArrayList<Bocadillo> obtenerTodos() {
        GesData.listaBocadillo.clear();  // Limpia la lista estática en GesData
        ArrayList<Bocadillo> lista = new ArrayList<>();
        File archivo = new File("src/persistencia/Bocadillo.dat");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("Archivo no existe o está vacío. Lista vacía.");
             GesData.listaBocadillo.clear(); // Devuelve la lista estática vacía
             GesData.listaBocadillo.addAll(lista); // Devuelve la lista estática vacía
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Bocadillo b = (Bocadillo) ois.readObject();
                    lista.add(b);
                } catch (EOFException eof) {
                    break; // Fin de archivo
                }
            }

            GesData.listaBocadillo.clear();
            GesData.listaBocadillo.addAll(lista);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return GesData.listaBocadillo;  // Devuelve la lista estática de GesData
    }


    /**
     * Este método busca un bocadillo por su nombre.
     * Primero, actualiza la lista de bocadillos desde el archivo Bocadillo.dat para asegurarse de que esté al día.
     * Luego, recorre la lista y compara cada nombre de bocadillo con el que se busca.
     * Si lo encuentra, lo devuelve. Si no lo encuentra, devuelve null.
     * @param nombre
     * @return true si encuentra un bocadillo
     */

    @Override
    public Bocadillo buscar(String nombre) {
        obtenerTodos();
        for (Bocadillo b : GesData.listaBocadillo) {
            if (b != null && b.getNombre().equalsIgnoreCase(nombre)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Este método elimina un bocadillo de la lista si se encuentra registrado por su nombre.
     * Primero busca el bocadillo usando el método buscar().
     * Si lo encuentra, lo elimina de la lista y guarda los cambios en el archivo .dat usando volcarListas().
     * Devuelve true si el bocadillo fue eliminado correctamente, o false si no se encontró.
     * @param nombre
     * @return true si elimina correctamente un bocadillo
     */

    @Override
    public boolean eliminar(String nombre) {
        Bocadillo b = buscar(nombre);
        if (b != null) {
            GesData.listaBocadillo.remove(b);
            volcarListas();  // Guarda los cambios en archivo

            return true;
        }
        return false;
    }
}
