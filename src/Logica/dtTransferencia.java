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
 * @author juan
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtTransferencia extends dtPago {

    private String banco;
    private String numeroCuenta;

    
    public dtTransferencia(String banco, String numeroCuenta, String tituloP, String titular, String nickname) {
        super(tituloP, titular, nickname, numeroCuenta);
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
    }



    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

}
