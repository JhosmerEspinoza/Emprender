/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Usuario;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.UsuarioServicio;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioServicio servicioUsuario;

    
    @GetMapping("/actualizarPerfil")
    public String actualizarPerfil(HttpSession session, @RequestParam String id, Model model) throws ErrorServicio {

        Usuario login = (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/emprender_principal";
        }

        Optional<Usuario> rta = servicioUsuario.findById(id);
        if (rta.isPresent()) {
            Usuario user = rta.get();
            model.addAttribute("usuario", user);
        } else {
            throw new ErrorServicio("Ha ocurrido un error");
        }
        return "actualizarPerfil";
    }

    
    @PostMapping("/actualizar")
    public String actualizar(Model model, HttpSession session, @RequestParam String id, @RequestParam String nombre, @RequestParam String username, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) throws ErrorServicio {
        Usuario user = null;
        try {
            Usuario login = (Usuario) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/emprender_principal";
            }
            
            user = servicioUsuario.buscarPorId(id);
            servicioUsuario.modificar(id, nombre, username, mail, clave1, clave2);
            session.setAttribute("usuariosession", user);
            return "redirect:/emprender_principal";

        } catch (ErrorServicio err) {
            model.addAttribute("error", err.getMessage());
            model.addAttribute("usuario", user);
            return "actualizarPerfil";
        }

    }
}
