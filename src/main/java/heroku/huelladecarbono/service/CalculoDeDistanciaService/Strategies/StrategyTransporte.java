package heroku.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import heroku.huelladecarbono.model.MedioDeTransporte.Medio;
import heroku.huelladecarbono.model.ModeloDeNegocio.Ubicacion;

public abstract class  StrategyTransporte {

    public abstract Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception;

}
