/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/foto")
public class FotoController {
  @Autowired
  private PublicacionServicio publicacionServicio;
  
  @GetMapping("/publicacion/{id}")
  public ResponseEntity<byte[]> fotoUsuario(@PathVariable String id) throws ErrorServicio{
      try{
        Optional<Publicacion> respuesta = publicacionServicio.buscarPorId(id);
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            if(publi.getPublicacionIMG() == null){
                throw new ErrorServicio("La publicacion no posee imagen");
            }
        byte[] foto = publi.getPublicacionIMG().getContenido();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(foto, headers , HttpStatus.OK);
        } else {
            throw new ErrorServicio("No se ha encontrado la publicacion");
        }
      } catch(ErrorServicio err){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
}
