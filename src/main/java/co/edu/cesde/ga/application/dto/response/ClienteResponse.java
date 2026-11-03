package co.edu.cesde.ga.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private Long id;
    private String identificacion;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private LocalDateTime fechaCreacion;
}
