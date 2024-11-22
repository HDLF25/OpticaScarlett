package GUI;

import Otros.Conexion;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PayMethods extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    DefaultTableModel Search = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public PayMethods(int idUsuario) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn();
        DeshabilitarTxt();
        CabeceraTabla();
        CargarCboxStatus();
        CheckUserPermissions(idUsuario);
    }

    private void HabilitarBtn() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void DeshabilitarBtn() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnErase.setEnabled(true);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void DeshabilitarMainBtn() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(false);
    }

    private void HabilitarTxt() {
        txtidMethod.setEnabled(true);
        txtMethod.setEnabled(true);
        cboxStatus.setEnabled(true);
    }

    private void DeshabilitarTxt() {
        txtidMethod.setEnabled(false);
        txtMethod.setEnabled(false);
        cboxStatus.setEnabled(false);
    }

    private void LimpiarTxt() {
        txtidMethod.setText("");
        txtMethod.setText("");
    }
    
    private void CheckUserPermissions(int idUser) throws SQLException {
        DeshabilitarMainBtn();
        String SQLUserCheck = "SELECT up.id_permission, per.name_permission, up.active FROM users_permissions up, permissions per WHERE up.id_permission = per.id_permission AND up.id_permission IN (31,32,33,34) AND id_usuario = " + idUser + ";";
        rs = con.Results(SQLUserCheck);
        while (rs.next()) {
            int idPermission = rs.getInt("id_permission");
            String namePermission = rs.getString("name_permission");
            Boolean isActive = rs.getBoolean("active");
            if (isActive) {
                switch (namePermission) {
                    case "PayCreate":
                        btnAdd.setEnabled(true);
                        break;
                    case "PayEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "PayDelete":
                        btnErase.setEnabled(true);
                        break;
                    case "PayList":
                        btnSearch.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso desconocido: " + idPermission + ", " + namePermission);
                }
            }
        }
    }

    private void NewPayMethod() throws SQLException {
        String ExistMethod = "Select * from paymethod";
        rs = con.Results(ExistMethod);
        if (rs.next()) {
            String NoMethod = "Select last_value+1 as nromethod from paymethod_id_paymethod_seq";
            rs = con.Results(NoMethod);
            if (rs.next()) {
                txtidMethod.setText(rs.getString("nromethod"));
            }
        } else {
            String NoMethod = "Select last_value as nromethod from paymethod_id_paymethod_seq";
            rs = con.Results(NoMethod);
            if (rs.next()) {
                txtidMethod.setText(rs.getString("nromethod"));
            }
        }
    }

    private void GuardarDatos() {
        String paymethod = txtMethod.getText();
        if (Flag == 1) {
            con.InsertarDatos("paymethod", "descr_paymethod, id_estado", "'" + paymethod + "'," + cboxStatus.getSelectedIndex());
        } else if (Flag == 2) {
            con.EditarDatos("paymethod", "descr_paymethod='" + paymethod + "'", "id_paymethod=" + txtidMethod.getText());
        } else if (Flag == 3) {
            con.BorrarDatos("paymethod", "id_paymethod=" + txtidMethod.getText());
        }
    }

    private void Recuperar(int id) throws SQLException {
        String SQL_Recuperar = "Select * from paymethod where id_paymethod = " + String.valueOf(id);
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtMethod.setText(rs.getString("descr_paymethod"));
            cboxStatus.setSelectedIndex(rs.getInt("id_estado"));
            txtMethod.setEnabled(true);
            txtMethod.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void CabeceraTabla() {
        Search.addColumn("ID");
        Search.addColumn("Método de Pago");
        Search.addColumn("Estado");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(50);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(120);
        TSearcher.getColumnModel().getColumn(2).setPreferredWidth(80);
    }

    private void LoadTable() throws SQLException {
        rs = con.Results("Select pm.id_paymethod, pm.descr_paymethod, es.estado from paymethod pm, estado es where pm.id_estado = es.id_estado");
        Search.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[3];
            row[0] = rs.getString("id_paymethod");
            row[1] = rs.getString("descr_paymethod");
            row[2] = rs.getString("estado");
            Search.addRow(row);
        }
    }

    private Boolean Validaciones() {
        if (txtMethod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo vacío. Ingrese el método de pago que desee registrar.");
            return false;
        }
        return true;
    }

    private void CargarCboxStatus() throws SQLException {
        cboxStatus.removeAllItems();
        cboxStatus.addItem("Selecciona");
        rs = con.Results("Select estado from estado where id_estado between 1 and 2 order by estado asc;");
        while (rs.next()) {
            cboxStatus.addItem(rs.getString("estado"));
        }
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (!cboxStatus.getSelectedItem().toString().equals("Selecciona")) {
                    txtMethod.setEnabled(true);
                    txtMethod.requestFocus();
                }
            }
        };
    }

    private void UserCheck(String ObtUser, String ObtPass) throws SQLException {
        String SQLUser = "select * from usuario where username = '" + ObtUser + "' and password = md5('" + ObtPass + "') and id_estado = 1;";
        rs = con.Results(SQLUser);
        while (rs.next()) {
            if (rs.getBoolean("admin") == true) {
                /*SUConfirm.setVisible(false);*/
                SUConfirm.dispose();
                HabilitarBtn();
                HabilitarTxt();
                txtidMethod.setEditable(true);
                txtidMethod.requestFocus();
                txtUser.setText("");
                txtPass.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ingresado no es un administrador. Inténtalo de nuevo más tarde.");
            }
        }
        if (!rs.next()) {
            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe. Intentalo nuevamente.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Searcher = new javax.swing.JDialog();
        lblMensaje = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TSearcher = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        SUConfirm = new javax.swing.JDialog();
        SUSubtitle = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnOKUser = new javax.swing.JButton();
        btnCancelUser = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnErase = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        lblIdMethod = new javax.swing.JLabel();
        txtidMethod = new javax.swing.JTextField();
        lblMethod = new javax.swing.JLabel();
        txtMethod = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cboxStatus = new javax.swing.JComboBox<>();
        btnConfirmar = new javax.swing.JButton();

        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("null");

        TSearcher.setModel(Search);
        TSearcher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSearcherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TSearcher);

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        lblFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filtrar.png"))); // NOI18N
        lblFiltrar.setText("Filtrar:");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearcherLayout = new javax.swing.GroupLayout(Searcher.getContentPane());
        Searcher.getContentPane().setLayout(SearcherLayout);
        SearcherLayout.setHorizontalGroup(
            SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(SearcherLayout.createSequentialGroup()
                        .addComponent(lblFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 71, Short.MAX_VALUE))
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearcherLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        SearcherLayout.setVerticalGroup(
            SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SUSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SUSubtitle.setText("null");

        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("Usuario");

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        lblPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPass.setText("Contraseña");

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        btnOKUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnOKUser.setText("Continuar");
        btnOKUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKUserActionPerformed(evt);
            }
        });

        btnCancelUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancelUser.setText("Cancelar");
        btnCancelUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SUConfirmLayout = new javax.swing.GroupLayout(SUConfirm.getContentPane());
        SUConfirm.getContentPane().setLayout(SUConfirmLayout);
        SUConfirmLayout.setHorizontalGroup(
            SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SUConfirmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SUSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(SUConfirmLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnOKUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPass)
                    .addComponent(btnCancelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPass, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        SUConfirmLayout.setVerticalGroup(
            SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SUConfirmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SUSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(lblPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SUConfirmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOKUser)
                    .addComponent(btnCancelUser))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setToolTipText("Registrar un nuevo método de pago.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setToolTipText("Editar un método de pago registrado.");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnErase.setToolTipText("Eliminar un método de pago registrado.");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearch.setToolTipText("Listar y buscar métodos de pago.");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblIdMethod.setText("ID");

        txtidMethod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtidMethod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidMethodKeyPressed(evt);
            }
        });

        lblMethod.setText("Método de Pago");

        lblStatus.setText("Estado");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
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
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnErase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIdMethod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMethod)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblStatus)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cboxStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMethod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnErase, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdMethod)
                            .addComponent(txtidMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)))
                    .addComponent(btnEdit)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(lblMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Flag = 1;
            HabilitarBtn();
            NewPayMethod();
            txtMethod.setEnabled(true);
            cboxStatus.setEnabled(true);
            txtMethod.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            Flag = 0;
            LimpiarTxt();
            DeshabilitarBtn();
            DeshabilitarTxt();
            cboxStatus.setSelectedIndex(0);
            CheckUserPermissions(Menu.idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(PayMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        HabilitarBtn();
        HabilitarTxt();
        txtidMethod.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 2;
        SUSubtitle.setText("Ingrese un usuario administrador para continuar con la operación.");
        SUConfirm.setTitle("Atención: Acción requerida");
        SUConfirm.setModal(true);
        SUConfirm.pack();
        SUConfirm.setResizable(false);
        SUConfirm.setLocationRelativeTo(null);
        SUConfirm.setVisible(true);
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Métodos de Pago");
            if (Flag == 2) {
                lblMensaje.setText("Seleccione una método de pago que desee modificar");
            } else if (Flag == 3) {
                lblMensaje.setText("Seleccione una método de pago que desee eliminar");
            }
            Searcher.setTitle("Buscar Métodos de Pago");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtidMethodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidMethodKeyPressed
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
            txtidMethod.setText("");
        }
        if (evt.getKeyCode() == 10 && !txtidMethod.getText().equals("")) {
            try {
                Recuperar(Integer.parseInt(txtidMethod.getText()));
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de inactivar este método de pago? " + txtMethod.getText().toString(), "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnConfirmar.doClick();
                    } else {
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
            }
        } else if (evt.getKeyCode() == 10 && txtidMethod.getText().equals("")) {
            try {
                LoadTable();
                if (Flag == 2) {
                    lblMensaje.setText("Seleccione una ciudad que desee modificar");
                } else if (Flag == 3) {
                    lblMensaje.setText("Seleccione una ciudad que desee eliminar");
                }
                Searcher.setTitle("Buscar Ciudad");
                Searcher.setModal(true);
                Searcher.pack();
                Searcher.setResizable(false);
                Searcher.setLocationRelativeTo(null);
                Searcher.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtidMethodKeyPressed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validaciones() == true) {
            try {
                GuardarDatos();
                LimpiarTxt();
                DeshabilitarTxt();
                DeshabilitarBtn();
                CheckUserPermissions(Menu.idUsuario);
                Flag = 0;
            } catch (SQLException ex) {
                Logger.getLogger(PayMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                Searcher.setModal(false);
                Searcher.setVisible(false);
                txtidMethod.setText(id);
                Recuperar(Integer.parseInt(txtidMethod.getText()));
                if (Flag == 3) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar '" + txtMethod.getText().toString() + "'?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        btnConfirmar.doClick();
                    } else {
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            String SQL_Search = "select pm.id_paymethod, pm.descr_paymethod, es.estado from paymethod pm, estado es where pm.id_estado = es.id_estado and descr_paymethod ilike '%" + txtFilter.getText() + "%'";
            rs = con.Results(SQL_Search);
            Search.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("id_paymethod");
                row[1] = rs.getString("descr_paymethod");
                row[2] = rs.getString("estado");
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnOKUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKUserActionPerformed
        String UserName = txtUser.getText().toString();
        //String Password = txtPass.getText().toString();
        char[] passwordArray = txtPass.getPassword();
        String Password = new String(passwordArray);
        java.util.Arrays.fill(passwordArray, ' '); // Borra la contraseña del array para mayor seguridad
        if (Flag == 2) {
            try {
                UserCheck(UserName, Password);
                /*Falta comprobación de usuario*/
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Flag == 3) {
            /*Falta comprobación de usuario*/
            Flag = 3;
            HabilitarBtn();
            HabilitarTxt();
            txtidMethod.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado.");
        }

    }//GEN-LAST:event_btnOKUserActionPerformed

    private void btnCancelUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUserActionPerformed
        SUConfirm.setVisible(false);
    }//GEN-LAST:event_btnCancelUserActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == 10 && !txtUser.getText().equals("")) {
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        char[] passwordArray = txtPass.getPassword();
        String password = new String(passwordArray);
        java.util.Arrays.fill(passwordArray, ' '); // Borra la contraseña del array para mayor seguridad
        if (evt.getKeyCode() == 10 && password != "") {
            btnOKUser.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog SUConfirm;
    private javax.swing.JLabel SUSubtitle;
    private javax.swing.JDialog Searcher;
    private javax.swing.JTable TSearcher;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelUser;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnOKUser;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblIdMethod;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMethod;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtMethod;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtidMethod;
    // End of variables declaration//GEN-END:variables
}
