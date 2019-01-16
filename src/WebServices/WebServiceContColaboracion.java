/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import Logica.ContColaboracion;
import Logica.DtColaboracionCompWeb;
import Logica.DtPagoWeb;
import Logica.DtarregloDtColCompWeb;
import Logica.dtContieneArray;
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
@WebService(serviceName = "ServicioContColabiracion")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebServiceContColaboracion {

    @Resource
    private WebServiceContext context;
    private Endpoint endpoint = null;
    private String direccion;
    ContColaboracion cC = ContColaboracion.getInstance();

    public WebServiceContColaboracion() {

    }

    public WebServiceContColaboracion(String direccion) {
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
///////////////////ServletColaboracion funciones

    @WebMethod
    public void registrarColaboracion(@WebParam(name = "tituloProp") String titulo, @WebParam(name = "nick") String nick, @WebParam(name = "monto") int monto, @WebParam(name = "retorno") String retorno) {
        DtColaboracionCompWeb nuevo = new DtColaboracionCompWeb();
        nuevo.setTituloP(titulo);
        nuevo.setNick(nick);
        nuevo.setMontoC(monto);
        nuevo.setRetornoP(retorno);
        cC.registrarColaboracion(nuevo);
    }

    @WebMethod
    public boolean registrarPago(@WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo, @WebParam(name = "pago") DtPagoWeb pago) {
        return cC.registrarPagoWeb(nick, titulo, pago);
    }

    @WebMethod
    public String armarRetorno(@WebParam(name = "cbe") String cbe, @WebParam(name = "cbp") String cbp) {
        return (String) cC.armarretorno(cbe, cbp);
    }
//////////////////////////////ServletConsultaDePerfil funciones

    //public List<String> listarColaboraciones(String nick)
    @WebMethod
    public dtContieneArray listarColaboraciones(@WebParam(name = "nick") String nick) {
        dtContieneArray nuevo = new dtContieneArray((ArrayList) cC.listarColaboraciones(nick), null);
        return nuevo;
    }

    //public List<dtColProp> listarmiscolaboraciones(String nick)
    @WebMethod
    public DtarregloDtColCompWeb listarMisColaboraciones(@WebParam(name = "nick") String nick) {

        return (DtarregloDtColCompWeb) cC.listarmiscolaboracionesWeb(nick);
    }
}
