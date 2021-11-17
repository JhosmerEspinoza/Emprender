/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Comentario;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Servicios.ComentarioServicio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PublicacionControlador {
        
    @Autowired
    private PublicacionServicio ps;
    @Autowired
    private ComentarioServicio cs;    
    
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("publicacion", new Publicacion());
        model.addAttribute("publicaciones", ps.listAll());
        model.addAttribute("comentario", new Comentario());
//        model.addAttribute(archivo);
        return "index";
    }
    
  
    @PostMapping("/guardarPublicacion")
    public String guardarPublicacion(ModelMap model, MultipartFile archivo, Publicacion publicacion) {
        ps.IngresarFoto(ps.crearPublicacion(publicacion).getId(), archivo);
        model.addAttribute("publicaciones", ps.listAll());
        return "redirect:/";
    }
    
    @PostMapping("/guardarComentario")
    public String guardarComentario(@ModelAttribute Comentario coment, @RequestParam String id_publicacion) {
        cs.crearComentario(coment, id_publicacion);
        return "redirect:/";
    }
}
