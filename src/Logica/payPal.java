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
public class payPal extends pago{
    private String numeroPaypal;
/**
     * @param numeroPaypal
     * @param titular
     * @param prop
     * @param nick*/
    public payPal(String numeroPaypal, String titular, String prop, String nick) {
        super(titular, prop, nick);
        this.numeroPaypal = numeroPaypal;
    }

  

   

    /**
     * @return the numeroPaypal
     */
    public String getNumeroPaypal() {
        return numeroPaypal;
    }

    /**
     * @param numeroPaypal the numeroPaypal to set
     */
    public void setNumeroPaypal(String numeroPaypal) {
        this.numeroPaypal = numeroPaypal;
    }
    
    
}
