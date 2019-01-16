/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan Contiene funciones genericas y utiles...
 */
public class utilidades {

    private static utilidades instance;
    private Map<String, Integer> idEstado = new HashMap<String, Integer>();

    public static utilidades getInstance() {
        if (instance == null) {
            instance = new utilidades();
        }
        return instance;
    }

    public void setearidEstado(ArrayList<dtEstado> nomEstados) {
        try {
            for (int i = 0; i < nomEstados.size(); i++) {
                dtEstado est = (dtEstado) nomEstados.get(i);
                idEstado.put(est.getNombre(), est.getNumero());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public int getIdEstado(String estadoNom) {
        int id = 0;
        id = idEstado.get(estadoNom);
        return id;

    }

    /**
     *
     * Mueve archivos de un lugar a otro, si ya existen los sobreescribe Recibe
     * dos String origen y destino
     *
     * @origen cadena con la ruta de origen del archivo
     * @destino cadena con la ruta de destino del archivo
     */
    public boolean copiarArchivo(String origen, String destino) throws IOException {
   
        File imagen = new File(origen);
        
        if (imagen.exists()) {
            try {
                InputStream inp = new FileInputStream(origen);
                int available = inp.available();
                OutputStream out = new FileOutputStream(destino);
                byte[] bufer = new byte[1024];
                int largo;
                while ((largo = inp.read(bufer)) > 0) {
                    out.write(bufer, 0, largo);
                }
                inp.close();
                out.close();
                return true;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     *
     *
     * Funcion que retorna un dtHora con la hora actual
     *
     */
    public dtHora getHora() {
        Date date = new Date();
        DateFormat dt2 = new SimpleDateFormat("hh:mm");
        String h = dt2.format(date);
        String[] hp = h.split(":");
        int hs = Integer.parseInt(hp[0]);
        int ms = Integer.parseInt(hp[1]);
        dtHora dth = new dtHora(hs, ms);
        return dth;
        /* 
     
         */
    }

    /**
     * Funcion que retorna un dtFecha con la fecha actual
     *
     */
    public dtFecha getFecha() {
        Date date = new Date();
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String f = dt.format(date);
        String[] fp = f.split("/");
        String dia = fp[0];
        String mes = fp[1];
        String anio = fp[2];

        dtFecha dtf = new dtFecha(dia, mes, anio);
        return dtf;
    }

    /**
     *
     * funcion que retorna un dtFecha a partir de un string. Formato dd/MM/yyyy
     */
    public dtFecha construirFecha(String fecha) {
        String[] splited = fecha.split("/");
        dtFecha fec = new dtFecha(splited[0], splited[1], splited[2]);
        return fec;
    }

    /**
     *
     * funcion que retorna un dtHora a partir de un string. Formato "HH:mm"
     */
    public dtHora construirHora(String hora) {

        String[] spl = hora.split(":");
        int hs = 0, mn = 0;
        hs = Integer.parseInt(spl[0]);
        mn = Integer.parseInt(spl[1]);
        dtHora hor = new dtHora(hs, mn);
        return hor;
    }

    /**
     *
     * Funcion que recibe un objeto date "fecha" y la contidad de dias a
     * incrementar (dias>0) o restar (dias<0)
     *
     * @param fecha
     * @param dias
     * @return
     */
    public static Date sumaRestaDias(Date fecha, int dias) {
        if (dias == 0) {//si viene 0 se retorna la fecha sin cambios
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();//obtengo la instancia calendar
        calendar.setTime(fecha);//setteo la fecha
        calendar.add(Calendar.DAY_OF_YEAR, dias);// add puede sumar o restar dias 
        return calendar.getTime();
    }

    /**
     *
     * Funcion que recibe un objeto date "fecha" y la contidad de horas a
     * incrementar (horas>0) o restar (horas<0)
     */
    public static Date sumaRestaHoras(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);

        calendar.add(Calendar.HOUR, horas);

        return calendar.getTime(); // Retorno la fecha con las horas

    }

    /**
     * funcion que retorna un String con LA fecha contenida en el Date tiene dos
     * opciones de formato tipo 0 = dd/MM/yyy tipo 1 = dd/MM/yyy HH:mm by Jp
     */
    public String fechaString(Date date, int tipoF) {
        Format formato = null;
        if (tipoF == 0) {
            formato = new SimpleDateFormat("dd/MM/yyyy");
        }
        if (tipoF == 1) {
            formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        }
        return formato.format(date);

    }

    /**
     * funcion para retornar una fecha incrementada by jp
     */
    public String getFechaInc(String fecha, String hora, int dias) {

        Date fechaNormal = fechaDate(fecha, hora);
        Date fechaMod = sumaRestaDias(fechaNormal, dias);
        return fechaString(fechaMod, 0);
    }

    /**
     * Funcion que retorna un objeto date (dd/MM/yyyy HH:mm) y recibe como
     * parametros dos String
     *
     *
     * f String - si es null se setea solo la hora date (HH:mm) h String - si es
     * null se setea solo la fecha date (dd/MM/yyyy)
     *
     * @return:
     * @null si f == null y h == null
     *
     */
    public static Date fechaDate(String f, String h) {

        Date fecha = null;
        try {

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formato3 = new SimpleDateFormat("HH:mm");
            if (f != null) {
                if (h != null) {
                    fecha = formato2.parse(f + " " + h);

                }
                if (h == null) {
                    fecha = formato.parse(f);

                }
            }
            if (f == null) {
                fecha = formato3.parse(h);
            }

        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }
        return fecha;

    }
}
