package co.edu.cesde.ga.application.service;

import co.edu.cesde.ga.application.dto.request.CheckOutRequest;
import co.edu.cesde.ga.application.dto.response.FacturaResponse;

public interface ServicioCheckOut {

    FacturaResponse realizarCheckOut(CheckOutRequest request);
}
