package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
@Entity
@NoArgsConstructor
public class MedioMotorizado extends Medio {

    @Enumerated(EnumType.STRING)
    private TipoVehiculoMotorizado tipoVehiculoMotorizado;
    @Enumerated(EnumType.STRING)
    private TipoCombustible tipoCombustible;
    @Column
    private String patente;
    @Column
    private String ID = "MM";
    @Column
    private Boolean esServicioContratado;
    @Column
    private String tipoServicio;

    public String getID() {
        return ID;
    }

    public String getTipo() {
        return String.valueOf(tipoVehiculoMotorizado);
    }

    public MedioMotorizado(TipoVehiculoMotorizado tipoVehiculoMotorizado, TipoCombustible tipoCombustible, String patente, Boolean esServicioContratado, String tipoServicio) {
        this.tipoVehiculoMotorizado = tipoVehiculoMotorizado;
        this.tipoCombustible = tipoCombustible;
        this.patente = patente;
        this.esServicioContratado = esServicioContratado;
        this.tipoServicio = tipoServicio;
    }

    public MedioMotorizado(TipoVehiculoMotorizado tipoVehiculoMotorizado, TipoCombustible tipoCombustible, String patente, String ID, Boolean esServicioContratado, String tipoServicio) {
        this.tipoVehiculoMotorizado = tipoVehiculoMotorizado;
        this.tipoCombustible = tipoCombustible;
        this.patente = patente;
        this.ID = ID;
        this.esServicioContratado = esServicioContratado;
        this.tipoServicio = tipoServicio;
    }
}
