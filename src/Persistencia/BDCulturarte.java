/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.dtCategoria;
import Logica.dtColaboracionCompleto;
import Logica.dtColaboraciones;
import Logica.dtColaborador;
import Logica.dtEstado;
import Logica.dtEstadosPropuestas;
import Logica.dtFavoritos;
import Logica.dtFecha;
import Logica.dtPago;
import Logica.dtPaypal;
import Logica.dtProponente;
import Logica.dtPropuestasBD;
import Logica.dtSeguidores;
import Logica.dtTarjetaCredito;
import Logica.dtTransferencia;
import Logica.dtUsuario;
import Logica.utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase del controlador de carga, se encarga de levantar datos a persistir y la
 * carga de datos...
 *
 * @author jp
 */
public class BDCulturarte {

    static ConexionDB conexion = ConexionDB.getInstance();
    utilidades util = new utilidades();

    public void levantaUsusOrigin(ArrayList<String> usus) {
        String sql = null;
        Connection conn = conexion.getConexion();

        try {
            sql = "SELECT * FROM cultuRarte.usuPer";
            Statement stusu = conn.createStatement();
            ResultSet rsusu = stusu.executeQuery(sql);
            while (rsusu.next()) {
                String nomUsu = rsusu.getString(1);
                if (nomUsu.isEmpty() != true) {
                    usus.add(nomUsu);
                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void levantarPropuestasOrigin(ArrayList<String> prop) {
        String sql = null;
        try {
            Connection conn = conexion.getConexion();
            sql = "SELECT * FROM cultuRarte.propPer";
            Statement st = conn.createStatement();
            ResultSet rsprop = st.executeQuery(sql);
            while (rsprop.next()) {
                prop.add(rsprop.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void levantarColaboracionesOrigin(ArrayList<dtColaboraciones> dtcol) {
        String sql = null;
        try {
            Connection conn = conexion.getConexion();
            sql = "SELECT * FROM cultuRarte.colPersitencia";
            Statement st = conn.createStatement();
            ResultSet rsCol = st.executeQuery(sql);
            while (rsCol.next()) {
                dtFecha fecha = (dtFecha) util.construirFecha(rsCol.getString(3));
                dtColaboraciones dtcola = new dtColaboraciones(rsCol.getString(1), rsCol.getString(2), fecha);
                dtcol.add(dtcola);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void levantarEstadosOrigin(ArrayList<dtEstadosPropuestas> estadosProp) {
        String sql = null;
        try {
            Connection conn = conexion.getConexion();
            sql = "SELECT * FROM cultuRarte.propEstPer";
            Statement st = conn.createStatement();
            ResultSet rsEstado = st.executeQuery(sql);
            while (rsEstado.next()) {
                dtFecha fecha = (dtFecha) util.construirFecha(rsEstado.getString(3));
                String propid = rsEstado.getString(1);
                String estado = rsEstado.getString(2);

                dtEstadosPropuestas nuevo = new dtEstadosPropuestas(propid, estado, fecha, null);

                estadosProp.add(nuevo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

    }

    public void levantarSeguidoresOrigin(ArrayList<dtSeguidores> segiresPer) {
        try {
            Connection conn = conexion.getConexion();
            String sql = "SELECT * FROM cultuRarte.SeguidoresPer";
            Statement st = conn.createStatement();
            ResultSet rsSeg = st.executeQuery(sql);
            while (rsSeg.next()) {
                dtSeguidores dtseg = new dtSeguidores(rsSeg.getString(1), rsSeg.getString(2));
                segiresPer.add(dtseg);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public void levantarCategoriasOrigin(ArrayList<dtCategoria> catePer) {
        try {
            String sql = "SELECT * FROM cultuRarte.categoriasPer";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rsCatPer = st.executeQuery(sql);
            while (rsCatPer.next()) {

                String padre = null;
                if (rsCatPer.getString(2) != null) {
                    padre = rsCatPer.getString(2);
                }
                dtCategoria dtCat = new dtCategoria(rsCatPer.getString(1), padre);
                catePer.add(dtCat);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public void levantarFavoritosOrigin(ArrayList<dtFavoritos> fav) {

        try {
            String sql = "SELECT * FROM `favoritosPer`";
            Connection con = conexion.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String usuario = null, propuesta = null;
                usuario = rs.getString(1);
                propuesta = rs.getString(2);
                dtFavoritos dtfav = new dtFavoritos(usuario, propuesta, null, null);
                fav.add(dtfav);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void levantarPagosOrigin(ArrayList<dtPago> pagosPer) {
        try {
            String sql = "SELECT * FROM `pagosPersistencia`";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int cont = 0;

            while (rs.next()) {
                dtPago pago = new dtPago(rs.getString(3), null, rs.getString(1), rs.getString(2));
                pagosPer.add(pago);
                System.out.println("Nick usu Pago: " + pago.getNickname());
                cont++;
            }
            System.out.println("Carge pagos: " + cont);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean cargaUsuariosOrigin(String usu) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();
            sql = "INSERT INTO `usuPer`(`idusuPer`)VALUES('" + usu + "')";
            st.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean cargaPropuestasOrigin(String prop) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();

            sql = "INSERT INTO `propPer`(`idpropPer`)VALUES('" + prop + "')";
            st.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public boolean cargaEstadoPropuestasOrigin(dtEstadosPropuestas estaprop) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();

            sql = "INSERT INTO `propEstPer`(`propId`,`estado`,`fecha`)VALUES('" + estaprop.getTituloprop() + "','" + estaprop.getEstado() + "','" + estaprop.getFecha() + "')";
            st.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean cargaSeguidoresOrigin(dtSeguidores sig) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();

            sql = "INSERT INTO `SeguidoresPer`(`usuSeguidor`,`usuSeguido`)VALUES('" + sig.getNickusuario() + "','" + sig.getNickaseguir() + "')";
            st.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean cargaColaboracionesOrigin(dtColaboraciones cola) {
        try {
            String sql = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();

            sql = "INSERT INTO `colPersistencia`(`colaborador`,`propuesta`,`fecha`)VALUES('" + cola.getNickname() + "','" + cola.getIdPropuesta() + "','" + cola.getFecha().getFecha() + "')";
            st.executeUpdate(sql);
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void altaUsuario(dtUsuario dtUsu) throws Exception {
        try {
            String sqlUsu = null, sqlProp = null, sqlCol = null;
            Connection con = conexion.getConexion();
            Statement st = (Statement) con.createStatement();
            if (dtUsu instanceof dtProponente) {
                dtProponente dtProp = (dtProponente) dtUsu;
                String pagWeb = dtProp.getSitioWeb();
                String bio = dtProp.getBiografia();
                sqlUsu = "INSERT INTO `usuario`(`idUsuario`, `nombre`, `apellido`, `email`, `fechaNacimiento`, `imagen`,`password`)VALUES('" + dtProp.getNickname() + "','" + dtProp.getNombre() + "','" + dtProp.getApellido() + "','" + dtProp.getEmail() + "','" + dtProp.getFechaNac().getFecha() + "','" + dtProp.getImagen() + "','" + dtProp.getPass() + "')";
                sqlProp = "INSERT INTO `Proponente` (`id_usuario`, `direccion`, `pag_web`, `biografia`, `desactivado`) VALUES ('" + dtProp.getNickname() + "','" + dtProp.getDireccion() + "','" + dtProp.getSitioWeb() + "','" + dtProp.getBiografia() + "'," + false + ")";
                st.executeUpdate(sqlUsu);
                st.executeUpdate(sqlProp);

            }
//acomodar estos 
            if (dtUsu instanceof dtColaborador) {
                dtColaborador dtCol = (dtColaborador) dtUsu;
                sqlUsu = "INSERT INTO `usuario`(`idUsuario`, `nombre`, `apellido`, `email`, `fechaNacimiento`, `imagen`,`password`)VALUES('" + dtCol.getNickname() + "','" + dtCol.getNombre() + "','" + dtCol.getApellido() + "','" + dtCol.getEmail() + "','" + dtCol.getFechaNac().getFecha() + "','" + dtCol.getImagen() + "','" + dtCol.getPass() + "')";
                sqlCol = "INSERT INTO `colaborador` (`idUsuario`) VALUES ('" + dtCol.getNickname() + "')";
                st.executeUpdate(sqlUsu);
                st.executeUpdate(sqlCol);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            // throw new Exception("Error al insertar los datos en la BD");

        }

    }

    public boolean agregarEstadoCD(dtEstado esta) {
        try {
            String sql = "INSERT INTO `cultuRarte`.`estado` (`estado`,`numero`)VALUES('" + esta.getNombre() + "'," + esta.getNumero() + ")";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //conexion.cerrar(conn);

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean altaPropuestaCD(dtPropuestasBD dtp) {
        try {
            String sql = null;
            sql = "INSERT INTO `Propuesta`(`titulo`, `descripcion`, `imagen`, `lugar`, `fecha`, `precio_entrada`, `monto_necesario`, `fecha_publicacion`, `proponente`, `categoria`, `retorno`)"
                    + " VALUES ('" + dtp.getTitulo() + "','" + dtp.getDescripcion() + "','" + dtp.getImagen() + "','" + dtp.getLugar() + "','" + dtp.getFecha().getFecha() + "','" + Integer.toString(dtp.getPrecio_entrada()) + "','" + Integer.toString(dtp.getMonto_necesario()) + "','" + dtp.getFecha_publicacion().getFecha() + "','" + dtp.getNickproponente() + "','" + dtp.getCategoria() + "','" + dtp.getRetorno() + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            return true;
            //   conexion.cerrar(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return false;
    }

    public boolean altaCategoriaCD(dtCategoria cat) {
        try {
            String sql = null;
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            String padre = cat.getPadre();
            if (padre != null) {
                sql = "INSERT INTO `cultuRarte`.`Categoria`(`nombre`,`padre`)VALUES ('" + cat.getNombre() + "','" + padre + "')";
                st.executeUpdate(sql);
            }
            if (padre == null) {
                sql = "INSERT INTO `cultuRarte`.`Categoria`(`nombre`)VALUES('" + cat.getNombre() + "')";
                st.executeUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean altaColaboracionCD(dtColaboracionCompleto dtcola) {
        try {

            String colaborador = null, titulo = null, fecha = null, hora = null, monto = null, retorno = null;
            colaborador = dtcola.getNickname();
            titulo = dtcola.getTitulo();
            fecha = dtcola.getFecha().getFecha();
            hora = dtcola.getHora().getHora();
            monto = Integer.toString(dtcola.getMonto());
            retorno = dtcola.getRetorno();
            String sql = null;
            if (dtcola.getComentario() != null) {
                sql = "INSERT INTO `cultuRarte`.`Colaboraciones` (`nickusuario`, `tituloprop`, `fecha`, `hora`, `monto`, `retorno`,`comentario`) VALUES ('" + colaborador + "','" + titulo + "','" + fecha + "','" + hora + "','" + monto + "','" + retorno + "','" + dtcola.getComentario() + "')";
            } else {
                sql = "INSERT INTO `cultuRarte`.`Colaboraciones` (`nickusuario`, `tituloprop`, `fecha`, `hora`, `monto`, `retorno`) VALUES ('" + colaborador + "','" + titulo + "','" + fecha + "','" + hora + "','" + monto + "','" + retorno + "')";
            }
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

    public boolean agregarPropEstadoCD(dtEstadosPropuestas dtestaprop) {
        try {
            String titulo = null, estado = null, fecha = null, hora = null, fechafin = null, sql = null;
            titulo = dtestaprop.getTituloprop();
            estado = dtestaprop.getEstado();
            fecha = dtestaprop.getFecha().getFecha();
            hora = dtestaprop.getHora().getHora();
            //fechafin = dtestaprop.getFechaFin().getFecha();
            fechafin = (String) util.getFechaInc(fecha, hora, 30);

            sql = "INSERT INTO `cultuRarte`.`estadoPropuesta`(`propuesta`, `estado`, `fechaInicial`, `hora`, `fechaFinal`) VALUES ('" + titulo + "','" + estado + "','" + fecha + "','" + hora + "','" + fechafin + "')";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            //     conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean agregarSeguidoresCD(dtSeguidores dts) {
        try {
            Connection conn = conexion.getConexion();
            String sql = "INSERT INTO `cultuRarte`.`Seguidores`(`nickusuario`,`nickaseguir`) VALUES ('" + dts.getNickusuario() + "','" + dts.getNickaseguir() + "')";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

            //   conexion.cerrar(conn);
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public void agregarFavoritosCD(String nickusuario, String titulo) {
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

    public void agregarPagosCD(dtPago pago) {

        String sql = null, sql2 = null;
        try {
            Connection conn = conexion.getConexion();
            System.out.println("Agregar Pago inicio...");
            Statement st = conn.createStatement();
            if (pago instanceof dtTransferencia) {
                dtTransferencia dttrans = (dtTransferencia) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + dttrans.getTitular() + "','" + dttrans.getNumeroCuenta() + "','" + dttrans.getNickname() + "','" + dttrans.getTituloP() + "')";
                sql2 = "INSERT INTO `transferenciaBancaria`(`nickname`, `NumTransferencia`, `Banco`, `tituloProp`) VALUES ('" + dttrans.getNickname() + "','" + dttrans.getNumeroCuenta() + "','" + dttrans.getBanco() + "','" + dttrans.getTituloP() + "')";
                st.executeUpdate(sql);

                st.executeUpdate(sql2);

            }
            if (pago instanceof dtPaypal) {
                dtPaypal paypal = (dtPaypal) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + paypal.getTitular() + "','" + paypal.getNumeroPaypal() + "','" + paypal.getNickname() + "','" + paypal.getTituloP() + "')";
                sql2 = "INSERT INTO `paypal`(`nickname`, `NumPaypal`, `tituloProp`) VALUES ('" + paypal.getNickname() + "','" + paypal.getNumeroPaypal() + "','" + paypal.getTituloP() + "')";
                st.executeUpdate(sql);

                st.executeUpdate(sql2);

            }
            if (pago instanceof dtTarjetaCredito) {
                dtTarjetaCredito tarjeta = (dtTarjetaCredito) pago;
                sql = "INSERT INTO `pagos`(`titular`, `NumTTP`, `nickname`, `tituloPropuesta`) VALUES ('" + tarjeta.getTitular() + "','" + tarjeta.getNumeroTarjeta() + "','" + tarjeta.getNickname() + "','" + tarjeta.getTituloP() + "')";
                sql2 = "INSERT INTO `tarjetaCredito`(`nickname`, `NumTarjeta`, `TipoTarjeta`, `fechaVencimiento`, `CVC`, `tituloProp`) VALUES ('" + tarjeta.getNickname() + "','" + tarjeta.getNumeroTarjeta() + "','" + tarjeta.getTipo() + "','" + tarjeta.getfVencimiento().getFecha() + "','" + tarjeta.getCvc() + "','" + tarjeta.getTituloP() + "')";
                st.executeUpdate(sql);

                st.executeUpdate(sql2);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + e.getCause());

        }

    }

    public boolean truncarCategoria() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Categoria`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarColaboraciones() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Colaboraciones`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean truncarPagos() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`pagos`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            truncarPagosTarjetaCredito();
            truncarPagosTransferencia();
            truncarPagosPaypal();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPagosTarjetaCredito() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`tarjetaCredito`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPagosTransferencia() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`transferenciaBancaria`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPagosPaypal() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`paypal`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarFavoritos() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Favoritos`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarProponente() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Proponente`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPropuesta() {
        try {
            truncarEstadoPropuesta();
            truncarColaboraciones();
            truncarPagos();
            truncarEstado();
            truncarCategoria();
            //previo
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Propuesta`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            //posteriormente

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPer() {
        try {
            truncarProPer();
            truncarPropEstPer();
            truncarSeguidoresPer();
            truncarUsuPer();

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }
    }

    public boolean truncarSeguidores() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`Seguidores`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarSeguidoresPer() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`SeguidoresPer`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarColPersistencia() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`colPersistencia`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarColaborador() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`colaborador`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarEstado() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`estado`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarEstadoPropuesta() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`estadoPropuesta`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarPropEstPer() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`propEstPer`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarProPer() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`propPer`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarUsuPer() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`usuPer`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean truncarUsuario() {

        try {
            truncarSeguidores();
            truncarProponente();
            truncarColaborador();
            truncarFavoritos();
            truncarUsuDesactiivado();
            //previamente
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`usuario`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private boolean truncarUsuDesactiivado() {
        try {
            Connection conn = conexion.getConexion();
            String sql = "TRUNCATE `cultuRarte`.`usuariosDesactivados`";
            Statement st = conn.createStatement();
            System.out.println(sql);
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public String levantarRutaImgUsu() {
        String ruta = null;
        try {
            String sql = "SELECT `tipo`, `ubicacion` FROM `ubicacionImagenes` WHERE tipo= 'USUARIO'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ruta = (String) rs.getString(2);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return ruta;
    }

    public void setearRutaImgUsu(String rutaNew) {
        try {
            String sql = "";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String levantarRutaImgPropuesta() {
        String ruta = null;
        try {
            String sql = "SELECT `tipo`, `ubicacion` FROM `ubicacionImagenes` WHERE tipo= 'PROPUESTA'";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ruta = rs.getString(2);
        } catch (Exception e) {
        }

        return ruta;
    }

    public void setearRutaImgPropuestas(String rutaNew) {
        try {
            String sql = "";
            Connection conn = conexion.getConexion();
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
