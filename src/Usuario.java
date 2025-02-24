import java.time.LocalDate;

public class Usuario {

    private int id;

    private String usuario;

    private String nombre;

    private String correo;

    private String curso;

    private  String[] alergia;

    private LocalDate fechaNacimiento;

    private int rol;

    private boolean esAlergico;

    public Usuario(int id, String usuario, String nombre, String correo, String curso, String[] alergia, LocalDate fechaNacimiento, boolean esAlergico, int rol) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.curso = curso;
        this.alergia = alergia;
        this.fechaNacimiento = fechaNacimiento;
        this.esAlergico = esAlergico;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String[] getAlergia() {
        return alergia;
    }

    public void setAlergia(String[] alergia) {
        this.alergia = alergia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public boolean getEsAlergico() {
        return esAlergico;
    }

    public void setEsAlergico(boolean esAlergico) {
        this.esAlergico = esAlergico;
    }

    public void mostrarInfoUsuario () {
        System.out.println("Usuario: " + usuario);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Curso: "+ curso);
        System.out.println("¿Eres Alergico?: " + esAlergico);
        System.out.println("Alergia: "+ alergia);
        System.out.println("Fecha de Nacimimento :" + fechaNacimiento);
        System.out.println("Rol: "+ rol);

    }

}
