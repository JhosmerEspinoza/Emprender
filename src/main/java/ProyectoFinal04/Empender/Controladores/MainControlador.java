/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jhosenny
 */
@Controller
@RequestMapping("inicio")
public class MainControlador {
    
    @GetMapping("index")
    public String index(){
        return "index";
    }
    
}
