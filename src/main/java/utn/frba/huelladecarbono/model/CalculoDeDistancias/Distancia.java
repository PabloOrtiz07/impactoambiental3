package utn.frba.huelladecarbono.model.CalculoDeDistancias;

import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="distancia")
public class Distancia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Double valor;
    @Column
    private String unidad;

    @OneToMany(mappedBy = "distancia",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Parada> paradas = new ArrayList<>();

    public Double getValor() {return valor;}
    public String getUnidad() {return unidad;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
