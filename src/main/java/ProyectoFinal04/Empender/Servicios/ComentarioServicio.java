
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Comentario;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Repositorios.ComentarioRepositorio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
>>>>>>> a10b5829a2417d64746c7c7676e2dab17538bd4d
@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    
    @Transactional
    public Comentario crearComentario(Comentario coment, String id){
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            publi.agregarComentario(coment);
            comentarioRepositorio.save(coment);
            publicacionRepositorio.save(publi);
        }
        return coment;
    }
    
    @Transactional
    public void modificarComentario(String id, String texto) {
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Comentario coment = respuesta.get();
            coment.setTexto(texto);
            comentarioRepositorio.save(coment);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
    
    @Transactional
    public void eliminar(String id) {
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Comentario coment = respuesta.get();
            coment.setAlta(Boolean.FALSE);
           comentarioRepositorio.save(coment);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
<<<<<<< HEAD
}
=======
}*/
