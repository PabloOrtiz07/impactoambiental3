package heroku.huelladecarbono.repository;



import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area,Integer> {
    List<Area> findByEstaActivo(Boolean estado);

    List<Area> findByOrganizacion_Id(Integer id);

}
