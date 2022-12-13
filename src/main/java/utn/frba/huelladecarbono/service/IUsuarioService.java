package utn.frba.huelladecarbono.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;

import java.util.List;

public interface IUsuarioService extends UserDetailsService {

    public List<Usuario> getUsuarios();

    public void saveUsuario(Usuario usuario);

    public void deleteUsuario(Integer id);

    public Usuario findUsuario(Integer id);

    public void cambiarEstadoUsuario(Integer id);

   Usuario modificarUsuario(Integer id, Usuario usuario);
}
