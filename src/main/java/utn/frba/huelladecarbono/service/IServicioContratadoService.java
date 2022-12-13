package utn.frba.huelladecarbono.service;



import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoServicio;

import java.util.List;

public interface IServicioContratadoService {

    public List<TipoServicio> getServiciosContratados();

    public void saveServicioContratado(TipoServicio tipoServicio);

    public void deleteServicioContratado(Integer id);

    public TipoServicio findServicioContratado(Integer id);
}
