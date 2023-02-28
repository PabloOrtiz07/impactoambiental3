package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedioMotorizadoRepository extends JpaRepository<MedioMotorizado,Integer> {
}
