package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Integer> {
}