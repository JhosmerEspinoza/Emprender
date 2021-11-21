/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private EmprendedorServicio servicioEmprendedor;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @PostMapping("/actualizarPerfil")
    public String actualizar_perfil(ModelMap model, HttpSession session, Emprendedor emprendedor){
        Usuario user = null;
        try{
            user = servicioEmprendedor.findByNombreUsuario(emprendedor.getNombreUsuario());
            servicioEmprendedor.modificar(emprendedor);
            session.setAttribute("usuariosession", user);
            return "redirect:/feed";
            
        }catch(Exception ex){
            model.put("error", ex.getMessage());
            model.put("perfil", user);
            return "redirect:/actualizarPerfil";
        }
    }
    
}
