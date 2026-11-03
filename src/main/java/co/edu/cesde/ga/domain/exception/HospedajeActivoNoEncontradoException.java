package co.edu.cesde.ga.domain.exception;

public class HospedajeActivoNoEncontradoException extends ResourceNotFoundException {

    public HospedajeActivoNoEncontradoException(Long clienteId) {
        super("No se encontró un hospedaje activo para el cliente con ID: " + clienteId);
    }
}
