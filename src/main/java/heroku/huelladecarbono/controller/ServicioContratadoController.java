package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.MedioDeTransporte.TipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.IServicioContratadoService;

import java.util.List;

@RestController
public class ServicioContratadoController {

    @Autowired
    private IServicioContratadoService interfazServicioContratado;

    @GetMapping("/serviciosContratados")
    public List<TipoServicio> getServiciosContratados(){
        return interfazServicioContratado.getServiciosContratados();
    }

    @DeleteMapping("servicioContratado/eliminar/{id}")
    public String deleteServicioContratado(@PathVariable Integer id) {
        interfazServicioContratado.deleteServicioContratado(id);
        return "El servicio fue eliminado correctamente";
    }

    @PostMapping("/servicioContratado/crear")
    public String saveServicioContratado(@RequestBody TipoServicio servicio){
        interfazServicioContratado.saveServicioContratado(servicio);
        return "El servicio fue creado correctamente";
    }


}
