package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.ITransportePublicoService;

import java.util.List;

@RestController
public class TransportePublicoController {

    @Autowired
    private ITransportePublicoService interfazTransporte;

    @GetMapping("/transportesPublicos")
    public List<TransportePublico> getTransportesPublicos(){
        return interfazTransporte.getTransportesPublicos();
    }

    @DeleteMapping("transportePublico/eliminar/{id}")
    public String deleteTransportePublico(@PathVariable Integer id) {
        interfazTransporte.deleteTransportePublico(id);
        return "El transporte fue eliminado correctamente";
    }

    @PostMapping("/transportePublico/crear")
    public String saveTransportePublico(@RequestBody TransportePublico transporte){
        interfazTransporte.saveTransportePublico(transporte);
        return "El transporte fue creado correctamente";
    }


}
