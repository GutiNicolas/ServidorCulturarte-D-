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
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtUsuario {

    /**
     *
     */
    @XmlElement(name = "nombre", namespace = "")
    protected String nombre;
    @XmlElement(name = "apellido", namespace = "")
    protected String apellido;
    @XmlElement(name = "nickname", namespace = "")
    protected String nickname;
    @XmlElement(name = "imagen", namespace = "")
    protected String imagen;
    @XmlElement(name = "email", namespace = "")
    protected String email;
    @XmlElement(name = "pass", namespace = "")
    protected String pass;
    @XmlElement(name = "fechaNac", namespace = "")
    protected dtFecha fechaNac;
    
    protected String rol;
    
    @XmlElement(name = "puntaje", namespace = "")
    protected int puntaje;

    public dtUsuario(String nombre, String apellido, String nickname, String imagen, String email, dtFecha fechaNac, String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.imagen = imagen;
        this.email = email;
        this.fechaNac = fechaNac;
        this.pass = pass;
    }

    /**
     * Constructor que almacena el puntaje del usuario para el Ranking Web
     */
    public dtUsuario(String nombre, String apellido, String nickname, int puntaje) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.puntaje = puntaje;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the fechaNac
     */
    public dtFecha getFechaNac() {
        return fechaNac;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getPuntaje() {
        return puntaje;
    }

}
