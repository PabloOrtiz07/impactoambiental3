package heroku.huelladecarbono.DTO;

import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResOrganizacion {
    Integer id;
    String nombre;
    String razonSocial;
    TipoOrg tipo;
    String pais;
    String provincia;
    String municipio;
    String localidad;
    String calle;
    String altura;
    List<ResArea> areas;

    public ResOrganizacion(Organizacion organizacion) {
        this.id = organizacion.getId();
        this.nombre = organizacion.getNombre();
        this.razonSocial = organizacion.getRazonSocial();
        this.tipo = organizacion.getTipo();
        this.pais = organizacion.getUbicacion().getPais();
        this.provincia = organizacion.getUbicacion().getProvincia();
        this.municipio = organizacion.getUbicacion().getMunicipio();
        this.localidad = organizacion.getUbicacion().getLocalidad();
        this.calle = organizacion.getUbicacion().getCalle();
        this.altura = organizacion.getUbicacion().getAltura();
        this.areas = organizacion.getAreas().stream().map(ResArea::new).collect(Collectors.toList());
    }

}
