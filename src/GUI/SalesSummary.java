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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SalesSummary extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    /* DefaultTableModel SearchCliente = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }; */
    
    public SalesSummary() {
        initComponents();
        con = new Conexion();
        con.Login();
        // CabeceraTablaCliente();
        jDateFirst.setDateFormatString("yyyy-MM-dd");
        jDateLast.setDateFormatString("yyyy-MM-dd");
        // SeleccionarChbox();
    }
    
    /* private void RecuperarCliente(String ci) throws SQLException{
        String SQL_Recuperar = "select * from cliente where ci_cliente='"+String.valueOf(ci)+"'";
        rs = con.Results(SQL_Recuperar);
        if(rs.next()){
            txtNroCliente.setText(rs.getString("id_cliente"));
            txtCliente.setText(rs.getString("nombre_cliente")+" "+rs.getString("apellido_cliente"));
        }else{
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el CI/RUC Nro. "+txtCi.getText());
            txtCliente.setText("");
            txtNroCliente.setText("");
            txtCi.setText("");
            txtCi.requestFocus();
        }
    }
    private void SeleccionarChbox(){
        chboxAbierto.setSelected(true);
        chboxCerrado.setSelected(true);
        chboxAnulado.setSelected(true);
    }
    
    private void RecuperarClientePorID(String id) throws SQLException{
        String SQL_Recuperar = "select * from cliente where id_cliente='"+String.valueOf(id)+"' and menordeedad=false";
        rs = con.Results(SQL_Recuperar);
        if(rs.next()){
            txtCi.setText(rs.getString("ci_cliente"));
            txtCliente.setText(rs.getString("nombre_cliente")+" "+rs.getString("apellido_cliente"));
        }else{
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el ID Nro. "+txtNroCliente.getText());
            txtCliente.setText("");
            txtCi.setText("");
            txtNroCliente.setText("");
            txtNroCliente.requestFocus();
        }
    }
    
    private void CabeceraTablaCliente(){
        SearchCliente.addColumn("ID");
        SearchCliente.addColumn("CI/RUC");
        SearchCliente.addColumn("Nombres");
        SearchCliente.addColumn("Apellidos");
        SearchCliente.addColumn("Menor");
        SearchCliente.addColumn("Teléfono");
        SearchCliente.addColumn("Ciudad");
        SearchCliente.addColumn("Dirección");
        TSearcherCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        TSearcherCliente.getColumnModel().getColumn(1).setPreferredWidth(80);
        TSearcherCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        TSearcherCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        TSearcherCliente.getColumnModel().getColumn(4).setPreferredWidth(30);
        TSearcherCliente.getColumnModel().getColumn(5).setPreferredWidth(60);
        TSearcherCliente.getColumnModel().getColumn(6).setPreferredWidth(60);
        TSearcherCliente.getColumnModel().getColumn(7).setPreferredWidth(150);
    }
    
    private void CargarTablaClientes() throws SQLException{
        String SQLCliente = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and menordeedad=false";
        rs = con.Results(SQLCliente);
        SearchCliente.setRowCount(0);
        while(rs.next()){
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
    } */
    
    private void ViewReport(/*String ftr_ci,*/Date filter_datefirst, Date filter_datelast, String filter_open, String filter_close, String filter_null){
        try {
            HashMap parametros = new HashMap();
            //parametros.put("ci", ftr_ci);
            parametros.put("date_first", filter_datefirst);
            parametros.put("date_last", filter_datelast);
            /* parametros.put("fabierto", filter_open);
            parametros.put("fcerrado", filter_close);
            parametros.put("fanulado", filter_null); */
            URL urlReport = getClass().getClassLoader().getResource("Reportes/SalesReport.jasper");
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
    
    private Boolean Validaciones(){
        /* if (txtNroCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo N° Cliente está vacío, ingrese un valor antes de continuar.");
            return false;
        }
        if (txtCi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo CI del Cliente está vacío, ingrese un valor antes de continuar.");
            return false;
        }
        if (chboxAllDates.isSelected() == false) {
            if (jDateFirst.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Fecha Desde está vacío, ingrese una fecha de inicio antes de continuar.");
                return false;
            }
            if (jDateLast.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Fecha Hasta está vacío, ingrese una fecha de fin antes de continuar.");
                return false;
            }
        }*/
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateFirst = new com.toedter.calendar.JDateChooser();
        jDateLast = new com.toedter.calendar.JDateChooser();
        btnGenerar = new javax.swing.JButton();

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Desde");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Hasta");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Rango de Fechas");

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printer.png"))); // NOI18N
        btnGenerar.setText("Generar Reporte");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGenerar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jDateLast, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateFirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (Validaciones() == true) {
            String filter_open = "";
            String filter_close = "";
            String filter_null = "";
            /* if (chboxAbierto.isSelected() == true) {
                if (chboxCerrado.isSelected() == true) {
                    if (chboxAnulado.isSelected() == true) {
                        ftr_abierto = "Abierto";
                        ftr_cerrado = "Cerrado";
                        ftr_anulado = "Anulado";
                    } else {
                        ftr_abierto = "Abierto";
                        ftr_cerrado = "Cerrado";
                    }
                } else if (chboxAnulado.isSelected() == true) {
                    ftr_abierto = "Abierto";
                    ftr_anulado = "Anulado";
                } else {
                    ftr_abierto = "Abierto";
                }
            } else if (chboxCerrado.isSelected() == true) {
                if (chboxAnulado.isSelected() == true) {
                    ftr_cerrado = "Cerrado";
                    ftr_anulado = "Anulado";
                } else {
                    ftr_cerrado = "Cerrado";
                }
            } else if (chboxAnulado.isSelected() == true) {
                ftr_anulado = "Anulado";
            } else {
                JOptionPane.showMessageDialog(null, "Hay algo raro aquí, esto no debería pasar D:");
            } */
            Date datefirst = null;
            Date datelast = null;
            /* if (chboxAllDates.isSelected() == true) {
                try {
                    String fdesde = "2000-01-01";
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                    ftr_fdesde = formatter1.parse(fdesde);
                    ftr_fhasta = java.sql.Date.valueOf(LocalDate.now());
                } catch (ParseException ex) {
                    Logger.getLogger(ListadoFicha.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ftr_fdesde = (Date) jDateFirst.getDate();
                ftr_fhasta = (Date) jDateLast.getDate();
            } */
            // String ftr_ci = txtCi.getText();
            // System.out.println("Filtro CI:     ("+ftr_ci+")");
            datefirst = (Date) jDateFirst.getDate();
            datelast = (Date) jDateLast.getDate();
            System.out.println("Filtro Desde:  ("+datefirst+")");
            System.out.println("Filtro Hasta:  ("+datelast+")");
            System.out.println("Filtro Estado: ("+filter_open+", "+filter_close+", "+filter_null+")");
            // ViewReport(ftr_ci, ftr_fdesde, ftr_fhasta, ftr_abierto, ftr_cerrado, ftr_anulado);
            ViewReport(/*ftr_ci,*/datefirst, datelast, filter_open, filter_close, filter_null);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private com.toedter.calendar.JDateChooser jDateFirst;
    private com.toedter.calendar.JDateChooser jDateLast;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
