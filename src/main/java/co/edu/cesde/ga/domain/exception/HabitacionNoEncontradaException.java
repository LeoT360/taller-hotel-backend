package co.edu.cesde.ga.domain.exception;

public class HabitacionNoEncontradaException extends ResourceNotFoundException {

    public HabitacionNoEncontradaException(Integer numero) {
        super("Habitación no encontrada con número: " + numero);
    }
}
