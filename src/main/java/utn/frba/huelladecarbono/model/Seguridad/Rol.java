package utn.frba.huelladecarbono.model.Seguridad;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Rol {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  @Column
  private String rol;

  public Rol(String role_user) {

  }

  public Rol() {

  }
}
