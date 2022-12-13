package utn.frba.huelladecarbono.service;

import com.github.jknack.handlebars.Handlebars;
import utn.frba.huelladecarbono.service.CalculoDeHuellaService.CalculadoraHCService;

public class HandleBars {

    private static Handlebars instance = null;

    public static Handlebars getHandleBars() {
        if (instance == null) {
            instance = new Handlebars();
        }
        return instance;
    }

}
