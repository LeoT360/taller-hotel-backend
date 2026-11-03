package co.edu.cesde.ga.application.service;

import co.edu.cesde.ga.application.dto.request.ClienteRequest;
import co.edu.cesde.ga.application.dto.response.ClienteResponse;

import java.util.List;

public interface ServicioCliente {

    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse consultarPorId(Long id);

    ClienteResponse consultarPorIdentificacion(String identificacion);

    List<ClienteResponse> consultarTodos();
}
