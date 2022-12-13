package utn.frba.huelladecarbono.service.CalculoDeDistanciaService.Strategies;

import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.APIDistanciaService;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;

public class StrategyVehiculoNoPublico extends StrategyTransporte {

    public Double calcularDistancia(Ubicacion ubicacion1,Ubicacion ubicacion2, Medio transporteMotorizado) throws Exception {
        return new APIDistanciaService().medirDistancia(ubicacion1, ubicacion2);
    }

}
