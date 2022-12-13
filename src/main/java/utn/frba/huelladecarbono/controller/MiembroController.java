package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.respuestaEndpoint.ResMiembro;
import utn.frba.huelladecarbono.respuestaEndpoint.ResRecorrido;
import utn.frba.huelladecarbono.service.AreaService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCMiembro;
import utn.frba.huelladecarbono.service.IMiembroService;
import utn.frba.huelladecarbono.service.IOrganizacionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/miembro")
public class MiembroController {

    @Autowired
    private IMiembroService interfazMiembro;

    @Autowired
    OrganizacionController orgController;

    @Autowired
    IOrganizacionService interfazOrganizacion;

    @Autowired
    private AreaService areaService;

    @GetMapping("/{id}")
    public ResMiembro getMiembroPorID(@PathVariable Integer id) throws Exception {
        return new ResMiembro(interfazMiembro.findMiembro(id));
    }
    @GetMapping("/")
    public List<ResMiembro> getMiembros(){
        List<ResMiembro> miembros = new ArrayList<>();
        interfazMiembro.getMiembros().forEach(miembro -> miembros.add(new ResMiembro(miembro)));
        return miembros;
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteMiembro(@PathVariable Integer id) {
        interfazMiembro.cambiarEstadoMiembro(id);
        return "El miembro fue eliminado correctamente";
    }

    //Endpoint para crear a un nuevo miembro
    @PostMapping("/crear")
    public String saveMiembro(@RequestBody Miembro miembro){
        interfazMiembro.saveMiembro(miembro);
        return "El miembro fue creado correctamente";
    }

    @PatchMapping("/editarEstado/{id}")
    public void cambiarEstadoMiembro(@PathVariable Integer id){
        interfazMiembro.cambiarEstadoMiembro(id);
        Miembro miembro = interfazMiembro.findMiembro(id);
    }
    //Endpoint para modificar a un usuario
    @PutMapping("/editar/{id}")
    public void actualizarMiembro(@PathVariable Integer id, @RequestBody String miembro) throws Exception {
        interfazMiembro.modificarMiembro(id,miembro);
    }

    @GetMapping("/calcularHuella/{miembroId}/{diaI}/{mesI}/{anioI}/{diaF}/{mesF}/{anioF}/{orgId}")
    public List<Double> calcularHuella(@PathVariable Integer miembroId, @PathVariable Integer diaI, @PathVariable Integer mesI, @PathVariable Integer anioI, @PathVariable Integer diaF, @PathVariable Integer mesF, @PathVariable Integer anioF, @PathVariable Integer orgId) throws Exception {
        LocalDate fechaI = LocalDate.of(anioI, mesI, diaI);
        LocalDate fechaF = LocalDate.of(anioF, mesF, diaF);
        Double huella = CalculadoraHCMiembro.calcularHC(interfazMiembro.findMiembro(miembroId), fechaI, fechaF, interfazOrganizacion.findOrganizacion(orgId));
        Double impacto = CalculadoraHCMiembro.calcularImpactoIndividual(interfazMiembro.findMiembro(miembroId),interfazOrganizacion.findOrganizacion(orgId), fechaI, fechaF);
        List<Double> resultado = new ArrayList<>();
        resultado.add(huella);
        resultado.add(impacto);
        return resultado;
    }

    @PostMapping("/solicitarSerParte/{orgId}/{areaId}/{miembroId}")
    public void solicitarSerParte(@PathVariable Integer orgId, @PathVariable Integer areaId, @PathVariable Integer miembroId) {
        areaService.solicitarSerParte(areaId, miembroId);
    }

    @GetMapping("{miembroId}/recorridos")
    public List<ResRecorrido> getRecorridos(@PathVariable Integer miembroId) {
        List<Recorrido> recorridos = interfazMiembro.findMiembro(miembroId).getRecorridos();
        List<ResRecorrido> resRecorridos = new ArrayList<>();
        recorridos.forEach(recorrido -> resRecorridos.add(new ResRecorrido(recorrido)));
        return resRecorridos;
    }

    @GetMapping("/eliminarArea/{miembroId}/{areaId}")
    public void eliminarArea(@PathVariable Integer miembroId, @PathVariable Integer areaId) {
        interfazMiembro.eliminarArea(miembroId, areaId);
    }

    @DeleteMapping("/eliminarRecorrido/{miembroId}/{recorridoId}")
    public void eliminarRecorrido(@PathVariable Integer miembroId, @PathVariable Integer recorridoId) {
        interfazMiembro.eliminarRecorrido(miembroId, recorridoId);
    }

    @GetMapping("/devincular/{areaId}/{miembroId}")
    public void desvincularMiembro(@PathVariable Integer areaId, @PathVariable Integer miembroId) {
        interfazMiembro.desvincular(miembroId, areaId);
    }

}
