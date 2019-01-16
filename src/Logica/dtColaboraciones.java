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
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtColaboraciones {

    private String nickname, idPropuesta;
    private dtFecha fecha;

    public dtColaboraciones(String nickname, String idPropuesta, dtFecha fecha) {
        this.nickname = nickname;
        this.idPropuesta = idPropuesta;
        this.fecha = fecha;
    }
//CONSTRUCTOR//
    public dtColaboraciones(String nickname, String idPropuesta) {
        this.nickname = nickname;
        this.idPropuesta = idPropuesta;
    }

//GETTERS//
    public String getNickname() {
        return nickname;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

//SETTERS//
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * @return the fecha
     */
    public dtFecha getFecha() {
        return fecha;
    }

    
}
