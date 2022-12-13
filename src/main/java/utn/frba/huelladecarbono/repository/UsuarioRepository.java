package utn.frba.huelladecarbono.repository;

import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByUsername(String username);
}
