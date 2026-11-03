package co.edu.cesde.ga.application.service;

import co.edu.cesde.ga.application.dto.response.FacturaResponse;

import java.util.List;

public interface ServicioFactura {

    FacturaResponse consultarPorId(Long id);

    List<FacturaResponse> consultarTodas();
}
