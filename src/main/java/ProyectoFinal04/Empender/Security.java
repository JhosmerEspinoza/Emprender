/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender;

import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Moriconi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
    
    
    //Autenticacion y autorizaciones
    
    //UserDetailService -> loadByUsername -> UsuarioServicio
    @Autowired
    private UsuarioServicio servicioUsuario;
    
    //Metodo para configurar autenticacion
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(servicioUsuario).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    //Configuracion peticiones HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*").permitAll()
                .and().formLogin()
                    .loginPage("/home")
                    .usernameParameter("nombreUsuario")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .loginProcessingUrl("/logincheck")
                    .failureUrl("/home?error=error")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home?logout")
                .and().csrf().disable();
    }
    
    
}
