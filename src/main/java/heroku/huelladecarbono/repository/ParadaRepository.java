package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.MedioDeTransporte.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepository extends JpaRepository<Parada,Integer> {
}
