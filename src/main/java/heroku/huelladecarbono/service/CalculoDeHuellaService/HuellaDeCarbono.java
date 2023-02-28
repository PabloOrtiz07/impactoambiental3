package heroku.huelladecarbono.service.CalculoDeHuellaService;

import java.time.LocalDate;

public class HuellaDeCarbono {
    String localDate;
    Double valor;

    public HuellaDeCarbono(String localDate, Double valor) {
        this.localDate = localDate;
        this.valor = valor;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
