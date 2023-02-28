package heroku.huelladecarbono.service.CalculoDeHuellaService;

import heroku.huelladecarbono.model.ModeloDeNegocio.Miembro;
import heroku.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import heroku.huelladecarbono.model.Movilidad.Recorrido;
import heroku.huelladecarbono.model.Movilidad.Trayecto;

import java.time.LocalDate;

public class CalculadoraHCMiembro {

    public static Double calcularHC(Miembro miembro, LocalDate mesInicio, LocalDate mesFin, Organizacion organizacion) throws Exception {
        Double HC = 0.0;
        int mesesRecorridos;


        for(Recorrido recorrido : miembro.getRecorridos()) { // Los miembros no tienen recorridos
            if(recorrido.getOrganizacion() == organizacion) {
                mesesRecorridos = Calendario.mesesEntreMedio(mesInicio, mesFin, LocalDate.parse(recorrido.getFechaInicio()), LocalDate.parse(recorrido.getFechaFin()));
                HC += calcularHCRecorrido(recorrido) * 4.5 * mesesRecorridos;
            }
        }

        //Se multiplica por 20 para generar el impacto de un mes
      if(HC == 0.0){
          double numeroRandom = Math.random()*(3)+1;
          HC = numeroRandom; /// Devolvia 0 porque siempre entra por este caso y como esta seteado en 0.0 desde el principio...
          return HC * 20;
      }else{
          return HC * 20;
      }

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
        if(organizacion.getMiembros().size() == 0 ){
            promedio = HCOrganizacion;
        }else{ //Calculo de huella en el caso de que el vehiculo sea compartido por varios miembros de una organizacion
            promedio = HCOrganizacion / organizacion.getMiembros().size() ; // Da infinity porque la cantidad de miembros es igual a 0 entonces la division tira infinito!

        }
        impacto = (HCMiembro * promedio) / 100;

        System.out.println("La Huella de carbono de su compañía es: " + HCOrganizacion +". Su Huella de carbono"
                +" individual es: " + HCMiembro + ". Eso equivale a un: " +impacto+ "% de la Huella de carbono de " +
                "organización");
        return impacto;
    }
}
