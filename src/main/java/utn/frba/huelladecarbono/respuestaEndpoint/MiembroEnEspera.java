package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

@Getter
public class MiembroEnEspera {
    Miembro miembro;
    Area area;

    public MiembroEnEspera(Miembro miembro, Area area) {
        this.miembro = miembro;
        this.area = area;
    }
}
