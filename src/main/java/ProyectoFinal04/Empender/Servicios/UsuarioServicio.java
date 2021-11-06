/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Excepciones.Errores;
import java.io.File;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import ProyectoFinal04.Empender.enums.Roles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    /*
    @Transactional
    public void registrar(String nombre, String nombreUsuario, String password){
        Usuario user=new Usuario();
        user.setNombre(nombre);
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        
        repositorioUsuario.save(user);
    }
     */
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
        
        return repositorioUsuario.save(user);
    }

    @Transactional
    public Optional<Usuario> findById(String id) {
        return repositorioUsuario.findById(id);
    }

    @Transactional
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
    public void modificarClave(String id, String claveActual, String claveNueva) throws Errores {
        Optional<Usuario> rta = repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario = rta.get();

            if (usuario.getPassword().equals(claveActual)) {
                usuario.setPassword(claveNueva);
                repositorioUsuario.save(usuario);
            } else {
                throw new Errores("Contraseña actual incorrecta");
            }

        }
    }

    //Logeo de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = repositorioUsuario.findByNombreUsuario(username);
            User user;
            List<GrantedAuthority> authorities = new ArrayList();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol()));
            return new User(username, usuario.getPassword(), authorities);//authorities son los permisos
        } catch (Exception e) {
            throw new UsernameNotFoundException("Nombre de usuario incorrecto");
        }

    }

}
