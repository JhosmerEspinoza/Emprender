
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Repositorios.EmprendedorRepositorio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
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
    public void crearPublicacion(String idEmprendedor, String descripcion, MultipartFile imagen) throws ErrorServicio{
        Emprendedor emprendedor = repositorioEmprendedor.findById(idEmprendedor).get();
        validar(descripcion, imagen);
        
        Publicacion publi = new Publicacion();
        publi.setDescripcion(descripcion);
        
        Foto img = servicioFoto.guardarfoto(imagen);
        publi.setPublicacionIMG(img);
        
        publi.setEmprendedor(emprendedor);
              
        publicacionRepositorio.save(publi);
    }
    
    public void validar(String descripcion, MultipartFile foto) throws ErrorServicio{
        if(descripcion==null || descripcion.isEmpty()){
            throw new ErrorServicio("Agregue una descripcion");
        }
        if(foto == null){
            throw new ErrorServicio("Agregue una foto a su publicacion");
        }
    }
    
    
    @Transactional
    public void modificarPublicacion(MultipartFile img, String idPubli, String idEmprendedor, String texto) throws ErrorServicio{
        validar(texto, img);
        
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(idPubli);
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            if(publi.getEmprendedor().getId().equals(idEmprendedor)){
                publi.setDescripccion(texto);
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
    
    
    /*
    
    
    @Transactional
    public void eliminar(String id) {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Publicacion coment = respuesta.get();
            coment.setAlta(Boolean.FALSE);
           publicacionRepositorio.save(coment);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
    
    @Transactional
    public void IngresarFoto(String id, MultipartFile fotoPerfil) {

        Optional<Publicacion> rta = publicacionRepositorio.findById(id);
        if (rta.isPresent()) {
            Publicacion publicacion = rta.get();
            
            String idFoto = publicacion.getId();
            
            Foto foto = new Foto();
            foto = servicioFoto.modificarFoto(idFoto, fotoPerfil);
            publicacion.setPublicacionIMG(foto);
            publicacionRepositorio.save(publicacion);
        }
    }
    
    @Transactional
    public List<Publicacion> listAll(){
        List<Publicacion> publicaciones = new ArrayList<>();
        DateTimeFormatter estilo = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for (Publicacion publicacion : publicacionRepositorio.findAll()) {
            Publicacion publi = publicacion;
            for (Publicacion publicacion1 : publicacionRepositorio.findAll()) {
                System.out.println(publicacion1.getDescripccion() + "1");
                if (LocalDate.parse(publi.getFecha(), estilo).isBefore(LocalDate.parse(publicacion1.getFecha(), estilo))) {
                    publi = publicacion1;
                     System.out.println("hola");
                }
            }
            if (LocalDate.parse(publi.getFecha(), estilo).isEqual(LocalDate.parse(publicacion.getFecha(), estilo)) || LocalDate.parse(publi.getFecha(), estilo).isBefore(LocalDate.parse(publicacion.getFecha(), estilo))) {
                publicaciones.add(publi);
                System.out.println(publi.getDescripccion()+ "2");
            }
        }
        return publicaciones;
    }*/

    public List<Publicacion> listAll() {
        return publicacionRepositorio.findAll();
    }
    
    
}