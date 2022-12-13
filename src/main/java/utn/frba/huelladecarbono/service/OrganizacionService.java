package utn.frba.huelladecarbono.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.repository.OrganizacionRepository;

import java.util.List;

@Service
public class OrganizacionService implements IOrganizacionService{

    @Autowired
    private OrganizacionRepository organizacionRepository;


    @Override
    public Organizacion findById(Integer id) throws Exception {
        return organizacionRepository.findById(id).get();
    }

    @Override
    public List<Organizacion> getOrganizaciones() {
        List <Organizacion> listaOrganizaciones = organizacionRepository.findAll();

        return listaOrganizaciones;
    }

    @Override
    public void saveOrganizacion(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
    }

    @Override
    public void deleteOrganizacion(Integer id) {
        cambiarEstadoOrganizacion(id);
    }


    @Override
    public Organizacion findOrganizacion(Integer id) {
        Organizacion organizacion = organizacionRepository.findById(id).orElse(null);
        return organizacion;
    }


    @Override
    public List<Organizacion> findOrganizacionByEstadoActivo() {

       return organizacionRepository.findByEstaActivo(true);
    }


    @Override
    public void cambiarEstadoOrganizacion(Integer id) {
        Organizacion organizacion = findOrganizacion(id);
        organizacion.setEstaActivo(false);

        this.saveOrganizacion(organizacion);
    }

    public Organizacion modificarOrganizacion(Integer id, Organizacion organizacion){
        Organizacion organizacionActualizada = this.findOrganizacion(id);
        organizacionActualizada.setRazonSocial(organizacion.getRazonSocial());
        organizacionActualizada.setTipo(organizacion.getTipo());
        organizacionActualizada.setClasificacion(organizacion.getClasificacion());
        organizacionActualizada.setEstaActivo(organizacion.getEstaActivo());
        this.saveOrganizacion(organizacionActualizada);
        return organizacionActualizada;
    }

    @Override
    public Organizacion crearOrganizacion(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
        return organizacion;
    }

    @Override
    public Double getHuellaTotal(Integer id){
        return organizacionRepository.findById(id).get().getHuellaTotal();
    }

    @Override
    public void eliminarRecorrido(Integer orgId, Integer recorridoId) {
        Organizacion organizacion = this.findOrganizacion(orgId);
        organizacion.getRecorridos().removeIf(recorrido -> recorrido.getId().equals(recorridoId));
        this.saveOrganizacion(organizacion);
    }

    @Override
    public void actualizarOrganizacion(String orgJson) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject jObject  = (JSONObject) parser.parse(orgJson);// json
        Organizacion orgA = findOrganizacion((Integer) jObject.get("idOrgaNuevo"));
        orgA.setNombre((String) jObject.get("nombreNuevo"));
        orgA.setRazonSocial((String) jObject.get("razonSocialNuevo"));
        orgA.setTipo((TipoOrg) jObject.get("tipoNuevo"));
        orgA.setClasificacion((Clasificacion) jObject.get("clasificacionNuevo"));
        orgA.setUbicacion(new Ubicacion((String) jObject.get("paisNuevo"), (String) jObject.get("provinciaNuevo"), (String) jObject.get("municipioNuevo"), (String) jObject.get("localidadNuevo"), (String) jObject.get("calleNuevo"), (String) jObject.get("alturaNuevo")));

        this.saveOrganizacion(orgA);
    }
}
