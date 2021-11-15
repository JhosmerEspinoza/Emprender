/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Excepciones.Errores;
import ProyectoFinal04.Empender.Repositorios.EmprendedorRepositorio;
import java.io.File;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@Service
public class EmprendedorServicio{

    @Autowired
    private EmprendedorRepositorio repositorioEmprendedor;
    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private FotoServicio fotoservicio;

    @Transactional
    public Emprendedor save(Emprendedor usuario) {
        return repositorioEmprendedor.save(usuario);
    }

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String nombreUsuario, String password, String mail, String direccion, String telefono) {
        Emprendedor emprendedor = new Emprendedor();
        emprendedor.setNombre(nombre);
        emprendedor.setNombreUsuario(nombreUsuario);
        emprendedor.setPassword(password);
        emprendedor.setMail(mail);
        emprendedor.setDireccion(direccion);
        emprendedor.setTelefono(telefono);
        Foto foto = fotoservicio.guardarfoto(archivo);
        emprendedor.setFoto(foto);
        repositorioEmprendedor.save(emprendedor);

    }

    @Transactional
    public void modificarTodo(MultipartFile archivo, String id, String nombre, String descripcionPerfil, String nombreUsuario, String mail, String telefono, String direccion) {

        Optional<Emprendedor> rta = repositorioEmprendedor.findById(id);
        if (rta.isPresent()) {
            Emprendedor emprendedor = rta.get();
            emprendedor.setNombre(nombre);
            emprendedor.setNombreUsuario(nombreUsuario);
            emprendedor.setMail(mail);
            emprendedor.setTelefono(telefono);
            emprendedor.setDireccion(direccion);
            emprendedor.setDescripcion_perfil(descripcionPerfil);
            String idFoto = null;
            if (emprendedor.getFoto() != null) {
                idFoto = emprendedor.getFoto().getId();
            }
            Foto foto = fotoservicio.modificarFoto(idFoto, archivo);
            emprendedor.setFoto(foto);
            repositorioEmprendedor.save(emprendedor);
        }
    }
    
   

    public void modificarPass(String id, String claveActual, String claveNueva) throws Errores {
        servicioUsuario.modificarClave(id, claveActual, claveNueva);
    }

    public void ingresarFoto(String id, File foto) {
        servicioUsuario.IngresarFoto(id, foto);
    }

}
