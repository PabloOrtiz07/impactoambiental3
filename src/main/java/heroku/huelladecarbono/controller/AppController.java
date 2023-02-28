package heroku.huelladecarbono.controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.DTO.ResInforme;
import heroku.huelladecarbono.service.HandleBars;

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
