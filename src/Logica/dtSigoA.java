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
 * @author nicolasgutierrez
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtSigoA {
     @XmlElement(name="nickUsuario",namespace = "")
    private String nickusuario;
     @XmlElement(name="rol",namespace = " ")
    private String rol;
     @XmlElement(name="nombreCompleto", namespace = "")
    private String nombrecompleto;

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public dtSigoA(String nickusuario, String rol, String nc) {
        this.nickusuario = nickusuario;
        this.rol = rol;
        this.nombrecompleto=nc;
    }

    public String getNickusuario() {
        return nickusuario;
    }

    public String getRol() {
        return rol;
    }
    
    
}
