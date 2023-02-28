package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import heroku.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import heroku.huelladecarbono.repository.DatoActividadRepository;
import heroku.huelladecarbono.repository.OrganizacionRepository;
import heroku.huelladecarbono.repository.UbicacionRepository;
import heroku.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;
import heroku.huelladecarbono.service.CalculoDeHuellaService.HuellaDeCarbono;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import heroku.huelladecarbono.service.CalculoDeHuellaService.RegistroCalculoHCDatoActividad;
import heroku.huelladecarbono.service.CargaDeMedicionesService.CargaDeMediciones;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizacionService implements IOrganizacionService{

    @Autowired
    private CalculadoraHCService calculadoraHCService;

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Autowired
    private DatoActividadRepository datoActividadRepository;

    @Autowired
    UbicacionRepository ubicacionRepository;

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
        JSONObject jObject  = (JSONObject) parser.parse(orgJson);
        Organizacion orgA = findOrganizacion(Integer.parseInt((String) jObject.get("idOrgaNuevo")));
        orgA.setNombre((String) jObject.get("nombreNuevo"));
        orgA.setRazonSocial((String) jObject.get("razonSocialNuevo"));
        orgA.setTipo(TipoOrg.valueOf((String) jObject.get("tipoNuevo")));
        orgA.setClasificacion(Clasificacion.valueOf((String) jObject.get("clasificacionNuevo")));
        //orgA.setUbicacion(creadorDeObjetos.crearUbicacion((String) jObject.get("paisNuevo"), (String) jObject.get("provinciaNuevo"), (String) jObject.get("municipioNuevo"), (String) jObject.get("localidadNuevo"), (String) jObject.get("calleNuevo"), (String) jObject.get("alturaNuevo")));
        this.saveOrganizacion(orgA);
    }

    public void cargarMedicion (String filePath,Organizacion organizacion){
        CargaDeMediciones cargaDeMediciones = new CargaDeMediciones();
        cargaDeMediciones.useExistingWorkbook(filePath);
        List<DatoDeMedicion> mediciones = cargaDeMediciones.lecturaArchivo(0);
        calcularHCDatosActividadYGuardarRegistroCalculo(organizacion,mediciones);
    }

    private void calcularHCDatosActividadYGuardarRegistroCalculo(Organizacion organizacion, List<DatoDeMedicion> mediciones) {
        List<HuellaDeCarbono> huellaDeCarbonoAnuales = new ArrayList<>();
        List<HuellaDeCarbono> huellaDeCarbonoMensuales = new ArrayList<>();
        Integer contador = 0;

        for (DatoDeMedicion medicion : mediciones){
            Double valorHuellaCarbono = calculadoraHCService.calcularHCDatoActividad(medicion);
            System.out.println("prueba "+medicion.getPeriodoImputacion()+" "+valorHuellaCarbono);
            HuellaDeCarbono huellaDeCarbono = new HuellaDeCarbono(medicion.getPeriodoImputacion(),valorHuellaCarbono);
            if(medicion.getPeriodicidad().equals("Mensual")){
                huellaDeCarbonoMensuales.add(huellaDeCarbono);
            }else{
                huellaDeCarbonoAnuales.add(huellaDeCarbono);
            }
            if(contador==10){
                break;
            }
            contador++;
        }

        System.out.println("pueba 3"+huellaDeCarbonoMensuales.get(0).getValor());

        guardarRegistroCalculoHCMensuales(organizacion, huellaDeCarbonoMensuales);
        guardarRegistroCalculoHCAnuales(organizacion, huellaDeCarbonoAnuales);


    }

    public void guardarRegistroCalculoHCMensuales(Organizacion organizacion, List<HuellaDeCarbono> huellasDeCarbonos){
        System.out.println("pueba 3"+huellasDeCarbonos.get(0).getValor());
        huellasDeCarbonos.forEach(huellaDeCarbonos->datoActividadRepository.save(new RegistroCalculoHCDatoActividad("Mensual"
             ,huellaDeCarbonos.getLocalDate(),organizacion,huellaDeCarbonos.getValor())));
    }

    public void guardarRegistroCalculoHCAnuales(Organizacion organizacion,List<HuellaDeCarbono> huellasDeCarbonos){
        huellasDeCarbonos.stream().forEach(huellaDeCarbonos->datoActividadRepository.save(new RegistroCalculoHCDatoActividad("Anual"
                ,huellaDeCarbonos.getLocalDate(), organizacion,calculadoraHCService.calcularHCAnualProrrateadoDatoActividad(huellaDeCarbonos.getValor(),2022))));
    }

}
