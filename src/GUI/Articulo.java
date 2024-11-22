package GUI;

import articles.ImageUtils;
import Otros.Conexion;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Articulo extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    private File imagenSeleccionadaTemporal;
    private String rutaImagenSeleccionada;
    DefaultTableModel Search = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Articulo(int idUsuario) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn();
        DeshabilitarTxt();
        CabeceraTabla();
        CargarCboMarca();
        txtCostoAnterior.setEditable(false);
        CheckUserPermissions(idUsuario);
    }

    private void HabilitarBtn() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnTransfer.setEnabled(true);
        btnImage.setEnabled(true);
    }

    private void DehabilitarMainBtn() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnErase.setEnabled(false);
        btnSearch.setEnabled(false);
    }

    private void DeshabilitarBtn() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnErase.setEnabled(true);
        btnSearch.setEnabled(true);
        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnTransfer.setEnabled(false);
        btnImage.setEnabled(false);
    }

    private void HabilitarTxt() {
        txtidArticulo.setEnabled(true);
        txtArticulo.setEnabled(true);
        cboxCategoria.setEnabled(true);
        cboxMarca.setEnabled(true);
        txtColor.setEnabled(true);
        txtMaterial.setEnabled(true);
        txtCostoActual.setEnabled(true);
        txtCostoAnterior.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtStock.setEnabled(true);
    }

    private void DeshabilitarTxt() {
        txtidArticulo.setEnabled(false);
        txtArticulo.setEnabled(false);
        cboxCategoria.setEnabled(false);
        cboxMarca.setEnabled(false);
        txtColor.setEnabled(false);
        txtMaterial.setEnabled(false);
        txtCostoActual.setEnabled(false);
        txtCostoAnterior.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtStock.setEnabled(false);
    }

    private void LimpiarTxt() {
        txtidArticulo.setText("");
        txtArticulo.setText("");
        txtColor.setText("");
        txtMaterial.setText("");
        txtCostoActual.setText("");
        txtCostoAnterior.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    private void RestartCbox() {
        cboxCategoria.setSelectedIndex(0);
        cboxMarca.setSelectedIndex(0);
    }

    private void CheckUserPermissions(int idUser) throws SQLException {
        DehabilitarMainBtn();
        String SQLUserCheck = "SELECT up.id_permission, per.name_permission, up.active FROM users_permissions up, permissions per WHERE up.id_permission = per.id_permission AND up.id_permission IN (12,13,14,15) AND id_usuario = " + idUser + ";";
        rs = con.Results(SQLUserCheck);
        while (rs.next()) {
            int idPermission = rs.getInt("id_permission");
            String namePermission = rs.getString("name_permission");
            Boolean isActive = rs.getBoolean("active");
            if (isActive) {
                switch (namePermission) {
                    case "ArtCreate":
                        btnAdd.setEnabled(true);
                        break;
                    case "ArtEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "ArtDelete":
                        btnErase.setEnabled(true);
                        break;
                    case "ArtList":
                        btnSearch.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso desconocido: " + idPermission + ", " + namePermission);
                }
            }
        }
    }

    private void GuardarDatos() throws SQLException, IOException {
        String idarticulo = txtidArticulo.getText();
        String articulo = txtArticulo.getText();
        String categoria = null;
        Integer marca = RecuperarMarca(cboxMarca.getSelectedItem().toString());
        if (cboxCategoria.getSelectedItem().toString().equals("Producto")) {
            categoria = "Producto";
        } else if (cboxCategoria.getSelectedItem().toString().equals("Cristal")) {
            categoria = "Cristal";
        } else if (cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            categoria = "Servicio";
        }
        String color = txtColor.getText();
        String material = txtMaterial.getText();
        String costoactual = txtCostoActual.getText();
        String costoanterior = txtCostoAnterior.getText();
        String precio = txtPrecio.getText();
        String stock = txtStock.getText();

        // Comprueba si el directorio existe, sino crea uno
        File directory = new File("ImageArt");
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directorio 'ImageArt' creado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el directorio 'ImageArt'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Obtener el nombre desde el JTextField
        String nombreImagen = txtidArticulo.getText().trim();
        if (nombreImagen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, asignar ID del artículo antes de cargar una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombreConExtension = nombreImagen + ".png";
        File destino = new File(directory, nombreConExtension);

        // Si no se seleccionó una imagen, usar la predeterminada
        if (imagenSeleccionadaTemporal == null) {
            File imagenPredeterminada = new File("ImageArt/default.png");
            if (!imagenPredeterminada.exists()) {
                JOptionPane.showMessageDialog(this, "La imagen predeterminada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Copiar la imagen predeterminada al destino
            Files.copy(imagenPredeterminada.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se usó la imagen predeterminada para: " + destino.getAbsolutePath());
        } else {
            // Verificar si el archivo ya existe
            if (destino.exists()) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Desea reemplazar la imagen?", "Confirmar reemplazo",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Redimensionar y guardar la imagen seleccionada
            ImageUtils.resizeImage(imagenSeleccionadaTemporal, destino, 800, 800);
            System.out.println("Imagen guardada en: " + destino.getAbsolutePath());
        }

        // Actualizar la variable para el SQL
        rutaImagenSeleccionada = destino.getAbsolutePath();

        if (Flag == 1) {
            con.InsertarDatos("articulos", "id_articulo, descripcion_articulo, categoria_articulo, costoactual_articulo, costoanterior_articulo, precioventa_articulo, id_marca, color, material, image_dir", "'" + idarticulo + "','" + articulo + "','" + categoria + "','" + costoactual + "','" + costoanterior + "','" + precio + "','" + marca + "','" + color + "','" + material + "','" + rutaImagenSeleccionada + "'");
            con.InsertarDatosDetalle("stock", "id_articulo, id_deposito, cantidad", "'" + idarticulo + "',1," + stock);
        } else if (Flag == 2) {
            con.EditarDatos("articulos", "descripcion_articulo='" + articulo + "'," + "categoria_articulo='" + categoria + "'," + "costoactual_articulo='" + costoactual + "'," + "costoanterior_articulo='" + costoanterior + "'," + "precioventa_articulo='" + precio + "'," + "id_marca='" + marca + "'," + "color='" + color + "'," + "material='" + material + "'", "id_articulo='" + txtidArticulo.getText() + "'");
            con.EditarDatosDetalle("stock", "cantidad=" + stock, "id_articulo='" + txtidArticulo.getText() + "' and id_deposito=1");
        } else if (Flag == 3) {
            con.BorrarDatosDetalle("stock", "id_articulo='" + txtidArticulo.getText() + "'");
            con.BorrarDatos("articulos", "id_articulo='" + txtidArticulo.getText() + "'");
        }

        // Vaciar lblImage después de confirmar
        lblImage.setIcon(null);

        // Resetear la variable de imagen temporal
        imagenSeleccionadaTemporal = null;
    }

    private void Recuperar(String id) throws SQLException {
        String SQL_Recuperar = "select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and art.id_articulo='" + String.valueOf(id) + "'";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtArticulo.setText(rs.getString("descripcion_articulo"));
            cboxCategoria.setSelectedItem(rs.getString("categoria_articulo"));
            cboxMarca.setSelectedItem(rs.getString("descripcion_marca"));
            txtColor.setText(rs.getString("color"));
            txtMaterial.setText(rs.getString("material"));
            txtCostoActual.setText(rs.getString("costoactual_articulo"));
            txtCostoAnterior.setText(rs.getString("costoanterior_articulo"));
            txtPrecio.setText(rs.getString("precioventa_articulo"));
            txtStock.setText(rs.getString("cantidad"));
            txtArticulo.setEnabled(true);
            txtArticulo.requestFocus();

            // Recuperar el directorio de la imagen
            String imageDir = rs.getString("image_dir");
            if (imageDir != null && !imageDir.isEmpty()) {
                File imageFile = new File(imageDir);
                if (imageFile.exists()) {
                    // Cargar y escalar la imagen en lblImage
                    ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                    lblImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH)));
                } else {
                    // Si la imagen no existe, vaciar lblImage
                    lblImage.setIcon(null);
                    System.out.println("La imagen no existe en la ruta especificada: " + imageDir);
                }
            } else {
                // Si no hay ruta almacenada, vaciar lblImage
                lblImage.setIcon(null);
                System.out.println("No se encontró una ruta de imagen para este artículo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void CabeceraTabla() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        Search.addColumn("ID");
        Search.addColumn("Descripción");
        Search.addColumn("Categoría");
        Search.addColumn("Marca");
        Search.addColumn("Costo Actual");
        Search.addColumn("Precio");
        Search.addColumn("Stock");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(50);
        TSearcher.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(200);
        TSearcher.getColumnModel().getColumn(2).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(3).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(4).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TSearcher.getColumnModel().getColumn(5).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        TSearcher.getColumnModel().getColumn(6).setPreferredWidth(50);
        TSearcher.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
    }

    private void LoadTable() throws SQLException {
        rs = con.Results("select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca");
        Search.setRowCount(0);
        while (rs.next()) {
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
    }

    private Boolean Validaciones() {
        if (txtidArticulo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Código vacío. Ingrese un código para el artículo.");
            return false;
        }
        if (txtArticulo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Descripción vacía. Ingrese artículo a registrar.");
            return false;
        }
        if (cboxCategoria.getSelectedItem().toString().equals("Selecciona")) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoría para el artículo.");
            return false;
        }
        if (cboxMarca.getSelectedItem().toString().equals("Selecciona") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "Seleccione una marca para el artículo.");
            return false;
        }
        if (txtColor.getText().equals("") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "El campo Color está vacío. Ingrese un color para el artículo.");
            return false;
        }
        if (txtMaterial.getText().equals("") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "El campo Material está vacío. Ingrese un material para el artículo.");
            return false;
        }
        if (txtCostoActual.getText().equals("") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "Costo Actual vacío. Ingrese un costo para el artículo.");
            return false;
        }
        if (txtCostoAnterior.getText().equals("") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "Costo Anterior vacío. Ingrese un costo para el artículo.");
            return false;
        }
        if (txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Precio de Venta vacío. Ingrese un precio para el artículo.");
            return false;
        }
        if (txtStock.getText().equals("") && !cboxCategoria.getSelectedItem().toString().equals("Servicio")) {
            JOptionPane.showMessageDialog(null, "Campo de Stock vacío. Ingrese un Stock para el artículo.");
            return false;
        }
        return true;
    }

    private Integer RecuperarMarca(String marca) throws SQLException {
        Integer id = null;
        String SQL_Marca = "select id_marca from marca where descripcion_marca = '" + marca + "'";
        rs = con.Results(SQL_Marca);
        if (rs.next()) {
            id = rs.getInt("id_marca");
        }
        return id;
    }

    private void CargarCboMarca() throws SQLException {
        cboxMarca.removeAllItems();
        cboxMarca.addItem("Selecciona");
        rs = con.Results("select descripcion_marca from marca order by descripcion_marca asc;");
        while (rs.next()) {
            cboxMarca.addItem(rs.getString("descripcion_marca"));
        }
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (!cboxMarca.getSelectedItem().toString().equals("Selecciona")) {
                    txtArticulo.setEnabled(true);
                    txtArticulo.requestFocus();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TSearcher = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        cboxFiltrar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnErase = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        lblidArticulo = new javax.swing.JLabel();
        txtidArticulo = new javax.swing.JTextField();
        lblArticulo = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cboxCategoria = new javax.swing.JComboBox<>();
        lblMarca = new javax.swing.JLabel();
        cboxMarca = new javax.swing.JComboBox<>();
        lblColor = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lblMaterial = new javax.swing.JLabel();
        txtMaterial = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblCostoActual = new javax.swing.JLabel();
        txtCostoActual = new javax.swing.JTextField();
        lblCostoAnterior = new javax.swing.JLabel();
        txtCostoAnterior = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnTransfer = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblTitleImage = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnImage = new javax.swing.JButton();

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

        javax.swing.GroupLayout SearcherLayout = new javax.swing.GroupLayout(Searcher.getContentPane());
        Searcher.getContentPane().setLayout(SearcherLayout);
        SearcherLayout.setHorizontalGroup(
            SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnAdd.setToolTipText("Registrar un nuevo artículo.");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setToolTipText("Editar un artículo registrado.");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
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

        txtidArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidArticuloKeyPressed(evt);
            }
        });

        lblArticulo.setText("Articulo");

        lblCategoria.setText("Categoría");

        cboxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Producto", "Cristal", "Servicio" }));
        cboxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxCategoriaItemStateChanged(evt);
            }
        });

        lblMarca.setText("Marca");

        lblColor.setText("Color");

        lblMaterial.setText("Material");

        lblCostoActual.setText("Costo Actual");

        lblCostoAnterior.setText("Costo Anterior");

        lblPrecio.setText("Precio de Venta");

        lblStock.setText("Stock...");

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

        btnTransfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transfer.png"))); // NOI18N
        btnTransfer.setToolTipText("Pasar costo actual a costo anterior.");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblTitleImage.setText("Imagen del Artículo");

        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/image.png"))); // NOI18N
        btnImage.setText("Cambiar imagen");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblArticulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnErase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCostoActual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(lblCostoActual, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTransfer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCostoAnterior)
                            .addComponent(txtCostoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPrecio)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblColor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaterial)
                            .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblidArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitleImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnErase, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAdd))
                    .addComponent(btnEdit)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleImage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblidArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtidArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblArticulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblMarca)
                                            .addComponent(lblColor)
                                            .addComponent(lblMaterial))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblCostoActual)
                                            .addComponent(lblCostoAnterior)
                                            .addComponent(lblPrecio))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCostoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCostoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnTransfer))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStock)
                                .addGap(12, 12, 12)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmar)
                            .addComponent(btnCancelar))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                Searcher.setModal(false);
                Searcher.setVisible(false);
                txtidArticulo.setText(id);
                Recuperar(txtidArticulo.getText());
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
            if (cboxFiltrar.getSelectedItem().equals("Descripción")) {
                filtro = "art.descripcion_articulo";
            } else if (cboxFiltrar.getSelectedItem().equals("Código/ID")) {
                filtro = "art.id_articulo";
            } else if (cboxFiltrar.getSelectedItem().equals("Marca")) {
                filtro = "mar.descripcion_marca";
            } else if (cboxFiltrar.getSelectedItem().equals("Categoría")) {
                filtro = "art.categoria_articulo";
            }
            String SQL_Search = "select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and " + filtro + " ilike '%" + txtFilter.getText() + "%'";
            rs = con.Results(SQL_Search);
            Search.setRowCount(0);
            while (rs.next()) {
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

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Searcher.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            if (Validaciones() == true) {
                try {
                    GuardarDatos();
                } catch (IOException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
                LimpiarTxt();
                RestartCbox();
                DeshabilitarTxt();
                DeshabilitarBtn();
                CheckUserPermissions(Menu.idUsuario);
                Flag = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Flag = 1;
        HabilitarBtn();
        HabilitarTxt();
        lblStock.setText("Stock Inicial");
        txtCostoActual.setText("0");
        txtCostoAnterior.setText("0");
        txtidArticulo.setEnabled(true);
        txtidArticulo.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        HabilitarBtn();
        HabilitarTxt();
        lblStock.setText("Stock Actual");
        txtidArticulo.setEnabled(true);
        txtidArticulo.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        Flag = 3;
        HabilitarBtn();
        lblStock.setText("Stock Actual");
        txtidArticulo.setEnabled(true);
        txtidArticulo.requestFocus();
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            Flag = 0;
            LimpiarTxt();
            DeshabilitarBtn();
            DeshabilitarTxt();
            RestartCbox();
            CheckUserPermissions(Menu.idUsuario);
            lblImage.setIcon(null);
            imagenSeleccionadaTemporal = null;
            lblStock.setText("Stock...");
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        txtCostoAnterior.setText(txtCostoActual.getText());
        txtCostoActual.setText("");
        txtCostoActual.requestFocus();
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            LoadTable();
            lblMensaje.setText("Listado de Artículos");
            if (Flag == 2) {
                lblMensaje.setText("Seleccione un artículo que desee modificar");
            } else if (Flag == 3) {
                lblMensaje.setText("Seleccione un artículo que desee eliminar");
            }
            Searcher.setTitle("Buscar Artículo");
            Searcher.setModal(true);
            Searcher.pack();
            Searcher.setResizable(false);
            Searcher.setLocationRelativeTo(null);
            Searcher.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtidArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidArticuloKeyPressed
        if (evt.getKeyCode() == 10 && !txtidArticulo.getText().equals("")) {
            try {
                Recuperar(txtidArticulo.getText());
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnConfirmar.doClick();
                    } else {
                        btnCancelar.doClick();
                    }
                }
            } catch (SQLException ex) {
            }
        } else if (evt.getKeyCode() == 10 && txtidArticulo.getText().equals("")) {
            try {
                LoadTable();
                if (Flag == 2) {
                    lblMensaje.setText("Seleccione una marca que desee modificar");
                } else if (Flag == 3) {
                    lblMensaje.setText("Seleccione una marca que desee eliminar");
                }
                Searcher.setTitle("Buscar Marcas");
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

    private void cboxFiltrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxFiltrarItemStateChanged
        txtFilter.setText("");
    }//GEN-LAST:event_cboxFiltrarItemStateChanged

    private void cboxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxCategoriaItemStateChanged
        if (cboxCategoria.getSelectedItem().equals("Servicio")) {
            txtColor.setText("-");
            txtColor.setEditable(false);
            txtMaterial.setText("-");
            txtMaterial.setEditable(false);
            txtCostoActual.setText("0");
            txtCostoActual.setEditable(false);
            txtCostoAnterior.setText("0");
            txtCostoAnterior.setEditable(false);
            txtStock.setText("0");
            txtStock.setEditable(false);
            cboxMarca.setSelectedItem("Sin Marca");
            cboxMarca.setEnabled(false);
        } else {
            txtColor.setText("");
            txtColor.setEditable(true);
            txtMaterial.setText("");
            txtMaterial.setEditable(true);
            txtCostoActual.setText("0");
            txtCostoActual.setEditable(true);
            if (Flag == 1) {
                txtCostoAnterior.setText("0");
            }
            txtCostoAnterior.setEditable(false);
            txtStock.setText("");
            txtStock.setEditable(true);
            cboxMarca.setSelectedItem("Selecciona");
            cboxMarca.setEnabled(true);
        }
    }//GEN-LAST:event_cboxCategoriaItemStateChanged

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionadaTemporal = fileChooser.getSelectedFile();

            // Mostrar imagen en el JLabel
            ImageIcon imageIcon = new ImageIcon(imagenSeleccionadaTemporal.getAbsolutePath());
            lblImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_btnImageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Searcher;
    private javax.swing.JTable TSearcher;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JComboBox<String> cboxCategoria;
    private javax.swing.JComboBox<String> cboxFiltrar;
    private javax.swing.JComboBox<String> cboxMarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblArticulo;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblCostoActual;
    private javax.swing.JLabel lblCostoAnterior;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMaterial;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitleImage;
    private javax.swing.JLabel lblidArticulo;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtCostoActual;
    private javax.swing.JTextField txtCostoAnterior;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtMaterial;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtidArticulo;
    // End of variables declaration//GEN-END:variables
}
