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
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtPagoWeb {

 //   @XmlElement(name = "titular", namespace = "")
    private String titularP;
 //   @XmlElement(name = "tipoDePago", namespace = "")
    private String tipoDePago;
 //   @XmlElement(name = "numeroCPaypal", namespace = "")
    private String numeroCPaypal;
 //   @XmlElement(name = "tipoTarjetaC", namespace = "")
    private String tipoTarjetaC;
 //   @XmlElement(name = "numeroTarjetaC", namespace = "")
    private String numeroTarjetaC;
 //   @XmlElement(name = "cvcTC", namespace = "")
    private String cvcTC;
 //   @XmlElement(name = "fVencimientoTC", namespace = "")
    private String fVencimientoTC;
 //   @XmlElement(name = "bancoNombre", namespace = "")
    private String bancoNombre;
  //  @XmlElement(name = "numeroCuentaB", namespace = "")
    private String numeroCuentaB;
    private String tituloP;
    
    public DtPagoWeb() {
    }

    /**
     * @return the titular
     */
    public String getTitular() {
        return titularP;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(String titular) {
        this.titularP = titular;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoDePago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoDePago = tipoPago;
    }

    /**
     * @return the numeroPaypal
     */
    public String getNumeroPaypal() {
        return numeroCPaypal;
    }

    /**
     * @param numeroPaypal the numeroPaypal to set
     */
    public void setNumeroPaypal(String numeroPaypal) {
        this.numeroCPaypal = numeroPaypal;
    }

    /**
     * @return the tipoTarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjetaC;
    }

    /**
     * @param tipoTarjeta the tipoTarjeta to set
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjetaC = tipoTarjeta;
    }

    /**
     * @return the numeroTarjeta
     */
    public String getNumeroTarjeta() {
        return numeroTarjetaC;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjetaC = numeroTarjeta;
    }

    /**
     * @return the cvc
     */
    public String getCvc() {
        return cvcTC;
    }

    /**
     * @param cvc the cvc to set
     */
    public void setCvc(String cvc) {
        this.cvcTC = cvc;
    }

    /**
     * @return the fVencimiento
     */
    public String getfVencimiento() {
        return fVencimientoTC;
    }

    /**
     * @param fVencimiento the fVencimiento to set
     */
    public void setfVencimiento(String fVencimiento) {
        this.fVencimientoTC = fVencimiento;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return bancoNombre;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.bancoNombre = banco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuentaB;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuentaB = numeroCuenta;
    }

    /**
     * @return the tituloPropuesta
     */
    public String getTituloPropuesta() {
        return tituloP;
    }

    /**
     * @param tituloPropuesta the tituloPropuesta to set
     */
    public void setTituloPropuesta(String tituloPropuesta) {
        this.tituloP = tituloPropuesta;
    }

}
