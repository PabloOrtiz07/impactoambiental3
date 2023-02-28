package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.MedioDeTransporte.Parada;
import heroku.huelladecarbono.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService implements IParadaService{

    @Autowired
    private ParadaRepository paradaRepository;

    @Override
    public List<Parada> getParadas() {
        List <Parada> listaParadas = paradaRepository.findAll();

        return listaParadas;
    }

    @Override
    public void saveParada(Parada parada) {
        paradaRepository.save(parada);
    }

    @Override
    public void deleteParada(Integer id) {
        paradaRepository.deleteById(id);
    }

    @Override
    public Parada findParada(Integer id) {
        Parada parada = paradaRepository.findById(id).orElse(null);
        return parada;
    }
}

