/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Esteban Menendez
 */
public class categoria {
    private categoria padre;
    private String nombre;

    public categoria(categoria padre, String nombre) {
        this.padre = padre;
        this.nombre = nombre;
    }

//CONSTRUCTOR//
    public categoria(String nombre) {
        this.nombre = nombre;
    }

//GETTER//
    public String getNombre() {
        return nombre;
    }

//SETTER//
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the padre
     */
    public categoria getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(categoria padre) {
        this.padre = padre;
    }

    
    
}

