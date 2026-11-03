package co.edu.cesde.ga.domain.exception;

import co.edu.cesde.ga.domain.model.TipoHabitacion;

public class HabitacionNoDisponibleException extends ResourceConflictException {

    public HabitacionNoDisponibleException(TipoHabitacion tipo) {
        super("No existen habitaciones disponibles del tipo: " + tipo);
    }
}
