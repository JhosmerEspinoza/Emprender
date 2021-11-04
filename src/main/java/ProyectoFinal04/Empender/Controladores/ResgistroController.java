/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Servicios.ClienteServicio;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import ProyectoFinal04.Empender.Servicios.FotoServicio;
import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/")
public class ResgistroController{
    
    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private EmprendedorServicio servicioEmprendedor;
    @Autowired
    private FotoServicio servicioFoto;
  
    
    @GetMapping("")
    public String usuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "index";
    }
    
    
    @GetMapping("registroEmprendedor")
    public String emprendedoror(Model model){
        model.addAttribute("emprendedor", new Emprendedor());
        return "registroEmprendedor";
    }
    

    
    @PostMapping("/save")
    public String registroEmprendedor(@ModelAttribute Emprendedor emprendedor) throws Exception{
        servicioEmprendedor.save(emprendedor);
        return "redirect:/registroEmprendedor";
    }
    
    @PostMapping("/saveUser")
    public String registroUsuario(@ModelAttribute Usuario usuario) throws Exception{
        servicioUsuario.save(usuario);
        return "redirect:/registroEmprendedor";
    }
    

}
