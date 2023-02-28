package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.Movilidad.Trayecto;
import heroku.huelladecarbono.repository.TrayectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TrayectoService implements  ITrayectoService {

    @Autowired
    private TrayectoRepository trayectoRepository;


    @Override
    public List<Trayecto> getTrayectos() {
        List <Trayecto> listaTrayectos = trayectoRepository.findAll();

        return listaTrayectos;
    }

    @Override
    public void saveTrayecto(Trayecto trayecto) {
        trayectoRepository.save(trayecto);
    }

    @Override
    public void deleteTrayecto(Integer id) {
        trayectoRepository.deleteById(id);
    }

    @Override
    public Trayecto findTrayecto(Integer id) {
        Trayecto trayecto = trayectoRepository.findById(id).orElse(null);
        return trayecto;
    }
}
