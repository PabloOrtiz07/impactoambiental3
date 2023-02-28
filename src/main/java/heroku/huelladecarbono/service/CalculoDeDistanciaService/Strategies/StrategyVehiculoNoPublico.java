package heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import heroku.huelladecarbono.model.MedioDeTransporte.Medio;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import heroku.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;

public class StrategyVehiculoNoPublico extends StrategyTransporte {

    public Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio transporteMotorizado) throws Exception {
        return new APIDistanciaService().medirDistancia(ubicacion1, ubicacion2);
    }

}
