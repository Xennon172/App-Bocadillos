import java.time.LocalDate;

public class Pedido {

    private int id, id_bocadillo;

    private String id_usuario, estado;

    private LocalDate fecha;

    public Pedido() {

    }

    public Pedido(int id, String id_usuario, int id_bocadillo, LocalDate fecha, String estado) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_bocadillo = id_bocadillo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_bocadillo() {
        return id_bocadillo;
    }

    public void setId_bocadillo(int id_bocadillo) {
        this.id_bocadillo = id_bocadillo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrar_info() {
        System.out.println("=============================");
        System.out.println("ID Pedido: " + id);
        System.out.println("ID Usuario: " + id_usuario);
        System.out.println("ID Bocadillo: " + id_bocadillo);
        System.out.println("Fecha: "+ fecha);
        System.out.println("Estado: "+ estado);
        System.out.println("=============================");
    }

    public Pedido[] inicializar_pedidos() {

        Pedido pedido1 = new Pedido(1,"3",3,LocalDate.of(2025, 2, 1),"En Espera");
        Pedido pedido2 = new Pedido(2,"4",2,LocalDate.of(2025,2,1),"En preparacion");
        Pedido pedido3 = new Pedido(3,"5",6,LocalDate.of(2025,2,1),"Listo");
        Pedido pedido4 = new Pedido(4,"6",8,LocalDate.of(2025,2,1),"Retirado");

        Pedido[] array_pedidos = {pedido1, pedido2, pedido3, pedido4};

        return array_pedidos;

    }
}
