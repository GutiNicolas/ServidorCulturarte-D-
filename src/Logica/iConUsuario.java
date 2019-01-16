/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolasgutierrez
 */
public interface iConUsuario {

    public abstract void cargarUsuarios();

    public abstract void agregarUsu(dtUsuario usuario) throws Exception;

    public abstract List<String> listarProponentes();

    public abstract dtUsuario infoProponente(String idProponente);

    public abstract List<String> listarColaboradores();

    public abstract dtUsuario infoColaborador(String idColaborador);

    public abstract List<dtPropuesta> listarPropuestas(String idColaborador);

    public abstract List<String> listarColaboradores(String idPropuesta);

    public abstract dtColaboraciones seleccionarColaborador(String idColaborador);

    public abstract void seguir(String nicknameSeguidor, String nicknameASeguir) throws Exception;

    public abstract void dejarDeSeguir(String nicknameSeguidor, String nicknameADejarDeSeguir) throws Exception;

    public abstract void eliminarColaboracion(colProp col) throws Exception;

    public abstract colProp seleccionarColaboracion(String nickusuario, String titulo); //original devuelve dt

    public abstract List<String> listarusuarios(String nick);

    public abstract void borrartodocUsuario();

    public abstract List<String> listarColaboradoresporNick(String nick);

    public abstract List<String> listarProponentes(String text);

    public abstract List<String> listarColaboradoresLike(String nick);

    public abstract boolean registrarColaboracion(String titulo, String colaborador, int monto, String retorno);

    public abstract void actualizardatospropuesta(dtPropuesta dtp, estado e, int orden, dtFecha dtf, dtHora dth) throws Exception;

    public ArrayList<proponente> getProponentes();

    public dtUsuario usuarioLogin(String usu);

    public abstract int getMontoColaborado(String idProp);

    public abstract void getPropuestas(ArrayList<propuesta> prop);

    public abstract boolean existeMail(String mail);

    public boolean emailValido(String email);

    public dtFecha creadtFecha(String fecha);
    
    public abstract dtPropuesta infoPropuesta(String titulo) throws Exception ;
       
    public abstract DtFechaWeb crearFecha(String fecha);
    
    public abstract void agregarUsuWeb(DtUsuarioWeb usu);
    
    public abstract DtPropuestaWeb infoPropuestaWeb(String titulo);
    
    public abstract DtUsuarioWeb usuarioLoginW(String nick);
    
    public abstract DtUsuarioWeb usuarioLoginA(String nick);
    
    public boolean usuarioDesactivado(String nick);
}