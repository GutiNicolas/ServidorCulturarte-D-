/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.colProp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nicolasgutierrez
 */
public class cancelarcolaboracionPersistencia {

    static ConexionDB conexion = ConexionDB.getInstance();

    public boolean cancelarColaboracion(String nick, colProp cp) throws ClassNotFoundException {
        try {
            Connection conn = conexion.getConexion();
            String sql = "DELETE FROM `cultuRarte`.`Colaboraciones` WHERE nickusuario = '" + nick + "'AND tituloprop ='" + cp.getPropColaborada().getTitulo() + "'";
            Statement stDEL = conn.createStatement();
            stDEL.executeUpdate(sql);
            //conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
