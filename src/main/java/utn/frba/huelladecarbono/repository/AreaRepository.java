package utn.frba.huelladecarbono.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area,Integer> {
    List<Area> findByEstaActivo(Boolean estado);

    List<Area> findByOrganizacion_Id(Integer id);

}
