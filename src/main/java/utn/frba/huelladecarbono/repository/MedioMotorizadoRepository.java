package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

public interface MedioMotorizadoRepository extends JpaRepository<MedioMotorizado,Integer> {
}
