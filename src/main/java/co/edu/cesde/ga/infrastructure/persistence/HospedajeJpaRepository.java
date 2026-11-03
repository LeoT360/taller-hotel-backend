package co.edu.cesde.ga.infrastructure.persistence;

import co.edu.cesde.ga.domain.model.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospedajeJpaRepository extends JpaRepository<Hospedaje, Long> {

    Optional<Hospedaje> findByClienteIdAndActivoTrue(Long clienteId);

    boolean existsByClienteIdAndActivoTrue(Long clienteId);
}
