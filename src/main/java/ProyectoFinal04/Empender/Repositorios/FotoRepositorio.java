/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Repositorios;

import ProyectoFinal04.Empender.Entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhosenny
 */
public interface FotoRepositorio extends JpaRepository<Foto, String>{
    
}
