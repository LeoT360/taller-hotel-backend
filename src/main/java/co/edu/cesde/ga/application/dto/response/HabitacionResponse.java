package co.edu.cesde.ga.application.dto.response;

import co.edu.cesde.ga.domain.model.EstadoHabitacion;
import co.edu.cesde.ga.domain.model.TipoHabitacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitacionResponse {

    private Long id;
    private Integer numeroHabitacion;
    private Integer piso;
    private TipoHabitacion tipoHabitacion;
    private BigDecimal valorNoche;
    private EstadoHabitacion estado;
}
