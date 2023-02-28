package heroku.huelladecarbono.controller;

import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.repository.MiembroRepository;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.repository.RecorridoRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import heroku.huelladecarbono.DTO.ResRecorrido;
import heroku.huelladecarbono.service.IRecorridoService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecorridoController {

    @Autowired
    private IRecorridoService interfazRecorrido;
    @Autowired
    private OrganizacionRepository organizacionRepository;
    @Autowired
    private RecorridoRepository recorridoRepository;
    @Autowired
    private MiembroRepository miembroRepository;

    @GetMapping("/recorridos/{orgId}")
    public List<ResRecorrido> getRecorridos(@PathVariable Integer orgId) {
        List<Recorrido> recorridos = interfazRecorrido.getRecorridos().stream().filter(recorrido -> recorrido.getOrganizacion().getId() == orgId).toList();
        List<ResRecorrido> res = new ArrayList<>();
        for (Recorrido recorrido : recorridos) {
            res.add(new ResRecorrido(recorrido));
        }
        return res;
    }

    @GetMapping("/recorrido/eliminar/{id}")
    public String deleteRecorrido(@PathVariable Integer id) {
        interfazRecorrido.deleteRecorrido(id);
        return "El recorrido fue eliminado correctamente";
    }

    @PostMapping("/recorrido/agregar/{orgId}")
    public void createRecorrido(@RequestBody String recorridoJson, @PathVariable Integer orgId) throws ParseException {
       JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(recorridoJson);
        Recorrido recorrido = new Recorrido();
         recorrido.setNombre((String) jObject.get("nombre"));
        recorrido.setOrganizacion(organizacionRepository.getById(orgId));
        recorrido.setCantidadDeTrayectos((Integer) jObject.get("trayectos"));
        recorrido.setEstaActivo(true);
        recorridoRepository.save(recorrido);
    }

    @PostMapping("recorrido/agregarMiembro/{miembroId}")
    public void agregarRecorridoMiembro(@PathVariable Integer miembroId, @RequestBody String recorridoStr) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(recorridoStr);
        Recorrido recorrido = recorridoRepository.getById((Integer) jObject.get("idRecorrido"));
        recorrido.setPeso(Double.parseDouble((String) jObject.get("peso")));
        recorrido.setFechaInicio((String) jObject.get("fechaDeInicio"));
        recorrido.setFechaFin((String) jObject.get("fechaDeFin"));
        recorridoRepository.save(recorrido);

        Miembro miembro = miembroRepository.getById(miembroId);
        miembro.addRecorrido(recorrido);
        miembroRepository.save(miembro);
    }

    @GetMapping("recorrido/miembro/{miembroId}")
    public List<ResRecorrido> getRecorridosMiembro(@PathVariable Integer miembroId) {
        List<Recorrido> recorridos = miembroRepository.getById(miembroId).getRecorridos();
        List<ResRecorrido> res = new ArrayList<>();
        for (Recorrido recorrido : recorridos) {
            res.add(new ResRecorrido(recorrido));
        }
        return res;
    }

    @GetMapping("recorrido/miembro/eliminar/{miembroId}/{recorridoId}")
    public void eliminarRecorridoMiembro(@PathVariable Integer miembroId, @PathVariable Integer recorridoId) {
        Miembro miembro = miembroRepository.getById(miembroId);
        miembro.eliminarRecorrido(recorridoRepository.getById(recorridoId));
        miembroRepository.save(miembro);
    }
}

























