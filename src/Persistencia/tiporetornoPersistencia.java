/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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
public class tiporetornoPersistencia {
    
   static ConexionDB conexion = ConexionDB.getInstance();
    
    public List<String> cargarRetornos(){
     try{
        List<String> list= new ArrayList<>();
        String sql="SELECT * FROM tipoRetorno";
         Connection conn = conexion.getConexion();
         Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){              
            list.add(rs.getString("nombre"));
            
        }
     //   conexion.cerrar(conn);
            return list;
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } 
    }
}
