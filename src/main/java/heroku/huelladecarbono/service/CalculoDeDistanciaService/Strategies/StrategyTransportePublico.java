package heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import heroku.huelladecarbono.model.MedioDeTransporte.Medio;
import heroku.huelladecarbono.model.MedioDeTransporte.Parada;
import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

import java.util.List;
import java.util.stream.Collectors;

public class StrategyTransportePublico extends StrategyTransporte{

    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio transportePublico) {
        List<Parada> paradas = ((TransportePublico) transportePublico).getParadas();
        List<Ubicacion> ubicaciones = paradas.stream().map(parada -> parada.getUbicacion()).collect(Collectors.toList());
        Double distanciaTotal = 0.0;
        Parada paradaTemp = paradas.get(0);
        for(int i = ubicaciones.indexOf(ubicacion1); i < ubicaciones.indexOf(ubicacion2); i++){
            paradaTemp = paradas.get(i);
            distanciaTotal += paradaTemp.distancaAProximaParada();
        }
        return distanciaTotal;
    }
}
