/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmUrna.java
 *
 * Created on 11-nov-2009, 1:47:03
 */
package ort.arqsoft.obl.presentacion;

//import com.sun.xml.internal.ws.api.message.Message;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import ort.arqsoft.obl.socket.SocketClient;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import ort.arqsoft.obl.communication.clsSocketCommunication;
import ort.arqsoft.obl.dominio.clsLista;
import ort.arqsoft.obl.utils.Constants;
import ort.arqsoft.obl.utils.XmlRead;
import ort.arqsoft.obl.utils.PrintAndWriterLog;

/**
 *
 * @author Felipe
 */
public class frmUrna extends javax.swing.JFrame {

    private DefaultListModel modelo;
//    public final static int INICIAL = 0; //cuando se ingresa al formulario o cuando se realiza un voto
//    public final static int PRIMERO = 1; //cuando se cumple el tiempo de espera
//    public final static int SEGUNDO = 2; //cuando se elige un partido politico
//    public final static int TERCERO = 3; //cuando se elige una lista
//    public final static String PARTDO1 = "P1";
//    public final static String PARTDO2 = "P2";
//    public final static String PARTDO3 = "P3";
//    public final static String PARTDO4 = "P4";
//    public final static String PARTDO5 = "P5";
//    public final static String PATH_LISTASXML = ".\\Listas.xml";
    private boolean isConnected = false;
    private int seconds;
    private XmlRead lx;
    ArrayList<clsLista> Listas = null;
    private clsLista listaVotada;

    //private Timer timer;
    /** Creates new form frmUrna */
    public frmUrna() {
        initComponents();
        listaVotada = new clsLista();

        formEnabled(Constants.INICIAL);

        if (!isConnected) {
            isConnected = clsSocketCommunication.connectSocket();
        }
        esperaParaIniciar();

        //if (isConnected) {
        // YA NO IMPORTA QUE ESTE DESCONECTADO...
        obtenerDatos();
        //}

        connectionStatus();
    }
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            seconds--;
            if (seconds == 0) {
                timer.stop();
                formEnabled(Constants.PRIMERO);
            }
            lblTiempo.setText(String.valueOf(seconds));
        }
    });

    private void formEnabled(int mode) {
        switch (mode) {
            case Constants.PRIMERO:
                this.pnlPartidosPoliticos.setVisible(true);
                this.pnlListas.setVisible(false);
                this.btnVotar.setVisible(false);
                break;
            case Constants.SEGUNDO:
                this.pnlPartidosPoliticos.setVisible(true);
                this.pnlListas.setVisible(true);
                this.btnVotar.setVisible(true);
                break;
            case Constants.TERCERO:
                this.pnlPartidosPoliticos.setVisible(true);
                this.pnlListas.setVisible(true);
                this.btnVotar.setVisible(true);
                break;
            case Constants.INICIAL:
                this.pnlPartidosPoliticos.setVisible(false);
                this.pnlListas.setVisible(false);
                this.btnVotar.setVisible(false);
                esperaParaIniciar();
                seconds = 5;
                timer.start();
                break;
        }
    }

    private void connectionStatus() {
        if (isConnected) {
            this.setTitle("Urna digital - [Conectado con servidor...] OK");
        } else {
            this.setTitle("Urna digital - [Trabajando sin conexión...]");
        }
    }

    private void esperaParaIniciar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(frmUrna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("static-access")
    private void obtenerDatos() {
        Listas = new ArrayList<clsLista>();
        String xml = null;
        String tipo_xml = null;

        if (isConnected) {
            //SI ESTOY CONECTADO AL SERVIDOR DE LA CORTE ELECTORAL...
            eliminarFiles(Constants.PATH_LISTASXML);
            xml = clsSocketCommunication.readData();
            if (xml != null) {
                tipo_xml = lx.obtenerTipo(xml);
                if (tipo_xml.equals("envio_listas")) {
                    lx = new XmlRead();
                    Listas = lx.obtenerListas(xml);
                //cargarLista();
                } else if (tipo_xml.equals("poner_voto")) {
                    System.out.println("Voto realizado correctamente");
                }
            }
        } else {
            //SI ESTOY TRABAJANDO SIN CONEXIÓN...
            File f = new File(Constants.PATH_LISTASXML);
            if (f.exists()) {
                FileReader file;
                try {
                    file = new FileReader(Constants.PATH_LISTASXML);
                    BufferedReader bfr = new BufferedReader(file);
                    xml = bfr.readLine();
                    if (xml != null) {
                        tipo_xml = lx.obtenerTipo(xml);
                        if (tipo_xml.equals("envio_listas")) {
                            lx = new XmlRead();
                            Listas = lx.obtenerListas(xml);
                        //cargarLista();
                        } else if (tipo_xml.equals("poner_voto")) {
                            System.out.println("Voto realizado correctamente");
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(frmUrna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void eliminarFiles(String pFilePath) {
        File f = new File(pFilePath);
        if (f.exists()) {
            f.delete();
        }
    }

    private void cargarLista() {
        modelo = new DefaultListModel();
        this.lstListas.setModel(modelo);
        modelo.clear();
        if (Listas != null) {
            for (int i = 0; i < Listas.size(); i++) {
                if (Listas.get(i).getPartidoPolitico().equals(listaVotada.getPartidoPolitico())) {
                    modelo.addElement(Listas.get(i).getPartidoPolitico() + " " + Listas.get(i).getLista() + " - " + Listas.get(i).getLema());
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlPartidosPoliticos = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        btnP1 = new javax.swing.JButton();
        btnP2 = new javax.swing.JButton();
        btnP3 = new javax.swing.JButton();
        btnP4 = new javax.swing.JButton();
        btnP5 = new javax.swing.JButton();
        pnlListas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstListas = new javax.swing.JList();
        btnVotar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        pnlTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlTitulo.setName(""); // NOI18N

        jLabel1.setText("Urna digital");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Siguiente votación en");

        lblTiempo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTiempo.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("segundos.");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(636, 636, 636)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTiempo)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPartidosPoliticos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnP1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP1.setText("P1");
        btnP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP1ActionPerformed(evt);
            }
        });

        btnP2.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnP2.setText("P2");
        btnP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP2ActionPerformed(evt);
            }
        });

        btnP3.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnP3.setText("P3");
        btnP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP3ActionPerformed(evt);
            }
        });

        btnP4.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnP4.setText("P4");
        btnP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP4ActionPerformed(evt);
            }
        });

        btnP5.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnP5.setText("P5");
        btnP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btnP1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP5, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnP1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(btnP2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(btnP3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(btnP4, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(btnP5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPartidosPoliticosLayout = new javax.swing.GroupLayout(pnlPartidosPoliticos);
        pnlPartidosPoliticos.setLayout(pnlPartidosPoliticosLayout);
        pnlPartidosPoliticosLayout.setHorizontalGroup(
            pnlPartidosPoliticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPartidosPoliticosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPartidosPoliticosLayout.setVerticalGroup(
            pnlPartidosPoliticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPartidosPoliticosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlListas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setViewportView(lstListas);

        javax.swing.GroupLayout pnlListasLayout = new javax.swing.GroupLayout(pnlListas);
        pnlListas.setLayout(pnlListasLayout);
        pnlListasLayout.setHorizontalGroup(
            pnlListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlListasLayout.setVerticalGroup(
            pnlListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListasLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnVotar.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnVotar.setText("VOTAR");
        btnVotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(btnVotar, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addGap(398, 398, 398))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPartidosPoliticos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlListas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPartidosPoliticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnVotar, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotarActionPerformed
        // TODO add your handling code here:

        if (this.lstListas.getSelectedIndex() >= 0) {

            long time = System.currentTimeMillis();
            Date fecha = new Date(time);

            //modelo = (DefaultListModel) lstListas.getModel();

            //listaVotada.setLista((String) modelo.get(1));
            //this.lstListas.getSelectedIndex();
            listaVotada.setLista((String) modelo.getElementAt(this.lstListas.getSelectedIndex()));

            PrintAndWriterLog.setOut(".\\");
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.write("VOTACIÓN REALIZADA CON FECHA Y HORA: " + fecha.toString());
            PrintAndWriterLog.write("Partido: " + listaVotada.getPartidoPolitico());
            PrintAndWriterLog.write("Lista: " + listaVotada.getLista());
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.flush();

            PrintAndWriterLog.setOut(System.out);
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.write("VOTACIÓN REALIZADA CON FECHA Y HORA: " + fecha.toString());
            PrintAndWriterLog.write("Partido: " + listaVotada.getPartidoPolitico());
            PrintAndWriterLog.write("Lista: " + listaVotada.getLista());
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.flush();

            if (isConnected) {
                clsSocketCommunication.sendData("<circuito><circuito-tipo_msj><tipo_msj>poner_voto</tipo_msj></circuito-tipo_msj><circuito_data><nro_circuito>600</nro_circuito><fecha>17/11/2009</fecha><hora>22:48</hora><partido_politico>1</partido_politico><nro_lista>100</nro_lista></circuito_data></circuito>");
            }

            formEnabled(Constants.INICIAL);
        }
    }//GEN-LAST:event_btnVotarActionPerformed

    private void btnP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP1ActionPerformed
        // TODO add your handling code here:
        listaVotada.setPartidoPolitico(Constants.PARTDO1);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP1ActionPerformed

    private void btnP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP2ActionPerformed
        // TODO add your handling code here:
        listaVotada.setPartidoPolitico(Constants.PARTDO2);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP2ActionPerformed

    private void btnP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP3ActionPerformed
        // TODO add your handling code here:
        listaVotada.setPartidoPolitico(Constants.PARTDO3);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP3ActionPerformed

    private void btnP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP4ActionPerformed
        // TODO add your handling code here:
        listaVotada.setPartidoPolitico(Constants.PARTDO4);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP4ActionPerformed

    private void btnP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP5ActionPerformed
        // TODO add your handling code here:
        listaVotada.setPartidoPolitico(Constants.PARTDO5);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP5ActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        // TODO add your handling code here:
//        if (isConnected)
//            isConnected = clsSocketCommunication.disconnectSocket();
        //connectionStatus();
    }//GEN-LAST:event_close

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmUrna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnP1;
    private javax.swing.JButton btnP2;
    private javax.swing.JButton btnP3;
    private javax.swing.JButton btnP4;
    private javax.swing.JButton btnP5;
    private javax.swing.JButton btnVotar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JList lstListas;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlListas;
    private javax.swing.JPanel pnlPartidosPoliticos;
    private javax.swing.JPanel pnlTitulo;
    // End of variables declaration//GEN-END:variables

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
