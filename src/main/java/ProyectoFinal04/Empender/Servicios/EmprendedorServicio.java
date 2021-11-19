/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Repositorios.EmprendedorRepositorio;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import ProyectoFinal04.Empender.enums.Roles;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@Service
public class EmprendedorServicio {

    @Autowired
    private EmprendedorRepositorio repositorioEmprendedor;
    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private UsuarioRepositorio repositorioUsuario;
    @Autowired
    private NotificacionServicio servicioNotif;
    @Autowired
    private FotoServicio servicioFoto;

    @Transactional
    public void registrar(MultipartFile fotoPerfil, String nombre, String nombreUsuario, String mail, String telefono, String direccion, String password, String password2) throws ErrorServicio {
        validar(nombre, nombreUsuario, mail, telefono, direccion, password, password2);
        if (findByNombreUsuario(nombreUsuario) != null) {
            throw new ErrorServicio("El nombre de usuario ya existe");
        }
        Emprendedor user = new Emprendedor();
        user.setNombre(nombre);
        user.setNombreUsuario(nombreUsuario);
        user.setMail(mail);
        user.setTelefono(telefono);
        user.setDireccion(direccion);
        //Encriptamiento de password
        String passEncriptado = new BCryptPasswordEncoder().encode(password);
        user.setPassword(passEncriptado);
        //Seteo de rol
        user.setRol(Roles.EMPRENDEDOR);
        //Seteo imagen
        Foto foto = servicioFoto.guardarfoto(fotoPerfil);
        user.setFotoPerfil(foto);
        //
        repositorioUsuario.save(user);
        //servicioNotificacion.enviarMail("Bienvenidx a emprender", "Emprender", user.getMail());
    }

    @Transactional
    public void modificar(String id, String nombre, String username, String mail, String telefono, String direccion, String password, String password2) throws ErrorServicio {
        validar(nombre, username, mail, telefono, direccion, password, password2);
        if (findByNombreUsuario(username) != null) {
            throw new ErrorServicio("El nombre de usuario ya existe");
        }

        Optional<Emprendedor> rta = repositorioEmprendedor.findById(id);
        if (rta.isPresent()) {
            Emprendedor user = rta.get();
            user.setNombre(nombre);
            user.setNombreUsuario(username);
            user.setMail(mail);
            user.setTelefono(telefono);
            user.setDireccion(direccion);
            String passEncriptado = new BCryptPasswordEncoder().encode(password);
            user.setPassword(passEncriptado);
            repositorioEmprendedor.save(user);
        } else {
            throw new ErrorServicio("Usuario no encontrado");
        }

    }

    private void validar(String nombre, String nombreUsuario, String mail, String telefono, String direccion, String password, String password2) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser vacio");
        }
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
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
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El numero de telefono no puede ser vacio");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new ErrorServicio("La direccion no puede ser vacia");
        }
    }

    @Transactional
    public void modificarTodo(String id, String nombre, String descripcionPerfil, String nombreUsuario, String mail, String telefono, String direccion) {

        Optional<Emprendedor> rta = repositorioEmprendedor.findById(id);
        if (rta.isPresent()) {
            Emprendedor emprendedor = rta.get();
            emprendedor.setNombre(nombre);
            emprendedor.setNombreUsuario(nombreUsuario);
            emprendedor.setMail(mail);
            emprendedor.setTelefono(telefono);
            emprendedor.setDireccion(direccion);
            emprendedor.setDescripcion_perfil(descripcionPerfil);
        }
    }

    @Transactional
    public void modificar(Emprendedor usuario) throws Exception {
        if (findByNombreUsuario(usuario.getNombreUsuario()) != null) {
            throw new Exception("El nombre de usuario ya existe");
        }
        //Encriptamiento de password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = usuario.getPassword();
        usuario.setPassword(encoder.encode(password));

        repositorioEmprendedor.save(usuario);

    }

    @Transactional
    public void modificarPass(String id, String claveActual, String claveNueva) throws ErrorServicio {
        servicioUsuario.modificarClave(id, claveActual, claveNueva);
    }

    public Optional<Emprendedor> findById(String id) {
        return repositorioEmprendedor.findById(id);
    }
        
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repositorioUsuario.findByNombreUsuario(nombreUsuario);
    }

    /*
    @Transactional
    public Emprendedor save(Emprendedor usuario) throws Exception {
        if (findByNombreUsuario(usuario.getNombreUsuario()) != null) {
            throw new Exception("El nombre de usuario ya existe");
        }
        //Encriptamiento de password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = usuario.getPassword();
        usuario.setPassword(encoder.encode(password));
        //Seteo de rol
        usuario.setRol(Roles.EMPRENDEDOR);
        
        servicioNotif.enviarMail("Bienvenidos a emprender", "Emprender", usuario.getMail());
        return repositorioEmprendedor.save(usuario);
        
    }

     */
}
