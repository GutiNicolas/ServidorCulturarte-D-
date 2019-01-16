/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtColaborador;
import Logica.dtFavoritos;
import Logica.dtFecha;
import Logica.dtHora;
import Logica.dtProponente;
import Logica.dtSeguidores;
import Logica.dtUsuario;
import Logica.utilidades;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class usuariosPersistencia {

    static ConexionDB conexion = ConexionDB.getInstance();
    utilidades util = new utilidades();

    public void altaUsuario(dtUsuario dtUsu) throws Exception {
        try {
            String sqlUsu = null, sqlProp = null, sqlCol = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();
            if (dtUsu instanceof dtProponente) {
                dtProponente dtProp = (dtProponente) dtUsu;
                sqlUsu = "INSERT INTO `usuario`(`idUsuario`, `nombre`, `apellido`, `email`, `fechaNacimiento`, `imagen`,`password`)VALUES('" + dtProp.getNickname() + "','" + dtProp.getNombre() + "','" + dtProp.getApellido() + "','" + dtProp.getEmail() + "','" + dtProp.getFechaNac().getFecha() + "','" + dtProp.getImagen() + "','" + dtProp.getPass() + "')";
                sqlProp = "INSERT INTO `Proponente` (`id_usuario`, `direccion`, `pag_web`, `biografia`, `desactivado`) VALUES ('" + dtProp.getNickname() + "','" + dtProp.getDireccion() + "','" + dtProp.getSitioWeb() + "','" + dtProp.getBiografia() + "',"+false+")";
                st.executeUpdate(sqlUsu);
                st.executeUpdate(sqlProp);

            }

            if (dtUsu instanceof dtColaborador) {
                dtColaborador dtCol = (dtColaborador) dtUsu;
                sqlUsu = "INSERT INTO `usuario`(`idUsuario`, `nombre`, `apellido`, `email`, `fechaNacimiento`, `imagen`,`password`)VALUES('" + dtCol.getNickname() + "','" + dtCol.getNombre() + "','" + dtCol.getApellido() + "','" + dtCol.getEmail() + "','" + dtCol.getFechaNac().getFecha() + "','" + dtCol.getImagen() + "','" + dtCol.getPass() + "')";
                sqlCol = "INSERT INTO colaborador (`idUsuario`) VALUES ('" + dtCol.getNickname() + "')";
                st.executeUpdate(sqlUsu);
                st.executeUpdate(sqlCol);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            // throw new Exception("Error al insertar los datos en la BD");

        }

    }

    public ArrayList<dtUsuario> cargaUsuarios() {
        ArrayList<dtUsuario> usuarios = new ArrayList<>();
        armarColaboradores(usuarios);
        int tam = usuarios.size();
        armarProponentes(usuarios);
        tam = usuarios.size();

        return usuarios;
    }

    /* while (rs2.next()){
                String codigo=rs2.getString("idUsuario");
                String[] splited=rs2.getString("fechaNacimiento").split("/");                
                dtFecha dtf2=new dtFecha(splited[0],splited[1],splited[2]);*/
    public void armarProponentes(ArrayList<dtUsuario> usuarios) {
        Connection conn = conexion.getConexion();

        try {

            String sqlUsu = "SELECT * FROM cultuRarte.usuario";
            String sqlProp = "SELECT * FROM cultuRarte.Proponente";
            Statement stUsu = (Statement) conn.createStatement();
            Statement stprop = (Statement) conn.createStatement();
            ResultSet usu = stUsu.executeQuery(sqlUsu);
            ResultSet prop = stprop.executeQuery(sqlProp);
            int seguir = 0;
            while (prop.next()) {
                seguir = 0;
                usu.beforeFirst();
                while (usu.next() && seguir == 0) {
                    if (prop.getString(1).equals(usu.getString(1)) == true) {
                        dtProponente dtProp = new dtProponente(usu.getString(2), usu.getString(3), usu.getString(1), usu.getString(6), usu.getString(4), (dtFecha) util.construirFecha(usu.getString(5)), prop.getString(2), prop.getString(4), prop.getString(3), usu.getString(7), prop.getBoolean(5));
                        usuarios.add(dtProp);
                        seguir = 1;
                    }

                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void armarColaboradores(ArrayList<dtUsuario> usuarios) {

        try {

            Connection conn = conexion.getConexion();
            String sqlCol = "SELECT * FROM cultuRarte.colaborador";
            String sqlUsu = "SELECT * FROM cultuRarte.usuario";
            int seguir = 0;
            Statement stUsu = (Statement) conn.createStatement();
            Statement stcol = (Statement) conn.createStatement();
            ResultSet usu = stUsu.executeQuery(sqlUsu);
            ResultSet col = stcol.executeQuery(sqlCol);
            while (col.next()) {
                seguir = 0;
                usu.beforeFirst();
                while (usu.next() && seguir == 0) {
                    if (col.getString(1).equals(usu.getString(1))) {
                        dtUsuario dtCol = new dtColaborador(usu.getString(2), usu.getString(3), usu.getString(1), usu.getString(6), usu.getString(4), (dtFecha) util.construirFecha(usu.getString(5)), usu.getString(7));
                        usuarios.add(dtCol);
                        seguir = 1;

                    }

                }
            }
            // conexion.cerrar(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void seguidores(ArrayList<dtSeguidores> seguidores) {
        try {
            Connection conn = conexion.getConexion();
            String sql = "SELECT * FROM cultuRarte.Seguidores";
            Statement usuSeg = conn.createStatement();
            ResultSet usu = usuSeg.executeQuery(sql);
            while (usu.next()) {
                dtSeguidores dtseg = new dtSeguidores(usu.getString(1), usu.getString(2));
                seguidores.add(dtseg);
            }

        } catch (Exception e) {

            System.err.println(e.getMessage());
        }

    }

    public void levantarFavoritos(ArrayList<dtFavoritos> favo) {
        try {
            String sql = "SELECT * FROM cultuRarte.Favoritos";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dtFavoritos dtFav = new dtFavoritos(rs.getString(1), rs.getString(2), null, null);
                favo.add(dtFav);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void agregarpropcomofav(String nickusuario, String titulo) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();
            sql = "INSERT INTO `cultuRarte`.`Favoritos`(`nickusuario`,`tituloprop`) VALUES ('" + nickusuario + "','" + titulo + "')";
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean eliminacionLogicaProponente(String nick) {
        try {
            dtFecha fecha = (dtFecha)util.getFecha();
            dtHora hora = (dtHora)util.getHora();
            String sql = "UPDATE `Proponente` SET `desactivado`=" + true + " WHERE `id_usuario`='" + nick + "'";
            String sql2 = "INSERT INTO `usuariosDesactivados`(`nickname`, `fecha`, `hora`) VALUES ('"+nick+"','"+fecha.getFecha()+"','"+hora.getHora()+"')";
            Connection con = conexion.getConexion();
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();
            st2.executeUpdate(sql2);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
            return false;
        }
    }
}
