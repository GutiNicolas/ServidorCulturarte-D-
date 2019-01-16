/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.FIELD)
public class DtarregloDtPropWeb {
     //@XmlElement(name="arreegloDePropuestas", namespace = "")
    
        private ArrayList<DtPropuestaWeb> arregloPropuestas;

    public DtarregloDtPropWeb() {
    }

    /**
     * @return the arregloProp
     */
    public ArrayList<DtPropuestaWeb> getArregloProp() {
        return arregloPropuestas;
    }

    /**
     * @param arregloProp the arregloProp to set
     */
    public void setArregloProp(ArrayList<DtPropuestaWeb> arregloProp) {
        this.arregloPropuestas = arregloProp;
    }
}
