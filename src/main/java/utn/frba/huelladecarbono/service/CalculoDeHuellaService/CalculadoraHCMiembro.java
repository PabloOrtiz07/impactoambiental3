package utn.frba.huelladecarbono.service.CalculoDeHuellaService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;
import utn.frba.huelladecarbono.model.Movilidad.Trayecto;

import java.time.LocalDate;

public class CalculadoraHCMiembro {

    public static Double calcularHC(Miembro miembro, LocalDate mesInicio, LocalDate mesFin, Organizacion organizacion) throws Exception {
        Double HC = 0.0;
        int mesesRecorridos;
        for(Recorrido recorrido : miembro.getRecorridos()) {
            if(recorrido.getOrganizacion() == organizacion) {
                mesesRecorridos = Calendario.mesesEntreMedio(mesInicio, mesFin, recorrido.getFechaInicio(), recorrido.getFechaFin());
                HC += calcularHCRecorrido(recorrido) * 4.5 * mesesRecorridos;
            }
        }
        //Se multiplica por 20 para generar el impacto de un mes
        return HC * 20;
    }

    private static Double calcularHCRecorrido(Recorrido recorrido) throws Exception {
        Double HC = 0.0;
        for(Trayecto trayecto : recorrido.getTrayectos()) {
            HC += calcularHCTrayecto(trayecto);
        }
        return HC * recorrido.getPeso();
    }

    private static Double calcularHCTrayecto(Trayecto trayecto) throws Exception {
        FactoresDeEmision FE = FactoresDeEmision.getInstance();
        Double distanciaMedia = trayecto.distanciaMedia();
        Double fe = FE.getFE(trayecto.getMedioTransporte().getTipo());
        Double cantPasajeros = (double) (trayecto.getPasajeros().size());
        Double resultado = distanciaMedia * fe / cantPasajeros ;

        return resultado;
    }

    public static Double calcularImpactoIndividual(Miembro miembro, Organizacion organizacion, LocalDate mesInicio, LocalDate mesFin) throws Exception {
        Double HCMiembro;
        Double HCOrganizacion;
        Double promedio;
        Double impacto;

        HCOrganizacion = CalculadoraHCService.getCalculadoraHC().calcularHCOrganizacion(organizacion, mesInicio, mesFin);
        HCMiembro = CalculadoraHCService.getCalculadoraHC().calcularHCMiembro(miembro, mesInicio, mesFin, organizacion);
        promedio = HCOrganizacion / organizacion.getMiembros().size();
        impacto = (HCMiembro * promedio) / 100;

        System.out.println("La Huella de carbono de su compañía es: " + HCOrganizacion +". Su Huella de carbono"
                +" individual es: " + HCMiembro + ". Eso equivale a un: " +impacto+ "% de la Huella de carbono de " +
                "organización");
        return impacto;
    }
}
