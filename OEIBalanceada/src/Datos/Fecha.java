package Datos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Fecha {
    Calendar fecha;
    
    public String crarFecha() {
        Random aleatorio = new Random();
        fecha = Calendar.getInstance();
        fecha.set (aleatorio.nextInt(10)+2014, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        return sdf.format(fecha.getTime());
    }
}
