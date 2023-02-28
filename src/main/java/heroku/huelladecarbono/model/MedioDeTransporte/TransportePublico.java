package heroku.huelladecarbono.model.MedioDeTransporte;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class TransportePublico extends Medio {
    @Enumerated(EnumType.STRING)
    private TipoTransportePublico tipoTransportePublico;
    @Column
    private String linea;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<Parada> paradas;
    @Column
    private String ID = "TP";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public TransportePublico() {
    }

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas, String ID) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        this.paradas = paradas;
        this.ID = ID;
    }

    public TransportePublico(TipoTransportePublico tipo, String linea, ArrayList<Parada> list){
        this.tipoTransportePublico = tipo;
        this.linea = linea;
        this.paradas = list;
    }

    public String getTipo() {
        return String.valueOf(tipoTransportePublico);
    }

    public void setTipo(TipoTransportePublico tipoTransportePublico) {
        this.tipoTransportePublico = tipoTransportePublico;
    }
    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(ArrayList<Parada> paradas) {
        this.paradas = paradas;
    }

}
