import java.util.Arrays;



public class Bocadillo {

    private int id;

    private String nombre;

    private String descripcion;

    private String[] ingredientes;

    private String[] alergenos;

    private String ciudadPopular;

    private String curiosidad;

    private double precio;

    private boolean esFrio;


    public Bocadillo(int id, String nombre, String descripcion, String[] ingredientes, String[] alergenos, String ciudadPopular, String curiosidad, double precio, boolean esFrio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.alergenos = alergenos;
        this.ciudadPopular = ciudadPopular;
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

    public String getCiudadPopular() {
        return ciudadPopular;
    }

    public void setCiudadPopular(String ciudadPopular) {
        this.ciudadPopular = ciudadPopular;
    }

    public String getCuriosidad() {
        return curiosidad;
    }

    public String setCuriosidad(){
        return curiosidad;
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

    public void mostrarInfo() {
        System.out.println("Id: "+ id);
        System.out.println("Nombre: "+ nombre);
        System.out.println("Descripcion: "+ descripcion);
        System.out.println("Ingredientes: "+ Arrays.toString(ingredientes));
        System.out.println("Alergenos: "+ Arrays.toString(alergenos));
        System.out.println("Ciudad Popular: "+ ciudadPopular);
        System.out.println("Curiosidad: "+ curiosidad);
        System.out.println("Precio: "+ precio);
        System.out.println("Frio: "+ esFrio);
    }

    public String mostrarIngredientes() {
        return Arrays.toString(this.ingredientes);
    }

    public String mostrarAlergenos(){
        return Arrays.toString(this.alergenos);
    }

}