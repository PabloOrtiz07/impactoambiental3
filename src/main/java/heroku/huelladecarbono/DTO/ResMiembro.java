package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResMiembro {
    private Integer id;
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private String numDoc;
    private String mail;
    private String telefono;
    private List<ResArea> areas;

    public ResMiembro(Miembro miembro) {
        this.id = miembro.getID();
        this.nombre = miembro.getNombre();
        this.apellido = miembro.getApellido();
        this.tipoDoc = miembro.getTipoDoc();
        this.numDoc = miembro.getNumDoc().toString();
        this.mail = miembro.getMail();
        this.telefono = miembro.getTelefono();
        this.areas = new ArrayList<>();
        miembro.getAreas().forEach(area -> this.areas.add(new ResArea(area)));
    }
}
