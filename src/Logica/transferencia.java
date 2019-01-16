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
public class transferencia extends pago{
    private String banco;
    private String numeroCuenta;
/**
     * @param banco
     * @param numeroCuenta
     * @param titular
     * @param prop
     * @param nick*/
    public transferencia(String banco, String numeroCuenta, String titular, String prop, String nick) {
        super(titular, prop, nick);
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
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
}
