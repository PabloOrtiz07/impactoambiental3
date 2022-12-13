package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.UsuarioRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> getUsuarios() {
        List <Usuario> listaUsuarios = usuarioRepository.findAll();

        return listaUsuarios;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        Usuario appUser = usuarioRepository.findByUsername(usuario.getUsername());
        if (appUser==null){
            Usuario usuarioGuardar = new Usuario(usuario.getUsername(),
                    usuario.getPassword(), usuario.getRoles());
            usuarioRepository.save(usuarioGuardar);
        }

    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }
    @Override
    public void cambiarEstadoUsuario(Integer id) {
        Usuario usuario = findUsuario(id);
        usuario.setEstaActivo(false);
        this.saveUsuario(usuario);
    }

    @Override
    public Usuario modificarUsuario(Integer id, Usuario usuario){
        Usuario usuarioActualizado = this.findUsuario(id);
        usuarioActualizado.setEstaActivo(usuario.getEstaActivo());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setRoles(usuario.getRoles());
        usuarioActualizado.setUsername(usuario.getUsername());
        usuarioActualizado.setMiembro(usuario.getMiembro());
        this.saveUsuario(usuarioActualizado);
        return usuarioActualizado;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
        Usuario appUser = usuarioRepository.findByUsername(username);
        if (appUser==null){
            throw new UsernameNotFoundException("no existe");
        }
        //Mapear nuestra lista de Authority con la de spring security
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        return (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
    }
}
