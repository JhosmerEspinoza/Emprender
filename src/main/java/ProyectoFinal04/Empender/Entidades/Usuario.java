/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
=======
>>>>>>> 466791f9729f9b7d917e15a6c1416661ea65f64a
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jhosenny
 */
@Entity
<<<<<<< HEAD
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
   @ManyToOne
    private String nombreUsuario;
    private String password;
    private File fotoPerfil;
    @ManyToOne
    private Comentario comentario;

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(File fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
       
=======
public class Usuario implements Serializable {
      @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer Edad;
    private String nombre;
    private String apellido;
    private String nombreDeUsuario;
    private String contrasenha;
    private File foto;
    private Integer seguidores;
    private String descripcion_perfil;
    private String mail;
    private String telefono;
    private String direccion;
    @ManyToOne
    //private Publicacion publicacion;

    
    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer Edad) {
        this.Edad = Edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Integer getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Integer seguidores) {
        this.seguidores = seguidores;
    }
    
    
    
    
    
>>>>>>> 466791f9729f9b7d917e15a6c1416661ea65f64a
}
