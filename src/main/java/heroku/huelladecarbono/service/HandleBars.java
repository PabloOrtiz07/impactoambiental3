package heroku.huelladecarbono.service;

import com.github.jknack.handlebars.Handlebars;

public class HandleBars {

    private static Handlebars instance = null;

    public static Handlebars getHandleBars() {
        if (instance == null) {
            instance = new Handlebars();
        }
        return instance;
    }

}
