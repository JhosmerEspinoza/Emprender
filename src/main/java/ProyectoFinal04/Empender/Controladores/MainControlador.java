/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jhosenny
 */
@Controller
@RequestMapping("/")
public class MainControlador {
    
    @GetMapping("editarPerfil")
    public String editProfile(){
        return "EditarPerfil";
    }
    @GetMapping("inicio")
    public String inicio(){
        return "inicio";
    }
    @GetMapping("feed")
    public String feed(){
        return "feed";
    }
    @GetMapping("registroImagen")
    public String registroimg(){
        return "registroImagen";
    }
    @GetMapping("sIndex")
    public String index2(){
        return "sIndex";
    }
    @GetMapping("navbar")
    public String nav(){
        return "navbar";
    }
    @GetMapping("profile")
    public String perfil(){
        return "profile";
    }
   
}
