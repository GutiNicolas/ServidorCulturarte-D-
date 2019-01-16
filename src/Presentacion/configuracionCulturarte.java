/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.ContCargaBD;
import Logica.culturarteFabrica;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jp
 */
public class configuracionCulturarte extends javax.swing.JInternalFrame {

    JFileChooser jF = new JFileChooser();
    JFileChooser jFProp = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties", "properties", "TXT", "txt");
    String rutaUini, rutaUdes, rutaPini, rutaPdes;
    HashMap<String, String> configProperties = new HashMap<>();
    ContCargaBD contCarga = ContCargaBD.getInstance();
    culturarteFabrica cF = culturarteFabrica.getInstance();

    /**
     * Creates new form configuracionCulturarte
     */
    public configuracionCulturarte() {
        initComponents();
        this.setSize(new Dimension(670, 600));
        cargarPropLogica();
        bloquearTxt();
        jF.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_wscname = new javax.swing.JTextField();
        txt_wscip = new javax.swing.JTextField();
        txt_wscpuerto = new javax.swing.JTextField();
        txt_wspname = new javax.swing.JTextField();
        txt_wspip = new javax.swing.JTextField();
        txt_wsppuerto = new javax.swing.JTextField();
        txt_wsuname = new javax.swing.JTextField();
        txt_wsuip = new javax.swing.JTextField();
        txt_wsupuerto = new javax.swing.JTextField();
        txt_database = new javax.swing.JTextField();
        txt_puertodb = new javax.swing.JTextField();
        txt_userdb = new javax.swing.JTextField();
        txt_passwdb = new javax.swing.JTextField();
        txt_hostdb = new javax.swing.JTextField();
        txt_imgUsuIni = new javax.swing.JTextField();
        txt_imgUsuDes = new javax.swing.JTextField();
        txt_imgPropIni = new javax.swing.JTextField();
        txt_imgPropDes = new javax.swing.JTextField();
        btn_imgUsuIni = new javax.swing.JButton();
        btn_imgUsuDes = new javax.swing.JButton();
        btn_imgPropIni = new javax.swing.JButton();
        btn_imgPropDes = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_propWeb = new javax.swing.JButton();
        btn_levantarP = new javax.swing.JButton();

        jLabel10.setText("wscname");

        jLabel1.setText("wscip");

        jLabel11.setText("wscpuerto");

        jLabel12.setText("paswdb");

        jLabel13.setText("userdb");

        jLabel14.setText("imgusudes");

        jLabel15.setText("hostdb");

        jLabel16.setText("imgpropdes");

        jLabel17.setText("puertodb");

        jLabel18.setText("wsupuerto");

        jLabel19.setText("wspname");

        jLabel20.setText("wspip");

        jLabel21.setText("wsppuerto");

        jLabel22.setText("imgusuini");

        jLabel25.setText("imgpropini");

        jLabel26.setText("database");

        jLabel27.setText("wsuname");

        jLabel28.setText("wsuip");

        btn_imgUsuIni.setText("Buscar");
        btn_imgUsuIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imgUsuIniActionPerformed(evt);
            }
        });

        btn_imgUsuDes.setText("Buscar");
        btn_imgUsuDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imgUsuDesActionPerformed(evt);
            }
        });

        btn_imgPropIni.setText("Buscar");
        btn_imgPropIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imgPropIniActionPerformed(evt);
            }
        });

        btn_imgPropDes.setText("Buscar");
        btn_imgPropDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imgPropDesActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_propWeb.setText("Generar Properties Web");
        btn_propWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_propWebActionPerformed(evt);
            }
        });

        btn_levantarP.setText("Cargar Archivo");
        btn_levantarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_levantarPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(14, 14, 14)
                            .addComponent(txt_wscname, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel22)
                            .addGap(22, 22, 22)
                            .addComponent(txt_imgUsuIni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(btn_imgUsuIni))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(40, 40, 40)
                            .addComponent(txt_wscip, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel14)
                            .addGap(13, 13, 13)
                            .addComponent(txt_imgUsuDes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(btn_imgUsuDes))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(5, 5, 5)
                            .addComponent(txt_wscpuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel25)
                            .addGap(15, 15, 15)
                            .addComponent(txt_imgPropIni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(btn_imgPropIni))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addGap(12, 12, 12)
                            .addComponent(txt_wspname, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel16)
                            .addGap(6, 6, 6)
                            .addComponent(txt_imgPropDes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(btn_imgPropDes))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21))
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_wspip, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_wsppuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addComponent(btn_editar)
                            .addGap(24, 24, 24)
                            .addComponent(btn_levantarP))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(12, 12, 12)
                            .addComponent(txt_wsuname, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addGap(38, 38, 38)
                            .addComponent(txt_wsuip, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(3, 3, 3)
                            .addComponent(txt_wsupuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(12, 12, 12)
                            .addComponent(txt_database, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(14, 14, 14)
                            .addComponent(txt_puertodb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(30, 30, 30)
                            .addComponent(txt_userdb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(24, 24, 24)
                            .addComponent(txt_passwdb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(30, 30, 30)
                            .addComponent(txt_hostdb, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(btn_propWeb)
                            .addGap(13, 13, 13)
                            .addComponent(btn_aceptar)
                            .addGap(11, 11, 11)
                            .addComponent(btn_cancelar)))
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(txt_wscname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txt_imgUsuIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_imgUsuIni))
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(txt_wscip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_imgUsuDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_imgUsuDes))
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(txt_wscpuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(txt_imgPropIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_imgPropIni))
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19)
                        .addComponent(txt_wspname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(txt_imgPropDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_imgPropDes))
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(15, 15, 15)
                            .addComponent(jLabel21))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txt_wspip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(txt_wsppuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_editar)
                                .addComponent(btn_levantarP))))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel27)
                        .addComponent(txt_wsuname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28)
                        .addComponent(txt_wsuip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(txt_wsupuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26)
                        .addComponent(txt_database, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(txt_puertodb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(txt_userdb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(txt_passwdb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_propWeb)
                        .addComponent(btn_aceptar)
                        .addComponent(btn_cancelar)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(txt_hostdb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        desbloquearTxt();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_levantarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_levantarPActionPerformed
        jFProp.setFileFilter(filter);
        jFProp.showOpenDialog(this);
        HashMap<String, String> nuevaLista = (HashMap<String, String>) contCarga.levantarYCargar((String) jFProp.getSelectedFile().getPath());
        if (!nuevaLista.isEmpty()) {
            btn_aceptar.setEnabled(true);
            listarNuevo(nuevaLista);
        } else {
            cargarPropLogica();
            JOptionPane.showMessageDialog(this, "Archivo Properties invalido");
        }


    }//GEN-LAST:event_btn_levantarPActionPerformed

    private void btn_imgUsuIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imgUsuIniActionPerformed
        jF.showOpenDialog(this);
        txt_imgUsuIni.setText(jF.getSelectedFile().getPath());        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imgUsuIniActionPerformed

    private void btn_imgUsuDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imgUsuDesActionPerformed
        jF.showOpenDialog(this);
        txt_imgUsuDes.setText(jF.getSelectedFile().getPath());        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imgUsuDesActionPerformed

    private void btn_imgPropIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imgPropIniActionPerformed
        jF.showOpenDialog(this);
        txt_imgPropIni.setText(jF.getSelectedFile().getPath());        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imgPropIniActionPerformed

    private void btn_imgPropDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imgPropDesActionPerformed
        jF.showOpenDialog(this);
        txt_imgPropDes.setText(jF.getSelectedFile().getPath());        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imgPropDesActionPerformed

    private void btn_propWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_propWebActionPerformed
        jF.showOpenDialog(this);
        String oath = jF.getSelectedFile().getPath();
        contCarga.crearPropertiesWebapp(oath);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_propWebActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        boolean setearNuevosAtributos = setearNuevosAtributos();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_aceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_imgPropDes;
    private javax.swing.JButton btn_imgPropIni;
    private javax.swing.JButton btn_imgUsuDes;
    private javax.swing.JButton btn_imgUsuIni;
    private javax.swing.JButton btn_levantarP;
    private javax.swing.JButton btn_propWeb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JTextField txt_database;
    private javax.swing.JTextField txt_hostdb;
    private javax.swing.JTextField txt_imgPropDes;
    private javax.swing.JTextField txt_imgPropIni;
    private javax.swing.JTextField txt_imgUsuDes;
    private javax.swing.JTextField txt_imgUsuIni;
    private javax.swing.JTextField txt_passwdb;
    private javax.swing.JTextField txt_puertodb;
    private javax.swing.JTextField txt_userdb;
    private javax.swing.JTextField txt_wscip;
    private javax.swing.JTextField txt_wscname;
    private javax.swing.JTextField txt_wscpuerto;
    private javax.swing.JTextField txt_wspip;
    private javax.swing.JTextField txt_wspname;
    private javax.swing.JTextField txt_wsppuerto;
    private javax.swing.JTextField txt_wsuip;
    private javax.swing.JTextField txt_wsuname;
    private javax.swing.JTextField txt_wsupuerto;
    // End of variables declaration//GEN-END:variables

    private void cargarPropLogica() {
        configProperties.clear();
        configProperties = (HashMap<String, String>) contCarga.getPropiedadesString();
        txt_database.setText(configProperties.get("database"));
        txt_hostdb.setText(configProperties.get("hostdb"));
        txt_puertodb.setText(configProperties.get("puertodb"));
        txt_userdb.setText(configProperties.get("userdb"));
        txt_passwdb.setText(configProperties.get("paswdb"));
        txt_wsuname.setText(configProperties.get("wsuname"));
        txt_wsuip.setText(configProperties.get("wsuip"));
        txt_wsupuerto.setText(configProperties.get("wsupuerto"));
        txt_wspname.setText(configProperties.get("wspname"));
        txt_wspip.setText(configProperties.get("wspip"));
        txt_wsppuerto.setText(configProperties.get("wsppuerto"));
        txt_wscname.setText(configProperties.get("wscname"));
        txt_wscip.setText(configProperties.get("wscip"));
        txt_wscpuerto.setText(configProperties.get("wscpuerto"));
        txt_imgUsuIni.setText(configProperties.get("imgusuini"));
        txt_imgUsuDes.setText(configProperties.get("imgusudes"));
        txt_imgPropIni.setText(configProperties.get("imgpropini"));
        txt_imgPropDes.setText(configProperties.get("imgpropdes"));
    }

    private void listarNuevo(HashMap<String, String> prop) {
        txt_database.setText(prop.get("database"));
        txt_hostdb.setText(prop.get("hostdb"));
        txt_puertodb.setText(prop.get("puertodb"));
        txt_userdb.setText(prop.get("userdb"));
        txt_passwdb.setText(prop.get("paswdb"));
        txt_wsuname.setText(prop.get("wsuname"));
        txt_wsuip.setText(prop.get("wsuip"));
        txt_wsupuerto.setText(prop.get("wsupuerto"));
        txt_wspname.setText(prop.get("wspname"));
        txt_wspip.setText(prop.get("wspip"));
        txt_wsppuerto.setText(prop.get("wsppuerto"));
        txt_wscname.setText(prop.get("wscname"));
        txt_wscip.setText(prop.get("wscip"));
        txt_wscpuerto.setText(prop.get("wscpuerto"));
        txt_imgUsuIni.setText(prop.get("imgusuini"));
        txt_imgUsuDes.setText(prop.get("imgusudes"));
        txt_imgPropIni.setText(prop.get("imgpropini"));
        txt_imgPropDes.setText(prop.get("imgpropdes"));
    }

    private void bloquearTxt() {
        btn_aceptar.setEnabled(false);
        btn_imgPropDes.setEnabled(false);
        btn_imgPropIni.setEnabled(false);
        btn_imgUsuDes.setEnabled(false);
        btn_imgUsuIni.setEnabled(false);
        txt_database.setEditable(false);
        txt_hostdb.setEditable(false);
        txt_puertodb.setEditable(false);
        txt_userdb.setEditable(false);
        txt_passwdb.setEditable(false);
        txt_wsuname.setEditable(false);
        txt_wsuip.setEditable(false);
        txt_wsupuerto.setEditable(false);
        txt_wspip.setEditable(false);
        txt_wspname.setEditable(false);
        txt_wsppuerto.setEditable(false);
        txt_wscip.setEditable(false);
        txt_wscname.setEditable(false);
        txt_wscpuerto.setEditable(false);
        txt_imgUsuIni.setEditable(false);
        txt_imgUsuDes.setEditable(false);
        txt_imgPropIni.setEditable(false);
        txt_imgPropDes.setEditable(false);
    }

    private void desbloquearTxt() {
        btn_aceptar.setEnabled(true);
        btn_imgPropDes.setEnabled(true);
        btn_imgPropIni.setEnabled(true);
        btn_imgUsuDes.setEnabled(true);
        btn_imgUsuIni.setEnabled(true);
        txt_database.setEditable(true);
        txt_hostdb.setEditable(true);
        txt_puertodb.setEditable(true);
        txt_userdb.setEditable(true);
        txt_passwdb.setEditable(true);
        txt_wsuname.setEditable(true);
        txt_wsuip.setEditable(true);
        txt_wsupuerto.setEditable(true);
        txt_wspip.setEditable(true);
        txt_wspname.setEditable(true);
        txt_wsppuerto.setEditable(true);
        txt_wscip.setEditable(true);
        txt_wscname.setEditable(true);
        txt_wscpuerto.setEditable(true);
        txt_imgUsuIni.setEditable(true);
        txt_imgUsuDes.setEditable(true);
        txt_imgPropIni.setEditable(true);
        txt_imgPropDes.setEditable(true);
    }

    private boolean setearNuevosAtributos() {
        if (!verficaCambio("database", txt_database.getText())) {
            configProperties.replace("database", txt_database.getText());
        }

        if (!verficaCambio("hostdb", txt_hostdb.getText())) {
            configProperties.replace("hostdb", txt_hostdb.getText());
        }

        if (!verficaCambio("paswdb", txt_passwdb.getText())) {
            configProperties.replace("paswdb", txt_passwdb.getText());
        }

        if (!verficaCambio("puertodb", txt_puertodb.getText())) {
            configProperties.replace("puertodb", txt_puertodb.getText());
        }

        if (!verficaCambio("userdb", txt_userdb.getText())) {
            configProperties.replace("userdb", txt_userdb.getText());
        }

        if (!verficaCambio("wscip", txt_wscip.getText())) {
            configProperties.replace("wscip", txt_wscip.getText());
        }

        if (!verficaCambio("wscname", txt_wscname.getText())) {
            configProperties.replace("wscname", txt_wscname.getText());
        }

        if (!verficaCambio("wscpuerto", txt_wscpuerto.getText())) {
            configProperties.replace("wscpuerto", txt_wscpuerto.getText());
        }

        if (!verficaCambio("wspip", txt_wspip.getText())) {
            configProperties.replace("wspip", txt_wspip.getText());
        }

        if (!verficaCambio("wspname", txt_wspname.getText())) {
            configProperties.replace("wspname", txt_wspname.getText());
        }

        if (!verficaCambio("wsppuerto", txt_wsppuerto.getText())) {
            configProperties.replace("wsppuerto", txt_wsppuerto.getText());
        }

        if (!verficaCambio("wsuip", txt_wsuip.getText())) {
            configProperties.replace("wsuip", txt_wsuip.getText());
        }

        if (!verficaCambio("wsuname", txt_wsuname.getText())) {
            configProperties.replace("wsuname", txt_wsuname.getText());
        }

        if (!verficaCambio("wsupuerto", txt_wsupuerto.getText())) {
            configProperties.replace("wsupuerto", txt_wsupuerto.getText());
        }
        if (!verficaCambio("imgusudes", txt_imgUsuDes.getText())) {
            configProperties.replace("imgusudes", txt_imgUsuDes.getText());
        }
        if (!verficaCambio("imgusuini", txt_imgUsuIni.getText())) {
            configProperties.replace("imgusuini", txt_imgUsuIni.getText());
        }
        if (!verficaCambio("imgpropdes", txt_imgPropDes.getText())) {
            configProperties.replace("imgpropdes", txt_imgPropDes.getText());
        }
        if (!verficaCambio("imgpropini", txt_imgPropIni.getText())) {
            configProperties.replace("imgpropini", txt_imgPropIni.getText());
        }
        if (contCarga.recibeDiccPropiedades(configProperties)) {
            cF.rearmar();
            bloquearTxt();
            btn_aceptar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Se a cargado con exito la nueva configuracion...");

        }

        return true;
    }

    private boolean verficaCambio(String key, String value) {
        System.out.println("verificaCambio...");
        String atri = (String) configProperties.get(key);
        return atri.equals(value);
    }
}
