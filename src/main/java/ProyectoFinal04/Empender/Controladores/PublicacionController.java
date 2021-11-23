/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Repositorios.PublicacionRepositorio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@Controller
@RequestMapping("/publicacion")
public class PublicacionController {
    
    @Autowired
    private PublicacionServicio servicioPublicacion;
    @Autowired
    private PublicacionRepositorio repositorioPublicacion;
    
    @GetMapping("/mis-publicaciones")
    public String misPublicaciones(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario == null) {
            return "redirect:/home";
        }
        Publicacion publi = new Publicacion();
        model.addAttribute("publicacion", publi);
        model.addAttribute("publicaciones", servicioPublicacion.buscarPorUsuario(usuario.getId()));
        return "redirect:/emprender_principal"; 
    }
    
    @GetMapping("/feed")
    public String feed(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario == null) {
            return "redirect:/home";
        }
//        Publicacion publi = new Publicacion();
//        model.addAttribute("publicacion", publi);
        model.addAttribute("publicaciones", servicioPublicacion.listAll());
        return "emprender_principal";
    }
    
    @PostMapping("/nuevaPublicacion")
    public String crearP(Model model, HttpSession session, MultipartFile img,@RequestParam String descripcion,@RequestParam String titulo){        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if(usuario == null){
            return "redirect:/home"; 
        }
        Emprendedor emprendedor = (Emprendedor) session.getAttribute("usuariosession");
        try{
            servicioPublicacion.crearPublicacion(emprendedor.getId(), descripcion, img, titulo);
        }catch(ErrorServicio err){
            model.addAttribute("error", err.getMessage());
        }
        return "redirect:/emprender_principal"; 
    }
    
    @PostMapping("/modificarPublicacion")
    public String modificarPublicacion(Model model, HttpSession session, MultipartFile img, String id_publicacion, String titulo, String descripcion) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario == null) {
            return "redirect:/home";
        }
        Emprendedor emprendedor = (Emprendedor) session.getAttribute("usuariosession");
        try {
            if(id_publicacion == null || id_publicacion.isEmpty()){
                servicioPublicacion.crearPublicacion(emprendedor.getId(), descripcion, img, titulo);
            }else{
                servicioPublicacion.modificarPublicacion(img, id_publicacion, emprendedor.getId(), descripcion, titulo);
            }
        } catch (ErrorServicio err) {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(titulo);
            publicacion.setDescripcion(descripcion);
            model.addAttribute("error", err.getMessage());
            model.addAttribute("descripcion", publicacion.getDescripcion());
            model.addAttribute("titulo", publicacion.getTitulo());
            model.addAttribute("img", publicacion.getPublicacionIMG());
            return "redirect:/emprender_principal";
        }
        return "redirect:/emprender_principal";
    }
    
    @DeleteMapping("/eliminarPublicacion")
    public String eliminarPublicacion(HttpSession session, String id_publicacion){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if(usuario == null){
            return "redirect:/home"; 
        }
        try{
            Emprendedor login = (Emprendedor)session.getAttribute("usuariosession");
            servicioPublicacion.eliminarPublicacion(id_publicacion, login.getId());
        }catch (ErrorServicio err){
            Logger.getLogger(PublicacionController.class.getName()).log(Level.SEVERE, null, err);
        }
        return "redirect:/emprender_principal";
    }
    
    
    
}
