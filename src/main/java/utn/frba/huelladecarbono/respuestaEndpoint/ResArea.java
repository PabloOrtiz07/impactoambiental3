package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;

import javax.persistence.criteria.CriteriaBuilder;

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
