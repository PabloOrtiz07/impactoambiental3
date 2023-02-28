package heroku.huelladecarbono.service.CalculoDeHuellaService;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.ListaDeDatosDeMedicion;
import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;

import java.time.LocalDate;

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
