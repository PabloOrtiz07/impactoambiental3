package utn.frba.huelladecarbono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;

@Repository
public interface SectorTerritorialRepository extends JpaRepository<SectorTerritorial, Integer> {
}
