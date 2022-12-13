package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

public interface MedioNoMotorizadoRepository extends JpaRepository<MedioNoMotorizado,Integer> {
}
