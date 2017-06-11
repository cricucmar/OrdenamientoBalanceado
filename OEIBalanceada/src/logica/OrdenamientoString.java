package logica;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class OrdenamientoString extends IntercalacionBalanceada {

    private String tope;
    private int tamanio;

    public OrdenamientoString(File f0, int indice) {
        super(f0, indice);    
    }

    @Override
    protected int distribuir() throws IOException {
        int j, nt;
        String anterior = null;
        String clave;
        boolean continuar = true;
        CsvReader flujo = new CsvReader(new FileReader(f0));
        flujo.readHeaders();
        CsvWriter flujoSalida[] = new CsvWriter[N2];
        for (j = 0; j < N2; j++) {
            flujoSalida[j] = new CsvWriter(new FileWriter(f[j]), ',');
        }

        j = 0; 		 // indice del flujo de salida
        nt = 0;
        if (flujo.readRecord()) {
            anterior = flujo.getValues()[indice];
            flujoSalida[j].writeRecord(flujo.getValues());
        }

        // bucle termina con la excepción fin de fichero
        while (flujo.readRecord()) {
            clave = flujo.getValues()[indice];
            while (anterior.compareTo(clave) <= 0) {
                flujoSalida[j].writeRecord(flujo.getValues());
                anterior = clave;
                if (flujo.readRecord()) {
                    clave = flujo.getValues()[indice];
                } else {
                    continuar = false;
                    break;
                }
            }
            nt++;                        // nuevo tramo
            j = (j < N2 - 1) ? j + 1 : 0;	 // siguiente archivo
            if (continuar) {
                flujoSalida[j].writeRecord(flujo.getValues());
            } else {
                break;
            }
            anterior = clave;
        }

        nt++;		 // cuenta ultimo tramo
        System.out.println("\n*** Número de tramos: " + nt + " ***");
        flujo.close();
        for (j = 0; j < N2; j++) {
            flujoSalida[j].close();
        }
        return nt;
    }

    @Override
    protected int minimo(String[][] r, boolean[] activo, int n) {
        int i, indice2;
        i=indice2=0;
        String m = tope;

        for (; i < n; i++) {
            if (activo[i] && r[i][indice].compareTo(m) < 0) {
                m = r[i][indice];
                indice2 = i;
            }
        }
        return indice2;
    }

    @Override
    protected void setTope() {
        CsvReader flujo = null;
        try {
            flujo = new CsvReader(new FileReader(f0));
            flujo.readHeaders();
            flujo.readRecord();
            tamanio = flujo.getValues()[indice].length();
            while (flujo.readRecord()) {
                if (flujo.getValues()[indice].length() > tamanio) {
                    tamanio = flujo.getValues()[indice].length();
                }
            }
            flujo.close();
            tope = StringUtils.repeat("z", tamanio + 1);
        } catch (IOException eof) {
            System.out.println("Archivo sin registros");
        }
    }
    @Override
    protected boolean findeTramo(String[] r, String[] anterior, int indice) {
        return (anterior[indice].compareTo(r[indice]) > 0);
    }
}
