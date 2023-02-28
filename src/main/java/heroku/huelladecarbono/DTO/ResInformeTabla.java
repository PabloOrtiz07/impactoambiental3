package heroku.huelladecarbono.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResInformeTabla {
    List<ResInforme> lista;

    public ResInformeTabla(List<ResInforme> informe) {
        this.lista = informe;
    }
}
