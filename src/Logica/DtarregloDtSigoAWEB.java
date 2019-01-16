/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.FIELD)
public class DtarregloDtSigoAWEB {
     // @XmlElement(name = "listaSeguidos", namespace = "")
  
    private List<DtSigoAWeb> arregloSigoAusu;

    public DtarregloDtSigoAWEB() {
    }

    /**
     * @return the arregloSigoA
     */
    public List<DtSigoAWeb> getArregloSigoA() {
        return arregloSigoAusu;
    }

    /**
     * @param arregloSigoA the arregloSigoA to set
     */
    public void setArregloSigoA(List<DtSigoAWeb> arregloSigoA) {
        this.arregloSigoAusu = arregloSigoA;
    }
}
