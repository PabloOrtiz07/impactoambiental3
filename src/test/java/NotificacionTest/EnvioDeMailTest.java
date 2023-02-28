package NotificacionTest;

import org.junit.jupiter.api.Assertions;
import heroku.huelladecarbono.service.NotificacionService.EnviarMail;
import org.junit.Test;

public class EnvioDeMailTest {
    @Test
    public void testEnvioDeMail() {
        Assertions.assertDoesNotThrow(() -> {
            String MailDestino = "tphcgrupo3@gmail.com";
            EnviarMail EnviadorDeMail = new EnviarMail();
            EnviadorDeMail.send(MailDestino);
        });
    }

}
