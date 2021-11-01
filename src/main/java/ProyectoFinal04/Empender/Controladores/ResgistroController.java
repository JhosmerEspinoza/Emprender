/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Servicios.ClienteServicio;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ClienteServicio servicioCliente;
    @Autowired
    private EmprendedorServicio servicioEmprendedor;
    
    @GetMapping("")
    public String registro(){
        return("index");
    }
    
    @PostMapping("/guardar")
    public String registroSave(Model model, @RequestParam String nombre, @RequestParam String nombreUsuario, @RequestParam String password, @RequestParam String mail, @RequestParam String telefono, @RequestParam String direccion){
        servicioEmprendedor.registrar(nombre, nombreUsuario, password, mail, direccion, telefono);
        return "redirect:/inicioSesion";
    }
    
    @PostMapping("/registro")
    public String registroCliente(Model model, @RequestParam String nombre, @RequestParam String nombreUsuario, @RequestParam String password){
       
        servicioCliente.registrar(nombre, nombreUsuario, password);
        return "redirect:/inicioSesion";
    }
}
