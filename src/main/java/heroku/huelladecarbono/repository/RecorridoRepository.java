package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.Movilidad.Recorrido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecorridoRepository extends JpaRepository<Recorrido,Integer> {
}
