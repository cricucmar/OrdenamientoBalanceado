package logica;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class IntercalacionBalanceada {

    protected final int N = 6;
    protected final int N2 = N / 2;
    protected int indice;
    protected File f0;
    protected File[] f = new File[N];

    public IntercalacionBalanceada(File f0, int indice) {
        String[] nomf = {"ar1.csv", "ar2.csv", "ar3.csv", "ar4.csv", "ar5.csv", "ar6.csv"};
        for (int i = 0; i < N; i++) {
            f[i] = new File(nomf[i]);
        }
        this.f0 = f0;
        this.indice = indice;
    }

    //metodo de ordenamiento
    public void mezcha() throws IOException {
        int i, j, k1, t=0;
        String[] anterior;
        int[] c = new int[N];
        int[] cd = new int[N];
        String[][] r = new String[N2][];
        Object[] flujo = new Object[N];
        CsvReader flujoEntradaActual = null;
        CsvWriter flujoSalidaActual = null;
        boolean[] actvs = new boolean[N2];
        //distribucion general de tramos desde el archivo original
        try {
            setTope();
            t = distribuir();
            for (i = 0; i < N; i++) {
                c[i] = i;
            }
            //bucle hasta numero de tramos==1 archivo ordenado
            do {
                k1 = (t < N2) ? t : N2;
                for (i = 0; i < k1; i++) {
                    flujo[c[i]] = new CsvReader(new FileReader(f[c[i]]));
                    cd[i] = c[i];
                }
                j = N2;//Indice de archivo de salida
                t = 0;
                for (i = j; i < N; i++) {
                    flujo[c[i]] = new CsvWriter(new FileWriter(f[c[i]]), ',');
                }
                //entrada de una nueva clave de cada flujo
                for (int n = 0; n < k1; n++) {
                    flujoEntradaActual = (CsvReader) flujo[cd[n]];
                    flujoEntradaActual.readRecord();
                    r[n] = flujoEntradaActual.getValues();
                }
                while (k1 > 0) {
                    t++;//mezcla de otro tramo
                    for (i = 0; i < k1; i++) {
                        actvs[i] = true;
                    }
                    flujoSalidaActual = (CsvWriter) flujo[c[j]];
                    while (!finDeTramo(actvs, k1)) {
                        int n;
                        n = minimo(r, actvs, k1);
                        flujoEntradaActual = (CsvReader) flujo[cd[n]];
                        flujoSalidaActual.writeRecord(r[n]);
                        anterior = r[n];
                        if(flujoEntradaActual.readRecord()){
                            r[n] = flujoEntradaActual.getValues();
                            if (findeTramo(r[n], anterior, indice)) {//fin de tramo
                                actvs[n] = false;
                            }
                        } else{
                            k1--;
                            flujoEntradaActual.close();
                            cd[n] = cd[k1];
                            r[n] = r[k1];
                            actvs[n] = actvs[k1];
                            actvs[k1] = false;//no se accede a la posicion k1
                        }
                    }
                    j = (j < N - 1) ? j + 1 : N2;//siguiente flujo de salida
                }
                for (i = N2; i < N; i++) {
                    flujoSalidaActual = (CsvWriter) flujo[c[i]];
                    flujoSalidaActual.close();
                }
                //Cambio de finalidad de los flujos: entrada <-> salida
                for (i = 0; i < N2; i++) {
                    int a;
                    a = c[i];
                    c[i] = c[i + N2];
                    c[i + N2] = a;
                }
            } while (t > 1);
            System.out.println("Archivo Ordenado...");
            //escribir(f[c[0]]);
        } catch (IOException er) {
            System.out.println("no");
            er.printStackTrace();
        }
    }

    //distribuye tramos de flujos de entrada en flujos de salida
    protected abstract int distribuir() throws IOException;

    //devuelve el índice del menor valor del array de claves
    protected abstract int minimo(String[][] r, boolean[] activo, int n);

    //asigna el tope de registros para ordenarlos
    protected abstract void setTope();
    
    //devuelve true si  r[n] > anterior
    protected abstract boolean findeTramo(String [] r, String[] anterior, int indice);
    
    //devuelve true si no hay un tramo activo
    protected boolean finDeTramo(boolean[] activo, int n) {
        boolean s = true;
        for (int k = 0; k < n; k++) {
            if (activo[k]) {
                s = false;
            }
        }
        return s;
    }
}
