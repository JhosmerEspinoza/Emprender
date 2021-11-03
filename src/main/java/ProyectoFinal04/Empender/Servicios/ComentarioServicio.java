/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Comentario;
import ProyectoFinal04.Empender.Repositorios.ComentarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicio {
//    @Autowired
//    private Comentario comentario;
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    
    public void crearComentario(String texto){
        Comentario coment = new Comentario();
        coment.setTexto(texto);
        comentarioRepositorio.save(coment);
    }
    
    public void eliminar(String id, String titulo) {
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Comentario coment = respuesta.get();
            coment.setAlta(Boolean.FALSE);
           comentarioRepositorio.save(coment);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
}
