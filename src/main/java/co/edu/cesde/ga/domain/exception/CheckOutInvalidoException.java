package co.edu.cesde.ga.domain.exception;

public class CheckOutInvalidoException extends ResourceConflictException {

    public CheckOutInvalidoException(String mensaje) {
        super(mensaje);
    }
}
