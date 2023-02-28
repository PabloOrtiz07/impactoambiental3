package heroku.huelladecarbono.service;
import heroku.huelladecarbono.model.MedioDeTransporte.MedioNoMotorizado;

import java.util.List;
public interface IMedioNoMotorizadoService {
    public List<MedioNoMotorizado> getMedioNoMotorizado();

    public void saveMedioNoMotorizado(MedioNoMotorizado medioMotorizado);

    public void deleteMedioNoMotorizado(Integer id);

    public MedioNoMotorizado findMedioNoMotorizado(Integer id);

}
