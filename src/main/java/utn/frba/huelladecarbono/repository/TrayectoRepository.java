package utn.frba.huelladecarbono.repository;

import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto,Integer> {
}
