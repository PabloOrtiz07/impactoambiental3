package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    public List<Ubicacion> getUbicacion();

    public void saveUbicacion(Ubicacion ubicacion);

    public void deleteUbicacion(Integer id);

    public Ubicacion findoUbicacion(Integer id);
}
