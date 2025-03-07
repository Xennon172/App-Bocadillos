import java.time.LocalDate;

public class Main {

    private static Bocadillo[] lista_bocadillos;
    private static Usuario[] listado_usuarios;
    private static Pedido[] listado_pedidos;
    private static Calendario[] listado_quincena;



    public static void listado_pedidos(){

        listado_pedidos =new Pedido[4];

        listado_pedidos[0] = new Pedido(1,"Francisco",3,LocalDate.of(2025, 2, 1),"En Espera");
        listado_pedidos[1] = new Pedido(2,"Julian",2,LocalDate.of(2025,2,1),"En preparacion");
        listado_pedidos[2] = new Pedido(3,"Julian",6,LocalDate.of(2025,2,1),"Listo");
        listado_pedidos[3] = new Pedido(4,"Carmen",8,LocalDate.of(2025,2,1),"Retirado");

    }

    public static void listado_quincena(){

        listado_quincena = new Calendario[5];

        listado_quincena[0] = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});
        listado_quincena[1] = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});
        listado_quincena[2] = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});
        listado_quincena[3] = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});
        listado_quincena[4] = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});


    }

    public static void lista_bocadillos(){

        lista_bocadillos = new Bocadillo[10];

        lista_bocadillos[0] = new Bocadillo(3,"Chivito","Delicioso Bocadillo Mediterraneo", new String[]{"Lomo de Cerdo", "Bacon", "Tomate", "Queso Manchego", "Lechuga", "Huevo", "Mayonesa", "Aceite de Oliva"}, new String[]{"gluten", "lacteos"},"Valencia", "nunguna", 6.5,true);
        lista_bocadillos[1] = new Bocadillo(2,"Bocadillo de Lomo con Queso","Maravilloso Bocadillo de lomo braseado con queso Chedar", new String[]{"lomo","queso chedar","tomate"}, new String[]{"gluten", "lacteos"}, "Albacete", "nunguna", 6, false);
        lista_bocadillos[2] = new Bocadillo(1, "Completo", "Bocadillo de grandes proporciones", new String[]{"huevo", "pechuga", "bacon", "lechuga", "tomate", "mayonesa"}, new String[]{"gluten", "lacteos"}, "La Murada", "nunguna", 5, false);
        lista_bocadillos[3] = new Bocadillo(4,"Tortilla","Delicioso Bocadillo de Tortilla con cebolla",new String[]{"Patata","Cebolla","Huevo"},new String[]{"glutem","lacteos"},"Murcia", "nunguna", 7.5,false);
        lista_bocadillos[4] = new Bocadillo(5,"Calamares","Delicioso Bocadillo de Calamares con Mayonesa", new String[]{"Calamares","Mayonesa"},new String[]{"gluten","lacteos"},"Madrid", "nunguna", 6.5, false);
        lista_bocadillos[5] = new Bocadillo(6,"Atun con tomate","Sabroso bocadillo de Atun de la rambla y tomates frescos", new String[]{"Atun","tomate"},new String[]{"gluten", "lacteos"}, "Benferri", "nunguna", 12.50, false);
        lista_bocadillos[6] = new Bocadillo(7,"Catalana","Bocadillo de Jamon Serrano Iberico", new String[]{"Jamon Serrano","Queso Manchego","Tomate","Aceite"},new String[]{"gluten","lacteos"},"Barcelona", "nunguna", 7.5,true);
        lista_bocadillos[7] = new Bocadillo(8,"Mortadelo","Bocadillo de Mortadela con Olivas, un clasico", new String[]{"mortadela con olivas","Aceite"},new String[]{"gluten"},"Desconocido", "nunguna", 3.5,true);
    }

    //static Bocadillo[] arrayBocadillos ={completo,lomoQueso,chivito,tortilla,calamares, atun,jamon, mortadela};

    static Pedido pedido = new Pedido();

    public static void listar_usuarios() {

        listado_usuarios = new Usuario[5];

        listado_usuarios[0] = new Usuario(1, "Alumno", "Francisco", "1234", "francisco@elcampico.org", "1º DAW",null, LocalDate.of(1985, 8, 15), true, 3);
        listado_usuarios[1] = new Usuario(4, "Alumno", "Carmelo", "1234", "francisco@elcampico.org", "1º DAW",null, LocalDate.of(1985, 8, 15), true, 3);
        listado_usuarios[2] = new Usuario(5, "Alumno", "Julian", "1234", "francisco@elcampico.org", "1º DAW",null, LocalDate.of(1985, 8, 15), true, 3);
        listado_usuarios[3] = new Usuario(2, "Cocina", "Maria", "1234", "maria@elcampico.org", "",null, LocalDate.of(1987, 3, 22), true, 2);
        listado_usuarios[4] = new Usuario(3, "Administrador", "Carmen", "1234", "carmen@elcampico.org", "",null, LocalDate.of(1985, 3, 12), false, 1);
    }





    //usuario1.mostrarInfoUsuario();
    Menu menu = new Menu();
    //menu.menu_inicial(arrayUsuarios);
    //menu.menu_login(arrayUsuarios);
    //Registro registro = new Registro();
    //registro.menu();
        //menu.menu_administrador(arrayUsuarios);
    //menu.realizarPedido(arrayUsuarios, pedido, arrayBocadillos);
    //String ingredientes = chivito.mostrarIngredientes();
    //System.out.println(ingredientes);
}




    /*
    public static void main(String[] args) {
        ArrayList<String> alergias = new ArrayList<>();
        alergias.add("huevo");

        Usuario usuario1 = new Usuario (1,"Alumno", "Francisco", "1234","francisco@elcampico.org", "1º DAW", alergias, LocalDate.of(1985, 8, 15), true, 3);
        Usuario cocinero = new Usuario (2,"Cocina","Maria","1234", "maria@elcampico.org","", alergias, LocalDate.of(1987, 3, 22),true,2);
        Usuario administrador = new Usuario (3, "Administrador","Carmen","1234" ,"carmen@elcampico.org","", alergias,LocalDate.of(1985,3,12),false,1);
        Usuario[] arrayUsuarios = {usuario1,cocinero,administrador};




        //usuario1.mostrarInfoUsuario();
        Menu menu = new Menu();
        //menu.menu_inicial(arrayUsuarios);
        //menu.menu_login(arrayUsuarios);
        //Registro registro = new Registro();
        //registro.menu();
        menu.menu_administrador(arrayUsuarios);
        //menu.realizarPedido(arrayUsuarios, pedido, arrayBocadillos);
        //String ingredientes = chivito.mostrarIngredientes();
        //System.out.println(ingredientes);

    }


    //Buenos


}

     */

