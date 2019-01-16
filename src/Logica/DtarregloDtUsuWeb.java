/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.FIELD)
public class DtarregloDtUsuWeb {
  //   @XmlElement(name = "UsuariosLista", namespace = "")
   
    private ArrayList<DtUsuarioWeb> listaUsuarios;

    public DtarregloDtUsuWeb() {
    }

    /**
     * @return the usuariosLista
     */
    public ArrayList<DtUsuarioWeb> getUsuariosLista() {
        return listaUsuarios;
    }

    /**
     * @param usuariosLista the usuariosLista to set
     */
    public void setUsuariosLista(ArrayList<DtUsuarioWeb> usuariosLista) {
        this.listaUsuarios = usuariosLista;
    }
}
