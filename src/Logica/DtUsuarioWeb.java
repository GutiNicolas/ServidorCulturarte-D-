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
 * @author jp
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtUsuarioWeb {

  //  @XmlElement(name = "nombre", namespace = "")
    private String nombreU;
  //  @XmlElement(name = "apellido", namespace = "")
    private String apellidoU;
   // @XmlElement(name = "nickname", namespace = "")
    private String nicknameU;
   // @XmlElement(name = "imagen", namespace = "")
    private String imagenU;
  //  @XmlElement(name = "email", namespace = "")
    private String emailU;
  //  @XmlElement(name = "pass", namespace = "")
    private String passU;
  //  @XmlElement(name = "fecha", namespace = "")
    private String fechaNacU;
  //  @XmlElement(name = "rol", namespace = "")
    private String rolU;
  //  @XmlElement(name = "puntaje", namespace = "")
    private int puntajeU;
  //  @XmlElement(name = "Direccion", namespace = "")
    private String direccionU;
   // @XmlElement(name = "pagWeb", namespace = "")
    private String pagWebU;
   // @XmlElement(name = "bio", namespace = "")
    private String bioU;

    public DtUsuarioWeb() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombreU;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombreU = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellidoU;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellidoU = apellido;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nicknameU;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nicknameU = nickname;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagenU;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagenU = imagen;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return emailU;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.emailU = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return passU;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.passU = pass;
    }

    /**
     * @return the fechaNac
     */
    public String getFechaNac() {
        return fechaNacU;
    }

    /**
     * @param fechaNacU the fechaNac to set
     */
    public void setFechaNac(String fechaNac) {
        this.fechaNacU = fechaNac;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rolU;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rolU = rol;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntajeU;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntajeU = puntaje;
    }

  

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccionU;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccionU = direccion;
    }

    /**
     * @return the pagWeb
     */
    public String getPagWeb() {
        return pagWebU;
    }

    /**
     * @param pagWeb the pagWeb to set
     */
    public void setPagWeb(String pagWeb) {
        this.pagWebU = pagWeb;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bioU;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bioU = bio;
    }

}
