package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorTerritorialRepository extends JpaRepository<SectorTerritorial, Integer> {
}
