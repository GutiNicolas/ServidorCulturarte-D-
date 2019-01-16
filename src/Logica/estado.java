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
public class estado {

    private String nombre;

//METODOS//
    
//CONSTRUCTOR//
    public estado(String nombre) {
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

}
