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
    @Autowired
    private Comentario comentario;
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    public void crearComentario(String texto, String cliente_id, String emprendedor_id){
        Comentario comentario = new Comentario();
        comentario.setTexto(texto);
        comentario.setCliente_id(cliente_id);
        comentario.setEmprendedor_id(emprendedor_id);
        comentarioRepositorio.save(comentario);
    }
    
    public void eliminar(String id, String titulo) {
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Comentario comentario = respuesta.get();
            comentario.setAlta(Boolean.FALSE);
           comentarioRepositorio.save(comentario);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
}
