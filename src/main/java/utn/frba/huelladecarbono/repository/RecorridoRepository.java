package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;

@Repository
public interface RecorridoRepository extends JpaRepository<Recorrido,Integer> {
}
