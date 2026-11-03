package co.edu.cesde.ga.infrastructure.persistence;

import co.edu.cesde.ga.domain.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaJpaRepository extends JpaRepository<Factura, Long> {
}
