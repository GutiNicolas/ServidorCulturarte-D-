/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Date;

/**
 *
 * @author Esteban Menendez
 */
public class propEstado {

    private dtFecha fechaIni, fechaFin;
    private dtHora hora;
    private estado estado;
    private dtPropuestaEstado midt;
    utilidades util = utilidades.getInstance();
//METODOS//

    /**
     * constructor propEstado recibe 4 parametros 2 dtFecha 1dtHora 1 estado si
     * fechaf == null se auto setea una fecha generada con 30 dias de diferencia
     * si fechaf != null setea la fecha que viene by jp
     */
    public propEstado(dtFecha fecha, dtHora hora, estado estado, dtFecha fechaf) {
        this.fechaIni = fecha;
        this.hora = hora;
        this.estado = estado;
        if (fechaf != null) {
            this.fechaFin = fechaf;
        }
        if (fechaf == null) {
            this.fechaFin = (dtFecha) fechaFinCalculo(fecha);
        }
    }

    private dtFecha fechaFinCalculo(dtFecha ini) {

        dtFecha dtf = null;
        try {
            Date f = (Date) utilidades.fechaDate(ini.getFecha(), null);
            Date ffin = (Date) utilidades.sumaRestaDias(f, 30);
            dtf = (dtFecha) util.construirFecha((String) util.fechaString(ffin, 0));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return dtf;
    }

    public dtFecha getFecha() {
        return fechaIni;
    }

    public dtHora getHora() {
        return hora;
    }

    public estado getEstado() {
        return estado;
    }

    public void setFecha(dtFecha fecha) {
        this.fechaIni = fecha;
    }

    public void setHora(dtHora hora) {
        this.hora = hora;
    }

    public void setEstado(estado estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaFin
     */
    public dtFecha getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(dtFecha fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setTituloP(String titulo) {

        dtPropuestaEstado pEDT = new dtPropuestaEstado(titulo, estado.getNombre(), fechaIni.getFecha(), hora.getHora(), fechaFin.getFecha());
        this.setMidt(pEDT);
    }

    /**
     * @return the midt
     */
    public dtPropuestaEstado getMidt() {
        return midt;
    }

    /**
     * @param midt the midt to set
     */
    public void setMidt(dtPropuestaEstado midt) {
        this.midt = midt;
    }

}
