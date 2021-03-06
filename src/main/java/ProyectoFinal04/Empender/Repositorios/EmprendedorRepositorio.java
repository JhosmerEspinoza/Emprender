/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;
import ProyectoFinal04.Empender.Entidades.Emprendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Moriconi
 */
@Repository
public interface EmprendedorRepositorio extends JpaRepository<Emprendedor, String> {

    @Query("SELECT us from Emprendedor us WHERE us.id = :id")
    Emprendedor buscarPorId(@Param("id") String id);
    
}
