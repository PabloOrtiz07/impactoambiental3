package utn.frba.huelladecarbono.service.CalculoDeDistanciaService;

import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyTransporte;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyTransportePublico;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyVehiculoNoPublico;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

public class CalculoDistanciaService {

    StrategyTransporte strategy = null;


    //Se podría extender para que reciba un trayecto directamente
    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception {
        if(medio.getClass() == TransportePublico.class) this.strategy = new StrategyTransportePublico();
        if(medio.getClass() == MedioMotorizado.class || medio.getClass() == MedioNoMotorizado.class) this.strategy = new StrategyVehiculoNoPublico();
        return strategy.calcularDistancia(ubicacion1, ubicacion2, medio);
    }

}
