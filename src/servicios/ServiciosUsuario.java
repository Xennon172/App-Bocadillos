package servicios;

import data.GesData;
import modelos.Administrador;
import modelos.Alumno;
import modelos.Cocina;
import modelos.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

public class ServiciosUsuario implements Crud<Usuario, String> {


    /**
     * Este método intenta insertar un nuevo usuario en la lista de usuarios.
     * Primero verifica si ya existe un usuario con el mismo nombre de usuario (para evitar duplicados).
     * Si no existe, lo agrega a la lista y devuelve true indicando que se insertó correctamente.
     * Si ya existe, no lo agrega y devuelve false.
     * @param usuario
     * @return true si el usuario no tiene un nombre asignado
     */

    @Override
    public boolean insertar(Usuario usuario) {
        if (buscar(usuario.getUsuario()) == null) {
            GesData.listaUsuarios.add(usuario);
            return true;
        }
        return false;
    }

    /**
     * Este método busca un usuario existente por su nombre de usuario.
     * Si lo encuentra, lo reemplaza por el objeto actualizado en la lista.
     * Luego, guarda toda la lista en el archivo .dat para que los cambios no se pierdan.
     * Devuelve true si se modificó correctamente, o false si no se encontró el usuario.
     * @param usuarioActualizado
     * @return true si el usuario existe en la lista y actualiza correctamente sus datos
     */



    @Override
    public boolean modificar(Usuario usuarioActualizado) {
        for (int i = 0; i < GesData.listaUsuarios.size(); i++) {
            if (GesData.listaUsuarios.get(i).getUsuario().equals(usuarioActualizado.getUsuario())) {
                GesData.listaUsuarios.set(i, usuarioActualizado);
                volcarListas(); // <- Guarda los cambios en el archivo
                return true;
            }
        }
        return false;
    }


    /**
     * Este método guarda (vuelca) todos los usuarios actuales en el archivo Usuario.dat.
     * Recorre la lista de usuarios y los escribe uno por uno como objetos serializados.
     * Se utiliza para mantener los cambios guardados de forma permanente.
     * Si ocurre un error al escribir, lanza una excepción.
     */

    public static void volcarListas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/persistencia/Usuario.dat"))) {
            for (Usuario u : GesData.listaUsuarios) {
                oos.writeObject(u);
            }
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método lee (carga) todos los usuarios desde el archivo Usuario.dat.
     *  Si el archivo no existe o está vacío, simplemente devuelve una lista vacía.
     *  Si el archivo existe, recorre todos los objetos guardados (usuarios) y los carga en memoria.
     *  Luego, actualiza la lista estática de GesData con esos usuarios cargados.
     *  Devuelve la lista completa de usuarios disponibles en el sistema.
     */

    @Override
    public ArrayList<Usuario> obtenerTodos() {
        GesData.listaUsuarios.clear();  // Limpia la lista estática en GesData
        ArrayList<Usuario> lista = new ArrayList<>();
        File archivo = new File("src/persistencia/Usuario.dat");

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("Archivo no existe o está vacío. Lista vacía.");
            return GesData.listaUsuarios; // Devuelve la lista estática vacía
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Usuario u = (Usuario) ois.readObject();
                    lista.add(u);
                } catch (EOFException eof) {
                    break; // Fin de archivo
                }
            }

            GesData.listaUsuarios.clear();
            GesData.listaUsuarios.addAll(lista);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return GesData.listaUsuarios;  // Devuelve la lista estática de GesData
    }


    /**
     * Este método busca un usuario por su nombre de usuario.
     * Primero, actualiza la lista de usuarios desde el archivo Usuario.dat para asegurarse de que esté al día.
     * Luego, recorre la lista y compara cada nombre de usuario con el que se busca.
     * Si lo encuentra, lo devuelve. Si no lo encuentra, devuelve null.
     * @param nombre_usuario
     * @return true si el usuario esta en la lista
     */


    @Override
    public Usuario buscar(String nombre_usuario) {
        // Actualizar lista en memoria desde archivo
        obtenerTodos();  // carga GesData.lista_usuarios desde .dat

        for (Usuario usuario : GesData.listaUsuarios) {
            if (usuario.getUsuario().equalsIgnoreCase(nombre_usuario)) {
                return usuario;
            }
        }
        return null;
    }


    /**
     * Este método elimina un usuario de la lista si se encuentra registrado por su nombre de usuario.
     * Primero busca el usuario usando el método buscar().
     * Si lo encuentra, lo elimina de la lista y guarda los cambios en el archivo .dat usando volcarListas().
     * Devuelve true si el usuario fue eliminado correctamente, o false si no se encontró.
     * @param nombre_usuario
     * @return true si el usuario se elimina correctamente y existe en la lista
     */

    @Override
    public boolean eliminar(String nombre_usuario) {
        Usuario usuario = buscar(nombre_usuario);
        if (usuario != null) {
            GesData.listaUsuarios.remove(usuario);
            volcarListas();  // Guarda los cambios en archivo
            return true;
        }
        return false;
    }

}
