package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.Seguridad.Usuario;
import heroku.huelladecarbono.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
