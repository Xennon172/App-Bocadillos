import java.util.Arrays;



public class Bocadillo {

    private int id;

    private String nombre, descripcion, ciudad_popular, curiosidad;

    private String[] ingredientes, alergenos;

    private double precio;

    private boolean esFrio;

    public Bocadillo() {

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

    public String getCiudad_popular() {
        return ciudad_popular;
    }

    public void setCiudad_popular(String ciudad_popular) {
        this.ciudad_popular = ciudad_popular;
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

    public Bocadillo[] inicializar_bocadillos(){

        Bocadillo bocadillo1 = new Bocadillo(3,"Chivito","Delicioso Bocadillo Mediterraneo", new String[]{"Lomo de Cerdo", "Bacon", "Tomate", "Queso Manchego", "Lechuga", "Huevo", "Mayonesa", "Aceite de Oliva"}, new String[]{"gluten", "lacteos"},"Valencia", "nunguna", 6.5,true);
        Bocadillo bocadillo2 = new Bocadillo(2,"Bocadillo de Lomo con Queso","Maravilloso Bocadillo de lomo braseado con queso Chedar", new String[]{"lomo","queso chedar","tomate"}, new String[]{"gluten", "lacteos"}, "Albacete", "nunguna", 6, false);
        Bocadillo bocadillo3 = new Bocadillo(1, "Completo", "Bocadillo de grandes proporciones", new String[]{"huevo", "pechuga", "bacon", "lechuga", "tomate", "mayonesa"}, new String[]{"gluten", "lacteos"}, "La Murada", "nunguna", 5, false);
        Bocadillo bocadillo4 = new Bocadillo(4,"Tortilla","Delicioso Bocadillo de Tortilla con cebolla", new String[]{"Patata","Cebolla","Huevo"}, new String[]{"glutem","lacteos"},"Murcia", "nunguna", 7.5,false);
        Bocadillo bocadillo5 = new Bocadillo(5,"Calamares","Delicioso Bocadillo de Calamares con Mayonesa", new String[]{"Calamares","Mayonesa"}, new String[]{"gluten","lacteos"},"Madrid", "nunguna", 6.5, false);
        Bocadillo bocadillo6 = new Bocadillo(6,"Atun con tomate","Sabroso bocadillo de Atun de la rambla y tomates frescos", new String[]{"Atun","tomate"}, new String[]{"gluten", "lacteos"}, "Benferri", "nunguna", 12.50, false);
        Bocadillo bocadillo7 = new Bocadillo(7,"Catalana","Bocadillo de Jamon Serrano Iberico", new String[]{"Jamon Serrano","Queso Manchego","Tomate","Aceite"}, new String[]{"gluten","lacteos"},"Barcelona", "nunguna", 7.5,true);
        Bocadillo bocadillo8 = new Bocadillo(8,"Mortadelo","Bocadillo de Mortadela con Olivas, un clasico", new String[]{"mortadela con olivas","Aceite"}, new String[]{"gluten"},"Desconocido", "nunguna", 3.5,true);
        Bocadillo bocadillo9 = new Bocadillo(9,"Lomo adobado","Delicioso bocadillo de lomo adobado con queso y tomate en pan de cristal", new String[]{"Lomo adobado", "Queso", "Tomate"}, new String[]{"lacteos"},"Abanilla","Bocadillo preferido en la fiesta de la Santa Cruz",5,false);
        Bocadillo bocadillo10 = new Bocadillo(10,"caballa","Bocadillo de caballa Gallega",new String[]{"Caballa", "Aceite de Oliva"}, new String[]{""},"Lugo","bocadillo muy demandado en el norte de España",4,true);

        Bocadillo[] array_bocadillos = {bocadillo1, bocadillo2, bocadillo3, bocadillo4, bocadillo5, bocadillo6, bocadillo7, bocadillo8,bocadillo9,bocadillo10};

        return array_bocadillos;
    }

}