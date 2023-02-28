package heroku.huelladecarbono.model.CreadorDeObjetos;

import heroku.huelladecarbono.model.MedioDeTransporte.*;
import heroku.huelladecarbono.model.ModeloDeNegocio.*;
import heroku.huelladecarbono.model.Repositorios.*;
import heroku.huelladecarbono.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.model.Movilidad.Trayecto;
import heroku.huelladecarbono.model.Seguridad.Usuario;
import heroku.huelladecarbono.service.CalculoDeHuellaService.Registro;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreadorDeObjetos {

    @Autowired
    OrganizacionRepository repoOrganizaciones;

    @Autowired
    RepositorioOrganizaciones repositorioOrganizacionesEnMemoria;

    @Autowired
    MiembroRepository repoMiembros;

    @Autowired
    RepositorioMiembros repositorioMiembrosEnMemoria;

    @Autowired
    ParadaRepository repoParadas;

    @Autowired
    RepositorioParadas repositorioParadasEnMemoria;

    @Autowired
    RecorridoRepository repoRecorridos;

    @Autowired
    RepositorioRecorrido repositorioRecorridoEnMemoria;
    //@Autowired
    //UsuarioRepository repoUsuarios;

    @Autowired
    AreaRepository repoArea;
    @Autowired
    TransportePublicoRepository repoTransportesPublico;

    @Autowired
    RepositorioTransportesPublicos repositorioTransportesPublicosEnMemoria;

    @Autowired
    TrayectoRepository repoTrayectos;

    @Autowired
    RepositorioTrayectos repositorioTrayectosEnMemoria;

    @Autowired
    SectorTerritorialRepository repoSectorTerritorial;

    @Autowired
    RepositorioSectorTerritorial repositorioSectorTerritorialEnMemoria;
    @Autowired
    MedioMotorizadoRepository repoMedioMotorizado;
    @Autowired
    RepositorioMedioMotorizado repositorioMedioMotorizado;
    @Autowired
    MedioNoMotorizadoRepository repoMedioNoMotorizado;
    @Autowired
    RepositorioMedioNoMotorizado repositorioMedioNoMotorizado;
    @Autowired
    UbicacionRepository repoUbicacion;
    @Autowired
    UsuarioRepository repoUsuario;



    public Organizacion crearOrganizacion(String razonSocial, TipoOrg tipo, Ubicacion ubicacion, Clasificacion clasificacion, ArrayList<Miembro> contactosMail, ArrayList<Miembro> contactosWP, Boolean estaActivo, String nombre) {
        Organizacion organizacion = new Organizacion(razonSocial,tipo, ubicacion, clasificacion,contactosMail,contactosWP,null, null,null,null, estaActivo, nombre);
        repoOrganizaciones.save(organizacion);
        repositorioOrganizacionesEnMemoria.agregarOrganizacion(organizacion);
        return organizacion;
    }

    public SectorTerritorial crearSectorTerritorial(String municipio, String prov, AgenteSectorial agent){
        //SectorTerritorial sectorTerritorial = new SectorTerritorial(municipio, prov, agent);
        //repoSectorTerritorial.save(sectorTerritorial);
        //repositorioSectorTerritorialEnMemoria.agregarSectorTerritorial(sectorTerritorial);
        return null;
    }

    public Organizacion crearOrganizacionConHC(String razonSocial, TipoOrg tipo, Ubicacion ubicacion, Clasificacion clasificacion, List<Miembro> contactosMail, List<Miembro> contactosWP, List<Registro> hcMensual, Double hcPromedio, List<HuellaCarbono> huellasCarbono, Double huellaCarbono , Boolean estaActivo, String nombre) {
        Organizacion organizacion = new Organizacion( razonSocial,  tipo,  ubicacion, clasificacion, contactosMail, contactosWP, hcMensual,  hcPromedio, huellasCarbono, huellaCarbono,  estaActivo, nombre);
        repoOrganizaciones.save(organizacion);
        repositorioOrganizacionesEnMemoria.agregarOrganizacion(organizacion);
        return organizacion;
    }

   /* public  Miembro crearMiembro(int id, String nombre, String apellido, String mail, String telefono, Boolean estaActivo) {
        Miembro miembro = new Miembro(id,nombre,apellido,mail,telefono,estaActivo);
        repoMiembros.save(miembro);
        repositorioMiembrosEnMemoria.agregarMiembro(miembro);
        return miembro;
    }*/

    public  Miembro crearMiembro(Area area, String nombre, String apellido, String mail, String telefono, Boolean estaActivo) {
        Miembro miembro = new Miembro(area, nombre,apellido,mail,telefono,estaActivo);
        repoMiembros.save(miembro);
        repositorioMiembrosEnMemoria.agregarMiembro(miembro);
        return miembro;
    }

    public  AgenteSectorial crearAS(SectorTerritorial st) {
        AgenteSectorial as = new AgenteSectorial(st);
        return as;
    }

    public Parada crearParada(String nombre, Ubicacion ubicacion) {
        Parada parada = new Parada(nombre,ubicacion);
        repoParadas.save(parada);
        repositorioParadasEnMemoria.agregarParada(parada);
        return parada;
    }


    public  Recorrido crearRecorrido(Organizacion organizacion, Double peso, LocalDate mesInicio, LocalDate mesFin) {
        Recorrido recorrido = new Recorrido(organizacion,peso,mesInicio,mesFin);
        repoRecorridos.save(recorrido);
        repositorioRecorridoEnMemoria.agregarRecorrido(recorrido);
        return null;
    }

    /*public  Usuario crearUsuario(String username, String password, List<Rol> rol){
        Usuario usuario = new Usuario(username,password,rol);
        repoUsuarios.save(usuario);
        repositorioUsuariosEnMemoria.agregarUsuario(usuario);
        return usuario;
    }*/

    public TransportePublico crearTransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas, String ID){
        TransportePublico transportePublico = new TransportePublico(tipoTransportePublico,linea,paradas,ID);
        repoTransportesPublico.save(transportePublico);
        repositorioTransportesPublicosEnMemoria.agregarLinea(transportePublico);
        return transportePublico;
    }

    public Trayecto crearTrayecto(List<Ubicacion> ubicaciones, Medio medio){
        Trayecto trayecto = new Trayecto(ubicaciones,medio);
        repoTrayectos.save(trayecto);
       // repositorioTrayectosEnMemoria.agregarTrayecto(trayecto);
        return trayecto;
    }

   public Area crearArea(String nombre, Organizacion organizacion, List<ListaDeDatosDeMedicion> mediciones){
        Area area = new Area(nombre,organizacion,mediciones);
        area.setEstaActivo(true);
        repoArea.save(area);
        return area;
    }

    public Area crearArea(String nombre, Organizacion organizacion){
        Area area = new Area(nombre,organizacion);
        //repoArea.save(area);
        return area;
    }

    public MedioMotorizado crearMedioMotorizado(TipoVehiculoMotorizado tipoVehiculoMotorizado, TipoCombustible tipoCombustible, String patente, Boolean esServicioContratado, String tipoServicio) {
        MedioMotorizado medio = new MedioMotorizado(tipoVehiculoMotorizado, tipoCombustible, patente, esServicioContratado, tipoServicio);
        repoMedioMotorizado.save(medio);
        repositorioMedioMotorizado.agregarMedioMotorizado(medio);
        return medio;

    }

    public MedioNoMotorizado crearMedioNoMotorizado(TipoMedioNoMotorizado tipo) {
        MedioNoMotorizado medio = new MedioNoMotorizado(tipo);
        repoMedioNoMotorizado.save(medio);
        repositorioMedioNoMotorizado.agregarMedioNoMotorizado(medio);
        return medio;
    }

    public SectorTerritorial crearSectorTerritorial(SectorTerritorial sector) {
        repoSectorTerritorial.save(sector);
        return sector;
    }

    public Ubicacion crearUbicacion(String pais, String provincia, String municipio, String localidad, String calle, String altura) throws Exception {
        Ubicacion ubicacion = new Ubicacion(pais, provincia, municipio, localidad, calle, altura);
        repoUbicacion.save(ubicacion);
        return ubicacion;
    }

    public Usuario crearUsuario(String username, String password, Integer rol, Miembro miembro) {
        Usuario usuario = new Usuario(username, password, rol, miembro.getID());
        repoUsuario.save(usuario);
        return usuario;
    }

    public Usuario crearUsuario(String username, String password, Integer rol, Organizacion organizacion) {
        Usuario usuario = new Usuario(username, password, rol, organizacion.getId());
        repoUsuario.save(usuario);
        return usuario;
    }

    public Usuario crearUsuario(String username, String password, Integer rol, AgenteSectorial agente) {
        Usuario usuario = new Usuario(username, password, rol, agente.getId());
        repoUsuario.save(usuario);
        return usuario;
    }

}
