package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositorioOrganizaciones {

  @Autowired
  OrganizacionController organizacionbd;

  private static RepositorioOrganizaciones instance;
  private List<Organizacion> organizaciones;

  public List<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  public void setOrganizaciones(List<Organizacion> organizaciones) {
    this.organizaciones = organizaciones;
  }

  public static RepositorioOrganizaciones getRepositorio() {
    if(instance == null) {
      instance = new RepositorioOrganizaciones();
      instance.setOrganizaciones(new ArrayList<>());
    }
    return instance;
  }

  public void agregarOrganizacion(Organizacion org){
    this.getRepositorio().organizaciones.add(org);
  }



  //Se cargan los datos traidos desde la base de datos en el repo
  public void cargarDeOrganizacionesDeBdAlSistema() {

    for (Organizacion organizacionclase : organizacionbd.getOrganizacionesActivas()) {
      Organizacion organizacion = new Organizacion(organizacionclase.getRazonSocial(), organizacionclase.getTipo(), organizacionclase.getClasificacion(), organizacionclase.getContactosMail(), organizacionclase.getContactosWP(),organizacionclase.getEstaActivo());
      this.agregarOrganizacion(organizacion);
    }
  }


  //Devuelve la lista de organizaciones activas, es la que realmente interesa mostrar en la UI
  // metodo alternativo porque el filtrado se hace con los metodos http en organizacionController
   public List<Organizacion> organizacionesActivas(){
    return this.getOrganizaciones().stream().filter(organizacion -> organizacion.estaActiva()).collect(Collectors.toList());
   }

  public Organizacion findOrganizacion(Integer id){
    return this.organizacionesActivas().stream()
            .filter(organizacion -> organizacion.getId() == id)
            .collect(Collectors.toList())
            .get(0);
  }


}