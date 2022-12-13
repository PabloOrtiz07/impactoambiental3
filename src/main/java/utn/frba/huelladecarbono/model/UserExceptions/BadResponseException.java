package utn.frba.huelladecarbono.model.UserExceptions;

public class BadResponseException extends RuntimeException {
    public BadResponseException(String mensajeDeError) {
        System.out.println(mensajeDeError);
    }
}
