package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.repository.RecorridoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecorridoService implements IRecorridoService{

    @Autowired
    private RecorridoRepository recorridoRepository;

    @Override
    public List<Recorrido> getRecorridos() {
        List <Recorrido> listaRecorridos = recorridoRepository.findAll();

        return listaRecorridos;
    }

    @Override
    public void saveRecorrido(Recorrido recorrido) {
        recorridoRepository.save(recorrido);
    }

    @Override
    public void deleteRecorrido(Integer id) {
        recorridoRepository.deleteById(id);
    }

    @Override
    public Recorrido findRecorrido(Integer id) {
        Recorrido recorrido = recorridoRepository.findById(id).orElse(null);
        return recorrido;
    }

    @Override
    public Recorrido modificarRecorrido(Integer id, Recorrido recorrido) {
        Recorrido recorridoActualizado = this.findRecorrido(id);
        recorridoActualizado.setEstaActivo(recorrido.getEstaActivo());
        recorridoActualizado.setOrganizacion(recorrido.getOrganizacion());
        recorridoActualizado.setFechaInicio(recorrido.getFechaInicio());
        recorridoActualizado.setFechaFin(recorrido.getFechaFin());
        recorridoActualizado.setPeso(recorrido.getPeso());
        recorridoActualizado.setTrayectos(recorrido.getTrayectos());
        this.saveRecorrido(recorridoActualizado);
        return recorridoActualizado;
        }

    @Override
    public void cambiarEstadoRecorrico(Integer id) {
       Recorrido recorrido = findRecorrido(id);
        recorrido.setEstaActivo(false);
        this.saveRecorrido(recorrido);
    }
    }

