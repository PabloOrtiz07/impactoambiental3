package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

@Getter
public class ResMiembro2 {
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private Integer numDoc;
    private String mail;
    private String telefono;
    private String areaNombre;
    private Integer areaId;

    public ResMiembro2(Miembro miembro, Area area) {
        this.nombre = miembro.getNombre();
        this.apellido = miembro.getApellido();
        this.tipoDoc = miembro.getTipoDoc();
        this.numDoc = miembro.getNumDoc();
        this.mail = miembro.getMail();
        this.telefono = miembro.getTelefono();
        this.areaNombre = area.getNombre();
        this.areaId = area.getId();
    }
}
