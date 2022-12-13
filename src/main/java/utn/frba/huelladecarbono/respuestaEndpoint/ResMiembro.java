package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResMiembro {
    private String nombre;
    private String apellido;
    private String tipoDoc;
    private Integer numDoc;
    private String mail;
    private String telefono;
    private List<ResArea> areas;

    public ResMiembro(Miembro miembro) {
        this.nombre = miembro.getNombre();
        this.apellido = miembro.getApellido();
        this.tipoDoc = miembro.getTipoDoc();
        this.numDoc = miembro.getNumDoc();
        this.mail = miembro.getMail();
        this.telefono = miembro.getTelefono();
        this.areas = new ArrayList<>();
        miembro.getAreas().forEach(area -> this.areas.add(new ResArea(area)));
    }
}
