package heroku.huelladecarbono.service.CalculoDeHuellaService;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    LocalDate mes;
    @Column
    Double hc;

    public Registro(LocalDate mes, Double hc) {
        this.mes = mes;
        this.hc = hc;
    }

    public Registro() {}
}
