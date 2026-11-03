package co.edu.cesde.ga.infrastructure.persistence;

import co.edu.cesde.ga.domain.model.EstadoHabitacion;
import co.edu.cesde.ga.domain.model.Habitacion;
import co.edu.cesde.ga.domain.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionJpaRepository extends JpaRepository<Habitacion, Long> {

    Optional<Habitacion> findByNumeroHabitacion(Integer numeroHabitacion);

    List<Habitacion> findByTipoHabitacionAndEstado(TipoHabitacion tipoHabitacion, EstadoHabitacion estado);
}
