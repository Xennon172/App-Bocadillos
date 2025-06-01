package modelos;

import java.io.Serializable;


/**
 * Clase que representa a un usuario Alumno.
 * Hereda o extiende de la clase Usuario e implementa Serializable con el fin
 * de permitir registrar o guardar su objeto en el .dat
 * Utilizamos diferentes constructores para no tener que pasar los valores inicialmente sino
 * desde un momento dado cuando se validen todos los campos correctamente.
 * Contiene un identificador que permite la compatibilidad con el archivo .dat, aunque
 * no se guarde en el archivo o utilice directamente.
 */

public class Bocadillo implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;

    private String nombre, descripcion, ciudad_popular, curiosidad, nombreNuevo;

    private String[] ingredientes, alergenos;

    private double precio;

    private boolean esFrio;

    public Bocadillo() {

    }

    public Bocadillo(String nombre, String descripcion, String[] ingredientes, String[] alergenos, String ciudad_popular, String curiosidad, double precio, boolean esFrio, String nombreNuevo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.alergenos = alergenos;
        this.ciudad_popular = ciudad_popular;
        this.curiosidad = curiosidad;
        this.precio = precio;
        this.esFrio = esFrio;
        this.nombreNuevo = nombreNuevo;
    }

    public Bocadillo(String nombre, String descripcion, String[] ingredientes, String[] alergenos, String ciudad_popular, String curiosidad, double precio, boolean esFrio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.alergenos = alergenos;
        this.ciudad_popular = ciudad_popular;
        this.curiosidad = curiosidad;
        this.precio = precio;
        this.esFrio = esFrio;
    }
    public Bocadillo(int id, String nombre, String descripcion, String[] ingredientes, String[] alergenos, String ciudad_popular, String curiosidad, double precio, boolean esFrio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.alergenos = alergenos;
        this.ciudad_popular = ciudad_popular;
        this.curiosidad = curiosidad;
        this.precio = precio;
        this.esFrio = esFrio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad_popular() {
        return ciudad_popular;
    }

    public void setCiudad_popular(String ciudad_popular) {
        this.ciudad_popular = ciudad_popular;
    }

    public String getCuriosidad() {
        return curiosidad;
    }

    public void setCuriosidad(String curiosidad){
        this.curiosidad = curiosidad;
    }


    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String[] getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String[] alergenos) {
        this.alergenos = alergenos;
    }

    public boolean isEsFrio() {
        return esFrio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getEsFrio() {
        return esFrio;
    }

    public void setEsFrio(boolean esFrio){
        this.esFrio = esFrio;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public void mostrar_info() {
        System.out.println("=============================================");
        System.out.println("Id: "+ id);
        System.out.println("Nombre: "+ nombre);
        System.out.println("Descripcion: "+ descripcion);
        mostrarIngredientes();
        mostrarAlergenos();
        System.out.println("Ciudad Popular: "+ ciudad_popular);
        System.out.println("Curiosidad: "+ curiosidad);
        System.out.println("Precio: "+ precio + " euros");
        System.out.println("Frio: "+ esFrio );
        System.out.println("=============================================");
    }

    public void mostrarIngredientes() {
        if(ingredientes!=null){
            System.out.println("INGREDIENTES: ");
            for(int i=0; i<ingredientes.length;i++){
                System.out.println("  * " + ingredientes[i]);
            }
        }
    }

    public void mostrarAlergenos() {
        if (alergenos != null) {
            System.out.println("Alergenos: ");
            for (int i = 0; i < alergenos.length; i++) {
                System.out.println(" * " + alergenos[i]);
            }
        }
    }




}