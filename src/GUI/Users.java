package GUI;

import Otros.Conexion;
import java.sql.ResultSet;

public class Users extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;

    public Users() {
        initComponents();
        EnableBtns();
        EnableActionBtns();
        DisableAllValues();
    }

    private void EnableBtns(){
        btnCreate.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnDisable.setEnabled(true);
        btnEnable.setEnabled(true);
        btnChangePass.setEnabled(true);
    }
    
    private void DisableBtns(){
        btnCreate.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisable.setEnabled(false);
        btnEnable.setEnabled(false);
        btnChangePass.setEnabled(false);
    }
    
    private void EnableActionBtns(){
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
    }
    
    private void DisableActionBtns(){
        btnConfirm.setEnabled(false);
        btnCancel.setEnabled(false);
    }
    
    private void EnableAllValues(){
        txtIDUser.setEnabled(true);
        txtUsername.setEnabled(true);
        txtFullname.setEnabled(true);
        txtCIUser.setEnabled(true);
        txtPassCurr.setEnabled(true);
        txtPassNew.setEnabled(true);
        txtPassConfirm.setEnabled(true);
        chbAdmin.setEnabled(true);
        chbFichaMain.setEnabled(true);
        chbFicha1.setEnabled(true);
        chbFicha2.setEnabled(true);
        chbFicha3.setEnabled(true);
        chbFicha4.setEnabled(true);
        chbCliMain.setEnabled(true);
        chbCli1.setEnabled(true);
        chbCli2.setEnabled(true);
        chbCli3.setEnabled(true);
        chbCli4.setEnabled(true);
        chbArtMain.setEnabled(true);
        chbArt1.setEnabled(true);
        chbArt2.setEnabled(true);
        chbArt3.setEnabled(true);
        chbArt4.setEnabled(true);
        chbStockMain.setEnabled(true);
        chbStock1.setEnabled(true);
        chbStock2.setEnabled(true);
        chbStock3.setEnabled(true);
        chbRepFicha.setEnabled(true);
        chbResSales.setEnabled(true);
        chbRepStock.setEnabled(true);
        chbCityMain.setEnabled(true);
        chbCity1.setEnabled(true);
        chbCity2.setEnabled(true);
        chbCity3.setEnabled(true);
        chbCity4.setEnabled(true);
        chbBrandMain.setEnabled(true);
        chbBrand1.setEnabled(true);
        chbBrand2.setEnabled(true);
        chbBrand3.setEnabled(true);
        chbBrand4.setEnabled(true);
        chbMethodMain.setEnabled(true);
        chbMethod1.setEnabled(true);
        chbMethod2.setEnabled(true);
        chbMethod3.setEnabled(true);
        chbMethod4.setEnabled(true);
    }
    
    private void DisableAllValues(){
        txtIDUser.setEnabled(false);
        txtUsername.setEnabled(false);
        txtFullname.setEnabled(false);
        txtCIUser.setEnabled(false);
        txtPassCurr.setEnabled(false);
        txtPassNew.setEnabled(false);
        txtPassConfirm.setEnabled(false);
        chbAdmin.setEnabled(false);
        chbFichaMain.setEnabled(false);
        chbFicha1.setEnabled(false);
        chbFicha2.setEnabled(false);
        chbFicha3.setEnabled(false);
        chbFicha4.setEnabled(false);
        chbCliMain.setEnabled(false);
        chbCli1.setEnabled(false);
        chbCli2.setEnabled(false);
        chbCli3.setEnabled(false);
        chbCli4.setEnabled(false);
        chbArtMain.setEnabled(false);
        chbArt1.setEnabled(false);
        chbArt2.setEnabled(false);
        chbArt3.setEnabled(false);
        chbArt4.setEnabled(false);
        chbStockMain.setEnabled(false);
        chbStock1.setEnabled(false);
        chbStock2.setEnabled(false);
        chbStock3.setEnabled(false);
        chbRepFicha.setEnabled(false);
        chbResSales.setEnabled(false);
        chbRepStock.setEnabled(false);
        chbCityMain.setEnabled(false);
        chbCity1.setEnabled(false);
        chbCity2.setEnabled(false);
        chbCity3.setEnabled(false);
        chbCity4.setEnabled(false);
        chbBrandMain.setEnabled(false);
        chbBrand1.setEnabled(false);
        chbBrand2.setEnabled(false);
        chbBrand3.setEnabled(false);
        chbBrand4.setEnabled(false);
        chbMethodMain.setEnabled(false);
        chbMethod1.setEnabled(false);
        chbMethod2.setEnabled(false);
        chbMethod3.setEnabled(false);
        chbMethod4.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnDisable = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnEnable = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblUserInfo = new javax.swing.JLabel();
        lblIDUser = new javax.swing.JLabel();
        txtIDUser = new javax.swing.JTextField();
        btnListUser = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblFullname = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        lblCIUser = new javax.swing.JLabel();
        txtCIUser = new javax.swing.JTextField();
        lblPassCurr = new javax.swing.JLabel();
        txtPassCurr = new javax.swing.JPasswordField();
        lblPassNew = new javax.swing.JLabel();
        txtPassNew = new javax.swing.JPasswordField();
        lblPassConfirm = new javax.swing.JLabel();
        txtPassConfirm = new javax.swing.JPasswordField();
        lblAdmin = new javax.swing.JLabel();
        chbAdmin = new javax.swing.JCheckBox();
        dlgUser = new javax.swing.JLabel();
        dlgPass = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPermissions = new javax.swing.JLabel();
        chbFichaMain = new javax.swing.JCheckBox();
        chbFicha1 = new javax.swing.JCheckBox();
        chbFicha2 = new javax.swing.JCheckBox();
        chbFicha3 = new javax.swing.JCheckBox();
        chbFicha4 = new javax.swing.JCheckBox();
        chbCliMain = new javax.swing.JCheckBox();
        chbCli1 = new javax.swing.JCheckBox();
        chbCli2 = new javax.swing.JCheckBox();
        chbCli3 = new javax.swing.JCheckBox();
        chbCli4 = new javax.swing.JCheckBox();
        chbArtMain = new javax.swing.JCheckBox();
        chbArt1 = new javax.swing.JCheckBox();
        chbArt2 = new javax.swing.JCheckBox();
        chbArt3 = new javax.swing.JCheckBox();
        chbArt4 = new javax.swing.JCheckBox();
        chbStockMain = new javax.swing.JCheckBox();
        chbStock1 = new javax.swing.JCheckBox();
        chbStock2 = new javax.swing.JCheckBox();
        chbStock3 = new javax.swing.JCheckBox();
        chbRepFicha = new javax.swing.JCheckBox();
        chbResSales = new javax.swing.JCheckBox();
        chbRepStock = new javax.swing.JCheckBox();
        chbCityMain = new javax.swing.JCheckBox();
        chbCity1 = new javax.swing.JCheckBox();
        chbCity2 = new javax.swing.JCheckBox();
        chbCity3 = new javax.swing.JCheckBox();
        chbCity4 = new javax.swing.JCheckBox();
        chbBrandMain = new javax.swing.JCheckBox();
        chbBrand1 = new javax.swing.JCheckBox();
        chbBrand2 = new javax.swing.JCheckBox();
        chbBrand3 = new javax.swing.JCheckBox();
        chbBrand4 = new javax.swing.JCheckBox();
        chbMethodMain = new javax.swing.JCheckBox();
        chbMethod1 = new javax.swing.JCheckBox();
        chbMethod2 = new javax.swing.JCheckBox();
        chbMethod3 = new javax.swing.JCheckBox();
        chbMethod4 = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnCreate.setText("Crear");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDisable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnDisable.setText("Deshabilitar");
        btnDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisableActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnEnable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnEnable.setText("Habilitar");
        btnEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnableActionPerformed(evt);
            }
        });

        btnChangePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contrasena.png"))); // NOI18N
        btnChangePass.setText("Cambiar Contraseña");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangePass))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEnable))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnCreate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDisable))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUserInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUserInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserInfo.setText("Datos Generales");

        lblIDUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDUser.setText("ID Usuario");

        btnListUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListUserActionPerformed(evt);
            }
        });

        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Nombre de Usuario");

        lblFullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFullname.setText("Nombre Completo");

        lblCIUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCIUser.setText("Cédula N°");

        lblPassCurr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassCurr.setText("Contraseña Actual");

        lblPassNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassNew.setText("Contraseña");

        lblPassConfirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassConfirm.setText("Confirmar Contraseña");

        lblAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmin.setText("Administrador");

        chbAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPermissions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPermissions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPermissions.setText("Permisos");

        chbFichaMain.setText("Ficha de Cliente");
        chbFichaMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFichaMainActionPerformed(evt);
            }
        });

        chbFicha1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha1.setText("Crear OT");

        chbFicha2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha2.setText("Editar OT");

        chbFicha3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha3.setText("Anular OT");

        chbFicha4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha4.setText("Cerrar OT");

        chbCliMain.setText("Clientes");
        chbCliMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbCliMainActionPerformed(evt);
            }
        });

        chbCli1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli1.setText("Crear Cliente");

        chbCli2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli2.setText("Editar Cliente");

        chbCli3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli3.setText("Eliminar Cliente");

        chbCli4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli4.setText("Listar Clientes");

        chbArtMain.setText("Artículo");
        chbArtMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbArtMainActionPerformed(evt);
            }
        });

        chbArt1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt1.setText("Crear Artículo");

        chbArt2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt2.setText("Editar Artículo");

        chbArt3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt3.setText("Eliminar Artículo");

        chbArt4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt4.setText("Listar Artículos");

        chbStockMain.setText("Stock de Artículos");
        chbStockMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbStockMainActionPerformed(evt);
            }
        });

        chbStock1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock1.setText("Añadir Stock");

        chbStock2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock2.setText("Restar Stock");

        chbStock3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock3.setText("Listar Artículos");

        chbRepFicha.setText("Buscar Ficha/OT");

        chbResSales.setText("Resumen de Ventas");

        chbRepStock.setText("Reporte de Existencia");

        chbCityMain.setText("Ciudades");
        chbCityMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbCityMainActionPerformed(evt);
            }
        });

        chbCity1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity1.setText("Añadir Ciudad");

        chbCity2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity2.setText("Editar Ciudad");

        chbCity3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity3.setText("Eliminar Ciudad");

        chbCity4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity4.setText("Listar Ciudades");

        chbBrandMain.setText("Marcas");
        chbBrandMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbBrandMainActionPerformed(evt);
            }
        });

        chbBrand1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand1.setText("Añadir Marca");

        chbBrand2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand2.setText("Editar Marca");

        chbBrand3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand3.setText("Eliminar Marca");

        chbBrand4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand4.setText("Listar Marcas");

        chbMethodMain.setText("Métodos de Pago");
        chbMethodMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMethodMainActionPerformed(evt);
            }
        });

        chbMethod1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod1.setText("Añadir Método");

        chbMethod2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod2.setText("Editar Método");

        chbMethod3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod3.setText("Eliminar Método");

        chbMethod4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod4.setText("Listar Métodos");

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirm.setText("Confirmar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(lblPermissions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(dlgPass, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dlgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbFicha1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFichaMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbCli1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCliMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbArt1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArtMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStockMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStock2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStock3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbRepFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbResSales, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbRepStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbCity1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCityMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbBrand1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrandMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethodMain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblIDUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIDUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnListUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIDUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnListUser)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsername)
                            .addComponent(lblFullname)
                            .addComponent(lblCIUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dlgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassNew)
                            .addComponent(lblPassConfirm)
                            .addComponent(lblPassCurr)
                            .addComponent(lblAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dlgPass, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPermissions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbFichaMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbCliMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli3)
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbArtMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chbArt4)
                                    .addComponent(chbCli4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbStockMain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock3)
                        .addGap(26, 26, 26)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbRepFicha)
                    .addComponent(chbResSales)
                    .addComponent(chbRepStock))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chbCityMain)
                    .addComponent(chbMethodMain)
                    .addComponent(chbBrandMain))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbCity1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbCity2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbCity3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbCity4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chbBrand4)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(chbBrand1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chbBrand2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chbBrand3)
                            .addGap(26, 26, 26)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbMethod1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbMethod2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbMethod3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbMethod4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnConfirm))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chbFichaMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFichaMainActionPerformed
        if (chbFichaMain.isSelected()) {
            chbFicha1.setEnabled(true);
            chbFicha1.setSelected(true);
            chbFicha2.setEnabled(true);
            chbFicha2.setSelected(true);
            chbFicha3.setEnabled(true);
            chbFicha3.setSelected(true);
            chbFicha4.setEnabled(true);
            chbFicha4.setSelected(true);
        } else {
            chbFicha1.setEnabled(false);
            chbFicha1.setSelected(false);
            chbFicha2.setEnabled(false);
            chbFicha2.setSelected(false);
            chbFicha3.setEnabled(false);
            chbFicha3.setSelected(false);
            chbFicha4.setEnabled(false);
            chbFicha4.setSelected(false);
        }
    }//GEN-LAST:event_chbFichaMainActionPerformed

    private void chbCliMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbCliMainActionPerformed
        if (chbCliMain.isSelected()) {
            chbCli1.setEnabled(true);
            chbCli1.setSelected(true);
            chbCli2.setEnabled(true);
            chbCli2.setSelected(true);
            chbCli3.setEnabled(true);
            chbCli3.setSelected(true);
            chbCli4.setEnabled(true);
            chbCli4.setSelected(true);
        } else {
            chbCli1.setEnabled(false);
            chbCli1.setSelected(false);
            chbCli2.setEnabled(false);
            chbCli2.setSelected(false);
            chbCli3.setEnabled(false);
            chbCli3.setSelected(false);
            chbCli4.setEnabled(false);
            chbCli4.setSelected(false);
        }
    }//GEN-LAST:event_chbCliMainActionPerformed

    private void chbArtMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbArtMainActionPerformed
        if (chbArtMain.isSelected()) {
            chbArt1.setEnabled(true);
            chbArt1.setSelected(true);
            chbArt2.setEnabled(true);
            chbArt2.setSelected(true);
            chbArt3.setEnabled(true);
            chbArt3.setSelected(true);
            chbArt4.setEnabled(true);
            chbArt4.setSelected(true);
        } else {
            chbArt1.setEnabled(false);
            chbArt1.setSelected(false);
            chbArt2.setEnabled(false);
            chbArt2.setSelected(false);
            chbArt3.setEnabled(false);
            chbArt3.setSelected(false);
            chbArt4.setEnabled(false);
            chbArt4.setSelected(false);
        }
    }//GEN-LAST:event_chbArtMainActionPerformed

    private void chbStockMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbStockMainActionPerformed
        if (chbStockMain.isSelected()) {
            chbStock1.setEnabled(true);
            chbStock1.setSelected(true);
            chbStock2.setEnabled(true);
            chbStock2.setSelected(true);
            chbStock3.setEnabled(true);
            chbStock3.setSelected(true);
        } else {
            chbStock1.setEnabled(false);
            chbStock1.setSelected(false);
            chbStock2.setEnabled(false);
            chbStock2.setSelected(false);
            chbStock3.setEnabled(false);
            chbStock3.setSelected(false);
        }
    }//GEN-LAST:event_chbStockMainActionPerformed

    private void chbCityMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbCityMainActionPerformed
        if (chbCityMain.isSelected()) {
            chbCity1.setEnabled(true);
            chbCity1.setSelected(true);
            chbCity2.setEnabled(true);
            chbCity2.setSelected(true);
            chbCity3.setEnabled(true);
            chbCity3.setSelected(true);
            chbCity4.setEnabled(true);
            chbCity4.setSelected(true);
        } else {
            chbCity1.setEnabled(false);
            chbCity1.setSelected(false);
            chbCity2.setEnabled(false);
            chbCity2.setSelected(false);
            chbCity3.setEnabled(false);
            chbCity3.setSelected(false);
            chbCity4.setEnabled(false);
            chbCity4.setSelected(false);
        }
    }//GEN-LAST:event_chbCityMainActionPerformed

    private void chbBrandMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbBrandMainActionPerformed
        if (chbBrandMain.isSelected()) {
            chbBrand1.setEnabled(true);
            chbBrand1.setSelected(true);
            chbBrand2.setEnabled(true);
            chbBrand2.setSelected(true);
            chbBrand3.setEnabled(true);
            chbBrand3.setSelected(true);
            chbBrand4.setEnabled(true);
            chbBrand4.setSelected(true);
        } else {
            chbBrand1.setEnabled(false);
            chbBrand1.setSelected(false);
            chbBrand2.setEnabled(false);
            chbBrand2.setSelected(false);
            chbBrand3.setEnabled(false);
            chbBrand3.setSelected(false);
            chbBrand4.setEnabled(false);
            chbBrand4.setSelected(false);
        }
    }//GEN-LAST:event_chbBrandMainActionPerformed

    private void chbMethodMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMethodMainActionPerformed
        if (chbMethodMain.isSelected()) {
            chbMethod1.setEnabled(true);
            chbMethod1.setSelected(true);
            chbMethod2.setEnabled(true);
            chbMethod2.setSelected(true);
            chbMethod3.setEnabled(true);
            chbMethod3.setSelected(true);
            chbMethod4.setEnabled(true);
            chbMethod4.setSelected(true);
        } else {
            chbMethod1.setEnabled(false);
            chbMethod1.setSelected(false);
            chbMethod2.setEnabled(false);
            chbMethod2.setSelected(false);
            chbMethod3.setEnabled(false);
            chbMethod3.setSelected(false);
            chbMethod4.setEnabled(false);
            chbMethod4.setSelected(false);
        }
    }//GEN-LAST:event_chbMethodMainActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        Flag = 1;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Creación de Usuario");
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Edición de Usuario");
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Flag = 3;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Eliminación de Usuario");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisableActionPerformed
        Flag = 4;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Deshabilitación de Usuario");
    }//GEN-LAST:event_btnDisableActionPerformed

    private void btnEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnableActionPerformed
        Flag = 5;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Habilitación de Usuario");
    }//GEN-LAST:event_btnEnableActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        Flag = 6;
        DisableBtns();
        lblUserInfo.setText("Datos Generales - Cambio de Contraseña");
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Flag = 0;
        EnableBtns();
        lblUserInfo.setText("Datos Generales");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // Procesar Datos
        EnableBtns();
        lblUserInfo.setText("Datos Generales");
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnListUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDisable;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEnable;
    private javax.swing.JButton btnListUser;
    private javax.swing.JCheckBox chbAdmin;
    private javax.swing.JCheckBox chbArt1;
    private javax.swing.JCheckBox chbArt2;
    private javax.swing.JCheckBox chbArt3;
    private javax.swing.JCheckBox chbArt4;
    private javax.swing.JCheckBox chbArtMain;
    private javax.swing.JCheckBox chbBrand1;
    private javax.swing.JCheckBox chbBrand2;
    private javax.swing.JCheckBox chbBrand3;
    private javax.swing.JCheckBox chbBrand4;
    private javax.swing.JCheckBox chbBrandMain;
    private javax.swing.JCheckBox chbCity1;
    private javax.swing.JCheckBox chbCity2;
    private javax.swing.JCheckBox chbCity3;
    private javax.swing.JCheckBox chbCity4;
    private javax.swing.JCheckBox chbCityMain;
    private javax.swing.JCheckBox chbCli1;
    private javax.swing.JCheckBox chbCli2;
    private javax.swing.JCheckBox chbCli3;
    private javax.swing.JCheckBox chbCli4;
    private javax.swing.JCheckBox chbCliMain;
    private javax.swing.JCheckBox chbFicha1;
    private javax.swing.JCheckBox chbFicha2;
    private javax.swing.JCheckBox chbFicha3;
    private javax.swing.JCheckBox chbFicha4;
    private javax.swing.JCheckBox chbFichaMain;
    private javax.swing.JCheckBox chbMethod1;
    private javax.swing.JCheckBox chbMethod2;
    private javax.swing.JCheckBox chbMethod3;
    private javax.swing.JCheckBox chbMethod4;
    private javax.swing.JCheckBox chbMethodMain;
    private javax.swing.JCheckBox chbRepFicha;
    private javax.swing.JCheckBox chbRepStock;
    private javax.swing.JCheckBox chbResSales;
    private javax.swing.JCheckBox chbStock1;
    private javax.swing.JCheckBox chbStock2;
    private javax.swing.JCheckBox chbStock3;
    private javax.swing.JCheckBox chbStockMain;
    private javax.swing.JLabel dlgPass;
    private javax.swing.JLabel dlgUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblCIUser;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblIDUser;
    private javax.swing.JLabel lblPassConfirm;
    private javax.swing.JLabel lblPassCurr;
    private javax.swing.JLabel lblPassNew;
    private javax.swing.JLabel lblPermissions;
    private javax.swing.JLabel lblUserInfo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtCIUser;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtIDUser;
    private javax.swing.JPasswordField txtPassConfirm;
    private javax.swing.JPasswordField txtPassCurr;
    private javax.swing.JPasswordField txtPassNew;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
