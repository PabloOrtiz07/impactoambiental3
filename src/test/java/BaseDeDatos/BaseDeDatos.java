package BaseDeDatos;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import utn.frba.huelladecarbono.model.MedioDeTransporte.*;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.AgenteSectorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Clasificacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.Calendario;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.FactoresDeEmision;

@Getter
public class BaseDeDatos {
    static BaseDeDatos instance = null;
    List<Organizacion> organizaciones = new ArrayList<>();
    List<Area> areas = new ArrayList<>();
    List<Miembro> miembros = new ArrayList<>();
    List<Ubicacion> ubicaciones = new ArrayList<>();
    List<Recorrido> recorridos = new ArrayList<>();
    List<Trayecto> trayectos = new ArrayList<>();
    List<SectorTerritorial> sectoresTerritoriales = new ArrayList<>();
    List<AgenteSectorial> agentesSectoriales = new ArrayList<>();

    public static BaseDeDatos getInstance() throws Exception {
        if(instance == null) {
            return new BaseDeDatos();
        } else {
            return instance;
        }
    }


    private BaseDeDatos() throws Exception {
        // UBICACIONES
        ubicaciones.add(new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "100"));
        ubicaciones.add(new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "200"));
        ubicaciones.add(new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "300"));
        ubicaciones.add(new Ubicacion("ARGENTINA", "MISIONES", "MONTECARLO", "CARAGUATAY ", "maipu", "400"));
        // ORGANIZACIONES
        organizaciones.add(new Organizacion("SA", TipoOrg.EMPRESA, ubicaciones.get(0), Clasificacion.MINISTERIO, null, null));
        organizaciones.add(new Organizacion("SRA", TipoOrg.GUBERNAMENTAL, ubicaciones.get(1), Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null));
        organizaciones.add(new Organizacion("SRL", TipoOrg.ONG, ubicaciones.get(2), Clasificacion.ESCUELA, null, null));
        organizaciones.add(new Organizacion("SA", TipoOrg.INSTITUCION, ubicaciones.get(3), Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null));
        // ÁREAS
        areas.add(new Area("Sistemas", organizaciones.get(0)));
        areas.add(new Area("Ventas", organizaciones.get(0)));
        areas.add(new Area("Compras", organizaciones.get(0)));
        areas.add(new Area("RRHH", organizaciones.get(1)));
        areas.add(new Area("Sistemas", organizaciones.get(1)));
        areas.add(new Area("Legal", organizaciones.get(2)));
        areas.add(new Area("Ventas", organizaciones.get(3)));
        List<Area> areas1 = new ArrayList<>();
        areas1.add(areas.get(0));
        List<Area> areas2 = new ArrayList<>();
        areas2.add(areas.get(0));
        areas2.add(areas.get(1));
        areas2.add(areas.get(2));
        // TRAYECTOS
        /*
        trayectos.add(new Trayecto(ubicaciones.get(0), ubicaciones.get(1), new MedioMotorizado(TipoVehiculoMotorizado.AUTO, TipoCombustible.GASOIL, "AUG324", false, "Particular")));
        trayectos.add(new Trayecto(ubicaciones.get(1), ubicaciones.get(2), new MedioMotorizado(TipoVehiculoMotorizado.MOTO, TipoCombustible.ELECTRICO, "KGD567", false, "Particular")));
        trayectos.add(new Trayecto(ubicaciones.get(2), ubicaciones.get(3), new MedioNoMotorizado(TipoMedioNoMotorizado.BICI)));
        trayectos.add(new Trayecto(ubicaciones.get(0), ubicaciones.get(3), new MedioNoMotorizado(TipoMedioNoMotorizado.MONOPATIN)));
        trayectos.add(new Trayecto(ubicaciones.get(1), ubicaciones.get(3), new MedioMotorizado(TipoVehiculoMotorizado.CAMIONETA, TipoCombustible.GASOIL, "AUG324", true, "Taxi")));
*/
        // RECORRIDOS
        recorridos.add(new Recorrido(organizaciones.get(0),0.5, Calendario.crearFecha(1, 2020), Calendario.crearFecha(9, 2022)));
        recorridos.get(0).addTrayecto(trayectos.get(0));
        recorridos.get(0).addTrayecto(trayectos.get(1));
        recorridos.get(0).addTrayecto(trayectos.get(2));
        recorridos.get(0).addTrayecto(trayectos.get(3));
        recorridos.add(new Recorrido(organizaciones.get(1),0.25, Calendario.crearFecha(5, 2015), Calendario.crearFecha(8, 2016)));
        recorridos.get(1).addTrayecto(trayectos.get(0));
        recorridos.get(1).addTrayecto(trayectos.get(1));
        recorridos.add(new Recorrido(organizaciones.get(2),1.0, Calendario.crearFecha(5, 2015), Calendario.crearFecha(8, 2016)));
        recorridos.get(2).addTrayecto(trayectos.get(0));
        recorridos.get(2).addTrayecto(trayectos.get(1));
        recorridos.add(new Recorrido(organizaciones.get(3),0.25, Calendario.crearFecha(3, 2022), Calendario.crearFecha(10, 2023)));
        recorridos.get(3).addTrayecto(trayectos.get(2));
        recorridos.get(3).addTrayecto(trayectos.get(3));
        recorridos.add(new Recorrido(organizaciones.get(3),0.5, Calendario.crearFecha(3, 2022), Calendario.crearFecha(10, 2023)));
        recorridos.get(4).addTrayecto(trayectos.get(2));
        recorridos.get(4).addTrayecto(trayectos.get(3));

        List<Recorrido> recorridos1 = new ArrayList<>();
        recorridos1.add(recorridos.get(0));
        recorridos1.add(recorridos.get(4));
        List<Recorrido> recorridos2 = new ArrayList<>();
        recorridos2.add(recorridos.get(0));
        recorridos2.add(recorridos.get(1));
        recorridos2.add(recorridos.get(3));
        List<Recorrido> recorridos3 = new ArrayList<>();
        recorridos3.add(recorridos.get(2));
        // MIEMBROS
        miembros.add(new Miembro("Carlos", "Castaño", "DNI", 14589653, areas1, recorridos1));
        miembros.add(new Miembro("Ariel", "Ramirez", "DNI", 54216598, areas2, recorridos2));
        miembros.add(new Miembro("Alexa", "Prieto", "DNI", 54269856, areas, recorridos3));
        miembros.add(new Miembro("Martha", "Paz", "DNI", 12546598, areas1, recorridos1));
        miembros.add(new Miembro("Lucas", "Sosa", "DNI", 45879652, areas2, recorridos1));

        // FACTORES DE EMISIÓN
        FactoresDeEmision FE = FactoresDeEmision.getInstance();
        FE.cargaDeFactores();

        // AGENTES SECTORIALES
        agentesSectoriales.add(new AgenteSectorial());
        
        // SECTORES TERRITORIALES
        SectorTerritorial sector1 = new SectorTerritorial(agentesSectoriales.get(0));
        sector1.setMunicipio("MONTECARLO");
        sectoresTerritoriales.add(sector1);

        SectorTerritorial sector2 = new SectorTerritorial();
        sector2.setProvincia("MISIONES");
        sectoresTerritoriales.add(sector2);
        
    }
}