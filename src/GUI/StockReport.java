package GUI;

import Otros.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StockReport extends javax.swing.JPanel {
    
    Conexion con;
    ResultSet rs;

    public StockReport() throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        rbtnAll.setSelected(true);
        chbArtNull.setSelected(true);
        LoadCboxBrand();
    }
    
    private void AllSelected(){
        cbByBrand.setEnabled(false);
        chbProduct.setEnabled(false);
        chbCristal.setEnabled(false);
        chbService.setEnabled(false);
    }
    
    private void BrandSelected(){
        cbByBrand.setEnabled(true);
        cbByBrand.setSelectedIndex(0);
        chbProduct.setEnabled(false);
        chbCristal.setEnabled(false);
        chbService.setEnabled(false);
    }
    
    private void CategorySelected(){
        cbByBrand.setEnabled(false);
        chbProduct.setEnabled(true);
        chbCristal.setEnabled(true);
        chbService.setEnabled(true);
        chbProduct.setSelected(true);
        chbCristal.setSelected(true);
        chbService.setSelected(true);
    }
    
    private void LoadCboxBrand() throws SQLException {
        cbByBrand.removeAllItems();
        cbByBrand.addItem("Selecciona");
        rs = con.Results("select descripcion_marca from marca order by descripcion_marca asc;");
        while (rs.next()) {
            cbByBrand.addItem(rs.getString("descripcion_marca"));
        }
    }
    
    private void ViewReport(String brand_name, String art_product, String art_cristal, String art_service, Boolean stock_art) {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("brand_name", brand_name);
            parametros.put("art_product", art_product);
            parametros.put("art_cristal", art_cristal);
            parametros.put("art_service", art_service);
            parametros.put("stock_art", stock_art);
            System.out.println("Parámetros: " + parametros);
            System.out.println("Datasource: " + con.Login());
            URL urlReport = getClass().getClassLoader().getResource("Reportes/StockReport.jasper");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFilter = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        rbtnAll = new javax.swing.JRadioButton();
        rbtnByBrand = new javax.swing.JRadioButton();
        cbByBrand = new javax.swing.JComboBox<>();
        rbtnByCategory = new javax.swing.JRadioButton();
        chbProduct = new javax.swing.JCheckBox();
        chbCristal = new javax.swing.JCheckBox();
        chbService = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        chbArtNull = new javax.swing.JCheckBox();
        btnRepGenerate = new javax.swing.JButton();

        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        lblTitle.setText("Selecciona el filtro para el reporte:");

        bgFilter.add(rbtnAll);
        rbtnAll.setText("Todos los artículos");
        rbtnAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnAllStateChanged(evt);
            }
        });

        bgFilter.add(rbtnByBrand);
        rbtnByBrand.setText("Por Marca:");
        rbtnByBrand.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnByBrandStateChanged(evt);
            }
        });

        bgFilter.add(rbtnByCategory);
        rbtnByCategory.setText("Por Categoría:");
        rbtnByCategory.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnByCategoryStateChanged(evt);
            }
        });

        chbProduct.setText("Producto");

        chbCristal.setText("Cristal");

        chbService.setText("Servicio");

        chbArtNull.setText("Incluir artículos sin existencia");

        btnRepGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printer.png"))); // NOI18N
        btnRepGenerate.setText("Generar Reporte");
        btnRepGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepGenerateActionPerformed(evt);
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
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle)
                                .addComponent(rbtnAll)
                                .addComponent(rbtnByCategory)
                                .addComponent(rbtnByBrand)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(chbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chbCristal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chbService, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cbByBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chbArtNull))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRepGenerate)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(rbtnAll)
                .addGap(18, 18, 18)
                .addComponent(rbtnByBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbByBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnByCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbProduct)
                    .addComponent(chbCristal)
                    .addComponent(chbService))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbArtNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepGenerate)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRepGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepGenerateActionPerformed
        if (rbtnAll.isSelected()) {
            String BrandName = "null";
            String ArtProduct = "Producto";
            String ArtCristal = "Cristal";
            String ArtService = "Servicio";
            Boolean ArtNull = chbArtNull.isSelected();
            ViewReport(BrandName, ArtProduct, ArtCristal, ArtService, ArtNull);
        } else if (rbtnByBrand.isSelected()) {
            String BrandName = cbByBrand.getSelectedItem().toString();
            String ArtProduct = "Producto";
            String ArtCristal = "Cristal";
            String ArtService = "Servicio";
            Boolean ArtNull = chbArtNull.isSelected();
            ViewReport(BrandName, ArtProduct, ArtCristal, ArtService, ArtNull);
        } else if (rbtnByCategory.isSelected()) {
            String BrandName = "null";
            String ArtProduct = "Producto";
            String ArtCristal = "Cristal";
            String ArtService = "Servicio";
            Boolean ArtNull = chbArtNull.isSelected();
            ViewReport(BrandName, ArtProduct, ArtCristal, ArtService, ArtNull);
        }
    }//GEN-LAST:event_btnRepGenerateActionPerformed

    private void rbtnAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnAllStateChanged
        if (rbtnAll.isSelected()) {
            AllSelected();
        }
    }//GEN-LAST:event_rbtnAllStateChanged

    private void rbtnByBrandStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnByBrandStateChanged
        if (rbtnByBrand.isSelected()) {
            BrandSelected();
        }
    }//GEN-LAST:event_rbtnByBrandStateChanged

    private void rbtnByCategoryStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnByCategoryStateChanged
        if (rbtnByCategory.isSelected()) {
            CategorySelected();
        }
    }//GEN-LAST:event_rbtnByCategoryStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgFilter;
    private javax.swing.JButton btnRepGenerate;
    private javax.swing.JComboBox<String> cbByBrand;
    private javax.swing.JCheckBox chbArtNull;
    private javax.swing.JCheckBox chbCristal;
    private javax.swing.JCheckBox chbProduct;
    private javax.swing.JCheckBox chbService;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rbtnAll;
    private javax.swing.JRadioButton rbtnByBrand;
    private javax.swing.JRadioButton rbtnByCategory;
    // End of variables declaration//GEN-END:variables
}
