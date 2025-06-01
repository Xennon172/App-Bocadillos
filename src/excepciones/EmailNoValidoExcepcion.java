package excepciones;


/**
 * Excepción que se muestra cuando el email proporcionado no es válido.
 * El mensaje no se maneja desde esta clase sino desde Validaciones
 * El correo o email debe tener @ y un formato para cumplir correctamente.
 */
public class EmailNoValidoExcepcion extends Exception{

    /**
     * Constructor que recibe un mensaje de error y lo pasa al constructor de la clase
     */
    public EmailNoValidoExcepcion(String message){
        super(message);
    }
}

