package heroku.huelladecarbono.model.ModeloDeNegocio;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class HuellaCarbono {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaIni;
    @Column(columnDefinition = "DATE")
    private LocalDate fechaFin;
    @Column
    private Double huella;

    public HuellaCarbono(LocalDate fechaIni, LocalDate fechaFin, Double huella) {
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.huella = huella;
    }

    public HuellaCarbono() {

    }
}
