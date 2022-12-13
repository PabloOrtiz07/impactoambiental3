package utn.frba.huelladecarbono.model.Movilidad;

import utn.frba.huelladecarbono.model.ModeloDeNegocio.Miembro;
import utn.frba.huelladecarbono.model.ModeloDeNegocio.Ubicacion;
import utn.frba.huelladecarbono.model.Repositorios.RepositorioMiembros;
import utn.frba.huelladecarbono.service.CalculoDeDistanciaService.*;;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@Table (name="trayecto")
public class Trayecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    private Medio medioTransporte;

    @ManyToMany
    private List<Ubicacion> ubicaciones;

    public Trayecto() {}


    public Trayecto(List<Ubicacion> ubicaciones,Medio medioTransporte) {
        this.medioTransporte = medioTransporte;
        this.ubicaciones = ubicaciones;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }


    public List<Miembro> getPasajeros(){
        return RepositorioMiembros.getRepositorio().getMiembros().stream()
                .filter(miembro -> miembro.getRecorridos().stream().
                        anyMatch(recorrido -> recorrido.getTrayectos().stream().
                                anyMatch(trayecto -> trayecto.equals(this))))
                .collect(Collectors.toList());
    }



    public Medio getMedioTransporte() {
        return medioTransporte;
    }


    public Double distanciaMedia() throws Exception {
        CalculoDistanciaService distanciaService = new CalculoDistanciaService();
        Double resultado = distanciaService.calcularDistancia(getUbicaciones().get(0),getUbicaciones().get(1), medioTransporte);
        return resultado;
    }


}
