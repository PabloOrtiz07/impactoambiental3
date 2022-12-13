package utn.frba.huelladecarbono.service;

import utn.frba.huelladecarbono.model.MedioDeTransporte.MedioMotorizado;

import java.beans.IntrospectionException;
import java.util.List;

public interface IMedioMotorizadoService {
    public List<MedioMotorizado> getMedioMotorizado();

    public void saveMedioMotorizado(MedioMotorizado medioMotorizado);

    public void deleteMedioMotorizado(Integer id);

    public MedioMotorizado findMedioMotorizado(Integer id);
}
