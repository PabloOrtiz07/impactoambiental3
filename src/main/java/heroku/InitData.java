package heroku;

import heroku.huelladecarbono.model.MedioDeTransporte.*;
import heroku.huelladecarbono.model.ModeloDeNegocio.*;
import heroku.huelladecarbono.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.OrganizacionController;
import heroku.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import heroku.huelladecarbono.model.Movilidad.Trayecto;
import heroku.huelladecarbono.service.CalculoDeHuellaService.Calendario;
import heroku.huelladecarbono.service.OrganizacionService;
import heroku.huelladecarbono.service.UsuarioService;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {


    @Autowired
    UsuarioService usuarioService;
    @Autowired
    OrganizacionService organizacionService;
    @Autowired
    OrganizacionRepository repoOrganizacion;
    @Autowired
    OrganizacionController organizacionController;
    @Autowired
    UsuarioRepository repoUsuario;
    @Autowired
    RecorridoRepository repoRecorrido;
    @Autowired
    ParadaRepository repoParadas;
    @Autowired
    SectorTerritorialRepository repoSectores;
    @Autowired
    TransportePublicoRepository repoTransportes;
    @Autowired
    MedioNoMotorizadoRepository repoMedioNoMotorizado;
    @Autowired
    MedioMotorizadoRepository repoMedioMotorizado;
    @Autowired
    MiembroRepository repoMiembros;
    @Autowired
    UbicacionRepository repoUbicaciones;
    @Autowired
    AreaRepository repoAreas;
    @Autowired
    TrayectoRepository repoTrayectos;
    @Autowired
    SectorTerritorialRepository repoSectorTerritorial;
    @Autowired
    RepositoryRestConfiguration config;
    @Autowired
    CreadorDeObjetos creadorDeObjetos;



    @Override
    public void run(String... args) throws Exception {

        cargarMiembros();
        cargarOrganizaciones();
        cargarRecorridos();
        cargarMedioNoMotorizado();
        cargarMedioMotorizado();
        cargarParadas();
        cargarUbicaciones();
        cargarAreas();
        cargarSectoresTerritoriales();
        cargarTransportePublico();
        cargarTrayectos();
        cargarDatosActividad();


        System.out.println("INIT TERMINADO");

    }

    //TODO Falla de recorridos

   public void cargarRecorridos() throws Exception {
        if (repoRecorrido.count() == 0) {
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Gugle");
            creadorDeObjetos.crearRecorrido(organizacion1, 0.50, Calendario.crearFecha(1, 2020), Calendario.crearFecha(9, 2022));
            // //De esta manera se carga el recorrido
            // Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
            // Recorrido recorridoPruebaDos =creadorDeObjetos.crearRecorrido(organizacion3,0.8,Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021));
        }
    }

    public void cargarDatosActividad() throws Exception {
        String filePath = "..\\DDS-2022-K3002-HuellaCarbonoG3\\mediciones2.xlsx";
        Organizacion organizacion = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Amason");
        organizacionService.cargarMedicion(filePath,organizacion);
    }

    public void cargarParadas() throws Exception {
        if(repoParadas.count() == 0) {
            Ubicacion ubicacionPruebaUno = creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Parada paradaPruebaUno = creadorDeObjetos.crearParada("120", ubicacionPruebaUno);
            //Parada paradaPruebaDos = creadorDeObjetos.crearParada("50",ubicacionPruebaUno);
        }
    }

    public void cargarUbicaciones() throws Exception {
        if (repoUbicaciones.count() == 0) {
            Ubicacion ubicacion1 = creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            List<Ubicacion> ubicaciones = List.of(ubicacion1);
        }
    }

    public void cargarSectores() {
        config.exposeIdsFor(SectorTerritorial.class);
        if(repoSectores.count() == 0){
            AgenteSectorial agente = new AgenteSectorial();
            SectorTerritorial sector = creadorDeObjetos.crearSectorTerritorial("Almirante Brown","Buenos Aires", agente);
        }
        else{
            System.out.println("Ya existen sectores territoriales creados anteriormente");
        }
    }



    public void cargarMedioNoMotorizado() throws Exception
    {
        config.exposeIdsFor(MedioNoMotorizado.class);
        if(repoMedioNoMotorizado.count()==0) {
            MedioNoMotorizado medio1 = new MedioNoMotorizado(TipoMedioNoMotorizado.A_PIE);
            List<MedioNoMotorizado> mediosNoMotorizados = List.of(medio1);
            mediosNoMotorizados.stream().forEach(medio -> {
                repoMedioNoMotorizado.save(medio);
            });
        }
    }

    public void cargarTransportePublico() throws Exception {

            List<Parada> paradas = new ArrayList<>();
            Ubicacion ubicacionPruebaUno = creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            Parada paradaPrueba3 = creadorDeObjetos.crearParada("120", ubicacionPruebaUno);
            paradas.add(paradaPrueba3);
            TransportePublico transporte1 = creadorDeObjetos.crearTransportePublico(TipoTransportePublico.COLECTIVO,"88", paradas,"123456");
            List<TransportePublico> transportePublicos = List.of(transporte1);
            transportePublicos.stream().forEach(transporte -> {
                repoTransportes.save(transporte);
            });


    }

    public void cargarMedioMotorizado() throws Exception
    {
        config.exposeIdsFor(MedioMotorizado.class);
        if(repoMedioMotorizado.count() == 0) {

            MedioMotorizado medio1 = new MedioMotorizado(TipoVehiculoMotorizado.MOTO, TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
            List<MedioMotorizado> mediosMotorizados = List.of(medio1);
            mediosMotorizados.stream().forEach(medio -> {
                repoMedioMotorizado.save(medio);
            });
        }
    }

    public void cargarAreas() throws Exception {
        config.exposeIdsFor(Area.class);
        if(repoAreas.count() == 0) {
            Area area1 = new Area("Gonzalez Catan",null,null);
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Micorosof");
            area1.setOrganizacion(organizacion1);
            List<Area> areas = List.of(area1);
            areas.stream().forEach(area -> {
                repoAreas.save(area);
            });
        }

    }

    //TODO evaluar si el formato JSON devuelto es valido
    public void cargarTrayectos() throws Exception {

            Ubicacion ubicacion1 = creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            //Ubicacion ubicacion2 = creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "150");
            List<Ubicacion> ubicaciones = List.of(ubicacion1);
            MedioMotorizado medio1 = creadorDeObjetos.crearMedioMotorizado(TipoVehiculoMotorizado.MOTO,TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
            Trayecto trayecto1 = creadorDeObjetos.crearTrayecto(ubicaciones,medio1);
            /* List<Ubicacion> ubicaciones = List.of(ubicacion1);
           ubicaciones.stream().forEach(ubicacion -> {repoUbicaciones.save(ubicacion);});
            MedioMotorizado medio1 = new MedioMotorizado(TipoVehiculoMotorizado.MOTO,TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
            trayecto1.setId(12);
            trayecto1.setMedioTransporte(medio1);
            trayecto1.setPuntoPartida(ubicacion1);
            trayecto1.setPuntoLlegada(ubicacion2);
            List<Trayecto> trayectos = List.of(trayecto1);
            List<MedioMotorizado> medios = List.of(medio1);
            medios.stream().forEach(medio -> repoMedioMotorizado.save(medio));
            trayectos.stream().forEach(trayecto -> repoTrayectos.save(trayecto));*/
    }


    public void cargarMiembros() throws Exception {
        if (repoMiembros.count() == 0) {
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Caralibro");
            Area area1 = creadorDeObjetos.crearArea("AreaPrueba", organizacion1);
            Miembro miembroPruebaUno = creadorDeObjetos.crearMiembro(area1, "Pablo", "Ortiz", "pablo@prueba", "2323", true);
            creadorDeObjetos.crearUsuario("lucadelpieri@gmail.com", "12345", 1, miembroPruebaUno);
            //Miembro miembroPruebaDos = creadorDeObjetos.crearMiembro(area1,"Juan","Ortiz","juan@prueba","23523",true);
        }
    }

    public void cargarOrganizaciones() throws Exception {
        if (repoOrganizacion.count() == 0){Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Amason");
        creadorDeObjetos.crearUsuario("amason", "qwerty", 2, organizacion1);
        Organizacion organizacion2 = creadorDeObjetos.crearOrganizacion("SRA", TipoOrg.GUBERNAMENTAL, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false, "MMM");
        creadorDeObjetos.crearUsuario("MMM", "qwerty", 2, organizacion2);
        Organizacion organizacion3 = creadorDeObjetos.crearOrganizacion("SRL", TipoOrg.ONG, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.ESCUELA, null, null, true, "Coto");
        creadorDeObjetos.crearUsuario("cotoooo", "qwerty", 2, organizacion3);
        Organizacion organizacion4 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.INSTITUCION, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false, "VVBA");
        creadorDeObjetos.crearUsuario("vvba", "qwerty", 2, organizacion4);
    }
    }

    public void cargarSectoresTerritoriales() {
        if(repoSectorTerritorial.count() == 0) {
           SectorTerritorial st = creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "BUENOS AIRES"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "FORMOSA"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "CHACO"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "SALTA"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, "MONTECARLO", "MISIONES"));
           AgenteSectorial as = creadorDeObjetos.crearAS(st);
           creadorDeObjetos.crearUsuario("usuarioAg", "qwerty", 3, as);
        }
    }


}

