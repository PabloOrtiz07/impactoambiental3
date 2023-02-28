package heroku.huelladecarbono.model.Repositorios;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.MedioNoMotorizadoController;

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

