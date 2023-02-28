package heroku.huelladecarbono.model.CalculoDeDistancias;

import org.springframework.context.annotation.Bean;



public class AutenticacionResponse {
    private String email;
    private String token;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Bean
    public String init(){

        return ("the value is: "+token);
    }
}
