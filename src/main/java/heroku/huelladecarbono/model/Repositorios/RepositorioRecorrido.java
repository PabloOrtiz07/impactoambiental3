package heroku.huelladecarbono.model.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.RecorridoController;
import heroku.huelladecarbono.model.Movilidad.Recorrido;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioRecorrido {

    @Autowired
    RecorridoController recorridobd;
    private static RepositorioRecorrido instance = new RepositorioRecorrido();
    private List<Recorrido> recorridos;


    private RepositorioRecorrido() {
        this.recorridos = new ArrayList<>();

    }

    public List<Recorrido> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<Recorrido> recorridos) {
        this.recorridos = recorridos;
    }

    public static RepositorioRecorrido getRepositorio() {
        return instance;
    }

    public void agregarRecorrido (Recorrido recorrido) {
            recorridos.add(recorrido);
    }

}
