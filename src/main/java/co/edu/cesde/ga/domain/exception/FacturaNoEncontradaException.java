package co.edu.cesde.ga.domain.exception;

public class FacturaNoEncontradaException extends ResourceNotFoundException {

    public FacturaNoEncontradaException(Long id) {
        super("Factura no encontrada con ID: " + id);
    }
}
