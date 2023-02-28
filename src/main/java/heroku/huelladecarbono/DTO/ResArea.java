package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import lombok.Getter;

@Getter
public class ResArea {
    Integer id;
    String nombre;
    Integer organizacionId;

    //crear constructor que reciba un objeto Area
    public ResArea(Area area) {
        this.id = area.getId();
        this.nombre = area.getNombre();
        this.organizacionId = area.getOrganizacion().getId();
    }
}
