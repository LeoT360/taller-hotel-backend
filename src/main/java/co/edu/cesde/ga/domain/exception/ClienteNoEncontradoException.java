package co.edu.cesde.ga.domain.exception;

public class ClienteNoEncontradoException extends ResourceNotFoundException {

    public ClienteNoEncontradoException(Long id) {
        super("Cliente no encontrado con ID: " + id);
    }

    public ClienteNoEncontradoException(String campo, String valor) {
        super("Cliente no encontrado con " + campo + ": " + valor);
    }
}
