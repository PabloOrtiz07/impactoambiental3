package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.repository.UbicacionRepository;

import java.util.List;

@Service
public class UbicacionService implements IUbicacionService{
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public List<Ubicacion> getUbicacion() {
        List<Ubicacion> listaUbicacion = ubicacionRepository.findAll();
        return listaUbicacion;
    }

    @Override
    public void saveUbicacion(Ubicacion ubicacion) {
        ubicacionRepository.save(ubicacion);

    }

    @Override
    public void deleteUbicacion(Integer id) {
        ubicacionRepository.deleteById(id);
    }

    @Override
    public Ubicacion findoUbicacion(Integer id) {
       Ubicacion ubicacion = ubicacionRepository.findById(id).orElse(null);
       return ubicacion;
    }



}
