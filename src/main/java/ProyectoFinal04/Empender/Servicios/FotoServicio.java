/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Servicios;

import ProyectoFinal04.Empender.Entidades.Foto;
import ProyectoFinal04.Empender.Repositorios.FotoRepositorio;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Moriconi
 */
@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotorepositorio;

    @Transactional
    public Foto guardarfoto(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fotorepositorio.save(foto);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Foto modificarFoto(String id, MultipartFile archivo) {
        if (archivo != null) {
            try {
                Foto foto = new Foto();

                if (id != null) {
                    Optional<Foto> rta = fotorepositorio.findById(id);
                    if (rta.isPresent()) {
                        foto = rta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fotorepositorio.save(foto);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
}
