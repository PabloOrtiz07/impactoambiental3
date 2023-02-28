package heroku.huelladecarbono.service.CalculoDeDistanciaService;

import heroku.huelladecarbono.model.MedioDeTransporte.Medio;
import heroku.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyTransporte;
import heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyTransportePublico;
import heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies.StrategyVehiculoNoPublico;

public class CalculoDistanciaService {

    StrategyTransporte strategy = null;


    //Se podría extender para que reciba un trayecto directamente
    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception {
        if(medio.getClass() == TransportePublico.class) this.strategy = new StrategyTransportePublico();
        if(medio.getClass() == MedioMotorizado.class || medio.getClass() == MedioNoMotorizado.class) this.strategy = new StrategyVehiculoNoPublico();
        return strategy.calcularDistancia(ubicacion1, ubicacion2, medio);
    }

}
