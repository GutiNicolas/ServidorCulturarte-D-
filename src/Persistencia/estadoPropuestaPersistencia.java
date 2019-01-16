/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtFecha;
import Logica.dtPropuestaEstado;
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
public class estadoPropuestaPersistencia {

    ConexionDB conexion = ConexionDB.getInstance();
    utilidades util = new utilidades();

    public boolean agregarPropEstado(String titulo, String estado, String fecha, String hora, String fechaFin) {
        try {
            String sql = null;
            if (fechaFin != null) {
                sql = "INSERT INTO `cultuRarte`.`estadoPropuesta`(`propuesta`, `estado`, `fechaInicial`, `hora`, `fechaFinal`) VALUES ('" + titulo + "','" + estado + "','" + fecha + "','" + hora + "','" + fechaFin + "')";
            }
            if (fechaFin == null) {
                String fin = util.getFechaInc(fecha, hora, 30);
                 sql = "INSERT INTO `cultuRarte`.`estadoPropuesta`(`propuesta`, `estado`, `fechaInicial`, `hora`, `fechaFinal`) VALUES ('" + titulo + "','" + estado + "','" + fecha + "','" + hora + "','" + fin + "')";
            }
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //     conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean eliminarPropEstado(String propuesta, String estado) {
        try {
            String sql = null;
            sql = "DELETE FROM `cultuRarte`.`estadoPropuesta` WHERE propuesta='" + propuesta + "' AND estado='" + estado + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //     conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public List<dtPropuestaEstado> CargarPropuestaEstados() {
        try {
            String sql = "SELECT * FROM estadoPropuesta";
            List<dtPropuestaEstado> lista = new ArrayList<>();
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String fechaFin = null;
                if (rs.getString(5) != null) {
                    fechaFin = rs.getString(5);
                }

                dtPropuestaEstado e = new dtPropuestaEstado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), fechaFin);
                lista.add(e);
            }
            //     conexion.cerrar(conn);
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     *
     * Actualiza en la bd los estados
     */
    public boolean updateFinanciacion(String fecha, String prop, String estado) {
        try {
        String sql = "UPDATE `estadoPropuesta` SET `fechaFinal`='" + fecha + "' WHERE `propuesta`='" + prop + "' AND`estado`='" + estado + "'";
        Connection conn = conexion.getConexion();
        Statement st = conn.createStatement();
        st.executeUpdate(sql);
        return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
            
        }
       
    }

}
