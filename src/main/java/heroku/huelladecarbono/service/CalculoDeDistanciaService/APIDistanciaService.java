package heroku.huelladecarbono.service.CalculoDeDistanciaService;

import heroku.huelladecarbono.model.CalculoDeDistancias.*;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import heroku.huelladecarbono.model.UserExceptions.BadResponseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.core.Response;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class APIDistanciaService {

    private Integer buscarIdLocalidad(Ubicacion ubicacion) throws Exception {
        Integer idPais      = this.buscarId("pais", -1, ubicacion);
        Integer idProvincia = this.buscarId("provincia", idPais     , ubicacion);
        Integer idMunicipio = this.buscarId("municipio", idProvincia, ubicacion);
        return this.buscarId("localidad", idMunicipio, ubicacion);
    }

    public Integer buscarId(String variable, Integer idSuperior, Ubicacion ubicacion) throws Exception {
        Integer id = -1;
        WebClient client = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        switch (variable) {
            case "pais":
                client = WebClient.create("https://ddstpa.com.ar/api/paises?offset=1");
                break;
            case "provincia":
                client = WebClient.create("https://ddstpa.com.ar/api/provincias?offset=1&paisId=" + idSuperior);
                break;
            case "municipio":
                client = WebClient.create("https://ddstpa.com.ar/api/municipios?offset=1&provinciaId=" + idSuperior);
                break;
            case "localidad":
                client = WebClient.create("https://ddstpa.com.ar/api/localidades?offset=1&municipioId=" + idSuperior);
                break;
        }

        assert client != null;
        Response response = client
                .header("Content-Type", "application/json")
                .authorization(this.token())
                .get();

        Integer status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        if (status == 200) {
            switch (variable) {
                case "pais":
                    Pais[] paises = mapper.readValue(responseBody, Pais[].class);
                    for(Pais pais : paises) {
                        if(Objects.equals(pais.getNombre(), ubicacion.getPais())) {
                            id = pais.getId();
                            break;
                        }
                    }
                    break;
                case "provincia":
                    Provincia[] provincias = mapper.readValue(responseBody, Provincia[].class);
                    for(Provincia provincia : provincias) {
                        if(Objects.equals(provincia.getNombre(), ubicacion.getProvincia())) {
                            id = provincia.getId();
                            break;
                        }
                    }
                    break;
                case "municipio":
                    Municipio[] municipios = mapper.readValue(responseBody, Municipio[].class);
                    for(Municipio municipio : municipios) {
                        if(Objects.equals(municipio.getNombre(), ubicacion.getMunicipio())) {
                            id = municipio.getId();
                            break;
                        }
                    }
                    break;
                case "localidad":
                    Localidad[] localidades = mapper.readValue(responseBody, Localidad[].class);
                    for(Localidad localidad : localidades) {
                        if(Objects.equals(localidad.getNombre(), ubicacion.getLocalidad())) {
                            id = localidad.getId();
                            break;
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + variable);
            }

            if(id == -1) {
                System.out.println(ubicacion.getPais());
                System.out.println(ubicacion.getProvincia());
                System.out.println(ubicacion.getLocalidad());
                System.out.println(ubicacion.getMunicipio());
                System.out.println(ubicacion.getCalle());
                System.out.println(ubicacion.getAltura());
                System.out.println(ubicacion.getAltura());
                throw new Exception("id no encontrado");
            } else {
                return id;
            }

        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada de búsqueda de " + variable);
        }
    }

    public Double medirDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2) throws IOException {
        int idLocalidadOrigen  = ubicacion1.getIdLocalidad();
        int idLocalidadDestino = ubicacion2.getIdLocalidad();

        WebClient clientDistancia = WebClient.create("https://ddstpa.com.ar/api/distancia?localidadOrigenId=" + idLocalidadOrigen + "&calleOrigen=" + ubicacion1.getCalle() + "&alturaOrigen=" + ubicacion1.getAltura() + "&localidadDestinoId=" + idLocalidadDestino + "&calleDestino=" + ubicacion2.getCalle() + "&alturaDestino=" + ubicacion2.getAltura());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        Response response = clientDistancia
                .header("Content-Type", "application/json")
                .authorization(this.token())
                .get();

        Integer status = response.getStatus();
        System.out.println("Status: " + status);
        String responseBody = response.readEntity(String.class);
        if (status == 200) {
            Distancia newDistancia = mapper.readValue(responseBody, Distancia.class);
            Double distancia = newDistancia.getValor();
            return distancia;
        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada a /api/user");
        }

    }

    public static String token() throws IOException {
       String CONFIGTOKEN = "src\\main\\resources\\application.properties";

        Properties property = new Properties();
		property.load(new FileReader(CONFIGTOKEN));
        String valorToken = property.getProperty("token");
		return valorToken;
    }

    public static Pais[] buscarPaises() throws IOException {
        WebClient client = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = WebClient.create("https://ddstpa.com.ar/api/paises?offset=1");
        assert client != null;
        Response response = client
                .header("Content-Type", "application/json")
                .authorization(APIDistanciaService.token())
                .get();

        Integer status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        if (status == 200) {
            return mapper.readValue(responseBody, Pais[].class);
        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada de búsqueda de paises");
        }
    }

    public static Provincia[] buscarProvincias(Integer idPais) throws IOException {
        WebClient client = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = WebClient.create("https://ddstpa.com.ar/api/provincias?offset=1&paisId=" + idPais);
        assert client != null;
        Response response = client
                .header("Content-Type", "application/json")
                .authorization(APIDistanciaService.token())
                .get();

        Integer status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        if (status == 200) {
            return mapper.readValue(responseBody, Provincia[].class);
        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada de búsqueda de provincias");
        }
    }

    public static Municipio[] buscarMunicipios(Integer idProvincia) throws IOException {
        WebClient client = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = WebClient.create("https://ddstpa.com.ar/api/municipios?offset=1&provinciaId=" + idProvincia);
        assert client != null;
        Response response = client
                .header("Content-Type", "application/json")
                .authorization(APIDistanciaService.token())
                .get();

        Integer status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        if (status == 200) {
            return mapper.readValue(responseBody, Municipio[].class);
        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada de búsqueda de municipios");
        }
    }

    public static Localidad[] buscarLocalidades(Integer idMunicipio) throws IOException {
        WebClient client = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = WebClient.create("https://ddstpa.com.ar/api/localidades?offset=1&municipioId=" + idMunicipio);
        assert client != null;
        Response response = client
                .header("Content-Type", "application/json")
                .authorization(APIDistanciaService.token())
                .get();

        Integer status = response.getStatus();
        String responseBody = response.readEntity(String.class);

        if (status == 200) {
            return mapper.readValue(responseBody, Localidad[].class);
        } else {
            System.out.println("Error response = " + responseBody);
            throw new BadResponseException("Error en la llamada de búsqueda de localidades");
        }
    }
}
