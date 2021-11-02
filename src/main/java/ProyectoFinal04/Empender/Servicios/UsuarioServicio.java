/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.Errores;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import java.io.File;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhosenny
 */
@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio repositorioUsuario;
    
    
    @Transactional
    public void registrar(String nombre, String nombreUsuario, String password){
        Usuario user=new Usuario();
        user.setNombre(nombre);
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        
        repositorioUsuario.save(user);
   
    }
    
    
    @Transactional
    public Optional<Usuario> findById(String id){
        return repositorioUsuario.findById(id);
    }
    
    
    @Transactional
    public void IngresarFoto(String id, File foto){
        
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setFotoPerfil(foto);
            repositorioUsuario.save(usuario);
        }
    }
    @Transactional
    public void modificarClave(String id,String claveActual,String claveNueva)throws Errores{
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            
            if (usuario.getPassword().equals(claveActual)){
                usuario.setPassword(claveNueva);
                repositorioUsuario.save(usuario);
            }else{
                throw new Errores("Contraseña actual incorrecta");
            }
        }   
    }
         
 
  
      
}
