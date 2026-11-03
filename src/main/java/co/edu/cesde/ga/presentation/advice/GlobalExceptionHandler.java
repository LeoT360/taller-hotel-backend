package co.edu.cesde.ga.presentation.advice;

import co.edu.cesde.ga.application.dto.response.ErrorResponse;
import co.edu.cesde.ga.domain.exception.ResourceAlreadyExistsException;
import co.edu.cesde.ga.domain.exception.ResourceConflictException;
import co.edu.cesde.ga.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, "Recurso no encontrado", ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return buildError(HttpStatus.CONFLICT, "Recurso ya existe", ex.getMessage());
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponse> handleResourceConflict(ResourceConflictException ex) {
        return buildError(HttpStatus.CONFLICT, "Conflicto de negocio", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return buildError(HttpStatus.BAD_REQUEST, "Solicitud inválida", mensaje);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor",
                "Ocurrió un error inesperado: " + ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String error, String mensaje) {
        ErrorResponse body = ErrorResponse.builder()
                .codigoHttp(status.value())
                .error(error)
                .mensaje(mensaje)
                .fechaHora(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
