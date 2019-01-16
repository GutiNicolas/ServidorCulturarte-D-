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
 * @author Esteban Menendez
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class dtColaborador extends dtUsuario {
    
    public dtColaborador(String nombre, String apellido, String nickname, String imagen, String email, dtFecha fechaNac, String pass) {
        super(nombre, apellido, nickname, imagen, email, fechaNac,pass);
   
        
    }
    

}
