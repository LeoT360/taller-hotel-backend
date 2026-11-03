package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.application.dto.request.CheckOutRequest;
import co.edu.cesde.ga.application.dto.response.FacturaResponse;
import co.edu.cesde.ga.application.service.ServicioCheckOut;
import co.edu.cesde.ga.domain.exception.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.exception.HospedajeActivoNoEncontradoException;
import co.edu.cesde.ga.domain.model.*;
import co.edu.cesde.ga.infrastructure.persistence.ClienteJpaRepository;
import co.edu.cesde.ga.infrastructure.persistence.FacturaJpaRepository;
import co.edu.cesde.ga.infrastructure.persistence.HabitacionJpaRepository;
import co.edu.cesde.ga.infrastructure.persistence.HospedajeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ServicioCheckOutImpl implements ServicioCheckOut {

    private static final LocalTime HORA_CHECK_OUT = LocalTime.of(13, 0);

    private final ClienteJpaRepository clienteJpaRepository;
    private final HospedajeJpaRepository hospedajeJpaRepository;
    private final HabitacionJpaRepository habitacionJpaRepository;
    private final FacturaJpaRepository facturaJpaRepository;

    @Override
    @Transactional
    public FacturaResponse realizarCheckOut(CheckOutRequest request) {
        Cliente cliente = clienteJpaRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(request.getClienteId()));

        Hospedaje hospedaje = hospedajeJpaRepository.findByClienteIdAndActivoTrue(cliente.getId())
                .orElseThrow(() -> new HospedajeActivoNoEncontradoException(cliente.getId()));

        LocalDateTime fechaHoraSalida = LocalDateTime.now();
        LocalDateTime fechaHoraIngreso = hospedaje.getFechaHoraIngreso();

        int cantidadNoches = calcularNoches(fechaHoraIngreso, fechaHoraSalida);

        Habitacion habitacion = hospedaje.getHabitacion();
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
        habitacionJpaRepository.save(habitacion);

        hospedaje.setFechaHoraSalida(fechaHoraSalida);
        hospedaje.setActivo(false);
        hospedajeJpaRepository.save(hospedaje);

        BigDecimal valorNoche = habitacion.getValorNoche();
        BigDecimal valorTotal = valorNoche.multiply(BigDecimal.valueOf(cantidadNoches));

        Factura factura = Factura.builder()
                .hospedaje(hospedaje)
                .cantidadNoches(cantidadNoches)
                .valorNoche(valorNoche)
                .valorTotal(valorTotal)
                .build();

        Factura facturaGuardada = facturaJpaRepository.save(factura);

        return FacturaResponse.builder()
                .facturaId(facturaGuardada.getId())
                .clienteId(cliente.getId())
                .cliente(cliente.getNombreCompleto())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .fechaHoraIngreso(fechaHoraIngreso)
                .fechaHoraSalida(fechaHoraSalida)
                .cantidadNoches(cantidadNoches)
                .valorNoche(valorNoche)
                .valorTotal(valorTotal)
                .fechaGeneracion(facturaGuardada.getFechaGeneracion())
                .build();
    }

    private int calcularNoches(LocalDateTime ingreso, LocalDateTime salida) {
        LocalDate fechaIngreso = ingreso.toLocalDate();
        LocalDate fechaSalida = salida.toLocalDate();

        long dias = ChronoUnit.DAYS.between(fechaIngreso, fechaSalida);

        if (dias == 0) {
            return 1;
        }

        int noches = (int) dias;

        if (salida.toLocalTime().isAfter(HORA_CHECK_OUT)) {
            noches++;
        }

        return Math.max(noches, 1);
    }
}
