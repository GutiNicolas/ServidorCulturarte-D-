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
 public class dtEstadosPropuestas {

    String tituloprop, estado;
    dtFecha fecha;
    dtHora hora;
    dtFecha fechaFin;

    public dtEstadosPropuestas(String tituloprop, String estado, dtFecha fecha, dtHora hora,dtFecha fechaF) {
        this.tituloprop = tituloprop;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaFin = fechaF;
    }

    public dtEstadosPropuestas(String tituloprop, String estado, dtFecha fecha, dtHora hora) {
        this.tituloprop = tituloprop;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getTituloprop() {
        return tituloprop;
    }

    public String getEstado() {
        return estado;
    }

    public dtFecha getFecha() {
        return fecha;
    }

    public dtHora getHora() {
        return hora;
    }

    /**
     * @return the horaFin
     */
  

    /**
     * @return the fechaFin
     */
    public dtFecha getFechaFin() {
        return fechaFin;
    }

}
