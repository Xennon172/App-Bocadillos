package modelos;


/**
 * Clase que representa o maneja la sesion con la informacion del usuario
 * Permite obtener el usuario actual para conocer su clase y denigrar el rol.
 * Iniciar sesion al pasarle los valores correspondientes
 * Cerrar sesion al dejar el objeto como null o vacio.
 */
public class Sesion {
    private static Usuario usuarioActual;

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void iniciarSesion(Usuario u) {
        usuarioActual = u;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}