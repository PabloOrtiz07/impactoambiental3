package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Integer> {
}
