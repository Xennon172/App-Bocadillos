import java.time.LocalDate;

public class Pedido {

    private int id;

    private String idUsuario;

    private int idBocadillo;

    private LocalDate fecha;

    private String estado;

    public Pedido() {

    }

    public Pedido(int id, String idUsuario, int idBocadillo, LocalDate fecha, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idBocadillo = idBocadillo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdPedido() {
        return id;
    }

    public void setIdPedido(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdBocadillo() {
        return idBocadillo;
    }

    public void setIdBocadillo(int idBocadillo) {
        this.idBocadillo = idBocadillo;
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

    public void mostrarPedido() {
        System.out.println("ID Pedido: " + id);
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("ID Bocadillo: " + idBocadillo);
        System.out.println("Fecha: "+ fecha);
        System.out.println("Estado: "+ estado);
    }
}
