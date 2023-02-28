package heroku.huelladecarbono.model.Repositorios;

import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.TransportePublicoController;

import java.util.ArrayList;
import java.util.List;
@Component
public class RepositorioTransportesPublicos {
  @Autowired
  TransportePublicoController transportePublicobd;

  private static RepositorioTransportesPublicos instance = new RepositorioTransportesPublicos();
  private List<TransportePublico> lineas;


  private RepositorioTransportesPublicos() {
    this.lineas = new ArrayList<>();

  }

  public List<TransportePublico> getLineas() {
    return lineas;
  }

  public void setLineas(List<TransportePublico> lineas) {
    this.lineas = lineas;
  }

  public static RepositorioTransportesPublicos getRepositorio() {
    return instance;
  }

  public void agregarLinea(TransportePublico tra){
    this.lineas.add(tra);
  }

  //TODO probar
  public void cargarDeTransportePublicoDeBdAlSistema() {
    for(TransportePublico transportePublicoclase : transportePublicobd.getTransportesPublicos()) {
      TransportePublico transportePublico = new TransportePublico(transportePublicoclase.getTipoTransportePublico(),transportePublicoclase.getLinea(),transportePublicoclase.getParadas(),transportePublicoclase.getID());
      this.agregarLinea(transportePublico);
    }
}}
