package heroku.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
@Entity
public class MedioNoMotorizado extends Medio {
    @Enumerated(EnumType.STRING)
    private TipoMedioNoMotorizado tipo;

    public String getTipo() {
        return String.valueOf(tipo);
    }
    @Column
    private String ID = "MNM";

    public Double getFE() {
        return 0.0;
    }

    public MedioNoMotorizado(TipoMedioNoMotorizado tipo) {
        this.tipo = tipo;
    }

    public MedioNoMotorizado() {}
}
