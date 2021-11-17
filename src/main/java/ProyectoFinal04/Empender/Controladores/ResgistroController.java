/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import ProyectoFinal04.Empender.Servicios.FotoServicio;
import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Moriconi
 */
@Controller
@RequestMapping("/")
public class ResgistroController {

    @Autowired
    private UsuarioServicio servicioUsuario;
    @Autowired
    private EmprendedorServicio servicioEmprendedor;
    @Autowired
    private FotoServicio servicioFoto;

    //REGISTRO DE CLIENTES
    @GetMapping("registroCliente")
    public String cliente() {
        
        return "registroCliente";
    }
    @PostMapping("/saveUser")
    public String registroUsuario(ModelMap model, @RequestParam String nombre, @RequestParam String username, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

        try {
            servicioUsuario.registrar(null, nombre, username, mail, clave1, clave2);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("username", username);
            model.put("mail", mail);
            model.put("clave1", clave1);
            model.put("clave2", clave2);
            return "registroCliente";
        }
        return "redirect:/home";
    }
    //------------------------------------------------------
    //REGISTRO DE EMPRENDEDOR
    @GetMapping("registroEmprendedor")
    public String emprendedoror() {
        return "registroEmprendedor";
    }
    
    @PostMapping("/save")
    public String registroEmprendedor(ModelMap model, @RequestParam String nombre, @RequestParam String username, @RequestParam String mail, @RequestParam String telefono, @RequestParam String direccion, @RequestParam String clave1, @RequestParam String clave2){

        try {
            servicioEmprendedor.registrar(null, nombre, username, mail, telefono, direccion, clave1, clave2);
        } catch (ErrorServicio ex) {
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("username", username);
            model.put("mail", mail);
            model.put("direccion", direccion);
            model.put("telefono", telefono);
            model.put("clave1", clave1);
            model.put("clave2", clave2);
            return "registroEmprendedor";
        }
        return "redirect:/home";
    }

    

}
