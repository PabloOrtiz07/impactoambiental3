package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.MiembroController;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositorioMiembros {

  @Autowired
  MiembroController miembrobd;
  private static RepositorioMiembros instance = new RepositorioMiembros();
  private List<Miembro> miembros;

  private RepositorioMiembros() {
    this.miembros = new ArrayList<>();
  }

  public static RepositorioMiembros getRepositorio() {
    if(instance == null) {
      instance = new RepositorioMiembros();
      instance.setMiembros(new ArrayList<>());
    }
    return instance;
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }

  public void setMiembros(List<Miembro> miembros) {
    this.miembros = miembros;
  }


  public void agregarMiembro(Miembro miem){
    this.getRepositorio().getMiembros().add(miem);
  }

  public Miembro findMiembro(Integer id){
    return this.getMiembros().stream()
            .filter(miembro -> miembro.getID() == id)
            .collect(Collectors.toList())
            .get(0);
  }
}