package utn.frba.huelladecarbono.model.MedioDeTransporte;

import utn.frba.huelladecarbono.model.CalculoDeDistancias.Distancia;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table (name="parada")
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @ManyToOne
    @JoinColumn (name="ubicacion_id",referencedColumnName = "id")
    private Ubicacion ubicacion;
    @ManyToOne
    @JoinColumn (name="distancia",referencedColumnName = "id")
    private Distancia distancia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parada() {
    }

    public Parada(String nom, Ubicacion ubi){
        this.nombre = nom;
        this.ubicacion = ubi;
    }

    public Parada(String nombre, Ubicacion ubicacion, Distancia distanciaAProximaParada) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.distancia = distanciaAProximaParada;
    }

    public Double distancaAProximaParada() {
        if (distancia.getValor()!= 0) {
            return distancia.getValor();
        }
        else{
            throw new RuntimeException("No hay siguiente parada (Parada Terminal)");
        }
    }

    @Override
    public String toString() {
        return "Parada{" +
                "ID='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ubicacion=" + ubicacion +
                ", distanciaAProximaParada=" + distancia +
                '}';
    }
}
