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
public class dtCola {
    
    protected String titulo, estado, proponente;
    protected int monto_recaudado;
    
    public dtCola(String titulo, String estado, String proponente, int monto_recaudado){
        this.titulo=titulo;
        this.estado=estado;
        this.proponente=proponente;
        this.monto_recaudado=monto_recaudado;
    
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEstado() {
        return estado;
    }

    public String getProponente() {
        return proponente;
    }

    public int getMonto_recaudado() {
        return monto_recaudado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setProponente(String proponente) {
        this.proponente = proponente;
    }

    public void setMonto_recaudado(int monto_recaudado) {
        this.monto_recaudado = monto_recaudado;
    }
    
    
}
