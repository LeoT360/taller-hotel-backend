package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.application.dto.request.CheckInRequest;
import co.edu.cesde.ga.application.dto.response.CheckInResponse;
import co.edu.cesde.ga.application.service.ServicioCheckIn;
import co.edu.cesde.ga.domain.exception.ClienteConHospedajeActivoException;
import co.edu.cesde.ga.domain.exception.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.exception.HabitacionNoDisponibleException;
import co.edu.cesde.ga.domain.model.*;
import co.edu.cesde.ga.infrastructure.persistence.ClienteJpaRepository;
import co.edu.cesde.ga.infrastructure.persistence.HabitacionJpaRepository;
import co.edu.cesde.ga.infrastructure.persistence.HospedajeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCheckInImpl implements ServicioCheckIn {

    private final ClienteJpaRepository clienteJpaRepository;
    private final HabitacionJpaRepository habitacionJpaRepository;
    private final HospedajeJpaRepository hospedajeJpaRepository;

    @Override
    @Transactional
    public CheckInResponse realizarCheckIn(CheckInRequest request) {
        Cliente cliente = clienteJpaRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(request.getClienteId()));

        if (hospedajeJpaRepository.existsByClienteIdAndActivoTrue(cliente.getId())) {
            throw new ClienteConHospedajeActivoException(cliente.getId());
        }

        List<Habitacion> disponibles = habitacionJpaRepository
                .findByTipoHabitacionAndEstado(request.getTipoHabitacion(), EstadoHabitacion.DISPONIBLE);

        if (disponibles.isEmpty()) {
            throw new HabitacionNoDisponibleException(request.getTipoHabitacion());
        }

        Habitacion habitacion = disponibles.get(0);
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionJpaRepository.save(habitacion);

        Hospedaje hospedaje = Hospedaje.builder()
                .cliente(cliente)
                .habitacion(habitacion)
                .fechaHoraIngreso(LocalDateTime.now())
                .activo(true)
                .build();

        Hospedaje guardado = hospedajeJpaRepository.save(hospedaje);

        return CheckInResponse.builder()
                .hospedajeId(guardado.getId())
                .clienteId(cliente.getId())
                .cliente(cliente.getNombreCompleto())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .fechaHoraIngreso(guardado.getFechaHoraIngreso())
                .mensaje("Check-In realizado exitosamente. Habitación asignada: " + habitacion.getNumeroHabitacion())
                .build();
    }
}
