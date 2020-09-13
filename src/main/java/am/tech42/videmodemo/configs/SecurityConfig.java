package am.tech42.videmodemo.configs;

import am.tech42.videmodemo.model.User;
import am.tech42.videmodemo.security.AuthentificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.PrivateKey;

@Configuration
@EnableWebSecurity
@ComponentScan("am.tech42.videmodemo.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthentificationProvider authentificationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login","/register").anonymous()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("mail")
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authentificationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}
