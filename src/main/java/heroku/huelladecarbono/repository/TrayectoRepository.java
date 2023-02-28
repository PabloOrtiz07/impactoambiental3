package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.Movilidad.Trayecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto,Integer> {
}
