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
 * @author nicolasgutierrez
 */
public class dtColaboracionCompleto {

    private String nickname;
    private String titulo;
    private String retorno;
    private int monto;
    private dtFecha fecha;
    private dtHora hora;
    private String comentario;
    private String estado;
    private String proponente;
/*
    /**
     * constructor de dtColaboracionCompleto
     */
    public dtColaboracionCompleto(String n, String t, dtFecha dtf, dtHora dth, int m, String r, String comen) {
        this.nickname = n;
        this.titulo = t;
        this.fecha = dtf;
        this.hora = dth;
        this.monto = m;
        this.retorno = r;
        if (comen != null) {
            this.comentario = comen;
        }
    }

    /**
     * constructor de dtCola
     */
  /*  public dtColaboracionCompleto(String titulo, String estado, String proponente, int monto_recaudado) {
        this.titulo = titulo;
        this.estado = estado;
        this.proponente = proponente;
        this.monto = monto_recaudado;
    }*/

    /**
     * constructor de dtColaboraciones
     */
   /* public dtColaboracionCompleto(String nickname, String idPropuesta, dtFecha fecha) {
        this.nickname = nickname;
        this.titulo = idPropuesta;
        this.fecha = fecha;
    }*/

    /**
     * constructor de dtColaboraciones
     */
  /*  public dtColaboracionCompleto(String nickname, String idPropuesta) {
        this.nickname = nickname;
        this.titulo = idPropuesta;
    }*/

    /**
     * Constructor de dtColProp
     */
   /* public dtColaboracionCompleto(String nickname, String retorno, dtFecha fecha, dtHora hora, int montoColaborado, String comen) {
        this.nickname = nickname;
        this.retorno = retorno;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = montoColaborado;
        this.comentario = comen;
    }*/

    /**
     * Constructor de dtColProp
     */
  /*  public dtColaboracionCompleto(String nickname, dtFecha fecha, dtHora hora, int montoColaborado, String titulo) {
        this.nickname = nickname;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = montoColaborado;
        this.titulo = titulo;
    }*/

    public String getNickname() {
        return nickname;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getRetorno() {
        return retorno;
    }

    public int getMonto() {
        return monto;
    }

    public dtFecha getFecha() {
        return fecha;
    }

    public dtHora getHora() {
        return hora;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the proponente
     */
    public String getProponente() {
        return proponente;
    }

}
