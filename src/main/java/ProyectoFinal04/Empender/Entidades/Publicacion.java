/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
public class Publicacion implements Serializable{
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    private String descripccion;
//    @OneToMany
//    private Etiqueta etiqueta;
    //private List<Etiqueta> etiqueta;
    private Boolean alta;
    @OneToOne
    private Foto publicacionIMG;
    @OneToMany
    private List<Comentario> comentarios;
    private String fecha;
    @ManyToOne
    private Emprendedor emprendor;

    public Publicacion() {
        alta = true;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        fecha = dtf.format(LocalDateTime.now());

    }

//    public Etiqueta getEtiqueta() {
//        return etiqueta;
//    }
//
//    public void setEtiqueta(Etiqueta etiqueta) {
//        this.etiqueta = etiqueta;
//    }

    public Emprendedor getEmprendor() {
        return emprendor;
    }

    public void setEmprendor(Emprendedor emprendor) {
        this.emprendor = emprendor;
    }

    public Foto getPublicacionIMG() {
        return publicacionIMG;
    }

    public void setPublicacionIMG(Foto publicacionIMG) {
        this.publicacionIMG = publicacionIMG;
    }
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario) {
        comentario.setPublicacion(this);
        this.comentarios.add(comentario);
    }
//    public void agregarEtiqueta(Etiqueta etiqueta) {
//        this.etiqueta.add(etiqueta);
//    }

 
}
