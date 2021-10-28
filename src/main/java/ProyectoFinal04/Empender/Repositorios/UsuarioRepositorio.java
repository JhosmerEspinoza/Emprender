/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;

import ProyectoFinal04.Empender.Entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    //Ejemplo de Query
    @Query("SELECT a FROM Usuario a")
    public List <Usuario> listarUsuario();
    
    @Query("SELECT a FROM Usuario a WHERE a.nombreUsuario like:nombreUsuario")
    public Usuario usuarioInicio(@Param("nombreUsuario") String nombreUsuario);
    
}
