import java.time.LocalDate;
import java.util.Arrays;

public class Calendario {

    private int id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String[] quincenaFrio;

    private String[] quincenaCaliente;

    public Calendario(int id, LocalDate fechaInicio, LocalDate fechaFin, String[] quincenaFrio, String[] quincenaCaliente) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quincenaFrio = quincenaFrio;
        this.quincenaCaliente = quincenaCaliente;
    }

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

    public String[] getQuincenaFrio() {
        return quincenaFrio;
    }

    public void setQuincenaFrio(String[] quincenaFrio) {
        this.quincenaFrio = quincenaFrio;
    }

    public String[] getQuincenaCaliente() {
        return quincenaCaliente;
    }

    public void setQuincenaCaliente(String[] quincenaCaliente) {
        this.quincenaCaliente = quincenaCaliente;
    }

    public void mostrarInfo() {
        System.out.println("Id: "+ id);
        System.out.println("Fecha de Inicio: "+ fechaInicio);
        System.out.println("Fecha de Fin: "+ fechaFin);
        System.out.println("Quincena Bocadillos Frios: "+ quincenaFrio);
        System.out.println("Quincena Bocadillos Calientes: "+ quincenaCaliente);

    }



}
