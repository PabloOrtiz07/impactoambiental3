package utn.frba.huelladecarbono.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Localidad;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Municipio;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Pais;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Provincia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import utn.frba.huelladecarbono.service.IUbicacionService;
import utn.frba.huelladecarbono.service.UbicacionService;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private IUbicacionService interfazUbicacion;

    @GetMapping({"/",""})
    public List<Ubicacion> getUbicacion() {return interfazUbicacion.getUbicacion();}

    @DeleteMapping("/eliminar/{id}")
    public String deleteUbicacion(@PathVariable Integer id) {
        interfazUbicacion.deleteUbicacion(id);
        return "La ubicacion ha sido eliminada";
    }

    @PostMapping("/crear")
    public String saveUbicacion(@RequestBody Ubicacion ubicacion) {
        interfazUbicacion.saveUbicacion(ubicacion);
        return "La ubicacion fue creada correctamente";
    }

    @GetMapping("/paises")
    public Pais[] getPaises() throws IOException {
       return APIDistanciaService.buscarPaises();
    }

    @GetMapping("/provincias/{idPais}")
    public Provincia[] getProvincias(@PathVariable Integer idPais) throws IOException {
        return APIDistanciaService.buscarProvincias(idPais);
    }

    @GetMapping("/municipios/{idProvincia}")
    public Municipio[] getMunicipios(@PathVariable Integer idProvincia) throws IOException {
        return APIDistanciaService.buscarMunicipios(idProvincia);
    }

    @GetMapping("/localidades/{idMunicipio}")
    public Localidad[] getLocalidades(@PathVariable Integer idMunicipio) throws IOException {
        return APIDistanciaService.buscarLocalidades(idMunicipio);
    }
}
