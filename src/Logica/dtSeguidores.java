/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author nicolasgutierrez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtSeguidores {
    
    String nickusuario, nickaseguir;

    public dtSeguidores(String nickusuario, String nickaseguir) {
        this.nickusuario = nickusuario;
        this.nickaseguir = nickaseguir;
    }

    public String getNickusuario() {
        return nickusuario;
    }

    public String getNickaseguir() {
        return nickaseguir;
    }
    
    
    
    
}
