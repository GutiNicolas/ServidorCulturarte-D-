/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author juan
 */
public class tarjetaCredito extends pago{
    private String tipo;
    private String numeroTarjeta;
    private String cvc;
    private dtFecha fVencimiento;
/**
     * @param tipo
     * @param numeroTarjeta
     * @param cvc
     * @param fVencimiento
     * @param titular
     * @param prop
     * @param nick
 */
    public tarjetaCredito(String tipo, String numeroTarjeta, String cvc, dtFecha fVencimiento, String titular, String prop, String nick) {
        super(titular, prop, nick);
        this.tipo = tipo;
        this.numeroTarjeta = numeroTarjeta;
        this.cvc = cvc;
        this.fVencimiento = fVencimiento;
    }

  

   

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the numeroTarjeta
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * @param cvc the cvc to set
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    /**
     * @return the fVencimiento
     */
    public dtFecha getfVencimiento() {
        return fVencimiento;
    }

    /**
     * @param fVencimiento the fVencimiento to set
     */
    public void setfVencimiento(dtFecha fVencimiento) {
        this.fVencimiento = fVencimiento;
    }

  

   
    
    
}
