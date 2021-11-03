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
 * @author Moriconi
 */
@Controller
@RequestMapping("/inicio")
public class UsuarioController {
    
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
