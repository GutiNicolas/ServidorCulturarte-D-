/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtFecha;
import Logica.dtPago;
import Logica.dtPaypal;
import Logica.dtTarjetaCredito;
import Logica.dtTransferencia;
import Logica.utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class pagosPersistencia {

    ConexionDB conexion = ConexionDB.getInstance();

    utilidades util = utilidades.getInstance();

    public boolean agregarPago(dtPago pago) {
        Connection conn = conexion.getConexion();
        System.out.println("Agregar Pago inicio...");
        String sql = null, sql2 = null;
        try {
            Statement st = conn.createStatement();
            if (pago instanceof dtTransferencia) {
                dtTransferencia dttrans = (dtTransferencia) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + dttrans.getTitular() + "','" + dttrans.getNumeroCuenta() + "','" + dttrans.getNickname() + "','" + dttrans.getTituloP() + "')";
                sql2 = "INSERT INTO `transferenciaBancaria`(`nickname`, `NumTransferencia`, `Banco`, `tituloProp`) VALUES ('" + dttrans.getNickname() + "','" + dttrans.getNumeroCuenta() + "','" + dttrans.getBanco() + "','" + dttrans.getTituloP() + "')";
                st.executeUpdate(sql);
                System.out.println(sql);
                st.executeUpdate(sql2);
                System.out.println(sql2);
            }
            if (pago instanceof dtPaypal) {
                dtPaypal paypal = (dtPaypal) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + paypal.getTitular() + "','" + paypal.getNumeroPaypal() + "','" + paypal.getNickname() + "','" + paypal.getTituloP() + "')";
                sql2 = "INSERT INTO `paypal`(`nickname`, `NumPaypal`, `tituloProp`) VALUES ('" + paypal.getNickname() + "','" + paypal.getNumeroPaypal() + "','" + paypal.getTituloP() + "')";
                st.executeUpdate(sql);
                System.out.println(sql);
                st.executeUpdate(sql2);
                System.out.println(sql2);
            }
            if (pago instanceof dtTarjetaCredito) {
                dtTarjetaCredito tarjeta = (dtTarjetaCredito) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + tarjeta.getTitular() + "','" + tarjeta.getNumeroTarjeta() + "','" + tarjeta.getNickname() + "','" + tarjeta.getTituloP() + "')";
                sql2 = "INSERT INTO `tarjetaCredito`(`nickname`, `NumTarjeta`, `TipoTarjeta`, `fechaVencimiento`, `CVC`, `tituloProp`) VALUES ('" + tarjeta.getNickname() + "','" + tarjeta.getNumeroTarjeta() + "','" + tarjeta.getTipo() + "','" + tarjeta.getfVencimiento().getFecha() + "','" + tarjeta.getCvc() + "','" + tarjeta.getTituloP() + "')";
                st.executeUpdate(sql);
                System.out.println(sql);
                st.executeUpdate(sql2);
                System.out.println(sql2);
            }
            System.out.println("Agregar pago finalizo...");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getCause());
            return false;
        }

    }

    public ArrayList<dtPago> cargarPagos() {
        ArrayList<dtPago> pagos = new ArrayList<>();
        try {

            System.out.println("cargarPagos inicio...");
            armarPagosTransferencia(pagos);
            armarPagosTarjetaCredito(pagos);
            armarPagosPaypal(pagos);
            System.out.println("cargarPagos finalizo...");
        } catch (Exception e) {
        }
        return pagos;
    }

    private void armarPagosTransferencia(ArrayList<dtPago> pagos) {
        try {
            Connection conn = conexion.getConexion();
            System.out.println("armarPagosTransferencia inicio...");
            int seguir;
            String sqlp = "SELECT * FROM `pagos`";
            String sqlT = "SELECT * FROM `transferenciaBancaria`";
            Statement st = conn.createStatement();
            Statement stt = conn.createStatement();
            ResultSet rspagos = st.executeQuery(sqlp);
            ResultSet rsTrans = stt.executeQuery(sqlT);
            while (rsTrans.next()) {
                String nickT = null, nickp = null;
                seguir = 0;
                while (rspagos.next() && seguir == 0) {
                    if (rspagos.getString(3).equals(rsTrans.getString(1))) {

                        if (rspagos.getString(2).equals(rsTrans.getString(2))) {

                            System.out.println("Encontre un pago!");
                            System.err.println("------------------------");
                            System.out.println("nick: " + rsTrans.getString(1));
                            System.out.println("numTransferencia: " + rsTrans.getString(2));
                            System.out.println("Bancho: " + rsTrans.getString(3));
                            System.out.println("titulo Prop: " + rsTrans.getString(4));
                            System.err.println("------------------------");
                            seguir = 1;
                            dtPago pago = new dtTransferencia(rsTrans.getString(3), rsTrans.getString(2), rsTrans.getString(4), rspagos.getString(1), rsTrans.getString(1));
                            pagos.add(pago);
                            System.out.println("agrego el pago!");
                        }
                    }

                }
                rspagos.beforeFirst();
            }
            System.out.println("armarPagos fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void armarPagosTarjetaCredito(ArrayList<dtPago> pagos) {
        try {
            Connection conn = conexion.getConexion();
            System.out.println("armarPagosTarjataCredito inicio...");
            String sqlp = null, sqltd = null;
            sqlp = "SELECT * FROM `pagos`";
            sqltd = "SELECT * FROM `tarjetaCredito`";
            Statement st = conn.createStatement();
            Statement stt = conn.createStatement();
            ResultSet rsPagos = st.executeQuery(sqlp);
            ResultSet rstd = stt.executeQuery(sqltd);
            while (rstd.next()) {
                int seguir = 0;
                while (rsPagos.next() && seguir == 0) {
                    String nick1 = rsPagos.getString(3);
                    String nick2 = rstd.getString(1);
                    if (nick1.equals(nick2)) {
                        String numT1 = rsPagos.getString(2);
                        String numT2 = rstd.getString(2);
                        if (numT1.equals(numT2)) {
                            System.out.println("Encontre un pago!");
                            System.err.println("------------------------");
                            System.out.println("nick: " + rsPagos.getString(3));
                            System.out.println("numTarjeta: " + rstd.getString(2));
                            System.out.println("Tipo T: " + rstd.getString(3));
                            System.out.println("Fecha V: " + rstd.getString(4));
                            System.out.println("CVC: " + rstd.getString(5));
                            System.out.println("Titulo P: " + rstd.getString(6));
                            System.err.println("------------------------");
                            dtFecha fechaVen = (dtFecha) util.construirFecha(rstd.getString(4));
                            dtPago pago = new dtTarjetaCredito(rstd.getString(3), rstd.getString(2), rstd.getString(5), fechaVen, rstd.getString(6), rsPagos.getString(1), rstd.getString(1));
                            pagos.add(pago);
                            System.out.println("agrege el pago!");
                            seguir = 1;
                        }
                    }
                }
                rsPagos.beforeFirst();

            }
            System.out.println("fin armarPagos...");
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getCause());
        }
    }

    private void armarPagosPaypal(ArrayList<dtPago> pagos) {
        try {
            Connection conn = conexion.getConexion();
            String sqlP = null, sqlpp = null;
            sqlP = "SELECT * FROM `pagos`";
            sqlpp = "SELECT * FROM `paypal`";
            Statement st = conn.createStatement();
            Statement stpp = conn.createStatement();
            ResultSet rsp = st.executeQuery(sqlP);
            ResultSet rspp = stpp.executeQuery(sqlpp);
            while (rspp.next()) {
                int seguir = 0;
                while (rsp.next() && seguir == 0) {
                    if (rsp.getString(3).equals(rspp.getString(1))) {

                        if (rsp.getString(2).equals(rspp.getString(2))) {
                            System.out.println("Encontre un pago!");
                            System.err.println("------------------------");
                            System.out.println("nick: " + rsp.getString(3));
                            System.out.println("numPaypal: " + rspp.getString(2));
                            System.out.println("Titulo P: " + rspp.getString(3));
                            System.err.println("------------------------");
                            dtPago pago = new dtPaypal(rspp.getString(2), rspp.getString(3), rsp.getString(1), rsp.getString(3));
                            pagos.add(pago);
                            seguir = 1;
                            System.out.println("agrege el pago!");
                        }
                    }
//
                }
                rsp.beforeFirst();

            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getCause());
        }
    }
}
