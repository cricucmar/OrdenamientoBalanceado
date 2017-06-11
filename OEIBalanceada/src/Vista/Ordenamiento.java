package Vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import Datos.CargarArchivo;
import Datos.GenerarArchivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import logica.IntercalacionBalanceada;
import logica.OrdenamientoBoolean;
import logica.OrdenamientoDate;
import logica.OrdenamientoInt;
import logica.OrdenamientoString;

public class Ordenamiento extends javax.swing.JFrame {

    private File f0;
    private Timer t;
    private static ActionListener ac;
    private int x = 0;
    private IntercalacionBalanceada intercalacion;

    public Ordenamiento() {
        initComponents();
        this.setLocationRelativeTo(null);//pone en el centro la pantalla
        this.setResizable(false);//desabilita el maximizar
        this.setTitle("Intercalacion Balanceada");//pone el titulo de la pantalla
        ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x++;
                BarraProgreso.setValue(x);
                if (BarraProgreso.getValue() == 100) {
                    t.stop();
                    JOptionPane.showMessageDialog(rootPane, "Archivo ordenado");
                    x = 0;
                }
            }
        };
        t = new Timer(1, ac);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Salir = new javax.swing.JButton();
        Ordenar = new javax.swing.JButton();
        Cargar = new javax.swing.JButton();
        BarraProgreso = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ListaCampos = new javax.swing.JComboBox<>();
        Generar1 = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Salir.setForeground(new java.awt.Color(255, 255, 255));
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Salir1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setBorder(null);
        Salir.setBorderPainted(false);
        Salir.setContentAreaFilled(false);
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Salir.setIconTextGap(-30);
        Salir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Salir3.png"))); // NOI18N
        Salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Salir2.png"))); // NOI18N
        Salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        getContentPane().add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 430, 140, 150));

        Ordenar.setForeground(new java.awt.Color(255, 255, 255));
        Ordenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Registrar1.png"))); // NOI18N
        Ordenar.setText("Ordenar");
        Ordenar.setBorder(null);
        Ordenar.setBorderPainted(false);
        Ordenar.setContentAreaFilled(false);
        Ordenar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ordenar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Ordenar.setIconTextGap(-30);
        Ordenar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Registrar3.png"))); // NOI18N
        Ordenar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Registrar2.png"))); // NOI18N
        Ordenar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrdenarActionPerformed(evt);
            }
        });
        getContentPane().add(Ordenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 140, 150));

        Cargar.setForeground(new java.awt.Color(255, 255, 255));
        Cargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Cargar1.png"))); // NOI18N
        Cargar.setText("Cargar Archivo");
        Cargar.setBorder(null);
        Cargar.setBorderPainted(false);
        Cargar.setContentAreaFilled(false);
        Cargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cargar.setIconTextGap(-30);
        Cargar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Cargar3.png"))); // NOI18N
        Cargar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Cargar2.png"))); // NOI18N
        Cargar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });
        getContentPane().add(Cargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 140, 150));

        BarraProgreso.setStringPainted(true);
        getContentPane().add(BarraProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 350, 20));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Progreso del Ordenamiento");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 350, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Campos del Archivo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 460, -1));

        ListaCampos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Campo 1 (Numero)", "Campo 2 (Texto)", "Campo 3 (Logica)", "Campo 4 (Fecha)" }));
        ListaCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaCamposActionPerformed(evt);
            }
        });
        getContentPane().add(ListaCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 460, -1));

        Generar1.setForeground(new java.awt.Color(255, 255, 255));
        Generar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Run1.png"))); // NOI18N
        Generar1.setText("Generar");
        Generar1.setBorder(null);
        Generar1.setBorderPainted(false);
        Generar1.setContentAreaFilled(false);
        Generar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Generar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Generar1.setIconTextGap(-30);
        Generar1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Run3.png"))); // NOI18N
        Generar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/Run2.png"))); // NOI18N
        Generar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Generar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Generar1ActionPerformed(evt);
            }
        });
        getContentPane().add(Generar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, 140, 150));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/fondo_pantalla.jpeg"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void OrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrdenarActionPerformed
        //t.start();
        switch(ListaCampos.getSelectedIndex()){
            case 0:
                intercalacion=new OrdenamientoInt(f0,0);
                break;
            case 1:
                intercalacion=new OrdenamientoString(f0,1);
                break;
            case 2:
                intercalacion=new OrdenamientoBoolean(f0,2);
                break;
            case 3:
                intercalacion=new OrdenamientoDate(f0,3);
                break;
        }
        try {
            intercalacion.mezcha();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "ERROR: Probemas al abrir el archivo");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_OrdenarActionPerformed

    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed
        CargarArchivo cargaArchivo = new CargarArchivo();
        f0 = new File(cargaArchivo.seleccionArchivo(this));
        String a = new String();
        try {
            Scanner l = new Scanner(f0);
            while (l.hasNext()) {
                a = l.nextLine();
                System.out.println(a);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "No existe el fichero o el directorio");
        }
    }//GEN-LAST:event_CargarActionPerformed

    private void ListaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaCamposActionPerformed
        System.out.println(ListaCampos.getSelectedIndex());
    }//GEN-LAST:event_ListaCamposActionPerformed

    private void Generar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Generar1ActionPerformed
        try {
            if (GenerarArchivo.getArchivoGenerado() == false) {
                f0 = GenerarArchivo.generarArchivo();
                JOptionPane.showMessageDialog(rootPane, "Archivo generado");
            } else {
                JOptionPane.showMessageDialog(rootPane, "El archivo ya esta generado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "ERROR: Probemas al generar el archivo");
        }
    }//GEN-LAST:event_Generar1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ordenamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar BarraProgreso;
    private javax.swing.JButton Cargar;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton Generar1;
    private javax.swing.JComboBox<String> ListaCampos;
    private javax.swing.JButton Ordenar;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
