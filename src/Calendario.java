import java.time.LocalDate;

public class Calendario {

    private int id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Bocadillo[] quincenaFrio;

    private Bocadillo[] quincenaCaliente;

    public Calendario(int id, LocalDate fechaInicio, LocalDate fechaFin, Bocadillo[] quincenaFrio, Bocadillo[] quincenaCaliente) {
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

    public Bocadillo[] getQuincenaFrio() {
        return quincenaFrio;
    }

    public void setQuincenaFrio(Bocadillo[] quincenaFrio) {
        this.quincenaFrio = quincenaFrio;
    }

    public Bocadillo[] getQuincenaCaliente() {
        return quincenaCaliente;
    }

    public void setQuincenaCaliente(Bocadillo[] quincenaCaliente) {
        this.quincenaCaliente = quincenaCaliente;
    }



}
