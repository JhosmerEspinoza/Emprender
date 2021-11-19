/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("emprender_principal")
    public String feed(){
        return "emprender_principal";
    }
    
    @GetMapping("actualizarPerfil")
    public String editarPerfil(){
        return "actualizarPerfil";
    }
    
   
}
