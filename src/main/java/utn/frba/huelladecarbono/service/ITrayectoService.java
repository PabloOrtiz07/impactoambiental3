package utn.frba.huelladecarbono.service;


import utn.frba.huelladecarbono.model.Movilidad.Trayecto;

import java.util.List;

public interface ITrayectoService {

    public List<Trayecto> getTrayectos();

    public void saveTrayecto(Trayecto trayecto);

    public void deleteTrayecto(Integer id);

    public Trayecto findTrayecto(Integer id);
}
