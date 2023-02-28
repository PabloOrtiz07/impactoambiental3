package heroku.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class DatoDeMedicion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private  String actividad;
    @Column
    private  String tipoDeConsumo;
    @Column
    private String valor;
    @Column
    private  String periodicidad;
    @Column
    private String periodoImputacion;
    @Column
    private String unidad;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HuellaCarbono> huellasCarbono = new ArrayList<>();

    public DatoDeMedicion() {
    }

    public DatoDeMedicion(Integer id, String actividad, String tipoDeConsumo, String valor, String periodicidad, String periodoImputacion, String unidad) {
        this.id = id;
        this.actividad = actividad;
        this.tipoDeConsumo = tipoDeConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
        this.unidad = unidad;
    }

    public DatoDeMedicion(String actividad, String unidad, String tipoDeConsumo, String valor, String periodicidad, String periodoImputacion) {
            this.actividad = actividad;
            this.tipoDeConsumo = tipoDeConsumo;
            this.valor = valor;
            this.periodicidad = periodicidad;
            this.periodoImputacion = periodoImputacion;
            this. unidad = unidad;
        }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTipoDeConsumo() {
        return tipoDeConsumo;
    }

    public void setTipoDeConsumo(String tipoDeConsumo) {
        this.tipoDeConsumo = tipoDeConsumo;
    }

    public Object getValor() {
            if(this.tipoDeConsumo != "Categoria de producto transportado" && this.tipoDeConsumo !=
                    "Medio de Transporte") return Double.parseDouble(valor);
            else return valor;
    }

    public void agregarHuella(HuellaCarbono huella){
        this.huellasCarbono.add(huella);
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPeriodoImputacion() {
        return periodoImputacion;
    }

    public void setPeriodoImputacion(String periodoImputacion) {
        this.periodoImputacion = periodoImputacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

}


