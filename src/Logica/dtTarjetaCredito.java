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
public class dtTarjetaCredito extends dtPago {

    private String tipo;
    private String numeroTarjeta;
    private String cvc;
    private dtFecha fVencimiento;

    

    public dtTarjetaCredito(String tipo, String numeroTarjeta, String cvc, dtFecha fVencimiento, String tituloP, String titular, String nickname) {
        super(tituloP, titular, nickname, numeroTarjeta);
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
     * @return the numeroTarjeta
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @return the cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * @return the fVencimiento
     */
    public dtFecha getfVencimiento() {
        return fVencimiento;
    }

}
