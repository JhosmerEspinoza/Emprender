/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

import java.io.File;

/**
 *
 * @author lucas
 */
public class Publicacion {
    
    private String etiqueta;
    private String descripccion;
    private File foto;
    private String comentario;
    private String id;

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Publicacion(String etiqueta, String descripccion, File foto, String comentario, String id) {
        this.etiqueta = etiqueta;
        this.descripccion = descripccion;
        this.foto = foto;
        this.comentario = comentario;
        this.id = id;
    }
    
    
}
