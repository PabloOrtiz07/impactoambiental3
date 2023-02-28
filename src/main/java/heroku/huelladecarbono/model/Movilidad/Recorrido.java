package heroku.huelladecarbono.model.Movilidad;

import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table (name="recorrido")
public class Recorrido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public List<Trayecto> trayectos = new ArrayList<>();
    @ManyToOne
    @JoinColumn (name="organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;
    @Column
    private Double peso;
    @Column(columnDefinition = "DATE")
    private String fechaInicio;
    @Column(columnDefinition = "DATE")
    private String fechaFin;
    @Column
    private Boolean estaActivo;

    @Column
    private Integer cantidadDeTrayectos;

    public Recorrido(Organizacion organizacion, Double peso, LocalDate fechaInicio, LocalDate fechaFin, Boolean estaActivo) {
        this.organizacion = organizacion;
        this.fechaInicio = fechaInicio.toString();
        this.fechaFin = fechaFin.toString();
        this.peso = peso;
        this.estaActivo = true;
    }

    public Recorrido(Integer id, List<Trayecto> trayectos, Organizacion organizacion, Double peso, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.trayectos = trayectos;
        this.organizacion = organizacion;
        this.peso = peso;
        this.fechaInicio = fechaInicio.toString();
        this.fechaFin = fechaFin.toString();
        this.estaActivo = true;
    }

    public Recorrido(Organizacion organizacion, Double peso, LocalDate fechaInicio, LocalDate fechaFin) {
        this.organizacion = organizacion;
        this.peso = peso;
        this.fechaInicio = fechaInicio.toString();
        this.fechaFin = fechaFin.toString();
        this.estaActivo = true;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recorrido() {
    }


    public void addTrayecto(Trayecto trayecto){
        this.trayectos.add(trayecto);
    }

    public void removeTrayecto(Trayecto trayecto){
        trayectos.remove(trayecto);
    }

    public Double distanciaTotal() throws Exception {
        Double distanciaTotal = Double.parseDouble("0");
        for (Trayecto trayecto : trayectos){
            distanciaTotal = distanciaTotal + trayecto.distanciaMedia();
        }
        return distanciaTotal;
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "id=" + id +
                ", trayectos=" + trayectos +
                ", organizacion=" + organizacion +
                ", peso=" + peso +
                ", mesInicio=" + fechaInicio +
                ", mesFin=" + fechaFin +
                '}';
    }

}

