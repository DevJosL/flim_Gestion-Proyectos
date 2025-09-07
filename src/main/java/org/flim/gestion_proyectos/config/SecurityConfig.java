package org.flim.gestion_proyectos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
        Definimos reglas de seguridad (spring security redirige automaticamente a una pagina de login,
        por lo que no funciona y de una manda al register entonces hay que definir practicamente
        que todos puedan entrar al login
        la ruta para verificar su funcionamiento es
        http://localhost:8090/register.xhtml
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/register.xhtml").permitAll()
                                .requestMatchers("/javax.faces.resource/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login.xhtml")
                                .permitAll()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}