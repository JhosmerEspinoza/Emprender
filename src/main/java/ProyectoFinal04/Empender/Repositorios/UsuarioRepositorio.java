/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;

import ProyectoFinal04.Empender.Entidades.Usuario;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhosenny
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("SELECT user from Usuario user WHERE user.nombreUsuario = :nombreUsuario")
    Usuario findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
    
    
=======
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    //Ejemplo de Query
    @Query("SELECT a FROM Usuario a")
    public List <Usuario> listarUsuario();
>>>>>>> 466791f9729f9b7d917e15a6c1416661ea65f64a
}
