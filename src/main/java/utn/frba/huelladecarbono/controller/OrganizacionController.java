package utn.frba.huelladecarbono.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.model.CalculoDeDistancias.Provincia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.repository.MiembroRepository;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;
import utn.frba.huelladecarbono.respuestaEndpoint.*;
import utn.frba.huelladecarbono.service.AreaService;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCOrganizacion;
import utn.frba.huelladecarbono.service.*;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import utn.frba.huelladecarbono.respuestaEndpoint.ResInforme;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("organizaciones/")
public class OrganizacionController {

    @Autowired
    private IOrganizacionService interfazOrganizacion;

    @Autowired
    OrganizacionRepository organizacionRepository;

    @Autowired
    OrganizacionService organizacionService;

    @Autowired
    private  AreaService areaService;

    @Autowired
    private SectorTerritorialController stc;

    @Autowired
    private UbicacionController uc;

    @Autowired
    private IMiembroService interfazMiembro;

    @Autowired
    private MiembroRepository miembroRepository;

    @GetMapping({"/", ""})
    public List<ResOrganizacion> getOrganizaciones(){
        List<ResOrganizacion> res = new ArrayList<>();
        List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones();
        for (Organizacion organizacion : organizaciones) {
            res.add(new ResOrganizacion(organizacion));
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResOrganizacion getOrganizacionPorID(@PathVariable Integer id) {
        return new ResOrganizacion(interfazOrganizacion.findOrganizacion(id));
    }

    public List<Organizacion> getOrganizacionesActivas() {
            return interfazOrganizacion.findOrganizacionByEstadoActivo();
    }

    @GetMapping("/areas/{id}")
    public List<ResArea> getAreas(@PathVariable Integer id) {
        List<ResArea> res = new ArrayList<>();
        List<Area> areas = areaService.findByOrganizacion(id);
        for (Area area : areas) {
            res.add(new ResArea(area));
        }
        return res;
    }

    @GetMapping("/huella/{id}")
    public Double getHuella(@PathVariable String id) {
        return interfazOrganizacion.getHuellaTotal(Integer.parseInt(id));
    }

    @GetMapping("/{id}/contactosWp")
    public List<ResMiembro> getContactosWp(@PathVariable Integer id) {
        List<ResMiembro> res = new ArrayList<>();
        List<Miembro> miembros = organizacionService.findOrganizacion(id).getContactosWP();
        for (Miembro miembro : miembros) {
            res.add(new ResMiembro(miembro));
        }
        return res;
    }

    @GetMapping("/{id}/contactosMail")
    public List<ResMiembro> getContactosMail(@PathVariable Integer id){
        List<ResMiembro> res = new ArrayList<>();
        List<Miembro> miembros = organizacionService.findOrganizacion(id).getContactosMail();
        for (Miembro miembro : miembros) {
            res.add(new ResMiembro(miembro));
        }
        return res;
    }

    //Endpoint para dar de baja a una organizacion, la baja solamente es logica por lo tanto solo se cambia el estado
    @DeleteMapping("/{id}")
    public String deleteOrganizacion(@PathVariable Integer id) {
        interfazOrganizacion.deleteOrganizacion(id);
        return "La organización fue dada de baja correctamente";
    }

    @PostMapping("/crear")
    public String saveOrganizacion(@RequestBody Organizacion organizacion) {
        interfazOrganizacion.saveOrganizacion(organizacion);
        return "La organización fue creada correctamente";
    }

    @PatchMapping("/editarEstado/{id}")
    public void cambiarEstadoOrganizacion(@PathVariable Integer id) {
        interfazOrganizacion.cambiarEstadoOrganizacion(id);
    }

    @PutMapping("/editar/{id}")
    public void actualizarOrganizacion(@PathVariable Integer id, @RequestParam String razonSocial, @RequestParam String tipo, @RequestParam String clasificacion,  @RequestParam Boolean estaActivo, @RequestParam String nombre) {
        Organizacion org = new Organizacion(razonSocial, TipoOrg.valueOf(tipo), null, Clasificacion.valueOf(clasificacion), null, null, null, null, null, null, estaActivo, nombre);
        interfazOrganizacion.modificarOrganizacion(id,org);
    }
    @GetMapping("{organizacionId}/miembros")
    public List<ResMiembro> miembros(@PathVariable Integer organizacionId) {

        List<ResMiembro> res = new ArrayList<>();
        List<Miembro> miembros = interfazMiembro.getMiembros().stream().filter(m -> m.esDeUnaOrganizacion(organizacionId)).collect(Collectors.toList());
        for (Miembro miembro : miembros) {
            res.add(new ResMiembro(miembro));
        }
        return res;
    }

    @GetMapping("{organizacionId}/miembrosEnEspera")
    public List<ResMiembroEnEspera> miembrosEnEspera(@PathVariable Integer organizacionId) {
        List<ResMiembroEnEspera> res = new ArrayList<>();
        List<Area> areas = areaService.findByOrganizacion(organizacionId);
        for (Area area : areas) {
             for (Miembro miembro : area.getMiembrosEnEspera()) {
                 MiembroEnEspera resMiembro = new MiembroEnEspera(miembro, area);
                 res.add(new ResMiembroEnEspera(resMiembro));
             }
        }
        return res;
    }
    @PatchMapping("aceptarMiembro/{organizacionId}/{areaId}/{miembroId}")
    public void aceptarMiembro(@PathVariable Integer organizacionId, @PathVariable Integer areaId, @PathVariable Integer miembroId) throws Exception {
        Miembro miembro = RepositorioMiembros.getRepositorio().findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).aceptarMiembro(miembro);
    }

    @PatchMapping("rechazarMiembro/{organizacionId}/{areaId}/{miembroId}")
    public void rechazarMiembro(@PathVariable Integer organizacionId, @PathVariable Integer areaId, @PathVariable Integer miembroId) {
        Miembro miembro = interfazMiembro.findMiembro(miembroId);
        organizacionRepository.getById(organizacionId).getArea(areaId).rechazarMiembro(miembro);
    }

    @GetMapping("calcularHuella/{org}/{diaI}/{mesI}/{anioI}/{diaF}/{mesF}/{anioF}/")
    public Double calcularHuella(@PathVariable Integer org, @PathVariable Integer diaI, @PathVariable Integer mesI, @PathVariable Integer anioI, @PathVariable Integer diaF, @PathVariable Integer mesF, @PathVariable Integer anioF) {
        LocalDate fechaI = LocalDate.of(anioI, mesI, diaI);
        LocalDate fechaF = LocalDate.of(anioF, mesF, diaF);
        Organizacion organizacion = interfazOrganizacion.findOrganizacion(org);
        return CalculadoraHCOrganizacion.calcularHC(organizacion, fechaI, fechaF);
    }

    //PARA HANDLEBAR - REPORTES
    public List<ResInforme> HCSectores() {
        List<ResInforme> res = new ArrayList<>();
        List<SectorTerritorial> sectores = stc.getSectorTerritorial();
        for (SectorTerritorial sector : sectores) {
            Double hc = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            res.add(new ResInforme(sector.getId().toString(), hc));
        }
        return res;
    }

    public List<ResInforme> HCTipoOrg() {
        List<TipoOrg> tipos = Arrays.stream(TipoOrg.values()).toList();
        List<ResInforme> res = new ArrayList<>();
        for (TipoOrg tipo : tipos) {
            List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones().stream().filter(org -> org.getTipo().equals(tipo)).toList();
            Double hc = 0.0;
            for (Organizacion org : organizaciones) {
                hc += CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(org,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            }
            res.add(new ResInforme(tipo.toString(),hc));
        }
        return res;
    }

    public List<ResInforme> HCProvincia() throws IOException {
        List<Provincia> provincias = Arrays.stream(uc.getProvincias(9)).toList();
        List<ResInforme> res = new ArrayList<>();

        for (Provincia provincia : provincias) {
            Double hc = 0.0;
            List<Organizacion> organizaciones = interfazOrganizacion.getOrganizaciones().stream().filter(org -> org.getUbicacion().getProvincia().equals(provincia.getNombre())).toList();
            for (Organizacion org : organizaciones) {
                hc += CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(org,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            }
            res.add(new ResInforme(provincia.getNombre(), hc));
        }
        return res;
    }

    public List<ResInforme> HCPropia(Integer orgId) {
        List<ResInforme> res = new ArrayList<>();
        List<Area> areas = areaService.findByOrganizacion(orgId);
        for (Area area : areas) {
            Double hc = CalculadoraHCService.getCalculadoraHC().calcularHCArea(area,LocalDate.of(LocalDate.EPOCH.getYear(), 1,1), LocalDate.of(LocalDate.EPOCH.getYear(), 12,31));
            res.add(new ResInforme(area.getNombre(), hc));
        }
        return res;
    }

    @GetMapping("/recorridos/{orgId}")
    public List<ResRecorrido> recorridos(@PathVariable Integer orgId) {
        List<ResRecorrido> res = new ArrayList<>();
        List<Recorrido> recorridos = interfazOrganizacion.findOrganizacion(orgId).getRecorridos();
        for (Recorrido recorrido : recorridos) {
            res.add(new ResRecorrido(recorrido));
        }
        return res;
    }

    @DeleteMapping("/eliminarRecorrido/{orgId}/{recorridoId}")
    public void eliminarRecorrido(@PathVariable Integer orgId, @PathVariable Integer recorridoId) {
        interfazOrganizacion.eliminarRecorrido(orgId, recorridoId);
    }

    @PutMapping("/actualizar")
    public void actualizar(@RequestBody String organizacion) throws Exception {
        interfazOrganizacion.actualizarOrganizacion(organizacion);
    }

    @GetMapping("{organizacionId}/miembros2")
    public List<ResMiembro2> miembros2(@PathVariable Integer organizacionId) {

        List<ResMiembro2> res = new ArrayList<>();
        List<Miembro> miembros = interfazMiembro.getMiembros().stream().filter(m -> m.esDeUnaOrganizacion(organizacionId)).collect(Collectors.toList());
        for (Miembro miembro : miembros) {
            for (Area area : miembro.getAreas()) {
                res.add(new ResMiembro2(miembro, area));
            }
        }
        return res;
    }
}
