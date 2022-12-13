package utn.frba.huelladecarbono.model.CalculoDeDistancias;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter @Entity
public class Localidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String codPostal;

    @ManyToOne
    private Municipio municipio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Localidad() {
    }

    public Localidad(Integer id, String nombre, String codPostal, Municipio municipio) {
        this.id = id;
        this.nombre = nombre;
        this.codPostal = codPostal;
        this.municipio = municipio;
    }
}
