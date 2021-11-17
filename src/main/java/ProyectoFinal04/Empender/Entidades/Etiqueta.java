/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal04.Empender.Entidades;

/**
 *
 * @author lucas
 */
public enum Etiqueta {
    DISEÑO(1, "Diseño"), MODA(2, "Moda"), SALUD(3, "Salud"), DECORACION(4, "Decoracion"), REPOSTERIA(5, "Reposteria"), PANADERIA(6, "Panaderia"), DEPORTE(7, "Deporte"), COSMETICA(8, "Cosmética"), INDUSTRIA(9, "Industria"), PROGRAMACION(10, "Programación");

    private Integer codigo;
    private String etiqueta;

    private Etiqueta(Integer codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

}
