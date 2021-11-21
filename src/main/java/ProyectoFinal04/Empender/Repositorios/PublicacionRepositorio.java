/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;

import ProyectoFinal04.Empender.Entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Moriconi
 */
@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String>{
    
}
