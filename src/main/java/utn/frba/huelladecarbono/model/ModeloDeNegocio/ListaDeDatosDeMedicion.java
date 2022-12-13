package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class ListaDeDatosDeMedicion {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private LocalDate fecha;
    @OneToMany
    private List<DatoDeMedicion> datosDeMedicion = new ArrayList<>();



}
