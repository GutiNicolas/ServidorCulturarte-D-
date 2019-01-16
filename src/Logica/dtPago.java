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
public class dtPago {

    private String tituloP;
    private String titular;
    private String nickname;
    private String codigo;

    public dtPago(String titular) {
        this.titular = titular;
    }

    public dtPago(String tituloP, String titular) {
        this.tituloP = tituloP;
        this.titular = titular;
    }

    public dtPago(String tituloP, String titular, String nickname) {
        this.tituloP = tituloP;
        this.titular = titular;
        this.nickname = nickname;
    }

    public dtPago(String tituloP, String titular, String nickname, String codi) {
        this.tituloP = tituloP;
        this.titular = titular;
        this.nickname = nickname;
        this.codigo=codi;
    }

    /**
     * @return the titular
     */
    public String getTitular() {
        return titular;
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

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
