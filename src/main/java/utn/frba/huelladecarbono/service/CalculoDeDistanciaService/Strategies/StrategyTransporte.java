package utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;

public abstract class  StrategyTransporte {

    public abstract Double calcularDistancia(Ubicacion ubicacion1, Ubicacion ubicacion2, Medio medio) throws Exception;

}
