package utn.frba.huelladecarbono.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.SectorTerritorialRepository;
import utn.frba.huelladecarbono.service.IMiembroService;
import utn.frba.huelladecarbono.service.ISectorTerritorialService;

import java.util.List;

@RestController
public class SectorTerritorialController {

    @Autowired
    private ISectorTerritorialService interfazSectorTerritorial;

    SectorTerritorialRepository sectorTerritorialRepository;

    //Prueba para obtener a un miembro
    @RequestMapping(value = "sectorTerritorial")
    public List<SectorTerritorial> getSectorTerritorial() {
        return interfazSectorTerritorial.getSectoresTerritoriales();
    }

    //Endpoint para dar de baja a un miembro
    @DeleteMapping("sectorTerritorial/eliminar/{id}")
    public String deleteSectorTerritorial(@PathVariable Integer id) {
        interfazSectorTerritorial.deleteSectorTerritorial(id);
        return "El sector territorial fue eliminado correctamente";
    }

    //Endpoint para crear a un nuevo miembro
    @PostMapping("/sectorTerritorial/crear")
    public String saveSectorTerritorial(@RequestBody SectorTerritorial sectorTerritorial) {
        interfazSectorTerritorial.saveSectorTerritorial(sectorTerritorial);
        return "El sector territorial fue creado correctamente";
    }


    //Endpoint para modificar a un usuario
    @PutMapping("/sectorTerritorial/editar/{id}")
    public SectorTerritorial actualizarSectorTerritorial(@PathVariable Integer id, @RequestBody SectorTerritorial sectorTerritorial) throws Exception {
        return interfazSectorTerritorial.modificarSectorTerritorial(id, sectorTerritorial);
    }
}
