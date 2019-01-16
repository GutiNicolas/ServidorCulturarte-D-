/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtColaboracionCompWeb {
  //  @XmlElement(name = "nickname", namespace = "")
    private String nickname;
  //  @XmlElement(name = "titulo", namespace = "")
    private String titulo;
 ///   @XmlElement(name = "retorno", namespace = "")
    private String retorno;
 //   @XmlElement(name = "monto", namespace = "")
    private int monto;
 //   @XmlElement(name = "fecha", namespace = "")
    private String fecha;
 //   @XmlElement(name = "hora", namespace = "")
    private String hora;
 //   @XmlElement(name = "comentario", namespace = "")
    private String comentario;
//    @XmlElement(name = "estado", namespace = "")
    private String estado;
//    @XmlElement(name = "proponente", namespace = "")
    private String proponente;

    public DtColaboracionCompWeb() {
    }

    /**
     * @return the nickname
     */
    public String getNick() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNick(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the titulo
     */
    public String getTituloP() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTituloP(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the retorno
     */
    public String getRetornoP() {
        return retorno;
    }

    /**
     * @param retorno the retorno to set
     */
    public void setRetornoP(String retorno) {
        this.retorno = retorno;
    }

    /**
     * @return the monto
     */
    public int getMontoC() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMontoC(int monto) {
        this.monto = monto;
    }

    /**
     * @return the fecha
     */
    public String getFechaC() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFechaC(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHoraC() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHoraC(String hora) {
        this.hora = hora;
    }

    /**
     * @return the comentario
     */
    public String getComentarioC() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentarioC(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the estado
     */
    public String getEstadoP() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstadoP(String estado) {
        this.estado = estado;
    }

    /**
     * @return the proponente
     */
    public String getProponenteP() {
        return proponente;
    }

    /**
     * @param proponente the proponente to set
     */
    public void setProponenteP(String proponente) {
        this.proponente = proponente;
    }
}
