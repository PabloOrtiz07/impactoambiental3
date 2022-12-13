package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Area;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.DatoDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.ListaDeDatosDeMedicion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class CalculadoraHCArea {
    public static Double calcularHC(Area area, LocalDate mesInicio, LocalDate mesFin){
        Double HC = 0.0;
        for (Miembro miembro : area.getMiembros()) {
            try {
                HC += CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin, area.getOrganizacion());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (ListaDeDatosDeMedicion mediciones :area.getMediciones() ) {
            HC += CalculadoraHCService.getCalculadoraHC().calcularHCMedicion(mediciones.getDatosDeMedicion(), mesInicio, mesFin);
        }
        return HC;
    }

    public static Double HCpromedio(Area area, LocalDate mesInicio, LocalDate mesFin) {
        return calcularHC(area, mesInicio, mesFin) / area.getMiembros().size();
    }
}
