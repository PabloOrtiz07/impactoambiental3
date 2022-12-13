package utn.frba.huelladecarbono.respuestaEndpoint;

import lombok.Getter;
import utn.frba.huelladecarbono.model.Movilidad.Recorrido;

import java.time.LocalDate;

@Getter
public class ResRecorrido {
    ResOrganizacion organizacion;
    Double peso;
    LocalDate fechaI;
    LocalDate fechaF;
    Boolean estaActivo;
    Integer cantTrayectos;

    public ResRecorrido(Recorrido recorrido) {
        this.organizacion = new ResOrganizacion(recorrido.getOrganizacion());
        this.peso = recorrido.getPeso();
        this.fechaI = recorrido.getFechaInicio();
        this.fechaF = recorrido.getFechaFin();
        this.estaActivo = recorrido.getEstaActivo();
        this.cantTrayectos = recorrido.getTrayectos().size();
    }
}
