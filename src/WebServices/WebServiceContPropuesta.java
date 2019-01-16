/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Logica.ContPropuesta;
import Logica.DtFechaWeb;
import Logica.DtHoraWeb;
import Logica.DtPropuestaWeb;
import Logica.DtarregloDtPropWeb;
import Logica.dtContieneArray;
import Logica.utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author jp
 */
@WebService(serviceName = "ServicioContPropuesta")
@SOAPBinding(style = SOAPBinding.Style.RPC)

public class WebServiceContPropuesta {

    @Resource
    private WebServiceContext context;
    private Endpoint endpoint = null;
    private String direccion;
    private ContPropuesta cP = ContPropuesta.getInstance();
    private utilidades util = utilidades.getInstance();

    public WebServiceContPropuesta() {
        this.direccion = "http://localhost:8680/ServicioP";
    }

    public WebServiceContPropuesta(String direccion) {
        this.direccion = direccion;
    }

    @WebMethod
    public String hola() {
        return "hola";
    }

    public boolean despublicar() {
        try {
            endpoint.stop();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public void publicar() {
        try {
            endpoint = Endpoint.publish(direccion, this);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
///////////////////funciones generales

    /**
     * funcion que cambia de estado automaticamente a las propuestas
     */
    @WebMethod
    public void propAutomaticas() {
        cP.propAutomaticas();

    }

    /**
     * funcion que recibe un String con la fecha y retorna un dtFecha Esta func.
     * hace referencia a construirFecha de utilidades
     *
     * @param fecha
     * @return
     */
    @WebMethod
    public DtFechaWeb construirDtFecha(@WebParam(name = "fecha") String fecha) {
        return cP.crearFecha(fecha);

    }

    @WebMethod
    public DtFechaWeb getFecha() {
        return cP.getFechaWeb();
    }

    @WebMethod
    public DtHoraWeb getHora() {
        return cP.getHoraWeb();
    }

    ///////////////////////////////ServletFavoritos funciones
    /**
     * listarTodasLasPropuestas funcion que recibe un String con el titulo de
     * las propuestas si viene null o vacio lista todas las propuestas Retorna
     * un dtContieneArray con el arreglo con las propuestas Esta func.
     * referencia a listartodaslaspropuestas() de ContPropuesta()
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray listarTodasLasPropuestas(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray();
        if ((List<String>) cP.listartodaslaspropuestas(nick) != null) {
            nuevo = new dtContieneArray((ArrayList) cP.listartodaslaspropuestas(nick), null);
        }
        return nuevo;

    }

    /**
     * listarMisPropuestasFavoritas funcion que recibe un String con el nickname
     * del usuario y retorna un dtContieneArray que contiene una lista de string
     * de las propuestas favoritas Esta func. referencia a listarmispropsfav()
     * de ContPropuesta.
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray listarMisPropuestasFavoritas(@WebParam(name = "nick") String nick) {

        dtContieneArray nuevo = new dtContieneArray();

        if ((List<String>) cP.listarmispropsfavs(nick) != null) {
            nuevo = new dtContieneArray((ArrayList) cP.listarmispropsfavs(nick), null);
        }

        return nuevo;
    }

    /**
     * agregarPropComoFav funcion que agrega una propuesta a la lista de
     * favoritas del usuario recibe un String con el nick del usuario y otro
     * String con el titulo de la prop Esta func. hace referencia a
     * agregarpropuestacomofav() de ContPropuesta()
     *
     * @param nick
     * @param titulo
     */
    @WebMethod
    public void agregarPropComoFav(@WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo) {
        cP.agregarpropuestacomofav(nick, titulo);
    }

//////////////////////////ServletExtender funciones
    /**
     * extenderFinanciacionProp funcion que recibe un String con el titulo de la
     * propuesta y la extiende 30 dias mas al extado En financiacion Esta func.
     * hace referencia a extenderFinanciacionProp() de ContPropuesta()
     *
     * @param tituloP
     * @return
     */
    @WebMethod
    public String extenderFinanciacionProp(@WebParam(name = "titulo") String tituloP) {
        return cP.extenderFinanciacionProp(tituloP);
    }
////////////////////////////////////////////ServletColaboracion funciones

    /**
     * agregarEstAPropW funcion que recible dos parametros String el estado a
     * agregar y el titulo de la propuesta. Esta func. hace referencia a
     * agregarestadoapropWEB() de ContPropuesta()
     *
     * @param estado
     * @param titulo
     */
    @WebMethod
    public void agregarEstAPropW(@WebParam(name = "estado") String estado, @WebParam(name = "titulo") String titulo) {
        cP.agregarestadoapropWEB(estado, titulo);
    }

    /**
     * listarPropuestasWeb retorna un dtContieneArray que contiene un areglo de
     * dtPropuestas. Esta func. hace referencia a listarpropuestasenlaweb de
     * ContPropuesta.
     *
     * @return
     */
    @WebMethod
    public DtarregloDtPropWeb listarPropuestasWeb() {

        DtarregloDtPropWeb nuevo = new DtarregloDtPropWeb();
        nuevo.setArregloProp((ArrayList<DtPropuestaWeb>) cP.listarpropuestasenlaweb());
        return nuevo;
    }
//////////////////////////////ServvletCancelarPropuesta funciones

    /**
     * propuestasParaCancelar funcion que recibe un Strin con el nickname del
     * usuario y retorna un dtContienearray con la lista de propuestas para
     * cancelar. Esta func. hace referencia a listarpropuestasparacancelar() de
     * ContPropuesta()
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray propuestasParaCancelar(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray();
        if ((List<String>) cP.listarpropuestasparacancelar(nick) != null) {
            nuevo = new dtContieneArray((ArrayList) cP.listarpropuestasparacancelar(nick), null);
        }
        return nuevo;
    }
///////////////////////////ServletAltaPropuesta funciones

    @WebMethod
    public dtContieneArray listaCategorias() {
        dtContieneArray nuevo = new dtContieneArray();
        if ((ArrayList) cP.listarCategorias() != null) {
            nuevo = new dtContieneArray((ArrayList) cP.listarCategorias(), null);
        }

        return nuevo;
    }

    @WebMethod
    public void altaPropuesta(@WebParam(name = "dtpropuesta") DtPropuestaWeb dtpropuesta) {
        cP.altaPropWeb(dtpropuesta);
    }

/////////////////////////////////ServletCOnsultadePropuestaProEstado funciones
    @WebMethod
    public DtarregloDtPropWeb listarPropEnCategoria(@WebParam(name = "categorioa") String cat) {
        DtarregloDtPropWeb nuevo = new DtarregloDtPropWeb();
        nuevo.setArregloProp((ArrayList<DtPropuestaWeb>) cP.listarpropuestasencategoriaWeb(cat));
        return nuevo;
    }

/////////////////////ServletConsultadePropuesta funciones
    @WebMethod
    public dtContieneArray listarPropMenosIng(@WebParam(name = "titulo") String titulo) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cP.listarpropuestasmenosingresadas(titulo), null);
        return nuevo;
    }

/////////////////////////ServletComentar funciones
    @WebMethod
    public dtContieneArray listarPropColaboradas(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cP.listarpropuestascolaboradaspor(nick), null);
        return nuevo;
    }

    @WebMethod
    public void comentar(@WebParam(name = "nick") String nick, @WebParam(name = "tituloP") String titulo, @WebParam(name = "comentario") String comentario) {
        cP.agregarcomentarioapropuesta(nick, titulo, comentario);
    }
/////////////////////////ServletConsultadePerfil funciones

    @WebMethod
    //public List<String> mispropuestasaceptadas(String nick)
    public dtContieneArray misPropAceptadas(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cP.mispropuestasaceptadas(nick), null);
        return nuevo;
    }

    @WebMethod
    public dtContieneArray misPropIngresadas(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cP.mispropuestasaingresadas(nick), null);
        return nuevo;
    }
}
