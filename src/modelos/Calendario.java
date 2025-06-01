package modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * Clase que representa o maneja las fechas de inicio y fin para la rotacion del pedido.
 * Utilizamos diferentes constructores para no tener que pasar los valores inicialmente sino
 * desde un momento dado cuando se validen todos los campos correctamente.
 * Contiene un identificador que permite la compatibilidad con el archivo .dat, aunque
 * no se guarde en el archivo o utilice directamente.
 * Esta clase se relaciona con pedido y bocadillos, funciona como una llave foranea en base de datos
 */

public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private LocalDate fechaInicio, fechaFin;

    private List<Bocadillo> quincenaFrio;
    private List<Bocadillo> quincenaCaliente;

    public Calendario() {
    }

    public Calendario(int id, LocalDate fechaInicio, LocalDate fechaFin,
                      List<Bocadillo> quincenaFrio, List<Bocadillo> quincenaCaliente) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quincenaFrio = quincenaFrio;
        this.quincenaCaliente = quincenaCaliente;
    }

    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public List<Bocadillo> getQuincenaFrio() {
        return quincenaFrio;
    }
    public void setQuincenaFrio(List<Bocadillo> quincenaFrio) {
        this.quincenaFrio = quincenaFrio;
    }
    public List<Bocadillo> getQuincenaCaliente() {
        return quincenaCaliente;
    }
    public void setQuincenaCaliente(List<Bocadillo> quincenaCaliente) {
        this.quincenaCaliente = quincenaCaliente;
    }
}
