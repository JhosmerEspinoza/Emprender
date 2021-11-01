/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

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
    public void registrar(String nombre, String nombreUsuario, String mail, String password){
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setNombreDeUsuario(nombreUsuario);
        user.setMail(mail);
        user.setContrasenha(password);
        
        repositorioUsuario.save(user);     
    }
    @Transactional
    public void iniciarSesion(String id, String nombreUsuario, String password){
        Optional<Usuario> rta=repositorioUsuario.findById(id); 
        
        if(rta.isPresent()){
            Usuario user=repositorioUsuario.findById(id).get();
            if(password.equals(user.getContrasenha())){
                
            }
        }
        
    }
   
    @Transactional
    public Optional<Usuario> findById(String id){
        return repositorioUsuario.findById(id);
    }
    
        
    
}
