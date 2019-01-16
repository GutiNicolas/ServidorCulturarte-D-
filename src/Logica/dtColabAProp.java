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
public class dtColabAProp {

    private String nombre;
    int montoColaborado;
    dtFecha fecha;
    dtHora hora;
    String retorno;    
    
//CONSTRUCTOR//
    public dtColabAProp(String nombre, int montoColaborado, dtFecha fecha, dtHora hora, String retorno) {
        this.nombre = nombre;
        this.montoColaborado = montoColaborado;
        this.fecha = fecha;
        this.hora = hora;
        this.retorno = retorno;
    }

//GETTERS//
    public String getNombre() {
        return nombre;
    }

    public int getMontoColaborado() {
        return montoColaborado;
    }

    public dtFecha getFecha() {
        return fecha;
    }

    public dtHora getHora() {
        return hora;
    }

    public String getRetorno() {
        return retorno;
    }

//SETTERS//
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMontoColaborado(int montoColaborado) {
        this.montoColaborado = montoColaborado;
    }

    public void setFecha(dtFecha fecha) {
        this.fecha = fecha;
    }

    public void setHora(dtHora hora) {
        this.hora = hora;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    
}
