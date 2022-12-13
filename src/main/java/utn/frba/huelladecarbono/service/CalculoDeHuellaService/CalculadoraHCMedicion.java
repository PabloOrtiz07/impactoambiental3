package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;

import java.time.LocalDate;
import java.util.List;

public class CalculadoraHCMedicion {
    static public Double calcularHCMedicionEstandar(DatoDeMedicion datoDeMedicion){
        Double FE = FactoresDeEmision.getInstance().getFE(datoDeMedicion.getActividad());
        Double valor = (Double) datoDeMedicion.getValor();
        if(datoDeMedicion.getPeriodicidad() == "mensual"){
            return valor * FE;
        }
        else{
            return valor/12 * FE;
        }
    }

    static public Double calcularHCMedicionLogistica (Double distancia, Double peso, String periocidad, Double FE, Double k) {
        if(periocidad == "Mensual"){
            return distancia * peso * k * FE;
        }
        else{
            return (distancia * peso)/12 * k * FE;
        }
    }
    static public Double calcularHC(List<DatoDeMedicion> mediciones, Double k, LocalDate mesInicio, LocalDate mesFin) {
    Double HCTotal = 0.0;
    Double peso = 0.0;
    Double distancia = 0.0;
    Double FE = 0.0;
        for (DatoDeMedicion datoDeMedicion : mediciones) {
            if (datoDeMedicion.getActividad() != "Logística de productos y resitudos") {
                HCTotal += calcularHCMedicionEstandar(datoDeMedicion);
            }
            else {
                if(datoDeMedicion.getTipoDeConsumo() == "Distancia Medio Recorrida")
                    distancia =(Double) datoDeMedicion.getValor();
                else if (datoDeMedicion.getTipoDeConsumo() == "Peso Total Transportado")
                    peso = (Double) datoDeMedicion.getValor();
                else if (datoDeMedicion.getTipoDeConsumo() == "Medio de Transporte")
                    FE = FactoresDeEmision.getInstance().getFE((String) datoDeMedicion.getValor());
            }
            HCTotal += calcularHCMedicionLogistica(distancia, peso, datoDeMedicion.getPeriodicidad(), FE, k);
        }
        return HCTotal;
    }
}
