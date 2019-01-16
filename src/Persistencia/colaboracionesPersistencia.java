/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtColaboracionCompleto;
import Logica.dtFecha;
import Logica.dtHora;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nicolasgutierrez
 */
public class colaboracionesPersistencia {

    ConexionDB conexion = ConexionDB.getInstance();

    public boolean registrarColaboracion(String colaborador, String titulo, String fecha, String hora, String monto, String retorno) {
        try {

            String sql = null;
            sql = "INSERT INTO `cultuRarte`.`Colaboraciones` (`nickusuario`, `tituloprop`, `fecha`, `hora`, `monto`, `retorno`) VALUES ('" + colaborador + "','" + titulo + "','" + fecha + "','" + hora + "','" + monto + "','" + retorno + "')";

            /*  if (comentario != null) {
                sql = "INSERT INTO `cultuRarte`.`Colaboraciones` (`nickusuario`, `tituloprop`, `fecha`, `hora`, `monto`, `retorno`, `comentario`) VALUES ('" + colaborador + "','" + titulo + "','" + fecha + "','" + hora + "','" + monto + "','" + retorno + "','" + comentario + "')";
            } else {
                sql = "INSERT INTO `cultuRarte`.`Colaboraciones` (`nickusuario`, `tituloprop`, `fecha`, `hora`, `monto`, `retorno`) VALUES ('" + colaborador + "','" + titulo + "','" + fecha + "','" + hora + "','" + monto + "','" + retorno + "')";
            }*/
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            // conexion.cerrar(conn);

            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public boolean eliminarColaboracion(String colaborador, String titulo) {
        try {
            String sql = null;
            sql = "DELETE FROM `cultuRarte`.`Colaboraciones` WHERE nickusuario='" + colaborador + "' AND tituloprop ='" + titulo + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            // conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
            return false;
        }
    }

    public dtFecha construirFecha(String fecha) {
        String[] splited = fecha.split("/");
        dtFecha fec = new dtFecha(splited[0], splited[1], splited[2]);
        return fec;
    }

    public dtHora construirHora(String hora) {

        String[] h = hora.split(":");
        dtHora dth = new dtHora(Integer.parseInt(h[0]), Integer.parseInt(h[1]));
        return dth;
    }

    public void agregarcomentarioaprop(String nick, String titulo, String comentario) {
        try {

            String sql = "UPDATE `cultuRarte`.`Colaboraciones` SET `comentario`='" + comentario + "' WHERE `tituloprop`='" + titulo + "' AND `nickusuario`='" + nick + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void cargarColaboraciones(ArrayList<dtColaboracionCompleto> dtcolaboraciones) {
        try {

            String sql = "SELECT * FROM cultuRarte.Colaboraciones";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String fechaS = rs.getString(3);
                dtFecha dtf = construirFecha(fechaS);
                String horaS = rs.getString(4);
                dtHora dth = construirHora(horaS);
                int monto = Integer.parseInt(rs.getString(5));
                dtColaboracionCompleto dtc = null;
                if (rs.getString(7) != null) {
                    dtc = new dtColaboracionCompleto(rs.getString(1), rs.getString(2), dtf, dth, monto, rs.getString(6), rs.getString(7));
                } else {
                    dtc = new dtColaboracionCompleto(rs.getString(1), rs.getString(2), dtf, dth, monto, rs.getString(6), null);
                }
                dtcolaboraciones.add(dtc);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
}
