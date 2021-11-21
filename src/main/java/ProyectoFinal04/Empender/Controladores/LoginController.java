/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/")
public class LoginController {
    
    @GetMapping("/home")
    public String login(Model model, @RequestParam(required=false) String error, @RequestParam(required=false) String logout){
        if(error != null){
            model.addAttribute("error", "Nombre de usuario o contraseña incorrecto");
        }
        if(logout != null){
            model.addAttribute("logout", "Has cerrado sesion correctamente");
        }
        return "home";
    }
    
    /*
    @GetMapping("/verificarCuenta")
    public String verificar(@RequestParam String idUser){
        
    }*/
    
}
