/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.TreeMap;

/**
 *
 * @author Esteban Menendez
 */
public class propuesta {

    utilidades util = utilidades.getInstance();
    private String titulo, descripcion, imagen, lugar, retorno;
    private dtFecha fecharealizacion;
    private TreeMap<Integer, propEstado> estados;
    private categoria categoria;
    private dtFecha fechapublicada;
    private int precioEntrada;
    private int montoRequerido;

    public propuesta(String titulo, String descripcion, String imagen, String lugar, dtFecha fecharealizacion, dtFecha fechapublicada, int precioEntrada, int montoRequerido, String retorno, estado est, categoria cat) {
        this.estados = new TreeMap<Integer, propEstado>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.fecharealizacion = fecharealizacion;
        this.fechapublicada = fechapublicada;
        this.precioEntrada = precioEntrada;
        this.montoRequerido = montoRequerido;
        this.retorno = retorno;
        this.categoria = cat;

        if (est != null) {

            /*Calendar cal = Calendar.getInstance();
            Date da = cal.getTime();
            dtHora dth = new dtHora(da.getHours(), da.getMinutes());*/
            dtHora dth = (dtHora) util.getHora();
            propEstado e = new propEstado(fechapublicada, dth, est, null);
            e.setTituloP(titulo);
            this.estados.put(1, e);
        }
    }

    public propuesta() {
    }

    public propuesta(String titulo, String descripcion, String imagen, String lugar, dtFecha fecharealizacion, dtFecha fechapublicada, int precioEntrada, int montoRequerido, String retorno, categoria cat) {
        this.estados = new TreeMap<Integer, propEstado>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.lugar = lugar;
        this.fecharealizacion = fecharealizacion;
        this.fechapublicada = fechapublicada;
        this.precioEntrada = precioEntrada;
        this.montoRequerido = montoRequerido;
        this.retorno = retorno;
        this.categoria = cat;

    }

    propuesta(String titulo, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param estado
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    public void setEstado(propEstado estprop, int orden) {
        try {
            estprop.setTituloP(titulo);
            estados.put(orden, estprop);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     *
     * retorna un string con el estado actual de la propuesta comment by Jp
     */
    public String getEstadoActual() {
        String est = "Ingresada";
        propEstado propEst = null;
        for (int key : estados.keySet()) {
            propEst = estados.get(key);
        }
        if (propEst != null && propEst.getEstado().getNombre().isEmpty() == false) {
            est = propEst.getEstado().getNombre();
        }

        return est;

    }

    /**
     * retorna la fecha de finalizacion del estado actual by jp
     */
    public String getFechaFinEstadoActual() {
        String fecha = null;
        String estAct = getEstadoActual();
        int posicion = util.getIdEstado(estAct);
        propEstado pe = (propEstado) estados.get(posicion);
        fecha = (String) pe.getFechaFin().getFecha();
        return fecha;
    }

    public String getHoraFinEstadoActual() {
        String hora = null;
        String estAct = getEstadoActual();
        int posicion = util.getIdEstado(estAct);
        propEstado pe = (propEstado) estados.get(posicion);
        hora = (String) pe.getHora().getHora();
        return hora;
    }

    public TreeMap<Integer, propEstado> getEstados() {
        return this.estados;
    }

    public boolean agregarNuevoEstado(estado e, dtFecha dtf, dtHora dth, int orden) {
        if (estados.containsKey(orden)) {
            return false;
        }
        propEstado pe = new propEstado(dtf, dth, e, null);
        this.estados.put(orden, pe);
        return this.estados.containsKey(orden);
    }

    public propEstado getPropEstadoActual() {
        propEstado pE = null;
        try {
            String estado = getEstadoActual();
            int codigo = util.getIdEstado(estado);
            pE = (propEstado) estados.get(codigo);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return pE;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria.getNombre();
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the fecharealizacion
     */
    public dtFecha getFecharealizacion() {
        return fecharealizacion;
    }

    /**
     * @param fecharealizacion the fecharealizacion to set
     */
    public void setFecharealizacion(dtFecha fecharealizacion) {
        this.fecharealizacion = fecharealizacion;
    }

    /**
     * @return the fechapublicada
     */
    public dtFecha getFechapublicada() {
        return fechapublicada;
    }

    /**
     * @param fechapublicada the fechapublicada to set
     */
    public void setFechapublicada(dtFecha fechapublicada) {
        this.fechapublicada = fechapublicada;
    }

    /**
     * @return the precioEntrada
     */
    public int getPrecioEntrada() {
        return precioEntrada;
    }

    /**
     * @param precioEntrada the precioEntrada to set
     */
    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public void setRetorno(String r) {
        this.retorno = r;
    }

    public String getRetorno() {
        return this.retorno;
    }

    /**
     * @return the montoRequerido
     */
    public int getMontoRequerido() {
        return montoRequerido;
    }

    /**
     * @param montoRequerido the montoRequerido to set
     */
    public void setMontoRequerido(int montoRequerido) {
        this.montoRequerido = montoRequerido;
    }

    //METODOS//
    public boolean sosPropuesta(String idPropuesta) {
        if (this.titulo.equals(idPropuesta) == true) {
            return true;
        }
        return false;
    }

    public boolean compararfechas(dtFecha uno, dtFecha dos) {
        int unoanio = Integer.parseInt(uno.getAnio());
        int unomes = Integer.parseInt(uno.getMes());
        int unodia = Integer.parseInt(uno.getDia());
        int dosanio = Integer.parseInt(dos.getAnio());
        int dosmes = Integer.parseInt(dos.getMes());
        int dosdia = Integer.parseInt(dos.getDia());

        if (unoanio > dosanio) {
            return true;
        }
        if (unoanio == dosanio && unomes > dosmes) {
            return true;
        }
        if (unoanio == dosanio && unomes == dosmes && unodia > dosdia) {
            return true;
        }
        if (unoanio == dosanio && unomes == dosmes && unodia == dosdia) { //CASO DUDOSO
            return false;
        } else {
            return false;
        }

    }

    public boolean compararhoras(dtHora uno, dtHora dos) {
        if (uno.getHoras() > dos.getHoras()) {
            return true;
        }
        if (uno.getHoras() == dos.getHoras() && uno.getMinutos() > dos.getMinutos()) {
            return true;
        }
        if (uno.getHoras() == dos.getHoras() && uno.getMinutos() == dos.getMinutos()) {
            return true;
        } else {
            return false;
        }

    }

    public void desvincular() {
        this.estados.clear();
        this.categoria = null;
        this.fechapublicada = null;
        this.fecharealizacion = null;
        this.estados = null;

    }

    /**
     * Retorna true si el estado actual es en financiacion
     *
     */
    public boolean enFinanciacion() {
        return getEstadoActual().equals("En financiacion");
    }

    /**
     *
     * funcion que retorna un dtPropuestaEstado con el propuestaestado actual
     */
    public dtPropuestaEstado getDtPropEstadoActual() {

        dtPropuestaEstado dtpropActual = getPropEstadoActual().getMidt();
        return dtpropActual;
    }

    public dtPropuesta getDtPropuesta() {
        String estado = getEstadoActual();
        dtPropuesta dtp = new dtPropuesta(this.titulo, this.descripcion, this.imagen, this.lugar, estado, null, null, this.fecharealizacion, this.fechapublicada, this.precioEntrada, this.montoRequerido, 0, this.retorno);
        return dtp;
    }
}
