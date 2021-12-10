/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;

import ProyectoFinal04.Empender.Entidades.Publicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Moriconi
 */
@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String>{
    
    
    @Query("SELECT c FROM Publicacion c WHERE c.emprendedor.id = :id ORDER BY c.fecha DESC")
    public List<Publicacion> buscarPorUsuarioId(@Param("id") String id);
    
    
}
