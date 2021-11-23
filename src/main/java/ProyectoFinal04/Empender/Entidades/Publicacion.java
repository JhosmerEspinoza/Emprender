/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private String titulo;
    private String descripcion;
    private Boolean alta;
    @OneToOne
    private Foto publicacionIMG;
    @ManyToOne
    private Emprendedor emprendedor;

    
    private String fecha;

    public Publicacion() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        fecha = dtf.format(LocalDateTime.now());
        alta = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripccion) {
        this.descripcion = descripccion;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }
    
}