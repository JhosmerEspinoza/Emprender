/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Publicacion;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jhosenny
 */
@Controller
@RequestMapping("/")
public class MainControlador {

    @Autowired
    private PublicacionServicio servicioPublicacion;

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("emprender_principal")
    public String feed(HttpSession session, @RequestParam(required = false) String idUser, Model model) {
        Publicacion publi = new Publicacion();
        model.addAttribute("publicacion", publi);
        
        
        model.addAttribute("publicaciones", servicioPublicacion.listAll());
        return "emprender_principal";
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPRENDEDORs')")
    @PostMapping("/nueva")
    public String crearP(Model model, HttpSession session, MultipartFile img, String descripcion, String titulo) {

        Emprendedor emprendedor = (Emprendedor) session.getAttribute("usuariosession");
        try {
            servicioPublicacion.crearPublicacion(emprendedor.getId(), descripcion, img, titulo);

        } catch (ErrorServicio err) {
            System.out.println(err.getMessage());
            model.addAttribute("error", err.getMessage());
        }
        return "redirect:/emprender_principal";

    }

}
