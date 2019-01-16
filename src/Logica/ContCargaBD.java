/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.BDCulturarte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author juan
 */
public class ContCargaBD implements iContCargaBD {
//import clases

    private static ContCargaBD instance;
    private BDCulturarte bdCul = new BDCulturarte();
    private utilidades util = utilidades.getInstance();
//arreglos dt para carga -- filtrados!
    private ArrayList<dtUsuario> usuariosPer = new ArrayList<>();
    private ArrayList<dtCategoria> categoriasPer = new ArrayList<>();
    private ArrayList<dtEstado> estadosPer = new ArrayList<>();
    private ArrayList<dtPropuestasBD> propuestasPer = new ArrayList<>();
    private ArrayList<dtColaboracionCompleto> colaboracionesPer = new ArrayList<>();
    private ArrayList<dtEstadosPropuestas> estadosPropuestaPer = new ArrayList<>();
    private ArrayList<dtSeguidores> seguidoresUPer = new ArrayList<>();
    private ArrayList<dtFavoritos> favoritosPer = new ArrayList<>();
    private ArrayList<dtPago> pagosPer = new ArrayList<>();
//arreglos primitivos para carga -- bruto
    private ArrayList<String> usuPer = new ArrayList<>();
    private ArrayList<String> propPer = new ArrayList<>();
    private ArrayList<dtColaboraciones> colPer = new ArrayList<>();
    private ArrayList<dtEstadosPropuestas> estaPropPer = new ArrayList<>();
    private ArrayList<dtSeguidores> seguidoresPer = new ArrayList<>();
    private ArrayList<dtCategoria> catPer = new ArrayList<>();
    private ArrayList<dtFavoritos> favPer = new ArrayList<>();
    private ArrayList<dtPago> pagPer = new ArrayList<>();
//Variables movimiento de imagenes --

    private String imagenUSU = null, imagenPropuesta = null, imagenLevantar = null;

//Variables properties    
    private static Properties propiedades = null;
    ArrayList<String> clavesPropiedades = new ArrayList<>();
    HashMap<String, String> configProp = new HashMap<>();
    private String datbase, hostdb, paswdb, userdb,
            puertodb, wscip, wscpuerto, wscname, wsuip,
            wsupuerto, wsuname, wspip, wsppuerto, wspname,
            imgusuini, imgusudes, imgpropini, imgpropdes;

    public static ContCargaBD getInstance() {
        if (instance == null) {
            instance = new ContCargaBD();
        }
        return instance;
    }

    public ContCargaBD() {

    }
//funciones levantar per -3-

    @Override
    public void levantaBDusuPer() {
        bdCul.levantaUsusOrigin(usuPer);
    }

    @Override
    public void levantarBDproPer() {
        bdCul.levantarPropuestasOrigin(propPer);
    }

    @Override
    public void levantarBDcolPer() {
        bdCul.levantarColaboracionesOrigin(colPer);
    }

    @Override
    public void levantarBDestadosPropPer() {
        bdCul.levantarEstadosOrigin(estaPropPer);
    }

    @Override
    public void levantarBDseguidoresPer() {
        bdCul.levantarSeguidoresOrigin(seguidoresPer);
    }

    @Override
    public void levantarBDCategoriasPer() {
        bdCul.levantarCategoriasOrigin(catPer);
    }

    public void levantarBDfavoritosPer() {
        bdCul.levantarFavoritosOrigin(favPer);
    }

    public boolean limpiarCargar() {

        try {
            System.out.println("Limpiar y cargar inicio");
            System.out.println("/////////////////////");
            System.out.println("Limpio todo propuesta");
            if (truncarPropuestas()) {
                System.out.println("ok propuestas");
            }
            System.out.println("/////////////////////");
            System.out.println("Limpio todo usuario");
            if (truncarUsuarios()) {
                System.out.println("ok usuarios");
            }
            System.out.println("Arranca carga de datos...");
            if (cargarDatos()) {
                System.out.println("OK CARGA....");
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean cargarDatos() {

        try {
            cargarUsuarios();
            cargarEstados();
            cargarCategorias();
            cargarPropuestas();
            cargarColaboraciones();
            cargarEstadosPropuestas();
            cargarSeguidores();
            cargarFavoritos();
            cargarPagos();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean comparaCadenas(String cad1, String cad2) {

        return cad1.equals(cad2);

    }

    @Override
    public void setearCategoria(ArrayList<dtCategoria> dtcate) {
        try {
            for (int i = 0; i < dtcate.size(); i++) {
                dtCategoria dtcat = dtcate.get(i);
                for (int p = 0; p < catPer.size(); p++) {
                    dtCategoria control = catPer.get(p);
                    System.out.println(dtcat.getNombre() + " - " + control.getNombre());
                    if (comparaCadenas(dtcat.getNombre(), control.getNombre())) {
                        categoriasPer.add(dtcat);

                    }

                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void setearEstado(ArrayList<dtEstado> estadosV) {
        try {

            for (int i = 0; i < estadosV.size(); i++) {
                dtEstado e = (dtEstado) estadosV.get(i);
                estadosPer.add(e);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private boolean comparaFecha(dtFecha fecha1, dtFecha fecha2) {
        String fecha = null, fechacomp = null;
        fecha = fecha1.getFecha();
        fechacomp = fecha2.getFecha();
        return fechacomp.equals(fecha);
    }

    @Override
    public void setearEstadoPropuesta(dtEstadosPropuestas estProp) {
        try {
            System.out.println("setearEstadoPropuesta incio...");
            for (int i = 0; i < estaPropPer.size(); i++) {
                dtEstadosPropuestas dtcontrol = (dtEstadosPropuestas) estaPropPer.get(i);
                String estado = null, titulo = null;
                estado = dtcontrol.getEstado();
                titulo = dtcontrol.getTituloprop();
                dtFecha fecha = dtcontrol.getFecha();
                if (estProp.getEstado().equals(estado)) {
                    if (estProp.getTituloprop().equals(titulo)) {
                        if (comparaFecha(estProp.getFecha(), fecha)) {
                            estadosPropuestaPer.add(estProp);
                        }
                    }
                }
            }
            System.out.println("setearEstadoPropuesta fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean compruebaSeguidores(dtSeguidores ori, dtSeguidores comparar) {

        try {
            String nickusuarioORI = ori.getNickusuario(), nickaseguirORI = ori.getNickaseguir();
            String nickusuarioCOMP = comparar.getNickusuario(), nickaseguirCOMP = comparar.getNickaseguir();
            if (nickusuarioORI.equals(nickusuarioCOMP)) {
                if (nickaseguirORI.equals(nickaseguirCOMP)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;

    }

    @Override
    public void seteardtSeguidores(dtSeguidores dtseg) {
// filtro seguidoresPer 

        try {
            for (int i = 0; i < seguidoresPer.size(); i++) {
                dtSeguidores control = (dtSeguidores) seguidoresPer.get(i);
                if (compruebaSeguidores(dtseg, control)) {
                    seguidoresUPer.add(dtseg);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }

    }

    @Override
    public void agregardtusu(dtUsuario us) {
        try {
            for (int i = 0; i < usuPer.size(); i++) {
                String usuN = usuPer.get(i);
                if (us.getNickname().equals(usuN)) {
                    usuariosPer.add(us);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void agregardtpropuesta(ArrayList<dtPropuestasBD> dtpropuesta) {
        try {
            for (int p = 0; p < dtpropuesta.size(); p++) {
                dtPropuestasBD dtprop = (dtPropuestasBD) dtpropuesta.get(p);
                for (int i = 0; i < propPer.size(); i++) {
                    String tituloP = propPer.get(i);
                    if (dtprop.getTitulo().equals(tituloP)) {
                        propuestasPer.add(dtprop);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void agregardtcolaboraciones(dtColaboracionCompleto dtcol) {
        try {
            for (int i = 0; i < colPer.size(); i++) {
                dtColaboraciones dtcola = colPer.get(i);
                if ((dtcol.getNickname().equals(dtcola.getNickname()) == true) && (dtcol.getTitulo().equals(dtcola.getIdPropuesta()))) {
                    if (comparaFecha(dtcol.getFecha(), dtcola.getFecha())) {
                        colaboracionesPer.add(dtcol);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setearFavoritos(dtFavoritos fav) {
        try {
            for (int i = 0; i < favPer.size(); i++) {
                dtFavoritos f = favPer.get(i);
                if (f.getUsuario().equals(fav.getUsuario())) {
                    if (f.getPropuestaTitulo().equals(fav.getPropuestaTitulo())) {
                        favoritosPer.add(fav);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     *
     */
    private void cargarUsuarios() {
        System.out.println("Carga de datos: cargar usuarios inicio");
        try {
            int cont = 0;
            for (int i = 0; i < usuariosPer.size(); i++) {
                dtUsuario usuAlta = (dtUsuario) usuariosPer.get(i);
                System.out.println("Cargo Us: " + usuAlta.getNickname());
                bdCul.altaUsuario(usuAlta);
                cont++;
            }
            System.err.println("----------------------");
            System.out.println("Carge usuarios: " + cont);
            System.out.println("Carga de datos: cargar usuarios fin!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

    private void cargarFavoritos() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: prop favoritas inicio...");
            for (int i = 0; i < favoritosPer.size(); i++) {
                dtFavoritos f = favoritosPer.get(i);
                System.out.println("Nick: " + f.getUsuario());
                System.out.println("Prop fav: " + f.getPropuestaTitulo());
                bdCul.agregarFavoritosCD(f.getUsuario(), f.getPropuestaTitulo());
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Carga de datos: prop favoritas fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void cargarEstados() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: cargar estados inicio...");
            for (int i = 0; i < estadosPer.size(); i++) {
                dtEstado esta = (dtEstado) estadosPer.get(i);
                System.out.println("Estado: " + esta.getNombre());
                bdCul.agregarEstadoCD(esta);
                cont++;
            }
            System.err.println("---------------------");
            System.out.println("Carge estados: " + cont);
            System.out.println("Carga de datos: cargar estados fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private void cargarCategorias() {
        try {
            System.out.println("Carga de datos: categorias inicio...");
            int cont = 0;
            for (int i = 0; i < categoriasPer.size(); i++) {

                dtCategoria dtcat = categoriasPer.get(i);
                System.out.println("Categoria: " + dtcat.getNombre());
                bdCul.altaCategoriaCD(dtcat);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Carga de datos: cargar categorias fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void cargarPropuestas() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: Propuestas inicio...");
            for (int i = 0; i < propuestasPer.size(); i++) {//revisar 
                dtPropuestasBD prop = (dtPropuestasBD) propuestasPer.get(i);
                System.out.println("Propuesta: " + prop.getTitulo());
                bdCul.altaPropuestaCD(prop);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Carga de datos: Propuestas fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private void cargarColaboraciones() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: cargar colaboraciones inicio...");
            for (int i = 0; i < colaboracionesPer.size(); i++) {//rev
                dtColaboracionCompleto dtcola = (dtColaboracionCompleto) colaboracionesPer.get(i);
                System.out.println("Colaboracion: " + dtcola.getNickname());
                bdCul.altaColaboracionCD(dtcola);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Carga de datos: cargar colaboraciones fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void cargarEstadosPropuestas() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: cargar estados propuestas inicio...");
            for (int i = 0; i < estadosPropuestaPer.size(); i++) {
                dtEstadosPropuestas estaprop = (dtEstadosPropuestas) estadosPropuestaPer.get(i);
                System.out.println("Estado: " + estaprop.getEstado() + " Prop: " + estaprop.getTituloprop());
                bdCul.agregarPropEstadoCD(estaprop);
                cont++;
            }
            System.out.println("Cargue: " + cont);
            System.out.println("Carga de datos: cargar estados propuestas fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void cargarSeguidores() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: cargar seguidores inicio...");
            for (int i = 0; i < seguidoresUPer.size(); i++) {
                dtSeguidores sig = seguidoresUPer.get(i);
                System.out.println("Seguidor: " + sig.getNickaseguir());
                bdCul.agregarSeguidoresCD(sig);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Carga de datos: cargar seguidores fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }

    }

    private void cargarPagos() {
        try {
            int cont = 0;
            System.out.println("Carga de datos: cargar pagos inicio...");
            for (int i = 0; i < pagosPer.size(); i++) {
                dtPago pago = (dtPago) pagosPer.get(i);
                System.out.println("pago: " + pago.getNickname());
                bdCul.agregarPagosCD(pago);
                cont++;
            }
            System.out.println("Cargue: " + cont);
            System.out.println("Carga de datos: cargar pagos fin...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void cargaOrigin() {
        if (cargaUsuariosOrigin()) {
            System.out.println("okUsusOrigin");
        }
        if (cargaPropuestasOrigin()) {
            System.out.println("okPropOrigin");
        }
        if (cargaColaboracionesOrigin()) {
            System.out.println("okColaOrigin");
        }
        if (cargaEstadoPropuestasOrigin()) {
            System.out.println("okPropEstOrigin");
        }
        if (cargaSeguidoresOrigin()) {
            System.out.println("okSegiOrigin");
        }

    }

    private boolean cargaUsuariosOrigin() {
        //usuPer
        try {
            for (int i = 0; i < usuPer.size(); i++) {
                String usu = usuPer.get(i);
                bdCul.cargaUsuariosOrigin(usu);
            }
            return true;
        } catch (Exception e) {

            System.err.println(e.getMessage());
            return false;

        }

    }

    private boolean cargaPropuestasOrigin() {
        try {
            for (int i = 0; i < propPer.size(); i++) {
                String prop = propPer.get(i);
                bdCul.cargaPropuestasOrigin(prop);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }

    }

    private boolean cargaEstadoPropuestasOrigin() {
        try {
            for (int i = 0; i < estaPropPer.size(); i++) {
                dtEstadosPropuestas esta = (dtEstadosPropuestas) estaPropPer.get(i);
                bdCul.cargaEstadoPropuestasOrigin(esta);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    private boolean cargaSeguidoresOrigin() {
        //seguidoresPer
        try {
            for (int i = 0; i < seguidoresPer.size(); i++) {
                dtSeguidores sig = seguidoresPer.get(i);
                bdCul.cargaSeguidoresOrigin(sig);
            }
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    private boolean cargaColaboracionesOrigin() {
        //colper
        try {
            for (int i = 0; i < colPer.size(); i++) {
                dtColaboraciones cola = (dtColaboraciones) colPer.get(i);
                bdCul.cargaColaboracionesOrigin(cola);
            }
            return true;
        } catch (Exception e) {
        }

        return false;
    }

    private boolean truncarTablas() {

        return bdCul.truncarPer();
    }

    private boolean truncarUsuarios() {
        return bdCul.truncarUsuario();

    }

    private boolean truncarPropuestas() {
        return bdCul.truncarPropuesta();
    }

    void borrarColecciones() {
        this.categoriasPer.clear();
        System.out.println("vacio CategoriasPer");
        this.colPer.clear();
        System.out.println("vacio colPer");
        this.colaboracionesPer.clear();
        System.out.println("vacio colaboracionesPer");
        this.estaPropPer.clear();
        System.out.println("vacio estaPropPer");
        this.estadosPer.clear();
        System.out.println("vacio estadosPer");
        this.estadosPropuestaPer.clear();
        System.out.println("vacio estadosPropuestaPer");
        this.propPer.clear();
        System.out.println("vacio propPer");
        this.propuestasPer.clear();
        System.out.println("vacio propuestasPer");
        this.seguidoresPer.clear();
        System.out.println("vacio seguidoresPer");
        this.seguidoresUPer.clear();
        System.out.println("vacio seguidoresUsPer");
        this.usuPer.clear();
        System.out.println("vacio usuPer");
        this.usuariosPer.clear();
        System.out.println("vacio usuariosPer");
        this.favPer.clear();
        System.out.println("vacio favPer");
        this.favoritosPer.clear();
        System.out.println("vacio favoritosPer");
        this.pagPer.clear();
        System.out.println("vacio pagPer");
        this.pagosPer.clear();
        System.out.println("vacio pagosPer");
        this.clavesPropiedades.clear();
        System.out.println("vacio clavesPropiedades");
        this.configProp.clear();
        System.out.println("vacio configProp");
        propiedades = null;
        System.out.println("vacio TODO");

    }

    /**
     * Retorna un String con la ruta de las imagenes del usuario
     *
     * @return
     */
    public String getImagenUSU() {
        return imagenUSU;
    }

    /**
     * Setea la ruta de las imagenes del usuario
     *
     * @param imagenUSU
     */
    public void setImagenUSU(String imagenUSU) {
        this.imagenUSU = imagenUSU;
    }

    /**
     * Retorna un string con la ruta de las imagenes de propuesta
     *
     * @return
     */
    public String getImagenPropuesta() {
        return imagenPropuesta;
    }

    /**
     * Setea la ruta de las imagenes de las propuestas
     *
     * @param imagenPropuesta
     */
    public void setImagenPropuesta(String imagenPropuesta) {
        this.imagenPropuesta = imagenPropuesta;
    }

    /**
     * @return the imagenLevantar
     */
    public String getImagenLevantar() {
        return imagenLevantar;
    }

    /**
     * @param imagenLevantar the imagenLevantar to set
     */
    public void setImagenLevantar(String imagenLevantar) {
        this.imagenLevantar = imagenLevantar;
    }

    @Override
    public void crearProperties() {
        System.out.println("crearProperties-inicio");
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("configuracionCulturarte.properties");
            prop.setProperty("database", getDatbase());//"cultuRarte"
            prop.setProperty("hostdb", getHostdb());
            prop.setProperty("paswdb", getPaswdb());
            prop.setProperty("userdb", getUserdb());
            prop.setProperty("puertodb", getPuertodb());
            prop.setProperty("wscip", getWscip());
            prop.setProperty("wscpuerto", getWscpuerto());
            prop.setProperty("wscname", getWscname());
            prop.setProperty("wsuip", getWsuip());
            prop.setProperty("wsupuerto", getWsupuerto());
            prop.setProperty("wsuname", getWsuname());
            prop.setProperty("wspip", getWspip());
            prop.setProperty("wsppuerto", getWsppuerto());
            prop.setProperty("wspname", getWspname());
            prop.setProperty("imgusuini", getImgusuini());//"/home/juan/ProgAplicaciones2018/Servidor/Imagenes_mover/imagenesPer/"
            prop.setProperty("imgusudes", getImgusudes());//"/home/juan/ProgAplicaciones2018/Servidor/imagenesPerfil/"
            prop.setProperty("imgpropini", getImgpropini());//"/home/juan/ProgAplicaciones2018/Servidor/Imagenes_mover/imagenesProp/"
            prop.setProperty("imgpropdes", getImgpropdes());// "/home/juan/ProgAplicaciones2018/Servidor/imagenesProp/"
            System.out.println("crearProperties-salvando:configuracionCulturarte.properties");
            prop.store(output, null);
            System.out.println("crearProperties-final");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public Properties levantarProperties() {
        InputStream in = null;
        Properties prop = new Properties();
        System.out.println("levantarProperties inicio...");
        try {

            String ruta = ContCargaBD.class.getResource("").getPath();

            // in = new FileInputStream("configuracion/configuracion.properties");
            in = new FileInputStream(dameArchivo(ruta));
            prop.load(in);

            if (propertiesValido(prop)) {
                propiedades = prop;
                setearPropiesdades();
                Iterator it = prop.keySet().iterator();
                System.out.println("Properties claves cargadas: ");
                while (it.hasNext()) {
                    String key = (String) it.next();
                    clavesPropiedades.add(key);
                    addDiccionarioProp(key, prop.getProperty(key));
                    System.out.println(key);

                }
                System.out.println("carga finalizada");
            } else {
                System.err.println("Properties invalido");
            }

        } catch (Exception e) {
            System.err.println("levantarProperties: " + e.getMessage());
        }

        return prop;
    }

    private boolean setearPropiesdades() {
        try {
            System.out.println("setearPropiedades inicio...");
            setDatbase(propiedades.getProperty("database"));
            setHostdb(propiedades.getProperty("hostdb"));
            setPaswdb(propiedades.getProperty("paswdb"));
            setPuertodb(propiedades.getProperty("puertodb"));
            setUserdb(propiedades.getProperty("userdb"));
            setWscip(propiedades.getProperty("wscip"));
            setWscname(propiedades.getProperty("wscname"));
            setWscpuerto(propiedades.getProperty("wscpuerto"));
            setWspip(propiedades.getProperty("wspip"));
            setWspname(propiedades.getProperty("wspname"));
            setWsppuerto(propiedades.getProperty("wsppuerto"));
            setWsuip(propiedades.getProperty("wsuip"));
            setWsuname(propiedades.getProperty("wsuname"));
            setWsupuerto(propiedades.getProperty("wsupuerto"));
            setImgusuini(propiedades.getProperty("imgusuini"));
            setImgusudes(propiedades.getProperty("imgusudes"));
            setImgpropini(propiedades.getProperty("imgpropini"));
            setImgpropdes(propiedades.getProperty("imgpropdes"));
            System.out.println("setearPropiedades finalizo...");
        } catch (Exception e) {
            System.err.println("setearPropiedades: " + e.getMessage() + " " + e.getCause());
        }
        return false;
    }

    @Override
    public Properties leerProperties(String ruta) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            if (cadenaOk(ruta)) {
                in = new FileInputStream(ruta);
                if (in.available() >= 0) {
                    prop.load(in);
                    Iterator it = prop.keySet().iterator();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        System.out.println(key);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return prop;
    }

    private boolean cadenaOk(String cadena) {
        if (cadena.isEmpty()) {
            return false;
        }
        if (cadena == null) {
            return false;
        }
        if (cadena.equals("")) {
            return false;
        }
        if (cadena.equals("null")) {
            return false;
        }
        if (cadena.length() <= 1) {
            return false;
        }
        if (cadena.equals(" ")) {
            return false;
        }
        return true;
    }

    /**
     * @return the datbase
     */
    public String getDatbase() {
        return datbase;
    }

    /**
     * @param datbase the datbase to set
     */
    public void setDatbase(String datbase) {
        this.datbase = datbase;
    }

    /**
     * @return the hostdb
     */
    public String getHostdb() {
        return hostdb;
    }

    /**
     * @param hostdb the hostdb to set
     */
    public void setHostdb(String hostdb) {
        this.hostdb = hostdb;
    }

    /**
     * @return the paswdb
     */
    public String getPaswdb() {
        return paswdb;
    }

    /**
     * @param paswdb the paswdb to set
     */
    public void setPaswdb(String paswdb) {
        this.paswdb = paswdb;
    }

    /**
     * @return the userdb
     */
    public String getUserdb() {
        return userdb;
    }

    /**
     * @param userdb the userdb to set
     */
    public void setUserdb(String userdb) {
        this.userdb = userdb;
    }

    /**
     * @return the puertodb
     */
    public String getPuertodb() {
        return puertodb;
    }

    /**
     * @param puertodb the puertodb to set
     */
    public void setPuertodb(String puertodb) {
        this.puertodb = puertodb;
    }

    /**
     * @return the wscip
     */
    public String getWscip() {
        return wscip;
    }

    /**
     * @param wscip the wscip to set
     */
    public void setWscip(String wscip) {
        this.wscip = wscip;
    }

    /**
     * @return the wscpuerto
     */
    public String getWscpuerto() {
        return wscpuerto;
    }

    /**
     * @param wscpuerto the wscpuerto to set
     */
    public void setWscpuerto(String wscpuerto) {
        this.wscpuerto = wscpuerto;
    }

    /**
     * @return the wscname
     */
    public String getWscname() {
        return wscname;
    }

    /**
     * @param wscname the wscname to set
     */
    public void setWscname(String wscname) {
        this.wscname = wscname;
    }

    /**
     * @return the wsuip
     */
    public String getWsuip() {
        return wsuip;
    }

    /**
     * @param wsuip the wsuip to set
     */
    public void setWsuip(String wsuip) {
        this.wsuip = wsuip;
    }

    /**
     * @return the wsupuerto
     */
    public String getWsupuerto() {
        return wsupuerto;
    }

    /**
     * @param wsupuerto the wsupuerto to set
     */
    public void setWsupuerto(String wsupuerto) {
        this.wsupuerto = wsupuerto;
    }

    /**
     * @return the wsuname
     */
    public String getWsuname() {
        return wsuname;
    }

    /**
     * @param wsuname the wsuname to set
     */
    public void setWsuname(String wsuname) {
        this.wsuname = wsuname;
    }

    /**
     * @return the wspip
     */
    public String getWspip() {
        return wspip;
    }

    /**
     * @param wspip the wspip to set
     */
    public void setWspip(String wspip) {
        this.wspip = wspip;
    }

    /**
     * @return the wsppuerto
     */
    public String getWsppuerto() {
        return wsppuerto;
    }

    /**
     * @param wsppuerto the wsppuerto to set
     */
    public void setWsppuerto(String wsppuerto) {
        this.wsppuerto = wsppuerto;
    }

    /**
     * @return the wspname
     */
    public String getWspname() {
        return wspname;
    }

    /**
     * @param wspname the wspname to set
     */
    public void setWspname(String wspname) {
        this.wspname = wspname;
    }

    /**
     * @return the imgusuini
     */
    public String getImgusuini() {
        return imgusuini;
    }

    /**
     * @param imgusuini the imgusuini to set
     */
    public void setImgusuini(String imgusuini) {
        this.imgusuini = imgusuini;
    }

    /**
     * @return the imgusudes
     */
    public String getImgusudes() {
        return imgusudes;
    }

    /**
     * @param imgusudes the imgusudes to set
     */
    public void setImgusudes(String imgusudes) {
        this.imgusudes = imgusudes;
    }

    /**
     * @return the imgpropini
     */
    public String getImgpropini() {
        return imgpropini;
    }

    /**
     * @param imgpropini the imgpropini to set
     */
    public void setImgpropini(String imgpropini) {
        this.imgpropini = imgpropini;
    }

    /**
     * @return the imgpropdes
     */
    public String getImgpropdes() {
        return imgpropdes;
    }

    /**
     * @param imgpropdes the imgpropdes to set
     */
    public void setImgpropdes(String imgpropdes) {
        this.imgpropdes = imgpropdes;
    }

    public String getUrlWSCU() {
        return "http://" + getWsuip() + ":" + getWsupuerto() + "/" + getWsuname();
    }

    public String getUrlWSP() {
        return "http://" + getWspip() + ":" + getWsppuerto() + "/" + getWspname();
    }

    public String getUrlWSC() {
        return "http://" + getWscip() + ":" + getWscpuerto() + "/" + getWscname();
    }

    public boolean crearPropertiesWebapp(String ruta) {
        Properties proweb = new Properties();
        proweb.setProperty("wscip", getWscip());
        proweb.setProperty("wscpuerto", getWscpuerto());
        proweb.setProperty("wscname", getWscname());
        proweb.setProperty("wsuip", getWsuip());
        proweb.setProperty("wsupuerto", getWsupuerto());
        proweb.setProperty("wsuname", getWsuname());
        proweb.setProperty("wspip", getWspip());
        proweb.setProperty("wsppuerto", getWsppuerto());
        proweb.setProperty("wspname", getWspname());
        OutputStream output = null;
        try {
            if (ruta == null) {
                output = new FileOutputStream("configuracion/configuracionweb.properties");
                proweb.store(output, null);
            }
            if (ruta != null) {
                output = new FileOutputStream(ruta + "/configuracionweb.properties");
                proweb.store(output, null);
            }
        } catch (Exception e) {
        }

        return false;
    }

    /**
     * @return the propiedades
     */
    public static Properties getPropiedades() {

        return propiedades;
    }

    public boolean setPropiedades(Properties propieda) {
        if (propertiesValido(propieda)) {
            propiedades = propieda;
            setearPropiesdades();

            return true;
        }
        return false;
    }

    public boolean propertiesValido(Properties prope) {
        for (int i = 0; i < clavesPropiedades.size(); i++) {
            String key = clavesPropiedades.get(i);
            if (prope.containsKey(key) != true) {
                return false;
            }
        }
        return true;
    }

    public HashMap getPropiedadesString() {
        return this.configProp;
    }

    private void addDiccionarioProp(String clave, String valor) {
        configProp.put(clave, valor);
    }

    private void cambiarDiccionarioProp(String key, String nuevoValor) {
        configProp.replace(key, nuevoValor);
    }

    public boolean recibeDiccPropiedades(HashMap<String, String> diccprop) {
        System.out.println("Recibi el diccionario");
        try {
            Properties nuevo = new Properties();
            Iterator it = diccprop.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                nuevo.setProperty(key, (String) diccprop.get(key));
            }
            propiedades = nuevo;
            setearPropiesdades();
            crearProperties();
            System.out.println("Lo setee y lo guarde");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public HashMap levantarYCargar(String ruta) {
        System.out.println("levantarYCargar inicio...");
        HashMap<String, String> dicTemp = new HashMap<>();
        Properties p = null;
        try {
            p = (Properties) leerProperties(ruta);
            if (propertiesValido(p)) {
                System.out.println("Properties valido");
                Iterator it = p.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    dicTemp.put(key, (String) p.getProperty(key));
                    System.out.println(key + ":" + (String) p.getProperty(key));
                }
            } else {
                System.err.println("Properties invalido");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Finalizo levantarYCargar...");
        return dicTemp;
    }

    ///////////pagos
    @Override
    public void seteardtPago(dtPago pago) {
        try {
            System.out.println("setearPago inicio...");
            String codigo = null;
            if (pago instanceof dtTarjetaCredito) {
                dtTarjetaCredito tc = (dtTarjetaCredito) pago;
                codigo = tc.getNumeroTarjeta();
                System.out.println("pago es dtTarjetaCredito " + "codigo: " + codigo);
            }
            if (pago instanceof dtTransferencia) {
                dtTransferencia tr = (dtTransferencia) pago;
                codigo = tr.getNumeroCuenta();
                System.out.println("pago es dtTraenciansfe " + "codigo: " + codigo);
            }
            if (pago instanceof dtPaypal) {
                dtPaypal py = (dtPaypal) pago;
                codigo = py.getNumeroPaypal();
                System.out.println("pago es dtPaypal " + "codigo: " + codigo);
            }
            System.out.println("procedo a buscar");
            for (int i = 0; i < pagPer.size(); i++) {
                dtPago p = pagPer.get(i);
                if (p.getNickname().equals(pago.getNickname())) {
                    if (p.getTituloP().equals(pago.getTituloP())) {
                        if (p.getCodigo().equals(codigo)) {
                            pagosPer.add(pago);
                            System.out.println("Encontre el pago");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void levantarBDPagosPer() {
        bdCul.levantarPagosOrigin(pagPer);
    }

    private String generaPathProperties(String cadena) {
        System.out.println("Ruta recibida: " + cadena);
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - 2; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ruta: " + path);
        return path;
    }

    private File dameArchivo(String path) {
        String ruta = generaPathProperties(path);
        File retorno = walking(new File(path));
        if (retorno != null) {
            return retorno;
        }
        return dameArchivo(ruta);

    }

    private File caminar(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals("configuracionCulturarte.properties")) {
                        return (File) listFile[i];
                    }
                }
                if (listFile[i].isDirectory()) {
                    caminar(listFile[i]);
                }

            }

        }
        return null;

    }

    private File walking(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals("configuracionCulturarte.properties")) {
                        return (File) listFile[i];
                    }
                }

            }
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walking(listFile[i]);
                }
            }
        }
        return null;
    }

    private String generaPathPropertiesProf(String cadena, int profundidad) {
        System.out.println("Ruta recibida: " + cadena);
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - profundidad; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ruta: " + path);
        return path;
    }
}
