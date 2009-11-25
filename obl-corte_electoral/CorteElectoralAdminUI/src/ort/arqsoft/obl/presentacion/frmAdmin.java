/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmAdmin.java
 *
 * Created on 18-nov-2009, 1:26:06
 */

package ort.arqsoft.obl.presentacion;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import ort.arqsoft.obl.dominio.Circuito;
import ort.arqsoft.obl.dominio.Lista;
import ort.arqsoft.obl.persistencia.PCircuito;
import ort.arqsoft.obl.persistencia.PEleccion;
import ort.arqsoft.obl.persistencia.PLista;
import ort.arqsoft.obl.utils.Constants;

/**
 *
 * @author Felipe
 */
public class frmAdmin extends javax.swing.JFrame {

    private DefaultListModel modeloLista;
    private DefaultListModel modeloCircuito;

    private int ActionLista = Constants.NEW;
    private int ActionCircuito = Constants.NEW;
    
    /** Creates new form frmAdmin */
    public frmAdmin() {
        initComponents();
        setearForm();
    }

    private void setearForm(){

        this.txtIdLista.enable(false);
        this.txtIdCircuito.enable(false);

        cargarListas();
        cargarCircuitos();
        cargarEleccion();
    }

    private void cargarListas() {
        ArrayList<Lista> Listas = new ArrayList<Lista>();
        PLista pl = new PLista();
        modeloLista = new DefaultListModel();
        this.lstListas.setModel(modeloLista);

        modeloLista.clear();        

        Listas = pl.listarListas();

        if (Listas != null) {
            for (int i = 0; i < Listas.size(); i++) {
                //modeloLista.addElement(Listas.get(i).toString());
                modeloLista.addElement((Lista)Listas.get(i));
                //modeloLista.addElement(Listas.get(i).getId() + " " + Listas.get(i).getPartidoPolitico() + " " + Listas.get(i).getLista() + " - " + Listas.get(i).getLema());
            }
        }
    }

     private void cargarCircuitos() {
        ArrayList<Circuito> Circuitos = new ArrayList<Circuito>();
        PCircuito pc = new PCircuito();
        modeloCircuito = new DefaultListModel();
        this.lstCircuitos.setModel(modeloCircuito);

        modeloCircuito.clear();

        Circuitos = pc.listarCircuitos();

        if (Circuitos != null) {
            for (int i = 0; i < Circuitos.size(); i++) {
                modeloCircuito.addElement((Circuito)Circuitos.get(i));
                //modeloCircuito.addElement(Circuitos.get(i).toString());
            }
        }
    }

     private void cargarEleccion(){
         PEleccion pe = new PEleccion();

        if (pe.estadoEscutinio().equals("Definitivo"))
            this.chkActivoEscrutinio.setSelected(true);
        else
            this.chkActivoEscrutinio.setSelected(false);
     }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlLista = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPPolitico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLista = new javax.swing.JTextField();
        txtLema = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnAgregarLista = new javax.swing.JButton();
        btnEditarLista = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstListas = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();
        txtIdLista = new javax.swing.JTextField();
        btnLimpiarLista = new javax.swing.JButton();
        btnGrabarLista = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        chkActivoLista = new javax.swing.JCheckBox();
        pnlCorcuito = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNroCircuito = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        txtLocal = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCircuitos = new javax.swing.JList();
        btnAgregar = new javax.swing.JButton();
        btnEditarCircuito = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        chkActivoCircuito = new javax.swing.JCheckBox();
        txtIdCircuito = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnGrabarCircuito = new javax.swing.JButton();
        btnLimpiarCircuito = new javax.swing.JButton();
        txtDesde = new javax.swing.JFormattedTextField();
        txtHasta = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGrabarEscrutinio = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        chkActivoEscrutinio = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLista.setBackground(new java.awt.Color(244, 229, 196));
        pnlLista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pnlLista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Administración de Listas");
        pnlLista.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        pnlLista.add(txtPPolitico, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 80, -1));

        jLabel9.setText("Partido Politico:");
        pnlLista.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jLabel10.setText("Lista:");
        pnlLista.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));
        pnlLista.add(txtLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 80, -1));
        pnlLista.add(txtLema, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 240, -1));

        jLabel11.setText("Lema:");
        pnlLista.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, 10));

        btnAgregarLista.setText("Nuevo");
        btnAgregarLista.setToolTipText("Debe completar los datos antes de agregar.");
        btnAgregarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarListaActionPerformed(evt);
            }
        });
        pnlLista.add(btnAgregarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 110, -1));

        btnEditarLista.setText("Editar");
        btnEditarLista.setToolTipText("Debe seleccionar de la lista para editar.");
        btnEditarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarListaActionPerformed(evt);
            }
        });
        pnlLista.add(btnEditarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 110, -1));

        lstListas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstListasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstListas);

        pnlLista.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 360, 170));

        jLabel12.setText("Nro.:");
        pnlLista.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));
        pnlLista.add(txtIdLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 80, -1));

        btnLimpiarLista.setText("Limpiar");
        btnLimpiarLista.setToolTipText("Limpia el formulario para ingresar nueva lista.");
        btnLimpiarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarListaActionPerformed(evt);
            }
        });
        pnlLista.add(btnLimpiarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 110, -1));

        btnGrabarLista.setText("Grabar");
        btnGrabarLista.setToolTipText("Guarda los datos nuevos o editados en la base de datos.");
        btnGrabarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarListaActionPerformed(evt);
            }
        });
        pnlLista.add(btnGrabarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 110, -1));
        pnlLista.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 900, 10));

        chkActivoLista.setBackground(new java.awt.Color(244, 229, 196));
        chkActivoLista.setSelected(true);
        chkActivoLista.setText("Activo");
        pnlLista.add(chkActivoLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 60, -1));

        pnlCorcuito.setBackground(new java.awt.Color(204, 255, 204));
        pnlCorcuito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pnlCorcuito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Administración de Circuitos");
        pnlCorcuito.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setText("Nro. Circuito:");
        pnlCorcuito.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel4.setText("Serie:");
        pnlCorcuito.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel5.setText("Nro. desde:");
        pnlCorcuito.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, 10));

        jLabel6.setText("Nro. hasta:");
        pnlCorcuito.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jLabel7.setText("Local:");
        pnlCorcuito.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel8.setText("Dirección:");
        pnlCorcuito.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));
        pnlCorcuito.add(txtNroCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 80, -1));
        pnlCorcuito.add(txtSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 80, -1));
        pnlCorcuito.add(txtLocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 240, -1));
        pnlCorcuito.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 240, -1));

        jScrollPane1.setViewportView(lstCircuitos);

        pnlCorcuito.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 360, 170));

        btnAgregar.setText("Agregar");
        btnAgregar.setActionCommand("Nuevo");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        pnlCorcuito.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 110, -1));

        btnEditarCircuito.setText("Editar");
        btnEditarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCircuitoActionPerformed(evt);
            }
        });
        pnlCorcuito.add(btnEditarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 110, -1));
        pnlCorcuito.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 32, 940, 0));
        pnlCorcuito.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 900, 10));

        chkActivoCircuito.setBackground(new java.awt.Color(204, 255, 204));
        chkActivoCircuito.setText("Activo");
        pnlCorcuito.add(chkActivoCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));
        pnlCorcuito.add(txtIdCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 80, -1));

        jLabel14.setText("Nro.:");
        pnlCorcuito.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        btnGrabarCircuito.setText("Grabar");
        btnGrabarCircuito.setToolTipText("Guarda los datos nuevos o editados en la base de datos.");
        btnGrabarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarCircuitoActionPerformed(evt);
            }
        });
        pnlCorcuito.add(btnGrabarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 110, -1));

        btnLimpiarCircuito.setText("Limpiar");
        btnLimpiarCircuito.setToolTipText("Limpia el formulario para ingresar nueva lista.");
        btnLimpiarCircuito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCircuitoActionPerformed(evt);
            }
        });
        pnlCorcuito.add(btnLimpiarCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 110, -1));

        txtDesde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        pnlCorcuito.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 80, -1));

        txtHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        pnlCorcuito.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 80, -1));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnGrabarEscrutinio.setText("Grabar");
        btnGrabarEscrutinio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarEscrutinioActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Administración de Listas");

        chkActivoEscrutinio.setBackground(new java.awt.Color(153, 204, 255));
        chkActivoEscrutinio.setSelected(true);
        chkActivoEscrutinio.setText("Escrutinio Definitivo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkActivoEscrutinio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGrabarEscrutinio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkActivoEscrutinio)
                    .addComponent(btnGrabarEscrutinio))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlCorcuito, javax.swing.GroupLayout.PREFERRED_SIZE, 940, Short.MAX_VALUE)
                        .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlCorcuito, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 970, 610));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setText("Consola de Administración - CORTE ELECTORAL");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCircuitoActionPerformed
        // TODO add your handling code here:
        Circuito circuito;

        if (this.lstCircuitos.getSelectedIndex() >= 0) {
            //lista = new Lista();
            circuito = (Circuito) modeloCircuito.getElementAt(this.lstCircuitos.getSelectedIndex());

            this.txtIdCircuito.setText(String.valueOf(circuito.getId()));
            this.txtNroCircuito.setText(String.valueOf(circuito.getNroCircuito()));
            this.txtSerie.setText(circuito.getSerie());
            this.txtDesde.setText(String.valueOf(circuito.getDesde()));
            this.txtHasta.setText(String.valueOf(circuito.getHasta()));
            this.txtLocal.setText(circuito.getLocal());
            this.txtDireccion.setText(circuito.getDireccion());

            if (circuito.getActivo()==1)
                this.chkActivoCircuito.setSelected(true);
            else
                this.chkActivoCircuito.setSelected(false);
            
            this.ActionCircuito = Constants.EDIT;
        }
}//GEN-LAST:event_btnEditarCircuitoActionPerformed

    private void btnEditarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarListaActionPerformed
        // TODO add your handling code here:
        Lista lista;

        if (this.lstListas.getSelectedIndex() >= 0) {
            //lista = new Lista();
            lista = (Lista) modeloLista.getElementAt(this.lstListas.getSelectedIndex());

            this.txtIdLista.setText(String.valueOf(lista.getId()));
            this.txtPPolitico.setText(lista.getPartidoPolitico());
            this.txtLista.setText(lista.getLista());
            this.txtLema.setText(lista.getLema());
            
            if (lista.getActivo()==1)
                this.chkActivoLista.setSelected(true);
            else
                this.chkActivoLista.setSelected(false);
            
            this.ActionLista = Constants.EDIT;
        }
        
    }//GEN-LAST:event_btnEditarListaActionPerformed

    private void btnAgregarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarListaActionPerformed
        // TODO add your handling code here:
        this.ActionLista = Constants.NEW;
        this.txtIdLista.setText("");
    }//GEN-LAST:event_btnAgregarListaActionPerformed

    private void btnLimpiarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarListaActionPerformed
        // TODO add your handling code here:
        limpiarListas();
}//GEN-LAST:event_btnLimpiarListaActionPerformed

    private void lstListasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstListasValueChanged
        // TODO add your handling code here:        
    }//GEN-LAST:event_lstListasValueChanged

    private void btnGrabarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarListaActionPerformed
        // TODO add your handling code here:
        if (validarDatosLista()){
            grabarLista();
        }
}//GEN-LAST:event_btnGrabarListaActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        this.ActionCircuito = Constants.NEW;
        this.txtIdCircuito.setText("");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGrabarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarCircuitoActionPerformed
        // TODO add your handling code here:
        if (validarDatosCircuito()){
            grabarCircuito();
        }
}//GEN-LAST:event_btnGrabarCircuitoActionPerformed

    private void btnLimpiarCircuitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCircuitoActionPerformed
        // TODO add your handling code here:
        limpiarCircuitos();
}//GEN-LAST:event_btnLimpiarCircuitoActionPerformed

    private void btnGrabarEscrutinioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarEscrutinioActionPerformed
        // TODO add your handling code here:
        grabarEscrutinio();
    }//GEN-LAST:event_btnGrabarEscrutinioActionPerformed

    private boolean validarDatosLista(){
        boolean valido = true;

        if(this.txtPPolitico.getText().equals(""))
            return false;
        if(this.txtLista.getText().equals(""))
            return false;
        if(this.txtLema.getText().equals(""))
            return false;

        return valido;
    }

     private boolean validarDatosCircuito(){
        boolean valido = true;

        if(this.txtNroCircuito.getText().equals(""))
            return false;
        if(this.txtSerie.getText().equals(""))
            return false;
        if(this.txtDesde.getText().equals(""))
            return false;
        if(this.txtHasta.getText().equals(""))
            return false;
        if(this.txtLocal.getText().equals(""))
            return false;
        if(this.txtDireccion.getText().equals(""))
            return false;
        
        return valido;
    }

    private void grabarLista(){
        PLista pl = new PLista();
        Lista lista = new Lista();

        
        if (this.ActionLista == Constants.NEW){
            lista.setId(0);
        }else if ((this.ActionLista == Constants.EDIT)){
            lista.setId(Long.parseLong(this.txtIdLista.getText()));
        }

        lista.setPartidoPolitico(this.txtPPolitico.getText());
        lista.setLista(this.txtLista.getText());
        lista.setLema(this.txtLema.getText());

        if (this.chkActivoLista.isSelected())
            lista.setActivo(1);
        else
            lista.setActivo(0);

        if (pl.grabarLista(lista)){
            limpiarListas();
        }else{

        }
    }

      private void grabarCircuito(){
        PCircuito pc = new PCircuito();
        Circuito circuito = new Circuito();


        if (this.ActionCircuito == Constants.NEW){
            circuito.setId(0);
        }else if ((this.ActionCircuito == Constants.EDIT)){
            circuito.setId(Long.parseLong(this.txtIdCircuito.getText()));
        }

        circuito.setNroCircuito(Long.parseLong(this.txtNroCircuito.getText()));
        circuito.setSerie(this.txtSerie.getText());
        circuito.setDesde(Long.parseLong(this.txtDesde.getText()));
        circuito.setHasta(Long.parseLong(this.txtHasta.getText()));
        circuito.setLocal(this.txtLocal.getText());
        circuito.setDireccion(this.txtDireccion.getText());

        if (this.chkActivoCircuito.isSelected())
            circuito.setActivo(1);
        else
            circuito.setActivo(0);

        if (pc.grabarCircuito(circuito)){
            limpiarCircuitos();
        }else{

        }
    }

    private void grabarEscrutinio(){
        PEleccion pe = new PEleccion();
        String estado = "";

        if (this.chkActivoEscrutinio.isSelected())
            estado = "Definitivo";
        else
            estado = "Parcial";

        if (pe.grabarEstado(estado)){
            //ok
        }else{
            //error
        }
    }

    private void limpiarListas(){
        
        cargarListas();

        this.txtIdLista.setText("");
        this.txtPPolitico.setText("");
        this.txtLista.setText("");
        this.txtLema.setText("");
        this.chkActivoLista.setSelected(true);
    }

     private void limpiarCircuitos(){

        cargarCircuitos();

        this.txtIdCircuito.setText("");
        this.txtNroCircuito.setText("");
        this.txtSerie.setText("");
        this.txtDesde.setText("");
        this.txtHasta.setText("");
        this.txtLocal.setText("");
        this.txtDireccion.setText("");
        this.chkActivoCircuito.setSelected(true);
    }

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmAdmin().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarLista;
    private javax.swing.JButton btnEditarCircuito;
    private javax.swing.JButton btnEditarLista;
    private javax.swing.JButton btnGrabarCircuito;
    private javax.swing.JButton btnGrabarEscrutinio;
    private javax.swing.JButton btnGrabarLista;
    private javax.swing.JButton btnLimpiarCircuito;
    private javax.swing.JButton btnLimpiarLista;
    private javax.swing.JCheckBox chkActivoCircuito;
    private javax.swing.JCheckBox chkActivoEscrutinio;
    private javax.swing.JCheckBox chkActivoLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JList lstCircuitos;
    private javax.swing.JList lstListas;
    private javax.swing.JPanel pnlCorcuito;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JFormattedTextField txtDesde;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JFormattedTextField txtHasta;
    private javax.swing.JTextField txtIdCircuito;
    private javax.swing.JTextField txtIdLista;
    private javax.swing.JTextField txtLema;
    private javax.swing.JTextField txtLista;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextField txtNroCircuito;
    private javax.swing.JTextField txtPPolitico;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables

}
