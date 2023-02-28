package heroku.huelladecarbono.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import heroku.huelladecarbono.service.CalculoDeHuellaService.RegistroCalculoHCDatoActividad;

@Repository
public interface DatoActividadRepository extends JpaRepository<RegistroCalculoHCDatoActividad,Integer> {


}