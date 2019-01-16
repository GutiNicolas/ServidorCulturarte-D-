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
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtInfoProp {

    private 
    //Lista usuarios;
    float montoRecaudado;

//CONSTRUCTOR//
    public dtInfoProp(float montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

//GETTER//
    public float getMontoRecaudado() {
        return montoRecaudado;
    }

//SETTER//
    public void setMontoRecaudado(float montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

    
    
}
