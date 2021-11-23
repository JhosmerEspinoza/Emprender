/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.ComentarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@Controller
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioServicio cs;
   
    @PostMapping("/nuevoComentario")
    public String guardarComentario(Model model, HttpSession session, String id_publicacion, String texto) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if(usuario == null){
            return "redirect:/home"; 
        }
        try {
            cs.crearComentario(usuario.getId(), id_publicacion, texto);
        } catch (ErrorServicio err) {
            model.addAttribute("error", err.getMessage());
        }
        return "redirect:/emprender_principal";
    }
    
    @DeleteMapping("/eliminarComentario")
    public String eliminarComentario(HttpSession session, String id_comentario){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        if (usuario == null) {
            return "redirect:/home";
        }
        try{
            Usuario login = (Usuario)session.getAttribute("usuariosession");
            cs.eliminarComentario(id_comentario, login.getId());
        }catch (ErrorServicio err){
            Logger.getLogger(ComentarioController.class.getName()).log(Level.SEVERE, null, err);
        }
        return "redirect:/emprender_principal";
    }
    
}
