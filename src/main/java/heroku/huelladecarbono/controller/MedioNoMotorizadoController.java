package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.IMedioNoMotorizadoService;

import java.util.List;
@RestController
public class MedioNoMotorizadoController {
    @Autowired
    private IMedioNoMotorizadoService interfazMedio;

    @GetMapping("/medioNoMotorizado")
    public List<MedioNoMotorizado> getMedioNoMotorizado() { return  interfazMedio.getMedioNoMotorizado();}

    @DeleteMapping("medioNoMotorizado/eliminar/{id}")
    public String deleteMedioMotorizado(@PathVariable Integer id) {
        interfazMedio.deleteMedioNoMotorizado(id);
        return "El transporte fue eliminado correctamente";
    }

    @PostMapping("/medioNoMotorizado/crear")
    public String saveMedioMotorizado(@RequestBody MedioNoMotorizado medioNoMotorizado){
        interfazMedio.saveMedioNoMotorizado(medioNoMotorizado);
        return "El transporte fue creado correctamente";
    }
}
