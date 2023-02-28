package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.repository.AreaRepository;
import heroku.huelladecarbono.repository.MiembroRepository;
import heroku.huelladecarbono.repository.RecorridoRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService implements IMiembroService {

    @Autowired
    private MiembroRepository miembroRepository;
    @Autowired
    private RecorridoRepository recorridoRepository;
    @Autowired
    private AreaRepository areaRepository;


    @Override
    public List<Miembro> getMiembros() {
        List <Miembro> listaMiembros = miembroRepository.findAll();

        return listaMiembros;
    }

    @Override
    public void saveMiembro(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    @Override
    public void deleteMiembro(Integer id) {
        miembroRepository.deleteById(id);
    }

    @Override
    public Miembro findMiembro(Integer id) {
        Miembro miembro = miembroRepository.findById(id).orElse(null);
        return miembro;
    }

    public Miembro modificarMiembro(Integer id, String miembroJson) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(miembroJson);
        Miembro miembroActualizado = this.findMiembro(id);
        miembroActualizado.setNombre((String) jObject.get("nombre"));
        miembroActualizado.setApellido((String) jObject.get("apellido"));
        miembroActualizado.setTipoDoc((String) jObject.get("tipoDoc"));
        miembroActualizado.setNumDoc(Integer.parseInt((String) jObject.get("numDoc")));
        miembroActualizado.setMail((String) jObject.get("email"));
        miembroActualizado.setTelefono((String) jObject.get("telefono"));
        this.saveMiembro(miembroActualizado);
        return miembroActualizado;
        }

    @Override
    public void cambiarEstadoMiembro(Integer id) {
        Miembro miembro = findMiembro(id);
        miembro.setEstaActivo(false);

        this.saveMiembro(miembro);
    }

    @Override
    public void eliminarArea(Integer miembroId, Integer areaId) {
        Miembro miembro = findMiembro(miembroId);
        miembro.eliminarArea(areaId);
        this.saveMiembro(miembro);
    }

    @Override
    public void eliminarRecorrido(Integer miembroId, Integer recorridoId) {
        Miembro miembro = findMiembro(miembroId);
        Recorrido recorrido = recorridoRepository.getById(recorridoId);
        miembro.eliminarRecorrido(recorrido);
        this.saveMiembro(miembro);
    }

    @Override
    public void desvincular(Integer miembroId, Integer areaId) {
        Miembro miembro = findMiembro(miembroId);
        Area area = areaRepository.getById(areaId);
        miembro.eliminarArea(areaId);
        area.desvincularMiembro(miembroId);
        miembroRepository.save(miembro);
        areaRepository.save(area);
    }
}