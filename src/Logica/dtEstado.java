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
public class dtEstado {
    private int numero;
    private String nombre;

    public dtEstado(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
}
