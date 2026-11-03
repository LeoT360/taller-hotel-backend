package co.edu.cesde.ga.application.service;

import co.edu.cesde.ga.application.dto.request.CheckInRequest;
import co.edu.cesde.ga.application.dto.response.CheckInResponse;

public interface ServicioCheckIn {

    CheckInResponse realizarCheckIn(CheckInRequest request);
}
