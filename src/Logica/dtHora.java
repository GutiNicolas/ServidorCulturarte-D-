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
public class dtHora {

    @XmlElement(name = "hora", namespace = "")
    private int hora;
    @XmlElement(name = "minuto", namespace = "")
    private int minuto;

    public dtHora(int horas, int minutos) {
        this.hora = horas;
        this.minuto = minutos;
    }

    public String convertirHoraAString(dtHora dt) {
        String retorno = dt.getHoras() + ":" + dt.getMinutos();
        return retorno;
    }

    public dtHora convertirHorasADtHora(String h) {
        dtHora retorno = new dtHora(1, 1);
        String[] parsed = h.split(":");
        retorno.setHoras(Integer.parseInt(parsed[0]));
        retorno.setMinutos(Integer.parseInt(parsed[1]));
        return retorno;
    }

    public String getHora() {
        String ret = this.getHoras() + ":" + this.getMinutos();
        return ret;
    }

    /**
     * @return the horas
     */
    public int getHoras() {
        return hora;
    }

    /**
     * @return the minutos
     */
    public int getMinutos() {
        return this.minuto;
    }

    /**
     * @param hora the hora to set
     */
    public void setHoras(int hora) {
        this.hora = hora;
    }

    /**
     * @param minutos the minutos to set
     */
    public void setMinutos(int minutos) {
        this.minuto = minutos;
    }

}
