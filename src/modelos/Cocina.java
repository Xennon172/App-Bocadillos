package modelos;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.LocalDate;



/**
 * Clase que representa a un usuario Cocinero.
 * Hereda o extiende de la clase Usuario e implementa Serializable con el fin
 * de permitir registrar o guardar su objeto en el .dat
 * Utilizamos diferentes constructores para no tener que pasar los valores inicialmente sino
 * desde un momento dado cuando se validen todos los campos correctamente.
 * Los constructores personalizados se adaptan a los distintos metodos para registrar, o cambiar contraseña.
 */

public class Cocina extends Usuario implements Serializable {

    public Cocina(){

    }
    public Cocina(String id, String usuario, String nombre, String apellidos, String email, String password, LocalDate fechaNacimiento, String dni) {
        super(id, usuario, nombre, apellidos, email, password, fechaNacimiento, dni);
    }

    public Cocina(String usuario, String nombre, String email, String password) {
        super(usuario, nombre, email, password);
    }


}

