package heroku.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.TrayectoController;
import heroku.huelladecarbono.model.Movilidad.Trayecto;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioTrayectos {
  @Autowired
  TrayectoController trayectobd;

  private static RepositorioTrayectos instance = new RepositorioTrayectos();
  private List<Trayecto> trayectos;

  private RepositorioTrayectos(){
    this.trayectos = new ArrayList<>();
  }

  public static RepositorioTrayectos getRepositorio(){
    return instance;
  }

  public List<Trayecto> getTrayectos() {
    return trayectos;
  }

  public void setTrayectos(List<Trayecto> trayectos) {
    this.trayectos = trayectos;
  }

  public void agregarTrayecto (Trayecto trayecto){
    this.trayectos.add(trayecto);
  }

  //TODO probar
  public void cargarDeTrayectoDeBdAlSistema() {
    for(Trayecto trayectoclase : trayectobd.getTrayectos()) {
      Trayecto trayecto = new Trayecto(trayectoclase.getUbicaciones(),trayectoclase.getMedioTransporte());
      this.agregarTrayecto(trayecto);
    }
  }
}

