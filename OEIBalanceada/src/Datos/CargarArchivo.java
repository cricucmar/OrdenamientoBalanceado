package Datos;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CargarArchivo {

    JFileChooser archivo = new JFileChooser();

    public CargarArchivo() {
        archivo.setDialogTitle("Seleccione un archivo");
        archivo.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter(".csv", "csv");
        archivo.setFileFilter(csvFilter);
    }

    public String seleccionArchivo(Component arch) {
        int archivoRecuperado = archivo.showOpenDialog(arch);
        if (archivoRecuperado == JFileChooser.APPROVE_OPTION) {
            File archivo = this.archivo.getSelectedFile();
            if (archivo != null) {
                JOptionPane.showMessageDialog(null, "Archivo seleccionado");
                return archivo.toString();
            } else {
                JOptionPane.showMessageDialog(null, "No se a seleccionado un archivo");
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se a seleccionado un archivo");
            return null;
        }
    }
}
