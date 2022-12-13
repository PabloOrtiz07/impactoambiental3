package utn.frba.huelladecarbono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;
import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;
import utn.frba.huelladecarbono.repository.MedioMotorizadoRepository;
import utn.frba.huelladecarbono.repository.MedioNoMotorizadoRepository;

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
