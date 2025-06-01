package modelos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa a un usuario Alumno.
 * Hereda o extiende de la clase Usuario e implementa Serializable con el fin
 * de permitir registrar o guardar su objeto en el .dat
 * Utilizamos diferentes constructores para no tener que pasar los valores inicialmente sino
 * desde un momento dado cuando se validen todos los campos correctamente.
 * Los constructores personalizados se adaptan a los distintos metodos para registrar, o cambiar contraseña.
 */

public class Alumno extends Usuario implements Serializable {

    private String[] alergias;
    private String curso;
    private String numExpediente;


    public Alumno(){

    }
    public Alumno(String id, String usuario, String nombre, String apellidos, String email, String password, LocalDate fechaNacimiento, String dni, String[] alergias, String curso, String numExpediente) {
        super(id, usuario, nombre, apellidos, email, password, fechaNacimiento, dni);
        this.alergias = alergias;
        this.curso = curso;
        this.numExpediente = numExpediente;
    }

    public Alumno(String usuario, String nombre, String email, String password, String[] alergias) {
        super(usuario, nombre, email, password);
        this.alergias = alergias;
    }

    public String[] getAlergias() { return alergias; }
    public void setAlergias(String[] alergias) { this.alergias = alergias; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getNumExpediente() { return numExpediente; }
    public void setNumExpediente(String numExpediente) { this.numExpediente = numExpediente; }


}



