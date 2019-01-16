/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtCreadoresPropuestas;
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
public class creadoresPropuestaPersistencia {
    
   static ConexionDB conexion = ConexionDB.getInstance();
    
    public static boolean agregarCreador(String proponente, String titulo) {
        try {
            String sql = "INSERT INTO 'CreadoresPropuesta' ('nickusuario', 'tituloprop') VALUES ('" + proponente + "','" + titulo + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);            
     //       conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }        
    }
    
    public boolean eliminarCreador(String proponente, String titulo) {
        try {
            String sql = "DELETE FROM 'CreadoresPropuesta' WHERE nickusuario='" + proponente + "' AND tituloprop='" + titulo + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
      //      conexion.cerrar(conn);
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }        
    }
    
    public String quienMeCreo(String titulo) {
        try {
            String sql = "SELECT 'nickusuario' FROM 'CreadoresPropuesta' WHERE tituloprop='" + titulo + "'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet ret = st.executeQuery(sql);
            String retorno = ret.getString("nickusuario");
         //   conexion.cerrar(conn);
            return retorno;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }        
        
    }
    
    public List<dtCreadoresPropuestas> cargarCreadores() {
        
        try {
            List<dtCreadoresPropuestas> list = new ArrayList<>();
            String sql = "SELECT * FROM CreadoresPropuesta";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                dtCreadoresPropuestas dt = new dtCreadoresPropuestas(rs.getString("nickusuario"), rs.getString("tituloprop"));
                list.add(dt);
                
            }
        //    conexion.cerrar(conn);
            return list;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }        
    }
}
