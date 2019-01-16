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
public class dtPropuestasBD {
    
    String titulo, nickproponente, descripcion, imagen, lugar, categoria, retorno;
    dtFecha fecha, fecha_publicacion;
    int precio_entrada, monto_necesario;

    public dtPropuestasBD(String titulo, String nickproponente, String descripcion, String imagen, String lugar, String categoria, String retorno, dtFecha fecha, dtFecha fecha_publicacion, int precio_entrada, int monto_necesario) {
        this.titulo = titulo;
        this.nickproponente = nickproponente;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.categoria = categoria;
        this.retorno = retorno;
        this.fecha = fecha;
        this.fecha_publicacion = fecha_publicacion;
        this.precio_entrada = precio_entrada;
        this.monto_necesario = monto_necesario;
    }

    public dtPropuestasBD(String titulo, String nickproponente) {
        this.titulo = titulo;
        this.nickproponente = nickproponente;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNickproponente() {
        return nickproponente;
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

    public String getCategoria() {
        return categoria;
    }

    public String getRetorno() {
        return retorno;
    }

    public dtFecha getFecha() {
        return fecha;
    }

    public dtFecha getFecha_publicacion() {
        return fecha_publicacion;
    }

    public int getPrecio_entrada() {
        return precio_entrada;
    }

    public int getMonto_necesario() {
        return monto_necesario;
    }
    
    
    
    
    
}
