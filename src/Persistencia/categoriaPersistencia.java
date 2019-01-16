/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.categoria;
import Logica.dtCategoria;
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
public class categoriaPersistencia {

     ConexionDB conexion = ConexionDB.getInstance();

    public void altaCategoria(String nombre, String padre) throws Exception {
        try {
            String sql = null;
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            if(padre!=null){sql = "INSERT INTO `cultuRarte`.`Categoria`(`nombre`,`padre`)VALUES ('" + nombre + "','" + padre + "')";
            }
            if (padre==null){sql = "INSERT INTO `cultuRarte`.`Categoria`(`nombre`)VALUES ('" + nombre + "')";}
            
            st.executeUpdate(sql);
            // conexion.cerrar(conn);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw new Exception("Error al insertar los datos en la BD");
        }

    }

    

    public void borrarCategoria(String nombre) throws Exception {
        try {
            String sql = null;
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            sql = "DELETE FROM `cultuRarte`.`Categoria` WHERE 'nombre' ='" + nombre + "'";
            st.executeUpdate(sql);
            //conexion.cerrar(conn);

        } catch (SQLException ex) {
            throw new Exception("Error al insertar los datos en la BD");
        }

    }

    public void cargarCat(ArrayList<dtCategoria> categorias) {

        try {
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM cultuRarte.Categoria";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = null;
                String padre = null;
                nombre = rs.getString(1);
                padre = rs.getString(2);
                if (padre != null) { //!padre.isEmpty()
                    dtCategoria dtCat = new dtCategoria(nombre, padre);
                    categorias.add(dtCat);
                }
                if (padre==null) {  //padre.isEmpty()
                    dtCategoria dtCat = new dtCategoria(nombre, null);
                    categorias.add(dtCat);
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Map<String, categoria> CargarCategorias() {
        Map<String, categoria> list = new HashMap<String, categoria>();
        try {
            String sql = null;
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            sql = ("SELECT * FROM cultuRarte.Categoria");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String codigo = rs.getString(1);
                categoria c = new categoria(null, codigo);
                list.put(codigo, c);
            }
            String sql2 = null;

            Statement st2 = conn.createStatement();
            sql2 = ("SELECT * FROM cultuRarte.Categoria");
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {
                String clave = rs2.getString(1);
                String padre = null;
                if (!rs2.getString(2).isEmpty()) {
                    padre = rs2.getString(2);
                }
                if (!padre.isEmpty()) {
                    categoria c = (categoria) list.get(clave);
                    categoria p = (categoria) list.get(padre);
                    c.setPadre(p);
                    list.replace(clave, c);
                }

            }
            // conexion.cerrar(conn);

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
