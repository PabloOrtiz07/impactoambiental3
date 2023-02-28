package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.ListaDeDatosDeMedicion;
import heroku.huelladecarbono.repository.AreaRepository;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.DTO.ResArea;
import heroku.huelladecarbono.DTO.ResDatoDeActividad;
import heroku.huelladecarbono.service.AreaService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AreaController {

    @Autowired
    private AreaService interfazArea;

    @Autowired
    private AreaRepository areaRepository;


    //Endpoint para obtener a todos las organizaciones
    @GetMapping("/areas")
    public List<ResArea> getAreas(){
        List<Area> areas = interfazArea.getAreas();
        return areas.stream().map(ResArea::new).collect(Collectors.toList());
    }
    @GetMapping("/areas/{idOrg}")
    public List<ResArea> getAreasByOrg(@PathVariable Integer idOrg) {
        List<Area> areas = interfazArea.getAreas();
        return areas.stream().filter(area -> area.getOrganizacion().getId() == idOrg).map(ResArea::new).collect(Collectors.toList());

    }

    //qobtener solo a las organizaciones que están activas en la bd
    // No funciona como endpoint por ser recursivo
    @GetMapping("/areas/estado")
    public List<Area> getOrganizacionesActivas() {
        return interfazArea.findAreaByEstadoActivo();
    }

    @PostMapping("/area/crear")
    public String saveArea(@RequestBody String areaJson) throws ParseException {
        interfazArea.crearArea(areaJson);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/area/editar/{id}")
    public void cambiarEstadoArea(@PathVariable Integer id) {
        interfazArea.cambiarEstadoArea(id);
        Area area = interfazArea.findArea(id);
    }

    @PutMapping("/area/editar/{id}")
    public void actualizarOrganizacion(@PathVariable Integer id, @RequestBody Area area) throws Exception {
        interfazArea.modificarArea(id,area);
    }

    @GetMapping("borrarArea/{idArea}")
    public void borrarArea(@PathVariable Integer idArea) {
        interfazArea.deleteArea(idArea);
    }

    @GetMapping("datosDeActividad/{orgId}")
    public List<ResDatoDeActividad> datosDeActividad(@PathVariable Integer orgId) {
        List<ResDatoDeActividad> res = new ArrayList<>();
        List<Area> areas = interfazArea.getAreas();
        for (Area area : areas) {
           for (ListaDeDatosDeMedicion dato : area.getMediciones()) {
               res.add(new ResDatoDeActividad(area, dato.getFecha()));
           }
        }
        return res;
    }

    @GetMapping("cargarDatosDeActividad/{areaId}")
    public void cargarDatosDeActividad(@PathVariable Integer areaId) {
        interfazArea.cargarDatoDeActividad(areaId);
    }
}

























