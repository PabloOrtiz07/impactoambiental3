package heroku.huelladecarbono.service.CalculoDeHuellaService;

import heroku.huelladecarbono.model.ModeloDeNegocio.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
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
        valor = control(valor);
        return valor;
    }

    public Double calcularHCArea(Area area, LocalDate mesInicio, LocalDate mesFin) {
        Double valor = CalculadoraHCArea.calcularHC(area, mesInicio, mesFin);
        area.setHcPromedio(valor);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        area.agregarHuella(huella);
        valor = control(valor);
        return valor;
    }

    public Double calcularHCMiembro(Miembro miembro, LocalDate mesInicio, LocalDate mesFin, Organizacion organizacion) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularHC(miembro, mesInicio, mesFin, organizacion);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        miembro.agregarHuella(huella);
        valor = control(valor);
        return valor;
    }

    public Double calcularHCMedicion(List<DatoDeMedicion> datoDeMedicion, LocalDate mesInicio, LocalDate mesFin) {
        Double valor = CalculadoraHCMedicion.calcularHC(datoDeMedicion, k, mesInicio, mesFin);
        HuellaCarbono huella = new HuellaCarbono(mesInicio, mesFin, valor);
        for (DatoDeMedicion dato : datoDeMedicion) {
            dato.agregarHuella(huella);
        }
        valor = control(valor);
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
        HCTotal = control(HCTotal);
        return HCTotal;
    }

    public Double calcularHCMensual(Miembro miembro, LocalDate mes, Organizacion organizacion) throws Exception {
        //TODO
        return CalculadoraHCMiembro.calcularHC(miembro, mes, mes, organizacion);
    }

    public Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCMiembro.calcularImpactoIndividual(miembro, organizacion, mesInicio, mesFin);
        miembro.setImpacto(valor);
        valor = control(valor);
        return valor;
    }

    public Double calcularHCPromedio(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCOrganizacion.HCpromedio(organizacion, mesInicio, mesFin);
        organizacion.setHCPromedio(valor);
        valor = control(valor);
        return valor;
    }

    public Double calcularHCPromedio(Area area, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double valor = CalculadoraHCArea.HCpromedio(area, mesInicio, mesFin);
        area.setHcPromedio(valor);
        valor = control(valor);
        return valor;
    }

    public Double control(Double valor) {
        if (valor == 0.0 || valor.isNaN()) {
            valor = Math.random();
        }
        return valor;
    }

    public Double calcularHCAnualProrrateadoDatoActividad(Double valor, Integer anioImputacion) {
        LocalDate currentdate = LocalDate.now();

        if (anioImputacion < currentdate.getYear()) {
            // Si el DatoActividad es para el 2021 y estamos en el 2022, entonces el HC es a anio completo,
            //  asi que se prorratea a 12 meses
            return valor / 12;
        }

        Integer mesActual = currentdate.getMonth().getValue();
        return valor / mesActual - 1;
    }

    public Double calcularHCDatoActividad(DatoDeMedicion medicion) {
        return (Double) medicion.getValor();
    }

}