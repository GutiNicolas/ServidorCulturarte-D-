/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Esteban Menendez
 */
public class usuario {

    protected String nickname, nombre,
            apellido,
            email,
            imagen;
    protected String password;

    protected dtFecha nacimiento;

    protected Map<String, usuario> seguidos;
    protected Map<String, propuesta> favoritas;

    //METODOS
    public List<dtPropuestasProponente> getPropuestas() {
        return null;

    }

    //CONSTRUCTORES//
    public usuario() {
    }

    public usuario(String nickname, String nombre, String apellido, String email, String imagen, dtFecha nacimiento, String password) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.imagen = imagen;
        this.nacimiento = nacimiento;
        this.seguidos = new HashMap<String, usuario>();
        this.favoritas = new HashMap<String, propuesta>();
        this.password = password;
    }

    //GETTERS//
    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getImagen() {
        return imagen;
    }

    public dtFecha getNacimiento() {
        return nacimiento;
    }

    //SETTERS//
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNacimiento(dtFecha nacimiento) {
        this.nacimiento = nacimiento;
    }

    //OPERACIONES
    public boolean loSigue(String nick) {
        return this.seguidos.containsKey(nick);
    }

    public void seguir(usuario us) {
        String usu = us.getNickname();
        this.seguidos.put(usu, us);
    }

    public String getNacimientoString() {
        String retorno = this.nacimiento.getDia() + "/" + this.nacimiento.getMes() + "/" + this.nacimiento.getAnio();
        return retorno;
    }

    public void dejardeSeguir(usuario us) {
        this.seguidos.remove(us.getNickname(), us);
    }

    public void eliminarColaboracion(colProp c) {

    }

    public void eliminartodoslosseguidos() {
        this.seguidos.clear();
        this.nacimiento = null;
    }

    public boolean seguisaalguien() {
        return this.seguidos.isEmpty();
    }

    public void eliminarFavoritos() {
        this.favoritas.clear();
    }

    public void NoSigasAlosQueNoEstenAca(Map<String, String> anoborrar) {
        for (String key : this.seguidos.keySet()) {
            if (anoborrar.containsKey(key) == false) {
                this.seguidos.remove(key);
            }
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavorita(propuesta p) {
        String pT = p.getTitulo();
        favoritas.put(pT, p);
    }

    public propuesta getPropFavorita(String tituloP) {
        propuesta p = favoritas.get(tituloP);
        return p;
    }

    public boolean meGustaEstaPropuesta(String titutloP) {
        return favoritas.containsKey(titutloP);

    }
}
