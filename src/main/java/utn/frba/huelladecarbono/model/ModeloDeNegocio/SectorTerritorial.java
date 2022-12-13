package utn.frba.huelladecarbono.model.ModeloDeNegocio;

import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
public class SectorTerritorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String municipio;
    @Column
    private String provincia;
    @Column
    private Double huellaCarbono;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HuellaCarbono> huellasCarbono = new ArrayList<>();
    @OneToOne
    private AgenteSectorial agenteSectorial;

    public SectorTerritorial(AgenteSectorial agenteSectorial, String municipio, String provincia) {
        this.agenteSectorial = agenteSectorial;
        this.municipio = municipio;
        this.provincia = provincia;
        this.huellaCarbono = 0.0;
    }

    private List<Organizacion> generarOrganizacionesPorCriterio(List<Organizacion> organizacionesTotales, Object criterioUbicacion) {
        List<Organizacion> organizacionesDelSector = new ArrayList<>();
        for (Organizacion organizacion : organizacionesTotales) {
            if (organizacion.getUbicacion().getMunicipio() == criterioUbicacion)
                organizacionesTotales.add(organizacion);
        }
        return organizacionesDelSector;
    }

    public List<Organizacion> getOrganizaciones() {
        List<Organizacion> organizacionesTotales = RepositorioOrganizaciones.getRepositorio().getOrganizaciones();
        if (municipio != null){
            return generarOrganizacionesPorCriterio(organizacionesTotales, municipio);
        }
        else {
            return generarOrganizacionesPorCriterio(organizacionesTotales, provincia);
        }
    }

    public String getProvincia() {
        return provincia;
    }

    public void setHC(Double valor){
        this.huellaCarbono = valor;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public AgenteSectorial getAgenteSectorial() {
        return agenteSectorial;
    }

    public void agregarHuella(HuellaCarbono hc){this.huellasCarbono.add(hc);}

    public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
        this.agenteSectorial = agenteSectorial;
    }

    public List<HuellaCarbono> getHuellasDeCarbono() { return huellasCarbono; }

    public SectorTerritorial() {
    }

    public SectorTerritorial(String municipio, String provincia, AgenteSectorial agenteSectorial) {
        this.municipio = municipio;
        this.provincia = provincia;
        this.agenteSectorial = agenteSectorial;
    }

    public SectorTerritorial(Integer id) {
        this.id = id;
    }

    public SectorTerritorial(String municipio) {
        this.municipio = municipio;
    }

    public SectorTerritorial(Double huellaCarbono) {
        this.huellaCarbono = huellaCarbono;
    }

    public SectorTerritorial(List<HuellaCarbono> huellasCarbono) {
        this.huellasCarbono = huellasCarbono;
    }

    public SectorTerritorial(AgenteSectorial agenteSectorial) {
        this.agenteSectorial = agenteSectorial;
    }
}
