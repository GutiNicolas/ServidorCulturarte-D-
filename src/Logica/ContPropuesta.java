/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.categoriaPersistencia;
import Persistencia.estadoPersistencia;
import Persistencia.estadoPropuestaPersistencia;
import Persistencia.propuestasPersistencia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author nicolasgutierrez
 */
public class ContPropuesta implements iConPropuesta {

    private ArrayList<String> listaImagenes = new ArrayList<>();
    private static ContPropuesta instance;
    private ContUsuario cUsuario = ContUsuario.getInstance();
    private Map<String, categoria> categorias = new HashMap<>();
    private Map<String, estado> estados = new HashMap<>();
    categoriaPersistencia catPer = new categoriaPersistencia();
    estadoPersistencia estPer = new estadoPersistencia();
    propuestasPersistencia propPer = new propuestasPersistencia();
    estadoPropuestaPersistencia estPropPer = new estadoPropuestaPersistencia();
    ArrayList<propuesta> propuestas = new ArrayList<>();
    private Map<String, Integer> idEstado = new HashMap<>();
    private ContCargaBD contCarga = ContCargaBD.getInstance();
    ArrayList<propuesta> propFiltradaING = new ArrayList<>();
    ArrayList<propuesta> propCambioEstadoAuto = new ArrayList<>();
    ArrayList<dtEstadosPropuestas> arregloDtEstProp = new ArrayList<>();
    utilidades util = utilidades.getInstance();
    ArrayList< dtCategoria> cat = new ArrayList<>();
    ArrayList<String> estadosString = new ArrayList<>();
    private String imagenDestino = contCarga.getImgpropdes();
    private String imagenInicio = contCarga.getImgpropini();

    private void cargaridEstado(ArrayList<dtEstado> nomEstados) {
        try {
            for (int i = 0; i < nomEstados.size(); i++) {
                dtEstado est = (dtEstado) nomEstados.get(i);
                idEstado.put(est.getNombre(), est.getNumero());
                estadosString.add(est.getNombre());
            }
            contCarga.setearEstado(nomEstados);
            util.setearidEstado(nomEstados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public int getIdEstado(String estadoNom) {
        int id = 0;
        id = idEstado.get(estadoNom);
        return id;

    }

    public boolean moverImagenesProp() {

        try {
            for (int i = 0; i < listaImagenes.size(); i++) {
                String inicio = null;
                String destino = null;
                String imagen = listaImagenes.get(i);
                inicio = imagenInicio + "/" + imagen;
                destino = imagenDestino + "/" + imagen;
                System.out.println(destino);
                util.copiarArchivo(inicio, destino);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

        return true;
    }

    public void sacarRutaImagen(dtPropuestasBD prop) {
        System.out.println("imagen: " + prop.getImagen());
        String imagen = prop.getImagen();
        if (imagen != null) {
            if (!imagen.equals("null")) {
                System.err.println(imagen);
                listaImagenes.add(imagen);
            }

        }

    }

    private int getNumEstado(String estado) {
        return this.idEstado.get(estado);

    }

    public static ContPropuesta getInstance() {
        if (instance == null) {
            instance = new ContPropuesta();
        }
        return instance;
    }

    @Override
    public void cargarPropuestas() {
        cargaCategorias();
        cargaEstados();
        cargaPropuestas();
        moverImagenesProp();

    }

    @Override
    public List<dtPropuesta> listaPropuestas(String idProponente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public dtInfoProp infoPropuesta(String idPropuesta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nuevaPropuesta(String idProponente, String tipoEspectaculo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //revisado jp---cambio atributos que se pasan al constructor/testear!
    public void datosPropuesta(dtPropuesta dtp) {
        try {
            estado esta = (estado) estados.get(dtp.getEstado());
            categoria cat = (categoria) getCategorias().get(dtp.getCategoria());
            propuesta p = new propuesta(dtp.getTitulo(), dtp.getDescripcion(), dtp.getImagen(), dtp.getLugar(), dtp.getFechaRealizacion(), dtp.getFechapublicada(), dtp.getPrecioentrada(), dtp.getMontorequerido(), dtp.getRetorno(), esta, cat);
            cUsuario.linkearpropuesta(p, dtp.getProponente());
            dtPropuestaEstado dtPropE = p.getDtPropEstadoActual();
            estPropPer.agregarPropEstado(dtPropE.getTitulo(), dtPropE.getEstado(), dtPropE.getFecha(), dtPropE.getHora(), dtPropE.getFechaFin());
            dtPropuestasBD dtpbd = new dtPropuestasBD(dtp.getTitulo(), dtp.getProponente(), dtp.getDescripcion(), dtp.getImagen(), dtp.getLugar(), dtp.getCategoria(), dtp.getRetorno(), dtp.getFechaRealizacion(), dtp.getFechapublicada(), dtp.getPrecioentrada(), dtp.getMontorequerido());
            propPer.altaPropuesta(dtpbd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void altaPropuesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dtPropuesta> listarPropuesta() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public dtPropuesta infoProp(String idPropuesta) {

        proponente p = (proponente) cUsuario.getUsuarioRecordado();
        dtPropuesta dtp = p.getPropuestas(idPropuesta);
        dtp.addColaboradores((ArrayList<String>) cUsuario.listarColaboradores(idPropuesta));
        dtp.setMontoactual((int) cUsuario.getMontoColaborado(idPropuesta));
        return dtp;
    }

    @Override
    public void modificarPropuesta(dtPropuesta dtProp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dtPropuestaComp> informacionPropuestas(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dtPropuestasProponente> listarPropuestasExistentes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public dtPropuesta mostrarInfoPropuesta(String idPropuesta) throws Exception {
        return ContUsuario.getInstance().infoPropuesta(idPropuesta);
    }

    @Override
    public List<String> listartodasPropuestas(String titulo) {
        return cUsuario.listartodaslaspropuestas(titulo);
    }
    // public ArrayList<dtCategoria> getdtCategorias(){}

    public void cargaCategorias() {
        try {
            ArrayList<dtCategoria> dtcate = new ArrayList<>();
            contCarga.levantarBDCategoriasPer();
            catPer.cargarCat(dtcate);
            contCarga.setearCategoria(dtcate);
            System.out.println(dtcate.size());
            for (int i = 0; i < dtcate.size(); i++) {
                System.out.print(i);
                dtCategoria dtcat = (dtCategoria) dtcate.get(i);
                categoria cat = new categoria(dtcat.getNombre());

                //+++++++++++++++++++
                getCategorias().put(cat.getNombre(), cat);
            }
            //dtcate.lastIndexOf(null);
            System.out.println(dtcate.size());
            for (int p = 0; p < dtcate.size(); p++) {
                dtCategoria dtcatp = (dtCategoria) dtcate.get(p);

                if (dtcatp.getPadre() != null) {
                    categoria catp = (categoria) getCategorias().get(dtcatp.getPadre());
                    categoria cath = (categoria) getCategorias().get(dtcatp.getNombre());
                    cath.setPadre(catp);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }//revisado

    public void cargaEstados() {
        try {
            ArrayList<dtEstado> nomEstados = new ArrayList<>();
            estPer.CargarEstados(nomEstados);
            for (int i = 0; i < nomEstados.size(); i++) {
                dtEstado est = nomEstados.get(i);
                String nombre = est.getNombre();
                estado nuevoEstado = new estado(nombre);
                estados.put(nuevoEstado.getNombre(), nuevoEstado);

            }
            cargaridEstado(nomEstados);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void cargaPropuestas() {
        contCarga.levantarBDproPer();
        contCarga.levantarBDestadosPropPer();
        ArrayList<dtPropuestasBD> dtpropuestasDb = new ArrayList<dtPropuestasBD>();
        propPer.cargarPropuestas(dtpropuestasDb);
        contCarga.agregardtpropuesta(dtpropuestasDb);
        ArrayList<dtEstadosPropuestas> estProp = new ArrayList<>();
        estPer.CargarEstadosPropuestas(estProp);
        llenaEstadosCarga(estProp);

        for (int i = 0; i < dtpropuestasDb.size(); i++) {
            dtPropuestasBD dtProp = (dtPropuestasBD) dtpropuestasDb.get(i);
            sacarRutaImagen(dtProp);

            propuesta prop = armarPropuesta(dtProp);
            cargarEstadosProp(prop, estProp);//revisar if ==true
            //filtraPropuestas(prop);
            String nick = dtProp.getNickproponente();
            cUsuario.esteUsuariopropusoestaProp(nick, prop);

        }
    }

    private void llenaEstadosCarga(ArrayList<dtEstadosPropuestas> estProp) {

        try {
            for (int i = 0; i < estProp.size(); i++) {
                dtEstadosPropuestas esta = estProp.get(i);
                contCarga.setearEstadoPropuesta(esta);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param prop
     * @param estProp
     */
    @Override
    public void cargarEstadosProp(propuesta prop, ArrayList<dtEstadosPropuestas> estProp) {
        try {
            int orden = 0;
            String nombre = null;
            for (int p = 0; p < estProp.size(); p++) {
                dtEstadosPropuestas dtEtPop = (dtEstadosPropuestas) estProp.get(p);
                nombre = dtEtPop.getEstado();

                if (prop.getTitulo().equals(dtEtPop.getTituloprop()) == true) {
                    propEstado propest = crearEstado(dtEtPop);

                    orden = getNumEstado(nombre);
                    prop.setEstado(propest, orden);

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public propEstado crearEstado(dtEstadosPropuestas dtestProp) {
        propEstado estaprop = null;
        try {
            estado est = getEstado(dtestProp.getEstado());
            estaprop = new propEstado(dtestProp.getFecha(), dtestProp.getHora(), est, dtestProp.getFechaFin());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return estaprop;
    }

    public propuesta armarPropuesta(dtPropuestasBD dtProp) {
        //propuesta prop = new propuesta(dtProp.getTitulo(), dtProp.getDescripcion() dtProp.getImagen(), dtProp.getLugar(), dtProp.getFecha(),dtProp.getFecha_publicacion(), dtProp.getPrecio_entrada(), dtProp.getMonto_necesario(), dtProp.getRetorno(),getCategoria(dtProp.getCategoria())); 
        categoria cat = getCategoria(dtProp.getCategoria());
        propuesta prop = new propuesta(dtProp.getTitulo(), dtProp.getDescripcion(), dtProp.getImagen(), dtProp.getLugar(), dtProp.getFecha(), dtProp.getFecha_publicacion(), dtProp.getPrecio_entrada(), dtProp.getMonto_necesario(), dtProp.getRetorno(), cat);
        return prop;
    }

    public estado getEstado(String estaNombre) {
        estado est = (estado) estados.get(estaNombre);
        return est;
    }

    @Override
    public categoria getCategoria(String cateNombre) {
        categoria cat = (categoria) categorias.get(cateNombre);
        return cat;
    }

    // //////// trunkate y carga bd
    @Override
    public void borrartodocPropuesta() {
        HashMap<String, String> lista = (HashMap<String, String>) propPer.cargarPropuestasNOBorrar();
        cUsuario.borrarPropuestas(lista);
    }

    public List<String> listarCategorias() {
        List<String> retorno = new ArrayList<>();
        for (String key : getCategorias().keySet()) {

            retorno.add(key);

        }
        return retorno;
    }

    @Override
    public void agregarEstadoAPropuesta(String e, String titulo, dtFecha dtf, dtHora dth) {
        cUsuario.agregarEstadoAPropuesta(getEstado(e), titulo, dtf, dth, getIdEstado(e));
    }

    @Override
    public List<String> listarEstados() {
        List<String> retorno = new ArrayList<>();
        for (String key : this.idEstado.keySet()) {
            retorno.add(key);
        }
        return retorno;
    }

    @Override
    public void actualizardatospropuesta(dtPropuesta dtp, String e, dtFecha dtf, dtHora dth) throws Exception {
        cUsuario.actualizardatospropuesta(dtp, getEstado(e), getIdEstado(e), dtf, dth);
    }

    public List<String> listarCategoriasBeta() {
        List<String> retorno = new ArrayList<>();
        for (String key : this.categorias.keySet()) {
            categoria c = this.categorias.get(key);
            String categoriacompleta = c.getNombre() + "/" + c.getPadre().getNombre();
            retorno.add(categoriacompleta);

        }
        return retorno;
    }

    void borrarColecciones() {
        this.listaImagenes.clear();
        this.categorias.clear();
        this.estados.clear();
        this.idEstado.clear();
        this.propCambioEstadoAuto.clear();
        this.propFiltradaING.clear();
        this.arregloDtEstProp.clear();
        this.propCambioEstadoAuto.clear();
        this.propuestas.clear();
    }

    /**
     * @return the categorias
     */
    public ArrayList<dtCategoria> getdtCategorias() {
        ArrayList<dtCategoria> cat = new ArrayList<>();

        try {
            Iterator it = categorias.keySet().iterator();
            while (it.hasNext()) {
                String stkey = (String) it.next();
                categoria cate = (categoria) categorias.get(stkey);
                String nombre = null;
                nombre = cate.getNombre();
                String padre = null;
                if (cate.getPadre() != null) {
                    padre = cate.getPadre().getNombre();
                }

                dtCategoria dtcat = new dtCategoria(nombre, padre);
                cat.add(dtcat);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return cat;

    }

    /**
     * @return the categorias
     */
    public Map<String, categoria> getCategorias() {
        return this.categorias;
    }

    @Override
    public void altaCategoria(dtCategoria cate) {
        try {
            if (cate.getPadre() != null) {
                if (cate.getPadre().equals("Categorias") != true) {
                    categoria catP = (categoria) categorias.get(cate.getPadre());
                    categoria categ = new categoria(catP, cate.getNombre());
                    categorias.put(cate.getNombre(), categ);
                    catPer.altaCategoria(cate.getNombre(), cate.getPadre());
                } else {
                    categoria categ = new categoria(null, cate.getNombre());
                    categorias.put(cate.getNombre(), categ);
                    catPer.altaCategoria(cate.getNombre(), null);
                }

            } else {
                if (cate.getPadre().equals("Categorias") != true) {
                    categoria catP = (categoria) categorias.get(cate.getPadre());
                    categoria categ = new categoria(catP, cate.getNombre());
                    categorias.put(cate.getNombre(), categ);
                    catPer.altaCategoria(cate.getNombre(), cate.getPadre());
                } else {
                    categoria categ = new categoria(null, cate.getNombre());
                    categorias.put(cate.getNombre(), categ);
                    catPer.altaCategoria(cate.getNombre(), null);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    /**
     *
     * retorna un arreglo de dtPropuestasBD con las propuestas con estado
     * ingresado solamente
     */
    public ArrayList<dtPropuestasBD> getdtPropIngr() {
        ArrayList<dtPropuestasBD> propu = new ArrayList<>();
        ArrayList<proponente> proponentes = (ArrayList<proponente>) cUsuario.getProponentes();
        for (int i = 0; i < proponentes.size(); i++) {
            proponente pro = (proponente) proponentes.get(i);
            if (pro.noPropuestas() != true) {
                cargaPropEstIngDt(propu, pro);
            }
        }

        return propu;
    }

    /**
     *
     * carga un arreglo de dtPropuestaBD con las propuestas con estado ingresado
     * del proponente by Jp
     */
    private void cargaPropEstIngDt(ArrayList<dtPropuestasBD> propu, proponente prop) {
        ArrayList<propuesta> propING = (ArrayList<propuesta>) prop.getPropuestasObj();
        for (int i = 0; i < propING.size(); i++) {
            propuesta pro = (propuesta) propING.get(i);
            if (pro.getEstadoActual().equals("Ingresada")) {
                propFiltradaING.add(pro);
                dtPropuestasBD dtprop = new dtPropuestasBD(pro.getTitulo(), prop.getNickname());
                propu.add(dtprop);
                //dtPropuestaComp dtprop=new dtPropuestaComp(titulo, descripcion, imagen, lugar, fechaPublicada, estado, categoria, fechaRealizacion, i, i, i)
            }

        }

    }

    /**
     *
     * setea el estado a la propuesta, recibe dos string: el titulo y el estado
     * by jp
     */
    public boolean nuevoEstadoProp(String idProp, String estado) {
        try {
            for (int i = 0; i < propFiltradaING.size(); i++) {
                propuesta p = (propuesta) propFiltradaING.get(i);
                System.out.println(p.getTitulo());
                System.out.println(idProp);
                if (p.getTitulo().equals(idProp)) {
                    estado est = (estado) retornaEstado(estado);
                    dtFecha fecha = (dtFecha) util.getFecha();
                    dtHora hora = (dtHora) util.getHora();
                    //  dtFecha fechaFin = getFechaFin(fecha, 30);
                    propEstado pE = new propEstado(fecha, hora, est, null);
                    p.setEstado(pE, getIdEstado(estado));
                    estPropPer.agregarPropEstado(idProp, estado, fecha.getFecha(), hora.getHora(), (String) pE.getFechaFin().getFecha());
                    return true;
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     *
     * retorna el estado que le pases by jp
     */
    private estado retornaEstado(String estado) {

        estado get = estados.get(estado);
        return get;
    }

    public void agregarestadoapropWEB(String estado, String titulo) {
        dtFecha dtf = util.getFecha();
        dtHora dth = util.getHora();

        agregarEstadoAPropuesta(estado, titulo, dtf, dth); //estado titulo fecha hora
    }

////////////////////PROCEDIMIENTO estado Automatico-----------------
    public void propAutomaticas() {
        try {
            System.out.println("Propuestas automaticas inicio...");
            this.propCambioEstadoAuto.clear();
            cUsuario.getPropuestas(propCambioEstadoAuto);
            for (int i = 0; i < propCambioEstadoAuto.size(); i++) {
                propuesta p = (propuesta) propCambioEstadoAuto.get(i);
                System.out.println("Propuesta a verificar: " + p.getTitulo());
                filtraPropuestas(p);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     *
     * Funcion que recibe una propuesta y verifica el estado, la fecha y la hora
     * Si la fecha es anterior a la actual actualiza de inmediato el estado al
     * que le sigue Si la fecha es igual a la actual verifica la hora, si la
     * hora es inferior o igual a la actual cambia el estado al que le sigue Si
     * la hora es superior a la actual almacena la propuesta en una coleccion
     * que se verifica durante el uso del programa y se encarga comprobar la
     * hora hasta que coincida y cambia el estado
     */
    public void filtraPropuestas(propuesta p) {

        if (comparaEstado(p.getEstadoActual())) {
            if (verificaFecha(p) == 0) {
                if (verificaHora(p) == -1) {

                    System.err.println("Propuesta coincide!!");
                    System.out.println("Hora anterior a la del sistema");
                    System.out.println("Fecha: " + p.getFechaFinEstadoActual());
                    System.out.println("Hora: " + p.getHoraFinEstadoActual());
                    cambiaPropEstadoAuto(p);
                }
                if (verificaHora(p) == 0) {
                    System.err.println("Propuesta coincide!!");
                    System.out.println("Hora igual a la del sistema");
                    System.out.println("Fecha: " + p.getFechaFinEstadoActual());
                    System.out.println("Hora: " + p.getHoraFinEstadoActual());
                    cambiaPropEstadoAuto(p);
                }
            }
            if (verificaFecha(p) == -1) {
                System.err.println("Propuesta coincide!!");
                System.out.println("Fecha anterior a la del sistema");
                System.out.println("Fecha: " + p.getFechaFinEstadoActual());
                System.out.println("Hora: " + p.getHoraFinEstadoActual());
                cambiaPropEstadoAuto(p);
            }
        }
    }

    /*
 *cambiaPropEstadoAuto recibe una propuesta y verifica si el estado es Publicada o En Financiacion
 * si es publicada cambia el estado a En Financiacion 
 * Si es En Financiacion verifica los montos y selecciona Financiada o No Financiada
 * 
     */
    private void cambiaPropEstadoAuto(propuesta p) {
        int m;
        String estado = p.getEstadoActual();
        if (estado.equals("Publicada")) {
            nuevoEstPropAUTO(p, "En financiacion");
        }
        if (estado.equals("En financiacion")) {
            int montoReq = p.getMontoRequerido();
            m = cUsuario.getMontoColaborado(p.getTitulo());
            if (m >= montoReq) {
                nuevoEstPropAUTO(p, "Financiada");
            }
            if (m < montoReq) {
                nuevoEstPropAUTO(p, "No financiada");
            }
        }
    }

    /**
     *
     *
     */
    private void nuevoEstPropAUTO(propuesta p, String estado) {
        try {
            estado est = (estado) retornaEstado(estado);
            dtFecha fecha = (dtFecha) util.getFecha();
            dtHora hora = (dtHora) util.getHora();
            propEstado pE = new propEstado(fecha, hora, est, null);
            int posicion = getNumEstado(estado);
            p.setEstado(pE, posicion);
            estPropPer.agregarPropEstado(p.getTitulo(), estado, fecha.getFecha(), hora.getHora(), p.getFechaFinEstadoActual());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     *
     * verificarFecha: funcion que retorna un int (-1, 0, 1)
     *
     * (1) si la fecha es anterior a la del sistema (0) si la fecha es igual a
     * la del sistema (-1) si es posterior a la del sistema
     */
    private int verificaFecha(propuesta p) {

        try {
            Date fechaFin = (Date) util.fechaDate(p.getFechaFinEstadoActual(), p.getHoraFinEstadoActual());
            dtFecha dtf = util.getFecha();
            dtHora dtH = util.getHora();
            Date fechaAc = util.fechaDate((String) dtf.getFecha(), dtH.getHora());
            if (fechaFin.before(fechaAc)) {
                return -1;
            }
            if (fechaAc.before(fechaFin)) {
                return 1;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    /**
     *
     * verificarHora: funcion que retorna un int (-1, 0, 1)
     *
     * (-1) si la hora es anterior a la del sistema (0) si la hora es igual a la
     * del sistema (1) si es posterior a la del sistema
     */
    private int verificaHora(propuesta p) {

        try {
            Date hora = (Date) util.fechaDate(null, (String) p.getPropEstadoActual().getHora().getHora());
            dtHora dth = (dtHora) util.getHora();
            Date horaSis = (Date) util.fechaDate(null, (String) dth.getHora());
            if (horaSis.before(hora)) {
                return 1;
            }
            if (hora.before(horaSis)) {
                return -1;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    /**
     * comparaEstado: funcion que compara los estados
     *
     * false: si el estado es: Ingresada - No financiada - Financiada true: si
     * es otro estado
     */
    private boolean comparaEstado(String stado) {
        if (stado.equals("Cancelada")) {
            return false;
        }
        if (stado.equals("Ingresada")) {
            return false;
        }
        if (stado.equals("No financiada")) {
            return false;
        }
        if (stado.equals("Financiada")) {
            return false;
        }
        return true;
    }
    //////////////////////EXTENDER FINANCIACION A PROPUESTA/////////////////////

    /**
     * Funcion que extiente el estado financiacion de una propuesta 30 dias mas
     * Retorna un mensaje armado
     *
     * @param tituloP
     * @return
     */
    public String extenderFinanciacionProp(String tituloP) {
        String respuesta = null;
        ArrayList<propuesta> propExt = new ArrayList<>();
        cUsuario.getPropuestas(propExt);
        for (int i = 0; i < propExt.size(); i++) {
            propuesta p = (propuesta) propExt.get(i);
            if (p.getTitulo().equals(tituloP)) {
                System.err.println("prop encontrada");
                if (p.enFinanciacion()) {
                    System.err.println("prop en financiacion");
                    propEstado pE = (propEstado) p.getPropEstadoActual();
                    dtFecha dtf = pE.getFechaFin();
                    String fecha = (String) util.getFechaInc(dtf.getFecha(), null, 30);
                    pE.setFechaFin((dtFecha) util.construirFecha(fecha));
                    estPropPer.updateFinanciacion(fecha, tituloP, p.getEstadoActual());
                    respuesta = "Financiacion de Propuesta: " + tituloP + " extendida con exito!";
                    System.out.println("prop extendida");
                } else {
                    respuesta = "Propuesta: " + tituloP + " no esta en estado de financiacion!";
                }
            }
        }
        return respuesta;
    }

//////////////////jtree categorias modelo para el jtree
    private void cargadtCategorias() {
        try {
            cat.clear();
            cat = (ArrayList< dtCategoria>) getdtCategorias();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private DefaultMutableTreeNode crearNodo(String nomNodo) {
        System.out.println(nomNodo);
        DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(nomNodo);
        return nuevo;
    }

    private void armarNodos(Map<String, DefaultMutableTreeNode> nodos) {
        try {

            for (int i = 0; i < cat.size(); i++) {

                dtCategoria ca = (dtCategoria) cat.get(i);
                System.out.println(ca.getNombre());
                nodos.put(ca.getNombre(), crearNodo(ca.getNombre()));

            }

        } catch (Exception e) {

            System.err.println(e.getMessage());
        }

    }

    private DefaultTreeModel armarPadres(Map<String, DefaultMutableTreeNode> nodos) {
        DefaultMutableTreeNode raiz = crearNodo("Categorias");
        DefaultTreeModel modelo = new DefaultTreeModel(raiz);
        try {

            for (int i = 0; i < cat.size(); i++) {
                dtCategoria categoriaNodo = cat.get(i);
                String padre = categoriaNodo.getPadre();
                if (padre == null) {
                    DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) nodos.get(categoriaNodo.getNombre());
                    modelo.insertNodeInto(nodo, raiz, 0);
                }
            }

        } catch (Exception e) {

            System.err.println(e.getMessage());
        }
        return modelo;
    }

    private void armarHijos(Map<String, DefaultMutableTreeNode> nodos, DefaultTreeModel modelo) {
        try {

            for (int i = 0; i < cat.size(); i++) {
                dtCategoria catNodo = cat.get(i);
                if (catNodo.getPadre() != null) {
                    DefaultMutableTreeNode padre = nodos.get(catNodo.getPadre());
                    DefaultMutableTreeNode hijo = nodos.get(catNodo.getNombre());
                    modelo.insertNodeInto(hijo, padre, 0);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public DefaultTreeModel modeloJT3Categorias() {
        DefaultTreeModel modelo = null;
        try {
            cargadtCategorias();
            Map<String, DefaultMutableTreeNode> nodos = new HashMap<>();
            armarNodos(nodos);
            modelo = armarPadres(nodos);
            armarHijos(nodos, modelo);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return modelo;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////funciones carga tablas CU: consulta de Prop x Estado    
    public ArrayList<String> getEstadosString() {
        return this.estadosString;

    }

    public ArrayList<dtPropuesta> getPropuestasxEstado(String stado) {
        ArrayList<dtPropuesta> prop = new ArrayList<>();
        ArrayList<propuesta> propArray = new ArrayList<>();
        cUsuario.getPropuestas(propArray);
        for (int i = 0; i < propArray.size(); i++) {
            propuesta p = (propuesta) propArray.get(i);
            String pEs = p.getEstadoActual();
            if (pEs.equals(stado)) {
                prop.add((dtPropuesta) p.getDtPropuesta());
            }

        }

        return prop;
    }

    public ArrayList<String> getColaboradoresString(String prop) {
        ArrayList<String> listaCola = new ArrayList<>();
        ArrayList<dtColaborador> dtCola = (ArrayList<dtColaborador>) cUsuario.getUsuariosColaboradores(prop);
        Iterator it = dtCola.iterator();
        while (it.hasNext()) {
            dtColaborador dtc = (dtColaborador) it.next();
            listaCola.add((String) dtc.getNickname());
        }
        return listaCola;
    }

    @Override
    /**
     * funcion que recibe un String on el nick del proponente y retorna todas
     * sus propuestas su viene null o vacio lista todas las propuestas
     */
    public List<String> listartodaslaspropuestas(String nombre) {
        return (List<String>) cUsuario.listartodaslaspropuestas(nombre);
    }

    @Override
    public List<String> listarmispropsfavs(String nickusuario) {
        return (List<String>) cUsuario.listarmispropsfavs(nickusuario);
    }

    @Override
    public void agregarpropuestacomofav(String nickusuario, String titulo) {
        cUsuario.agregarpropuestacomofav(nickusuario, titulo);
    }

    @Override
    public ArrayList<DtPropuestaWeb> listarpropuestasenlaweb() {
        ArrayList<DtPropuestaWeb> listarpropuestasenlaweb = (ArrayList<DtPropuestaWeb>) cUsuario.listarpropuestasenlaweb();
        return listarpropuestasenlaweb;
    }

    @Override
    public List<String> listarpropuestasparacancelar(String nickp) {
        return (List<String>) cUsuario.listarpropuestasparacancelar(nickp);
    }

    @Override
    public ArrayList<DtPropuestaWeb> listarpropuestasencategoriaWeb(String cat) {
        ArrayList<DtPropuestaWeb> arregloPropW = new ArrayList<>();
        try {
            ArrayList<dtPropuesta> propDt = (ArrayList<dtPropuesta>) cUsuario.listarpropuestasencategoria(cat);
            for (int i = 0; i < propDt.size(); i++) {
                arregloPropW.add((DtPropuestaWeb) invertirADtPropWeb((dtPropuesta) propDt.get(i)));
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return arregloPropW;
    }

    private DtPropuestaWeb invertirADtPropWeb(dtPropuesta prop) {
        DtPropuestaWeb nueva = new DtPropuestaWeb();
        nueva.setTitulo(prop.getTitulo());
        nueva.setRetorno(prop.getRetorno());
        nueva.setCategoria(prop.getCategoria());
        nueva.setDescripcion(prop.getDescripcion());
        nueva.setEstado(prop.getEstado());
        nueva.setFechaRealizacion(prop.getFechaRealizacion().getFecha());
        nueva.setFechapublicada(prop.getFechapublicada().getFecha());
        nueva.setImagen(prop.getImagen());
        nueva.setLugar(prop.getLugar());
        nueva.setMontoactual(prop.getMontoTotal());
        nueva.setMontorequerido(prop.getMontorequerido());
        nueva.setPrecioentrada(prop.getPrecioentrada());
        nueva.setProponente(prop.getProponente());

        return nueva;
    }

    @Override
    public List<String> listarpropuestasmenosingresadas(String titulo) {
        return (List<String>) cUsuario.listarpropuestasmenosingresadas(titulo);
    }

    @Override
    public List<String> listarpropuestascolaboradaspor(String string) {
        return (List<String>) cUsuario.listarpropuestascolaboradaspor(string);
    }

    @Override
    public boolean agregarcomentarioapropuesta(String string, String titulo, String comentario) {
        return cUsuario.agregarcomentarioapropuesta(string, titulo, comentario);
    }

    @Override
    public List<String> mispropuestasaceptadas(String nick) {
        return (List<String>) cUsuario.mispropuestasaceptadas(nick);

    }

    @Override
    public List<String> mispropuestasaingresadas(String nick) {
        return (List<String>) cUsuario.mispropuestasaingresadas(nick);
    }

    @Override
    public DtFechaWeb getFechaWeb() {
        DtFechaWeb nueva = new DtFechaWeb();
        try {
            dtFecha fe = util.getFecha();
            nueva.setDia(fe.getDia());
            nueva.setMes(fe.getMes());
            nueva.setAnio(fe.getAnio());
            nueva.setFecha(fe.getFecha());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return nueva;
    }

    @Override
    public DtHoraWeb getHoraWeb() {
        DtHoraWeb nueva = new DtHoraWeb();
        try {
            dtHora dtH = (dtHora) util.getHora();
            int min = dtH.getMinutos();
            int horas = dtH.getHoras();
            nueva.setHora(horas);
            nueva.setMinuto(min);
            nueva.setHoraC(dtH.getHora());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return nueva;
    }

    @Override
    public void altaPropWeb(DtPropuestaWeb prop) {
        try {
            dtFecha fRea = util.construirFecha((String) prop.getFechaRealizacion());
            dtFecha fPubli = util.construirFecha((String) prop.getFechapublicada());
            dtPropuesta nuevaP = new dtPropuesta(prop.getTitulo(), prop.getDescripcion(), prop.getImagen(), prop.getLugar(), prop.getEstado(), prop.getCategoria(), prop.getProponente(), fRea, fPubli, prop.getPrecioentrada(), prop.getMontorequerido(), prop.getMontoactual(), prop.getRetorno());
            datosPropuesta(nuevaP);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public DtFechaWeb crearFecha(String fecha) {
        dtFecha aux = util.construirFecha(fecha);
        DtFechaWeb retorno = new DtFechaWeb();
        retorno.setDia(aux.getDia());
        retorno.setMes(aux.getMes());
        retorno.setAnio(aux.getAnio());
        retorno.setFecha(aux.getFecha());
        return retorno;
    }

    /**
     * @return the imagenDestino
     */
    public String getImagenDestino() {
        return imagenDestino;
    }

    /**
     * @param imagenDestino the imagenDestino to set
     */
    public void setImagenDestino(String imagenDestino) {
        this.imagenDestino = imagenDestino;
    }

    /**
     * @return the imagenInicio
     */
    public String getImagenInicio() {
        return imagenInicio;
    }

    /**
     * @param imagenInicio the imagenInicio to set
     */
    public void setImagenInicio(String imagenInicio) {
        this.imagenInicio = imagenInicio;
    }

}
