/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Esteban Menendez
 */

public class dtPropuesta {

   
    private String titulo;
    
    private String descripcion;
    
    private String imagen;
    
    private String lugar;
 
    private String estado;
    
    private String categoria;
  
    private String proponente;
    
    private String retorno;
    
    private dtFecha fechaRealizacion;
    
    private dtFecha fechapublicada;
 
    private int precioentrada;
    
    private int montorequerido;
    
    private int montoactual;
    
    private List<String> colaboradores;

//CONSTRUCTOR//
    
    
    public dtPropuesta(dtPropuesta dt, int monto) {
        this.titulo = dt.getTitulo();
        this.descripcion = dt.getDescripcion();
        this.imagen = dt.getImagen();
        this.lugar = dt.getLugar();
        this.estado = dt.getEstado();
        this.categoria = dt.getCategoria();
        this.proponente = dt.getProponente();
        this.fechaRealizacion = dt.getFechaRealizacion();
        this.fechapublicada = dt.getFechapublicada();
        this.precioentrada = dt.getPrecioentrada();
        this.montorequerido = dt.getMontorequerido();
        this.montoactual = monto;
        this.retorno = dt.getRetorno();
        this.colaboradores = (ArrayList) dt.detColaboradores();
    }

    public dtPropuesta(String titulo, String descripcion, String imagen, String lugar, String estado, String categoria, String proponente, dtFecha fechaRealizacion, dtFecha fechapublicada, int precioentrada, int montorequerido, int montoactual, String retorno) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.estado = estado;
        this.categoria = categoria;
        this.proponente = proponente;
        this.fechaRealizacion = fechaRealizacion;
        this.fechapublicada = fechapublicada;
        this.precioentrada = precioentrada;
        this.montorequerido = montorequerido;
        this.montoactual = montoactual;
        this.retorno = retorno;
        this.colaboradores = new ArrayList<String>();
    }

    /*public dtPropuesta(String titulo, String descripcion, String imagen, String lugar, String estado, String categoria, String proponente, dtFecha fechaRealizacion, dtFecha fechapublicada, int precioentrada, int montorequerido, int montoactual, String retorno, List<String> colaboradores) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.estado = estado;
        this.categoria = categoria;
        this.proponente = proponente;
        this.retorno = retorno;
        this.fechaRealizacion = fechaRealizacion;
        this.fechapublicada = fechapublicada;
        this.precioentrada = precioentrada;
        this.montorequerido = montorequerido;
        this.montoactual = montoactual;
        this.colaboradores = colaboradores;
    }*/

    public dtPropuesta(String titulo, String proponente) {
        this.titulo = titulo;
        this.proponente = proponente;
    }

    public boolean colabora(String nick) {
        boolean ret = false;
        Iterator it = this.colaboradores.iterator();
        while (it.hasNext()) {
            String c = (String) it.next();
            if (c.equals(nick)) {
                ret = true;
            }
        }
        return ret;
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

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProponente() {
        return proponente;
    }

    public dtFecha getFechaRealizacion() {
        return fechaRealizacion;
    }

    public dtFecha getFechapublicada() {
        return fechapublicada;
    }

    public int getPrecioentrada() {
        return precioentrada;
    }

    public int getMontorequerido() {
        return montorequerido;
    }

    public List<String> detColaboradores() {
        return colaboradores;
    }

    public int getMontoTotal() {
        return getMontoactual();
    }

    public void setMontoactual(int montoactual) {
        this.montoactual = montoactual;
    }
    
    

    /**
     * @return the retorno
     */
    public String getRetorno() {
        return retorno;
    }
public void addColaboradores(List<String> cola){
this.colaboradores= (List<String>) cola;
}

    /**
     * @return the montoactual
     */
    public int getMontoactual() {
        return montoactual;
    }
}
