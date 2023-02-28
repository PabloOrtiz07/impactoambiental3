package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import heroku.huelladecarbono.repository.SectorTerritorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorTerritorialService implements ISectorTerritorialService {

    @Autowired
    private SectorTerritorialRepository sectorTerritorialRepository;


    @Override
    public List<SectorTerritorial> getSectoresTerritoriales() {
        return  sectorTerritorialRepository.findAll();
    }

    @Override
    public void saveSectorTerritorial(SectorTerritorial sectorTerritorial) {
        sectorTerritorialRepository.save(sectorTerritorial);
    }

    @Override
    public void deleteSectorTerritorial(Integer id) {
        sectorTerritorialRepository.deleteById(id);
    }

    @Override
    public SectorTerritorial findSectorTerritorial(Integer id) {
        return sectorTerritorialRepository.findById(id).orElse(null);
    }

    public SectorTerritorial modificarSectorTerritorial(Integer id, SectorTerritorial sectorTerritorial){
        SectorTerritorial stActualizado = this.findSectorTerritorial(id);
        stActualizado.setMunicipio(sectorTerritorial.getMunicipio());
        stActualizado.setProvincia(sectorTerritorial.getProvincia());
        this.saveSectorTerritorial(stActualizado);
        return stActualizado;

        }
}