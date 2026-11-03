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
public class ErrorResponse {

    private int codigoHttp;
    private String error;
    private String mensaje;
    private LocalDateTime fechaHora;
}
