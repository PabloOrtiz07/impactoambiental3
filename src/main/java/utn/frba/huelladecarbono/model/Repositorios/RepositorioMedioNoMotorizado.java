package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.MedioMotorizadoController;
import utn.frba.huelladecarbono.controller.MedioNoMotorizadoController;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;

import java.util.List;

@Component
public class RepositorioMedioNoMotorizado {

    @Autowired
    MedioNoMotorizadoController motorizadodb;


        private static RepositorioMedioNoMotorizado instance = new RepositorioMedioNoMotorizado();
    private List<MedioNoMotorizado> medios;

    public void agregarMedioNoMotorizado(MedioNoMotorizado medio) {
        this.medios.add(medio);
    }

        public static RepositorioMedioNoMotorizado getRepositorio() {
            return instance;
        }

}

