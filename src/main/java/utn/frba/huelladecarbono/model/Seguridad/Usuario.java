package utn.frba.huelladecarbono.model.Seguridad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.service.ValidadorContraseniasService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Rol> roles;
    private int cantIntentos = 0;
    @OneToOne
    private Miembro miembro;

    private  Boolean estaActivo;

    public Usuario(String username, String password, List<Rol> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



    private void validarCredencialesUser(String user, String psw){
        this.username = user;
        this.password = psw;
        ValidadorContraseniasService.getValidadorContrasenias().validarPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCantIntentos() {
        return cantIntentos;
    }


    public Miembro getMiembro() {
        return miembro;
    }

    public void setCantIntentos(int cantIntentos) {
        this.cantIntentos = cantIntentos;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public void validarLogueo(String password) throws InterruptedException {
        if(this.roles == Arrays.asList(new Rol("ROLE_USER"))) {
            if(this.password != password) {
                cantIntentos += 1;
                TimeUnit.SECONDS.sleep(2 ^ cantIntentos);
            }
        }
    }

    public Usuario() {
    }

    public Usuario(String username, String password, int cantIntentos, Miembro miembro) {
        this.username = username;
        this.password = password;
        this.cantIntentos = cantIntentos;
        this.miembro = miembro;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", cantIntentos=" + cantIntentos +
                ", miembro=" + miembro +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
