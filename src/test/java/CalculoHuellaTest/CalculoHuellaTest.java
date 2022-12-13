package CalculoHuellaTest;

import BaseDeDatos.BaseDeDatos;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.*;

public class CalculoHuellaTest {
    BaseDeDatos BD = BaseDeDatos.getInstance();

    public CalculoHuellaTest() throws Exception {
    }

    @Test
    public void calcularHuellaMiembro() throws Exception {
        Miembro miembro = BD.getMiembros().get(0);
        LocalDate fechaInicio = Calendario.crearFecha(0,2021);
        LocalDate fechaFin = Calendario.crearFecha(11,2021);
        Organizacion organizacion = BD.getMiembros().get(0).getAreas().get(0).getOrganizacion();
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, fechaInicio, fechaFin, organizacion);
            System.out.println("La huella de carbono del miembro es: " + huella);
        });
    }

    @Test
    public void calcularHuellaOrganizacion() {
        Organizacion organizacion = BD.getOrganizaciones().get(0);
        LocalDate fechaInicio = Calendario.crearFecha(0,2021);
        LocalDate fechaFin = Calendario.crearFecha(11,2021);
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, fechaInicio, fechaFin);
            System.out.println("La huella de carbono de la organizacion es: " + huella);
        });
    }

    @Test
    public void calcularHuellaArea() {
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCArea(BD.getAreas().get(0), Calendario.crearFecha(0,2021), Calendario.crearFecha(11,2021));
            System.out.println("La huella de carbono del area es: " + huella);
        });
    }

    @Test
    public void calcularHuellaSectorTerritorial() {
        SectorTerritorial sector = BD.getSectoresTerritoriales().get(0);
        LocalDate fechaInicio = Calendario.crearFecha(0,2021);
        LocalDate fechaFin = Calendario.crearFecha(11,2021);
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCSectorTerritorial(sector, fechaInicio, fechaFin);
            System.out.println("La huella de carbono del sector territorial es: " + huella);
        });
    }

    @Test
    public void calcularHuellaPromedioArea() {
        LocalDate fechaInicio = Calendario.crearFecha(0,2021);
        LocalDate fechaFin = Calendario.crearFecha(11,2021);
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCPromedio(BD.getAreas().get(1), fechaInicio, fechaFin);
            System.out.println("La huella de carbono promedio para el area es: " + huella);
        });
    }

    @Test
    public void calcularHuellaPromedioOrganizacion() {
        LocalDate fechaInicio = Calendario.crearFecha(0,2021);
        LocalDate fechaFin = Calendario.crearFecha(11,2021);
        Assertions.assertDoesNotThrow(() -> {
            Double huella = CalculadoraHCService.getCalculadoraHC().calcularHCPromedio(BD.getOrganizaciones().get(0), fechaInicio, fechaFin);
            System.out.println("La huella de carbono promedio para la organizacion es: " + huella);
        });
    }

    @Test
    public void medirImpactoPersonal() {
        Miembro miembro = BD.getMiembros().get(0);
        Organizacion organizacion = miembro.getAreas().get(0).getOrganizacion();
        LocalDate fechaInicio = Calendario.crearFecha(0, 2022);
        LocalDate fechaFin = Calendario.crearFecha(5, 2022);
        Assertions.assertDoesNotThrow(() -> {
            Double impacto = CalculadoraHCService.getCalculadoraHC().calcularImpactoIndividual(miembro, organizacion, fechaInicio, fechaFin);
            System.out.println("El impacto personal del miembro en la organización es: " + impacto + "%");
        });
    }
}
