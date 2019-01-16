/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Esteban Menendez
 */
public class proponente extends usuario {

    protected final Map<String, propuesta> propuestasUsuario = new HashMap<String, propuesta>();//diccionario con clave string
    private String direccion;
    //   private Map<String, usuario> seguidos;
    private boolean desactivado;
    private String biografia, web;

    public proponente(String nickname, String nombre, String apellido, String email, String imagen, dtFecha nacimiento, String direccion, String biografia, String web, String pass) {
        super(nickname, nombre, apellido, email, imagen, nacimiento, pass);
        this.direccion = direccion;
        this.biografia = biografia;
        this.web = web;
    }

    public proponente(String nickname, String nombre, String apellido, String email, String imagen, dtFecha nacimiento, String direccion, String biografia, String web, String pass, boolean desactivado) {
        super(nickname, nombre, apellido, email, imagen, nacimiento, pass);
        this.direccion = direccion;
        this.desactivado = desactivado;
        this.biografia = biografia;
        this.web = web;
    }

    /**
     *
     * @param prop
     */
    public void agregarPropuesta(propuesta prop) {
        propuestasUsuario.put(prop.getTitulo(), prop);

    }

    /**
     *
     * return true si no posee propuestas
     */
    public boolean noPropuestas() {

        return propuestasUsuario.isEmpty();
    }

    public List<dtPropuestasProponente> getPropuestas() {
        List<dtPropuestasProponente> listaprop = null;
        Iterator it = propuestasUsuario.entrySet().iterator();

        return null;

    }

    public dtProponente getDtProponente() {

        dtProponente dtprop = new dtProponente(this.nombre, this.apellido, this.nickname, this.imagen, this.email, this.nacimiento, this.direccion, this.biografia, this.web, this.password);

        return dtprop;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * @param biografia the biografia to set
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
    }

    //METODOS//
    public dtPropuesta getPropuestas(String idPropuesta) {
        propuesta p = (propuesta) this.propuestasUsuario.get(idPropuesta);
        dtPropuesta prop = new dtPropuesta(p.getTitulo(), p.getDescripcion(), p.getImagen(), p.getLugar(), p.getEstadoActual(), p.getCategoria(), this.getNickname(), p.getFecharealizacion(), p.getFechapublicada(), p.getPrecioEntrada(), p.getMontoRequerido(), 0, p.getRetorno());
        return prop;

    }

    public List<dtPropuesta> getTodasPropuestas() {
        List<dtPropuesta> retornar = new ArrayList<dtPropuesta>();
        if (this.propuestasUsuario.isEmpty() == false) {
            Iterator it = this.propuestasUsuario.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                propuesta p = this.propuestasUsuario.get(key);
                dtPropuesta dtp = new dtPropuesta(p.getTitulo(), p.getDescripcion(), p.getImagen(), p.getLugar(), p.getEstadoActual(), p.getCategoria(), this.getNickname(), p.getFecharealizacion(), p.getFechapublicada(), p.getPrecioEntrada(), p.getMontoRequerido(), 0, p.getRetorno());
                retornar.add(dtp);
            }
        }
        return retornar;
    }

    public List<String> listarmispropuestas() {
        List<String> retorno = new ArrayList();
        this.propuestasUsuario.keySet().forEach((key) -> {
            retorno.add(key);
        });
        return retorno;
    }

    public List<String> listarmispropuestaslike(String titulo) {
        List<String> retorno = new ArrayList();
        this.propuestasUsuario.keySet().forEach((key) -> {
            propuesta p = (propuesta) this.propuestasUsuario.get(key);
            if (p.getTitulo().contains(titulo)) {
                retorno.add(key);
            }
        });
        return retorno;
    }

    public boolean tenesPropuesta(String titulo) {
        boolean tengo = false;
        try {
            tengo = this.propuestasUsuario.containsKey(titulo);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return tengo;
    }

    public propuesta damelapropuesta(String titulo) {
        propuesta prop = null;
        try {
            prop = (propuesta) this.propuestasUsuario.get(titulo);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return prop;
    }

//     public void ordenalosestadosdepropuestas(){
//         for(String key: this.propuestasUsuario.keySet()){
//             propuesta p=this.propuestasUsuario.get(key);
//             p.ordenarestados();
//         }
//     }
    public void borratuspropuestas() {
        try {
            for (String key : this.propuestasUsuario.keySet()) {
                propuesta p = this.propuestasUsuario.get(key); //            propuesta p = this.propuestasUsuario.remove(key);
                p.desvincular();
            }

            this.propuestasUsuario.clear();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     *
     * retorna un arreglo con las propuestas del proponente by Jp
     */
    public ArrayList<propuesta> getPropuestasObj() {

        ArrayList<propuesta> propuestas = new ArrayList<>();

        try {
            Iterator it = propuestasUsuario.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                propuesta pro = (propuesta) propuestasUsuario.get(key);
                propuestas.add(pro);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return propuestas;

    }

    public List<String> listarmispropuestasmenosingresadas() {
        List<String> retorno = new ArrayList();
        for (String key : this.propuestasUsuario.keySet()) {
            propuesta p = this.propuestasUsuario.get(key);
            if (p.getEstadoActual().equals("Ingresada") == false && p.getEstadoActual().equals("Desactivada") == false) {
                retorno.add(key);
            }
        }
        return retorno;
    }

    /**
     * @return the desactivado
     */
    public boolean isDesactivado() {
        return desactivado;
    }

    /**
     * @param desactivado the desactivado to set
     */
    public void setDesactivado(boolean desactivado) {
        this.desactivado = desactivado;
    }
}
