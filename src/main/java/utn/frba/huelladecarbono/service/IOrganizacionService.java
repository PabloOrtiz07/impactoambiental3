package utn.frba.huelladecarbono.service;

import net.minidev.json.parser.ParseException;
import org.apache.poi.ss.extractor.ExcelExtractor;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;

import java.util.List;

public interface IOrganizacionService {

    //metodo para obtener organizacion por ID
    public Organizacion findById(Integer id)throws Exception;

    //metodo para obtener a todas las organizaciones
    public List<Organizacion> getOrganizaciones();

    //Metodo para dar de alta a una organizacion
    public void saveOrganizacion(Organizacion organizacion);

    //Metodo para eliminar una organizacion
    public void deleteOrganizacion(Integer id);

    //Metodo para encontrar a una organizacion
    public Organizacion findOrganizacion(Integer id);

    //Metodo para encontrar a una organizacion por su estado
    public List<Organizacion> findOrganizacionByEstadoActivo();

    public void cambiarEstadoOrganizacion(Integer id);

    public Organizacion modificarOrganizacion(Integer id, Organizacion organizacion);

    public Organizacion crearOrganizacion(Organizacion organizacion);

    public Double getHuellaTotal(Integer id);

    void eliminarRecorrido(Integer orgId, Integer recorridoId);

    void actualizarOrganizacion(String organizacion) throws Exception;
}
