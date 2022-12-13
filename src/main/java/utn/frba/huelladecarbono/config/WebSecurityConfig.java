package utn.frba.huelladecarbono.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import utn.frba.huelladecarbono.service.IUsuarioService;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    String[] resources = new String[]{
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**", "/static/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/login")
                .authorizeRequests()
                .antMatchers("/miembro/**").permitAll()
                .antMatchers("/miembro/datosPersonales").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/miembro/datosPersonales")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }

    @Configuration
    @Order(2)
    public class WebSecurityConfigOrg extends WebSecurityConfigurerAdapter {

        String[] resources = new String[]{
                "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**", "/static/**"
        };

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/loginOrganizacion").authorizeRequests()
                    .antMatchers(resources).permitAll()
                    .antMatchers("/organizacion/recomendaciones").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/loginOrganizacion")
                    .permitAll()
                    .defaultSuccessUrl("/organizacion/recomendaciones")
                    .failureUrl("/login?error=true")
                    .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/login?logout");
        }


        }

    @Configuration
    @Order(3)
    public  class WebSecurityConfigAgente extends WebSecurityConfigurerAdapter {

        String[] resources = new String[]{
                "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**", "/static/**"
        };

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/loginAgente").authorizeRequests()
                    .antMatchers(resources).permitAll()
                    .antMatchers("/AS/recomendaciones").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/loginAgente")
                    .permitAll()
                    .defaultSuccessUrl("/AS/recomendaciones")
                    .failureUrl("/login?error=true")
                    .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/login?logout");
        }


        }
        @Autowired
        IUsuarioService userDetailsService;

        //Registra el service para usuarios y el encriptador de contrasenaº
        @Autowired
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        }

}
