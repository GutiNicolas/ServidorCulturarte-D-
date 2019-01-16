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
 * @author jp
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtSigoAWeb {
    //  @XmlElement(name="nickUsuario",namespace = "")
    private String nickusuarioS;
    // @XmlElement(name="rol",namespace = " ")
    private String rolS;
  //   @XmlElement(name="nombreCompleto", namespace = "")
    private String nombrecompletoS;

    public DtSigoAWeb() {
    }

    public String getNickusuario() {
        return nickusuarioS;
    }

    public String getRol() {
        return rolS;
    }

    public String getNombrecompleto() {
        return nombrecompletoS;
    }

    public void setNickusuario(String nickusuario) {
        this.nickusuarioS = nickusuario;
    }

    public void setRol(String rol) {
        this.rolS = rol;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompletoS = nombrecompleto;
    }
     
     
}
