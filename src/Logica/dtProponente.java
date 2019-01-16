/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Esteban Menendez
 */
public class dtProponente extends dtUsuario {

    private String direccion;
    private String biografia;
    private String sitioWeb;
    private boolean desactivado;

    public dtProponente(String nombre, String apellido, String nickname, String imagen, String email, dtFecha fechaNac, String direccion, String biografia, String sitioWeb, String pass) {
        super(nombre, apellido, nickname, imagen, email, fechaNac, pass);
        this.direccion = direccion;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;

    }

    public dtProponente(String nombre, String apellido, String nickname, String imagen, String email, dtFecha fechaNac, String direccion, String biografia, String sitioWeb, String pass,boolean desactivado) {
        super(nombre, apellido, nickname, imagen, email, fechaNac, pass);
        this.direccion = direccion;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
        this.desactivado = desactivado;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * @return the sitioWeb
     */
    public String getSitioWeb() {
        return sitioWeb;
    }

    /**
     * @return the desactivado
     */
    public boolean isDesactivado() {
        return desactivado;
    }

    /**
     * @param desactivado the desactivado to set
     */
    public void setDesactivado(boolean desactivado) {
        this.desactivado = desactivado;
    }

}
