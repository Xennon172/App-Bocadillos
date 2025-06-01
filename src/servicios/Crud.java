package servicios;

import java.util.ArrayList;


/**
 * Interfaz generica o dinamica que define las operaciones básicas de un CRUD.
 * Crear,Leer,Modificar y Eliminar
 * La T significa el tipo de entidad que se va a gestionar siendo una clase Bocadillo o Pedido.
 * El ID significa el tipo de identificador único de la entidad si es int o String
 * @param <T>
 * @param <ID>
 */

public interface Crud<T, ID> {
    boolean insertar(T entidad);
    boolean modificar(T entidad);
    ArrayList<T> obtenerTodos();
    T buscar(ID id);
    boolean eliminar(ID id);
}
