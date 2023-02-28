package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import lombok.Getter;

@Getter
public class ResMiembro2 {
    private Integer miembroId;
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private String numDoc;
    private String mail;
    private String telefono;
    private String areaNombre;
    private Integer areaId;

    public ResMiembro2(Miembro miembro, Area area) {
        this.miembroId = miembro.getID();
        this.nombre = miembro.getNombre();
        this.apellido = miembro.getApellido();
        this.tipoDoc = miembro.getTipoDoc();
        this.numDoc = miembro.getNumDoc().toString();
        this.mail = miembro.getMail();
        this.telefono = miembro.getTelefono();
        this.areaNombre = area.getNombre();
        this.areaId = area.getId();
    }
}
