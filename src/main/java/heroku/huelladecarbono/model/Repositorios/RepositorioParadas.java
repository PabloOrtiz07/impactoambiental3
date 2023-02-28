package heroku.huelladecarbono.model.Repositorios;

import heroku.huelladecarbono.model.MedioDeTransporte.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.ParadaController;

import java.util.ArrayList;
import java.util.List;
@Component
public class RepositorioParadas {
  @Autowired
  ParadaController paradabd;
  private static RepositorioParadas instance = new RepositorioParadas();
  private List<Parada> paradas;


  private RepositorioParadas() {
    this.paradas = new ArrayList<>();

  }

  public List<Parada> getParadas() {
    return paradas;
  }

  public void setParadas(List<Parada> paradas) {
    this.paradas = paradas;
  }

  public static RepositorioParadas getRepositorio() {
    return instance;
  }

  public void agregarParada(Parada par){
    this.paradas.add(par);
  }

  public void cargarDeParadaDeBdAlSistema() {
    for(Parada paradaclase : paradabd.getParadas()) {
      Parada parada = new Parada(paradaclase.getNombre(),paradaclase.getUbicacion(),paradaclase.getDistancia());
      this.agregarParada(parada);
    }
  }

}
