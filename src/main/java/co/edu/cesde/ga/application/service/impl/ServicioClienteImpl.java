package co.edu.cesde.ga.application.service.impl;

import co.edu.cesde.ga.application.dto.request.ClienteRequest;
import co.edu.cesde.ga.application.dto.response.ClienteResponse;
import co.edu.cesde.ga.application.service.ServicioCliente;
import co.edu.cesde.ga.domain.exception.ClienteNoEncontradoException;
import co.edu.cesde.ga.domain.exception.ClienteYaExisteException;
import co.edu.cesde.ga.domain.model.Cliente;
import co.edu.cesde.ga.infrastructure.persistence.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioClienteImpl implements ServicioCliente {

    private final ClienteJpaRepository clienteJpaRepository;

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest request) {
        if (clienteJpaRepository.existsByIdentificacion(request.getIdentificacion())) {
            throw new ClienteYaExisteException(request.getIdentificacion());
        }

        Cliente cliente = Cliente.builder()
                .identificacion(request.getIdentificacion())
                .nombreCompleto(request.getNombreCompleto())
                .correoElectronico(request.getCorreoElectronico())
                .telefono(request.getTelefono())
                .build();

        return mapToResponse(clienteJpaRepository.save(cliente));
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse consultarPorId(Long id) {
        Cliente cliente = clienteJpaRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
        return mapToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse consultarPorIdentificacion(String identificacion) {
        Cliente cliente = clienteJpaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ClienteNoEncontradoException("identificación", identificacion));
        return mapToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> consultarTodos() {
        return clienteJpaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .identificacion(cliente.getIdentificacion())
                .nombreCompleto(cliente.getNombreCompleto())
                .correoElectronico(cliente.getCorreoElectronico())
                .telefono(cliente.getTelefono())
                .fechaCreacion(cliente.getFechaCreacion())
                .build();
    }
}
