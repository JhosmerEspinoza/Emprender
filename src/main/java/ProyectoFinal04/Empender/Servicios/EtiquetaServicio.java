/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Etiqueta;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Repositorios.EtiquetaRepositorio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jhosenny
 */
@Service
public class EtiquetaServicio {
    @Autowired
    private EtiquetaRepositorio etiquetarepositorio;
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
   
    @Transactional
    private void crearEtiqueta(Publicacion publicacion ){
//       contenido = "%"+contenido+"%";
//       publicacionRepository.buscarPublicacionesPorEtiqueta(contenido);
//        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
//        if (respuesta.isPresent()) {
//             Publicacion publi = respuesta.get();
             Etiqueta etiqueta=new Etiqueta();
             etiqueta.setContenido(publicacion.getDescripccion());
             etiqueta.setPublicacion(publicacion);
//             publi.agregarEtiqueta(etiqueta);
             etiquetarepositorio.save(etiqueta);
             
       // }
       
    }
     @Transactional
    private Etiqueta validar(Publicacion publicacion){      
        if (etiquetarepositorio.buscarEtiqueta(publicacion.getDescripccion())==null) {
            crearEtiqueta(publicacion);
        }
        return etiquetarepositorio.buscarEtiqueta(publicacion.getDescripccion());
    }
    
     @Transactional
    public void CargarEtiqueta(Publicacion publicacion){
        Pattern pattern = Pattern.compile("#([a?-z?])+");
        Matcher matcher = pattern.matcher(publicacion.getDescripccion());

        if (matcher.find()) {
            String resultado =publicacion.getDescripccion().substring(publicacion.getDescripccion().indexOf("#"));
            String[] eliminar = resultado.split("#");
            for (int i = 1; i < eliminar.length; i++) {
                System.err.println(eliminar[i]);
            Optional<Publicacion> respuesta = publicacionRepositorio.findById(publicacion.getId());
        if (respuesta.isPresent()) {
            Publicacion publi = respuesta.get();
            publi.setEtiqueta(validar(publicacion));
            publicacionRepositorio.save(publi);
        }      
            }  
        }
        }
    
}




