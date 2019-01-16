/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtFecha {

    
    @XmlElement(name="dia", namespace = "")
    private String dia;
    @XmlElement(name="mes", namespace = "")
    private String mes;
    @XmlElement(name="anio", namespace = "")
    private String anio;

//CONSTRUCTOR//
    /**
     *
     * Constructor de 3 parametros String dia/mes/año
     */
    
    public dtFecha(String dia, String mes, String anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     *
     * Constructor de 4 parametros String dia/mes/año Date fecha - recibe un
     * objeto date
     */
//GETTERS//
    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }
    public String getFecha() {
        String fecha = null;
        fecha = this.dia + "/" + this.mes + "/" + this.anio;

        return fecha;
    }

    /**
     * retorna un objeto date
     */
}
