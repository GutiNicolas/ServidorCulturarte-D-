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
public class dtCreadoresPropuestas {
    String nickproponente,tituloprop;
    
    public dtCreadoresPropuestas(String n, String t){
        this.nickproponente=n;
        this.tituloprop=t;
    }

    public String getNickproponente() {
        return nickproponente;
    }

    public String getTituloprop() {
        return tituloprop;
    }

    public void setNickproponente(String nickproponente) {
        this.nickproponente = nickproponente;
    }

    public void setTituloprop(String tituloprop) {
        this.tituloprop = tituloprop;
    }
    
    
}
