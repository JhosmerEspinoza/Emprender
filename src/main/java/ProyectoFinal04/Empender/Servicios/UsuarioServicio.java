/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import java.io.File;
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
    public void registrar(String nombre, String apellido, String nombreUsuario, String mail, String password)  {
        
        validar(nombre, apellido, nombreUsuario, mail, password);
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setNombreDeUsuario(nombreUsuario);
        user.setMail(mail);
        user.setContrasenha(password);
        
        repositorioUsuario.save(user);     
    }
    @Transactional
    public void modificarTodo(String id,String nombre,String nombreUsuario,String mail){     
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setNombre(nombre);   
            usuario.setNombreDeUsuario(nombreUsuario);
            repositorioUsuario.save(usuario); 
        }
        }
    @Transactional
    public void mIngresarFoto(String id,File foto)throws Error{
         if(foto==null){
            throw new Error("El Nombre no puede ser NULL o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setFoto(foto);
            repositorioUsuario.save(usuario); 
        }
        }
    @Transactional
    public void modificarNombre(String id,String nombre)throws Error{
         if(nombre==null || nombre.isEmpty()){
            throw new Error("El Nombre no puede ser NULL o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setNombre(nombre);
            repositorioUsuario.save(usuario); 
        }
        }
    @Transactional
    public void modificarApellido(String id,String apellido)throws Error{
         if(apellido==null || apellido.isEmpty()){
            throw new Error("El apellido no puede ser nulo o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setApellido(apellido);
            repositorioUsuario.save(usuario); 
        }
        }
    @Transactional
    public void modificarNombreUsuario(String id,String nombreUsuario)throws Error{
         if(nombreUsuario==null || nombreUsuario.isEmpty()){
            throw new Error("El nombre del usuario no puede ser nulo o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setNombreDeUsuario(nombreUsuario);
            repositorioUsuario.save(usuario); 
        }   
    }
    @Transactional
    public void modificarMail(String id,String mail)throws Error{
         if(mail==null || mail.isEmpty()){
            throw new Error("El correo electronico no puede ser nulo o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setMail(mail);
            repositorioUsuario.save(usuario); 
        }   
    }
   @Transactional
   public void modificarDescripcion(String id,String descripcion)throws Error{
         if(descripcion==null || descripcion.isEmpty()){
            throw new Error("La descripcion no puede ser nula o estar vacia.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setDescripcion_perfil(descripcion);
            repositorioUsuario.save(usuario); 
        }   
    }
   @Transactional
   public void modificarDirrecion(String id,String direccion)throws Error{
         if(direccion==null || direccion.isEmpty()){
            throw new Error("La direccion no puede ser nula o estar vacia.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setDireccion(direccion);
            repositorioUsuario.save(usuario); 
        }   
    }
   @Transactional
   public void modificarTelefono(String id,String telefono)throws Error{
         if(telefono==null || telefono.isEmpty()){
            throw new Error("El tenefono no puede ser nulo o estar vacio.");
        }
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setTelefono(telefono);
            repositorioUsuario.save(usuario); 
        }   
    }
    @Transactional
    public void modificarClave(String id,String claveActual,String claveNueva1,String claveNueva2)throws Error{
         validarNuevaClave(claveActual, claveNueva1, claveNueva2);
        Optional<Usuario>rta=repositorioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario usuario=rta.get();
            usuario.setContrasenha(claveNueva2);
            repositorioUsuario.save(usuario); 
        }   
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
    public void validarNuevaClave(String claveActual,String nuevaClave1,String nuevaClave2)throws Error{
        if (claveActual==null || claveActual.isEmpty()) {
            throw new Error("La clave no puede ser null o estar vacia.");
        }
        if (nuevaClave1==null || nuevaClave1.isEmpty()) {
            throw new Error("La clave no puede ser null o estar vacia.");
        }
        if (nuevaClave2==null || nuevaClave2.isEmpty()) {
            throw new Error("La clave no puede ser null o estar vacia.");
        }
        if (!nuevaClave1.equals(nuevaClave2)) {
            throw new Error("Las claves no son las mismas");
        }
        
    }
    public void validar(String nombre, String apellido, String nombreUsuario, String mail, String password)throws Error{
        
          if(nombre==null || nombre.isEmpty()){
            throw new Error("El Nombre no puede ser NULL o estar vacio.");
        }
        if(apellido==null || apellido.isEmpty()){
            throw new Error("El apellido no puede ser NULL o estar vacio.");
        }
        if(nombreUsuario==null || nombreUsuario.isEmpty()){
            throw new Error("El Nombre no puede ser NULL o estar vacio.");
        }
        if(mail==null || mail.isEmpty()){
            throw new Error("El mail no puede ser NULL o estar vacio.");
        }
        if(password==null || password.isEmpty() || password.length() < 6) {
            throw new Error("La clave no puede  estar vacia y tiene que ser mayor a 6 caracteres.");
        }
    }
    
    
  
            
            
    
    
        
    
}
