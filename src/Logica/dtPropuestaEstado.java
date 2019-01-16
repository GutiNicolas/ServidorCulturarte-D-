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
public class dtPropuestaEstado {

    private String titulo, estado;
    private String fecha, hora;
    private String fechaFin;

    public dtPropuestaEstado(String titulo, String estado, String fecha, String hora, String fechaFin) {
        this.titulo = titulo;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaFin = fechaFin;
    }

    /* 
    public dtPropuestaEstado(String titulo, String estado, String fecha, String hora){
        this.estado=estado;
        this.titulo=titulo;
        this.fecha=fecha;
        this.hora=hora;
    }
     */
    public String getEstado() {
        return this.estado;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getHora() {
        return this.hora;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }
}
