package GUI;

import Otros.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Menu extends javax.swing.JFrame {

    Conexion con;
    ResultSet rs;
    static JDialog contenedor = null;
    public static int idUsuario = 0;

    public Menu(String Username, int idUsuario) throws SQLException {
        initComponents();
        this.idUsuario = idUsuario;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        URL url = getClass().getResource("/images/icon.png");
        ImageIcon imgicon = new ImageIcon(url);
        this.setIconImage(imgicon.getImage());
        lblWelcome.setText("Bienvenido, " + Username + "!");
        con = new Conexion();
        con.Login();
        DisableAllBtns();
        CheckUserPermissions(idUsuario);
    }

    private void CheckUserPermissions(int idUser) throws SQLException {
        String SQLUserCheck = "SELECT up.id_permission, per.name_permission, up.active FROM users_permissions up, permissions per WHERE up.id_permission = per.id_permission AND up.id_permission IN (1,6,11,16,20,25,30,35,41,42,43) AND id_usuario = " + idUser + ";";
        rs = con.Results(SQLUserCheck);
        while (rs.next()) {
            int idPermission = rs.getInt("id_permission");
            String namePermission = rs.getString("name_permission");
            Boolean isActive = rs.getBoolean("active");
            if (isActive) {
                switch (namePermission) {
                    case "FichaMain":
                        btnFicha.setEnabled(true);
                        itemFicha.setEnabled(true);
                        break;
                    case "CliMain":
                        btnClientes.setEnabled(true);
                        itemClientes.setEnabled(true);
                        break;
                    case "ArtMain":
                        btnArticulos.setEnabled(true);
                        itemArticulos.setEnabled(true);
                        break;
                    case "StockMain":
                        btnIngresarStock.setEnabled(true);
                        itemStock.setEnabled(true);
                        break;
                    case "CityMain":
                        btnCiudades.setEnabled(true);
                        itemCiudades.setEnabled(true);
                        break;
                    case "BrandMain":
                        btnMarcas.setEnabled(true);
                        itemMarcas.setEnabled(true);
                        break;
                    case "PayMain":
                        btnPayMethod.setEnabled(true);
                        itemPayMethod.setEnabled(true);
                        break;
                    case "UserMain":
                        btnUser.setEnabled(true);
                        itemUser.setEnabled(true);
                        break;
                    case "ReportFicha":
                        btnFindFicha.setEnabled(true);
                        itemSearchOT.setEnabled(true);
                        break;
                    case "ReportSales":
                        btnSaleSummary.setEnabled(true);
                        itemSaleSummary.setEnabled(true);
                        break;
                    case "ReportStock":
                        btnStockReport.setEnabled(true);
                        itemStockReport.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso desconocido: "+idPermission+", "+namePermission);
                }
            }
        }
    }
    
    private void DisableAllBtns(){
        btnFicha.setEnabled(false);
        btnClientes.setEnabled(false);
        btnFindFicha.setEnabled(false);
        btnSaleSummary.setEnabled(false);
        btnStockReport.setEnabled(false);
        btnArticulos.setEnabled(false);
        btnIngresarStock.setEnabled(false);
        btnCiudades.setEnabled(false);
        btnMarcas.setEnabled(false);
        btnPayMethod.setEnabled(false);
        btnUser.setEnabled(false);
        itemFicha.setEnabled(false);
        itemClientes.setEnabled(false);
        itemArticulos.setEnabled(false);
        itemStock.setEnabled(false);
        itemSearchOT.setEnabled(false);
        itemSaleSummary.setEnabled(false);
        itemStockReport.setEnabled(false);
        itemCiudades.setEnabled(false);
        itemMarcas.setEnabled(false);
        itemPayMethod.setEnabled(false);
        itemUser.setEnabled(false);
    }

    private Menu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnFicha = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnFindFicha = new javax.swing.JButton();
        btnSaleSummary = new javax.swing.JButton();
        btnStockReport = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnArticulos = new javax.swing.JButton();
        btnIngresarStock = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnCiudades = new javax.swing.JButton();
        btnMarcas = new javax.swing.JButton();
        btnPayMethod = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuOp = new javax.swing.JMenu();
        itemFicha = new javax.swing.JMenuItem();
        itemClientes = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemArticulos = new javax.swing.JMenuItem();
        itemStock = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemSearchOT = new javax.swing.JMenuItem();
        itemSaleSummary = new javax.swing.JMenuItem();
        itemStockReport = new javax.swing.JMenuItem();
        menuRef = new javax.swing.JMenu();
        itemCiudades = new javax.swing.JMenuItem();
        itemMarcas = new javax.swing.JMenuItem();
        itemPayMethod = new javax.swing.JMenuItem();
        itemUser = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        itemChangelog = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Bienvenido");

        jLabel2.setText("Clientes/Pacientes");

        btnFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card.png"))); // NOI18N
        btnFicha.setText("Ficha de Cliente");
        btnFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFichaActionPerformed(evt);
            }
        });

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnFindFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnFindFicha.setText("Buscar Ficha/OT");
        btnFindFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindFichaActionPerformed(evt);
            }
        });

        btnSaleSummary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSaleSummary.setText("Resumen de Ventas");
        btnSaleSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleSummaryActionPerformed(evt);
            }
        });

        btnStockReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnStockReport.setText("Reporte de Existencia");
        btnStockReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockReportActionPerformed(evt);
            }
        });

        jLabel3.setText("Artículos");

        btnArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delivery.png"))); // NOI18N
        btnArticulos.setText("Artículos");
        btnArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArticulosActionPerformed(evt);
            }
        });

        btnIngresarStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addbox.png"))); // NOI18N
        btnIngresarStock.setText("Stock de Artículos");
        btnIngresarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarStockActionPerformed(evt);
            }
        });

        jLabel1.setText("Configuración");

        btnCiudades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/city.png"))); // NOI18N
        btnCiudades.setText("Ciudades");
        btnCiudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCiudadesActionPerformed(evt);
            }
        });

        btnMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/price-tag.png"))); // NOI18N
        btnMarcas.setText("Marcas");
        btnMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcasActionPerformed(evt);
            }
        });

        btnPayMethod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        btnPayMethod.setText("Métodos de Pago");
        btnPayMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayMethodActionPerformed(evt);
            }
        });

        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contrasena.png"))); // NOI18N
        btnUser.setText("Usuarios");
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnLogout.setText("Cerrar Sesión");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        btnExit.setText("Salir del Sistema");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        menuOp.setText("Operaciones");

        itemFicha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/id-card.png"))); // NOI18N
        itemFicha.setText("Ficha de Cliente");
        itemFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFichaActionPerformed(evt);
            }
        });
        menuOp.add(itemFicha);

        itemClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        itemClientes.setText("Clientes");
        itemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientesActionPerformed(evt);
            }
        });
        menuOp.add(itemClientes);
        menuOp.add(jSeparator3);

        itemArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delivery.png"))); // NOI18N
        itemArticulos.setText("Artículos");
        itemArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemArticulosActionPerformed(evt);
            }
        });
        menuOp.add(itemArticulos);

        itemStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addbox.png"))); // NOI18N
        itemStock.setText("Ingresar Stock");
        itemStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStockActionPerformed(evt);
            }
        });
        menuOp.add(itemStock);

        jMenuBar1.add(menuOp);

        jMenu1.setText("Reportes");

        itemSearchOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        itemSearchOT.setText("Buscar Ficha/OT");
        itemSearchOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSearchOTActionPerformed(evt);
            }
        });
        jMenu1.add(itemSearchOT);

        itemSaleSummary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        itemSaleSummary.setText("Resumen de Ventas");
        itemSaleSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaleSummaryActionPerformed(evt);
            }
        });
        jMenu1.add(itemSaleSummary);

        itemStockReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        itemStockReport.setText("Reporte de Existencia");
        itemStockReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStockReportActionPerformed(evt);
            }
        });
        jMenu1.add(itemStockReport);

        jMenuBar1.add(jMenu1);

        menuRef.setText("Configuración");
        menuRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRefActionPerformed(evt);
            }
        });

        itemCiudades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/city.png"))); // NOI18N
        itemCiudades.setText("Ciudades");
        itemCiudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCiudadesActionPerformed(evt);
            }
        });
        menuRef.add(itemCiudades);

        itemMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/price-tag.png"))); // NOI18N
        itemMarcas.setText("Marcas");
        itemMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMarcasActionPerformed(evt);
            }
        });
        menuRef.add(itemMarcas);

        itemPayMethod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        itemPayMethod.setText("Métodos de Pago");
        menuRef.add(itemPayMethod);

        itemUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contrasena.png"))); // NOI18N
        itemUser.setText("Usuarios");
        itemUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUserActionPerformed(evt);
            }
        });
        menuRef.add(itemUser);

        jMenuBar1.add(menuRef);

        menuHelp.setText("Ayuda");

        itemChangelog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gear.png"))); // NOI18N
        itemChangelog.setText("Changelog");
        itemChangelog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChangelogActionPerformed(evt);
            }
        });
        menuHelp.add(itemChangelog);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIngresarStock, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFindFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaleSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStockReport, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFicha)
                    .addComponent(btnClientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindFicha)
                    .addComponent(btnSaleSummary)
                    .addComponent(btnStockReport))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArticulos)
                    .addComponent(btnIngresarStock))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCiudades)
                    .addComponent(btnMarcas)
                    .addComponent(btnPayMethod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnLogout))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcasActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Marca(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Marcas");
            URL url = getClass().getResource("/images/icon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMarcasActionPerformed

    private void itemMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMarcasActionPerformed
        btnMarcas.doClick();
    }//GEN-LAST:event_itemMarcasActionPerformed

    private void btnCiudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCiudadesActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Ciudad(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Ciudades");
            URL url = getClass().getResource("/images/icon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCiudadesActionPerformed

    private void itemCiudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCiudadesActionPerformed
        btnCiudades.doClick();
    }//GEN-LAST:event_itemCiudadesActionPerformed

    private void btnArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArticulosActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Articulo(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Articulos");
            URL url = getClass().getResource("/images/boxicon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnArticulosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Cliente(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Clientes");
            URL url = getClass().getResource("/images/usericon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFichaActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new FichaCliente(idUsuario));
            contenedor.setModal(false);
            contenedor.setTitle("Ficha del Cliente");
            URL url = getClass().getResource("/images/idcardicon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFichaActionPerformed

    private void btnIngresarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarStockActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Stock(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Ingresar Stock");
            URL url = getClass().getResource("/images/addicon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIngresarStockActionPerformed

    private void itemFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFichaActionPerformed
        btnFicha.doClick();
    }//GEN-LAST:event_itemFichaActionPerformed

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientesActionPerformed
        btnClientes.doClick();
    }//GEN-LAST:event_itemClientesActionPerformed

    private void itemArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemArticulosActionPerformed
        btnArticulos.doClick();
    }//GEN-LAST:event_itemArticulosActionPerformed

    private void itemStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStockActionPerformed
        btnIngresarStock.doClick();
    }//GEN-LAST:event_itemStockActionPerformed

    private void btnFindFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindFichaActionPerformed
        contenedor = new JDialog();
        contenedor.getContentPane().add(new ListadoFicha());
        contenedor.setModal(false);
        contenedor.setTitle("Buscar Ficha");
        URL url = getClass().getResource("/images/searchicon.png");
        ImageIcon imgicon = new ImageIcon(url);
        contenedor.setIconImage(imgicon.getImage());
        contenedor.pack();
        contenedor.setLocationRelativeTo(null);
        contenedor.setResizable(false);
        contenedor.setVisible(true);
    }//GEN-LAST:event_btnFindFichaActionPerformed

    private void itemChangelogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChangelogActionPerformed
        contenedor = new JDialog();
        contenedor.getContentPane().add(new Changelog());
        contenedor.setModal(false);
        contenedor.setTitle("Changelog (Registro de cambios)");
        URL url = getClass().getResource("/images/icon.png");
        ImageIcon imgicon = new ImageIcon(url);
        contenedor.setIconImage(imgicon.getImage());
        contenedor.pack();
        contenedor.setLocationRelativeTo(null);
        contenedor.setResizable(false);
        contenedor.setVisible(true);
    }//GEN-LAST:event_itemChangelogActionPerformed

    private void btnPayMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayMethodActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new PayMethods(idUsuario));
            contenedor.setModal(true);
            contenedor.setTitle("Métodos de Pago");
            URL url = getClass().getResource("/images/icon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPayMethodActionPerformed

    private void btnSaleSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleSummaryActionPerformed
        contenedor = new JDialog();
        contenedor.getContentPane().add(new SalesSummary());
        contenedor.setModal(false);
        contenedor.setTitle("Resumen de Ventas");
        URL url = getClass().getResource("/images/searchicon.png");
        ImageIcon imgicon = new ImageIcon(url);
        contenedor.setIconImage(imgicon.getImage());
        contenedor.pack();
        contenedor.setLocationRelativeTo(null);
        contenedor.setResizable(false);
        contenedor.setVisible(true);
    }//GEN-LAST:event_btnSaleSummaryActionPerformed

    private void itemSearchOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSearchOTActionPerformed
        btnFindFicha.doClick();
    }//GEN-LAST:event_itemSearchOTActionPerformed

    private void itemSaleSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaleSummaryActionPerformed
        btnSaleSummary.doClick();
    }//GEN-LAST:event_itemSaleSummaryActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new Users(idUsuario));
            contenedor.setModal(false);
            contenedor.setTitle("Usuarios");
            URL url = getClass().getResource("/images/idcardicon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        JFrame loginFrame = new Login();
        loginFrame.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void itemStockReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStockReportActionPerformed
        btnStockReport.doClick();
    }//GEN-LAST:event_itemStockReportActionPerformed

    private void menuRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefActionPerformed
        btnPayMethod.doClick();
    }//GEN-LAST:event_menuRefActionPerformed

    private void itemUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUserActionPerformed
        btnUser.doClick();
    }//GEN-LAST:event_itemUserActionPerformed

    private void btnStockReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockReportActionPerformed
        try {
            contenedor = new JDialog();
            contenedor.getContentPane().add(new StockReport());
            contenedor.setModal(false);
            contenedor.setTitle("Reporte de Exitencia de Artículos");
            URL url = getClass().getResource("/images/searchicon.png");
            ImageIcon imgicon = new ImageIcon(url);
            contenedor.setIconImage(imgicon.getImage());
            contenedor.pack();
            contenedor.setLocationRelativeTo(null);
            contenedor.setResizable(false);
            contenedor.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStockReportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArticulos;
    private javax.swing.JButton btnCiudades;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFicha;
    private javax.swing.JButton btnFindFicha;
    private javax.swing.JButton btnIngresarStock;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMarcas;
    private javax.swing.JButton btnPayMethod;
    private javax.swing.JButton btnSaleSummary;
    private javax.swing.JButton btnStockReport;
    private javax.swing.JButton btnUser;
    private javax.swing.JMenuItem itemArticulos;
    private javax.swing.JMenuItem itemChangelog;
    private javax.swing.JMenuItem itemCiudades;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemFicha;
    private javax.swing.JMenuItem itemMarcas;
    private javax.swing.JMenuItem itemPayMethod;
    private javax.swing.JMenuItem itemSaleSummary;
    private javax.swing.JMenuItem itemSearchOT;
    private javax.swing.JMenuItem itemStock;
    private javax.swing.JMenuItem itemStockReport;
    private javax.swing.JMenuItem itemUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuOp;
    private javax.swing.JMenu menuRef;
    // End of variables declaration//GEN-END:variables
}
