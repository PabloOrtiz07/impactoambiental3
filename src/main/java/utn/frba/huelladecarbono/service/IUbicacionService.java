package utn.frba.huelladecarbono.service;

import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    public List<Ubicacion> getUbicacion();

    public void saveUbicacion(Ubicacion ubicacion);

    public void deleteUbicacion(Integer id);

    public Ubicacion findoUbicacion(Integer id);
}
