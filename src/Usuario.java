import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {

    private int id;

    private String usuario, nombre, apellido, correo, curso, dni, contrasena, respuesta_seguridad;

    private ArrayList<String> alergias;

    private LocalDate fechaNacimiento;

    private int rol;

    private boolean esAlergico;

    public Usuario () {

    }

    public Usuario(int id, String usuario, String nombre, String contrasena, String correo, String curso, ArrayList<String> alergias, LocalDate fechaNacimiento, boolean esAlergico, int rol, String respuesta_seguridad) {
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
        this.respuesta_seguridad = respuesta_seguridad;
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

    public String getRespuesta_seguridad() {
        return respuesta_seguridad;
    }

    public void setRespuesta_seguridad(String respuesta_seguridad) {
        this.respuesta_seguridad = respuesta_seguridad;
    }

    public void mostrar_info() {
        Calendario calendario = new Calendario();

        System.out.println("===============================================");
        System.out.println("Usuario: " + usuario);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Curso: "+ curso);
        System.out.println("Fecha de Nacimimento :" + fechaNacimiento);
        System.out.println("Rol: "+ rol);
        System.out.println("¿Es Alérgico?: " + esAlergico);

        if (esAlergico) {
            System.out.println("Alergias:");
            for(int i = 0; i < alergias.size(); i++) {
                System.out.println(i+ 1 + " " + alergias.get(i));
            }
        }

        System.out.println("===========================================");

        // Solo para testeo
        //System.out.println("SOLO TEST Contraseña: " + contrasena);
        //System.out.println("SOLO TEST Respuesta serguridad: " + respuesta_seguridad);
    }

    public Usuario[] inicializar_usuarios() {
        ArrayList<String> alergias = new ArrayList<>();
        alergias.add("huevo");
        alergias.add("lácteos");

        Usuario alumno1 = new Usuario (3,"alumno1", "Francisco", "1234","francisco@elcampico.org", "1º DAW", alergias, LocalDate.of(1985, 8, 15), true, 3, "azul");
        Usuario alumno2 = new Usuario (4,"alumno2", "José","1234","jose@elcampico.org", "1º DAM", alergias, LocalDate.of(1985, 8, 15), true, 3, "azul");
        Usuario alumno3 = new Usuario (5,"alumno3", "Laura", "1234","laura@elcampico.org", "1º DAM", alergias, LocalDate.of(1985, 8, 15), true, 3, "azul");
        Usuario alumno4 = new Usuario (6,"alumno4", "Marta", "1234","marta@elcampico.org", "2º DAW", alergias, LocalDate.of(1985, 8, 15), true, 3, "azul");
        Usuario alumno5 = new Usuario (7,"alumno5", "Juan", "1234","juan@elcampico.org", "2º DAM", alergias, LocalDate.of(1985, 8, 15), true, 3, "azul");
        Usuario cocinero = new Usuario (2,"cocina","Maria","1234", "maria@elcampico.org","", alergias, LocalDate.of(1987, 3, 22),true,2, "rojo");
        Usuario administrador = new Usuario (1, "administrador","Carmen","1234" ,"carmen@elcampico.org","", alergias,LocalDate.of(1985,3,12),false,1, "verde");

        Usuario[] array_usuarios = {alumno1, alumno2, alumno3, alumno4, alumno5,cocinero,administrador};

        return array_usuarios;
    }


}
