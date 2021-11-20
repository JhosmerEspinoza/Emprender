/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PublicacionServicio {
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private FotoServicio servicioFoto;
    
    @Transactional
    public Publicacion crearPublicacion(Publicacion publicacion){
        publicacionRepositorio.save(publicacion);
        return publicacion;
    }
     
    @Transactional
    public void modificarPublicacion(String id, String texto) {
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Publicacion coment = respuesta.get();
            coment.setDescripccion(texto);
            publicacionRepositorio.save(coment);
        } else {
            //throw new ErrorServicio("El autor ingresado no se encuentra");
        }
    }
    
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
    }
}
