package utn.frba.huelladecarbono.service.CalculoDeHuellaService;


import utn.frba.huelladecarbono.model.ModeloDeNegocio.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class CalculadoraHCService {
    Double k = 0.0;
    private static CalculadoraHCService instance = null;

    private CalculadoraHCService() {
    }

    public static CalculadoraHCService getCalculadoraHC() {
        if (instance == null) {
            instance = new CalculadoraHCService();
        }
        return instance;
    }

    public Double calcularHCOrganizacion(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) {
        Double valor = CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
        organizacion.setHC(valor);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        organizacion.agregarHuella(huella);
        return valor;
    }

    public Double calcularHCArea(Area area, LocalDate mesInicio, LocalDate mesFin) {
        Double valor = CalculadoraHCArea.calcularHC(area, mesInicio, mesFin);
        area.setHcPromedio(valor);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        area.agregarHuella(huella);
        return valor;
    }

    public Double calcularHCMiembro(Miembro miembro, LocalDate mesInicio, LocalDate mesFin, Organizacion organizacion) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularHC(miembro, mesInicio, mesFin, organizacion);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        miembro.agregarHuella(huella);
        return valor;
    }

    public Double calcularHCMedicion(List<DatoDeMedicion> datoDeMedicion, LocalDate mesInicio, LocalDate mesFin) {
        Double valor = CalculadoraHCMedicion.calcularHC(datoDeMedicion, k, mesInicio, mesFin);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        for(DatoDeMedicion dato : datoDeMedicion){
            dato.agregarHuella(huella);
        }
        return valor;
    }

    public Double calcularHCSectorTerritorial(SectorTerritorial sectorTerritorial, LocalDate mesInicio, LocalDate mesFin) {
        Double HCTotal = 0.0;
        for (Organizacion organizacion : sectorTerritorial.getOrganizaciones()) {
            HCTotal += CalculadoraHCOrganizacion.calcularHC(organizacion, mesInicio, mesFin);
        }
        sectorTerritorial.setHC(HCTotal);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, HCTotal);
        sectorTerritorial.agregarHuella(huella);
        return HCTotal;
    }

    public Double calcularHCMensual(Miembro miembro, LocalDate mes, Organizacion organizacion) throws Exception {
        //TODO
        return CalculadoraHCMiembro.calcularHC(miembro, mes, mes, organizacion);
    }

    public Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularImpactoIndividual(miembro, organizacion, mesInicio, mesFin);
        miembro.setImpacto(valor);
        return valor;
    }

    public Double calcularHCPromedio(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCOrganizacion.HCpromedio(organizacion, mesInicio, mesFin);
        organizacion.setHCPromedio(valor);
        return valor;
    }

    public Double calcularHCPromedio(Area area,LocalDate mesInicio,LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCArea.HCpromedio(area, mesInicio, mesFin);
        area.setHcPromedio(valor);
        return valor;
    }
}
