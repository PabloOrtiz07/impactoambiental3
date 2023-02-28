package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.MedioDeTransporte.Parada;

import java.util.List;

public interface IParadaService {

    public List<Parada> getParadas();

    public void saveParada(Parada parada);

    public void deleteParada(Integer id);

    public Parada findParada(Integer id);
}
