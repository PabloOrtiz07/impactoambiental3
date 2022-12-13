package utn.frba.huelladecarbono.repository;

import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportePublicoRepository extends JpaRepository<TransportePublico,Integer> {
}
