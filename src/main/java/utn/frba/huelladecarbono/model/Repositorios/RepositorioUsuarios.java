package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.UsuarioController;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;

import java.util.ArrayList;

@Component
public class RepositorioUsuarios {
    @Autowired
    UsuarioController usuariobd;
    private static RepositorioUsuarios instance = new RepositorioUsuarios();
    private ArrayList<Usuario> usuarios;

    private RepositorioUsuarios() {
        this.usuarios = new ArrayList<>();

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public static RepositorioUsuarios getRepositorio() {
        return instance;
    }

    public void agregarUsuario(Usuario user){
        this.usuarios.add(user);
    }

    public void cargarDeUsuariosDeBdAlSistema() {
        for(Usuario usuarioclase : usuariobd.getUsuarios()) {
            Usuario usuario = new Usuario(usuarioclase.getUsername(),usuarioclase.getPassword(),usuarioclase.getRoles());
            this.agregarUsuario(usuario);
        }
    }

}
