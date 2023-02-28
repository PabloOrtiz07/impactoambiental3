package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.DTO.ResMiembro;
import heroku.huelladecarbono.DTO.ResRecorrido;
import heroku.huelladecarbono.service.AreaService;
import heroku.huelladecarbono.service.CalculoDeHuellaServiceV2.Calculadora;
import heroku.huelladecarbono.service.IMiembroService;
import heroku.huelladecarbono.service.IOrganizacionService;

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
        Organizacion organizacion = interfazOrganizacion.findOrganizacion(orgId);
        Double huella = Calculadora.calcularHCOrganizacion(organizacion, fechaI, fechaF);
        Double impacto = Calculadora.calcularHCMiembro(fechaI, fechaF, interfazMiembro.findMiembro(miembroId));
        System.out.println("Huella: " + huella);
        System.out.println("Impacto: " + impacto);
        List<Double> resultado = new ArrayList<>();

        resultado.add(Math.round(huella * 100.0) / 100.0);
        resultado.add(Math.round(impacto * 100.0) / 100.0);
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

    @GetMapping("/eliminarRecorrido/{miembroId}/{recorridoId}")
    public void eliminarRecorrido(@PathVariable Integer miembroId, @PathVariable Integer recorridoId) {
        interfazMiembro.eliminarRecorrido(miembroId, recorridoId);
    }

    @GetMapping("/desvincular/{areaId}/{miembroId}")
    public void desvincularMiembro(@PathVariable Integer areaId, @PathVariable Integer miembroId) {
        interfazMiembro.desvincular(miembroId, areaId);
    }

}
