package heroku.huelladecarbono.service.CalculoDeHuellaServiceV2;

import heroku.huelladecarbono.model.ModeloDeNegocio.Area;
import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.service.CalculoDeHuellaService.Registro;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class Calculadora {

    public static Double calcularHCOrganizacion(Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
       Double hc = 0.0;
        // CALCULAR HC MES ACTUAL
        if (mesFin.getYear() > LocalDate.now().getYear()
                || (mesFin.getYear() == LocalDate.now().getYear()
                && mesFin.getMonthValue() >= LocalDate.now().getMonthValue())) {
            List<Area> areas = organizacion.getAreas();
           for (Area area : areas) {
               hc += area.getHCMediciones();
               for (Miembro miembro : area.getMiembros()) {
                   for (Recorrido recorrido : miembro.getRecorridos()) {
                           Random random = new Random();
                           hc += (random.nextDouble() + 1.0) * recorrido.getCantidadDeTrayectos();
                   }
               }
           }
       }
        // CALCULO HC ANTERIOR
        if (mesFin.getMonthValue() < LocalDate.now().getMonthValue() || mesFin.getYear() < LocalDate.now().getYear()) {
            List<Registro> registros = organizacion.getHcMensual();
            List<Registro> select = registros.stream().filter(registro -> registro.getMes().isAfter(mesInicio) && registro.getMes().isBefore(LocalDate.now())).toList();

            for (Registro registro : select) {
                hc += registro.getHc();
            }
        }

 
        return hc;
    }

    public static Double calcularHCMiembro(LocalDate fechaI, LocalDate fechaF, Miembro miembro) {
        Double hc = 0.0;
        for (Recorrido recorrido : miembro.getRecorridos()) {
            Random random = new Random();
            hc += (random.nextDouble() + 1.0) * recorrido.getCantidadDeTrayectos();
        }
        return hc * ChronoUnit.MONTHS.between(fechaI, fechaF);

    }
}
