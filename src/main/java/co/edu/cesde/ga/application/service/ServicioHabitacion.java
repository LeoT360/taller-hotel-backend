package co.edu.cesde.ga.application.service;

import co.edu.cesde.ga.application.dto.response.HabitacionResponse;
import co.edu.cesde.ga.domain.model.TipoHabitacion;

import java.util.List;

public interface ServicioHabitacion {

    List<HabitacionResponse> consultarTodas();

    HabitacionResponse consultarPorNumero(Integer numero);

    List<HabitacionResponse> buscarDisponiblePorTipo(TipoHabitacion tipo);
}
