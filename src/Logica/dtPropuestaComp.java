/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtPropuestaComp {

    private String titulo, descripcion, imagen, lugar, fechaPublicada, estado, categoria;
    private dtFecha fechaRealizacion;
    private int precioEntrada;
    private int montoRequerido;
    private float montoRecaudado;
   

//CONSTRUCTOR//
    public dtPropuestaComp(String titulo, String descripcion, String imagen, String lugar, String fechaPublicada, String estado, String categoria, dtFecha fechaRealizacion, int precioEntrada, int montoRequerido, float montoRecaudado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.fechaPublicada = fechaPublicada;
        this.estado = estado;
        this.categoria = categoria;
        this.fechaRealizacion = fechaRealizacion;
        this.precioEntrada = precioEntrada;
        this.montoRequerido = montoRequerido;
        this.montoRecaudado = montoRecaudado;
       
    }

//GETTERS//
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFechaPublicada() {
        return fechaPublicada;
    }

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public dtFecha getFechaRealizacion() {
        return fechaRealizacion;
    }

    public int getPrecioEntrada() {
        return precioEntrada;
    }

    public int getMontoRequerido() {
        return montoRequerido;
    }

    public float getMontoRecaudado() {
        return montoRecaudado;
    }

//SETTERS//
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFechaPublicada(String fechaPublicada) {
        this.fechaPublicada = fechaPublicada;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFechaRealizacion(dtFecha fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public void setMontoRequerido(int montoRequerido) {
        this.montoRequerido = montoRequerido;
    }

    public void setMontoRecaudado(float montoRecaudado) {
        this.montoRecaudado = montoRecaudado;
    }

}
