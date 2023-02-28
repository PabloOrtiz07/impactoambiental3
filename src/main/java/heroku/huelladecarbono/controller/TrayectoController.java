package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.Movilidad.Trayecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.ITrayectoService;

import java.util.List;

@RestController
public class TrayectoController {

    @Autowired
    private ITrayectoService interfazTrayecto;

    @GetMapping("/trayectos")
    public List<Trayecto> getTrayectos(){
        return interfazTrayecto.getTrayectos();
    }

    @DeleteMapping("trayecto/eliminar/{id}")
    public String deleteTrayecto(@PathVariable Integer id) {
        interfazTrayecto.deleteTrayecto(id);
        return "El trayecto fue eliminado correctamente";
    }

    @PostMapping("/trayecto/crear")
    public String saveTrayecto(@RequestBody Trayecto trayecto){
        interfazTrayecto.saveTrayecto(trayecto);
        return "El trayecto fue creado correctamente";
    }


}
