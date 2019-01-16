/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.List;

/**
 *
 * @author nicolasgutierrez
 */
public interface iConColaboracion {

    public abstract void cargarColaboracion();
    
    public abstract void cargarPagos();

    public abstract dtColProp seleccionarColaboracion(String nickusuario, String titulo);

    public abstract void eliminar() throws Exception;

    public abstract void borrartodocColaboraciones();

    public abstract List<dtCola> listarcolaboracionesdelcolaborador(String nickcolaborador);

    public abstract void eliminarcolaboracion(String nickname, String titulo) throws Exception;

    public abstract boolean registrarColaboracion(DtColaboracionCompWeb cola);

    public abstract boolean registrarPagoWeb(String usuario, String tituloP, DtPagoWeb pago);

    public abstract List<String> listarColaboraciones(String nick);
    
    public abstract DtarregloDtColCompWeb listarmiscolaboracionesWeb(String nick);
    
    public abstract String armarretorno(String cbe, String cbp);
}
