import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Calendario {

    private int id;

    private LocalDate fechaInicio, fechaFin;

    private String[] quincenaFrio;

    private String[] quincenaCaliente;

    public Calendario() {

    }

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


    public void mostrar_info() {
        System.out.println("==================================================================");
        System.out.println("Id: "+ id);
        System.out.println("Fecha de Inicio: "+ fechaInicio);
        System.out.println("Fecha de Fin: "+ fechaFin);
        System.out.println("Quincena Bocadillos Frios: "+ Arrays.toString(quincenaFrio));
        System.out.println("Quincena Bocadillos Calientes: "+ Arrays.toString(quincenaCaliente));
        System.out.println("==================================================================");

    }



    public Calendario [] inicializar_calendario(){

        Calendario calendario1 = new Calendario (1,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});
        Calendario calendario2 = new Calendario (2,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Caballa"}, new String[]{"Completo"});
        Calendario calendario3 = new Calendario (3,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Mortadela con olivas"}, new String[]{"Tortilla"});
        Calendario calendario4 = new Calendario (4,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Catalana"}, new String[]{"Lomo con tomate"});
        Calendario calendario5 = new Calendario (5,LocalDate.of(2025,2,1),LocalDate.of(2025,2,6), new String[]{"Atun con tomate"}, new String[]{"Lomo con tomate"});

        Calendario[] array_calendario = {calendario1, calendario2, calendario3, calendario4, calendario5};

        return array_calendario;

    }


}
