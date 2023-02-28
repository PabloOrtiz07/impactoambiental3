package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.DTO.*;
import heroku.huelladecarbono.model.CalculoDeDistancias.Provincia;
import heroku.huelladecarbono.model.ModeloDeNegocio.*;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.repository.MiembroRepository;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import heroku.huelladecarbono.service.IMiembroService;
import heroku.huelladecarbono.service.IOrganizacionService;
import heroku.huelladecarbono.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.service.AreaService;
import heroku.huelladecarbono.service.CalculoDeHuellaServiceV2.Calculadora;
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
    public List<ResMiembro> getContactosMail(@PathVariable Integer id) {
        List<ResMiembro> res = new ArrayList<>();
        List<Miembro> miembros = organizacionService.findOrganizacion(id).getContactosMail();
        for (Miembro miembro : miembros) {
            res.add(new ResMiembro(miembro));
        }
        return res;
    }

    @GetMapping("{id}/agregarContactoWpp/{miembroId}")
    public void agregarContactoWpp(@PathVariable Integer id, @PathVariable Integer miembroId) {
        Organizacion organizacion = organizacionService.findOrganizacion(id);
        Miembro miembro = interfazMiembro.findMiembro(miembroId);
        organizacion.agregarContactoWP(miembro);
        organizacionRepository.save(organizacion);
    }

    @GetMapping("{id}/agregarContactoMail/{miembroId}")
    public void agregarContactoMail(@PathVariable Integer id, @PathVariable Integer miembroId) {
        Organizacion organizacion = organizacionService.findOrganizacion(id);
        Miembro miembro = interfazMiembro.findMiembro(miembroId);
        organizacion.agregarContactoMail(miembro);
        organizacionRepository.save(organizacion);
    }

    @GetMapping("/{id}/eliminarContactoWpp/{miembroId}")
    public void eliminarContactoWpp(@PathVariable Integer id, @PathVariable Integer miembroId) {
        Organizacion organizacion = organizacionService.findOrganizacion(id);
        Miembro miembro = interfazMiembro.findMiembro(miembroId);
        organizacion.eliminarContactoWP(miembro);
        organizacionRepository.save(organizacion);
    }

    @GetMapping("/{id}/eliminarContactoMail/{miembroId}")
    public void eliminarContactoMail(@PathVariable Integer id, @PathVariable Integer miembroId) {
        Organizacion organizacion = organizacionService.findOrganizacion(id);
        Miembro miembro = interfazMiembro.findMiembro(miembroId);
        organizacion.eliminarContactoMail(miembro);
        organizacionRepository.save(organizacion);
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
    @GetMapping("aceptarMiembro/{areaId}/{miembroId}")
    public void aceptarMiembro(@PathVariable Integer areaId, @PathVariable Integer miembroId) throws Exception {
        Miembro miembro = miembroRepository.findById(miembroId).orElseThrow(() -> new Exception("No existe el miembro"));
        Area area = areaService.findById(areaId);
        area.aceptarMiembro(miembro);
        areaService.saveArea(area);

        miembro.agregarArea(area);
        miembroRepository.save(miembro);
    }

    @GetMapping("rechazarMiembro/{areaId}/{miembroId}")
    public void rechazarMiembro(@PathVariable Integer areaId, @PathVariable Integer miembroId) throws Exception {
        Miembro miembro = miembroRepository.findById(miembroId).orElseThrow(() -> new Exception("No existe el miembro"));
        Area area = areaService.findById(areaId);
        area.rechazarMiembro(miembro);
        areaService.saveArea(area);
    }

    @GetMapping("calcularHuella/{org}/{diaI}/{mesI}/{anioI}/{diaF}/{mesF}/{anioF}/")
    public Double calcularHuella(@PathVariable Integer org, @PathVariable Integer diaI, @PathVariable Integer mesI, @PathVariable Integer anioI, @PathVariable Integer diaF, @PathVariable Integer mesF, @PathVariable Integer anioF) throws Exception {
        System.out.println(anioF);
        LocalDate fechaI = LocalDate.of(anioI, mesI, diaI);
        LocalDate fechaF = LocalDate.of(anioF, mesF, diaF);
        Organizacion organizacion = interfazOrganizacion.findOrganizacion(org);

        return Math.round(Calculadora.calcularHCOrganizacion(organizacion, fechaI, fechaF) * 100.0) / 100.0;
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
