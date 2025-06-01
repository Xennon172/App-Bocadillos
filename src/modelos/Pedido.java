package modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Clase que representa o maneja los pedidos.
 * Utilizamos diferentes constructores para no tener que pasar los valores inicialmente sino
 * desde un momento dado cuando se validen todos los campos correctamente.
 * Contiene un identificador que permite la compatibilidad con el archivo .dat, aunque
 * no se guarde en el archivo o utilice directamente.
 * Esta clase se relaciona con usuarios y bocadillos, funciona como una tabla que recibe o tiene llaves
 * foraneas para hacer las relaciones dinamicas
 */
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id, idBocadillo;

    private String idUsuario, estado;

    private LocalDate fecha;
    private LocalTime horaPedido;



    public Pedido() {

    }

    public Pedido(int id, String idUsuario, int idBocadillo, LocalDate fecha, LocalTime horaPedido, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idBocadillo = idBocadillo;
        this.fecha = fecha;
        this.horaPedido = horaPedido;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public LocalTime getHoraPedido() { return horaPedido; }
    public void setHoraPedido(LocalTime horaPedido) { this.horaPedido = horaPedido; }

    public void mostrar_info() {
        System.out.println("=============================");
        System.out.println("ID Pedido: " + id);
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("ID Bocadillo: " + idBocadillo);
        System.out.println("Fecha: "+ fecha);
        System.out.println("Estado: "+ estado);
        System.out.println("=============================");
    }


}
