/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import ProyectoFinal04.Empender.enums.Roles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jhosenny
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repositorioUsuario;
    @Autowired
    private FotoServicio servicioFoto;
    @Autowired
    private NotificacionServicio servicioNotificacion;

    @Transactional
    public void registrar(MultipartFile fotoPerfil, String nombre, String nombreUsuario, String mail, String password, String password2) throws ErrorServicio {
        validar(nombre, nombreUsuario, mail, password, password2);
        if (findByNombreUsuario(nombreUsuario) != null) {
            throw new ErrorServicio("El nombre de usuario ya existe");
        }
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setNombreUsuario(nombreUsuario);
        user.setMail(mail);
        //Encriptamiento de password
        String passEncriptado = new BCryptPasswordEncoder().encode(password);
        user.setPassword(passEncriptado);
        //Seteo de rol
        user.setRol(Roles.CLIENTE);
        //Seteo imagen
        Foto foto = servicioFoto.guardarfoto(fotoPerfil);
        user.setFotoPerfil(foto);
        //

        
        /* MAIL DE CONFIRMACION
        String codigoVerificacion =  user.getId().substring(0,5)+String.valueOf((int) (Math.random()*1000) );
        user.setCodigoVerificacion(codigoVerificacion);
        
        String mensaje =  "Bienvenidx a emprender, ingrese al siguiente link para verificar su cuenta: ";
        String link = "localhost:8080/verificarCuenta?idUser="+user.getId()+"&verificacion="+user.getCodigoVerifiacion();
        servicioNotificacion.enviarMailRegistro(mensaje, "Emprender", user.getMail(), link);
        */
        
        repositorioUsuario.save(user);
        

    }

    @Transactional
    public void modificar(String id, String nombre, String username, String mail, String password, String password2) throws ErrorServicio {
        validar(nombre, username, mail, password, password2);
        if (findByNombreUsuario(username) != null) {
            throw new ErrorServicio("El nombre de usuario ya existe");
        }

        Optional<Usuario> rta = repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario user = rta.get();
            user.setNombre(nombre);
            user.setNombreUsuario(username);
            user.setMail(mail);
            String passEncriptado = new BCryptPasswordEncoder().encode(password);
            user.setPassword(passEncriptado);
            repositorioUsuario.save(user);
        } else {
            throw new ErrorServicio("Usuario no encontrado");
        }

    }

    public Optional<Usuario> findById(String id) {
        return repositorioUsuario.findById(id);
    }
    public Usuario buscarPorId(String id){
        return repositorioUsuario.buscarPorId(id);
    }
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repositorioUsuario.findByNombreUsuario(nombreUsuario);
    }

    @Transactional
    public void IngresarFoto(String id, MultipartFile fotoPerfil) {

        Optional<Usuario> rta = repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario = rta.get();

            String idFoto = usuario.getFotoPerfil().getId();

            Foto foto = new Foto();
            foto = servicioFoto.modificarFoto(idFoto, fotoPerfil);
            usuario.setFotoPerfil(foto);
            repositorioUsuario.save(usuario);
        }
    }

    @Transactional
    public void modificarClave(String id, String claveActual, String claveNueva) throws ErrorServicio {
        Optional<Usuario> rta = repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario = rta.get();

            if (usuario.getPassword().equals(claveActual)) {
                usuario.setPassword(claveNueva);
                repositorioUsuario.save(usuario);
            } else {
                throw new ErrorServicio("Contraseña actual incorrecta");
            }

        }
    }

    //Logeo de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorioUsuario.findByNombreUsuario(username);
        if (usuario != null) {
            List<GrantedAuthority> authorities = new ArrayList();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO"));

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            return new User(username, usuario.getPassword(), authorities);//authorities son los permisos
        } else {
            return null;
        }

    }

    /*
    @Transactional
    public Usuario save(Usuario user) throws Exception {

        if (findByNombreUsuario(user.getNombreUsuario()) != null) {
            throw new Exception("El nombre de usuario ya existe");
        }
        //Encriptamiento de password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        //Seteo de rol
        user.setRol(Roles.CLIENTE);
        //Seteo imagen

        //
        return repositorioUsuario.save(user);

    }
     */
    private void validar(String nombre, String username, String mail, String password, String password2) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser vacio");
        }
        if (username == null || username.isEmpty()) {
            throw new ErrorServicio("El nombre de usuario no puede ser vacio");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser vacio");
        }
        if (password == null || password.isEmpty() || password.length() <= 6) {
            throw new ErrorServicio("La clave tiene que tener mas de 6 digitos");
        }
        if (!password.equals(password2)) {
            throw new ErrorServicio("Las claves deben ser iguales");
        }
        if(!validarUsername(username)){
            throw new ErrorServicio("El nombre de usuario no puede tener espacios en blanco");
        }
    }
    private Boolean validarUsername(String username){
        int espacios = 0;
        for(int i=0; i<username.length(); i++){
            if (username.charAt(i) == ' ') espacios++;
        }
        return espacios==0;
    }
}
