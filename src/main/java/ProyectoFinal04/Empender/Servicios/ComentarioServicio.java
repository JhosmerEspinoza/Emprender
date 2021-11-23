
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Comentario;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Repositorios.ComentarioRepositorio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import ProyectoFinal04.Empender.Repositorios.UsuarioRepositorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearComentario(String usuarioId, String publicacionId, String texto) throws ErrorServicio{
            validar(texto);        
            Usuario usuario = usuarioRepositorio.findById(usuarioId).get();
            Comentario comentario = new Comentario();
            Optional<Publicacion> respuesta = publicacionRepositorio.findById(publicacionId);
            if (respuesta.isPresent()) {
                Publicacion publi = respuesta.get();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                comentario.setFecha(dtf.format(LocalDateTime.now()));
                comentario.setTexto(texto);
                comentario.setPublicacion(publi);
                comentario.setUsuario(usuario);
                comentarioRepositorio.save(comentario);
            }else{
                throw new ErrorServicio("No se puede realizar el comentario");
            }
    }
    
    @Transactional
    public void eliminarComentario(String idComentario, String idUsuario) throws ErrorServicio {
        Optional<Comentario> respuesta = comentarioRepositorio.findById(idComentario);
        if (respuesta.isPresent()) {
            Comentario comentario = respuesta.get();
            if (comentario.getUsuario().getId().equals(idUsuario)) {
                comentario.setAlta(Boolean.FALSE);
                comentarioRepositorio.save(comentario);
            } else {
                throw new ErrorServicio("Usted no puede realizar esta operacion");
            }
        } else {
            throw new ErrorServicio("No se ha encontrado el comentario");
        }
    }
    
    @Transactional
    public List<Comentario> listById(String id) {
        return comentarioRepositorio.buscarPorId(id);
    }
/*
>>>>>>> a10b5829a2417d64746c7c7676e2dab17538bd4d
    
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
*/
    public void validar(String descripcion) throws ErrorServicio {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("Agregue una descripcion");
        }
    }
}
