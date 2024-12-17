/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Otros.Conexion;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Ciudad extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    DefaultTableModel Search = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public Ciudad(List<Menu.Permission> permissions) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn();
        DeshabilitarTxt();
        CabeceraTabla();
        CheckUserPermissions(permissions);
    }

    private void HabilitarBtn(){
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void DeshabilitarBtn(){
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnErase.setEnabled(true);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void DeshabilitarMainBtn(){
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(false);
    }

    private void HabilitarTxt(){
        txtidCiudad.setEnabled(true);
        txtCiudad.setEnabled(true);
    }

    private void DeshabilitarTxt(){
        txtidCiudad.setEnabled(false);
        txtCiudad.setEnabled(false);
    }

    private void LimpiarTxt(){
        txtidCiudad.setText("");
        txtCiudad.setText("");
    }
    
    private void CheckUserPermissions(List<Menu.Permission> permissions) throws SQLException {
        DeshabilitarMainBtn();
        for (Menu.Permission permission : permissions) {
            if (permission.isActivePerm()) {
                switch (permission.getNamePerm()) {
                    case "CityCreate":
                        btnAdd.setEnabled(true);
                        break;
                    case "CityEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "CityDelete":
                        btnErase.setEnabled(true);
                        break;
                    case "CityList":
                        btnSearch.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso no utilizado o no concedido: " + permission.getPermID() + ", " + permission.getNamePerm());
                }
            }
        }
        /*
        String SQLUserCheck = "SELECT up.id_permission, per.name_permission, up.active FROM users_permissions up, permissions per WHERE up.id_permission = per.id_permission AND up.id_permission IN (21,22,23,24) AND id_usuario = " + idUser + ";";
        rs = con.Results(SQLUserCheck);
        while (rs.next()) {
            int idPermission = rs.getInt("id_permission");
            String namePermission = rs.getString("name_permission");
            Boolean isActive = rs.getBoolean("active");
            if (isActive) {
                switch (namePermission) {
                    case "CityCreate":
                        btnAdd.setEnabled(true);
                        break;
                    case "CityEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "CityDelete":
                        btnErase.setEnabled(true);
                        break;
                    case "CityList":
                        btnSearch.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso desconocido: " + idPermission + ", " + namePermission);
                }
            }
        }*/
    }
    
    private void NuevaCiudad() throws SQLException{
        String ExisteCiudad = "Select * from ciudad";
        rs = con.Results(ExisteCiudad);
        if (rs.next()) {
            String NroCiudad = "Select last_value+1 as nrociudad from ciudad_id_ciudad_seq";
            rs = con.Results(NroCiudad);
            if (rs.next()) {
                txtidCiudad.setText(rs.getString("nrociudad"));
            }
        } else {
            String NroCiudad = "Select last_value as nrociudad from ciudad_id_ciudad_seq";
            rs = con.Results(NroCiudad);
            if (rs.next()) {
                txtidCiudad.setText(rs.getString("nrociudad"));
            }
        }
    }

    private void GuardarDatos(){
        String ciudad = txtCiudad.getText();
        if (Flag == 1) {
            con.InsertarDatos("ciudad", "ciudad", "'"+ciudad+"'");
        } else if (Flag == 2) {
            con.EditarDatos("ciudad", "ciudad='"+ciudad+"'", "id_ciudad="+txtidCiudad.getText());
        } else if (Flag == 3) {
            con.BorrarDatos("ciudad", "id_ciudad="+txtidCiudad.getText());
        }
    }

    private void Recuperar(int id) throws SQLException{
        String SQL_Recuperar = "Select * from ciudad where id_ciudad = "+String.valueOf(id);
        rs = con.Results(SQL_Recuperar);
        if(rs.next()){
            txtCiudad.setText(rs.getString("ciudad"));
            txtCiudad.setEnabled(true);
            txtCiudad.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }
    private void CabeceraTabla(){
        Search.addColumn("ID");
        Search.addColumn("Ciudad");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(90);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    private void LoadTable() throws SQLException{
        rs = con.Results("Select id_ciudad, ciudad from ciudad");
        Search.setRowCount(0);
        while(rs.next()){
            Object[] row = new Object[2];
            row[0] = rs.getString("id_ciudad");
            row[1] = rs.getString("ciudad");
            Search.addRow(row);
        }
    }
    private Boolean Validaciones(){
        if(txtCiudad.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo vacío. Ingrese ciudad a registrar.");
            return false;
        }
        return true;
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
        lblIdMarca = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        txtidCiudad = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMarca = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        txtCiudad = new javax.swing.JTextField();
        btnErase = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();

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

        lblIdMarca.setText("ID Ciudad");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setToolTipText("Editar una marca registrada.");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        txtidCiudad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtidCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidCiudadKeyPressed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearch.setToolTipText("Listar y buscar marcas.");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblMarca.setText("Nombre Ciudad");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnErase.setToolTipText("Eliminar una marca registrada.");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setToolTipText("Registrar una nueva marca.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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
                        .addComponent(lblIdMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarca)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(lblIdMarca)
                            .addComponent(txtidCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)))
                    .addComponent(btnEdit)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(lblMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        HabilitarBtn();
        HabilitarTxt();
        txtidCiudad.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtidCiudadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidCiudadKeyPressed
        if(!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())){
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
            txtidCiudad.setText("");
        }
        if (evt.getKeyCode() == 10 && !txtidCiudad.getText().equals("")) {
            try{
                Recuperar(Integer.parseInt(txtidCiudad.getText()));
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro?", "Atención!",JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if(resp == 0){
                        btnConfirmar.doClick();
                    }else{
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
            }
        }else if(evt.getKeyCode() == 10 && txtidCiudad.getText().equals("")){
            try {
                LoadTable();
                if(Flag == 2){
                    lblMensaje.setText("Seleccione una ciudad que desee modificar");
                }else if(Flag == 3){
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
    }//GEN-LAST:event_txtidCiudadKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Ciudades");
            if(Flag == 2){
                lblMensaje.setText("Seleccione una ciudad que desee modificar");
            }else if(Flag == 3){
                lblMensaje.setText("Seleccione una ciudad que desee eliminar");
            }
            Searcher.setTitle("Buscar Ciudades");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            Flag = 0;
            LimpiarTxt();
            DeshabilitarBtn();
            DeshabilitarTxt();
            CheckUserPermissions(Menu.permissions);
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validaciones() == true) {
            try {
                GuardarDatos();
                LimpiarTxt();
                DeshabilitarTxt();
                DeshabilitarBtn();
                CheckUserPermissions(Menu.permissions);
                Flag = 0;
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 3;
        HabilitarBtn();
        HabilitarTxt();
        txtidCiudad.requestFocus();
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Flag = 1;
            HabilitarBtn();
            NuevaCiudad();
            txtCiudad.setEnabled(true);
            txtCiudad.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                Searcher.setModal(false);
                Searcher.setVisible(false);
                txtidCiudad.setText(id);
                Recuperar(Integer.parseInt(txtidCiudad.getText()));
                if(Flag == 3){
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0)
                    btnConfirmar.doClick();
                    else
                    btnCancelar.doClick();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            String SQL_Search = "select id_ciudad, ciudad from ciudad where ciudad ilike '%"+txtFilter.getText()+"%'";
            rs = con.Results(SQL_Search);
            Search.setRowCount(0);
            while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rs.getString("id_ciudad");
                row[1] = rs.getString("ciudad");
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Searcher;
    private javax.swing.JTable TSearcher;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblIdMarca;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtidCiudad;
    // End of variables declaration//GEN-END:variables
}
