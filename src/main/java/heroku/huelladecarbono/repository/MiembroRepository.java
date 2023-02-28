package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Integer> {
}