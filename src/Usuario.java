import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {

    private int id;

    private String usuario;

    private String nombre;

    private String apellido;

    private String correo;

    private String curso;

    private String dni;

    private String[] alergias;

    private LocalDate fechaNacimiento;

    private String contrasena;

    private int rol;

    private boolean esAlergico;

    public Usuario () {

    }

    public Usuario(int id, String usuario, String nombre, String contrasena, String correo, String curso, ArrayList<String> alergias, LocalDate fechaNacimiento, boolean esAlergico, int rol) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.curso = curso;
        this.alergias = alergias;
        this.fechaNacimiento = fechaNacimiento;
        this.esAlergico = esAlergico;
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void  setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isEsAlergico() {
        return esAlergico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(ArrayList<String> alergias) {
        this.alergias = alergias;
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
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Curso: "+ curso);
        System.out.println("Fecha de Nacimimento :" + fechaNacimiento);
        System.out.println("Rol: "+ rol);
        System.out.println("¿Eres Alergico?: " + esAlergico);
        System.out.println("Alergias:");
        for(int i = 0; i < alergias.size(); i++) {
            System.out.println(i+1 + " " + alergias.get(i));
        }


        // Solo para testeo
        System.out.println("SOLO TEST Contraseña: " + contrasena);

    }



}
