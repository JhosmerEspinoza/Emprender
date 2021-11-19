/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/emprendedor")
public class EmprendedorController {
    
    @Autowired
    private EmprendedorServicio emprendedorServicio;
    
    
    
    @GetMapping("/actualizarPerfil")
    private String actualizarPerfil(@RequestParam String id, Model model) throws ErrorServicio{
        Optional<Emprendedor> rta = emprendedorServicio.findById(id);
        if(rta.isPresent()){
            Emprendedor user = rta.get();
            model.addAttribute("usuario", user);
        }else{
            throw new ErrorServicio("Ha ocurrido un error");
        }
        return "actualizarPerfil";
    }
    
    
    
}
