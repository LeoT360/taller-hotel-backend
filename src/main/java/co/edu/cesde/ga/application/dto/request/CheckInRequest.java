package co.edu.cesde.ga.application.dto.request;

import co.edu.cesde.ga.domain.model.TipoHabitacion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El tipo de habitación es obligatorio")
    private TipoHabitacion tipoHabitacion;
}
