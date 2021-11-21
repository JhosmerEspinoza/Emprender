/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/inicio")
public class UsuarioController {
    @Autowired
    private UsuarioServicio servicioUsuario;
    
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    /*
    @PostMapping("/save")
    public String registroUsuario(@ModelAttribute Usuario user) throws Exception{
        servicioUsuario.save(user);
        return "redirect:/index";
    }
    */
}
