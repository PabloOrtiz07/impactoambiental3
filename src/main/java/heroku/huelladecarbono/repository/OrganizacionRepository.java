package heroku.huelladecarbono.repository;

import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Integer> {

    List<Organizacion> findByEstaActivo(Boolean estado);

    List<Organizacion> findByRazonSocial(String razonSocial);


}
