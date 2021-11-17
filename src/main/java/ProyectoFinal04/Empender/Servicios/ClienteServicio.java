/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Cliente;
import ProyectoFinal04.Empender.Excepciones.Errores;
import ProyectoFinal04.Empender.Repositorios.ClienteRepositorio;
import java.io.File;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Moriconi
 */
@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio repositorioCliente;
    @Autowired
    private UsuarioServicio servicioUsuario;
    
    @Transactional
    public Cliente save(Cliente usuario){
        return repositorioCliente.save(usuario);
    }
    
    @Transactional
    public void registrar(String nombre, String nombreUsuario, String password){
        servicioUsuario.registrar(nombre, nombreUsuario, password);
    }
    
    @Transactional
    public void modificarTodo(String id,String nombre,String nombreUsuario){     
        Optional<Cliente>rta=repositorioCliente.findById(id);
        if (rta.isPresent()) {
            Cliente cliente=rta.get();
            cliente.setNombre(nombre);   
            cliente.setNombreUsuario(nombreUsuario);
            repositorioCliente.save(cliente); 
        }
    }
    
    public void modificarPass(String id, String claveActual, String claveNueva) throws Errores{
        servicioUsuario.modificarClave(id, claveActual, claveNueva);
    }
    public void ingresarFoto(String id, File foto){
        servicioUsuario.IngresarFoto(id, foto);
    }
}
   

