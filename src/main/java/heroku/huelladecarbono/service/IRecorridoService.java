package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.Movilidad.Recorrido;

import java.util.List;

public interface IRecorridoService {

    public List<Recorrido> getRecorridos();

    public void saveRecorrido(Recorrido recorrido);

    public void deleteRecorrido(Integer id);

    public Recorrido findRecorrido(Integer id);

    public void cambiarEstadoRecorrico(Integer id);
    Recorrido modificarRecorrido(Integer id, Recorrido recorrido);
}
