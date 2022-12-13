package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import utn.frba.huelladecarbono.service.IMedioMotorizadoService;

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
