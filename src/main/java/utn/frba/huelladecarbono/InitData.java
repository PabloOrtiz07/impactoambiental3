package utn.frba.huelladecarbono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.OrganizacionController;
import utn.frba.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.model.Seguridad.Rol;
import utn.frba.huelladecarbono.model.Seguridad.Usuario;
import utn.frba.huelladecarbono.repository.*;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;
import utn.frba.huelladecarbono.service.UsuarioService;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {


    @Autowired
    UsuarioService usuarioService;
    @Autowired
    OrganizacionRepository repoOrganizacion;

    @Autowired
    OrganizacionController organizacionController;

    //Usuarios
    @Autowired
    UsuarioRepository repoUsuario;

    @Autowired
    RecorridoRepository repoRecorrido;
    

    //Paradas
    @Autowired
    ParadaRepository repoParadas;

    //Sector
    @Autowired
    SectorTerritorialRepository repoSectores;

    //Transporte

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
        cargarUsuarios();
        cargarParadas();
        cargarUbicaciones();
        cargarAreas();
        cargarSectoresTerritoriales();
        cargarTransportePublico();
        darDeBajaOrganizacion();
        cargarTrayectos();


        Usuario usuarioMiembro = new Usuario("pablo@gmail.com","123",Arrays.asList(new Rol("ROLE_USER")));
        usuarioService.saveUsuario(usuarioMiembro);

        Usuario usuarioOrga = new Usuario("luca@gmail.com","123",Arrays.asList(new Rol("ROLE_ADM")));
        usuarioService.saveUsuario(usuarioOrga);

        Usuario usuarioAS = new Usuario("gonza@gmail.com","123",Arrays.asList(new Rol("ROLE_ADM")));
        usuarioService.saveUsuario(usuarioOrga);


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

   public void cargarUsuarios() throws Exception
    {
        config.exposeIdsFor(Usuario.class);
        if(repoUsuario.count() == 0) {

            Usuario usuario1 = new Usuario("prueba", "Yagni3210+", Arrays.asList(new Rol("ROLE_USER")));
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuario1);
            usuarios.stream().forEach(usuario -> {
                repoUsuario.save(usuario);
            });
        }
        else{
            System.out.println("Ya existen Usuarios creados anteriormente");
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

            MedioMotorizado medio1 = new MedioMotorizado(TipoVehiculoMotorizado.MOTO,TipoCombustible.NAFTA,"FRX123",Boolean.FALSE,"Particular");
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

    //Solo a modo prueba es esto

    public void cargarMiembros() throws Exception {
        if (repoMiembros.count() == 0) {
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Caralibro");
            Area area1 = creadorDeObjetos.crearArea("AreaPrueba", organizacion1);
            Miembro miembroPruebaUno = creadorDeObjetos.crearMiembro(area1, "Pablo", "Ortiz", "pablo@prueba", "2323", true);
            //Miembro miembroPruebaDos = creadorDeObjetos.crearMiembro(area1,"Juan","Ortiz","juan@prueba","23523",true);
        }
    }

    public void cargarOrganizaciones() throws Exception {
        if (repoOrganizacion.count() == 0) {
            Organizacion organizacion1 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.EMPRESA, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.MINISTERIO, null, null, true, "Amason");
            Organizacion organizacion2 = creadorDeObjetos.crearOrganizacion("SRA", TipoOrg.GUBERNAMENTAL, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false, "MMM");
            Organizacion organizacion3 = creadorDeObjetos.crearOrganizacion("SRL", TipoOrg.ONG, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.ESCUELA, null, null, true, "Coto");
            Organizacion organizacion4 = creadorDeObjetos.crearOrganizacion("SA", TipoOrg.INSTITUCION, creadorDeObjetos.crearUbicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"), Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false, "VVBA");
            /*Ubicacion ubicacionPruebaUno = new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100");
            ArrayList<Double> listaHCPrueba = new ArrayList<>();
            //Area area1 = creadorDeObjetos.crearArea("AreaPrueba", organizacion1);
            organizacion1.setUbicacion(ubicacionPruebaUno);
            listaHCPrueba.add(100.00);
            HuellaCarbono huellaPrueba = new HuellaCarbono(Calendario.crearFecha(2,2021),Calendario.crearFecha(3,2021), 250.00);
            List<HuellaCarbono> hashMapPrueba = new ArrayList<>();
            hashMapPrueba.add(huellaPrueba);
            Organizacion organizacionConHC = creadorDeObjetos.crearOrganizacionConHC("SA",TipoOrg.INSTITUCION,ubicacionPruebaUno,Clasificacion.EMPRESA_SECTOR_SECUNDARIO,null,null,listaHCPrueba,250.00,hashMapPrueba,500.00,false);
            Miembro miembroPruebaMail = creadorDeObjetos.crearMiembro(null,"Gonza","D","mail@prueba","5511",true);
            Miembro miembroPruebaWP = creadorDeObjetos.crearMiembro(null,"Gonza2","D","mail2@prueba","221",true);
            organizacion1.agregarContactoWP(miembroPruebaWP);
            organizacion1.agregarContactoMail(miembroPruebaMail);
           // RepositorioOrganizaciones.getRepositorio().getOrganizaciones().add(organizacion1);*/
        }
    }
/*
    public void actualizarOrganizacion() throws Exception
    {
        Organizacion nuevaOrganizacion = repoOrganizacion.findById(1).get();
        nuevaOrganizacion.setRazonSocial("OrganizacionPableken!");
        organizacionController.actualizarOrganizacion(1,nuevaOrganizacion);
    }
*/
    public void darDeBajaOrganizacion() throws Exception
    {
        organizacionController.deleteOrganizacion(1);
    }

    public void cargarSectoresTerritoriales() {
        if(repoSectorTerritorial.count() == 0) {
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "BUENOS AIRES"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "FORMOSA"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "CHACO"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, null, "SALTA"));
           creadorDeObjetos.crearSectorTerritorial(new SectorTerritorial(null, "MONTECARLO", "MISIONES"));
        }
    }

}

