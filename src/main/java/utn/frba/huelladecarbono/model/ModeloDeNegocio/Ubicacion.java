package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import javax.persistence.*;


@Getter @Setter
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer idPais;
    @Column
    private String pais;
    @Column
    private Integer idProvincia;
    @Column
    private String provincia;
    @Column
    private Integer idMunicipio;
    @Column
    private String municipio;
    @Column
    private Integer idLocalidad;
    @Column
    private String localidad;
    @Column
    private String calle;
    @Column
    private String altura;


    public Ubicacion(String pais, String provincia, String municipio, String localidad, String calle, String altura) throws Exception {
        APIDistanciaService API = new APIDistanciaService();
        this.pais = pais;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
        this.idPais = API.buscarId("pais", -1, this);
        this.idProvincia = API.buscarId("provincia", idPais, this);
        this.idMunicipio = API.buscarId("municipio", idProvincia, this);
        this.idLocalidad = API.buscarId("localidad", idMunicipio, this);
    }

    public Ubicacion(Integer id, String provincia, String municipio, String localidad, String calle, String altura) throws Exception {
        APIDistanciaService API = new APIDistanciaService();
        this.id = id;
        this.pais = pais;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
        this.idPais = API.buscarId("pais", -1, this);
        this.idProvincia = API.buscarId("provincia", idPais, this);
        this.idMunicipio = API.buscarId("municipio", idProvincia, this);
        this.idLocalidad = API.buscarId("localidad", idMunicipio, this);
    }

    public Ubicacion() {

    }


    public Ubicacion(Integer idPais, String pais, Integer idProvincia, String provincia, Integer idMunicipio, String municipio, Integer idLocalidad, String localidad, String calle, String altura) {
        this.idPais = idPais;
        this.pais = pais;
        this.idProvincia = idProvincia;
        this.provincia = provincia;
        this.idMunicipio = idMunicipio;
        this.municipio = municipio;
        this.idLocalidad = idLocalidad;
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
    }
}
