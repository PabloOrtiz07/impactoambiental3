package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.IMedioMotorizadoService;

import java.util.List;


@RestController
public class MedioMotorizadoController {

    @Autowired
    private IMedioMotorizadoService interfazMedio;

    @GetMapping("/medioMotorizado")
    public List<MedioMotorizado> getMedioMotorizado() { return  interfazMedio.getMedioMotorizado();}

    @DeleteMapping("medioMotorizado/eliminar/{id}")
    public String deleteMedioMotorizado(@PathVariable Integer id) {
        interfazMedio.deleteMedioMotorizado(id);
        return "El transporte fue eliminado correctamente";
    }

    @PostMapping("/medioMotorizado/crear")
    public String saveMedioMotorizado(@RequestBody MedioMotorizado medioMotorizado){
        interfazMedio.saveMedioMotorizado(medioMotorizado);
        return "El transporte fue creado correctamente";
    }

}
