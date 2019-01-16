/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

/**
 *
 * @author Esteban Menendez
 */
public class colaborador extends usuario{

protected Map<String,colProp> colaboracionesUsuario = new HashMap<String,colProp>();//diccionario con clave string
    

public boolean sosColaborador(){
    return true;
    }
    
public dtColaborador getColaboradores(){

    return null;
}

public dtColaborador getColaborador(){
    dtColaborador dt=new dtColaborador(this.nombre,this.apellido,this.nickname,this.imagen,this.email,this.nacimiento,this.password);
    return dt;
}


public String getRetornos(){
String s = "retorno";
return s;
}


  //  @Override
    public colProp seleccioonarColaboracion(String titulo){
        return this.colaboracionesUsuario.get(titulo);
       
    }
    
    @Override
    public void eliminarColaboracion(colProp c){
        this.colaboracionesUsuario.remove(c.getPropColaborada().getTitulo());
    }

   

    public colaborador(String nickname, String nombre, String apellido, String email, String imagen, dtFecha nacimiento,String pass) {
        super(nickname, nombre, apellido, email, imagen, nacimiento,pass);
    }
 
    public boolean colaborasconpropuesta(String tituloprop){
        return this.colaboracionesUsuario.containsKey(tituloprop);
    }
    
    public int getmontocolaboracion(String tituloprop){
        colProp cp=(colProp)this.colaboracionesUsuario.get(tituloprop);
        if(cp!=null)
            return cp.getMontocolaborado();
        else
            return 0;
    }
    
    public void agregarcolaboracion(colProp cp){
        String titulo= cp.getPropColaborada().getTitulo();
        this.colaboracionesUsuario.put(titulo, cp);
    }
    
    public void eliminarcolaboraciones(){
        for(String key: this.colaboracionesUsuario.keySet()){
            colProp cp=this.colaboracionesUsuario.get(key);
            cp.eliminate();
        }
        
        this.colaboracionesUsuario.clear();
    }
    
    public boolean notenescolaboraciones(){
        return this.colaboracionesUsuario.isEmpty();
    }

    public void eliminalasrestantes(Map<String, String> lista){
    
        for(String key: this.colaboracionesUsuario.keySet()){
            if(lista.containsKey(key)==false){
                colProp cp=this.colaboracionesUsuario.remove(key);
                cp.setPropuesta(null);
                cp.setFecha(null);
                cp.setHora(null);
            
            }
        }
    }

    public ArrayList<String> listarmiscolaboraciones() {
        ArrayList<String> retorno= new ArrayList<>();
        for(String key: this.colaboracionesUsuario.keySet()){
            retorno.add(key);
        }
        return retorno;
    }

}
