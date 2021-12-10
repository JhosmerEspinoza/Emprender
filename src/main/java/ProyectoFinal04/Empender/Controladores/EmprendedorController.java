/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Controladores;

import ProyectoFinal04.Empender.Entidades.Emprendedor;
import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Excepciones.ErrorServicio;
import ProyectoFinal04.Empender.Servicios.EmprendedorServicio;
import ProyectoFinal04.Empender.Servicios.FotoServicio;
import ProyectoFinal04.Empender.Servicios.PublicacionServicio;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@Controller
@RequestMapping("/emprendedor")
public class EmprendedorController {
    
    @Autowired
    private EmprendedorServicio emprendedorServicio;
    @Autowired
    private FotoServicio servicioFoto;
    @Autowired
    private PublicacionServicio servicioPublicacion;
    
    @GetMapping("/profile")
    public String perfil(HttpSession session, Model model, @RequestParam String id) throws ErrorServicio {
        Optional<Emprendedor> rta = emprendedorServicio.findById(id);
        
        Emprendedor login = (Emprendedor) session.getAttribute("usuariosession");
        
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/emprender_principal";
        }
        if (rta.isPresent()) {
            Emprendedor user = rta.get();
            model.addAttribute("usuario", user);
            model.addAttribute("publicas", servicioPublicacion.buscarPorUsuario(user.getId()));
        } else {
            throw new ErrorServicio("Ha ocurrido un error");
        }
        return "profile";
    }
    
    @GetMapping("/actualizarPerfil")
    public String actualizarPerfil(HttpSession session, @RequestParam String id, Model model) throws ErrorServicio {
        Optional<Emprendedor> rta = emprendedorServicio.findById(id);
        
        Emprendedor login = (Emprendedor) session.getAttribute("usuariosession");
        if (login == null || !login.getId().equals(id)) {
            return "redirect:/emprender_principal";
        }
        if (rta.isPresent()) {
            Emprendedor user = rta.get();
            model.addAttribute("usuario", user);
        } else {
            throw new ErrorServicio("Ha ocurrido un error");
        }
        return "actualizarPerfilEmprendedor";
    }

    @PostMapping("/actualizarFoto")
    public String actualizarFoto(Model model, HttpSession session, @RequestParam String id, MultipartFile archivo) throws ErrorServicio {
        Emprendedor user = null;
        try {
            Emprendedor login = (Emprendedor) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/emprender_principal";
            }
            user = emprendedorServicio.buscarPorId(id);
            emprendedorServicio.modificarFoto(archivo, id);
            session.setAttribute("usuariosession", user);
            return "redirect:/emprender_principal";
            
        } catch (ErrorServicio err) {
            System.out.println(err.getMessage());
            model.addAttribute("error", err.getMessage());
            model.addAttribute("usuario", user);
            return "actualizarPerfilEmprendedor";
        }
        
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Model model, HttpSession session,MultipartFile archivo, @RequestParam String id, @RequestParam String nombre, @RequestParam String username, @RequestParam String mail, @RequestParam String telefono, @RequestParam String direccion, @RequestParam String clave1, @RequestParam String clave2) throws ErrorServicio {
        Emprendedor user = null;
        try {
            Emprendedor login = (Emprendedor) session.getAttribute("usuariosession");
            if (login == null || !login.getId().equals(id)) {
                return "redirect:/emprender_principal";
            }
            user = emprendedorServicio.buscarPorId(id);
            emprendedorServicio.modificar(archivo, id, nombre, username, mail, telefono, direccion, clave1, clave2);
            session.setAttribute("usuariosession", user);
            return "redirect:/emprender_principal";
            
        } catch (ErrorServicio err) {
            model.addAttribute("error", err.getMessage());
            model.addAttribute("usuario", user);
            return "actualizarPerfilEmprendedor";
        }
        
    }
    
}

/*
    @PostMapping("/actualizarFoto")
    public String actualizarFoto(Model model, HttpSession session, @RequestParam String idUser, MultipartFile foto) throws ErrorServicio {
        Emprendedor user = null;
        
        try {
            Emprendedor logeado = (Emprendedor) session.getAttribute("usuariosession");
            if (logeado == null || !logeado.getId().equals(idUser)) {
                return "redirect:/home";
            }
            user = emprendedorServicio.buscarPorId(logeado.getId());

            if (user.getFotoPerfil() == null) {
                Foto imgPerfil = servicioFoto.guardarfoto(foto);
                user.setFotoPerfil(imgPerfil);
            } else {
                Foto imgPerfil = servicioFoto.modificarFoto(user.getFotoPerfil().getId(), foto);
                user.setFotoPerfil(imgPerfil);
            }

            session.setAttribute("usuariosession", user);
            return "redirect:/emprender_principal";

        } catch (ErrorServicio err) {
            model.addAttribute("error", err.getMessage());
            model.addAttribute("usuario", user);
            return "actualizarPerfilEmprendedor";
        }

    }*/
