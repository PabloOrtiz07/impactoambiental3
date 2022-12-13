package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.MedioDeTransporte.Medio;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.repository.MedioMotorizadoRepository;
import java.util.List;

@Service
public class MedioMotorizadoService implements IMedioMotorizadoService{
    @Autowired
    private MedioMotorizadoRepository medioMotorizadoRepository;

    @Override
    public List<MedioMotorizado> getMedioMotorizado() {
        List<MedioMotorizado> listaMedios = medioMotorizadoRepository.findAll();
        return listaMedios;
    }

    @Override
    public void saveMedioMotorizado(MedioMotorizado medio) {
        medioMotorizadoRepository.save(medio);
    }

    @Override
    public void deleteMedioMotorizado(Integer id) {
        medioMotorizadoRepository.deleteById(id);
    }
    @Override
    public MedioMotorizado findMedioMotorizado(Integer id) {
        MedioMotorizado medioMotorizado = medioMotorizadoRepository.findById(id).orElse(null);
        return medioMotorizado;
    }

}
