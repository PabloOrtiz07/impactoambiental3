package utn.frba.huelladecarbono.service.InformesService;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.HuellaCarbono;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Organizacion;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.SectorTerritorial;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.TipoOrg;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioOrganizaciones;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioSectorTerritorial;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenerDatosInformes {

    public String hcTotalPorST(){
        String hcTotalPorST = "";
        List<SectorTerritorial> sectoresTerritoriales = RepositorioSectorTerritorial.getRepositorio().getSectoresTerritoriales();
        for(SectorTerritorial st : sectoresTerritoriales){
            hcTotalPorST += hcTotalDeST(st);
        }
        return hcTotalPorST;
    }

    public String hcTotalDeST(SectorTerritorial sector) {
        if(sector.getMunicipio().isEmpty()){
            return "Sector de Provincia: " + sector.getProvincia() + ". HC total: " + sector.getHuellaCarbono() + "\n";
        }
        else{
            return "Sector de Municipio: " + sector.getMunicipio() + ". HC total: " + sector.getHuellaCarbono() + "\n";
        }
    }

    public String hcTotalPais() {
        String hcTotalPais = "";
        List<SectorTerritorial> sectoresTerritoriales = RepositorioSectorTerritorial.getRepositorio().getSectoresTerritoriales();
        for(SectorTerritorial st : sectoresTerritoriales){

                hcTotalPais += "Sector de Provincia: " + st.getProvincia() + ". HC total: " + st.getHuellaCarbono() + "\n";

        }
        return hcTotalPais;
    }

    public String hcTotalPorTipoDeOrg(){
        List<Organizacion> organizaciones = RepositorioOrganizaciones.getRepositorio().getOrganizaciones();
        String valoresGubernamental = getValoresOrg(organizaciones, TipoOrg.GUBERNAMENTAL);
        String valoresONG = getValoresOrg(organizaciones, TipoOrg.ONG);
        String valoresEmpresa = getValoresOrg(organizaciones, TipoOrg.EMPRESA);
        String valoresInstitucion = getValoresOrg(organizaciones, TipoOrg.INSTITUCION);
        return  "HC Gubernamental: " + valoresGubernamental + "\n" +
                "HC Empresa: " + valoresEmpresa + "\n" +
                "HC ONG: " + valoresONG + "\n" +
                "HC Institucion: " + valoresInstitucion;
    }

    public String hcDeOrganizacion(Organizacion organizacion) {
       return "HC Organizacion: " + organizacion.getHuellaTotal();
    }

    public String getValoresOrg(List<Organizacion> organizaciones, TipoOrg tipo){
       return organizaciones.stream()
                .filter(organizacion -> organizacion.getTipo().equals(tipo))
                .map(Organizacion::getHuellaTotal)
                .collect(Collectors.summingDouble(Double::doubleValue))
                .toString();
    }

    public String evolucionOrg(Organizacion organizacion){
        return evolucion(organizacion.getHuellasDeCarbono());
    }

    public String evolucionST(SectorTerritorial st){
        return evolucion(st.getHuellasDeCarbono());
    }

    private String evolucion(List<HuellaCarbono> huellas){
        String evolucion = "";
        List<HuellaCarbono> huellasOrganizadas = huellas.stream()
                .sorted(Comparator.comparing(HuellaCarbono::getFechaIni))
                .collect(Collectors.toList());
        for(HuellaCarbono h : huellasOrganizadas){
            evolucion += "Hc en el periodo " + h.getFechaIni() +
                    "/" + h.getFechaFin() + ": " + h.getHuella() + "\n";
        }
        return evolucion;
    }
}
