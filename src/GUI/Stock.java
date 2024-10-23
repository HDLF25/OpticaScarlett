/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Otros.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author david
 */
public class Stock extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    DefaultTableModel Search = new DefaultTableModel(){
    @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    String costoactualcomp = "";
    
    public Stock() throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn();
        DeshabilitarTxt();
        CabeceraTabla();
    }
    
    private void HabilitarBtn(){
        btnAdd.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void DeshabilitarBtn(){
        btnAdd.setEnabled(true);
        btnErase.setEnabled(true);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    private void HabilitarTxt(){
        txtidArticulo.setEnabled(true);
        txtArticulo.setEnabled(true);
        txtCostoActual.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtStock.setEnabled(true);
        txtNuevoStock.setEnabled(true);
    }

    private void DeshabilitarTxt(){
        txtidArticulo.setEnabled(false);
        txtArticulo.setEnabled(false);
        txtCostoActual.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtStock.setEnabled(false);
        txtNuevoStock.setEnabled(false);
    }

    private void LimpiarTxt(){
        txtidArticulo.setText("");
        txtArticulo.setText("");
        txtCostoActual.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    private void GuardarDatos() throws SQLException{
        String idarticulo = txtidArticulo.getText();
        String costoactual = txtCostoActual.getText();
        String precio = txtPrecio.getText();
        String nuevostock = txtNuevoStock.getText();
        if (Flag == 1) {
            con.EditarDatos("stock", "cantidad=cantidad+"+nuevostock, "id_articulo='"+idarticulo+"' and id_deposito=1");
            if (costoactualcomp != costoactual) {
                con.EditarDatosDetalle("articulos", "costoanterior_articulo=costoactual_articulo,costoactual_articulo='"+costoactual+"',precioventa_articulo='"+precio+"'", "id_articulo='"+idarticulo+"'");
            } else {
                con.EditarDatosDetalle("articulos", "precioventa_articulo='"+precio+"'", "id_articulo='"+idarticulo+"'");
            }
            
        } else if (Flag == 3) {
            con.EditarDatos("stock", "cantidad=cantidad-"+nuevostock, "id_articulo='"+idarticulo+"' and id_deposito=1");
            if (costoactualcomp != costoactual) {
                con.EditarDatosDetalle("articulos", "costoanterior_articulo=costoactual_articulo,costoactual_articulo='"+costoactual+"',precioventa_articulo='"+precio+"'", "id_articulo='"+idarticulo+"'");
            } else {
                con.EditarDatosDetalle("articulos", "precioventa_articulo='"+precio+"'", "id_articulo='"+idarticulo+"'");
            }
        }
    }
    
    private void Recuperar(String id) throws SQLException{
        String SQL_Recuperar = "select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and art.id_articulo='"+String.valueOf(id)+"'";
        rs = con.Results(SQL_Recuperar);
        if(rs.next()){
            txtArticulo.setText(rs.getString("descripcion_articulo"));
            txtCostoActual.setText(rs.getString("costoactual_articulo"));
            costoactualcomp = rs.getString("costoactual_articulo");
            txtPrecio.setText(rs.getString("precioventa_articulo"));
            txtStock.setText(rs.getString("cantidad"));
            txtArticulo.setEnabled(true);
            txtArticulo.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }
    private void CabeceraTabla(){
        Search.addColumn("ID");
        Search.addColumn("Descripción");
        Search.addColumn("Categoría");
        Search.addColumn("Marca");
        Search.addColumn("Costo Actual");
        Search.addColumn("Costo Anterior");
        Search.addColumn("Precio");
        Search.addColumn("Stock");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(50);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(200);
        TSearcher.getColumnModel().getColumn(2).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(3).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(4).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(5).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(6).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(7).setPreferredWidth(50);
    }
    private void LoadTable() throws SQLException{
        rs = con.Results("select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and art.categoria_articulo!='Servicio'");
        Search.setRowCount(0);
        while(rs.next()){
            Object[] row = new Object[8];
            row[0] = rs.getString("id_articulo");
            row[1] = rs.getString("descripcion_articulo");
            row[2] = rs.getString("categoria_articulo");
            row[3] = rs.getString("descripcion_marca");
            row[4] = rs.getString("costoactual_articulo");
            row[5] = rs.getString("costoanterior_articulo");
            row[6] = rs.getString("precioventa_articulo");
            row[7] = rs.getString("cantidad");
            Search.addRow(row);
        }
    }
    private Boolean Validaciones(){
        if(txtidArticulo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Código vacío. Ingrese un código para el artículo.");
            return false;
        }
        if(txtArticulo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Descripción vacía. Ingrese artículo a registrar.");
            return false;
        }
        if (txtCostoActual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Costo Actual vacío. Ingrese un costo para el artículo.");
        }
        if (txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Precio de Venta vacío. Ingrese un precio para el artículo.");
        }
        if (txtStock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo de Stock vacío. Ingrese un Stock para el artículo.");
        }
        if (txtNuevoStock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Agregue una cantidad a modificar antes de continuar.");
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
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        cboxFiltrar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TSearcher = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblCostoActual = new javax.swing.JLabel();
        txtCostoActual = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        btnErase = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblidArticulo = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtidArticulo = new javax.swing.JTextField();
        lblArticulo = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        txtNuevoStock = new javax.swing.JTextField();
        lblNuevoStock = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();

        lblFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filtrar.png"))); // NOI18N
        lblFiltrar.setText("Filtrar por:");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("null");

        cboxFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código/ID", "Descripción", "Marca", "Categoría" }));
        cboxFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxFiltrarItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        jButton1.setText("Limpiar Filtro");

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

        javax.swing.GroupLayout SearcherLayout = new javax.swing.GroupLayout(Searcher.getContentPane());
        Searcher.getContentPane().setLayout(SearcherLayout);
        SearcherLayout.setHorizontalGroup(
            SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(SearcherLayout.createSequentialGroup()
                        .addComponent(lblFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblFiltrar)
                    .addComponent(cboxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        lblCostoActual.setText("Costo Actual");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setText("Añadir");
        btnAdd.setToolTipText("Registrar un nuevo artículo.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblStock.setText("Stock Actual");

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnErase.setText("Restar");
        btnErase.setToolTipText("Eliminar un artículo registrado.");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearch.setToolTipText("Buscar artículo.");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblidArticulo.setText("ID Articulo");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtidArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidArticuloKeyPressed(evt);
            }
        });

        lblArticulo.setText("Articulo");

        lblNuevoStock.setText("Cantidad...");

        jLabel2.setText("+");

        lblPrecio.setText("Precio de Venta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnErase, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCostoActual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(lblCostoActual, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecio)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblArticulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(lblStock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNuevoStock)
                            .addComponent(txtNuevoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblidArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addComponent(txtArticulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnErase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblidArticulo)
                        .addComponent(txtidArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArticulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCostoActual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNuevoStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevoStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Flag = 1;
        HabilitarBtn();
        HabilitarTxt();
        lblNuevoStock.setText("Cantidad a Ingresar");
        txtidArticulo.setEnabled(true);
        txtidArticulo.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 3;
        HabilitarBtn();
        lblNuevoStock.setText("Cantidad a Egresar");
        txtidArticulo.setEnabled(true);
        txtidArticulo.requestFocus();
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Artículos");
            if(Flag == 1){
                lblMensaje.setText("Seleccione un artículo que desee modificar");
            }else if(Flag == 3){
                lblMensaje.setText("Seleccione un artículo que desee modificar");
            }
            Searcher.setTitle("Buscar Artículo");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validaciones() == true) {
            try {
                GuardarDatos();
                LimpiarTxt();
                DeshabilitarTxt();
                DeshabilitarBtn();
                Flag = 0;
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Flag = 0;
        LimpiarTxt();
        DeshabilitarBtn();
        DeshabilitarTxt();
        lblNuevoStock.setText("Cantidad...");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtidArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidArticuloKeyPressed
        if (evt.getKeyCode() == 10 && !txtidArticulo.getText().equals("")) {
            try {
                Recuperar(txtidArticulo.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(evt.getKeyCode() == 10 && txtidArticulo.getText().equals("")){
            try {
                LoadTable();
                if(Flag == 1){
                    lblMensaje.setText("Seleccione una marca que desee modificar");
                }else if(Flag == 3){
                    lblMensaje.setText("Seleccione una marca que desee modificar");
                }
                Searcher.setTitle("Buscar Artículos");
                Searcher.setModal(true);
                Searcher.pack();
                Searcher.setResizable(false);
                Searcher.setLocationRelativeTo(null);
                Searcher.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtidArticuloKeyPressed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cboxFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxFiltrarItemStateChanged
        txtFilter.setText("");
    }//GEN-LAST:event_cboxFiltrarItemStateChanged

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                Searcher.setModal(false);
                Searcher.setVisible(false);
                txtidArticulo.setText(id);
                Recuperar(txtidArticulo.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            String filtro = "";
            if (cboxFiltrar.getSelectedItem().equals("Descripción")) {
                filtro = "descripcion_articulo";
            } else if (cboxFiltrar.getSelectedItem().equals("Código/ID")) {
                filtro = "id_articulo";
            } else if (cboxFiltrar.getSelectedItem().equals("Marca")) {
                filtro = "descripcion_marca";
            } else if (cboxFiltrar.getSelectedItem().equals("Categoría")) {
                filtro = "categoria_articulo";
            }
            String SQL_Search = "select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and art.categoria_articulo!='Servicio' and "+filtro+" ilike '%"+txtFilter.getText()+"%'";
            rs = con.Results(SQL_Search);
            Search.setRowCount(0);
            while(rs.next()){
                Object[] row = new Object[7];
                row[0] = rs.getString("id_articulo");
                row[1] = rs.getString("descripcion_articulo");
                row[2] = rs.getString("categoria_articulo");
                row[3] = rs.getString("descripcion_marca");
                row[4] = rs.getString("costoactual_articulo");
                row[5] = rs.getString("precioventa_articulo");
                row[6] = rs.getString("cantidad");
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Searcher;
    private javax.swing.JTable TSearcher;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxFiltrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArticulo;
    private javax.swing.JLabel lblCostoActual;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNuevoStock;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblidArticulo;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtCostoActual;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtNuevoStock;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtidArticulo;
    // End of variables declaration//GEN-END:variables
}
