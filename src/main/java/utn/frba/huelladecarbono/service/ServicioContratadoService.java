package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.MedioDeTransporte.TipoServicio;
import utn.frba.huelladecarbono.repository.ServicioContratadoRepository;

import java.util.List;

@Service
public class ServicioContratadoService implements IServicioContratadoService {

    @Autowired
    private ServicioContratadoRepository servicioContratadoRepository;


    @Override
    public List<TipoServicio> getServiciosContratados() {
        List <TipoServicio> listaServiciosContratados = servicioContratadoRepository.findAll();

        return listaServiciosContratados;
    }

    @Override
    public void saveServicioContratado(TipoServicio servicioContratado) {
        servicioContratadoRepository.save(servicioContratado);
    }

    @Override
    public void deleteServicioContratado(Integer id) {
        servicioContratadoRepository.deleteById(id);
    }

    @Override
    public TipoServicio findServicioContratado(Integer id) {
        TipoServicio servicioContratado = servicioContratadoRepository.findById(id).orElse(null);
        return servicioContratado;
    }
}
