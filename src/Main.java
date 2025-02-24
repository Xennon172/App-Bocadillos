import java.time.LocalDate;

public class Main {

    static Bocadillo chivito = new Bocadillo(3,"Chivito","Delicioso Bocadillo Mediterraneo", new String[]{"Lomo de Cerdo", "Bacon", "Tomate", "Queso Manchego", "Lechuga", "Huevo", "Mayonesa", "Aceite de Oliva"}, new String[]{"gluten", "lacteos"},"Valencia", "nunguna", 6.5,true);
    static Bocadillo lomoQueso = new Bocadillo(2,"Bocadillo de Lomo con Queso","Maravilloso Bocadillo de lomo braseado con queso Chedar", new String[]{"lomo","queso chedar","tomate"}, new String[]{"gluten", "lacteos"}, "Albacete", "nunguna", 6, false);
    static Bocadillo completo = new Bocadillo(1, "Completo", "Bocadillo de grandes proporciones", new String[]{"huevo", "pechuga", "bacon", "lechuga", "tomate", "mayonesa"}, new String[]{"gluten", "lacteos"}, "La Murada", "nunguna", 5, false);
    static Bocadillo tortilla = new Bocadillo(4,"Tortilla","Delicioso Bocadillo de Tortilla con cebolla",new String[]{"Patata","Cebolla","Huevo"},new String[]{"glutem","lacteos"},"Murcia", "nunguna", 7.5,false);
    static Bocadillo calamares = new Bocadillo(5,"Calamares","Delicioso Bocadillo de Calamares con Mayonesa", new String[]{"Calamares","Mayonesa"},new String[]{"gluten","lacteos"},"Madrid", "nunguna", 6.5, false);
    static Bocadillo atun = new Bocadillo(6,"Atun con tomate","Sabroso bocadillo de Atun de la rambla y tomates frescos", new String[]{"Atun","tomate"},new String[]{"gluten", "lacteos"}, "Benferri", "nunguna", 12.50, false);
    static Bocadillo jamon = new Bocadillo(7,"Catalana","Bocadillo de Jamon Serrano Iberico", new String[]{"Jamon Serrano","Queso Manchego","Tomate","Aceite"},new String[]{"gluten","lacteos"},"Barcelona", "nunguna", 7.5,true);
    static Bocadillo mortadela = new Bocadillo(8,"Mortadelo","Bocadillo de Mortadela con Olivas, un clasico", new String[]{"mortadela con olivas","Aceite"},new String[]{"gluten"},"Desconocido", "nunguna", 3.5,true);

    static Bocadillo[] arrayBocadillos ={completo,lomoQueso,chivito,tortilla,calamares, atun,jamon, mortadela};

    static Pedido pedido = new Pedido();

    static Usuario usuario1 = new Usuario (1,"Alumno", "Francisco", "1234","francisco@elcampico.org", "1º DAW", new String[]{"gluten"}, LocalDate.of(1985, 8, 15), true, 1);
    static Usuario cocinero = new Usuario (2,"Cocina","Maria","1234", "maria@elcampico.org","",new String[]{"lacteos"}, LocalDate.of(1987, 3, 22),true,2);
    static Usuario administrador = new Usuario (3, "Administrador","Carmen","1234" ,"carmen@elcampico.org","", new String[]{""},LocalDate.of(1985,3,12),false,3);
    static Usuario[] arrayUsuarios = {usuario1,cocinero,administrador};

    public static void main(String[] args) {
        //usuario1.mostrarInfoUsuario();
        Menu menu = new Menu();

        menu.realizarPedido(arrayUsuarios, pedido, arrayBocadillos);
        //String ingredientes = chivito.mostrarIngredientes();
        //System.out.println(ingredientes);
        //menu.menuInicial(arrayBocadillos);
    }


}
