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

public class Marca extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    DefaultTableModel Search = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public Marca(List<Menu.Permission> permissions) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarTxt();
        DeshabilitarBtn();
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
        txtidMarca.setEnabled(true);
        txtMarca.setEnabled(true);
    }

    private void DeshabilitarTxt(){
        txtidMarca.setEnabled(false);
        txtMarca.setEnabled(false);
    }

    private void LimpiarTxt(){
        txtidMarca.setText("");
        txtMarca.setText("");
    }
    
    private void CheckUserPermissions(List<Menu.Permission> permissions) throws SQLException {
        DeshabilitarMainBtn();
        for (Menu.Permission permission : permissions) {
            if (permission.isActivePerm()) {
                switch (permission.getNamePerm()) {
                    case "BrandCreate":
                        btnAdd.setEnabled(true);
                        break;
                    case "BrandEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "BrandDelete":
                        btnErase.setEnabled(true);
                        break;
                    case "BrandList":
                        btnSearch.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso no utilizado o no concedido: " + permission.getPermID() + ", " + permission.getNamePerm());
                }
            }
        }
    }
    
    private void NuevaMarca() throws SQLException{
        String ExisteMarca = "Select * from marca";
        rs = con.Results(ExisteMarca);
        if (rs.next()) {
            String NroMarca = "Select last_value+1 as nromarca from marca_id_marca_seq";
            rs = con.Results(NroMarca);
            if (rs.next()) {
                txtidMarca.setText(rs.getString("nromarca"));
            }
        } else {
            String NroMarca = "Select last_value as nromarca from marca_id_marca_seq";
            rs = con.Results(NroMarca);
            if (rs.next()) {
                txtidMarca.setText(rs.getString("nromarca"));
            }
        }
    }

    private void GuardarDatos(){
        String marca = txtMarca.getText();
        if (Flag == 1) {
            con.InsertarDatos("marca", "descripcion_marca", "'"+marca+"'");
        } else if (Flag == 2) {
            con.EditarDatos("marca", "descripcion_marca='"+marca+"'", "id_marca="+txtidMarca.getText());
        } else if (Flag == 3) {
            con.BorrarDatos("marca", "id_marca="+txtidMarca.getText());
        }
    }

    private void Recuperar(int id) throws SQLException{
        String SQL_Recuperar = "Select * from marca where id_marca = "+String.valueOf(id);
        rs = con.Results(SQL_Recuperar);
        if(rs.next()){
            txtMarca.setText(rs.getString("descripcion_marca"));
            txtMarca.setEnabled(true);
            txtMarca.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }
    private void CabeceraTabla(){
        Search.addColumn("ID");
        Search.addColumn("Marca");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(90);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    private void LoadTable() throws SQLException{
        rs = con.Results("Select id_marca, descripcion_marca from marca");
        Search.setRowCount(0);
        while(rs.next()){
            Object[] row = new Object[2];
            row[0] = rs.getString("id_marca");
            row[1] = rs.getString("descripcion_marca");
            Search.addRow(row);
        }
    }
    private Boolean Validaciones(){
        if(txtMarca.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo vacío. Ingrese marca a registrar.");
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
        btnAdd = new javax.swing.JButton();
        lblIdMarca = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        txtidMarca = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMarca = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        btnErase = new javax.swing.JButton();

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

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setToolTipText("Registrar una nueva marca.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblIdMarca.setText("ID Marca");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setToolTipText("Editar una marca registrada.");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        txtidMarca.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtidMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidMarcaKeyPressed(evt);
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

        lblMarca.setText("Nombre Marca");

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
                        .addComponent(txtidMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarca)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtidMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)))
                    .addComponent(btnEdit)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(lblMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            NuevaMarca();
            txtMarca.setEnabled(true);
            txtMarca.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            Flag = 0;
            LimpiarTxt();
            DeshabilitarBtn();
            DeshabilitarTxt();
            CheckUserPermissions(Menu.permissions);
        } catch (SQLException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        HabilitarBtn();
        HabilitarTxt();
        txtidMarca.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 3;
        HabilitarBtn();
        HabilitarTxt();
        txtidMarca.requestFocus();
    }//GEN-LAST:event_btnEraseActionPerformed

    private void txtidMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidMarcaKeyPressed
        if(!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())){
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
            txtidMarca.setText("");
        }
        if (evt.getKeyCode() == 10 && !txtidMarca.getText().equals("")) {
            try{
                Recuperar(Integer.parseInt(txtidMarca.getText()));
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
        }else if(evt.getKeyCode() == 10 && txtidMarca.getText().equals("")){
            try {
                LoadTable();
                if(Flag == 2){
                    lblMensaje.setText("Seleccione una marca que desee modificar");
                }else if(Flag == 3){
                    lblMensaje.setText("Seleccione una marca que desee eliminar");
                }
                Searcher.setTitle("Buscar Marcas");
                Searcher.setModal(true);
                Searcher.pack();
                Searcher.setResizable(false);
                Searcher.setLocationRelativeTo(null);
                Searcher.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtidMarcaKeyPressed

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
                Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Marcas");
            if(Flag == 2){
                lblMensaje.setText("Seleccione una marca que desee modificar");
            }else if(Flag == 3){
                lblMensaje.setText("Seleccione una marca que desee eliminar");
            }
            Searcher.setTitle("Buscar Marcas");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                Searcher.setModal(false);
                Searcher.setVisible(false);
                txtidMarca.setText(id);
                Recuperar(Integer.parseInt(txtidMarca.getText()));
                if(Flag == 3){
                    int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0)
                    btnConfirmar.doClick();
                    else
                    btnCancelar.doClick();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            String SQL_Search = "select id_marca, descripcion_marca from marca where descripcion_marca ilike '%"+txtFilter.getText()+"%'";
            rs = con.Results(SQL_Search);
            Search.setRowCount(0);
            while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rs.getString("id_marca");
                row[1] = rs.getString("descripcion_marca");
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtidMarca;
    // End of variables declaration//GEN-END:variables
}
