package GUI;

import Otros.Conexion;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;

public class Cliente extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    DefaultTableModel Search = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Cliente() throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn();
        DeshabilitarTxt();
        CabeceraTabla();
        CargarCboCiudad();
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

    private void HabilitarTxt() {
        txtidCliente.setEnabled(true);
        txtCiRuc.setEnabled(true);
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        chboxMenor.setEnabled(true);
        txtTelefono.setEnabled(true);
        cboxCiudad.setEnabled(true);
        txtDireccion.setEnabled(true);
    }

    private void DeshabilitarTxt() {
        txtidCliente.setEnabled(false);
        txtCiRuc.setEnabled(false);
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        chboxMenor.setEnabled(false);
        txtTelefono.setEnabled(false);
        cboxCiudad.setEnabled(false);
        txtDireccion.setEnabled(false);
    }

    private void EditableTxt() {
        txtidCliente.setEditable(true);
        txtCiRuc.setEditable(true);
        txtNombres.setEditable(true);
        txtApellidos.setEditable(true);
        txtTelefono.setEditable(true);
        txtDireccion.setEditable(true);
    }

    private void NoEditableTxt() {
        txtidCliente.setEditable(false);
        txtCiRuc.setEditable(false);
        txtNombres.setEditable(false);
        txtApellidos.setEditable(false);
        txtTelefono.setEditable(false);
        txtDireccion.setEditable(false);
    }

    private void LimpiarTxt() {
        txtidCliente.setText("");
        txtCiRuc.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        chboxMenor.setSelected(false);
    }

    private void NuevoCliente() throws SQLException {
        String ExisteCliente = "Select * from cliente";
        rs = con.Results(ExisteCliente);
        if (rs.next()) {
            String NroCliente = "Select last_value+1 as nrocliente from cliente_id_cliente_seq";
            rs = con.Results(NroCliente);
            if (rs.next()) {
                txtidCliente.setText(rs.getString("nrocliente"));
            }
        } else {
            String NroCliente = "Select last_value as nrocliente from cliente_id_cliente_seq";
            rs = con.Results(NroCliente);
            if (rs.next()) {
                txtidCliente.setText(rs.getString("nrocliente"));
            }
        }
    }

    private void RestartCbox() {
        cboxCiudad.setSelectedIndex(0);
    }

    private void GuardarDatos() throws SQLException {
        String idcliente = txtidCliente.getText();
        String ciruc = txtCiRuc.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String menor;
        if (chboxMenor.isSelected()) {
            menor = "true";
        } else {
            menor = "false";
        }
        Integer ciudad = RecuperarCiudad(cboxCiudad.getSelectedItem().toString());
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        if (Flag == 1) {
            con.InsertarDatos("cliente", "nombre_cliente, apellido_cliente, menordeedad, ci_cliente, telefono, id_ciudad, direccion_cliente", "'" + nombres + "','" + apellidos + "'," + menor + ",'" + ciruc + "','" + telefono + "','" + ciudad + "','" + direccion + "'");
        } else if (Flag == 2) {
            con.EditarDatos("cliente", "nombre_cliente='" + nombres + "',apellido_cliente='" + apellidos + "',menordeedad=" + menor + ",ci_cliente='" + ciruc + "',telefono='" + telefono + "',id_ciudad='" + ciudad + "',direccion_cliente='" + direccion + "'", "id_cliente='" + txtidCliente.getText() + "'");
        } else if (Flag == 3) {
            con.BorrarDatos("cliente", "id_cliente='" + txtidCliente.getText() + "'");
        }
    }

    private void RecuperarPorID(String id) throws SQLException {
        String SQL_Recuperar = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and cl.id_cliente='" + String.valueOf(id) + "'";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtCiRuc.setText(rs.getString("ci_cliente"));
            txtNombres.setText(rs.getString("nombre_cliente"));
            txtApellidos.setText(rs.getString("apellido_cliente"));
            Boolean checkmenor = rs.getBoolean("menordeedad");
            if (checkmenor == true) {
                chboxMenor.setSelected(true);
            } else {
                chboxMenor.setSelected(false);
            }
            txtTelefono.setText(rs.getString("telefono"));
            cboxCiudad.setSelectedItem(rs.getString("ciudad"));
            txtDireccion.setText(rs.getString("direccion_cliente"));
            txtNombres.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void RecuperarPorCI(String ci) throws SQLException {
        String SQL_Recuperar = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and cl.ci_cliente='" + String.valueOf(ci) + "'";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtidCliente.setText(rs.getString("id_cliente"));
            txtNombres.setText(rs.getString("nombre_cliente"));
            txtApellidos.setText(rs.getString("apellido_cliente"));
            Boolean checkmenor = rs.getBoolean("menordeedad");
            if (checkmenor == true) {
                chboxMenor.setSelected(true);
            } else {
                chboxMenor.setSelected(false);
            }
            txtTelefono.setText(rs.getString("telefono"));
            cboxCiudad.setSelectedItem(rs.getString("ciudad"));
            txtDireccion.setText(rs.getString("direccion_cliente"));
            txtNombres.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el CI/RUC Nro. " + txtCiRuc.getText());
        }
    }

    private void CabeceraTabla() {
        Search.addColumn("ID");
        Search.addColumn("CI/RUC");
        Search.addColumn("Nombres");
        Search.addColumn("Apellidos");
        Search.addColumn("Menor");
        Search.addColumn("Teléfono");
        Search.addColumn("Ciudad");
        Search.addColumn("Dirección");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(30);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(2).setPreferredWidth(100);
        TSearcher.getColumnModel().getColumn(3).setPreferredWidth(100);
        TSearcher.getColumnModel().getColumn(4).setPreferredWidth(30);
        TSearcher.getColumnModel().getColumn(5).setPreferredWidth(60);
        TSearcher.getColumnModel().getColumn(6).setPreferredWidth(60);
        TSearcher.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    private void LoadTable() throws SQLException {
        rs = con.Results("select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad");
        Search.setRowCount(0);
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
            Search.addRow(row);
        }
    }

    private Boolean Validaciones() {
        if (Flag != 1) {
            if (txtidCliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Código vacío. Ingrese un código para el cliente.");
                return false;
            }
        }
        if (txtNombres.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nombre no ingresado. Introduzca nombre al cliente a registrar.");
            txtNombres.requestFocus();
            return false;
        }
        if (txtApellidos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Apellido no ingresado. Introduzca un apellido al cliente a registrar.");
            txtApellidos.requestFocus();
            return false;
        }
        if (txtTelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nro Celular no ingresado. Introduzca un nro celular al cliente a registrar.");
            txtTelefono.requestFocus();
            return false;
        }
        if (cboxCiudad.getSelectedItem().toString().equals("Selecciona")) {
            JOptionPane.showMessageDialog(null, "Selecciona una ciudad para el cliente.");
            return false;
        }
        if (txtTelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nro Celular no ingresado. Introduzca un nro celular al cliente a registrar.");
            txtTelefono.requestFocus();
            return false;
        }
        if (txtDireccion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Dirección no ingresada. Introduzca la dirección del cliente a registrar.");
            txtDireccion.requestFocus();
            return false;
        }
        return true;
    }

    private Integer RecuperarCiudad(String ciudad) throws SQLException {
        Integer id = null;
        String SQL_Marca = "select id_ciudad from ciudad where ciudad = '" + ciudad + "'";
        rs = con.Results(SQL_Marca);
        if (rs.next()) {
            id = rs.getInt("id_ciudad");
        }
        return id;
    }

    private void CargarCboCiudad() throws SQLException {
        cboxCiudad.removeAllItems();
        cboxCiudad.addItem("Selecciona");
        rs = con.Results("select ciudad from ciudad order by ciudad asc;");
        while (rs.next()) {
            cboxCiudad.addItem(rs.getString("ciudad"));
        }
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (!cboxCiudad.getSelectedItem().toString().equals("Selecciona")) {
                    txtNombres.setEnabled(true);
                    txtNombres.requestFocus();
                }
            }
        };
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
        cboxFiltrar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TSearcher = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        chboxMenor = new javax.swing.JCheckBox();
        btnErase = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        txtCiRuc = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtTelefono = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtidCliente = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboxCiudad = new javax.swing.JComboBox<>();
        txtNombres = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        cboxFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CI/RUC", "Nombres", "Apellidos", "Teléfono", "Ciudad" }));
        cboxFiltrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxFiltrarItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        jButton1.setText("Limpiar Filtro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        lblFiltrar.setText("Buscar por:");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("null");

        javax.swing.GroupLayout SearcherLayout = new javax.swing.GroupLayout(Searcher.getContentPane());
        Searcher.getContentPane().setLayout(SearcherLayout);
        SearcherLayout.setHorizontalGroup(
            SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosKeyReleased(evt);
            }
        });

        jLabel3.setText("Apellidos");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setToolTipText("Registrar un nuevo cliente.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        chboxMenor.setText("Menor de edad");

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnErase.setToolTipText("Eliminar un cliente registrado.");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        jLabel4.setText("C.I./R.U.C.");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setToolTipText("Editar un cliente registrado.");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        txtCiRuc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCiRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiRucKeyPressed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearch.setToolTipText("Buscar cliente.");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        jLabel5.setText("Teléfono");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Cliente");

        jLabel6.setText("Dirección");

        txtidCliente.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setText("Nombres");

        txtNombres.setToolTipText("Inserte nombre");
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });

        jLabel7.setText("Ciudad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cboxCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtDireccion)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnErase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCiRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))
                            .addComponent(jLabel5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chboxMenor))
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
                            .addComponent(jLabel1)
                            .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd)))
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCiRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chboxMenor)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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
                txtidCliente.setText(id);
                RecuperarPorID(txtidCliente.getText());
                if (Flag == 3) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        btnConfirmar.doClick();
                    } else {
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

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
            Search.setRowCount(0);
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
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            Flag = 1;
            HabilitarBtn();
            HabilitarTxt();
            NuevoCliente();
            txtidCliente.setEnabled(false);
            txtCiRuc.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        HabilitarBtn();
        HabilitarTxt();
        txtCiRuc.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 3;
        HabilitarBtn();
        HabilitarTxt();
        NoEditableTxt();
        txtidCliente.setEditable(true);
        txtCiRuc.setEditable(true);
        txtCiRuc.requestFocus();
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Flag = 0;
        LimpiarTxt();
        DeshabilitarBtn();
        DeshabilitarTxt();
        RestartCbox();
        EditableTxt();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Clientes");
            if (Flag == 2) {
                lblMensaje.setText("Seleccione un cliente que desee modificar");
            } else if (Flag == 3) {
                lblMensaje.setText("Seleccione un cliente que desee eliminar");
            }
            Searcher.setTitle("Buscar Cliente");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        if (Validaciones() == true) {
            try {
                GuardarDatos();
                LimpiarTxt();
                DeshabilitarTxt();
                DeshabilitarBtn();
                RestartCbox();
                Flag = 0;
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void txtCiRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiRucKeyPressed
        if (evt.getKeyCode() == 10 && !txtCiRuc.getText().equals("")) {
            try {
                RecuperarPorCI(txtCiRuc.getText());
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnConfirmar.doClick();
                    } else {
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtCiRuc.getText().equals("")) {
            try {
                LoadTable();
                if (Flag == 2) {
                    lblMensaje.setText("Selecciona un cliente que desee modificar");
                } else if (Flag == 3) {
                    lblMensaje.setText("Selecciona un cliente que desee eliminar");
                }
                Searcher.setTitle("Buscar Clientes");
                Searcher.setModal(true);
                Searcher.pack();
                Searcher.setResizable(false);
                Searcher.setLocationRelativeTo(null);
                Searcher.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCiRucKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            cboxFiltrar.setSelectedIndex(0);
            txtFilter.setText("");
            LoadTable();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        String ClientName = txtNombres.getText();
        if (ClientName.matches(".*[\'\"!@#$%^&*()].*")) {
            txtNombres.setText(ClientName.substring(0, ClientName.length() - 1));
            txtNombres.setToolTipText("Inserte nombre. No se permiten caracteres especiales.");
            ToolTipManager.sharedInstance().mouseMoved(
                    new MouseEvent(txtNombres, 0, 0, 0, 0, 0, 0, false)
            );
        }
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyReleased
        String ClientLastName = txtNombres.getText();
        if (ClientLastName.matches(".*[\'\"!@#$%^&*()].*")) {
            txtNombres.setText(ClientLastName.substring(0, ClientLastName.length() - 1));
            txtNombres.setToolTipText("Inserte apellido. No se permiten caracteres especiales.");
            ToolTipManager.sharedInstance().mouseMoved(
                    new MouseEvent(txtNombres, 0, 0, 0, 0, 0, 0, false)
            );
        }
    }//GEN-LAST:event_txtApellidosKeyReleased


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
    private javax.swing.JComboBox<String> cboxCiudad;
    private javax.swing.JComboBox<String> cboxFiltrar;
    private javax.swing.JCheckBox chboxMenor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCiRuc;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtidCliente;
    // End of variables declaration//GEN-END:variables
}
