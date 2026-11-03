package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.response.HabitacionResponse;
import co.edu.cesde.ga.application.service.ServicioHabitacion;
import co.edu.cesde.ga.domain.model.TipoHabitacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
@Tag(name = "Habitaciones", description = "Consulta de habitaciones del hotel")
public class HabitacionController {

    private final ServicioHabitacion servicioHabitacion;

    @GetMapping
    @Operation(summary = "Listar todas las habitaciones")
    public ResponseEntity<List<HabitacionResponse>> consultarTodas() {
        return ResponseEntity.ok(servicioHabitacion.consultarTodas());
    }

    @GetMapping("/{numero}")
    @Operation(summary = "Consultar habitación por número")
    public ResponseEntity<HabitacionResponse> consultarPorNumero(@PathVariable Integer numero) {
        return ResponseEntity.ok(servicioHabitacion.consultarPorNumero(numero));
    }

    @GetMapping("/disponibles/{tipo}")
    @Operation(summary = "Consultar habitaciones disponibles por tipo")
    public ResponseEntity<List<HabitacionResponse>> buscarDisponiblePorTipo(@PathVariable TipoHabitacion tipo) {
        return ResponseEntity.ok(servicioHabitacion.buscarDisponiblePorTipo(tipo));
    }
}
