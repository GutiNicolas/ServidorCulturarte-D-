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
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtFechaWeb {

   // @XmlElement(name = "dia", namespace = "")
    private String diaF;
  //  @XmlElement(name = "mes", namespace = "")
    private String mesF;
 //   @XmlElement(name = "anio", namespace = "")
    private String anioF;
 //   @XmlElement(name="fecha",namespace = "")
    private String fechaArmada;

    public DtFechaWeb() {
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return diaF;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.diaF = dia;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mesF;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mesF = mes;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anioF;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anioF = anio;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fechaArmada;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fechaArmada = fecha;
    }
}
