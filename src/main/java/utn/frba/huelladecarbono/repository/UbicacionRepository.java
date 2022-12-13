package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Integer> {
}
