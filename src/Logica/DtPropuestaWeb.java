/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtPropuestaWeb {

   // @XmlAttribute
    private String tituloP;
 //@XmlAttribute
    private String descripcionP;
 //@XmlAttribute
    private String imagenP;
 //@XmlAttribute
    private String lugarP;
// @XmlAttribute
    private String estadoP;
// @XmlAttribute
    private String categoriaP;
// @XmlAttribute
    private String proponenteP;
// @XmlAttribute
    private String retornoP;
// @XmlAttribute
    private String fechaRealizacionP;
// @XmlAttribute
    private String fechapublicadaP;
// @XmlAttribute
    private int precioentradaP;
// @XmlAttribute
    private int montorequeridoP;
// @XmlAttribute
    private int montoactualP;
 //@XmlElement
// @XmlList
    private List<String> colaboradoresP;

    public DtPropuestaWeb() {
    }

    public String getTitulo() {
        return tituloP;
    }

    public String getDescripcion() {
        return descripcionP;
    }

    public String getImagen() {
        return imagenP;
    }

    public String getLugar() {
        return lugarP;
    }

    public String getEstado() {
        return estadoP;
    }

    public String getCategoria() {
        return categoriaP;
    }

    public String getProponente() {
        return proponenteP;
    }

    public String getRetorno() {
        return retornoP;
    }

    public String getFechaRealizacion() {
        return fechaRealizacionP;
    }

    public String getFechapublicada() {
        return fechapublicadaP;
    }

    public int getPrecioentrada() {
        return precioentradaP;
    }

    public int getMontorequerido() {
        return montorequeridoP;
    }

    public int getMontoactual() {
        return montoactualP;
    }

    public List<String> getColaboradores() {
        return colaboradoresP;
    }

    public void setTitulo(String titulo) {
        this.tituloP = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionP = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagenP = imagen;
    }

    public void setLugar(String lugar) {
        this.lugarP = lugar;
    }

    public void setEstado(String estado) {
        this.estadoP = estado;
    }

    public void setCategoria(String categoria) {
        this.categoriaP = categoria;
    }

    public void setProponente(String proponente) {
        this.proponenteP = proponente;
    }

    public void setRetorno(String retorno) {
        this.retornoP = retorno;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacionP = fechaRealizacion;
    }

    public void setFechapublicada(String fechapublicada) {
        this.fechapublicadaP = fechapublicada;
    }

    public void setPrecioentrada(int precioentrada) {
        this.precioentradaP = precioentrada;
    }

    public void setMontorequerido(int montorequerido) {
        this.montorequeridoP = montorequerido;
    }

    public void setMontoactual(int montoactual) {
        this.montoactualP = montoactual;
    }

    public void setColaboradores(List<String> colaboradores) {
        this.colaboradoresP = colaboradores;
    }

}
