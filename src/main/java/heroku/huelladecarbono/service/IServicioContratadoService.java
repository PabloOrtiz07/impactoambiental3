package heroku.huelladecarbono.service;



import heroku.huelladecarbono.model.MedioDeTransporte.TipoServicio;

import java.util.List;

public interface IServicioContratadoService {

    public List<TipoServicio> getServiciosContratados();

    public void saveServicioContratado(TipoServicio tipoServicio);

    public void deleteServicioContratado(Integer id);

    public TipoServicio findServicioContratado(Integer id);
}
