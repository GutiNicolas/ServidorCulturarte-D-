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
public class dtCargadb {
    private String nickname;
    private String propuesta;

    public dtCargadb(String nickname, String propuesta) {
        this.nickname = nickname;
        this.propuesta = propuesta;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @return the propuesta
     */
    public String getPropuesta() {
        return propuesta;
    }
}
