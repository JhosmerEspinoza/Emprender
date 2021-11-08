/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.Errores;
import ProyectoFinal04.Empender.Repositorios.EmprendedorRepositorio;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import ProyectoFinal04.Empender.enums.Roles;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        return repositorioEmprendedor.save(usuario);
    }

    @Transactional
    public void registrar(String nombre, String nombreUsuario, String password, String mail, String direccion, String telefono) {
        Emprendedor emprendedor = new Emprendedor();
        emprendedor.setNombre(nombre);
        emprendedor.setNombreUsuario(nombreUsuario);
        emprendedor.setPassword(password);
        emprendedor.setMail(mail);
        emprendedor.setDireccion(direccion);
        emprendedor.setTelefono(telefono);

        repositorioEmprendedor.save(emprendedor);

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
            repositorioEmprendedor.save(emprendedor);
        }
    }

    @Transactional
    public void modificarPass(String id, String claveActual, String claveNueva) throws Errores {
        servicioUsuario.modificarClave(id, claveActual, claveNueva);
    }

    @Transactional
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repositorioUsuario.findByNombreUsuario(nombreUsuario);
    }

}
