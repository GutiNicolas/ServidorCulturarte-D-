/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.DtUsuarioWeb;
import Logica.dtPago;
import Logica.dtSigoA;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author juan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class dtContieneArray {

    @XmlElement(name = "myarreglo", namespace = "")
    private ArrayList<String> myArreglo;
    @XmlElement(name = "nombreArray", namespace = "")
    private String nombreArray;
   


    public dtContieneArray(ArrayList myArreglo, String nombreArray) {
        this.myArreglo = myArreglo;
        this.nombreArray = nombreArray;
    }

   

    

  

    /**
     * Clase que contiene ArrayList para usar en Web recibe dos parametros el
     * arreglo y un nombre para el arreglo
     *
     * @param myArreglo
     * @param nombreArray
     */
    public dtContieneArray() {
    }

    /**
     * @return the myArreglo
     */
    public ArrayList getMyArreglo() {
        return myArreglo;
    }

    /**
     * @return the nombreArray
     */
    public String getNombreArray() {
        return nombreArray;
    }

    /**
     * @param myArreglo the myArreglo to set
     */
    public void setMyArreglo(ArrayList myArreglo) {
        this.myArreglo = myArreglo;
    }

    /**
     * @param nombreArray the nombreArray to set
     */
    public void setNombreArray(String nombreArray) {
        this.nombreArray = nombreArray;
    }

    /**
     * @return the usuarios
     */
   
}
