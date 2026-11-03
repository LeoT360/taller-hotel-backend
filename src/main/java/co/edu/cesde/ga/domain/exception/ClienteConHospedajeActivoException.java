package co.edu.cesde.ga.domain.exception;

public class ClienteConHospedajeActivoException extends ResourceConflictException {

    public ClienteConHospedajeActivoException(Long clienteId) {
        super("El cliente con ID " + clienteId + " ya tiene un hospedaje activo");
    }
}
