/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ProyectoFinal04.Empender.Entidades.Etiqueta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jhosenny
 */
@Repository
public interface EtiquetaRepositorio extends JpaRepository<Etiqueta, Integer> {
    
    @Query("SELECT * from Etiqueta WHERE contenido = :contenido")
    Etiqueta buscarEtiqueta(@Param("contenido") String contenido);
    
//    @Query("SELECT p from Publicacion p WHERE contenido = :contenido")
//    
//    
    
}