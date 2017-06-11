package logica;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrdenamientoInt extends IntercalacionBalanceada {

    private int tope = 0;

    public OrdenamientoInt(File f0, int indice) {
        super(f0, indice);
    }

    @Override
    protected int distribuir() throws IOException {
        int anterior, j, nt;
        int clave;
        boolean continuar = true;
        CsvReader flujo = new CsvReader(new FileReader(f0));
        flujo.readHeaders();
        CsvWriter flujoSalida[] = new CsvWriter[N2];
        for (j = 0; j < N2; j++) {
            flujoSalida[j] = new CsvWriter(new FileWriter(f[j]), ',');
        }
        j = 0; 		 // indice del flujo de salida
        nt = 0;
        anterior = 0;
        if (flujo.readRecord()) {
            anterior = Integer.parseInt(flujo.getValues()[indice]);
            flujoSalida[j].writeRecord(flujo.getValues());
        }

        // bucle termina con la excepción fin de fichero
        while (flujo.readRecord()) {
            clave = Integer.parseInt(flujo.getValues()[indice]);
            while (anterior <= clave) {
                flujoSalida[j].writeRecord(flujo.getValues());
                System.out.println("tramos " + flujo.getValues()[0]);
                anterior = clave;
                if (flujo.readRecord()) {
                    clave = Integer.parseInt(flujo.getValues()[indice]);
                } else {
                    continuar = false;
                    break;
                }
            }
            nt++;                        // nuevo tramo
            j = (j < N2 - 1) ? j + 1 : 0;	 // siguiente archivo
            if (continuar) {
                flujoSalida[j].writeRecord(flujo.getValues());
                System.out.println("t -> " + flujo.getValues()[0]);
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
        i = indice2 = 0;
        int m = tope + 1;

        for (; i < n; i++) {
            if (activo[i] && Integer.parseInt(r[i][indice]) < m) {
                m = Integer.parseInt(r[i][indice]);
                indice2 = i;
            }
        }
        return indice2;
    }

    @Override
    protected void setTope() {
        CsvReader flujo;
        try {
            flujo = new CsvReader(new FileReader(f0));
            flujo.readHeaders();
            flujo.readRecord();
            tope = Integer.parseInt(flujo.getValues()[indice]);
            while (flujo.readRecord()) {
                if (Integer.parseInt(flujo.getValues()[indice]) > tope) {
                    tope = Integer.parseInt(flujo.getValues()[indice]);
                }
            }
            flujo.close();
        } catch (IOException eof) {
            System.out.println("Archivo sin registros");
        }
    }

    @Override
    protected boolean findeTramo(String[] r, String[] anterior, int indice) {
        return (Integer.parseInt(anterior[indice]) > Integer.parseInt(r[indice]));  // fin de tramo
    }
}
