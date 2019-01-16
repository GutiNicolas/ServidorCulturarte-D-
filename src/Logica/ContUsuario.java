/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.cancelarcolaboracionPersistencia;
import Persistencia.colaboracionesPersistencia;
import Persistencia.estadoPropuestaPersistencia;
import Persistencia.propuestasPersistencia;
import Persistencia.seguirdejardeseguirPersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Persistencia.usuariosPersistencia;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author nicolasgutierrez
 */
public class ContUsuario implements iConUsuario {

    private ArrayList<String> listaImagenes = new ArrayList<>();
    usuariosPersistencia usuPer = new usuariosPersistencia();
    private Map<String, usuario> usuarios = new HashMap<>();
    seguirdejardeseguirPersistencia segdej = new seguirdejardeseguirPersistencia();
    colaboracionesPersistencia colabPer = new colaboracionesPersistencia();
    estadoPropuestaPersistencia estadopropper = new estadoPropuestaPersistencia();
    propuestasPersistencia propPersis = new propuestasPersistencia();
    utilidades util = utilidades.getInstance();
    ArrayList<dtFavoritos> favo = new ArrayList<>();

    private ContCargaBD contCarga = ContCargaBD.getInstance();
    private String imagenDestino = contCarga.getImgusudes();
    private String imagenInicio = contCarga.getImgusuini();
    private static ContUsuario instance;
    private usuario usuariorecordado;
    private seguirdejardeseguirPersistencia seguirdejardeseguir = new seguirdejardeseguirPersistencia();
    private cancelarcolaboracionPersistencia cancelarcolab = new cancelarcolaboracionPersistencia();

    public static ContUsuario getInstance() {
        if (instance == null) {
            instance = new ContUsuario();
        }
        return instance;
    }

    /**
     *
     * @param fecha
     * @return
     */
    @Override
    public dtFecha creadtFecha(String fecha) {
        return (dtFecha) util.construirFecha(fecha);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public boolean emailValido(String email) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);

        return mather.find();
    }

    public boolean existeUsuario(String nickName) {

        if (usuarios.containsKey(nickName) == true) {
            usuario u = (usuario) usuarios.get(nickName);
            if (u instanceof proponente) {
                proponente p = (proponente) u;
                if (!p.isDesactivado()) {
                    return true;
                }

            } else {
                return true;
            }

        }

        return false;

    }

    public boolean existeMail(String mail) {
        try {
            Iterator it = usuarios.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                usuario u = null;
                u = usuarios.get(key);
                if (u.getEmail().equals(mail)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean moverImagenesUsu() {

        for (int i = 0; i < listaImagenes.size(); i++) {
            try {
                String inicio = null;
                String destino = null;
                String imagen = listaImagenes.get(i);
                inicio = imagenInicio + "/" + imagen;
                destino = imagenDestino + "/" + imagen;
                System.out.println(destino);
                util.copiarArchivo(inicio, destino);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public void cargarUsuarios() {

        try {
            System.out.println("Cargar usuarios inicio...");
            contCarga.levantaBDusuPer();
            ArrayList<dtUsuario> dtUsuarios = new ArrayList<dtUsuario>();
            dtUsuarios = usuPer.cargaUsuarios();
            int tam = 0;
            for (int i = 0; i < dtUsuarios.size(); i++) {
                dtUsuario usu = (dtUsuario) dtUsuarios.get(i);
                System.out.println("Usuario: " + usu.getNickname());
                sacarRutaImagen(usu);
                agregaUsuCD(usu);
                contCarga.agregardtusu(usu);
                usu = null;
                tam++;

            }
            System.out.println("Carge: " + tam + " usuarios!");
            System.out.println("Termine...");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        moverImagenesUsu();
        cargarSeguidores();

    }

    public void cargarFavoritos() {
        System.out.println("Cargar P Favoritas inicio");
        try {
            int cont = 0;
            contCarga.levantarBDfavoritosPer();
            usuPer.levantarFavoritos(favo);
            for (int i = 0; i < favo.size(); i++) {
                dtFavoritos f = (dtFavoritos) favo.get(i);
                String usu = null, prop = null;
                usu = f.getUsuario();
                prop = f.getPropuestaTitulo();
                usuario u = new usuario();
                propuesta p = new propuesta();
                System.out.println("Usuario: " + usu);
                u = (usuario) usuarios.get(usu);
                System.out.println("Propuesta: " + prop);
                p = (propuesta) damePropuesta(prop);
                u.setFavorita(p);
                contCarga.setearFavoritos(f);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Termine...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void sacarRutaImagen(dtUsuario usu) {
        if (usu.getImagen() != null) {
            if (usu.getImagen() != null && usu.getImagen().equals("null") != true) {
                if (usu.getImagen().equals("") != true) {
                    String imagen = usu.getImagen();
                    listaImagenes.add(imagen);
                }
            }
        }

    }
//revisar

    public void cargarSeguidores() {
        ArrayList<dtSeguidores> siguen = new ArrayList<>();
        System.out.println("Cargar seguidores inicio...");
        contCarga.levantarBDseguidoresPer();
        int cont = 0;
        try {
            usuPer.seguidores(siguen);
            for (int i = 0; i < siguen.size(); i++) {
                dtSeguidores seg = null;
                seg = (dtSeguidores) siguen.get(i);
                seguirCD(seg);
                contCarga.seteardtSeguidores(seg);
                cont++;
            }
            System.out.println("Carge: " + cont);
            System.out.println("Finalize...");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void seguirCD(dtSeguidores dtseg) {
        usuario ussig = null;
        usuario usaseg = null;
        try {
            ussig = (usuario) usuarios.get(dtseg.getNickusuario());
            usaseg = (usuario) usuarios.get(dtseg.getNickaseguir());
            ussig.seguir(usaseg);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void agregaUsuCD(dtUsuario dtusu) throws Exception {
        if (dtusu instanceof dtProponente) {
            dtProponente dtProp = (dtProponente) dtusu;
            proponente usuProp = new proponente((dtProp.getNickname()), dtProp.getNombre(), dtProp.getApellido(), dtProp.getEmail(), dtProp.getImagen(),
                    dtProp.getFechaNac(), dtProp.getDireccion(), dtProp.getBiografia(), dtProp.getSitioWeb(), dtProp.getPass(), dtProp.isDesactivado());
            usuarios.put(usuProp.getNickname(), usuProp);

        } else {
        }

        if (dtusu instanceof dtColaborador) {
            dtColaborador colaborador = (dtColaborador) dtusu;
            colaborador usuCola = new colaborador(colaborador.getNickname(), colaborador.getNombre(), colaborador.getApellido(), colaborador.getEmail(), colaborador.getImagen(), colaborador.getFechaNac(), colaborador.getPass());
            usuarios.put(usuCola.getNickname(), usuCola);

        }
    }

    /**
     *
     * @param dtusu
     * @throws Exception
     */
    @Override
    public void agregarUsu(dtUsuario dtusu) throws Exception {
        try {

            if (dtusu instanceof dtProponente) {
                dtProponente dtProp = (dtProponente) dtusu;

                proponente usuProp = new proponente((dtProp.getNickname()), dtProp.getNombre(), dtProp.getApellido(), dtProp.getEmail(), dtProp.getImagen(),
                        dtProp.getFechaNac(), dtProp.getDireccion(), dtProp.getBiografia(), dtProp.getSitioWeb(), dtProp.getPass());
                usuarios.put(usuProp.getNickname(), usuProp);
                usuPer.altaUsuario(dtusu);
            } else {
            }

            if (dtusu instanceof dtColaborador) {
                dtColaborador colaborador = (dtColaborador) dtusu;
                colaborador usuCola = new colaborador(colaborador.getNickname(), colaborador.getNombre(), colaborador.getApellido(), colaborador.getEmail(), colaborador.getImagen(), colaborador.getFechaNac(), colaborador.getPass());
                usuarios.put(usuCola.getNickname(), usuCola);
                usuPer.altaUsuario(dtusu);
            }

        } catch (Exception ex) {

            throw new Exception("Error: " + ex);

        }
    }

    /**
     *
     * @param nick
     * @return
     */
    @Override
    public List<String> listarProponentes(String nick) {
        List<String> retornar = new ArrayList<String>();
        if (!usuarioDesactivado(nick)) {
            if (nick.isEmpty()) {
                for (String key : this.usuarios.keySet()) {
                    if (this.usuarios.get(key) instanceof proponente) {
                        if (!usuarioDesactivado(key)) {
                            retornar.add(key);
                        }
                    }
                }
            } else {
                for (String key : this.usuarios.keySet()) {
                    if (this.usuarios.get(key) instanceof proponente && key.contains(nick)) {
                        retornar.add(key);
                    }
                }
            }
        }
        return retornar;
    }

    @Override
    public dtProponente infoProponente(String idProponente) {

        proponente p = (proponente) usuarios.get(idProponente);
        dtProponente res = new dtProponente(p.getNombre(), p.getApellido(), p.getNickname(), p.getImagen(), p.getEmail(), p.getNacimiento(), p.getDireccion(), p.getBiografia(), p.getWeb(), p.getPassword());
        this.usuariorecordado = p;
        return res;
    }

    @Override
    public List<String> listarColaboradores() {
        List<String> colabs = new ArrayList();
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                if (c != null) {
                    colabs.add(c.nickname);
                }
            }
        }
        return colabs;

    }

    @Override
    public dtUsuario infoColaborador(String idColaborador) {
        colaborador c = (colaborador) this.usuarios.get(idColaborador);
        return c.getColaborador();
    }

    @Override
    public List<dtPropuesta> listarPropuestas(String idProponente) {
        List<dtPropuesta> retornar = new ArrayList<>();
        if (!usuarioDesactivado(idProponente)) {
            proponente p = (proponente) this.usuarios.get(idProponente);
            retornar = (List<dtPropuesta>) p.getTodasPropuestas();
            if (retornar.isEmpty()) {
                Iterator it = retornar.iterator();
                while (it.hasNext()) {
                    dtPropuesta dtp = (dtPropuesta) it.next();
                    dtp.setMontoactual(montopropuesta(dtp.getTitulo()));
                }

            }
        }

        return retornar;
    }

    @Override
    public List<String> listarColaboradores(String idPropuesta) {
        List<String> res = new ArrayList<>();
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                if (c.colaborasconpropuesta(idPropuesta)) {
                    res.add(c.getNickname());
                }

            }

        }
        return res;
    }

    @Override
    public dtColaboraciones seleccionarColaborador(String idColaborador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seguir(String nicknameSeguidor, String nicknameASeguir) throws Exception {
        usuario us = this.usuarios.get(nicknameSeguidor);
        if (us != null) {
            usuario as = this.usuarios.get(nicknameASeguir);
            if (as == null) //throw el usuario a seguir no existe
            {
                throw new Exception("El usuario " + nicknameASeguir + " que desea seguir no existe");
            }
            if (!us.loSigue(nicknameASeguir)) {
                dtSeguidores dts = new dtSeguidores(nicknameSeguidor, nicknameASeguir);
                boolean res = this.seguirdejardeseguir.seguir(dts);
                if (res) {
                    us.seguir(as);
                }
            } else {
                //throw ya lo sigue
                throw new Exception("Ya sigue a este usuario");
            }
        } else {
            //throw no existe el usuario
            throw new Exception("El usuario " + nicknameSeguidor + " no existe");

        }

    }

    @Override
    public void dejarDeSeguir(String nicknameSeguidor, String nicknameADejarDeSeguir) throws Exception {
        usuario us = this.usuarios.get(nicknameSeguidor);
        if (us != null) {
            usuario adds = this.usuarios.get(nicknameADejarDeSeguir);
            if (adds == null) {
                throw new Exception("El usuario " + nicknameADejarDeSeguir + " que desea dejar de seguir no existe");
            }
            if (!us.loSigue(nicknameADejarDeSeguir)) {
                throw new Exception("El usuario " + nicknameADejarDeSeguir + " no se encuentra entre tus usuarios seguidos");
            } else {
                dtSeguidores dts = new dtSeguidores(nicknameSeguidor, nicknameADejarDeSeguir);
                boolean res = this.seguirdejardeseguir.dejardeseguir(dts);
                if (res) {
                    us.dejardeSeguir(adds);
                }
            }
        } else {
            throw new Exception("El usuario " + nicknameSeguidor + " no existe");
        }
    }

    @Override
    public void eliminarColaboracion(colProp col) throws Exception {
        boolean res = this.cancelarcolab.cancelarColaboracion(this.usuariorecordado.getNickname(), col);
        if (res) {
            this.usuariorecordado.eliminarColaboracion(col);
        } else {
            throw new Exception("Imposible eliminar la colaboracion");
        }
    }

    /**
     *
     * @param nickusuario
     * @param titulo
     * @return
     */
    @Override
    public colProp seleccionarColaboracion(String nickusuario, String titulo) {
        propuesta p = damePropuesta(titulo);
        if (!usuarioDesactivado(p.getDtPropuesta().getProponente())) {
            colaborador c = (colaborador) this.usuarios.get(nickusuario);
            this.usuariorecordado = c;
            return c.seleccioonarColaboracion(titulo);
        }
        return null;
    }

    public usuario getUsuarioRecordado() {
        return this.usuariorecordado;
    }

    @Override
    public List<String> listarProponentes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> listartodaslaspropuestas(String titulo) {
        List<String> ret = new ArrayList();

        if (titulo.isEmpty()) {
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(key);
                    if (!p.isDesactivado()) {
                        ret.addAll(p.listarmispropuestas());
                    }

                }
            }
        } else {
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(key);
                    if (!p.isDesactivado()) {
                        ret.addAll(p.listarmispropuestaslike(titulo));
                    }
                }
            }
        }

        return ret;
    }

    /**
     *
     * @param titulo
     * @return
     * @throws Exception
     */
    @Override
    public dtPropuesta infoPropuesta(String titulo) throws Exception {
        dtPropuesta dtp = null;
        System.err.println(usuarios.size());
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(key);
                if (p.propuestasUsuario.containsKey(titulo)) {
                    dtp = p.getPropuestas(titulo);
                    dtp.setMontoactual(this.getMontoColaborado(titulo));
                    dtp.addColaboradores((List<String>) listarColaboradores(titulo));
                }

            }
        }

        return dtp;
    }

    public int montopropuesta(String idPropuesta) {
        int res = 0;
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                if (c.colaborasconpropuesta(idPropuesta)) {
                    res = res + c.getmontocolaboracion(idPropuesta);
                }
            }
        }
        return res;
    }

    @Override
    public List<String> listarusuarios(String nick) {
        List<String> lst = new ArrayList<String>();
        if (nick.isEmpty()) {
            for (String key : this.usuarios.keySet()) {
                if (!usuarioDesactivado(key)) {
                    if (key.contains(nick)) {
                        lst.add(key);
                    }
                }

            }
        } else {
            for (String key : this.usuarios.keySet()) {
                if (!usuarioDesactivado(key)) {
                    lst.add(key);
                }
            }
        }
        return lst;
    }

    public void esteUsuariopropusoestaProp(String nickproponente, propuesta p) {
        try {
            proponente prop = (proponente) usuarios.get(nickproponente);
            prop.agregarPropuesta(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean compararfechas(dtFecha uno, dtFecha dos) {
        int unoanio = Integer.parseInt(uno.getAnio());
        int unomes = Integer.parseInt(uno.getMes());
        int unodia = Integer.parseInt(uno.getDia());
        int dosanio = Integer.parseInt(dos.getAnio());
        int dosmes = Integer.parseInt(dos.getMes());
        int dosdia = Integer.parseInt(dos.getDia());

        if (unoanio > dosanio) {
            return true;
        }
        if (unoanio == dosanio && unomes > dosmes) {
            return true;
        }
        if (unoanio == dosanio && unomes == dosmes && unodia > dosdia) {
            return true;
        }
        if (unoanio == dosanio && unomes == dosmes && unodia == dosdia) { //CASO DUDOSO
            return true;
        } else {
            return false;
        }

    }

    public boolean compararhoras(dtHora uno, dtHora dos) {
        if (uno.getHoras() > dos.getHoras()) {
            return true;
        }
        if (uno.getHoras() == dos.getHoras() && uno.getMinutos() > dos.getMinutos()) {
            return true;
        }
        if (uno.getHoras() == dos.getHoras() && uno.getMinutos() == dos.getMinutos()) {
            return true;
        } else {
            return false;
        }

    }

    public void agregarEstadoAPropuesta(estado e, String titulo, dtFecha dtf, dtHora dth, int orden) {
        propuesta p = this.damePropuesta(titulo);
        if (p.getTitulo().equals(titulo)) {
            boolean agrego = p.agregarNuevoEstado(e, dtf, dth, orden);
            if (agrego) {
                String fechaFin = (String) util.getFechaInc(dtf.getFecha(), dth.getHora(), 30);
                estadopropper.agregarPropEstado(titulo, e.getNombre(), dtf.getFecha(), dth.getHora(), fechaFin);
            }
        }
    }

    public propuesta damePropuesta(String titulo) {

        for (String key : this.usuarios.keySet()) {
            usuario usu = (usuario) usuarios.get(key);
            if (usu instanceof proponente) {
                proponente p = (proponente) usu;
                if (p.tenesPropuesta(titulo) == true) {
                    return p.damelapropuesta(titulo);

                }
            }
        }
        return null;
    }

    public void registrarcolaboracion(String nickc, String titulo, colProp cp) {
        propuesta prop = damePropuesta(titulo);
        cp.setPropuesta(prop);
        usuario usu = usuarios.get(nickc);
        if (usu instanceof colaborador) {
            colaborador c = (colaborador) usu;
            c.agregarcolaboracion(cp);
        }
    }

    @Override
    public void borrartodocUsuario() {

        //VACIAR LOS SEGUIDOS POR LOS USUARIOS QUE SERAN ELIMINADOS
        for (String key : this.usuarios.keySet()) {

            usuario u;
            u = this.usuarios.get(key);
            u.eliminartodoslosseguidos();
            u.eliminarFavoritos();
        }

        //SACA EL PUNTERO AL USUARIO RECORDADO SI ESTE DEBE SER ELIMINADO
    }

    public void borrarColaboraciones() {

        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                c.eliminarcolaboraciones();
            }

        }

    }

    public void borrarPropuestas(Map<String, String> pnoborrar) {

        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(key);
                p.borratuspropuestas();

            }
        }

    }

    @Override
    public List<String> listarColaboradoresporNick(String nick) {
        List res = new ArrayList<String>();
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                if (c.getNickname().contains(nick)) {
                    res.add(c.getNickname());
                }
            }
        }
        return res;
    }

    public List<dtCola> colaboracionesde(String nickcolaborador) {
        List<dtCola> list = new ArrayList<>();
        colaborador c = (colaborador) this.usuarios.get(nickcolaborador);
        for (String key : c.colaboracionesUsuario.keySet()) {
            colProp cp = c.colaboracionesUsuario.get(key);
            if (!propuestaBloqueada((String) cp.getPropColaborada().getTitulo())) {
                dtCola dtco = new dtCola(cp.getPropColaborada().getTitulo(), cp.getPropColaborada().getEstadoActual(), quienpropuso(cp.getPropColaborada().getTitulo()), montopropuesta(cp.getPropColaborada().getTitulo()));
                list.add(dtco);
            }

        }
        return list;
    }

    public String quienpropuso(String titulo) {
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(key);
                if (p.propuestasUsuario.containsKey(titulo)) {
                    return p.getNickname();
                }
            }
        }
        return "";
    }

    public void linkearpropuesta(propuesta p, String prop) {
        for (String key : this.usuarios.keySet()) {
            if (key.contentEquals(prop)) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente pr = (proponente) this.usuarios.get(key);
                    pr.propuestasUsuario.put(p.getTitulo(), p);
                }
            }
        }
    }

    @Override
    public List<String> listarColaboradoresLike(String nick) {

        List<String> colabs = new ArrayList();
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(key);
                if (c != null && c.getNickname().contains(nick)) {
                    colabs.add(c.nickname);
                }
            }
        }
        return colabs;

    }

    public boolean registrarColaboracion(String titulo, String colab, int monto, String retorno) {
        propuesta p = this.damePropuesta(titulo);
        if (this.usuarios.get(colab) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(colab);
            if (c != null) {
                if (p != null && c.colaborasconpropuesta(titulo) == false) {
                    dtFecha dtf = (dtFecha) util.getFecha();
                    dtHora dth = (dtHora) util.getHora();

                    colProp cp = new colProp(dtf, dth, monto, retorno, p, null);
                    c.colaboracionesUsuario.put(p.getTitulo(), cp);
                    boolean h = colabPer.registrarColaboracion(colab, titulo, dtf.getFecha(), dth.getHora(), Integer.toString(monto), retorno);

                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void actualizardatospropuesta(dtPropuesta dtp, estado e, int orden, dtFecha dtf, dtHora dth) throws Exception {

        propuesta p = this.damePropuesta(dtp.getTitulo());
        if (p.getTitulo().equals(dtp.getTitulo())) {
            p.setDescripcion(dtp.getDescripcion());
            p.setImagen(dtp.getImagen());
            p.setLugar(dtp.getLugar());
            boolean estadoagregado = p.agregarNuevoEstado(e, dtf, dth, orden);
            p.setFechapublicada(dtp.getFechapublicada());
            p.setFecharealizacion(dtp.getFechaRealizacion());
            p.setPrecioEntrada(dtp.getPrecioentrada());
            p.setMontoRequerido(dtp.getMontorequerido());
            dtPropuestasBD dtpbd = new dtPropuestasBD(dtp.getTitulo(), dtp.getProponente(), dtp.getDescripcion(), dtp.getImagen(), dtp.getLugar(), dtp.getCategoria(), p.getRetorno(), dtp.getFechaRealizacion(), dtp.getFechapublicada(), dtp.getPrecioentrada(), dtp.getMontorequerido());
            propPersis.actualizarPropuesta(dtpbd);
            if (estadoagregado == true) {
                estadopropper.agregarPropEstado(p.getTitulo(), e.getNombre(), dtf.getFecha(), dth.getHora(), p.getFechaFinEstadoActual());
            }

        } else {
            throw new Exception("La propuesta " + dtp.getTitulo() + " que desea modificar no existe");
        }
    }

    /*  List<String> listarPropuestasPorEstado(String estado) {
        List<String> retorno = new ArrayList<>();

        for (String ku : this.usuarios.keySet()) {
            if (this.usuarios.get(ku) instanceof proponente) {
                proponente prop = (proponente) this.usuarios.get(ku);
                for (String kp : prop.propuestasUsuario.keySet()) {
                    propuesta p = prop.propuestasUsuario.get(kp);
                    if (p.getEstadoActual().contains(estado)) {
                        retorno.add(kp);
                    }
                }

            }
        }
        return retorno;
    }
     */
    void eliminarcolaboracion(String nickname, String titulo) throws Exception {
        colaborador c = this.damecolaborador(nickname);
        if (c.getNickname().contentEquals(nickname)) {
            colProp cp = c.colaboracionesUsuario.remove(titulo);
            cp.eliminate();
            colabPer.eliminarColaboracion(nickname, titulo);
        } else {
            throw new Exception("El colaborador " + nickname + " no existe");
        }
    }

    public colaborador damecolaborador(String nick) {
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof colaborador && key.contentEquals(nick)) {
                colaborador c = (colaborador) this.usuarios.get(key);
                return c;
            }
        }

        return null;
    }

    void borrarColecciones() {
        this.listaImagenes.clear();
        this.usuariorecordado = null;
        this.usuarios.clear();
    }

    @Override
    public ArrayList<proponente> getProponentes() {

        ArrayList<proponente> propo = new ArrayList<>();

        try {
            Iterator it = usuarios.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                if ((usuario) usuarios.get(key) instanceof proponente) {
                    proponente prop = (proponente) (usuario) usuarios.get(key);
                    if (!usuarioDesactivado(prop.getNickname())) {
                        propo.add(prop);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return propo;
    }

    public dtUsuario usuarioLogin(String usu) {
        dtUsuario retorno = null;

        if (usu.contains("@") == false) { //Busqueda por Nick
            if (this.usuarios.containsKey(usu)) {
                if (this.usuarios.get(usu) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(usu);
                    if (!usuarioDesactivado(p.getNickname())) {
                        retorno = p.getDtProponente();
                        retorno.setRol("Proponente");
                    }
                } else {
                    colaborador c = (colaborador) this.usuarios.get(usu);
                    retorno = c.getColaborador();
                    retorno.setRol("Colaborador");
                }
            }
        }
        if (usu.contains("@") == true) { //Busqueda por Correo
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(usu);
                    if (!usuarioDesactivado(p.getNickname())) {
                        if (p.getEmail().equals(usu)) {
                            retorno = p.getDtProponente();
                        }
                    }
                } else {
                    colaborador c = (colaborador) this.usuarios.get(usu);
                    if (c.getEmail().equals(usu)) {
                        retorno = c.getColaborador();
                    }
                }
            }
        }
        return retorno;

    }

    /**
     * Esta funcion se usa para tener informacion sin importar si es Colaborador
     * o Proponente es usada en la pagina WEB
     *
     * @param nick
     * @return
     */
    public DtUsuarioWeb infoUsuarioGeneral(String nick) {
        DtUsuarioWeb dtu = new DtUsuarioWeb();
        if (!usuarioDesactivado(nick)) {
            if (this.usuarios.get(nick) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(nick);
                dtu = (DtUsuarioWeb) recombinar(p.getDtProponente());
            }
        } else {
            return null;
        }

        if (this.usuarios.get(nick) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(nick);
            dtu = (DtUsuarioWeb) recombinar(c.getColaborador());
        }
        return dtu;
    }

    private DtUsuarioWeb recombinar(dtUsuario usu) {
        DtUsuarioWeb retorno = new DtUsuarioWeb();
        if (usu instanceof dtProponente) {
            dtProponente uP = (dtProponente) usu;
            retorno.setNombre(uP.getNombre());
            retorno.setApellido(uP.getApellido());
            retorno.setNickname(uP.getNickname());
            retorno.setEmail(uP.getEmail());
            retorno.setImagen(uP.getImagen());
            retorno.setFechaNac((String) uP.getFechaNac().getFecha());
            retorno.setRol("Proponente");
            retorno.setPass(uP.getPass());
            retorno.setBio(uP.getBiografia());
            retorno.setPagWeb(uP.getSitioWeb());
            retorno.setDireccion(uP.getDireccion());

        }
        if (usu instanceof dtColaborador) {
            dtColaborador cola = (dtColaborador) usu;
            retorno.setNombre(cola.getNombre());
            retorno.setApellido(cola.getApellido());
            retorno.setNickname(cola.getNickname());
            retorno.setEmail(cola.getEmail());
            retorno.setImagen(cola.getImagen());
            retorno.setFechaNac((String) cola.getFechaNac().getFecha());
            retorno.setRol("Colaborador");
            retorno.setPass(cola.getPass());
        }
        return retorno;
    }

    /**
     * Esta funcion es usada por la WEB para listar los usuarios que siguen a el
     * usuario nick
     *
     * @param nick
     * @return
     */
    public List<DtUsuarioWeb> listarmisseguidores(String nick) {
        ArrayList<DtUsuarioWeb> retorno = new ArrayList<>();
        for (String key : this.usuarios.keySet()) {
            usuario u = this.usuarios.get(key);

            if (u.seguidos.containsKey(nick)) {
                if (!usuarioDesactivado(u.getNickname())) {
                    if (infoUsuarioGeneral(u.getNickname()) != null) {
                        DtUsuarioWeb dtu = (DtUsuarioWeb) infoUsuarioGeneral(u.getNickname());
                        retorno.add(dtu);
                    }
                }
            }
        }

        return retorno;
    }

    /**
     * Esta funcion es usada por la WEB para listar los seguidores de un usuario
     *
     * @param nick
     * @return
     */
    public ArrayList<DtSigoAWeb> listarmisseguidos(String nick) {
        ArrayList<DtSigoAWeb> retorno = new ArrayList<>();

        usuario u = this.usuarios.get(nick);
        for (String key : u.seguidos.keySet()) {

            if (u.seguidos.get(key) instanceof proponente) {
                proponente aux = (proponente) this.usuarios.get(key);
                if (!aux.isDesactivado()) {
                    DtSigoAWeb nuevo = new DtSigoAWeb();
                    nuevo.setNickusuario(key);
                    nuevo.setNombrecompleto(aux.getNombre() + " " + aux.getApellido());
                    nuevo.setRol("Proponente");
                    retorno.add(nuevo);
                }

            }

            if (u.seguidos.get(key) instanceof colaborador) {
                colaborador aux = (colaborador) this.usuarios.get(key);
                DtSigoAWeb nuevo = new DtSigoAWeb();
                nuevo.setNickusuario(key);
                nuevo.setNombrecompleto(aux.getNombre() + " " + aux.getApellido());
                nuevo.setRol("Colaborador");
                retorno.add(nuevo);
            }

        }
        return (ArrayList<DtSigoAWeb>) retorno;
    }

    public List<String> mispropuestasfavoritas(String nick) {
        ArrayList<String> retorno = new ArrayList<>();
        usuario u = this.usuarios.get(nick);
        for (String key : u.favoritas.keySet()) {
            propuesta p = damePropuesta(key);
            if (!usuarioDesactivado((String) p.getDtPropuesta().getProponente())) {
                retorno.add(key);
            }
        }
        return retorno;
    }

    /**
     * funcion utilizada por la WEB para listar las propuestas de un proponente
     * ecepto las ingresadas
     *
     * @param nick
     * @return
     */
    public List<String> mispropuestasaceptadas(String nick) {
        ArrayList<String> retorno = new ArrayList<>();
        if (this.usuarios.get(nick) instanceof proponente) {
            proponente p = (proponente) this.usuarios.get(nick);
            for (String key : p.propuestasUsuario.keySet()) {
                propuesta prop = p.propuestasUsuario.get(key);
                if (prop.getEstadoActual().equals("Ingresada") == false) {
                    retorno.add(prop.getTitulo());
                }
            }
        }
        return retorno;
    }

    public List<String> listarColaboraciones(String nick) {
        ArrayList<String> retorno = new ArrayList<>();
        if (this.usuarios.get(nick) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(nick);
            retorno = (ArrayList<String>) filtraColaboraciones((List<String>) c.listarmiscolaboraciones());
        }
        return retorno;
    }

    private List<String> filtraColaboraciones(List<String> lista) {
        List<String> li = new ArrayList<>();
        try {
            for (int i = 0; i < lista.size(); i++) {
                propuesta p = damePropuesta((String) lista.get(i));
                if (!usuarioDesactivado((String) p.getDtPropuesta().getProponente())) {
                    li.add(lista.get(i));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }
        return li;
    }

    public List<String> mispropuestasaingresadas(String nick) {
        ArrayList<String> retorno = new ArrayList<>();
        if (this.usuarios.get(nick) instanceof proponente) {
            proponente p = (proponente) this.usuarios.get(nick);
            for (String key : p.propuestasUsuario.keySet()) {
                propuesta prop = p.propuestasUsuario.get(key);
                if (prop.getEstadoActual().equals("Ingresada")) {
                    retorno.add(prop.getTitulo());
                }
            }
        }
        return retorno;
    }

    public List<dtColProp> listarmiscolaboraciones(String nick) {
        ArrayList<dtColProp> retorno = new ArrayList<>();
        if (this.usuarios.get(nick) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(nick);
            for (String key : c.colaboracionesUsuario.keySet()) {
                colProp cp = c.colaboracionesUsuario.get(key);
                if (!propuestaBloqueada(cp.getPropColaborada().getTitulo())) {
                    dtColProp dtcp = new dtColProp(nick, cp.getFecha(), cp.getHora(), cp.getMontocolaborado(), key);
                    retorno.add(dtcp);
                }
            }
        }
        return retorno;
    }

    public List<String> listarpropuestasmenosingresadas(String titulo) {
        List<String> ret = new ArrayList();
        if (titulo.isEmpty()) {
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(key);
                    if (!p.isDesactivado()) {
                        ret.addAll(p.listarmispropuestasmenosingresadas());
                    }

                }
            }
        } else {
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof proponente) {
                    proponente p = (proponente) this.usuarios.get(key);
                    if (!p.isDesactivado()) {
                        ret.addAll(p.listarmispropuestaslike(titulo));
                    }
                }
            }
        }

        return ret;

    }

    /**
     * Esta funcion se usa para listar propuestas en el la WEB
     *
     * @return
     */
    public ArrayList<DtPropuestaWeb> listarpropuestasenlaweb() {
        ArrayList<DtPropuestaWeb> retorno = new ArrayList<>();
        for (String key : this.usuarios.keySet()) {
            if (this.usuarios.get(key) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(key);
                if (!p.isDesactivado()) {
                    for (String keyp : p.propuestasUsuario.keySet()) {
                        DtPropuestaWeb dtp = new DtPropuestaWeb();
                        dtp.setProponente(key);
                        dtp.setTitulo(keyp);
                        retorno.add(dtp);
                    }
                }
            }
        }
        return retorno;
    }

    public List<String> cargarlosseguidospor(String nickusuario) {
        List<String> retorno = new ArrayList<>();
        usuario u = this.usuarios.get(nickusuario);
        for (String key : u.seguidos.keySet()) {
            if (!usuarioDesactivado(key)) {
                retorno.add(key);
            }
        }
        return retorno;
    }

    public List<String> listarpropuestasparacancelar(String nickp) {
        List<String> retorno = new ArrayList<>();
        if (this.usuarios.get(nickp) instanceof proponente) {
            proponente p = (proponente) this.usuarios.get(nickp);
            for (String key : p.propuestasUsuario.keySet()) {
                propuesta prop = p.propuestasUsuario.get(key);
                if (prop.getEstadoActual().equals("Financiada")) {
                    retorno.add(key);
                }
            }
        }
        return retorno;
    }

    public void agregarpropuestacomofav(String nickusuario, String titulo) {
        propuesta p = this.damePropuesta(titulo);
        usuario u = this.usuarios.get(nickusuario);
        u.favoritas.put(titulo, p);
        usuPer.agregarpropcomofav(nickusuario, titulo);
    }

    public List<String> listarmispropsfavs(String nickusuario) {
        List<String> retorno = new ArrayList<>();
        usuario u = this.usuarios.get(nickusuario);
        if (u != null) {

            for (String key : u.favoritas.keySet()) {
                if (!propuestaBloqueada(key)) {
                    retorno.add(key);
                }

            }

        }
        return retorno;
    }

    private boolean propuestaBloqueada(String nomProp) {

        return usuarioDesactivado(getProponenteXProp(nomProp));
    }

    public List<dtPropuesta> listarpropuestasencategoria(String cat) throws Exception {
        List<dtPropuesta> retorno = new ArrayList<>();
        for (String usus : this.usuarios.keySet()) {
            if (this.usuarios.get(usus) instanceof proponente) {
                proponente p = (proponente) this.usuarios.get(usus);
                for (String key : p.propuestasUsuario.keySet()) {
                    if (!propuestaBloqueada(key)) {
                        propuesta prop = p.propuestasUsuario.get(key);
                        if (prop.getCategoria().equals(cat)) {
                            retorno.add(infoPropuesta(key));
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public List<dtColProp> listarcolaboradasfinanciadas(String nickusuario) {
        List<dtColProp> retorno = new ArrayList<>();
        for (String usus : this.usuarios.keySet()) {
            if (this.usuarios.get(usus) instanceof colaborador) {
                colaborador c = (colaborador) this.usuarios.get(usus);
                for (String key : c.colaboracionesUsuario.keySet()) {
                    propuesta p = this.damePropuesta(key);
                    if (!propuestaBloqueada(key)) {
                        if (p.getCategoria().equals("Financiada")) {
                            colProp cp = c.colaboracionesUsuario.get(key);
                            dtColProp dtcp = new dtColProp(usus, cp.getRetorno(), cp.getFecha(), cp.getHora(), cp.getMontocolaborado(), cp.getComentario());
                            dtcp.setTitulo(key);
                            if (dtcp.getComentario().isEmpty()) {
                                retorno.add(dtcp);
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    @Override
    /**
     *
     * Funcion que retorna el monto colaborado de la propuesta
     */
    public int getMontoColaborado(String idProp) {
        int monto = 0;

        try {
            for (String key : this.usuarios.keySet()) {
                System.out.println(key);
                usuario usu = (usuario) usuarios.get(key);

                if (usu instanceof colaborador) {
                    colaborador cola = (colaborador) usu;
                    if (cola.colaborasconpropuesta(idProp)) {
                        monto += cola.getmontocolaboracion(idProp);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return monto;
    }

    @Override
    public void getPropuestas(ArrayList<propuesta> prop) {
        try {
            Iterator it = usuarios.keySet().iterator();
            while (it.hasNext()) {
                usuario usu = usuarios.get((String) it.next());
                if (usu instanceof proponente) {
                    proponente p = (proponente) usu;
                    if (!p.isDesactivado()) {
                        prop.addAll((ArrayList<propuesta>) p.getPropuestasObj());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String armarretorno(String cbe, String cbp) {
        String re = null;
        if (cbe != null) {
            if (cbp == null || cbp.isEmpty()) {
                re = cbe;
            } else if (cbe != null && cbp != null) {
                if (!cbe.isEmpty() && !cbp.isEmpty()) {
                    re = cbe + "/" + cbp;
                }
            }
        } else if (cbp != null) {
            if (cbe == null || !cbe.isEmpty()) {
                re = cbp;
            }
        }
        return re;

    }

    public ArrayList<DtUsuarioWeb> listarusuariosweb(String nick) {
        ArrayList<DtUsuarioWeb> lst = new ArrayList<>();
        if (!nick.isEmpty()) {
            for (String key : this.usuarios.keySet()) {
                if (!usuarioDesactivado(key)) {
                    if (key.contains(nick)) {
                        lst.add((DtUsuarioWeb) this.infoUsuarioGeneral(key));
                    }
                }

            }
        } else {
            for (String key : this.usuarios.keySet()) {
                if (key.contains(nick)) {
                    DtUsuarioWeb usuWWW = (DtUsuarioWeb) this.infoUsuarioGeneral(key);
                    if (usuWWW != null) {
                        lst.add(usuWWW);
                    }

                }
            }
        }
        return lst;
    }

    public List<String> listarpropuestascolaboradaspor(String string) {
        List<String> retorno = new ArrayList<>();
        if (this.usuarios.get(string) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(string);
            for (String key : c.colaboracionesUsuario.keySet()) {
                propuesta p = this.damePropuesta(key);
                if (p.getEstadoActual().equals("Financiada")) {
                    colProp cp = c.colaboracionesUsuario.get(key);
                    if (cp.getComentario().isEmpty() || cp.getComentario().equals("null") || cp.getComentario() == null) {
                        retorno.add(key);
                    }
                }
            }
        }
        return retorno;
    }

    public boolean agregarcomentarioapropuesta(String string, String titulo, String comentario) {
        if (this.usuarios.get(string) instanceof colaborador) {
            colaborador c = (colaborador) this.usuarios.get(string);
            if (c.colaboracionesUsuario.containsKey(titulo)) {
                colProp cp = c.colaboracionesUsuario.get(titulo);
                propuesta p = cp.getPropColaborada();
                if (p.getEstadoActual().equals("Financiada")) {
                    if (cp.getComentario() == null) {
                        cp.setComentario(comentario);
                        colabPer.agregarcomentarioaprop(string, titulo, comentario);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public dtUsuario usuarioLoginApp(String usu) {
        dtUsuario retorno = null;
        if (usu.contains("@") == false) { //Busqueda por Nick
            if (this.usuarios.containsKey(usu)) {
                if (this.usuarios.get(usu) instanceof colaborador) {
                    colaborador c = (colaborador) this.usuarios.get(usu);
                    retorno = c.getColaborador();
                    retorno.setRol("Colaborador");
                }
            }
        }
        if (usu.contains("@") == true) { //Busqueda por Correo
            for (String key : this.usuarios.keySet()) {
                if (this.usuarios.get(key) instanceof colaborador) {
                    colaborador c = (colaborador) this.usuarios.get(usu);
                    if (c.getEmail().equals(usu)) {
                        retorno = c.getColaborador();
                        retorno.setRol("Colaborador");
                    }
                }
            }
        }
        return retorno;
    }

    /**
     * Funcion que devuelve el ranking calculado de usuarios con mayor puntaje
     *
     * @return List dtUsuario
     */
    public ArrayList<DtUsuarioWeb> ranking() {
        ArrayList<DtUsuarioWeb> rank = new ArrayList<>();
        for (String key : this.usuarios.keySet()) {

            if (!usuarioDesactivado(key)) {
                usuario u = this.usuarios.get(key);
                if (contarseguidores(key) > 0) {
                    DtUsuarioWeb dtU = new DtUsuarioWeb();
                    dtU.setNombre(u.getNombre());
                    dtU.setApellido(u.getApellido());
                    dtU.setNickname(u.getNickname());
                    dtU.setPuntaje((Integer) contarseguidores(key));
                    rank.add(dtU);
                }
            }

        }

        DtUsuarioWeb aux = null;
        for (int i = 0; i < rank.size(); i++) {
            for (int j = i + 1; j < rank.size(); j++) {
                if (rank.get(i).getPuntaje() < rank.get(j).getPuntaje()) {
                    aux = rank.get(i);
                    rank.set(i, rank.get(j));
                    rank.set(j, aux);
                }
            }
        }

        return rank;
    }

    public int contarseguidores(String nick) {
        int cant = 0;
        for (String key : this.usuarios.keySet()) {
            if (!usuarioDesactivado(key)) {
                usuario u = this.usuarios.get(key);
                if (u.seguidos.containsKey(nick)) {
                    cant++;
                }
            }

        }
        return cant;
    }

    public ArrayList<dtColaborador> getUsuariosColaboradores(String pp) {
        ArrayList<dtColaborador> colaboradores = new ArrayList<>();
        Iterator it = usuarios.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            usuario u = (usuario) usuarios.get(key);
            if (u instanceof colaborador) {
                colaborador c = (colaborador) u;
                if (c.colaborasconpropuesta(pp)) {
                    colaboradores.add((dtColaborador) c.getColaborador());
                }

            }

        }

        return colaboradores;
    }

    /**
     *
     * @param fecha
     * @return
     */
    @Override
    public DtFechaWeb crearFecha(String fecha) {
        dtFecha aux = creadtFecha(fecha);
        DtFechaWeb retorno = new DtFechaWeb();
        retorno.setDia(aux.getDia());
        retorno.setMes(aux.getMes());
        retorno.setAnio(aux.getAnio());
        retorno.setFecha(aux.getFecha());
        return retorno;
    }

    @Override
    public void agregarUsuWeb(DtUsuarioWeb usu) {
        try {
            dtUsuario dtusu = null;
            if (usu.getRol().equals("Proponente")) {
                dtusu = new dtProponente(usu.getNombre(), usu.getApellido(), usu.getNickname(), usu.getImagen(), usu.getEmail(), (dtFecha) util.construirFecha((String) usu.getFechaNac()), usu.getDireccion(), usu.getBio(), usu.getPagWeb(), usu.getPass());
            }
            if (usu.getRol().equals("Colaborador")) {
                dtusu = new dtColaborador(usu.getNombre(), usu.getApellido(), usu.getNickname(), usu.getImagen(), usu.getEmail(), (dtFecha) util.construirFecha((String) usu.getFechaNac()), usu.getPass());
            }
            agregarUsu(dtusu);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public DtUsuarioWeb usuarioLoginW(String nick) {
        DtUsuarioWeb retorno = new DtUsuarioWeb();
        dtUsuario nuevo = (dtUsuario) this.usuarioLogin(nick);
        if (nuevo instanceof dtProponente) {
            retorno.setNombre(nuevo.getNombre());
            retorno.setNickname(nick);
            retorno.setApellido(nuevo.getApellido());
            retorno.setEmail(nuevo.getEmail());
            retorno.setFechaNac(nuevo.getFechaNac().getFecha());
            retorno.setImagen(nuevo.getImagen());
            retorno.setPass(nuevo.getPass());
            retorno.setBio(((dtProponente) nuevo).getBiografia());
            retorno.setDireccion(((dtProponente) nuevo).getDireccion());
            retorno.setPagWeb(((dtProponente) nuevo).getSitioWeb());
            retorno.setRol(nuevo.getRol());
        }
        if (nuevo instanceof dtColaborador) {
            retorno.setNombre(nuevo.getNombre());
            retorno.setNickname(nick);
            retorno.setApellido(nuevo.getApellido());
            retorno.setEmail(nuevo.getEmail());
            retorno.setFechaNac(nuevo.getFechaNac().getFecha());
            retorno.setImagen(nuevo.getImagen());
            retorno.setPass(nuevo.getPass());
            retorno.setRol(nuevo.getRol());

        }
        return retorno;
    }

    @Override
    public DtPropuestaWeb infoPropuestaWeb(String titulo) {
        DtPropuestaWeb retorno = new DtPropuestaWeb();
        try {
            dtPropuesta aux = infoPropuesta(titulo);
            retorno.setTitulo(titulo);
            retorno.setDescripcion(aux.getDescripcion());
            retorno.setImagen(aux.getImagen());
            retorno.setLugar(aux.getLugar());
            retorno.setEstado(aux.getEstado());
            retorno.setCategoria(aux.getCategoria());
            retorno.setProponente(aux.getProponente());
            retorno.setFechaRealizacion((String) aux.getFechaRealizacion().getFecha());
            retorno.setFechapublicada((String) aux.getFechapublicada().getFecha());
            retorno.setPrecioentrada(aux.getPrecioentrada());
            retorno.setMontorequerido(aux.getMontorequerido());
            retorno.setMontoactual(aux.getMontoTotal());
            retorno.setRetorno(aux.getRetorno());
            retorno.setColaboradores((ArrayList) aux.detColaboradores());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public DtUsuarioWeb usuarioLoginA(String nick) {
        DtUsuarioWeb retorno = new DtUsuarioWeb();
        dtUsuario nuevo = (dtUsuario) this.usuarioLoginApp(nick);
        if (nuevo instanceof dtProponente) {
            retorno.setNombre(nuevo.getNombre());
            retorno.setNickname(nick);
            retorno.setApellido(nuevo.getApellido());
            retorno.setEmail(nuevo.getEmail());
            retorno.setFechaNac(nuevo.getFechaNac().getFecha());
            retorno.setImagen(nuevo.getImagen());
            retorno.setPass(nuevo.getPass());
            retorno.setBio(((dtProponente) nuevo).getBiografia());
            retorno.setDireccion(((dtProponente) nuevo).getDireccion());
            retorno.setPagWeb(((dtProponente) nuevo).getSitioWeb());
            retorno.setRol(nuevo.getRol());
        }
        if (nuevo instanceof dtColaborador) {
            retorno.setNombre(nuevo.getNombre());
            retorno.setNickname(nick);
            retorno.setApellido(nuevo.getApellido());
            retorno.setEmail(nuevo.getEmail());
            retorno.setFechaNac(nuevo.getFechaNac().getFecha());
            retorno.setImagen(nuevo.getImagen());
            retorno.setPass(nuevo.getPass());
            retorno.setRol(nuevo.getRol());

        }
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

    /**
     *
     * @param nick para dar de baja un proponente desde la web
     * @return
     */
    public boolean dardebaja(String nick) {
        boolean retorno = false;
        if (this.usuarios.get(nick) instanceof proponente) {
            proponente p = (proponente) this.usuarios.get(nick);
            p.setDesactivado(true);
            return usuPer.eliminacionLogicaProponente(nick);

        }
        return retorno;
    }

    @Override
    public boolean usuarioDesactivado(String nick) {
        if (usuarios.containsKey(nick)) {
            usuario u = (usuario) usuarios.get(nick);
            if (u instanceof proponente) {
                proponente p = (proponente) u;
                return p.isDesactivado();
            }
        }
        return false;
    }

    public String getProponenteXProp(String titulop) {
        String nickname = null;
        try {
            Iterator it = usuarios.keySet().iterator();
            while (it.hasNext()) {
                usuario u = usuarios.get((String) it.next());
                if (u instanceof proponente) {
                    proponente p = (proponente) u;
                    if (p.tenesPropuesta(titulop)) {
                        nickname = p.getNickname();
                        break;
                    }
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return nickname;
    }

    public boolean colaborasConPropuesta(String titulop, String nick) {
        if (usuarios.containsKey(nick)) {
            usuario u = usuarios.get(nick);
            if (u instanceof colaborador) {
                colaborador c = (colaborador) u;
                return c.colaborasconpropuesta(titulop);
            }
        }

        return false;
    }
}
/*p.getTitulo(), p.getDescripcion(), p.getImagen(), p.getLugar(),
p.getEstadoActual(), p.getCategoria(), this.getNickname(), p.getFecharealizacion(),
p.getFechapublicada(), p.getPrecioEntrada(), p.getMontoRequerido(), 0, p.getRetorno()*/
