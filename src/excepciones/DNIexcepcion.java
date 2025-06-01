package excepciones;

/**
 * Excepción que se muestra cuando el DNI proporcionado no es válido.
 * El mensaje no se maneja desde esta clase sino desde Validaciones
 * El DNI debe tener un formato para cumplir correctamente, una letra y una cantidad exacta de numeros.
 */

public class DNIexcepcion extends Exception{
    /**
     * Constructor que recibe un mensaje de error y lo pasa al constructor de la clase
     */
    public DNIexcepcion(String message) {
        super(message);
    }

}

