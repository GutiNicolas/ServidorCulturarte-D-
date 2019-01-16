/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtColProp {

    //@XmlElement(name = "nickname", namespace = "")
    @XmlAttribute
    private String nick;
    //@XmlElement(name = "retorno", namespace = "")
    @XmlAttribute
    private String retornoCol;
    @XmlElement(name = "fechaCol", namespace = "")
    private dtFecha fechaCol;
    @XmlElement(name = "horaCol", namespace = "")
    private dtHora horaCol;
    // @XmlElement(name = "montoColaborado", namespace = "")
    @XmlAttribute
    private int montoColaboradoCol;
    //@XmlElement(name = "comentario", namespace = "")
    @XmlAttribute
    private String comentario;
    //@XmlElement(name = "titulop", namespace = "")
    @XmlAttribute
    private String titulop;

//CONSTRUCTOR//
    public dtColProp(String nickname, String retorno, dtFecha fecha, dtHora hora, int montoColaborado, String comen) {
        this.nick = nickname;
        this.retornoCol = retorno;
        this.fechaCol = fecha;
        this.horaCol = hora;
        this.montoColaboradoCol = montoColaborado;
        this.comentario = comen;
    }

    public dtColProp(String nickname, dtFecha fecha, dtHora hora, int montoColaborado, String titulo) {
        this.nick = nickname;
        this.fechaCol = fecha;
        this.horaCol = hora;
        this.montoColaboradoCol = montoColaborado;
        this.titulop = titulo;
    }

//GETTERS//
    public String getNickname() {
        return nick;
    }

    public String getRetorno() {
        return retornoCol;
    }

    public dtFecha getFechaCol() {
        return fechaCol;
    }

    public dtHora getHoraCol() {
        return horaCol;
    }

    public int getMontoColaborado() {
        return montoColaboradoCol;
    }

    public String getTitulo() {
        return titulop;
    }

//SETTERS//
    public void setNickname(String nickname) {
        this.nick = nickname;
    }

    public void setRetorno(String retorno) {
        this.retornoCol = retorno;
    }

    public void setMontoColaborado(int montoColaborado) {
        this.montoColaboradoCol = montoColaborado;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    public void setTitulo(String titulo) {
        this.titulop = titulo;
    }

}
