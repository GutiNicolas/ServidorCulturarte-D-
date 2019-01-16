/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Logica.dtContieneArray;
import Logica.DtUsuarioWeb;
import Logica.DtFechaWeb;
import Logica.ContUsuario;
import Logica.DtPropuestaWeb;
import Logica.DtSigoAWeb;
import Logica.DtarregloDtSigoAWEB;
import Logica.DtarregloDtUsuWeb;
import Logica.culturarteFabrica;
import Logica.utilidades;
import java.util.ArrayList;
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
@WebService(serviceName = "ServicioContusuario")
//@SOAPBinding(style = Style.RPC)

@SOAPBinding(style = SOAPBinding.Style.RPC)

public class WebServiceContUsusario {

    @Resource
    private WebServiceContext context;
    culturarteFabrica fb = culturarteFabrica.getInstance();
    private ContUsuario cU = ContUsuario.getInstance();
    private utilidades util = utilidades.getInstance();

    private Endpoint endpoint = null;
    private String direccion;

    public WebServiceContUsusario() {
        this.direccion = "http://localhost:8580/ServicioU";
    }

    public WebServiceContUsusario(String direccion) {
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
/////////////////////////CARGAR DATOS DE PRUEBA

    @WebMethod
    public boolean cargarDatosPrueba() {
        try {

            fb.cargarDatosdePrueba();
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //////////////////////////ServletRegistrarse funciones    
    /**
     * funcion que comprueba existencia de nickname u correo retorna 1 si existe
     * el nickname retorna -1 si existe el correo retorna 0 si no existe ninguno
     *
     * @param nick
     * @param correo
     * @return
     */
    @WebMethod
    public int verificaExistencia(@WebParam(name = "nick") String nick, @WebParam(name = "correo") String correo) {
        if (cU.existeUsuario(nick)) {
            return 1;
        }
        if (cU.existeMail(correo)) {
            return -1;
        }
        return 0;
    }

    /**
     * crearDtFecha funcion que recibe un String con la fecha y retorna un
     * dtFecha Esta func. hace referencia a creardtFecha() de ContUsuario()
     *
     * @param fecha
     * @return
     */
    @WebMethod
    public DtFechaWeb crearDtFecha(@WebParam(name = "fecha") String fecha) {

        return cU.crearFecha(fecha);

    }

    /**
     * agregarUsu funcion que recibe un dtUsuario con el usuario a crear Esta
     * func. hace referencia a agregarUsu() de ContUsuario()
     *
     * @param usu
     * @throws java.lang.Exception
     */
    @WebMethod
    public void agregarUsu(@WebParam(name = "usuario") DtUsuarioWeb usu) throws Exception {
        try {

            cU.agregarUsuWeb(usu);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
///////////////////////ServletSeguir funciones

    /**
     * retorna un dtContieneArray con una lista de string de nick de usuarios
     * Esta func. referencia a listarusuarios() de ContUsuario()
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray listarUsuarios(@WebParam(name = "nick") String nick) {

        dtContieneArray nuevo = new dtContieneArray((ArrayList<String>) cU.listarusuarios(nick), null);

        return nuevo;
    }

    /**
     * CargarLosSeguidosPor retorna un dtContieneArray con una lista String con
     * los nick de los seguidores Esta func. hace referencia a
     * cargarlosseguidospor() de ContUsuario()
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray cargarLosSeguidosPor(@WebParam(name = "nick") String nick) {

        dtContieneArray nuevo = new dtContieneArray((ArrayList<String>) cU.cargarlosseguidospor(nick), null);

        return nuevo;
    }

    /**
     * seguir funcion que recibe un String con el nickname de usuario actual y
     * un String con el nickname del usuario a seguir. Esta func. hace
     * referencia a seguir() de ContUsuario()
     *
     * @param nickusuario
     * @param usuSeguir
     * @throws java.lang.Exception
     */
    @WebMethod
    public void seguir(@WebParam(name = "nickusuario") String nickusuario, @WebParam(name = "nickUsuSeguir") String usuSeguir) throws Exception {
        try {

            cU.seguir(nickusuario, usuSeguir);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
////////////////////////ServletLogin funciones

    /**
     * usuarioLogin funcion que recibe dos parametros String el nick y el pass
     * del usuario. Retorna un dtusuario con los datos del usuario
     *
     * @param usu
     * @param Pass
     * @return dtUsuario
     */
    @WebMethod
    //dtUsuario usuarioLogin(String usu)
    public DtUsuarioWeb usuarioLogin(@WebParam(name = "nickUsuario") String usu, @WebParam(name = "pass") String Pass) {
        DtUsuarioWeb nuevo = cU.usuarioLoginW(usu);
        if (nuevo.getPass().equals(Pass)) {
            return nuevo;
        }
        return null;
    }

    @WebMethod
    public DtUsuarioWeb usuarioLoginSN(@WebParam(name = "nick") String nick) {
        return (DtUsuarioWeb) cU.usuarioLoginW(nick);
    }

//////////////////////////////////////////////////////ServletColaboracion funciones
    /**
     * infoPropuesta funcion que recibe un String con el titulo de la propuesta
     * y retorna una dtPropuesta.
     *
     * Esta func. hace referencia a infoPropuesta() de ContUsuario(). Si se
     * ṕroduce una excepcion retorna null
     *
     * @param titulo
     * @return
     */
    @WebMethod
    public DtPropuestaWeb infoPropuesta(@WebParam(name = "titulo") String titulo) {

        return (DtPropuestaWeb) cU.infoPropuestaWeb(titulo);

    }

/////////////////////////////ServletDejarDeSeguir funciones
    /**
     * cargarLosSegPor funcion que recibe un String con el nick dek usuario y
     * retorna un dtContieneArray con una lista de usuarios seguidores. Esta
     * func. hace referencia a cargarlosseguidospor() de ContUsuario().
     *
     * @param nick
     * @return
     */
    @WebMethod
    public dtContieneArray cargarLosSegPor(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList<String>) cU.cargarlosseguidospor(nick), null);
        return nuevo;
    }

    /**
     * dejarDeSeguir funcion que recibe dos parametros String el nick del
     * usuario seguidor y el nick del usuario a dejar de seguir. Esta func. hace
     * referencia a dejarDeSeguir() de ContUsuario().
     *
     * @param nickSeguidor
     * @param dejarDSeguir
     * @throws java.lang.Exception
     */
    @WebMethod
    public void dejarDeSeguir(@WebParam(name = "nickSeguidor") String nickSeguidor, @WebParam(name = "nickDejardeSeguir") String dejarDSeguir) throws Exception {
        cU.dejarDeSeguir(nickSeguidor, dejarDSeguir);
    }
//////////////////ServvletConsultadePerfil

    @WebMethod
// public List<dtUsuario> listarusuariosweb
    public DtarregloDtUsuWeb listarUsuariosWeb(@WebParam(name = "nick") String nick) {
        DtarregloDtUsuWeb nuevo = new DtarregloDtUsuWeb();
        nuevo.setUsuariosLista((ArrayList<DtUsuarioWeb>) cU.listarusuariosweb(nick));

        return nuevo;
    }

    @WebMethod
    public DtUsuarioWeb infoUsuarioGeneral(@WebParam(name = "nick") String nick) {
        return (DtUsuarioWeb) cU.infoUsuarioGeneral(nick);
    }

    @WebMethod
    public DtarregloDtUsuWeb listarMisSeguidores(@WebParam(name = "nick") String nick) {
        DtarregloDtUsuWeb nuevo = new DtarregloDtUsuWeb();
        nuevo.setUsuariosLista((ArrayList<DtUsuarioWeb>) cU.listarmisseguidores(nick));
        return nuevo;
    }

    @WebMethod
    public DtarregloDtSigoAWEB listarMisSeguidos(@WebParam(name = "nick") String nick) {
        DtarregloDtSigoAWEB nuevo = new DtarregloDtSigoAWEB();
        nuevo.setArregloSigoA((ArrayList<DtSigoAWeb>) cU.listarmisseguidos(nick));
        return nuevo;
    }

    @WebMethod
    // public List<String> mispropuestasfavoritas(String nick) 
    public dtContieneArray misPropFav(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cU.mispropuestasfavoritas(nick), null);
        return nuevo;
    }
/////////////////////////ServletRanking funciones

    @WebMethod
    public DtarregloDtUsuWeb ranking() {
        DtarregloDtUsuWeb nuevo = new DtarregloDtUsuWeb();
        nuevo.setUsuariosLista((ArrayList<DtUsuarioWeb>) cU.ranking());
        return nuevo;
    }
////////////////AppMobil funciones

    @WebMethod
    public DtUsuarioWeb usuarioLoginApp(@WebParam(name = "nick") String nick) {
        DtUsuarioWeb usuarioLoginA = cU.usuarioLoginA(nick);
        return usuarioLoginA;
    }

    @WebMethod
    public boolean existeUsuario(@WebParam(name = "nick") String nick) {
        return cU.existeUsuario(nick);
    }
//////////////Funcion dar de baja

    @WebMethod
    public boolean dardebaja(@WebParam(name = "nick") String nick) {
        return cU.dardebaja(nick);
    }
//////////////////////////funcion que verifica si un usuario esta desactivado
    @WebMethod
    public boolean usuarioDesactivado(@WebParam(name = "nickname") String nick) {
        return cU.usuarioDesactivado(nick);
    }
}
