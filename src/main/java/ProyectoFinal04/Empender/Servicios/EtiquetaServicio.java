/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Etiqueta;
import ProyectoFinal04.Empender.Repositorios.EtiquetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhosenny
 */
@Service
public class EtiquetaServicio {
    @Autowired
    private EtiquetaRepositorio etiquetarepositorio;
   
    
    public void crearEtiqueta(String contenido){
//       contenido = "%"+contenido+"%";
//       publicacionRepository.buscarPublicacionesPorEtiqueta(contenido);
        Etiqueta etiqueta=new Etiqueta();
        etiqueta.setContenido(contenido);
        etiquetarepositorio.save(etiqueta);   
    }
   
    
    public Etiqueta validar(String contenido){      
        if (etiquetarepositorio.buscarEtiqueta(contenido)==null) {
            crearEtiqueta(contenido);
        }
        return etiquetarepositorio.buscarEtiqueta(contenido);
    }
    
}




