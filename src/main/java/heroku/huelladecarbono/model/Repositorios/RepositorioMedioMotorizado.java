package heroku.huelladecarbono.model.Repositorios;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.MedioMotorizadoController;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioMedioMotorizado {
    @Autowired
    MedioMotorizadoController motorizadodb;

    private static RepositorioMedioMotorizado instance = new RepositorioMedioMotorizado();
    private List<MedioMotorizado> medios = new ArrayList<>();

    public void agregarMedioMotorizado(MedioMotorizado medio) {
        this.medios.add(medio);
    }

    public static RepositorioMedioMotorizado getRepositorio() {
        return instance;
    }

}
