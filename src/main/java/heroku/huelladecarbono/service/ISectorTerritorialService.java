package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;


import java.util.List;

public interface ISectorTerritorialService {

    //metodo para obtener a todos los miembros
    public List<SectorTerritorial> getSectoresTerritoriales();

    //Metodo para dar de alta a un miembro
    public void saveSectorTerritorial(SectorTerritorial sectorTerritorial);

    //Metodo para eliminar a un miembro
    public void deleteSectorTerritorial(Integer id);

    //Metodo para encontrar a un miembro
    public SectorTerritorial findSectorTerritorial(Integer id);

    //Metodo para actualizar la informacion de un miembro
    public SectorTerritorial modificarSectorTerritorial(Integer id, SectorTerritorial sectorTerritorial);

}
