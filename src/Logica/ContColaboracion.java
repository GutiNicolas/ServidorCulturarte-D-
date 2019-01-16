/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.colaboracionesPersistencia;
import Persistencia.pagosPersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author nicolasgutierrez
 */
public class ContColaboracion implements iConColaboracion {

    private static ContColaboracion instance;
    private colProp colaboracion;
    private ContUsuario cUsuario = ContUsuario.getInstance();
    private colaboracionesPersistencia colPer = new colaboracionesPersistencia();
    private ContCargaBD contCarga = ContCargaBD.getInstance();
    private pagosPersistencia pPer = new pagosPersistencia();
    private ArrayList<pago> pagos = new ArrayList<>();

    public static ContColaboracion getInstance() {
        if (instance == null) {
            instance = new ContColaboracion();
        }
        return instance;
    }

    @Override
    public void cargarColaboracion() {
        cargarColaboraciones();
    }

    @Override
    public dtColProp seleccionarColaboracion(String nickusuario, String titulo) {
        colProp cp = (colProp) this.cUsuario.seleccionarColaboracion(nickusuario, titulo);
        this.colaboracion = cp;
        dtColProp dtcp = new dtColProp(nickusuario, cp.getRetorno(), cp.getFecha(), cp.getHora(), cp.getMontocolaborado(), cp.getComentario());
        return dtcp;
    }

    @Override
    public void eliminar() throws Exception {
        this.cUsuario.eliminarColaboracion(this.colaboracion);
    }

    public void cargarColaboraciones() {
        ArrayList<dtColaboracionCompleto> dtColaComp = new ArrayList<>();

        try {
            System.out.println("Cargar Colaboraciones inicio...");
            contCarga.levantarBDcolPer();
            colPer.cargarColaboraciones(dtColaComp);
            cargarPagos();
            int cont = 0;
            for (int i = 0; i < dtColaComp.size(); i++) {
                dtColaboracionCompleto dt = (dtColaboracionCompleto) dtColaComp.get(i);
                contCarga.agregardtcolaboraciones(dt);
                colProp cp = new colProp(dt.getFecha(), dt.getHora(), dt.getMonto(), dt.getRetorno(), null, dt.getComentario());
                setearPago(cp, dt.getNickname(), dt.getTitulo());
                cUsuario.registrarcolaboracion(dt.getNickname(), dt.getTitulo(), cp);
                cont++;
            }
            System.out.println("Cargue: "+cont);
            System.out.println("Termine...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private void setearPago(colProp cp, String nick, String prop) {
        try {
            for (int i = 0; i < pagos.size(); i++) {
                pago p = (pago) pagos.get(i);
                if (p.getNickname().equals(nick)) {
                    if (p.getTituloP().equals(prop)) {
                        cp.setFormaPago(p);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " - " + e.getCause());
        }
    }

    @Override
    public void borrartodocColaboraciones() {
        cUsuario.borrarColaboraciones();
    }

    @Override
    public List<dtCola> listarcolaboracionesdelcolaborador(String nickcolaborador) {
        return cUsuario.colaboracionesde(nickcolaborador);
    }

    @Override
    public void eliminarcolaboracion(String nickname, String titulo) throws Exception {
        cUsuario.eliminarcolaboracion(nickname, titulo);
    }

    void borrarColecciones() {
        this.colaboracion = null;
    }

    @Override
    public boolean registrarColaboracion(DtColaboracionCompWeb cola) {

        String titulo = (String) cola.getTituloP();
        String colab = (String) cola.getNick();
        int monto = (Integer) cola.getMontoC();
        String retorno = (String) cola.getRetornoP();
        return cUsuario.registrarColaboracion(titulo, colab, monto, retorno);

    }

    private pago crearPago(dtPago p) {

        if (p instanceof dtTarjetaCredito) {
            dtTarjetaCredito dttc = (dtTarjetaCredito) p;
            /*String tipo, String numeroTarjeta, String cvc, dtFecha fVencimiento, String titular, String prop, String nick*/
            pago fp = new tarjetaCredito(dttc.getTipo(), dttc.getNumeroTarjeta(), dttc.getCvc(), dttc.getfVencimiento(), dttc.getTitular(), dttc.getTituloP(), dttc.getNickname());
            return fp;
        }
        if (p instanceof dtTransferencia) {
            dtTransferencia trans = (dtTransferencia) p;
            /*String banco, String numeroCuenta, String titular, String prop, String nick*/
            pago fp = new transferencia(trans.getBanco(), trans.getNumeroCuenta(), p.getTitular(), trans.getTituloP(), trans.getNickname());
            return fp;
        }
        if (p instanceof dtPaypal) {
            dtPaypal dtpp = (dtPaypal) p;
            pago fp = new payPal(dtpp.getNumeroPaypal(), dtpp.getTitular(), dtpp.getTituloP(), dtpp.getNickname());
            return fp;
        }
        return null;
    }

    @Override
    public boolean registrarPagoWeb(String usuario, String tituloP, DtPagoWeb pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> listarColaboraciones(String nick) {
        return (List<String>) cUsuario.listarColaboraciones(nick);
    }

    @Override
    public DtarregloDtColCompWeb listarmiscolaboracionesWeb(String nick) {
        List<dtColProp> listCol = new ArrayList<>();
        listCol = cUsuario.listarmiscolaboraciones(nick);
        DtarregloDtColCompWeb nuevo = new DtarregloDtColCompWeb();
        ArrayList<DtColaboracionCompWeb> arrColWeb = new ArrayList<>();
        try {
            for (int i = 0; i < listCol.size(); i++) {
                arrColWeb.add((DtColaboracionCompWeb) invertirDtColProp((dtColProp) listCol.get(i)));

            }
            nuevo.setArregloCola(arrColWeb);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return nuevo;
    }

    private DtColaboracionCompWeb invertirDtColProp(dtColProp colP) {//nick fecha hora monto propuesta
        DtColaboracionCompWeb nuevo = new DtColaboracionCompWeb();
        nuevo.setTituloP(colP.getTitulo());
        nuevo.setNick(colP.getNickname());
        nuevo.setFechaC((String) colP.getFechaCol().getFecha());
        nuevo.setHoraC(colP.getHoraCol().getHora());
        nuevo.setMontoC(colP.getMontoColaborado());
        return nuevo;
    }

    @Override
    public String armarretorno(String cbe, String cbp) {
        return (String) cUsuario.armarretorno(cbe, cbp);
    }

    @Override
    public void cargarPagos() {
        try {
            System.out.println("Cargar pagos incio...");
            contCarga.levantarBDPagosPer();
            int cont = 0;
            ArrayList<dtPago> Pagos = (ArrayList<dtPago>) pPer.cargarPagos();
            for (int i = 0; i < Pagos.size(); i++) {
                dtPago p = (dtPago) Pagos.get(i);
                contCarga.seteardtPago(p);
                pagos.add((pago) this.crearPago(p));
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
