
public class Main {


    public static void main(String[] args) {

        Bocadillo bocadillo = new Bocadillo();
        Usuario usuario = new Usuario();
        Pedido pedido = new Pedido();
        Calendario calendario = new Calendario();
        Menu menu = new Menu();


        Usuario[] array_usuarios;
        Bocadillo[] array_bocadillos;
        Pedido[] array_pedidos;
        Calendario[] array_calendarios;


        array_usuarios = usuario.inicializar_usuarios();
        array_bocadillos = bocadillo.inicializar_bocadillos();
        array_pedidos = pedido.inicializar_pedidos();
        array_calendarios = calendario.inicializar_calendario();


        // Menu inicial
        menu.menu_inicial(array_usuarios, array_bocadillos, array_pedidos, array_calendarios);

    }

}



