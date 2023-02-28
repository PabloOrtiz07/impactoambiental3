package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import heroku.huelladecarbono.repository.MedioNoMotorizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioNoMotorizadoService implements IMedioNoMotorizadoService {
    @Autowired
    private MedioNoMotorizadoRepository medioNoMotorizadoRepository;

    @Override
    public List<MedioNoMotorizado> getMedioNoMotorizado() {
        List<MedioNoMotorizado> listaMedios = medioNoMotorizadoRepository.findAll();
        return listaMedios;
    }

    @Override
    public void saveMedioNoMotorizado(MedioNoMotorizado medio) {
        medioNoMotorizadoRepository.save(medio);
    }

    @Override
    public void deleteMedioNoMotorizado(Integer id) {
        medioNoMotorizadoRepository.deleteById(id);
    }
    @Override
    public MedioNoMotorizado findMedioNoMotorizado(Integer id) {
        MedioNoMotorizado medioNoMotorizado = medioNoMotorizadoRepository.findById(id).orElse(null);
        return medioNoMotorizado;
    }

}
