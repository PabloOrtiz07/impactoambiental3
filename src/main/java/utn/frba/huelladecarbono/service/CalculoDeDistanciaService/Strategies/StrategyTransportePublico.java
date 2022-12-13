package utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Parada;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

import java.util.ArrayList;
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
