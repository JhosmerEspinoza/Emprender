package ProyectoFinal04.Empender;

import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmpenderApplication {
        
        @Autowired
        private UsuarioServicio servicioUsuario;
        
	public static void main(String[] args) {
		SpringApplication.run(EmpenderApplication.class, args);
	}
        
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(servicioUsuario).passwordEncoder(new BCryptPasswordEncoder());
        }
}
