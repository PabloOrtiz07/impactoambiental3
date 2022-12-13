package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.service.IUsuarioService;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService interfazUsuario;

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return interfazUsuario.getUsuarios();
    }

    @DeleteMapping("usuario/eliminar/{id}")
    public String deleteUsuario(@PathVariable Integer id) {
        interfazUsuario.cambiarEstadoUsuario(id);
        return "El usuario fue eliminado correctamente";
    }

    @PostMapping("/usuario/crear")
    public String saveUsuario(@RequestBody Usuario usuario){
        interfazUsuario.saveUsuario(usuario);
        return "El usuario fue creado correctamente";
    }

    @PatchMapping("/usuario/editar/{id}")
    public Usuario cambiarEstadoUsuario(@PathVariable Integer id){
        interfazUsuario.cambiarEstadoUsuario(id);
        Usuario usuario = interfazUsuario.findUsuario(id);
        return usuario;
    }

    @PutMapping("/usuario/editar/{id}")
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) throws Exception {
        return interfazUsuario.modificarUsuario(id,usuario);
    }

}
