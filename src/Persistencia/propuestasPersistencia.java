/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtFecha;
import Logica.dtPropuestasBD;
import Logica.utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nicolasgutierrez
 */
public class propuestasPersistencia {

    ConexionDB conexion = ConexionDB.getInstance();
    utilidades util = new utilidades();

    public void altaPropuesta(dtPropuestasBD dtp) throws SQLException {
        try {
            String sql = null;
            sql = "INSERT INTO `cultuRarte`.`Propuesta`(`titulo`, `descripcion`, `imagen`, `lugar`, `fecha`, `precio_entrada`, `monto_necesario`, `fecha_publicacion`, `proponente`, `categoria`, `retorno`)"
                    + " VALUES ('" + dtp.getTitulo() + "','" + dtp.getDescripcion() + "','" + dtp.getImagen() + "','" + dtp.getLugar() + "','" + dtp.getFecha().getFecha() + "','" + Integer.toString(dtp.getPrecio_entrada()) + "','" + Integer.toString(dtp.getMonto_necesario()) + "','" + dtp.getFecha_publicacion().getFecha() + "','" + dtp.getNickproponente() + "','" + dtp.getCategoria() + "','" + dtp.getRetorno() + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            //   conexion.cerrar(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public void cargarPropuestas(ArrayList<dtPropuestasBD> propuestas) {
        try {
            String sql = "SELECT * FROM cultuRarte.Propuesta";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dtPropuestasBD dt = null;
                dtFecha dtf = (dtFecha) util.construirFecha(rs.getString(5));
                dtFecha dtfp = (dtFecha) util.construirFecha(rs.getString(8));
                int precio = 0;
                int monto = 0;
                precio = Integer.parseInt(rs.getString(6));
                monto = Integer.parseInt(rs.getString(7));//separar esto para solucionar lo de las imagenes en null
                if (rs.getString(3) != null&&!rs.getString(3).equals("null")&&!rs.getString(3).equals("")&&!rs.getString(3).isEmpty()) {
                    String imagen = rs.getString(3);
                    dt = new dtPropuestasBD(rs.getString(1), rs.getString(9), rs.getString(2), imagen, rs.getString(4), rs.getString(10), rs.getString(11), dtf, dtfp, precio, monto);
                    System.out.println("Con imagen");
                }
                if (rs.getString(3) == null||rs.getString(3).equals("null")||rs.getString(3).equals("")||rs.getString(3).isEmpty()) {
                    dt = new dtPropuestasBD(rs.getString(1), rs.getString(9), rs.getString(2), null, rs.getString(4), rs.getString(10), rs.getString(11), dtf, dtfp, precio, monto);
                    System.out.println("Sin imagen");
                }
                propuestas.add(dt);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+" Causa: "+ex.getCause());

        }

    }

    public Map<String, String> cargarPropuestasNOBorrar() {
        Map<String, String> retorno = new HashMap<>();
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();
            sql = "SELECT * FROM `cultuRarte`.`propPer`";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nick = rs.getString(1);
                retorno.put(nick, nick);
            }

            return retorno;

        } catch (Exception e) {

            System.err.println(e.getMessage());
            return null;
        }
    }

    public void actualizarPropuesta(dtPropuestasBD dtp) throws SQLException {
        try {
            String sql = null;
            sql = "UPDATE `cultuRarte`.`Propuesta` SET `descripcion`='" + dtp.getDescripcion() + "', `imagen`='" + dtp.getImagen() + "', `lugar`='" + dtp.getLugar() + "', `fecha`='" + dtp.getFecha().getFecha() + "', `precio_entrada`='" + Integer.toString(dtp.getPrecio_entrada()) + "', `monto_necesario`='" + Integer.toString(dtp.getMonto_necesario()) + "', `fecha_publicacion`='" + dtp.getFecha_publicacion().getFecha() + "', `proponente`='" + dtp.getNickproponente() + "', `categoria`='" + dtp.getCategoria() + "', `retorno`='" + dtp.getRetorno() + "' WHERE `titulo`='" + dtp.getTitulo() + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

}
