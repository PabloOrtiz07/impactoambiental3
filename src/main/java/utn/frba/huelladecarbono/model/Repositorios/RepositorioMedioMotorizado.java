package utn.frba.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.frba.huelladecarbono.controller.MedioMotorizadoController;
import utn.frba.huelladecarbono.controller.TransportePublicoController;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TransportePublico;

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
