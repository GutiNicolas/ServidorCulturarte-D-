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
 * @author juan
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtCategoria {
    private String nombre;
    private String padre;
    private dtCategoria linkP;

    public dtCategoria(String nombre, String padre, dtCategoria linkP) {
        this.nombre = nombre;
        this.padre = padre;
        this.linkP = linkP;
    }

    public dtCategoria(String nombre,String padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the padre
     */
    public String getPadre() {
        return padre;
    }
    
    
}
