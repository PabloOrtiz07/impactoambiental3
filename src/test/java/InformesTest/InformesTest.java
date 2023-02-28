package InformesTest;

import heroku.huelladecarbono.model.ModeloDeNegocio.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import heroku.huelladecarbono.model.CreadorDeObjetos.CreadorDeObjetos;
import heroku.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import heroku.huelladecarbono.model.Repositorios.RepositorioSectorTerritorial;
import heroku.huelladecarbono.service.InformesService.ObtenerDatosInformes;

import java.time.LocalDate;

public class InformesTest {

    CreadorDeObjetos creadorDeObjetos;
    AgenteSectorial agente = new AgenteSectorial();
    SectorTerritorial sectorTerritorialprueba = new SectorTerritorial("Gonzalez Catan","Buenos Aires",agente);
    SectorTerritorial sectorTerritorialprueba2 = new SectorTerritorial("Isidro Casanova","Buenos Aires",agente);
    ObtenerDatosInformes obtenerDato = new ObtenerDatosInformes();

    Organizacion organizacion1 = new Organizacion("SA", TipoOrg.EMPRESA, Clasificacion.MINISTERIO, null, null, true);
    Organizacion organizacion2 = new Organizacion ("SRA", TipoOrg.GUBERNAMENTAL, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, null, false);
    Organizacion organizacion3 = new Organizacion("SRL", TipoOrg.ONG, Clasificacion.ESCUELA, null, null, true);
    Organizacion organizacion4 = new Organizacion("SA", TipoOrg.INSTITUCION, Clasificacion.EMPRESA_SECTOR_SECUNDARIO, null, null, false);


    @Test
    public void testHCSector() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalPorST();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }

    @Test
    public void testHCSectorEspecifico() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalDeST(sectorTerritorialprueba);
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }

    @Test
    public void testHCPais() {
        sectorTerritorialprueba.setHC(25.0);
        sectorTerritorialprueba2.setHC(50.0);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba);
        RepositorioSectorTerritorial.getRepositorio().agregarSectorTerritorial(sectorTerritorialprueba2);
        String huella = obtenerDato.hcTotalPais();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");
    }

    @Test
    public void testEvolucionSectorTerritorial() {
        LocalDate fechaIni = LocalDate.of(2020,1,8);
        LocalDate fechaFin = LocalDate.of(2020,2,20);
        HuellaCarbono huella = new HuellaCarbono(fechaIni,fechaFin,50.0);
        sectorTerritorialprueba.agregarHuella(huella);
        String evolucion = obtenerDato.evolucionST(sectorTerritorialprueba);
        System.out.println(evolucion);
        Assertions.assertTrue(evolucion != "");
    }

    @Test
    public void testHCOrganizacion() {
        LocalDate fechaIni = LocalDate.of(2020,1,8);
        LocalDate fechaFin = LocalDate.of(2020,2,20);
        HuellaCarbono huella1 = new HuellaCarbono(fechaIni,fechaFin,50.0);
        LocalDate fechaIni2 = LocalDate.of(2020,1,8);
        LocalDate fechaFin2 = LocalDate.of(2020,2,20);
        HuellaCarbono huella2 = new HuellaCarbono(fechaIni2,fechaFin2,25.0);

        organizacion1.agregarHuella(huella1);
        organizacion2.agregarHuella(huella2);
        organizacion3.agregarHuella(huella1);
        organizacion4.agregarHuella(huella2);

        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion1);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion2);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion3);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion4);
        String huella = obtenerDato.hcTotalPorTipoDeOrg();
        System.out.println(huella);
        Assertions.assertTrue(huella != "");

    }

    @Test
    public void testHCOrganizacionEspecifica() {
        LocalDate fechaIni = LocalDate.of(2020,1,8);
        LocalDate fechaFin = LocalDate.of(2020,2,20);
        HuellaCarbono huella1 = new HuellaCarbono(fechaIni,fechaFin,50.0);
        LocalDate fechaIni2 = LocalDate.of(2020,1,8);
        LocalDate fechaFin2 = LocalDate.of(2020,2,20);
        HuellaCarbono huella2 = new HuellaCarbono(fechaIni2,fechaFin2,25.0);

        organizacion1.agregarHuella(huella1);
        organizacion2.agregarHuella(huella2);
        organizacion3.agregarHuella(huella1);
        organizacion4.agregarHuella(huella2);

        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion1);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion2);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion3);
        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion4);
        String huella = obtenerDato.hcDeOrganizacion(organizacion4);
        System.out.println(huella);
        Assertions.assertTrue(huella != "");



    }

    @Test
    public void testEvolucionDeOrganizacion() {
        LocalDate fechaIni = LocalDate.of(2020,1,8);
        LocalDate fechaFin = LocalDate.of(2020,2,20);
        HuellaCarbono huella1 = new HuellaCarbono(fechaIni,fechaFin,50.0);
        LocalDate fechaIni2 = LocalDate.of(2020,1,8);
        LocalDate fechaFin2 = LocalDate.of(2020,2,20);
        HuellaCarbono huella2 = new HuellaCarbono(fechaIni2,fechaFin2,25.0);

        organizacion4.agregarHuella(huella1);
        organizacion4.agregarHuella(huella2);
        organizacion4.agregarHuella(huella1);
        organizacion4.agregarHuella(huella2);

        RepositorioOrganizaciones.getRepositorio().agregarOrganizacion(organizacion4);
        String evolucion = obtenerDato.evolucionOrg(organizacion4);
        System.out.println(evolucion);
        Assertions.assertTrue(evolucion != "");
    }






}
