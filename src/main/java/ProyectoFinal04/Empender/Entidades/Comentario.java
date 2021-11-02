/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String texto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPublicacion;
    private String cliente_id;
    private String emprendedor_id;
    private Boolean alta;

    public Comentario() {
        alta = true;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getEmprendedor_id() {
        return emprendedor_id;
    }

    public void setEmprendedor_id(String emprendedor_id) {
        this.emprendedor_id = emprendedor_id;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
}
