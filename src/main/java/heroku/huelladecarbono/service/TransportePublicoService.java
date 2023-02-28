package heroku.huelladecarbono.service;

import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;
import heroku.huelladecarbono.repository.TransportePublicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TransportePublicoService implements ITransportePublicoService {

    @Autowired
    private TransportePublicoRepository transportePublicoRepository;


    @Override
    public List<TransportePublico> getTransportesPublicos() {
        List <TransportePublico> listaTransportes = transportePublicoRepository.findAll();

        return listaTransportes;
    }

    @Override
    public void saveTransportePublico(TransportePublico transportePublico) {
        transportePublicoRepository.save(transportePublico);
    }

    @Override
    public void deleteTransportePublico(Integer id) {
        transportePublicoRepository.deleteById(id);
    }

    @Override
    public TransportePublico findTransportePublico(Integer id) {
        TransportePublico transportePublico = transportePublicoRepository.findById(id).orElse(null);
        return  transportePublico;
    }
}
