package utiles;

import data.GesData;
import excepciones.DNIexcepcion;
import excepciones.EmailNoValidoExcepcion;
import modelos.Bocadillo;
import modelos.Pedido;
import modelos.Usuario;

import java.util.Random;
import java.util.Scanner;

public class Validaciones {

    private static int contadorId = 1000;
    public Validaciones() {

    }

    /**
     * Válida el formato del email que introduce el usuario.
     *
     * @param email email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    public boolean validarEmail(String email) {
        int arroba = email.indexOf('@');

        // si devuelve -1, no encuentra el caracter
        if (arroba == -1 || arroba == 0) {
            System.out.println("Email no válido");
            return false;
        }

        for (int i = 0; i < arroba; i++) {
            if (email.charAt(i) == ' ') {
                return false;
            }
        }

        // Comprobamos que el punto este después de la arroba
        int punto = email.indexOf('.', arroba);
        if (punto == -1) {
            System.out.println("Email no válido");
            return false;
        }

        // Comprobamos que el punto no vaya tras la arroba
        if (arroba+1 == punto) {
            return false;
        }

        // Comprobamos que tras la arroba hayan caracteres alfanumericos
        for (int i = arroba + 1; i < punto; i++) {
            if (!esLetra(email.charAt(i)) && !esNumero(email.charAt(i))) {
                return false;
            }
        }

        int tamano = email.length();
        // comprobamos que el email termine en letra
        if (!esLetra(email.charAt(tamano - 1))) {
            System.out.println("Email no válido");
            return false;
        }
        return true;
    }

    public void validarEmailConExcepcion(String email) throws EmailNoValidoExcepcion {
        if (!validarEmail(email)) {
            throw new EmailNoValidoExcepcion("Email inválido. Por favor, introduce un email con formato correcto.");
        }
    }

    /**
     * Comprueba si una contraseña cumple con los requisitos.
     *
     * @param contrasena contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    public boolean validarContrasena(String contrasena) {
        if (contrasena.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;

        for (int i = 0; i < contrasena.length(); i++) {
            char c = contrasena.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                tieneMayuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                tieneMinuscula = true;
            } else if (esNumero(c)) {
                tieneNumero = true;
            } else {
                tieneEspecial = true;
            }
            if (tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si el nombre cumple con los requisitos como longitud, acentos y espacios.
     *
     * @param nombre nombre a validar.
     * @return true si el nombre es válido, false en caso contrario.
     */
    public boolean validarNombre(String nombre) {
        nombre = nombre.toLowerCase();

        for (int i = 0; i < nombre.length(); i++) {
            char letra = nombre.charAt(i);

            // Si algún booleano es false
            if (!esLetra(letra) && !esAcento(letra) && letra != ' ') {
                System.out.println("Nombre no válido, debe contener solo letras");
                return false;
            }
        }
        return true;
    }


    // Valida que día y mes solo contengan 2 dígitos y sean numéricos
    public boolean validar_dia_mes(String mes_str) {
        if (mes_str.length() > 2) {
            System.out.println("Valor INCORRECTO, el límite son dos dígitos.");
            return false;
        }
        for (int i = 0; i < mes_str.length(); i++) {
            char letra = mes_str.charAt(i);
            if (!esNumero(letra)) {
                System.out.println("Valor INCORRECTO,  inserta solo números.");
                return false;
            }
        }
        return true;
    }

    public boolean validarAno(String anoStr) {
        if (anoStr.length() != 4) {
            System.out.println("Valor INCORRECTO, introduce 4 digitos.");
            return false;
        }
        for (int i = 0; i < anoStr.length(); i++) {
                char letra = anoStr.charAt(i);
                if (!esNumero(letra)) {
                    System.out.println("Valor INCORRECTO,  inserta solo números.");
                    return false;
                }
        }
        return true;
    }

    /**
     * Solicita y comprueba la fecha de nacimiento del usuario.
     *
     * @return true si la fecha es válida, false en caso contrario.
     */
    public Boolean validarFecha(String diaStr, String mesStr, String anoStr) {

        int dia = Integer.parseInt(diaStr);
        int mes = Integer.parseInt(mesStr);
        int ano = Integer.parseInt(anoStr);

        // Validamos mes
        int diasMes;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                diasMes = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                diasMes = 30;
                break;
            case 2:
                if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
                    diasMes = 29;
                } else {
                    diasMes = 28;
                }
                break;
            default:
                System.out.println("La fecha NO es válida.");
                return false;
        }

        if (dia < 1 || dia > diasMes) {
            System.out.println("La fecha NO es válida.");
            return false;
        }

        String fecha_registrada = diaStr + "/" + mesStr + "/" + anoStr;
        System.out.println("Fecha " + fecha_registrada + " validada.");
        return true;
    }

    /**
     * Valida si un DNI o NIE es válido.
     *
     * @param dni y nie ingresado.
     * @return true si el DNI es válido, false en caso contrario.
     */
    public boolean validar_dni(String dni) {

        if (dni.length() != 9) {
            return false;
        }

        // Si es dni, empieza por numero
        if (esNumero(dni.charAt(0))) {
            return comprobarDni(dni);
            // Si es nie, empieza por letra
        } else if (esLetra(dni.charAt(0))) {
            return comprobarNie(dni);
        }
        return false;
    }

    public void validarDNI(String dni) throws DNIexcepcion {
        if (dni == null || dni.length() != 9) {
            throw new DNIexcepcion("El DNI/NIE debe tener 9 caracteres.");
        }

        if (esNumero(dni.charAt(0))) {
            if (!comprobarDni(dni)) {
                throw new DNIexcepcion("DNI inválido.");
            }
        } else if (esLetra(dni.charAt(0))) {
            if (!comprobarNie(dni)) {
                throw new DNIexcepcion("NIE inválido.");
            }
        } else {
            throw new DNIexcepcion("El DNI/NIE debe comenzar con un número o letra válido.");
        }
    }


    /**
     * Comprueba si un DNI tiene formato válido.
     *
     * @param dni a comprobar.
     * @return true si el formato es válido, false en caso contrario.
     */
    private boolean comprobarDni(String dni) {
        for (int i = 0; i < 8; i++) {
            if (!esNumero(dni.charAt(i))) {
                return false;
            }
        }
        return esLetra(dni.charAt(8));
    }

    /**
     * Comprueba si un NIE tiene formato válido.
     *
     * @param nie a comprobar.
     * @return true si el formato es válido, false en caso contrario.
     */
    private boolean comprobarNie(String nie) {
        for (int i = 1; i <= 7; i++) {
            if (!esNumero(nie.charAt(i))) {
                return false;
            }
        }
        return esLetra(nie.charAt(8));
    }

    /**
     * Verifica si un carácter es un número.
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es un número, false en caso contrario.
     */
    private boolean esNumero(char letra) {
        return letra >= '0' && letra <= '9';
    }

    /**
     * Cromprueba que el valor sea una letra
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es una letra, false en caso contrario.
     */
    public boolean esLetra(char letra) {
        return (letra >= 'A' && letra <= 'Z') || (letra >= 'a' && letra <= 'z');
    }

    /**
     * Comprueba que el valor sea una letra acentuada
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es una letra con acento, false en caso contrario.
     */
    private boolean esAcento(char letra) {
        return (letra == 'á' || letra == 'é' || letra == 'í' || letra == 'ó' || letra == 'ú' || letra == 'ç' || letra == 'ñ');
    }


    /**
     * Verifica si las dos contraseñas proporcionadas coinciden y si la primera contraseña es valida
     * Verifica que el formato sea correcto con el primero
     * @param contrasenaRegistrada1
     * @param contrasenaRegistrada2
     * @return true si las contraseñas coinciden
     * @return false si son diferentes
     */

    public boolean verificarContrasenas(String contrasenaRegistrada1, String contrasenaRegistrada2) {
        if (!contrasenaRegistrada1.equals(contrasenaRegistrada2)) {
            System.out.println("Las contraseñas no coinciden. Intenta de nuevo.");
            return false;
        }

        if (!validarContrasena(contrasenaRegistrada1)) {
            System.out.println("Contraseña no válida.");
            return false;
        }
        return true;
    }



    /**
     * Verifica el tipo de delimitador o flotante para evitar errores o romper el flujo del programa
     * @param numeroStr
     * @return true si coloca el decimal correcto
     * @return false si coloca un caracter extraño
     */
    public boolean validarDecimalConComa(String numeroStr) {
        if (numeroStr.isEmpty()) {
            System.out.println("Error: campo vacío.");
            return false;
        }

        int contadorComas = 0;
        for (int i = 0; i < numeroStr.length(); i++) {
            char c = numeroStr.charAt(i);

            if (Character.isDigit(c)) {
                continue;
            } else if (c == ',') {
                contadorComas++;
                if (contadorComas > 1) {
                    System.out.println("Valor INCORRECTO, solo se permite una coma como separador decimal.");
                    return false;
                }
            } else {
                System.out.println("Valor INCORRECTO, solo se permiten números y una coma.");
                return false;
            }
        }
        return true;
    }

    /**
     * Muestra un mensaje en caso de obtener un flotante difernte a una coma
     * @param sc
     * @param mensaje
     * @return true si el flotante es una coma
     * @return false si el flotante es diferente de una coma
     */
    public double leerPrecioConValidacion(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();

            if (validarDecimalConComa(entrada)) {
                entrada = entrada.replace(',', '.');
                try {
                    return Double.parseDouble(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el número, intente de nuevo.");
                }
            }
            // Si no válido, vuelve a pedir
        }
    }


    /**
     * Genera un numero de aleatorio random con formato de tres letras y tres numeros
     * @return un codigo o numero de expediente con formato
     */
    public static String generarNumeroExpediente() {
        Random random = new Random();
        StringBuilder expediente = new StringBuilder();

        // Generar 3 letras mayúsculas aleatorias
        for (int i = 0; i < 3; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            expediente.append(letra);
        }

        // Generar 3 números aleatorios
        for (int i = 0; i < 3; i++) {
            int numero = random.nextInt(10);
            expediente.append(numero);
        }

        return expediente.toString();
    }


    /**
     * Genera un id automatica y comprueba el ultimo registrado en el fichero Pedido.dat
     * @return un id autogenerado
     */
    public static int generarNuevoIdPedido() {
        int maxId = 0;
        for (Pedido p : GesData.listarPedido) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Genera un id automatica y comprueba el ultimo registrado en el fichero Usuario.dat
     * @return un id autogenerado
     */
    public static String generarNuevoIdUsuario() {
        int maxId = 0;
        for (Usuario u : GesData.listaUsuarios) {
            try {
                int idNum = Integer.parseInt(u.getId());
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (NumberFormatException e) {
           System.out.println(e.getMessage());
            }
        }
        // Retornamos el siguiente número convertido a String
        return String.valueOf(maxId + 1);
    }


    /**
     * Genera un id automatica y comprueba el ultimo registrado en el fichero Bocadillo.dat
     * @return un id autogenerado
     */
    public static int generarNuevoIdBocadillo() {
        int maxId = 0;
        for (Bocadillo b : GesData.listaBocadillo) {
            if (b.getId() > maxId) {
                maxId = b.getId();
            }
        }
        return maxId + 1;
    }


}
