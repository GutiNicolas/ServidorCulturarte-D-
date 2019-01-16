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
public class pago {

    private String titular;
    private String tituloP;
    private String nickName;
/**
     * @param titular
     * @param prop
     * @param nick*/
    public pago(String titular, String prop, String nick) {
        this.titular = titular;
        this.tituloP = prop;
        this.nickName = nick;
    }

    public void setNickname(String nick) {
        this.nickName = nick;
    }

    public String getNickname() {
        return this.nickName;
    }

    /**
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * @return the tituloP
     */
    public String getTituloP() {
        return tituloP;
    }

    /**
     * @param tituloP the tituloP to set
     */
    public void setTituloP(String tituloP) {
        this.tituloP = tituloP;
    }

}
