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
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//import ort.arqsoft.obl.socket.SocketClient;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import ort.arqsoft.obl.communication.SocketCommunication;
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.dominio.Voto;
import ort.arqsoft.obl.utils.Constants;
import ort.arqsoft.obl.utils.XmlRead;
import ort.arqsoft.obl.utils.PrintAndWriterLog;
import ort.arqsoft.obl.utils.Xml;

/**
 *
 * @author Felipe
 */
public class frmUrna extends javax.swing.JFrame {

    private DefaultListModel modelo;
    private boolean isConnected = false;
    private int seconds;
    private XmlRead xr;
    ArrayList<Lista> Listas = null;
    //private Lista listaVotada;
    private Voto voto;

    //private Timer timer;
    /** Creates new form frmUrna */
    public frmUrna() {
        initComponents();
        voto = new Voto();        
        voto.setNroCircuito(Constants.NROCIRCUITO);
        
        this.jLabel1.setText("Urna digital - Circuito número:   " + Constants.NROCIRCUITO);

        formEnabled(Constants.INICIAL);

        if (!isConnected) {
            isConnected = SocketCommunication.connectSocket();

            //ENVIO LA SOLICITUD DE LAS LISTAS
            if (isConnected)
                SocketCommunication.sendData(Xml.getSolicitarListas());
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
                this.lblMsj.setVisible(false);
                this.pnlPartidosPoliticos.setVisible(true);
                this.pnlListas.setVisible(false);
                this.btnVotar.setVisible(false);
                break;
            case Constants.SEGUNDO:
                this.lblMsj.setVisible(false);
                this.pnlPartidosPoliticos.setVisible(true);
                this.pnlListas.setVisible(true);
                if (voto.getPartidoPolitico().equals(Constants.BLANCO)){
                    this.lstListas.setVisible(false);
                    this.btnVotar.setVisible(true);
                }else{
                    this.lstListas.setVisible(true);
                    this.btnVotar.setVisible(false);
                }
                break;
            case Constants.TERCERO:
                this.lblMsj.setVisible(true);
                this.pnlPartidosPoliticos.setVisible(false);
                this.pnlListas.setVisible(false);
                this.btnVotar.setVisible(false);
                break;
            case Constants.INICIAL:
                this.lblMsj.setVisible(false);
                this.pnlPartidosPoliticos.setVisible(false);
                this.pnlListas.setVisible(false);
                this.btnVotar.setVisible(false);
                esperaParaIniciar();
                seconds = 60;
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
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(frmUrna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("static-access")
    private void obtenerDatos() {
        Listas = new ArrayList<Lista>();
        String xml = null;
        String tipo_xml = null;

        if (isConnected) {
            //SI ESTOY CONECTADO AL SERVIDOR DE LA CORTE ELECTORAL...
            eliminarFiles(Constants.PATH_LISTASXML);
            xml = SocketCommunication.readData();
            if (xml != null) {
                tipo_xml = xr.obtenerTipo(xml);
                if (tipo_xml.equals("envio_listas")) {
                    xr = new XmlRead();
                    Listas = xr.obtenerListas(xml);
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
                        tipo_xml = xr.obtenerTipo(xml);
                        if (tipo_xml.equals("envio_listas")) {
                            xr = new XmlRead();
                            Listas = xr.obtenerListas(xml);
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
                if (Listas.get(i).getPartidoPolitico().equals(voto.getPartidoPolitico())) {
                    modelo.addElement((Lista)Listas.get(i));
                    //modelo.add((int) Listas.get(i).getLista(), Listas.get(i).getPartidoPolitico() + " " + Listas.get(i).getLista() + " - " + Listas.get(i).getLema());
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

        btnVotar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlListas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstListas = new javax.swing.JList();
        lblListas = new javax.swing.JLabel();
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
        btnB = new javax.swing.JButton();
        lblMsj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        btnVotar.setBackground(new java.awt.Color(204, 255, 204));
        btnVotar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnVotar.setText("VOTAR");
        btnVotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotarActionPerformed(evt);
            }
        });

        pnlListas.setBackground(new java.awt.Color(204, 255, 204));
        pnlListas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lstListas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstListasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstListas);

        lblListas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblListas.setText("Listas del partido politico ''.");

        javax.swing.GroupLayout pnlListasLayout = new javax.swing.GroupLayout(pnlListas);
        pnlListas.setLayout(pnlListasLayout);
        pnlListasLayout.setHorizontalGroup(
            pnlListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
                    .addComponent(lblListas))
                .addContainerGap())
        );
        pnlListasLayout.setVerticalGroup(
            pnlListasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListasLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblListas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTitulo.setBackground(new java.awt.Color(204, 255, 204));
        pnlTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlTitulo.setName(""); // NOI18N

        jLabel1.setText("Urna digital - Circuito número: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Siguiente votación en");

        lblTiempo.setFont(new java.awt.Font("Tahoma", 1, 24));
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
                .addGap(531, 531, 531)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        pnlPartidosPoliticos.setBackground(new java.awt.Color(204, 255, 204));
        pnlPartidosPoliticos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnP1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP1.setText("PARTIDO 1");
        btnP1.setToolTipText("Votar al partido politico 1");
        btnP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP1ActionPerformed(evt);
            }
        });

        btnP2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP2.setText("PARTIDO 2");
        btnP2.setToolTipText("Votar al partido politico 2");
        btnP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP2ActionPerformed(evt);
            }
        });

        btnP3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP3.setText("PARTIDO 3");
        btnP3.setToolTipText("Votar al partido politico 3");
        btnP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP3ActionPerformed(evt);
            }
        });

        btnP4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP4.setText("PARTIDO 4");
        btnP4.setToolTipText("Votar al partido politico 4");
        btnP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP4ActionPerformed(evt);
            }
        });

        btnP5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnP5.setText("PARTIDO 5");
        btnP5.setToolTipText("Votar al partido politico 5");
        btnP5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP5ActionPerformed(evt);
            }
        });

        btnB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnB.setText("EN BLANCO");
        btnB.setToolTipText("Votar en blanco");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(btnP1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnP5, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(143, 143, 143))
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
                    .addComponent(btnP5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
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

        lblMsj.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlPartidosPoliticos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlListas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMsj, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(340, 340, 340))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPartidosPoliticos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsj, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVotar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotarActionPerformed
        // TODO add your handling code here:
        String mensaje = "";

        if (this.lstListas.getSelectedIndex() >= 0) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            long time = System.currentTimeMillis();
            Date fecha = new Date(time);
                       
            Lista lista;               
            if (this.lstListas.getSelectedIndices().length == 1){
                lista = (Lista) modelo.getElementAt(this.lstListas.getSelectedIndex());
                if (voto.getPartidoPolitico().equals(Constants.BLANCO)){
                    //VOTO EN BLANCO
                    voto.setLista(0);
                }else{
                    //VOTO CORRECTO
                    voto.setLista(lista.getLista());
                }
            }else{
                //SI SE HA SELECCIONADO MAS DE UNA LISATA, SE ANULA EL VOTO
                voto.setPartidoPolitico(Constants.ANULADO);
                voto.setLista(0);
            }
                                                            
            voto.setFecha(df.format(fecha));

            PrintAndWriterLog.setOut(".\\");
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.write("VOTACIÓN REALIZADA CON FECHA Y HORA: " + voto.getFecha()); //fecha.toString());
            PrintAndWriterLog.write("Partido: " + voto.getPartidoPolitico());
            PrintAndWriterLog.write("Lista: " + voto.getLista());
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.flush();

            PrintAndWriterLog.setOut(System.out);
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.write("VOTACIÓN REALIZADA CON FECHA Y HORA: " + voto.getFecha());
            PrintAndWriterLog.write("Partido: " + voto.getPartidoPolitico());
            PrintAndWriterLog.write("Lista: " + voto.getLista());
            PrintAndWriterLog.write("*******************************************************************");
            PrintAndWriterLog.flush();

            if (isConnected) {
                //SocketCommunication.sendData("<circuito><circuito-tipo_msj><tipo_msj>poner_voto</tipo_msj></circuito-tipo_msj><circuito_data><nro_circuito>600</nro_circuito><fecha>17/11/2009</fecha><hora>22:48</hora><partido_politico>1</partido_politico><nro_lista>100</nro_lista></circuito_data></circuito>");
                SocketCommunication.sendData(Xml.setVoto(voto));
            }

//            if (voto.getPartidoPolitico().equals(Constants.BLANCO)){
//                mensaje = "SE HA VOTADO EN BLANCO SATISFACTORIAMENTE";
//            }else if (voto.getPartidoPolitico().equals(Constants.ANULADO)){
//                mensaje = "SE HA ANULADO EL VOTO";
//            }else{
//                mensaje = "SE HA RELIZADO EL VOTO SATISFACTORIAMENTE";
//            }
//            mostrarVotacion(mensaje);
            
            formEnabled(Constants.INICIAL);
        }
    }//GEN-LAST:event_btnVotarActionPerformed

    private void mostrarVotacion(String mensaje){
        formEnabled(Constants.TERCERO);
        this.lblMsj.setText(mensaje);       
    }

    private void btnP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP1ActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Listas del partido politico 'PARTIDO 1'.");
        voto.setPartidoPolitico(Constants.PARTIDO1);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP1ActionPerformed

    private void btnP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP2ActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Listas del partido politico 'PARTIDO 2'.");
        voto.setPartidoPolitico(Constants.PARTIDO2);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP2ActionPerformed

    private void btnP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP3ActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Listas del partido politico 'PARTIDO 3'.");
        voto.setPartidoPolitico(Constants.PARTIDO3);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP3ActionPerformed

    private void btnP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP4ActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Listas del partido politico 'PARTIDO 4'.");
        voto.setPartidoPolitico(Constants.PARTIDO4);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP4ActionPerformed

    private void btnP5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP5ActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Listas del partido politico 'PARTIDO 5'.");
        voto.setPartidoPolitico(Constants.PARTIDO5);
        cargarLista();
        this.formEnabled(Constants.SEGUNDO);
    }//GEN-LAST:event_btnP5ActionPerformed

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        // TODO add your handling code here:
//        if (isConnected)
//            isConnected = clsSocketCommunication.disconnectSocket();
        //connectionStatus();
    }//GEN-LAST:event_close

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        this.lblListas.setText("Ha seleccionado VOTAR EN BLANCO.");
        voto.setPartidoPolitico(Constants.BLANCO);
        //cargarLista();
        this.formEnabled(Constants.SEGUNDO);
}//GEN-LAST:event_btnBActionPerformed

    private void lstListasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstListasValueChanged
        // TODO add your handling code here:
        this.btnVotar.setVisible(true);
    }//GEN-LAST:event_lstListasValueChanged

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
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnP1;
    private javax.swing.JButton btnP2;
    private javax.swing.JButton btnP3;
    private javax.swing.JButton btnP4;
    private javax.swing.JButton btnP5;
    private javax.swing.JButton btnVotar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblListas;
    private javax.swing.JLabel lblMsj;
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
