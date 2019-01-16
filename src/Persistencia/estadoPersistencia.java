/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.ContCargaBD;
import Logica.dtEstado;
import Logica.dtEstadosPropuestas;
import Logica.dtFecha;
import Logica.dtHora;
import Logica.utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolasgutierrez
 */
public class estadoPersistencia {

    static ConexionDB conexion = ConexionDB.getInstance();
    utilidades util = new utilidades();
//    ContCargaBD contCarga = ContCargaBD.getInstance();

    private ArrayList<dtEstadosPropuestas> estados = new ArrayList<>();

    public static boolean agregarestado(String nombre) {
        try {
            String sql = "INSERT INTO `cultuRarte`.'estado' ('estado') VALUES ('" + nombre + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //conexion.cerrar(conn);

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarestado(String nombre) {
        try {
            String sql = "DELETE FROM `cultuRarte`.'estado' WHERE estado='" + nombre + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //  conexion.cerrar(conn);

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void CargarEstados(ArrayList<dtEstado> nomEstados) {
        try {
            String sql = "SELECT * FROM cultuRarte.estado";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dtEstado esta = new dtEstado(rs.getInt(2), rs.getString(1));
                nomEstados.add(esta);
            }
            //   conexion.cerrar(conn);
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public void agregarEstadosPropuestas(dtEstadosPropuestas dtep) {
        try {
            String sql = null;
            sql = "INSERT INTO `cultuRarte`.`estadoPropuesta`(`propuesta`,`estado`,`fecha`,`hora`,, `fechaFinal`)VALUES('" + dtep.getTituloprop() + "','" + dtep.getEstado() + "','" + dtep.getFecha().getFecha() + "','" + dtep.getHora().getHora() + "','" + dtep.getFechaFin().getFecha() + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //     conexion.cerrar(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

   
    private void setearEstadoPropuesta(dtEstadosPropuestas dt) {
        estados.add(dt);

    }

    public void CargarEstadosPropuestas(ArrayList<dtEstadosPropuestas> estados) {
        try {
            String sql = null;
            sql = "SELECT * FROM cultuRarte.estadoPropuesta";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dtFecha dtf = (dtFecha)util.construirFecha(rs.getString(3));
                System.out.println(rs.getString(1) + " " + rs.getString(4));
                dtHora dth = (dtHora)util.construirHora(rs.getString(4));
                dtFecha dtff =null;
                if(rs.getString(5)!=null){
                dtff= (dtFecha)util.construirFecha(rs.getString(5));}
                dtEstadosPropuestas dt = new dtEstadosPropuestas(rs.getString(1), rs.getString(2), dtf, dth,dtff);
                setearEstadoPropuesta(dt);
                estados.add(dt);
            }
            //      conexion.cerrar(conn);

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());

        }
    }

    /**
     * @return the estados
     */
    public ArrayList<dtEstadosPropuestas> getEstados() {
        return estados;
    }
}
