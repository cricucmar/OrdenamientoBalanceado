package Datos;

import java.io.File;
import com.csvreader.CsvWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class  GenerarArchivo {
    static File f0;
    static final int NumReg = 10;
    static final int Tope = 20;
    static boolean archivoGenerado=false;
    
    public static File generarArchivo() throws IOException{
        f0=new File("archivo.csv");
        CsvWriter datos = new CsvWriter(new FileWriter(f0),',');
        datos.writeRecord("campo1,campo2,campo3,campo4".split(","));
        Random randomico = new Random();
        Fecha fechaAleatoria = new Fecha();
        for (int i = 0; i < NumReg; i++) {
            datos.write(String.valueOf((int)(1+Tope*Math.random())));
            datos.write(RandomStringUtils.random((int)(1+Tope*Math.random()),true,true));
            datos.write(String.valueOf(randomico.nextBoolean()));
            datos.write(fechaAleatoria.crarFecha());
            datos.endRecord();
        }
        datos.close();
        archivoGenerado=true;
        return f0;
    }
    
    public static File getArchivo() {
        return f0;
    }
    
    public static boolean getArchivoGenerado(){
        return archivoGenerado;
    }
}
