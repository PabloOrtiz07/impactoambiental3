package utn.frba.huelladecarbono.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utn.frba.huelladecarbono.respuestaEndpoint.ResInforme;
import utn.frba.huelladecarbono.service.HandleBars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
     private OrganizacionController orgaCont;

    private Handlebars handlebars = HandleBars.getHandleBars();

    @GetMapping({"/login", "/", "/index"})
    public String login() {return "login";}




    @GetMapping("/miembro/calcularHuella")
    public String calcularHuellaM() {return "MiembroCalculadora";}
    @GetMapping("/miembro/datosPersonales")

    public String datosPersonalesM(){return "MiembroDatosPersonales";}

    @GetMapping("/miembro/organizaciones")
    public String organizacionesM(){return "MiembroOrganizaciones";}

    @GetMapping("/miembro/recorridos")
    public String recorridosM(){return "MiembroRecorridos";}




    //Rutas de vistas de organizacion
/*
    @GetMapping(value="/{idOrganizacion}/areas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> areas(@PathVariable String idOrganizacion) throws Exception {

        Template template = handlebars.compile("/templates/OrgAreas");

        //despues borrar cuando funcione lo de los repos en memoria
        //usar ID de organizacion 1 y 2 para probar

        Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
        Organizacion organizacion2 = new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
        Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
        Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);
        Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
        ArrayList<Double> listaHCPrueba = new ArrayList<>();
        Area area1 = new Area("AreaPrueba1", organizacion1);
        Area area2 = new Area("AreaPrueba2", organizacion1);
        organizacion2.setArea(area1);
        listaHCPrueba.add(100.00);
        HuellaCarbono huellaPrueba = new HuellaCarbono(Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021), 250.00);
        List<HuellaCarbono> hashMapPrueba = new ArrayList<>();
        hashMapPrueba.add(huellaPrueba);
        organizacion1.setID(1);
        organizacion2.setID(2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion2);
        RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion1);

        //borrar arriba

        List<Area> areas = RepositorioOrganizaciones
                .getRepositorio()
                .findOrganizacion(Integer.parseInt(idOrganizacion))
                .getAreas();

        Map<String, Object> model = new HashMap<>();
        model.put("area", areas);
        model.put("organizacionID", idOrganizacion);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }*/

    // ORGANIZACIÓN
    @GetMapping("organizacion/miembros")
    public String orgMiembros(){return "orgMiembros";}

    @GetMapping("/organizacion/calcularHuella")
    public String calcularHuellaO(){return "OrgCalculadora";}

    @GetMapping("/organizacion/contactos")
    public String contactosO(){return "OrgContactos";}

    @GetMapping("/organizacion/datosDeActividad")
    public String datosDeActividadO(){return "OrgDatosDeActividad";}

    @GetMapping("/organizacion/datosInternos")
    public String datosInternosO(){return "OrgDatosInternos";}

    @GetMapping("/organizacion/recomendaciones")
    public String recomendacionesO(){return "OrgRecomendaciones";}

    @GetMapping("/organizacion/reportes")
    public String reportesOrg(){return "OrgReportes";}

    @GetMapping(value="/organizacion/reportesTablas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> reportesOrgTablas() throws Exception{
        Template template = handlebars.compile("/templates/tablaReportesOrg");

        List<ResInforme> hcporst = orgaCont.HCSectores();
        List<ResInforme> hcportipoorg = orgaCont.HCTipoOrg();
        List<ResInforme> hcporprovincias = orgaCont.HCProvincia();
        List<ResInforme> hcporareas = orgaCont.HCPropia(1);



        Map<String, Object> model = new HashMap<>();
        model.put("HCporST", hcporst);
        model.put("HCporTipoOrg", hcportipoorg);
        model.put("HCporProvincias", hcporprovincias);
        model.put("HCporAreas", hcporareas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);

    }
    @GetMapping("/organizacion/recorridos")
    public String recorridosO(){return "OrgRecorridos";}

    @GetMapping({"/organizacion/areas"})
    public String orgAreas(){return "OrgAreas";}


    // AGENTE SECTORIAL

    @GetMapping("/AS/reportes")
    public String reportesAS(){return "AgenteReportes";}

    @GetMapping(value="/AS/reportesTablas", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> reportesASTablas() throws Exception{
        Template template = handlebars.compile("/templates/tablaReportesAS");

        List<ResInforme> hcporst = orgaCont.HCSectores();
        List<ResInforme> hcportipoorg = orgaCont.HCTipoOrg();
        List<ResInforme> hcporprovincias = orgaCont.HCProvincia();

        /*List<HCInforme> listaPruebaST = new ArrayList<>();
        listaPruebaST.add(new HCInforme("laPampa", 89.88));
        List<HCInforme> listaPruebaProv = new ArrayList<>();
        listaPruebaProv.add(new HCInforme("BuenosAires", 922.44));
        listaPruebaProv.add(new HCInforme("cordoba", 94.2));

        hcporst = listaPruebaST;
        hcporprovincias = listaPruebaProv;*/

        Map<String, Object> model = new HashMap<>();
        model.put("HCporST", hcporst);
        model.put("HCporTipoOrg", hcportipoorg);
        model.put("HCporProvincias", hcporprovincias);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);

    }

    @GetMapping("/AS/recomendaciones")
    public String recomendacionesAS(){return "AgenteRecomendaciones";}

}
