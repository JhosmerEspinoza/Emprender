/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/publicacion")
public class PublicacionController {
    
    @Autowired
    private PublicacionServicio servicioPublicacion;
    
    @PostMapping("/nueva")
    public String crearP(Model model, HttpSession session, MultipartFile img, String descripcion){
        Emprendedor emprendedor = (Emprendedor) session.getAttribute("usuariosession");
        try{
            servicioPublicacion.crearPublicacion(emprendedor.getId(), descripcion, img);
        }catch(ErrorServicio err){
            model.addAttribute("error", err.getMessage());
        }
        return "redirect:/emprender_principal";
        
    }
}
