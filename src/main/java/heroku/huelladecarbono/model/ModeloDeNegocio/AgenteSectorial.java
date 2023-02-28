package heroku.huelladecarbono.model.ModeloDeNegocio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class AgenteSectorial {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private SectorTerritorial sectorTerritorial;

    public SectorTerritorial getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }

    public AgenteSectorial() {
    }

    public AgenteSectorial(Integer id, SectorTerritorial sectorTerritorial) {
        this.id = id;
        this.sectorTerritorial = sectorTerritorial;
    }

    public AgenteSectorial(Integer id) {
        this.id = id;
    }

    public AgenteSectorial(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }
}
