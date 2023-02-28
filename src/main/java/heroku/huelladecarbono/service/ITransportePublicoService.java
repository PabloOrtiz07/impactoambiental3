package heroku.huelladecarbono.service;



import heroku.huelladecarbono.model.MedioDeTransporte.TransportePublico;

import java.util.List;

public interface ITransportePublicoService {

    public List<TransportePublico> getTransportesPublicos();

    public void saveTransportePublico(TransportePublico transportePublico);

    public void deleteTransportePublico(Integer id);

    public TransportePublico findTransportePublico(Integer id);
}
