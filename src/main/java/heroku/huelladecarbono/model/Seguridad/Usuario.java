package heroku.huelladecarbono.model.Seguridad;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Integer rol;
    @Column
    private Integer idRol;


    public Usuario(String username, String password, Integer rol, Integer idRol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.idRol = idRol;
    }

    public Usuario() {

    }
}
