package co.edu.cesde.ga.domain.exception;

public class ClienteYaExisteException extends ResourceAlreadyExistsException {

    public ClienteYaExisteException(String identificacion) {
        super("Ya existe un cliente con la identificación: " + identificacion);
    }
}
