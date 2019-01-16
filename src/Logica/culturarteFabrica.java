/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.ConexionDB;
import WebServices.WebServiceContColaboracion;
import WebServices.WebServiceContPropuesta;
import WebServices.WebServiceContUsusario;
import java.util.Properties;

/**
 *
 * @author nicolasgutierrez
 */
public class culturarteFabrica {

    private static culturarteFabrica instance;

    ContColaboracion ic = ContColaboracion.getInstance();
    ContUsuario iu = ContUsuario.getInstance();
    ContPropuesta ip = ContPropuesta.getInstance();
    ContCargaBD cCBD = ContCargaBD.getInstance();
    ConexionDB con = ConexionDB.getInstance();
    WebServiceContUsusario WSContU = null;
    WebServiceContPropuesta WSContP = null;
    WebServiceContColaboracion WSContC = null;

    public static culturarteFabrica getInstance() {
        if (instance == null) {
            instance = new culturarteFabrica();
        }
        return instance;
    }

    private culturarteFabrica() {

    }

    public void cargarDatosdePrueba() {
        borrartodo();
        //  levantarBDdesdeMemoria();
    }

    public iConColaboracion getIContColaboracion() {
        iConColaboracion icc = ContColaboracion.getInstance();
        return icc;
    }

    public iConUsuario getIContUsuario() {
        iConUsuario icu = ContUsuario.getInstance();
        return icu;
    }

    public iConPropuesta getIContPropuesta() {
        iConPropuesta icp = ContPropuesta.getInstance();
        return icp;
    }

    public void borrartodo() {
        cCBD.limpiarCargar();
        ic.borrartodocColaboraciones();
        ip.borrartodocPropuesta();
        iu.borrartodocUsuario();
        ic.borrarColecciones();
        ip.borrarColecciones();
        iu.borrarColecciones();
        cCBD.borrarColecciones();
        bajarWebServices();

    }

    public void cargarinicio() {
        
        //cCBD.crearProperties(); descomentar para crear por primera vez y sustituir llamadas por datos
        cargarProperties();
        iu.cargarUsuarios();
        ip.cargarPropuestas();
        ic.cargarColaboracion();
        iu.cargarFavoritos();
        ip.propAutomaticas();
        levantarWebServices();

    }

    public boolean rearmar() {
        try {
            System.out.println("rearmar inicio...");
            ic.borrartodocColaboraciones();
            System.out.println("rearmar limpio colaboraciones");
            ip.borrartodocPropuesta();
            System.out.println("rearmar limpio propuesta");
            iu.borrartodocUsuario();
            System.out.println("rearmar limpio usuario");
            ic.borrarColecciones();
            System.out.println("rearmar limpio coleccionesC");
            ip.borrarColecciones();
            System.out.println("rearmar limpio coleccionesP");
            iu.borrarColecciones();
            System.out.println("rearmar limpio coleccionesU");
            cCBD.borrarColecciones();
            System.out.println("rearmar limpio coleccionesCCBD");
            cargarProperties();
            System.out.println("rearmar cargo properties");
            iu.cargarUsuarios();
            System.out.println("rearmar cargo usuarios");
            ip.cargarPropuestas();
            System.out.println("rearmar cargo propuestas");
            ic.cargarColaboracion();
            System.out.println("rearmar cargo colaboraciones");
            iu.cargarFavoritos();
            System.out.println("rearmar cargo favoritos");
            ip.propAutomaticas();
            System.out.println("rearmar ejecuto propAutomaticas");
            System.err.println("|||||||||||||||||||||||||||||||||||||||");
            System.out.println("rearmar despublicando servicios...");
            bajarWebServices();
            System.out.println("rearmar publicando servicios...");
            levantarWebServices();
            System.out.println("rearmar finalizo");
        } catch (Exception e) {
            System.err.println("rearmar: " + e.getMessage() + " " + e.getCause());
        }

        return false;
    }

    public String funcionprueba() {
        return "exito";
    }

    public boolean datosDePrueba() {

        try {
            borrartodo();
            cargarinicio();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void cargarProperties() {
        System.out.println("cargarProperties inicio...");
        Properties propiedades = cCBD.levantarProperties();
        iu.setImagenInicio(propiedades.getProperty("imgusuini"));
        iu.setImagenDestino(propiedades.getProperty("imgusudes"));
        ip.setImagenInicio(propiedades.getProperty("imgpropini"));
        ip.setImagenDestino(propiedades.getProperty("imgpropdes"));
        con.setDb(propiedades.getProperty("database"));
        con.setHost(propiedades.getProperty("hostdb"));
        con.setPass(propiedades.getProperty("paswdb"));
        con.setPort(propiedades.getProperty("puertodb"));
        con.setUser(propiedades.getProperty("userdb"));
        System.out.println("cargarProperties fin...");
    }

    public boolean levantarWebServices() {
        try {
            System.out.println("Levantar WebServices inicio");
            System.err.println("--------------------------------------");
            WSContU = new WebServiceContUsusario(cCBD.getUrlWSCU());
            WSContP = new WebServiceContPropuesta(cCBD.getUrlWSP());
            WSContC = new WebServiceContColaboracion(cCBD.getUrlWSC());
            WSContU.publicar();
            System.out.println("Levante Web Service ContUsuario en: " + cCBD.getUrlWSCU() + "?wsdl");
            WSContP.publicar();
            System.out.println("Levante Web Service ContPropuesta en: " + cCBD.getUrlWSP() + "?wsdl");
            WSContC.publicar();
            System.out.println("Levante Web Service ContColaboracion en: " + cCBD.getUrlWSC() + "?wsdl");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean bajarWebServices() {
        try {
            System.out.println("Bajando servicios...");
            if (!WSContU.despublicar()) {
                return false;
            }
            if (!WSContP.despublicar()) {
                return false;
            }
            if (!WSContC.despublicar()) {
                return false;
            }
            System.out.println("Servicios offline...");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
