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
 * @author jp
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtHoraWeb {

   // @XmlElement(name = "hora", namespace = "")
    private int horas;
//    @XmlElement(name = "minuto", namespace = "")
    private int minutos;
 //   @XmlElement(name = "horaContenida", namespace = "")
    private String horaContenida;

    public DtHoraWeb() {
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return horas;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.horas = hora;
    }

    /**
     * @return the minuto
     */
    public int getMinuto() {
        return minutos;
    }

    /**
     * @param minuto the minuto to set
     */
    public void setMinuto(int minuto) {
        this.minutos = minuto;
    }

    /**
     * @return the horaC
     */
    public String getHoraC() {
        return horaContenida;
    }

    /**
     * @param horaC the horaC to set
     */
    public void setHoraC(String horaC) {
        this.horaContenida = horaC;
    }

}
