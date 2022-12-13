package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

@Getter
public class ResMiembroEnEspera {
    private ResMiembro miembro;
    private ResArea area;

    public ResMiembroEnEspera(MiembroEnEspera miembro) {
        this.miembro = new ResMiembro(miembro.getMiembro());
        this.area = new ResArea(miembro.getArea());
    }
}
