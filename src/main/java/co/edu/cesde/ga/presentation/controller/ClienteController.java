package co.edu.cesde.ga.presentation.controller;

import co.edu.cesde.ga.application.dto.request.ClienteRequest;
import co.edu.cesde.ga.application.dto.response.ClienteResponse;
import co.edu.cesde.ga.application.service.ServicioCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gestión de clientes del hotel")
public class ClienteController {

    private final ServicioCliente servicioCliente;

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCliente.crearCliente(request));
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes")
    public ResponseEntity<List<ClienteResponse>> consultarTodos() {
        return ResponseEntity.ok(servicioCliente.consultarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar cliente por ID")
    public ResponseEntity<ClienteResponse> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicioCliente.consultarPorId(id));
    }

    @GetMapping("/documento/{identificacion}")
    @Operation(summary = "Consultar cliente por identificación")
    public ResponseEntity<ClienteResponse> consultarPorIdentificacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(servicioCliente.consultarPorIdentificacion(identificacion));
    }
}
