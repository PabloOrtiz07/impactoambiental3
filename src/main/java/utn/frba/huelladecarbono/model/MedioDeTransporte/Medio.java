package utn.frba.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Medio {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String ID;

    abstract public String getID();

    abstract public String getTipo();

    public Medio() {
    }

    public Medio(String ID) {
        this.ID = ID;
    }
}
