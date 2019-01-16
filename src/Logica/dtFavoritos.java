/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *dtContenedor de favoritos puede contener dos dt o dos string 
 * @author juan
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtFavoritos {
    private String usuario;
    private String propuestaTitulo;
    private dtPropuesta propDt;
    private dtUsuario usuarioDt;
/**
 *constructor: los parametros a no usar setearlos como null
 */
    public dtFavoritos(String usuario, String propuestaTitulo, dtPropuesta propDt, dtUsuario usuarioDt) {
        this.usuario = usuario;
        this.propuestaTitulo = propuestaTitulo;
        this.propDt = propDt;
        this.usuarioDt = usuarioDt;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the propuestaTitulo
     */
    public String getPropuestaTitulo() {
        return propuestaTitulo;
    }

    /**
     * @return the propDt
     */
    public dtPropuesta getPropDt() {
        return propDt;
    }

    /**
     * @return the usuarioDt
     */
    public dtUsuario getUsuarioDt() {
        return usuarioDt;
    }
}
