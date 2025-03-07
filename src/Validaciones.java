import java.util.Scanner;

public class Validaciones {
    public Validaciones() {

    }
    /**
     * Genera un número aleatorio de 4 dígitos como captcha.
     *
     * @return un número entero aleatorio de 4 dígitos.
     */
    public int generar_captcha() {
        return 1000 + (int) (Math.random() * 9000);
    }

    /**
     * Válida si el captcha introducido coincide con el generado.
     *
     * @param captcha_generado el captcha generado.
     * @param captcha_usuario el captcha ingresado por el usuario.
     * @return true si el captcha es válido, false en caso contrario.
     */
    public boolean validar_captcha(int captcha_generado, String captcha_usuario) {
        if (!captcha_usuario.equals(String.valueOf(captcha_generado))) {
            return false;
        }
        return true;
    }

    /**
     * Válida el formato del email que introduce el usuario.
     *
     * @param email email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    public boolean validar_email(String email) {
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
            if (!es_letra(email.charAt(i)) && !es_numero(email.charAt(i))) {
                return false;
            }
        }

        int tamano = email.length();
        // comprobamos que el email termine en letra
        if (!es_letra(email.charAt(tamano - 1))) {
            System.out.println("Email no válido");
            return false;
        }
        return true;
    }

    /**
     * Comprueba si una contraseña cumple con los requisitos.
     *
     * @param contrasena contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    public boolean validar_contrasena(String contrasena) {
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
            } else if (es_numero(c)) {
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
    public boolean validar_nombre(String nombre) {
        nombre = nombre.toLowerCase();

        for (int i = 0; i < nombre.length(); i++) {
            char letra = nombre.charAt(i);

            // Si algún booleano es false
            if (!es_letra(letra) && !es_acento(letra) && letra != ' ') {
                System.out.println("Nombre no válido, debe contener solo letras");
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
    public Boolean validar_fecha(String dia_str, String mes_str, String ano_str) {

        boolean validador = false;
        int dia = 0;
        int mes = 0;
        int ano = 0;

        do {
            for (int i = 0; i < dia_str.length(); i++) {
                char letra = dia_str.charAt(i);
                if (!es_numero(letra)) {
                    validador = false;
                    System.out.println("Valor INCORRECTO, inserta solo números.");
                    break;
                } else if (dia_str.length() > 2) {
                    validador = false;
                    System.out.println("Valor INCORRECTO, el limite son dos digitos");
                    break;
                }
                validador = true;
            }
            if (validador) {
                dia = Integer.parseInt(dia_str);
            }
        } while (!validador);

        do {
            for (int i = 0; i < mes_str.length(); i++) {
                char letra = mes_str.charAt(i);
                if (!es_numero(letra)) {
                    validador = false;
                    System.out.println("Valor INCORRECTO,  inserta solo números.");
                    break;
                } else if (mes_str.length() > 2) {
                    validador = false;
                    System.out.println("Valor INCORRECTO, el limite son dos digitos");
                    break;
                }
                validador = true;
            }
            if (validador) {
                mes = Integer.parseInt(mes_str);
            }
        } while (!validador);

        do {
            for (int i = 0; i < ano_str.length(); i++) {
                char letra = ano_str.charAt(i);
                if (!es_numero(letra)) {
                    validador = false;
                    System.out.println("Valor INCORRECTO,  inserta solo números.");
                    break;
                } else if (ano_str.length() != 4) {
                    validador = false;
                    System.out.println("Valor INCORRECTO, introduce 4 digitos.");
                    break;
                }
                validador = true;
            }
            if (validador) {
                ano = Integer.parseInt(ano_str);
            }
        } while (!validador);

        // Validamos mes
        int dias_mes;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dias_mes = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dias_mes = 30;
                break;
            case 2:
                if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
                    dias_mes = 29;
                } else {
                    dias_mes = 28;
                }
                break;
            default:
                System.out.println("La fecha NO es válida.");
                return false;
        }

        if (dia < 1 || dia > dias_mes) {
            System.out.println("La fecha NO es válida.");
            return false;
        }

        // Verificamos si es menor de edad
        if (ano > 2006 || (ano == 2006 && mes > 11)) {
            System.out.println("Eres MENOR de edad. No puedes registrarte");
            return false;
        }
        String fecha_registrada = dia_str + "/" + mes_str + "/" + ano_str;
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
        if (es_numero(dni.charAt(0))) {
            return comprobar_dni(dni);
            // Si es nie, empieza por letra
        } else if (es_letra(dni.charAt(0))) {
            return comprobar_nie(dni);
        }
        return false;
    }

    /**
     * Comprueba si un DNI tiene formato válido.
     *
     * @param dni a comprobar.
     * @return true si el formato es válido, false en caso contrario.
     */
    private boolean comprobar_dni(String dni) {
        for (int i = 0; i < 8; i++) {
            if (!es_numero(dni.charAt(i))) {
                return false;
            }
        }
        return es_letra(dni.charAt(8));
    }

    /**
     * Comprueba si un NIE tiene formato válido.
     *
     * @param nie a comprobar.
     * @return true si el formato es válido, false en caso contrario.
     */
    private boolean comprobar_nie(String nie) {
        for (int i = 1; i <= 7; i++) {
            if (!es_numero(nie.charAt(i))) {
                return false;
            }
        }
        return es_letra(nie.charAt(8));
    }

    /**
     * Verifica si un carácter es un número.
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es un número, false en caso contrario.
     */
    private boolean es_numero(char letra) {
        return letra >= '0' && letra <= '9';
    }

    /**
     * Cromprueba que el valor sea una letra
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es una letra, false en caso contrario.
     */
    public boolean es_letra(char letra) {
        return (letra >= 'A' && letra <= 'Z') || (letra >= 'a' && letra <= 'z');
    }

    /**
     * Comprueba que el valor sea una letra acentuada
     *
     * @param letra carácter a comprobar.
     * @return true si el carácter es una letra con acento, false en caso contrario.
     */
    private boolean es_acento(char letra) {
        return (letra == 'á' || letra == 'é' || letra == 'í' || letra == 'ó' || letra == 'ú' || letra == 'ç' || letra == 'ñ');
    }

    public Usuario hacer_login(String usuario, String contrasena, Usuario[] array_usuarios) {
        for(int i = 0; i < array_usuarios.length; i++) {
            if (usuario.equals(array_usuarios[i].getUsuario()) && contrasena.equals(array_usuarios[i].getContrasena())) {
                return array_usuarios[i];
            }
        }
        return null;
    }

    public boolean verificar_contrasenas (String contrasena_registrada1, String contrasena_registrada2) {
        if (!contrasena_registrada1.equals(contrasena_registrada2)) {
            System.out.println("Las contraseñas no coinciden. Intenta de nuevo.");
            return false;
        }

        if (!validar_contrasena(contrasena_registrada1)) {
            System.out.println("Contraseña no válida.");
            return false;
        }
        return true;
    }
}
