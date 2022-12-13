package utn.frba.huelladecarbono.model.UserExceptions;

public class ArchivoNotFoundException extends  RuntimeException {
    public ArchivoNotFoundException(String mensajeDeError) {
        System.out.println(mensajeDeError);
    }
}
