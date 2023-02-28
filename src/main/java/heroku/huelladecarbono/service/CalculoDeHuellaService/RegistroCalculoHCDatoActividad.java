package heroku.huelladecarbono.service.CalculoDeHuellaService;


import javax.persistence.*;

import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "datosActividad")
public class RegistroCalculoHCDatoActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String periodicidad;
    private String periodoImputacion;
    @ManyToOne
    @JoinColumn (name="organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;
    private Double valor;

    public RegistroCalculoHCDatoActividad(String periodicidad, String periodoImputacion, Organizacion organizacion, Double valor) {
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
        this.organizacion = organizacion;
        this.valor = valor;
    }

    public RegistroCalculoHCDatoActividad() {

    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }



}
