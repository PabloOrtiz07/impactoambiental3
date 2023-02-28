package heroku.huelladecarbono.model.Repositorios;

import heroku.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import heroku.huelladecarbono.controller.SectorTerritorialController;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorioSectorTerritorial {
    @Autowired
    SectorTerritorialController sectorTerritorialbd;
    private static RepositorioSectorTerritorial instance = new RepositorioSectorTerritorial();

    private List<SectorTerritorial> sectoresTerritoriales;

    private RepositorioSectorTerritorial() {
        this.sectoresTerritoriales = new ArrayList<>();
    }

    public List<SectorTerritorial> getSectoresTerritoriales() {
        return sectoresTerritoriales;
    }

    public static RepositorioSectorTerritorial getRepositorio() {
        return instance;
    }

    public void agregarSectorTerritorial(SectorTerritorial sectorTerritorial){
        this.sectoresTerritoriales.add(sectorTerritorial);
    }

    public void cargarDeSectorTerritorialDeBdAlSistema() {
        for(SectorTerritorial sectorTerritorialbd : sectorTerritorialbd.getSectorTerritorial()) {
            SectorTerritorial sectorTerritorial= new SectorTerritorial(sectorTerritorialbd.getMunicipio(), sectorTerritorialbd.getProvincia(), sectorTerritorialbd.getAgenteSectorial());
            this.agregarSectorTerritorial(sectorTerritorial);
        }
    }

}
