package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import lombok.Getter;

@Getter
public class MiembroEnEspera {
    Miembro miembro;
    Area area;

    public MiembroEnEspera(Miembro miembro, Area area) {
        this.miembro = miembro;
        this.area = area;
    }
}
