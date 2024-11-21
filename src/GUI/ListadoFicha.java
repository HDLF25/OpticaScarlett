package GUI;

import Otros.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ListadoFicha extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    DefaultTableModel SearchCliente = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ListadoFicha() {
        initComponents();
        con = new Conexion();
        con.Login();
        CabeceraTablaCliente();
        jFechaDesde.setDateFormatString("yyyy-MM-dd");
        jFechaHasta.setDateFormatString("yyyy-MM-dd");
        SeleccionarChbox();
        txtCi.requestFocus();
    }

    private void RecuperarCliente(String ci) throws SQLException {
        String SQL_Recuperar = "select * from cliente where ci_cliente='" + String.valueOf(ci) + "'";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtNroCliente.setText(rs.getString("id_cliente"));
            txtCliente.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el CI/RUC Nro. " + txtCi.getText());
            txtCliente.setText("");
            txtNroCliente.setText("");
            txtCi.setText("");
            txtCi.requestFocus();
        }
    }

    private void SeleccionarChbox() {
        chboxOpen.setSelected(true);
        chboxClose.setSelected(true);
        chboxNull.setSelected(true);
    }

    private void RecuperarClientePorID(String id) throws SQLException {
        String SQL_Recuperar = "select * from cliente where id_cliente='" + String.valueOf(id) + "' and menordeedad=false";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtCi.setText(rs.getString("ci_cliente"));
            txtCliente.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el ID Nro. " + txtNroCliente.getText());
            txtCliente.setText("");
            txtCi.setText("");
            txtNroCliente.setText("");
            txtNroCliente.requestFocus();
        }
    }

    private void CabeceraTablaCliente() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        SearchCliente.addColumn("ID");
        SearchCliente.addColumn("CI/RUC");
        SearchCliente.addColumn("Nombres");
        SearchCliente.addColumn("Apellidos");
        SearchCliente.addColumn("Menor");
        SearchCliente.addColumn("Teléfono");
        SearchCliente.addColumn("Ciudad");
        SearchCliente.addColumn("Dirección");
        TSearcherCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        TSearcherCliente.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcherCliente.getColumnModel().getColumn(1).setPreferredWidth(80);
        TSearcherCliente.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        TSearcherCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TSearcherCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        TSearcherCliente.getColumnModel().getColumn(4).setPreferredWidth(30);
        TSearcherCliente.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        TSearcherCliente.getColumnModel().getColumn(5).setPreferredWidth(60);
        TSearcherCliente.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        TSearcherCliente.getColumnModel().getColumn(6).setPreferredWidth(60);
        TSearcherCliente.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        TSearcherCliente.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    private void CargarTablaClientes() throws SQLException {
        String SQLCliente = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and menordeedad=false";
        rs = con.Results(SQLCliente);
        SearchCliente.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[8];
            row[0] = rs.getString("id_cliente");
            row[1] = rs.getString("ci_cliente");
            row[2] = rs.getString("nombre_cliente");
            row[3] = rs.getString("apellido_cliente");
            Boolean menor = rs.getBoolean("menordeedad");
            if (menor == true) {
                row[4] = "Sí";
            } else {
                row[4] = "No";
            }
            row[5] = rs.getString("telefono");
            row[6] = rs.getString("ciudad");
            row[7] = rs.getString("direccion_cliente");
            SearchCliente.addRow(row);
        }
    }

    private void ViewReport(String ftr_ci, Date ftr_fdesde, Date ftr_fhasta, String ftr_abierto, String ftr_cerrado, String ftr_anulado) {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            //HashMap parametros = new HashMap();
            parametros.put("ci", ftr_ci);
            parametros.put("fdesde", ftr_fdesde);
            parametros.put("fhasta", ftr_fhasta);
            parametros.put("fabierto", ftr_abierto);
            parametros.put("fcerrado", ftr_cerrado);
            parametros.put("fanulado", ftr_anulado);
            URL urlReport = getClass().getClassLoader().getResource("Reportes/DocSearchReport.jasper");
            JasperReport masterReport = null;
            masterReport = (JasperReport) JRLoader.loadObject(urlReport);
            JasperPrint masterPrint = null;
            masterPrint = JasperFillManager.fillReport(masterReport, parametros, con.Login());
            JasperViewer.viewReport(masterPrint, false);
            /* JasperPrintManager.printReport(masterPrint, false); */
        } catch (JRException ex) {
            Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private Boolean Validaciones() {
        if (txtNroCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo N° Cliente está vacío, ingrese un valor antes de continuar.");
            return false;
        }
        if (txtCi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo CI del Cliente está vacío, ingrese un valor antes de continuar.");
            return false;
        }
        if (chboxAllDates.isSelected() == false) {
            if (jFechaDesde.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Fecha Desde está vacío, ingrese una fecha de inicio antes de continuar.");
                return false;
            }
            if (jFechaHasta.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Fecha Hasta está vacío, ingrese una fecha de fin antes de continuar.");
                return false;
            }
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

        SearcherCliente = new javax.swing.JDialog();
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        cboxFiltrar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TSearcherCliente = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblCli = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblCI = new javax.swing.JLabel();
        txtCi = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblFechas = new javax.swing.JLabel();
        chboxAllDates = new javax.swing.JCheckBox();
        lblDesde = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        jFechaDesde = new com.toedter.calendar.JDateChooser();
        jFechaHasta = new com.toedter.calendar.JDateChooser();
        lblExcluir = new javax.swing.JLabel();
        chboxOpen = new javax.swing.JCheckBox();
        chboxClose = new javax.swing.JCheckBox();
        chboxNull = new javax.swing.JCheckBox();
        btnGenerate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNroCliente = new javax.swing.JTextField();

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

        cboxFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CI/RUC", "Nombres", "Apellidos", "Teléfono", "Ciudad" }));
        cboxFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxFiltrarItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        jButton1.setText("Limpiar Filtro");

        TSearcherCliente.setModel(SearchCliente);
        TSearcherCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSearcherClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TSearcherCliente);

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout SearcherClienteLayout = new javax.swing.GroupLayout(SearcherCliente.getContentPane());
        SearcherCliente.getContentPane().setLayout(SearcherClienteLayout);
        SearcherClienteLayout.setHorizontalGroup(
            SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addGroup(SearcherClienteLayout.createSequentialGroup()
                        .addComponent(lblFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearcherClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        SearcherClienteLayout.setVerticalGroup(
            SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltrar)
                    .addComponent(cboxFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        lblCli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCli.setText("Cliente");

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar fichas del cliente");

        lblCI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCI.setText("CI/RUC:");

        txtCi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCiFocusLost(evt);
            }
        });
        txtCi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiKeyPressed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/backspace.png"))); // NOI18N
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCliente.setText("Nombre:");

        txtCliente.setEditable(false);

        lblFechas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechas.setText("Rango de Fechas");

        chboxAllDates.setText("Buscar todo");
        chboxAllDates.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chboxAllDatesStateChanged(evt);
            }
        });

        lblDesde.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDesde.setText("Desde:");

        lblHasta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHasta.setText("Hasta:");

        lblExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblExcluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExcluir.setText("Incluir OT");

        chboxOpen.setText("Abiertos");
        chboxOpen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chboxOpenStateChanged(evt);
            }
        });

        chboxClose.setText("Cerrados");
        chboxClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chboxCloseActionPerformed(evt);
            }
        });

        chboxNull.setText("Anulados");
        chboxNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chboxNullActionPerformed(evt);
            }
        });

        btnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printer.png"))); // NOI18N
        btnGenerate.setText("Generar Reporte");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("N° Cliente:");

        txtNroCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNroClienteFocusLost(evt);
            }
        });
        txtNroCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroClienteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chboxAllDates, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClean))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnGenerate)
                            .addComponent(lblExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(chboxOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chboxClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chboxNull, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCI)
                        .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch)
                    .addComponent(btnClean))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDesde)
                    .addComponent(lblHasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chboxAllDates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(lblExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chboxOpen)
                    .addComponent(chboxClose)
                    .addComponent(chboxNull))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnGenerate)
                .addContainerGap())
        );

        lblDesde.getAccessibleContext().setAccessibleName("");
        lblHasta.getAccessibleContext().setAccessibleName("");
        jFechaDesde.getAccessibleContext().setAccessibleName("");
        jFechaHasta.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        SearcherCliente.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cboxFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxFiltrarItemStateChanged
        try {
            txtFilter.setText("");
            CargarTablaClientes();
        } catch (SQLException ex) {
            Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboxFiltrarItemStateChanged

    private void TSearcherClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherClienteMouseClicked
        try {
            int row = TSearcherCliente.getSelectedRow();
            String ciCliente = TSearcherCliente.getModel().getValueAt(row, 1).toString();
            SearcherCliente.setModal(false);
            SearcherCliente.setVisible(false);
            txtCi.setText(ciCliente);
            RecuperarCliente(txtCi.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TSearcherClienteMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            String filtro = "";
            if (cboxFiltrar.getSelectedItem().equals("CI/RUC")) {
                filtro = "cl.ci_cliente";
            } else if (cboxFiltrar.getSelectedItem().equals("Nombres")) {
                filtro = "cl.nombre_cliente";
            } else if (cboxFiltrar.getSelectedItem().equals("Apellidos")) {
                filtro = "cl.apellido_cliente";
            } else if (cboxFiltrar.getSelectedItem().equals("Teléfono")) {
                filtro = "cl.telefono";
            } else if (cboxFiltrar.getSelectedItem().equals("Ciudad")) {
                filtro = "c.ciudad";
            }
            String SQL_Search = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and " + filtro + " ilike '%" + txtFilter.getText() + "%'";
            rs = con.Results(SQL_Search);
            SearchCliente.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("id_cliente");
                row[1] = rs.getString("ci_cliente");
                row[2] = rs.getString("nombre_cliente");
                row[3] = rs.getString("apellido_cliente");
                Boolean menor = rs.getBoolean("menordeedad");
                if (menor == true) {
                    row[4] = "Si";
                } else {
                    row[4] = "No";
                }
                row[5] = rs.getString("telefono");
                row[6] = rs.getString("ciudad");
                row[7] = rs.getString("direccion_cliente");
                SearchCliente.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased

    private void txtCiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiKeyPressed
        if (evt.getKeyCode() == 10 && !txtCi.getText().equals("")) {
            try {
                RecuperarCliente(txtCi.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtCi.getText().equals("")) {
            try {
                CargarTablaClientes();
                SearcherCliente.setTitle("Buscar Clientes");
                SearcherCliente.setModal(true);
                SearcherCliente.pack();
                SearcherCliente.setResizable(false);
                SearcherCliente.setLocationRelativeTo(null);
                SearcherCliente.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCiKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            CargarTablaClientes();
            lblMensaje.setText("Selecciona un Cliente");
            SearcherCliente.setTitle("Listado de Clientes");
            SearcherCliente.setModal(true);
            SearcherCliente.pack();
            SearcherCliente.setResizable(false);
            SearcherCliente.setLocationRelativeTo(null);
            SearcherCliente.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        txtCi.setText("");
        txtCliente.setText("");
        txtNroCliente.setText("");
        txtCi.requestFocus();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void chboxAllDatesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chboxAllDatesStateChanged
        if (chboxAllDates.isSelected() == true) {
            jFechaDesde.setEnabled(false);
            jFechaDesde.setCalendar(null);
            jFechaHasta.setEnabled(false);
            jFechaHasta.setCalendar(null);
        } else {
            jFechaDesde.setEnabled(true);
            jFechaHasta.setEnabled(true);
        }
    }//GEN-LAST:event_chboxAllDatesStateChanged

    private void chboxOpenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chboxOpenStateChanged
        if (chboxOpen.isSelected() == false && chboxNull.isSelected() == false && chboxClose.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
            chboxOpen.setSelected(true);
        }
    }//GEN-LAST:event_chboxOpenStateChanged

    private void chboxCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chboxCloseActionPerformed
        if (chboxOpen.isSelected() == false && chboxNull.isSelected() == false && chboxClose.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
            chboxClose.setSelected(true);
        }
    }//GEN-LAST:event_chboxCloseActionPerformed

    private void chboxNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chboxNullActionPerformed
        if (chboxOpen.isSelected() == false && chboxNull.isSelected() == false && chboxClose.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
            chboxNull.setSelected(true);
        }
    }//GEN-LAST:event_chboxNullActionPerformed

    private void txtNroClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroClienteKeyPressed
        if (evt.getKeyCode() == 10 && !txtNroCliente.getText().equals("")) {
            try {
                RecuperarClientePorID(txtNroCliente.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtNroCliente.getText().equals("")) {
            try {
                CargarTablaClientes();
                SearcherCliente.setTitle("Buscar Clientes");
                SearcherCliente.setModal(true);
                SearcherCliente.pack();
                SearcherCliente.setResizable(false);
                SearcherCliente.setLocationRelativeTo(null);
                SearcherCliente.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNroClienteKeyPressed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        if (Validaciones() == true) {
            String ftr_abierto = "";
            String ftr_cerrado = "";
            String ftr_anulado = "";
            if (chboxOpen.isSelected() == true) {
                if (chboxClose.isSelected() == true) {
                    if (chboxNull.isSelected() == true) {
                        ftr_abierto = "Abierto";
                        ftr_cerrado = "Cerrado";
                        ftr_anulado = "Anulado";
                    } else {
                        ftr_abierto = "Abierto";
                        ftr_cerrado = "Cerrado";
                    }
                } else if (chboxNull.isSelected() == true) {
                    ftr_abierto = "Abierto";
                    ftr_anulado = "Anulado";
                } else {
                    ftr_abierto = "Abierto";
                }
            } else if (chboxClose.isSelected() == true) {
                if (chboxNull.isSelected() == true) {
                    ftr_cerrado = "Cerrado";
                    ftr_anulado = "Anulado";
                } else {
                    ftr_cerrado = "Cerrado";
                }
            } else if (chboxNull.isSelected() == true) {
                ftr_anulado = "Anulado";
            } else {
                JOptionPane.showMessageDialog(null, "Hay algo raro aquí, esto no debería pasar D:");
            }
            Date ftr_fdesde = null;
            Date ftr_fhasta = null;
            if (chboxAllDates.isSelected() == true) {
                try {
                    String fdesde = "2000-01-01";
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                    ftr_fdesde = formatter1.parse(fdesde);
                    ftr_fhasta = java.sql.Date.valueOf(LocalDate.now());
                } catch (ParseException ex) {
                    Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ftr_fdesde = (Date) jFechaDesde.getDate();
                ftr_fhasta = (Date) jFechaHasta.getDate();
            }
            String ftr_ci = txtCi.getText();
            System.out.println("Filtro CI:     (" + ftr_ci + ")");
            System.out.println("Filtro Desde:  (" + ftr_fdesde + ")");
            System.out.println("Filtro Hasta:  (" + ftr_fhasta + ")");
            System.out.println("Filtro Estado: (" + ftr_abierto + ", " + ftr_cerrado + ", " + ftr_anulado + ")");
            ViewReport(ftr_ci, ftr_fdesde, ftr_fhasta, ftr_abierto, ftr_cerrado, ftr_anulado);
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void txtNroClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNroClienteFocusLost
        if (txtNroCliente.getText().equals("")) {
            txtCi.setText("");
            txtCliente.setText("");
        }
    }//GEN-LAST:event_txtNroClienteFocusLost

    private void txtCiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCiFocusLost
        if (txtCi.getText().equals("")) {
            txtNroCliente.setText("");
            txtCliente.setText("");
        }
    }//GEN-LAST:event_txtCiFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog SearcherCliente;
    private javax.swing.JTable TSearcherCliente;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxFiltrar;
    private javax.swing.JCheckBox chboxAllDates;
    private javax.swing.JCheckBox chboxClose;
    private javax.swing.JCheckBox chboxNull;
    private javax.swing.JCheckBox chboxOpen;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jFechaDesde;
    private com.toedter.calendar.JDateChooser jFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCI;
    private javax.swing.JLabel lblCli;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblFechas;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCi;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtNroCliente;
    // End of variables declaration//GEN-END:variables
}
