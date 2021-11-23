

package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Repositorios.EmprendedorRepositorio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PublicacionServicio {
    
    @Autowired
    private EmprendedorRepositorio repositorioEmprendedor;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private FotoServicio servicioFoto;
    
    @Transactional
    public void crearPublicacion(String idEmprendedor, String descripcion, MultipartFile imagen, String titulo) throws ErrorServicio{
        Emprendedor emprendedor = repositorioEmprendedor.findById(idEmprendedor).get();
        validar(descripcion, imagen, titulo);
        
        Publicacion publi = new Publicacion();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        publi.setFecha(dtf.format(LocalDateTime.now()));
        publi.setTitulo(titulo);
        publi.setDescripcion(descripcion);
        
        Foto img = servicioFoto.guardarfoto(imagen);
        publi.setPublicacionIMG(img);
        
        publi.setEmprendedor(emprendedor);
              
        publicacionRepositorio.save(publi);
    }
    
    public void validar(String descripcion, MultipartFile foto, String titulo) throws ErrorServicio{
        if(descripcion==null || descripcion.isEmpty()){
            throw new ErrorServicio("Agregue una descripcion");
        }
        if(foto == null){
            throw new ErrorServicio("Agregue una foto a su publicacion");
        }
        if (titulo == null || descripcion.isEmpty()) {
            throw new ErrorServicio("Agregue un titulo");
        }
    }
    
    
    @Transactional
    public void modificarPublicacion(MultipartFile img, String idPubli, String idEmprendedor, String texto, String titulo) throws ErrorServicio{
        validar(texto, img, titulo);
        
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(idPubli);
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            if(publi.getEmprendedor().getId().equals(idEmprendedor)){
                publi.setTitulo(titulo);
                publi.setDescripcion(texto);
                String idFoto = null;
                if(publi.getPublicacionIMG() != null){
                    idFoto = publi.getPublicacionIMG().getId();
                }
                Foto foto = servicioFoto.modificarFoto(idFoto, img);
                publi.setPublicacionIMG(foto);
                publicacionRepositorio.save(publi);
            }else{
                throw new ErrorServicio("Usted no puede realizar esta operacion");
            } 
        } else {
            throw new ErrorServicio("No se ha encontrado la publicacion");
        }
    }
    
    @Transactional
    public void eliminarPublicacion(String idPubli, String idEmprendedor) throws ErrorServicio {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(idPubli);
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            if (publi.getEmprendedor().getId().equals(idEmprendedor)) {
                publi.setAlta(Boolean.FALSE);
                publicacionRepositorio.save(publi);
            } else {
                throw new ErrorServicio("Usted no puede realizar esta operacion");
            }
        } else {
            throw new ErrorServicio("No se ha encontrado la publicacion");
        }
    }
    
    @Transactional
    public List<Publicacion> buscarPorUsuario(String id) {
        return publicacionRepositorio.buscarPorUsuarioId(id);
    }
    
    @Transactional
    public Optional<Publicacion> buscarPorId(String id) {
        return publicacionRepositorio.findById(id);
    }
    
    @Transactional
    public List<Publicacion> listAll() {
        return publicacionRepositorio.findAll();
    }
    
    public Foto salvarFoto(MultipartFile imagen) throws ErrorServicio{
        Foto img = new Foto();
        return img = servicioFoto.guardarfoto(imagen);
    }
}
