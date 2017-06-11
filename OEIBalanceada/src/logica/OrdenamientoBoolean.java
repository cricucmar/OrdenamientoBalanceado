package logica;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrdenamientoBoolean extends IntercalacionBalanceada {

    private boolean tope = true;

    public OrdenamientoBoolean(File f0, int indice) {
        super(f0, indice);
    }

    public void mezcla() throws IOException {
        int t = distribuir();
        CsvReader flujo[] = new CsvReader[2];
        flujo[0] = new CsvReader(new FileReader(f[0]));
        flujo[1] = new CsvReader(new FileReader(f[1]));
        CsvWriter flujoSalida = new CsvWriter(new FileWriter(f[3]), ',');

        while (flujo[0].readRecord()) {
            flujoSalida.writeRecord(flujo[0].getValues());
        }

        while (flujo[1].readRecord()) {
            flujoSalida.writeRecord(flujo[1].getValues());
        }

        System.out.print("Archivo ordenado ...");
    }

    @Override
    protected int distribuir() throws IOException {
        int j, nt;
        boolean clave;
        CsvReader flujo = new CsvReader(new FileReader(f0));
        flujo.readHeaders();
        CsvWriter flujoSalida[] = new CsvWriter[N2];
        for (j = 0; j < N2 - 1; j++) {
            flujoSalida[j] = new CsvWriter(new FileWriter(f[j]), ',');
        }

        j = 0; 		 // indice del flujo de salida
        nt = 1;

        // bucle termina con la excepción fin de fichero
        while (flujo.readRecord()) {
            clave = Boolean.parseBoolean(flujo.getValues()[indice]);

            if (!clave) {
                flujoSalida[0].writeRecord(flujo.getValues());
            } else {
                flujoSalida[1].writeRecord(flujo.getValues());
            }
        }

        nt++;		 // cuenta ultimo tramo
        System.out.println("\n*** Número de tramos: " + nt + " ***");
        flujo.close();
        for (j = 0; j < N2 - 1; j++) {
            flujoSalida[j].close();
        }
        return nt;
    }

    @Override
    protected int minimo(String[][] r, boolean[] activo, int n) {
        int i, indice2;
        i = indice2 = 0;
        boolean m = true;

        for (; i < n; i++) {
            if (activo[i] && Boolean.compare(Boolean.parseBoolean(r[i][indice]), m) < 0) {
                m = Boolean.parseBoolean(r[i][indice]);
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
            while (flujo.readRecord()) {
            }
            //System.out.println("\n *** Fin del archivo ***\n");
            flujo.close();
        } catch (IOException eof) {
            System.out.println("Archivo sin registros");
        }
    }

    @Override
    protected boolean findeTramo(String[] r, String[] anterior, int indice) {
        return (Boolean.compare(Boolean.parseBoolean(anterior[indice]), Boolean.parseBoolean(r[indice])) > 0);
    }

}
