package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.MedioDeTransporte.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioContratadoRepository extends JpaRepository<TipoServicio, Integer> {
}
