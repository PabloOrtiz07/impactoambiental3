package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedioNoMotorizadoRepository extends JpaRepository<MedioNoMotorizado,Integer> {
}
