package GUI;

import Otros.Conexion;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FichaCliente extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    int TagSearch = 0;
    DefaultTableModel SearchCliente = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel SearchArticulo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel SearchOT = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel DetalleArt = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel PayMethod = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel ConsultOT = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel ConsultOTDetail = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel ConsultOTPay = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public FichaCliente(List<Menu.Permission> permissions) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        DeshabilitarBtn1();
        DeshabilitarBtn2();
        DeshabilitarTxt1();
        DeshabilitarTxt2();
        NoEditTxt();
        ConsultNoEditTxt();
        CabeceraTablaDetalleArticulo();
        CabeceraTablaArticulo();
        CabeceraTablaCliente();
        CabeceraTablaOT();
        CabeceraTablaPayMethod();
        AddConsultOTColumn();
        AddConsultOTDetailColumn();
        AddConsultOTPayColumn();
        CargarCboxPMethod();
        CheckUserPermissions(permissions);
        dtOTDate.setDateFormatString("dd/MM/yyyy");
        btnPayDelete.setRequestFocusEnabled(false);
    }

    private void CheckUserPermissions(List<Menu.Permission> permissions) throws SQLException {
        DeshabilitarMainBtn();
        for (Menu.Permission permission : permissions) {
            if (permission.isActivePerm()) {
                switch (permission.getNamePerm()) {
                    case "FichaCreate":
                        btnOTNew.setEnabled(true);
                        break;
                    case "FichaEdit":
                        btnOTEdit.setEnabled(true);
                        break;
                    case "FichaNull":
                        btnOTNull.setEnabled(true);
                        btnOTREOpen.setEnabled(true);
                        break;
                    case "FichaClose":
                        btnOTClose.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso no utilizado o no concedido: " + permission.getPermID() + ", " + permission.getNamePerm());
                }
            }
        }
    }

    private void DeshabilitarMainBtn() {
        btnOTNew.setEnabled(false);
        btnOTEdit.setEnabled(false);
        btnOTNull.setEnabled(false);
        btnOTREOpen.setEnabled(false);
        btnOTClose.setEnabled(false);
    }

    private void HabilitarBtn1() {
        btnOTNew.setEnabled(false);
        btnOTEdit.setEnabled(false);
        btnOTNull.setEnabled(false);
        btnOTClose.setEnabled(false);
        btnSearchOT.setEnabled(true);
        btnSearchSol.setEnabled(true);
        btnClearSol.setEnabled(true);
        btnCopy.setEnabled(true);
        btnSearchPa.setEnabled(true);
        btnClearPa.setEnabled(true);
        chboxMenor.setEnabled(false);
        btnConfirmarCli.setEnabled(true);
        btnOTConfirm.setEnabled(true);
        btnOTCancel.setEnabled(true);
    }

    private void HabilitarBtn2() {
        btnCristalSearch.setEnabled(true);
        btnCristClean.setEnabled(true);
        btnArtSearch.setEnabled(true);
        btnArtAdd.setEnabled(true);
        btnArtClean.setEnabled(true);
        btnArtRemove.setEnabled(true);
        btnPayAdd.setEnabled(true);
        btnPayDelete.setEnabled(true);
    }

    private void DeshabilitarBtn1() {
        btnOTNew.setEnabled(true);
        btnOTEdit.setEnabled(true);
        btnOTNull.setEnabled(true);
        btnOTClose.setEnabled(true);
        btnSearchOT.setEnabled(false);
        btnSearchSol.setEnabled(false);
        btnClearSol.setEnabled(false);
        btnCopy.setEnabled(false);
        btnSearchPa.setEnabled(false);
        btnClearPa.setEnabled(false);
        chboxMenor.setEnabled(false);
        btnConfirmarCli.setEnabled(false);
        btnOTConfirm.setEnabled(false);
        btnOTCancel.setEnabled(false);
    }

    private void DeshabilitarBtn2() {
        btnCristalSearch.setEnabled(false);
        btnCristClean.setEnabled(false);
        btnArtSearch.setEnabled(false);
        btnArtAdd.setEnabled(false);
        btnArtClean.setEnabled(false);
        btnArtRemove.setEnabled(false);
        btnPayAdd.setEnabled(false);
        btnPayDelete.setEnabled(false);
    }

    private void HabilitarTxt1() {
        txtOTNro.setEnabled(true);
        txtOTState.setEnabled(true);
        dtOTDate.setEnabled(true);
        txtCiSol.setEnabled(true);
        txtCiSol.setEditable(true);
        txtClienteSol.setEnabled(true);
        txtCiPa.setEnabled(true);
        txtCiPa.setEditable(true);
        txtClientePa.setEnabled(true);
    }

    private void HabilitarTxt2() {
        txtOIEsferico.setEnabled(true);
        txtODEsferico.setEnabled(true);
        txtOICilindrico.setEnabled(true);
        txtODCilindrico.setEnabled(true);
        txtOIEje.setEnabled(true);
        txtODEje.setEnabled(true);
        txtOIAdicion.setEnabled(true);
        txtODAdicion.setEnabled(true);
        spinOICantidad.setEnabled(true);
        spinODCantidad.setEnabled(true);
        txtDI.setEnabled(true);
        txtDND.setEnabled(true);
        txtDNI.setEnabled(true);
        txtAlturaFocal.setEnabled(true);
        txtCristalCode.setEnabled(true);
        txtCristalDescr.setEnabled(true);
        txtCristalPrice.setEnabled(true);
        txtObservacion.setEnabled(true);
        txtArtCode.setEnabled(true);
        txtArtDescr.setEnabled(true);
        txtArtUnitPrice.setEnabled(true);
        spinArtQuantity.setEnabled(true);
        txtArtSubtotal.setEnabled(true);
        TSearcherDetalle.setEnabled(true);
        cboxPayMethod.setEnabled(true);
        txtPayMount.setEnabled(true);
        txtPayCompr.setEnabled(true);
        txtArtTotal.setEnabled(true);
        txtCristalTotal.setEnabled(true);
        txtOTSubtotal.setEnabled(true);
        txtSena.setEnabled(true);
        txtSaldo.setEnabled(true);
    }

    private void DeshabilitarTxt1() {
        txtOTNro.setEnabled(false);
        txtOTState.setEnabled(false);
        dtOTDate.setEnabled(false);
        txtCiSol.setEnabled(false);
        txtClienteSol.setEnabled(false);
        txtCiPa.setEnabled(false);
        txtClientePa.setEnabled(false);
    }

    private void DeshabilitarClientPanel() {
        txtCiSol.setEditable(false);
        txtClienteSol.setEditable(false);
        txtCiPa.setEditable(false);
        txtClientePa.setEditable(false);
        btnSearchSol.setEnabled(false);
        btnClearSol.setEnabled(false);
        btnSearchPa.setEnabled(false);
        btnClearPa.setEnabled(false);
        btnCopy.setEnabled(false);
        btnConfirmarCli.setEnabled(false);
    }

    private void DeshabilitarTxt2() {
        txtOIEsferico.setEnabled(false);
        txtODEsferico.setEnabled(false);
        txtOICilindrico.setEnabled(false);
        txtODCilindrico.setEnabled(false);
        txtOIEje.setEnabled(false);
        txtODEje.setEnabled(false);
        txtOIAdicion.setEnabled(false);
        txtODAdicion.setEnabled(false);
        spinOICantidad.setEnabled(false);
        spinODCantidad.setEnabled(false);
        txtDI.setEnabled(false);
        txtDND.setEnabled(false);
        txtDNI.setEnabled(false);
        txtAlturaFocal.setEnabled(false);
        txtCristalCode.setEnabled(false);
        txtCristalDescr.setEnabled(false);
        txtCristalPrice.setEnabled(false);
        txtObservacion.setEnabled(false);
        txtArtCode.setEnabled(false);
        txtArtDescr.setEnabled(false);
        txtArtUnitPrice.setEnabled(false);
        spinArtQuantity.setEnabled(false);
        txtArtSubtotal.setEnabled(false);
        TSearcherDetalle.setEnabled(false);
        cboxPayMethod.setEnabled(false);
        txtPayMount.setEnabled(false);
        txtPayCompr.setEnabled(false);
        txtArtTotal.setEnabled(false);
        txtCristalTotal.setEnabled(false);
        txtOTSubtotal.setEnabled(false);
        txtSena.setEnabled(false);
        txtSaldo.setEnabled(false);
    }

    private void LimpiarTxt() {
        txtOTNro.setText("");
        txtOTState.setText("");
        dtOTDate.setDate(null);
        txtCiSol.setText("");
        txtClienteSol.setText("");
        txtCiPa.setText("");
        txtClientePa.setText("");
        chboxMenor.setSelected(false);
    }

    private void LimpiarTxt2() {
        txtOIEsferico.setText("");
        txtODEsferico.setText("");
        txtOICilindrico.setText("");
        txtODCilindrico.setText("");
        txtOIEje.setText("");
        txtODEje.setText("");
        txtOIAdicion.setText("");
        txtODAdicion.setText("");
        spinOICantidad.setValue(0);
        spinODCantidad.setValue(0);
        txtDI.setText("");
        txtDND.setText("");
        txtDNI.setText("");
        txtAlturaFocal.setText("");
        txtCristalCode.setText("");
        txtCristalDescr.setText("");
        txtCristalPrice.setText("");
        txtObservacion.setText("");
        txtArtCode.setText("");
        txtArtDescr.setText("");
        spinArtQuantity.setValue(0);
        txtArtSubtotal.setText("");
        txtArtUnitPrice.setText("");
        lblImage.setIcon(null);
        DetalleArt.setRowCount(0);
        cboxPayMethod.setSelectedIndex(0);
        PayMethod.setRowCount(0);
        txtPayMount.setText("");
        txtPayCompr.setText("");
        txtArtTotal.setText("");
        txtCristalTotal.setText("");
        txtOTSubtotal.setText("");
        txtSena.setText("");
        txtSaldo.setText("");
    }

    private void NoEditTxt() {
        txtClienteSol.setEditable(false);
        txtClientePa.setEditable(false);
        txtOTState.setEditable(false);
        txtOTSubtotal.setEditable(false);
        txtCristalDescr.setEditable(false);
        txtCristalTotal.setEditable(false);
        txtArtDescr.setEditable(false);
        txtArtSubtotal.setEditable(false);
        txtArtTotal.setEditable(false);
        txtSena.setEditable(false);
        txtSaldo.setEditable(false);
        txtCOTSaldo.setEditable(false);
        txtCOTSena.setEditable(false);
    }

    private void ConsultNoEditTxt() {
        txtGGOTNro.setEditable(false);
        txtGGOTStatus.setEditable(false);
        txtGGOTDate.setEnabled(false);
        txtGGOTTotal.setEditable(false);
        txtGGOTPay.setEditable(false);
        txtGGOTBalance.setEditable(false);
        txtGGPatientDoc.setEditable(false);
        txtGGPatientName.setEditable(false);
        txtGGUsername.setEditable(false);
        txtGGOISpherical.setEditable(false);
        txtGGODSpherical.setEditable(false);
        txtGGOICylindrical.setEditable(false);
        txtGGODCylindrical.setEditable(false);
        txtGGOIAxis.setEditable(false);
        txtGGODAxis.setEditable(false);
        txtGGOIAddition.setEditable(false);
        txtGGODAddition.setEditable(false);
        txtGGOIQuantity.setEditable(false);
        txtGGODQuantity.setEditable(false);
        txtGGDI.setEditable(false);
        txtGGDND.setEditable(false);
        txtGGDNI.setEditable(false);
        txtGGAltFoc.setEditable(false);
        txtGGCrystalType.setEditable(false);
        txtGGCrystalDescr.setEditable(false);
        txtGGCrystalTotal.setEditable(false);
        txtGGObs.setEditable(false);
        txtGGUnitPrice.setEditable(false);
        txtGGTotalArticle.setEditable(false);
        tConsultOTPay.setEnabled(false);
    }

    private void NuevoOT() throws SQLException {
        txtOTState.setText("En Proceso");
        dtOTDate.setDate(java.sql.Date.valueOf(LocalDate.now()));
        String ExisteOT = "Select * from ordentrabajo";
        rs = con.Results(ExisteOT);
        if (rs.next()) {
            String NroOT = "Select last_value+1 as nroot from ordentrabajo_id_ordentrabajo_seq";
            rs = con.Results(NroOT);
            if (rs.next()) {
                txtOTNro.setText(rs.getString("nroot"));
            }
        } else {
            String NroOT = "Select last_value as nroot from ordentrabajo_id_ordentrabajo_seq";
            rs = con.Results(NroOT);
            if (rs.next()) {
                txtOTNro.setText(rs.getString("nroot"));
            }
        }
        txtOTNro.setEditable(false);
        txtArtUnitPrice.setText("0");
        txtArtSubtotal.setText("0");
        txtArtTotal.setText("0");
        txtCristalTotal.setText("0");
        txtPayMount.setText("0");
        txtOTSubtotal.setText("0");
        txtSena.setText("0");
        txtSaldo.setText("0");
    }

    private Boolean Check(DefaultTableModel auxModel, int column, String valorCodArt) {
        Boolean insert = true;
        for (int a = 0; a < auxModel.getRowCount(); a++) {
            if (auxModel.getValueAt(a, column).equals(valorCodArt)) {
                insert = false;
                return insert;
            }
        }
        return insert;
    }

    private String SumaTotal(DefaultTableModel auxModel, int column) {
        int total = 0;
        for (int a = 0; a < auxModel.getRowCount(); a++) {
            total += Integer.parseInt(auxModel.getValueAt(a, column).toString());
        }
        return String.valueOf(total);
    }

    private void SumaGeneral() {
        /* Esta función agarra los campos TotalArticulo, TotalCristal y TotalPago, hace cálculos y el resultado manda al campo Saldo */
        int CalcTotal = 0;
        CalcTotal = (Integer.parseInt(txtArtTotal.getText()) + Integer.parseInt(txtCristalTotal.getText())) - Integer.parseInt(txtSena.getText());
        /* Se suma TOTAL ARTICULO y TOTAL CRISTAL y se resta con el TOTAL PAGO */
        String OTTotal = String.valueOf(CalcTotal);
        txtSaldo.setText(OTTotal);
        /* Se define la suma y resta ((TotalArticulo+TotalCristal)-TotalArticulo) en el campo Saldo */
    }

    private void SumaSubTotal() {
        int Total = 0;
        Total = (Integer.parseInt(txtArtTotal.getText()) + Integer.parseInt(txtCristalTotal.getText()));
        String TTotal = String.valueOf(Total);
        txtOTSubtotal.setText(TTotal);
    }

    private void SumaCristal() {
        int TCristal = 0;
        TCristal = ((Integer) spinOICantidad.getValue() + (Integer) spinODCantidad.getValue()) * Integer.parseInt(txtCristalPrice.getText());
        String TotalCristal = String.valueOf(TCristal);
        txtCristalTotal.setText(TotalCristal);
    }

    private void CalcPayMethod() {
        /*Esta función actualiza los campos Total Pago y Saldo*/
        int TPayMethod = 0;
        for (int a = 0; a < PayMethod.getRowCount(); a++) {
            /* Recorre la tabla de PayMethod y acumula los montos de la columna Monto en la variable TPayMethod */
            TPayMethod = TPayMethod + Integer.parseInt(PayMethod.getValueAt(a, 1).toString());
        }
        txtSena.setText(String.valueOf(TPayMethod));
        txtCOTSena.setText(String.valueOf(TPayMethod));
        /* El Monto sumado en la variable TPayMethod define en el campo TotalPay */
        int CalcSubtPay = Integer.parseInt(txtOTSubtotal.getText()) - TPayMethod;
        /* Se resta el total OT con el Pago total */
        txtSaldo.setText(String.valueOf(CalcSubtPay));
        txtCOTSaldo.setText(String.valueOf(CalcSubtPay));
        /* El restante de (TotalOT-TotalPago) se define en el campo Saldo */
    }

    private void RefreshTotals() {
        int CristTotal = Integer.parseInt(txtCristalTotal.getText());
        int ArtTotal = Integer.parseInt(txtArtTotal.getText());
        int PayTotal = Integer.parseInt(txtSena.getText());
    }

    private void ProcessData() throws SQLException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(dtOTDate.getDate());
        String NroOT = txtOTNro.getText();
        String Estado = "Abierto";
        String CISolicitante = txtCiSol.getText();
        String CIPaciente = txtCiPa.getText();
        String IDSol = "";
        String IDPa = "";
        String OIEsferico = txtOIEsferico.getText();
        String ODEsferico = txtODEsferico.getText();
        String OICilindrico = txtOICilindrico.getText();
        String ODCilindrico = txtODCilindrico.getText();
        String OIEje = txtOIEje.getText();
        String ODEje = txtODEje.getText();
        String OIAdicion = txtOIAdicion.getText();
        String ODAdicion = txtODAdicion.getText();
        int OICant = (Integer) spinOICantidad.getValue();
        int ODCant = (Integer) spinODCantidad.getValue();
        int TotalCristal = OICant + ODCant;
        String DI = txtDI.getText();
        String DNI = txtDNI.getText();
        String DND = txtDND.getText();
        String AlturaFocal = txtAlturaFocal.getText();
        String Observacion = txtObservacion.getText();
        String IDCristal = txtCristalCode.getText();
        String PrecioCristal = txtCristalPrice.getText();
        String SubTotal = txtOTSubtotal.getText();
        String Sena = txtSena.getText();
        String Saldo = txtSaldo.getText();
        String SQL_IDSol = "select id_cliente from cliente where ci_cliente='" + CISolicitante + "'";
        String SQL_IDPa = "select id_cliente from cliente where ci_cliente='" + CIPaciente + "'";
        rs = con.Results(SQL_IDSol);
        if (rs.next()) {
            IDSol = rs.getString("id_cliente");
        }
        rs = con.Results(SQL_IDPa);
        if (rs.next()) {
            IDPa = rs.getString("id_cliente");
        }
        if (Flag == 1) { // Guardar datos
            con.InsertarDatos("ordentrabajo",
                    "id_cliente,id_paciente,id_usuario,ot_estado,fecha_ordentrabajo,oi_esferico,oi_cilindrico,oi_eje,oi_adicion,oi_cantidad,od_esferico,od_cilindrico,od_eje,od_adicion,od_cantidad,di,dnd,dni,alturafocal,id_cristal,preciocristal,observacion,subtotal,sena,total",
                    "'" + IDSol + "','" + IDPa + "','" + Menu.idUsuario + "','" + Estado + "','" + fecha + "','" + OIEsferico + "','" + OICilindrico + "','" + OIEje + "','" + OIAdicion + "','" + OICant + "','" + ODEsferico + "','" + ODCilindrico + "','" + ODEje + "','" + ODAdicion + "','" + ODCant + "','" + DI + "','" + DND + "','" + DNI + "','" + AlturaFocal + "','" + IDCristal + "','" + PrecioCristal + "','" + Observacion + "','" + SubTotal + "','" + Sena + "','" + Saldo + "'");
            for (int a = 0; a < DetalleArt.getRowCount(); a++) {
                String IDArt = DetalleArt.getValueAt(a, 0).toString();
                String PrecioArt = DetalleArt.getValueAt(a, 2).toString();
                String CantidadArt = DetalleArt.getValueAt(a, 3).toString();
                String SubtotalArt = DetalleArt.getValueAt(a, 4).toString();
                con.InsertarDatosDetalle("detalle_ordentrabajo", "id_ordentrabajo,id_articulo,precio_articulo,cantidad_articulo,subtotal_articulo", "'" + NroOT + "','" + IDArt + "','" + PrecioArt + "','" + CantidadArt + "','" + SubtotalArt + "'");
                String SQLServ = "select * from articulos where categoria_articulo != 'Servicio' and id_articulo = '" + IDArt + "'";
                rs = con.Results(SQLServ);
                if (rs.next()) {
                    con.EditarDatosDetalle("stock", "cantidad = cantidad - '" + CantidadArt + "'", "id_articulo='" + IDArt + "' and id_deposito=1");
                }
            }
            con.EditarDatosDetalle("stock", "cantidad = cantidad - '" + TotalCristal + "'", "id_articulo='" + IDCristal + "' and id_deposito=1");
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    String Pay_Mount = PayMethod.getValueAt(i, 1).toString();
                    String Pay_Nro = PayMethod.getValueAt(i, 2).toString();
                    con.InsertarDatosDetalle("ot_pay", "id_paymethod,id_ordentrabajo,payamount,nrocomprobante", Pay_Code + "," + NroOT + "," + Pay_Mount + "," + Pay_Nro);
                }
            }
        } else if (Flag == 2) { // Editar Datos
            for (int a = 0; a < DetalleArt.getRowCount(); a++) {
                String IDArt = DetalleArt.getValueAt(a, 0).toString();
                String PrecioArt = DetalleArt.getValueAt(a, 2).toString();
                String CantidadArt = DetalleArt.getValueAt(a, 3).toString();
                String SubtotalArt = DetalleArt.getValueAt(a, 4).toString();
                String SQLServ = "select * from articulos where categoria_articulo != 'Servicio' and id_articulo = '" + IDArt + "'";
                rs = con.Results(SQLServ);
                if (rs.next()) {
                    String SQLCompCant = "select dot.cantidad_articulo, art.descripcion_articulo from ordentrabajo ot, detalle_ordentrabajo dot, articulos art where ot.id_ordentrabajo=dot.id_ordentrabajo and dot.id_articulo=art.id_articulo and dot.id_ordentrabajo='" + NroOT + "' and dot.id_articulo='" + IDArt + "'";
                    ResultSet rs2 = con.Results(SQLCompCant);
                    if (rs2.next()) {
                        int CantArtSQL = rs2.getInt("cantidad_articulo");
                        int CantArtTable = Integer.parseInt(CantidadArt);
                        if (CantArtTable > CantArtSQL) {
                            int Rest = CantArtTable - CantArtSQL;
                            con.EditarDatosDetalle("stock", "cantidad = cantidad - '" + Rest + "'", "id_articulo='" + IDArt + "' and id_deposito=1");
                        } else if (CantArtTable < CantArtSQL) {
                            int Rest = CantArtSQL - CantArtTable;
                            con.EditarDatosDetalle("stock", "cantidad = cantidad + '" + Rest + "'", "id_articulo='" + IDArt + "' and id_deposito=1");
                        }
                    }
                }
                con.EditarDatosDetalle("detalle_ordentrabajo", "precio_articulo='" + PrecioArt + "',cantidad_articulo='" + CantidadArt + "',subtotal_articulo='" + SubtotalArt + "'", "id_ordentrabajo='" + NroOT + "' and id_articulo='" + IDArt + "'");
            }
            String SQLExistCristal = "select oi_cantidad, od_cantidad from ordentrabajo where id_ordentrabajo=" + NroOT;
            rs = con.Results(SQLExistCristal);
            if (rs.next()) {
                int CristCant = rs.getInt("oi_cantidad") + rs.getInt("od_cantidad");
                if (TotalCristal > CristCant) {
                    int Resto = TotalCristal - CristCant;
                    con.EditarDatos("stock", "cantidad = cantidad - '" + Resto + "'", "id_articulo='" + IDCristal + "' and id_deposito=1");
                } else if (TotalCristal < CristCant) {
                    int Resto = CristCant - TotalCristal;
                    con.EditarDatos("stock", "cantidad = cantidad - '" + Resto + "'", "id_articulo='" + IDCristal + "' and id_deposito=1");
                }
            }
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod (Elimina los métodos agregados anteriormente)
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    con.BorrarDatosDetalle("ot_pay", "id_paymethod=" + Pay_Code + " and id_ordentrabajo=" + NroOT);
                }
            }
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod (Agrega los nuevos métodos definidos)
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    String Pay_Mount = PayMethod.getValueAt(i, 1).toString();
                    String Pay_Nro = PayMethod.getValueAt(i, 2).toString();
                    con.InsertarDatosDetalle("ot_pay", "id_paymethod,id_ordentrabajo,payamount,nrocomprobante", Pay_Code + "," + NroOT + "," + Pay_Mount + "," + Pay_Nro);
                }
            }
            con.EditarDatos("ordentrabajo", "id_cliente='" + IDSol + "',id_paciente='" + IDPa + "',id_usuario='" + 1 + "',ot_estado='" + Estado + "',fecha_ordentrabajo='" + fecha + "',oi_esferico='" + OIEsferico + "',oi_cilindrico='" + OICilindrico + "',oi_eje='" + OIEje + "',oi_adicion='" + OIAdicion + "',oi_cantidad='" + OICant + "',od_esferico='" + ODEsferico + "',od_cilindrico='" + ODCilindrico + "',od_eje='" + ODEje + "',od_adicion='" + ODAdicion + "',od_cantidad='" + ODCant + "',di='" + DI + "',dnd='" + DND + "',dni='" + DNI + "',alturafocal='" + AlturaFocal + "',id_cristal='" + IDCristal + "',preciocristal='" + PrecioCristal + "',observacion='" + Observacion + "',subtotal='" + SubTotal + "',sena='" + Sena + "',total='" + Saldo + "'", "id_ordentrabajo=" + NroOT);
        } else if (Flag == 3) { //Anular Datos
            con.EditarDatos("ordentrabajo", "ot_estado='Anulado'", "id_ordentrabajo='" + NroOT + "'");
            String RecuCant = "select oi_cantidad, od_cantidad from ordentrabajo where id_ordentrabajo='" + NroOT + "'";
            rs = con.Results(RecuCant);
            if (rs.next()) {
                int CantCristal = rs.getInt("oi_cantidad") + rs.getInt("od_cantidad");
                con.EditarDatos("stock", "cantidad = cantidad + " + CantCristal, "id_articulo='" + IDCristal + "'");
            }
            for (int a = 0; a < DetalleArt.getRowCount(); a++) {
                String IDArt = DetalleArt.getValueAt(a, 0).toString();
                String CantidadArt = DetalleArt.getValueAt(a, 3).toString();
                String SQLServ = "select * from articulos where categoria_articulo != 'Servicio' and id_articulo = '" + IDArt + "'";
                rs = con.Results(SQLServ);
                if (rs.next()) {
                    con.EditarDatosDetalle("stock", "cantidad = cantidad + '" + CantidadArt + "'", "id_articulo='" + IDArt + "' and id_deposito=1");
                }
            }
            /* con.BorrarDatos("ot_pay", "id_ordentrabajo=" + NroOT);
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod (Elimina los métodos agregados anteriormente)
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    con.BorrarDatos("ot_pay", "id_paymethod=" + Pay_Code + " and id_ordentrabajo=" + NroOT);
                }
            } */
        } else if (Flag == 4) { // Cerrar OT (Editar datos)
            con.EditarDatos("ordentrabajo", "sena=" + txtCOTSena.getText() + ", total=" + txtCOTSaldo.getText() + ", ot_estado='Cerrado'", "id_ordentrabajo='" + NroOT + "'");
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod (Elimina los métodos agregados anteriormente)
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    con.BorrarDatosDetalle("ot_pay", "id_paymethod=" + Pay_Code + " and id_ordentrabajo=" + NroOT);
                }
            }
            for (int i = 0; i < PayMethod.getRowCount(); i++) { // Este FOR ejecuta según la cantidad de filas de la tabla PayMethod (Agrega los nuevos métodos definidos)
                String CurrPay = PayMethod.getValueAt(i, 0).toString();
                String PaySQL = "select * from paymethod where descr_paymethod = '" + CurrPay + "'"; // Este query es para quitar el ID del Método de pago
                rs = con.Results(PaySQL); // Ejecuta el query definido arriba
                if (rs.next()) { // Si trae algún dato, entra en el IF 
                    int Pay_Code = rs.getInt("id_paymethod");
                    String Pay_Mount = PayMethod.getValueAt(i, 1).toString();
                    String Pay_Nro = PayMethod.getValueAt(i, 2).toString();
                    con.InsertarDatosDetalle("ot_pay", "id_paymethod,id_ordentrabajo,payamount,nrocomprobante", Pay_Code + "," + NroOT + "," + Pay_Mount + "," + Pay_Nro);
                }
            }
        } else if (Flag == 5) { // Reabrir OT (Pa Editar datos)
            con.EditarDatos("ordentrabajo", "ot_estado = 'Abierto'", "id_ordentrabajo = " + NroOT);
        }
    }

    private void RecuperarOT(String id) throws SQLException {
        String SQL_Recuperar = "select ot.*,us.username,sum(dot.subtotal_articulo) as subtotal_articulo from ordentrabajo ot, detalle_ordentrabajo dot, cliente cl, usuario us where cl.id_cliente=ot.id_cliente and ot.id_usuario=us.id_usuario and dot.id_ordentrabajo=ot.id_ordentrabajo and ot.id_ordentrabajo='" + id + "' group by ot.id_ordentrabajo,cl.ci_cliente,cl.nombre_cliente,cl.apellido_cliente,us.username order by fecha_ordentrabajo desc";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            String NroOT = rs.getString("id_ordentrabajo");
            String CiSol = rs.getString("id_cliente");
            String CiPa = rs.getString("id_paciente");
            txtOTState.setText(rs.getString("ot_estado"));
            dtOTDate.setDate(rs.getDate("fecha_ordentrabajo"));
            txtOIEsferico.setText(rs.getString("oi_esferico"));
            txtOICilindrico.setText(rs.getString("oi_cilindrico"));
            txtOIEje.setText(rs.getString("oi_eje"));
            txtOIAdicion.setText(rs.getString("oi_adicion"));
            spinOICantidad.setValue(rs.getInt("oi_cantidad"));
            txtODEsferico.setText(rs.getString("od_esferico"));
            txtODCilindrico.setText(rs.getString("od_cilindrico"));
            txtODEje.setText(rs.getString("od_eje"));
            txtODAdicion.setText(rs.getString("od_adicion"));
            spinODCantidad.setValue(rs.getInt("od_cantidad"));
            txtDI.setText(rs.getString("di"));
            txtDNI.setText(rs.getString("dni"));
            txtDND.setText(rs.getString("dnd"));
            txtAlturaFocal.setText(rs.getString("alturafocal"));
            txtObservacion.setText(rs.getString("observacion"));
            int OI_Cant = rs.getInt("oi_cantidad");
            int OD_Cant = rs.getInt("od_cantidad");
            int Cris_Price = rs.getInt("preciocristal");
            int TtlCristal = (OI_Cant + OD_Cant) * Cris_Price;
            String TotalCristal = String.valueOf(TtlCristal);
            txtCristalTotal.setText(TotalCristal);
            txtOTSubtotal.setText(rs.getString("subtotal"));
            txtArtTotal.setText(rs.getString("subtotal_articulo"));
            txtSena.setText(rs.getString("sena"));
            txtSaldo.setText(rs.getString("total"));
            String CodCristal = rs.getString("id_cristal");
            txtCristalCode.setText(rs.getString("id_cristal"));
            txtCristalPrice.setText(rs.getString("preciocristal"));
            String SQLCliente = "select * from cliente where id_cliente='" + CiSol + "';";
            rs = con.Results(SQLCliente);
            if (rs.next()) {
                txtCiSol.setText(rs.getString("ci_cliente"));
                txtClienteSol.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
            }
            String SQLPaciente = "select * from cliente where id_cliente='" + CiPa + "';";
            rs = con.Results(SQLPaciente);
            if (rs.next()) {
                txtCiPa.setText(rs.getString("ci_cliente"));
                txtClientePa.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
                Boolean checkmenor = rs.getBoolean("menordeedad");
                if (checkmenor == true) {
                    chboxMenor.setSelected(true);
                } else {
                    chboxMenor.setSelected(false);
                }
            }
            String SQLCristal = "select * from articulos where id_articulo='" + CodCristal + "';";
            rs = con.Results(SQLCristal);
            if (rs.next()) {
                txtCristalCode.setText(rs.getString("id_articulo"));
                txtCristalDescr.setText(rs.getString("descripcion_articulo"));
            }
            String SQLArticulos = "select dot.id_articulo,art.descripcion_articulo,dot.cantidad_articulo,dot.precio_articulo,dot.subtotal_articulo from detalle_ordentrabajo dot, ordentrabajo ot, articulos art where dot.id_ordentrabajo=ot.id_ordentrabajo and dot.id_articulo=art.id_articulo and dot.id_ordentrabajo=" + NroOT;
            rs = con.Results(SQLArticulos);
            while (rs.next()) {
                String CodArt = rs.getString("id_articulo");
                if (Check(DetalleArt, 0, CodArt) == true) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getString("id_articulo");
                    fila[1] = rs.getString("descripcion_articulo");
                    fila[2] = rs.getString("precio_articulo");
                    fila[3] = rs.getString("cantidad_articulo");
                    fila[4] = rs.getString("subtotal_articulo");
                    DetalleArt.addRow(fila);
                }
            }
            String SQLPays = "select pm.descr_paymethod,otp.payamount,otp.nrocomprobante from paymethod pm, ot_pay otp where otp.id_paymethod=pm.id_paymethod and otp.id_ordentrabajo=" + NroOT;
            rs = con.Results(SQLPays);
            while (rs.next()) {
                String PayDescr = rs.getString("descr_paymethod");
                if (Check(PayMethod, 0, PayDescr) == true) {
                    Object[] fila = new Object[3];
                    fila[0] = rs.getString("descr_paymethod");
                    fila[1] = rs.getString("payamount");
                    fila[2] = rs.getString("nrocomprobante");
                    PayMethod.addRow(fila);
                }
            }
            HabilitarBtn2();
            HabilitarTxt2();
            btnOTConfirm.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void CleanConsultOT() {
        txtGGOTNro.setText("");
        txtGGOTStatus.setText("");
        txtGGPatientDoc.setText("");
        txtGGPatientName.setText("");
        txtGGObs.setText("");
        txtGGUsername.setText("");
        txtGGODAddition.setText("");
        txtGGODAxis.setText("");
        txtGGODCylindrical.setText("");
        txtGGODQuantity.setText("");
        txtGGODSpherical.setText("");
        txtGGOIAddition.setText("");
        txtGGOIAxis.setText("");
        txtGGOICylindrical.setText("");
        txtGGOIQuantity.setText("");
        txtGGOISpherical.setText("");
        txtGGDI.setText("");
        txtGGDND.setText("");
        txtGGDNI.setText("");
        txtGGAltFoc.setText("");
        txtGGCrystalDescr.setText("");
        txtGGCrystalTotal.setText("");
        txtGGCrystalType.setText("");
        txtGGUnitPrice.setText("");
        txtGGOTTotal.setText("");
        txtGGOTPay.setText("");
        txtGGOTBalance.setText("");
        txtGGTotalArticle.setText("");
        ConsultOTDetail.setRowCount(0);
        ConsultOTPay.setRowCount(0);
        lblGGImage.setIcon(null);
    }

    private void LoadConsultOT(String OT_ID) throws SQLException {
        CleanConsultOT();
        String SQL_Recuperar = "SELECT ot.*, art.descripcion_articulo, cl.ci_cliente, cl.nombre_cliente||' '||cl.apellido_cliente AS paciente, us.username, SUM(dot.subtotal_articulo) AS subtotal_articulo FROM ordentrabajo ot, detalle_ordentrabajo dot, cliente cl, usuario us, articulos art WHERE ot.id_ordentrabajo = dot.id_ordentrabajo AND cl.id_cliente = ot.id_paciente AND ot.id_usuario = us.id_usuario AND ot.id_cristal = art.id_articulo AND ot.id_ordentrabajo = '" + OT_ID + "' GROUP BY ot.id_ordentrabajo, cl.ci_cliente, cl.ci_cliente, cl.nombre_cliente, cl.apellido_cliente, us.username, art.descripcion_articulo ORDER BY fecha_ordentrabajo DESC;";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            txtGGOTNro.setText(rs.getString("id_ordentrabajo"));
            txtGGOTStatus.setText(rs.getString("ot_estado"));
            txtGGPatientDoc.setText(rs.getString("ci_cliente"));
            txtGGPatientName.setText(rs.getString("paciente"));
            txtGGObs.setText(rs.getString("observacion"));
            txtGGUsername.setText(rs.getString("username"));
            txtGGODSpherical.setText(rs.getString("od_esferico"));
            txtGGODCylindrical.setText(rs.getString("od_cilindrico"));
            txtGGODAxis.setText(rs.getString("od_eje"));
            txtGGODAddition.setText(rs.getString("od_adicion"));
            txtGGODQuantity.setText(rs.getString("od_cantidad"));
            txtGGOISpherical.setText(rs.getString("oi_esferico"));
            txtGGOICylindrical.setText(rs.getString("oi_cilindrico"));
            txtGGOIAxis.setText(rs.getString("oi_eje"));
            txtGGOIAddition.setText(rs.getString("oi_adicion"));
            txtGGOIQuantity.setText(rs.getString("oi_cantidad"));
            txtGGDI.setText(rs.getString("di"));
            txtGGDND.setText(rs.getString("dnd"));
            txtGGDNI.setText(rs.getString("dni"));
            txtGGAltFoc.setText(rs.getString("alturafocal"));
            txtGGCrystalType.setText(rs.getString("id_cristal"));
            txtGGCrystalDescr.setText(rs.getString("descripcion_articulo"));
            txtGGUnitPrice.setText(rs.getString("preciocristal"));
            int TotalCrystal = (rs.getInt("oi_cantidad") + rs.getInt("od_cantidad")) * rs.getInt("preciocristal");
            txtGGCrystalTotal.setText(String.valueOf(TotalCrystal));
            txtGGOTTotal.setText(rs.getString("subtotal"));
            txtGGOTPay.setText(rs.getString("sena"));
            txtGGOTBalance.setText(rs.getString("total"));
            txtGGTotalArticle.setText(rs.getString("subtotal_articulo"));
            // FALTA AGREGAR LAS TABLAS DE ARTICULOS Y METODOS DE PAGO
            String SQL_LoadArticles = "SELECT dot.id_ordentrabajo, dot.id_articulo, dot.cantidad_articulo, dot.precio_articulo, dot.subtotal_articulo, art.descripcion_articulo, art.id_marca, mc.descripcion_marca, art.image_dir FROM detalle_ordentrabajo dot JOIN articulos art ON dot.id_articulo = art.id_articulo JOIN marca mc ON art.id_marca = mc.id_marca WHERE dot.id_ordentrabajo = '" + OT_ID + "';";
            ResultSet rs2 = con.Results(SQL_LoadArticles);
            ConsultOTDetail.setRowCount(0);
            while (rs2.next()) {
                Object[] row = new Object[6];
                row[0] = rs2.getString("id_articulo");
                row[1] = rs2.getString("descripcion_articulo");
                row[2] = rs2.getString("descripcion_marca");
                row[3] = rs2.getString("precio_articulo");
                row[4] = rs2.getString("cantidad_articulo");
                row[5] = rs2.getString("subtotal_articulo");
                ConsultOTDetail.addRow(row);
            }
            String SQL_LoadPays = "SELECT otp.payamount, pm.descr_paymethod, otp.nrocomprobante FROM ot_pay otp JOIN paymethod pm ON otp.id_paymethod = pm.id_paymethod WHERE id_ordentrabajo = '" + OT_ID + "';";
            ResultSet rs3 = con.Results(SQL_LoadPays);
            ConsultOTPay.setRowCount(0);
            while (rs3.next()) {
                Object[] row = new Object[3];
                row[0] = rs3.getString("descr_paymethod");
                row[1] = rs3.getString("payamount");
                row[2] = rs3.getString("nrocomprobante");
                ConsultOTPay.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void LoadConsultOTArtImage(String ArtID) throws SQLException {
        String SQL_Recuperar = "SELECT * FROM articulos WHERE id_articulo = '" + ArtID + "';";
        rs = con.Results(SQL_Recuperar);
        if (rs.next()) {
            // Recuperar el directorio de la imagen
            String imageDir = rs.getString("image_dir");
            if (imageDir != null && !imageDir.isEmpty()) {
                File imageFile = new File(imageDir);
                if (imageFile.exists()) {
                    // Cargar y escalar la imagen en lblImage
                    ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                    lblGGImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(lblGGImage.getWidth(), lblGGImage.getHeight(), Image.SCALE_SMOOTH)));
                } else {
                    // Si la imagen no existe, vaciar lblImage
                    lblGGImage.setIcon(null);
                    System.out.println("La imagen no existe en la ruta especificada: " + imageDir);
                }
            } else {
                // Si no hay ruta almacenada, vaciar lblImage
                lblGGImage.setIcon(null);
                System.out.println("No se encontró una ruta de imagen para este artículo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }
    }

    private void RecuperarCliente(String ci) throws SQLException {
        String SQL_Recuperar = "select * from cliente where ci_cliente='" + String.valueOf(ci) + "'";
        rs = con.Results(SQL_Recuperar);
        if (TagSearch == 1) {
            if (rs.next()) {
                txtClienteSol.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el CI/RUC Nro. " + txtCiSol.getText());
                txtClienteSol.setText("");
                txtCiSol.requestFocus();
            }
        } else if (TagSearch == 2) {
            if (rs.next()) {
                txtClientePa.setText(rs.getString("nombre_cliente") + " " + rs.getString("apellido_cliente"));
                Boolean checkmenor = rs.getBoolean("menordeedad");
                if (checkmenor == true) {
                    chboxMenor.setSelected(true);
                } else {
                    chboxMenor.setSelected(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el CI/RUC Nro. " + txtCiPa.getText());
                txtClientePa.setText("");
                chboxMenor.setSelected(false);
                txtCiPa.requestFocus();
            }
        }
    }

    private void RecuperarArticulo(String codArt) throws SQLException {
        if (TagSearch == 3) {
            String SQL_Recuperar = "Select * from articulos where categoria_articulo = 'Cristal' and id_articulo='" + String.valueOf(codArt) + "'";
            rs = con.Results(SQL_Recuperar);
            if (rs.next()) {
                txtCristalDescr.setText(rs.getString("descripcion_articulo"));
                txtCristalPrice.setText(rs.getString("precioventa_articulo"));
                txtCristalPrice.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún artículo con el Código Nro. " + txtCristalCode.getText());
                txtCristalDescr.setText("");
                txtCristalCode.requestFocus();
            }
        } else if (TagSearch == 4) {
            String SQL_Recuperar = "Select * from articulos where categoria_articulo != 'Cristal' and id_articulo='" + String.valueOf(codArt) + "'";
            rs = con.Results(SQL_Recuperar);
            if (rs.next()) {
                txtArtDescr.setText(rs.getString("descripcion_articulo"));
                txtArtUnitPrice.setText(rs.getString("precioventa_articulo"));
                spinArtQuantity.setValue(1);
                spinArtQuantity.requestFocus();
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
                JOptionPane.showMessageDialog(null, "No se encontró ningún artículo con el Código Nro. " + txtArtCode.getText());
                txtArtDescr.setText("");
                txtArtCode.requestFocus();
            }
        }
    }

    private void CabeceraTablaDetalleArticulo() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        DetalleArt.addColumn("ID");
        DetalleArt.addColumn("Descripción");
        DetalleArt.addColumn("Precio Unit.");
        DetalleArt.addColumn("Stock");
        DetalleArt.addColumn("Subtotal");
        TSearcherDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
        TSearcherDetalle.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcherDetalle.getColumnModel().getColumn(1).setPreferredWidth(100);
        TSearcherDetalle.getColumnModel().getColumn(2).setPreferredWidth(45);
        TSearcherDetalle.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        TSearcherDetalle.getColumnModel().getColumn(3).setPreferredWidth(15);
        TSearcherDetalle.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        TSearcherDetalle.getColumnModel().getColumn(4).setPreferredWidth(30);
        TSearcherDetalle.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }

    private void CabeceraTablaArticulo() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        SearchArticulo.addColumn("ID");
        SearchArticulo.addColumn("Descripción");
        SearchArticulo.addColumn("Categoría");
        SearchArticulo.addColumn("Marca");
        SearchArticulo.addColumn("Costo Actual");
        SearchArticulo.addColumn("Precio");
        SearchArticulo.addColumn("Stock");
        TSearcherArt.getColumnModel().getColumn(0).setPreferredWidth(50);
        TSearcherArt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcherArt.getColumnModel().getColumn(1).setPreferredWidth(200);
        TSearcherArt.getColumnModel().getColumn(2).setPreferredWidth(80);
        TSearcherArt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TSearcherArt.getColumnModel().getColumn(3).setPreferredWidth(80);
        TSearcherArt.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        TSearcherArt.getColumnModel().getColumn(4).setPreferredWidth(80);
        TSearcherArt.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TSearcherArt.getColumnModel().getColumn(5).setPreferredWidth(80);
        TSearcherArt.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        TSearcherArt.getColumnModel().getColumn(6).setPreferredWidth(50);
        TSearcherArt.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
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
        TSearcherCli.getColumnModel().getColumn(0).setPreferredWidth(30);
        TSearcherCli.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcherCli.getColumnModel().getColumn(1).setPreferredWidth(80);
        TSearcherCli.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        TSearcherCli.getColumnModel().getColumn(2).setPreferredWidth(100);
        TSearcherCli.getColumnModel().getColumn(3).setPreferredWidth(100);
        TSearcherCli.getColumnModel().getColumn(4).setPreferredWidth(30);
        TSearcherCli.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        TSearcherCli.getColumnModel().getColumn(5).setPreferredWidth(60);
        TSearcherCli.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        TSearcherCli.getColumnModel().getColumn(6).setPreferredWidth(60);
        TSearcherCli.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        TSearcherCli.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    private void CabeceraTablaOT() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        SearchOT.addColumn("Nro OT"); // Columna 0
        SearchOT.addColumn("CI Paciente"); // Columna 1
        SearchOT.addColumn("Nombre Paciente"); // Columna 2
        SearchOT.addColumn("Fecha"); // Columna 3
        SearchOT.addColumn("Estado"); // Columna 3
        SearchOT.addColumn("Observación"); // Columna 4
        SearchOT.addColumn("Total Cristal"); // Columna 5
        SearchOT.addColumn("Cant. Art."); // Columna 6
        SearchOT.addColumn("Total Art."); // Columna 7
        SearchOT.addColumn("Subtotal"); // Columna 8
        SearchOT.addColumn("Seña"); // Columna 9
        SearchOT.addColumn("Total");  // Columna 10
        TSearcherOT.getColumnModel().getColumn(0).setPreferredWidth(15); //Nro OT
        TSearcherOT.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); //Nro OT
        TSearcherOT.getColumnModel().getColumn(1).setPreferredWidth(20); //CI Paciente
        TSearcherOT.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); //CI Paciente
        TSearcherOT.getColumnModel().getColumn(2).setPreferredWidth(90); //Nombre Paciente
        TSearcherOT.getColumnModel().getColumn(3).setPreferredWidth(20); //Fecha
        TSearcherOT.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); //Fecha
        TSearcherOT.getColumnModel().getColumn(4).setPreferredWidth(20); //Estado
        TSearcherOT.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); //Estado
        TSearcherOT.getColumnModel().getColumn(5).setPreferredWidth(120); //Observación
        TSearcherOT.getColumnModel().getColumn(6).setPreferredWidth(20); //Total de Cristal
        TSearcherOT.getColumnModel().getColumn(6).setCellRenderer(rightRenderer); //Total de Cristal
        TSearcherOT.getColumnModel().getColumn(7).setPreferredWidth(10); // Cantidad de Artículos
        TSearcherOT.getColumnModel().getColumn(7).setCellRenderer(centerRenderer); // Cantidad de Artículos
        TSearcherOT.getColumnModel().getColumn(8).setPreferredWidth(20); // Total de Artículos
        TSearcherOT.getColumnModel().getColumn(8).setCellRenderer(rightRenderer); // Total de Artículos
        TSearcherOT.getColumnModel().getColumn(9).setPreferredWidth(20); // Subtotal OT
        TSearcherOT.getColumnModel().getColumn(9).setCellRenderer(rightRenderer); // Subtotal OT
        TSearcherOT.getColumnModel().getColumn(10).setPreferredWidth(20); // Seña
        TSearcherOT.getColumnModel().getColumn(10).setCellRenderer(rightRenderer); // Seña
        TSearcherOT.getColumnModel().getColumn(11).setPreferredWidth(20); // Total por Pagar Restante
        TSearcherOT.getColumnModel().getColumn(11).setCellRenderer(rightRenderer); // Total por Pagar Restante
    }

    private void CabeceraTablaPayMethod() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        PayMethod.addColumn("Método de Pago"); // Columna 0
        PayMethod.addColumn("Monto"); // Columna 1
        PayMethod.addColumn("Nro de Comprobante"); // Columna 2
        TSeacherPay.getColumnModel().getColumn(0).setPreferredWidth(20); //Método de Pago
        TSeacherPay.getColumnModel().getColumn(1).setPreferredWidth(50); //Monto
        TSeacherPay.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); //Monto
        TSeacherPay.getColumnModel().getColumn(2).setPreferredWidth(100); //Nro de Comprobante
        TSeacherPay.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); //Nro de Comprobante
    }

    private void AddConsultOTColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        ConsultOT.addColumn("OT N°"); // Columna 0
        ConsultOT.addColumn("Estado"); // Columna 1
        ConsultOT.addColumn("Fecha"); // Columna 2
        ConsultOT.addColumn("Doc. N°"); // Columna 3
        ConsultOT.addColumn("Paciente"); // Columna 4
        ConsultOT.addColumn("Cant. Cristal"); // Columna 5
        ConsultOT.addColumn("Total Cristal"); // Columna 6
        ConsultOT.addColumn("Total Art."); // Columna 7
        ConsultOT.addColumn("Subtotal"); // Columna 8
        ConsultOT.addColumn("Pago"); // Columna 9
        ConsultOT.addColumn("Saldo"); // Columna 10
        tConsultOT.getColumnModel().getColumn(0).setPreferredWidth(20); //
        tConsultOT.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tConsultOT.getColumnModel().getColumn(1).setPreferredWidth(20); //
        tConsultOT.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tConsultOT.getColumnModel().getColumn(2).setPreferredWidth(20); //
        tConsultOT.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tConsultOT.getColumnModel().getColumn(3).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(4).setPreferredWidth(190); //
        tConsultOT.getColumnModel().getColumn(5).setPreferredWidth(20); //
        tConsultOT.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(6).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(7).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(8).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(9).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
        tConsultOT.getColumnModel().getColumn(10).setPreferredWidth(50); //
        tConsultOT.getColumnModel().getColumn(10).setCellRenderer(rightRenderer);
    }

    private void LoadOTConsult() throws SQLException {
        String SQLOT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
        rs = con.Results(SQLOT);
        ConsultOT.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[11];
            row[0] = rs.getString("id_ordentrabajo");
            row[1] = rs.getString("ot_estado");
            row[2] = rs.getString("fecha_ordentrabajo");
            row[3] = rs.getString("ci_cliente");
            row[4] = rs.getString("paciente");
            row[5] = rs.getInt("oi_cantidad") + rs.getInt("od_cantidad");
            row[6] = (rs.getInt("oi_cantidad") + rs.getInt("od_cantidad")) * rs.getInt("preciocristal");
            row[7] = rs.getString("subtotal_articulo");
            row[8] = rs.getString("subtotal");
            row[9] = rs.getString("sena");
            row[10] = rs.getString("total");
            ConsultOT.addRow(row);
        }
    }

    private void AddConsultOTDetailColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        ConsultOTDetail.addColumn("Art. ID"); // Columna 0
        ConsultOTDetail.addColumn("Descripción"); // Columna 1
        ConsultOTDetail.addColumn("Marca"); // Columna 2
        ConsultOTDetail.addColumn("Precio Unit."); // Columna 3
        ConsultOTDetail.addColumn("Cantidad"); // Columna 4
        ConsultOTDetail.addColumn("Subtotal"); // Columna 5
        tConsultOTDetail.getColumnModel().getColumn(0).setPreferredWidth(80); // ID Artículo
        tConsultOTDetail.getColumnModel().getColumn(1).setPreferredWidth(180); // Descripción
        tConsultOTDetail.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tConsultOTDetail.getColumnModel().getColumn(2).setPreferredWidth(10); // Marca
        tConsultOTDetail.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tConsultOTDetail.getColumnModel().getColumn(3).setPreferredWidth(80); // Precio Unitario
        tConsultOTDetail.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tConsultOTDetail.getColumnModel().getColumn(4).setPreferredWidth(10); // Cantidad
        tConsultOTDetail.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tConsultOTDetail.getColumnModel().getColumn(5).setPreferredWidth(80); // Subtotal
        tConsultOTDetail.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
    }

    private void AddConsultOTPayColumn() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        ConsultOTPay.addColumn("Método de Pago"); // Columna 0
        ConsultOTPay.addColumn("Monto"); // Columna 1
        ConsultOTPay.addColumn("Nro de Comprobante"); // Columna 2
        tConsultOTPay.getColumnModel().getColumn(0).setPreferredWidth(20); //Método de Pago
        tConsultOTPay.getColumnModel().getColumn(1).setPreferredWidth(50); //Monto
        tConsultOTPay.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); //Monto
        tConsultOTPay.getColumnModel().getColumn(2).setPreferredWidth(100); //Nro de Comprobante
        tConsultOTPay.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); //Nro de Comprobante
    }

    private void UserCheck(String ObtUser, String ObtPass) throws SQLException {
        String SQLUser = "select * from usuario where username = '" + ObtUser + "' and password = md5('" + ObtPass + "') and id_estado = 1;";
        rs = con.Results(SQLUser);
        SearchOT.setRowCount(0);
        while (rs.next()) {
            if (rs.getBoolean("admin") == true) {
                SUConfirm.dispose();
                DeshabilitarMainBtn();
                btnOTCancel.setEnabled(true);
                txtOTNro.setEnabled(true);
                txtOTNro.requestFocus();
                txtUser.setText("");
                txtPass.setText("");
                btnSearchOT.setEnabled(true);
                return;
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ingresado no es un administrador. Inténtalo de nuevo más tarde.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "El usuario ingresado no existe o está inactivo. Intentalo nuevamente.");
        txtUser.setText("");
        txtPass.setText("");
        txtUser.requestFocus();
    }

    private void CargarTablaClientes() throws SQLException {
        if (TagSearch == 1) {
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
        } else if (TagSearch == 2) {
            rs = con.Results("select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad");
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
        }
    }

    private void CargarTablaOT() throws SQLException {
        String SQLOT = "";
        if (Flag == 5) {
            SQLOT = "select ot.id_ordentrabajo,ot.id_cliente,ot.id_paciente,ot.fecha_ordentrabajo,ot.ot_estado,ot.observacion,ot.preciocristal,sum(dot.cantidad_articulo) as cantidad_articulo,sum(dot.subtotal_articulo) as subtotal_articulo,ot.subtotal,ot.sena,ot.total from detalle_ordentrabajo dot, ordentrabajo ot where dot.id_ordentrabajo=ot.id_ordentrabajo and ot.ot_estado='Cerrado' group by ot.id_ordentrabajo order by ot.fecha_ordentrabajo desc";
        } else {
            SQLOT = "select ot.id_ordentrabajo,ot.id_cliente,ot.id_paciente,ot.fecha_ordentrabajo,ot.ot_estado,ot.observacion,ot.preciocristal,sum(dot.cantidad_articulo) as cantidad_articulo,sum(dot.subtotal_articulo) as subtotal_articulo,ot.subtotal,ot.sena,ot.total from detalle_ordentrabajo dot, ordentrabajo ot where dot.id_ordentrabajo=ot.id_ordentrabajo and ot.ot_estado='Abierto' group by ot.id_ordentrabajo order by ot.fecha_ordentrabajo desc";
        }
        rs = con.Results(SQLOT);
        SearchOT.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[12];
            row[0] = rs.getString("id_ordentrabajo");
            String SQLCliente = "select cl.ci_cliente,cl.nombre_cliente,cl.apellido_cliente from cliente cl, ordentrabajo ot where cl.id_cliente=ot.id_paciente and ot.id_ordentrabajo='" + rs.getString("id_ordentrabajo") + "'";
            ResultSet rs2 = con.Results(SQLCliente);
            if (rs2.next()) {
                row[1] = rs2.getString("ci_cliente");
                row[2] = rs2.getString("nombre_cliente") + " " + rs2.getString("apellido_cliente");
            }
            row[3] = rs.getString("fecha_ordentrabajo");
            row[4] = rs.getString("ot_estado");
            row[5] = rs.getString("observacion");
            row[6] = rs.getString("preciocristal");
            row[7] = rs.getString("cantidad_articulo");
            row[8] = rs.getString("subtotal_articulo");
            row[9] = rs.getString("subtotal");
            row[10] = rs.getString("sena");
            row[11] = rs.getString("total");
            SearchOT.addRow(row);
        }
    }

    private void CargarTablaArticulos() throws SQLException {
        if (TagSearch == 3) {
            rs = con.Results("select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and categoria_articulo = 'Cristal'");
            SearchArticulo.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getString("id_articulo");
                row[1] = rs.getString("descripcion_articulo");
                row[2] = rs.getString("categoria_articulo");
                row[3] = rs.getString("descripcion_marca");
                row[4] = rs.getString("costoactual_articulo");
                row[5] = rs.getString("precioventa_articulo");
                row[6] = rs.getString("cantidad");
                SearchArticulo.addRow(row);
            }
        } else if (TagSearch == 4) {
            rs = con.Results("select art.*, mar.descripcion_marca, stk.cantidad from articulos art, marca mar, stock stk where art.id_articulo=stk.id_articulo and art.id_marca=mar.id_marca and categoria_articulo != 'Cristal'");
            SearchArticulo.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getString("id_articulo");
                row[1] = rs.getString("descripcion_articulo");
                row[2] = rs.getString("categoria_articulo");
                row[3] = rs.getString("descripcion_marca");
                row[4] = rs.getString("costoactual_articulo");
                row[5] = rs.getString("precioventa_articulo");
                row[6] = rs.getString("cantidad");
                SearchArticulo.addRow(row);
            }
        }
    }

    private Boolean Validaciones() {
        if (Flag != 1) {
            if (txtOTNro.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Código vacío. Ingrese un código para el OT.");
                return false;
            }
        }
        if (txtOIEsferico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Esférico' del Ojo Izquierdo está vacío, ingrese un valor antes de continuar.");
            txtOIEsferico.requestFocus();
            return false;
        }
        if (txtODEsferico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Esférico' del Ojo Derecho está vacío, ingrese un valor antes de continuar.");
            txtODEsferico.requestFocus();
            return false;
        }
        if (txtOICilindrico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Cilindrico' del Ojo Izquierdo está vacío, ingrese un valor antes de continuar.");
            txtOICilindrico.requestFocus();
            return false;
        }
        if (txtODCilindrico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Cilindrico' del Ojo Derecho está vacío, ingrese un valor antes de continuar.");
            txtODCilindrico.requestFocus();
            return false;
        }
        if (txtOIEje.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Eje' del Ojo Izquierdo está vacío, ingrese un valor antes de continuar.");
            txtOIEje.requestFocus();
            return false;
        }
        if (txtODEje.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Eje' del Ojo Derecho está vacío, ingrese un valor antes de continuar.");
            txtODEje.requestFocus();
            return false;
        }
        if (txtOIAdicion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Adición' del Ojo Izquierdo está vacío, ingrese un valor antes de continuar.");
            txtOIAdicion.requestFocus();
            return false;
        }
        if (txtODAdicion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor 'Adición' del Ojo Derecho está vacío, ingrese un valor antes de continuar.");
            txtODAdicion.requestFocus();
            return false;
        }
        if (txtDI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor DI está vacío, ingrese un valor antes de continuar.");
            txtDI.requestFocus();
            return false;
        }
        if (txtDND.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor DND está vacío, ingrese un valor antes de continuar.");
            txtDND.requestFocus();
            return false;
        }
        if (txtDNI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor DNI está vacío, ingrese un valor antes de continuar.");
            txtDNI.requestFocus();
            return false;
        }
        if (txtAlturaFocal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El valor de la Altura Focal está vacío, ingrese un valor antes de continuar.");
            txtAlturaFocal.requestFocus();
            return false;
        }
        if (txtCristalCode.getText().equals("") || txtCristalDescr.getText().equals("") || txtCristalPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tipo de Cristal se encuentra vacío, ingrese un tipo de cristal antes de continuar.");
            txtCristalCode.requestFocus();
            return false;
        }
        if (txtObservacion.getText().equals("")) {
            int Respuesta = JOptionPane.showConfirmDialog(null, "Observación no especificada, desea ingresar una nueva descripción?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (Respuesta == 0) {
                txtObservacion.requestFocus();
                return false;
            } else {
                txtObservacion.setText("-");
            }
        }
        if (DetalleArt.getRowCount() == 0) {
            int Respuesta = JOptionPane.showConfirmDialog(null, "No se añadió ningún producto/servicio, desea añadir algún producto/servicio?", "Atención", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (Respuesta == 0) {
                txtArtCode.requestFocus();
                return false;
            }
        }
        if (Flag == 4) {
            int OTSaldo = Integer.parseInt(txtCOTSaldo.getText());
            if (OTSaldo > 0) {
                JOptionPane.showMessageDialog(null, "No es posible cerrar el OT mientras el Saldo no sea 0.\nAsegúrese de agregar todos los métodos de pago que correspondan antes de proceder con el cierre.");
                btnOTCancel.doClick();
                return false;
            } else if (OTSaldo < 0) {
                JOptionPane.showMessageDialog(null, "Error: No es posible continuar con el proceso.\nAsegúrese de que el pago no supere al total de la OT antes de continuar.");
                btnOTCancel.doClick();
                return false;
            }
        }
        return true;
    }

    private Boolean ValidarCliente() {
        if (txtCiSol.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nro. de documento del Cliente Solicitante se encuentra vacío. Ingrese el Nro. de documento del cliente para continuar.");
            return false;
        }
        if (txtClienteSol.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre del Cliente Solicitante se encuentra vacío. Ingrese el Nro. de documento del cliente para continuar.");
            return false;
        }
        if (txtCiPa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nro. de documento del Cliente Paciente se encuentra vacío. Ingrese el Nro. de documento del cliente para continuar.");
            return false;
        }
        if (txtClientePa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre del Cliente Paciente se encuentra vacío. Ingrese el Nro. de documento del cliente para continuar.");
            return false;
        }
        return true;
    }

    private void CargarCboxPMethod() throws SQLException {
        cboxPayMethod.removeAllItems();
        cboxPayMethodCOT.removeAllItems();
        cboxPayMethod.addItem("Selecciona");
        cboxPayMethodCOT.addItem("Selecciona");
        rs = con.Results("select descr_paymethod from paymethod;");
        while (rs.next()) {
            cboxPayMethod.addItem(rs.getString("descr_paymethod"));
            cboxPayMethodCOT.addItem(rs.getString("descr_paymethod"));
        }
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (cboxPayMethod.getSelectedItem().toString().equals("Efectivo")) {
                    txtPayCompr.setEnabled(true);
                    txtPayCompr.setText("");
                    txtPayCompr.requestFocus();
                } else if (!cboxPayMethod.getSelectedItem().toString().equals("Selecciona")) {
                    txtPayCompr.setEnabled(false);
                    txtPayCompr.setText("0");
                    btnPayAdd.requestFocus();
                }
            }
        };
        ItemListener itemListener2 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (cboxPayMethodCOT.getSelectedItem().toString().equals("Efectivo")) {
                    txtPayComprCOT.setEnabled(true);
                    txtPayComprCOT.setText("");
                    txtPayComprCOT.requestFocus();
                } else if (!cboxPayMethodCOT.getSelectedItem().toString().equals("Selecciona")) {
                    txtPayComprCOT.setEnabled(false);
                    txtPayComprCOT.setText("0");
                    btnPayAddCOT.requestFocus();
                }
            }
        };
    }

    private void OTConsultFilter() throws SQLException {
        String fOpen = "";
        String fClose = "";
        String fNull = "";
        if (chkGGOpen.isSelected() == true) {
            if (chkGGClose.isSelected() == true) {
                if (chkGGNull.isSelected() == true) {
                    fOpen = "Abierto";
                    fClose = "Cerrado";
                    fNull = "Anulado";
                } else {
                    fOpen = "Abierto";
                    fClose = "Cerrado";
                }
            } else if (chkGGNull.isSelected() == true) {
                fOpen = "Abierto";
                fNull = "Anulado";
            } else {
                fOpen = "Abierto";
            }
        } else if (chkGGClose.isSelected() == true) {
            if (chkGGNull.isSelected() == true) {
                fClose = "Cerrado";
                fNull = "Anulado";
            } else {
                fClose = "Cerrado";
            }
        } else if (chkGGNull.isSelected() == true) {
            fNull = "Anulado";
        } else {
            JOptionPane.showMessageDialog(null, "Hay algo raro aquí, esto no debería pasar D:");
        }
        String SQL_OT = "";
        String Filters = txtGGFilter.getText();
        if (Filters == "") {
            SQL_OT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente WHERE ot.ot_estado IN ('" + fOpen + "', '" + fClose + "', '" + fNull + "') GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
        } else {
            if (cboxGGFilter.getSelectedItem() == "CI/RUC Paciente") {
                SQL_OT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente WHERE ot.ot_estado IN ('" + fOpen + "', '" + fClose + "', '" + fNull + "') AND cl.ci_cliente ILIKE '%"+txtGGFilter.getText()+"%' GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
            } else if (cboxGGFilter.getSelectedItem() == "Nombre Paciente") {
                SQL_OT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente WHERE ot.ot_estado IN ('" + fOpen + "', '" + fClose + "', '" + fNull + "') AND cl.nombre_cliente ILIKE '%"+txtGGFilter.getText()+"%' GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
            } else if (cboxGGFilter.getSelectedItem() == "Nro OT") {
                if (txtGGFilter.getText().isEmpty()) {
                    SQL_OT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente WHERE ot.ot_estado IN ('" + fOpen + "', '" + fClose + "', '" + fNull + "') GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
                } else if (txtGGFilter.getText() != "") {
                    SQL_OT = "SELECT ot.id_ordentrabajo, ot.id_cliente, ot.id_paciente, ot.fecha_ordentrabajo, cl.nombre_cliente || ' ' || cl.apellido_cliente AS paciente, cl.ci_cliente, ot.oi_cantidad, ot.od_cantidad, ot.ot_estado, ot.observacion, ot.preciocristal, SUM(COALESCE(dot.cantidad_articulo, 0)) AS cantidad_articulo, SUM(COALESCE(dot.subtotal_articulo, 0)) AS subtotal_articulo, ot.subtotal, ot.sena, ot.total FROM ordentrabajo ot JOIN detalle_ordentrabajo dot ON dot.id_ordentrabajo = ot.id_ordentrabajo JOIN cliente cl ON ot.id_paciente = cl.id_cliente WHERE ot.ot_estado IN ('" + fOpen + "', '" + fClose + "', '" + fNull + "') AND ot.id_ordentrabajo = '"+txtGGFilter.getText()+"' GROUP BY ot.id_ordentrabajo, cl.nombre_cliente, cl.apellido_cliente, cl.ci_cliente ORDER BY ot.id_ordentrabajo DESC;";
                }
            }
        }
        rs = con.Results(SQL_OT);
        ConsultOT.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[11];
            row[0] = rs.getString("id_ordentrabajo");
            row[1] = rs.getString("ot_estado");
            row[2] = rs.getString("fecha_ordentrabajo");
            row[3] = rs.getString("ci_cliente");
            row[4] = rs.getString("paciente");
            row[5] = rs.getInt("oi_cantidad") + rs.getInt("od_cantidad");
            row[6] = (rs.getInt("oi_cantidad") + rs.getInt("od_cantidad")) * rs.getInt("preciocristal");
            row[7] = rs.getString("subtotal_articulo");
            row[8] = rs.getString("subtotal");
            row[9] = rs.getString("sena");
            row[10] = rs.getString("total");
            ConsultOT.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearcherCliente = new javax.swing.JDialog();
        lblFiltrarCli = new javax.swing.JLabel();
        btnCerrarCli = new javax.swing.JButton();
        lblMensajeCli = new javax.swing.JLabel();
        cboxFiltrarCli = new javax.swing.JComboBox<>();
        btnCleanFilterCli = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TSearcherCli = new javax.swing.JTable();
        txtFilterCli = new javax.swing.JTextField();
        SearcherArticulo = new javax.swing.JDialog();
        btnCerrarArt = new javax.swing.JButton();
        lblMensajeArt = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TSearcherArt = new javax.swing.JTable();
        SearcherOT = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        TSearcherOT = new javax.swing.JTable();
        btnCerrarOT = new javax.swing.JButton();
        lblMensajeOT = new javax.swing.JLabel();
        WindowCloseOT = new javax.swing.JDialog();
        lblCOTTitle = new javax.swing.JLabel();
        lblCOTNro = new javax.swing.JLabel();
        lblCOTTotal = new javax.swing.JLabel();
        txtCOTTotal = new javax.swing.JTextField();
        lblCOTSena = new javax.swing.JLabel();
        txtCOTSena = new javax.swing.JTextField();
        lblCOTSaldo = new javax.swing.JLabel();
        txtCOTSaldo = new javax.swing.JTextField();
        btnCOTConfirm = new javax.swing.JButton();
        btnCOTCancel = new javax.swing.JButton();
        TablePayMethod1 = new javax.swing.JScrollPane();
        TSeacherPayCOT = new javax.swing.JTable();
        txtPayMethodCOT = new javax.swing.JLabel();
        cboxPayMethodCOT = new javax.swing.JComboBox<>();
        lblPayMountCOT = new javax.swing.JLabel();
        txtPayMountCOT = new javax.swing.JTextField();
        lblPayComprCOT = new javax.swing.JLabel();
        txtPayComprCOT = new javax.swing.JTextField();
        btnPayAddCOT = new javax.swing.JButton();
        btnPayDeleteCOT = new javax.swing.JButton();
        SUConfirm = new javax.swing.JDialog();
        lblSUSubtitle = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnOKUser = new javax.swing.JButton();
        btnCancelUser = new javax.swing.JButton();
        ConsultOTDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGGFilter = new javax.swing.JTextField();
        cboxGGFilter = new javax.swing.JComboBox<>();
        btnGGEraseFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tConsultOT = new javax.swing.JTable();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtGGOTNro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGGOTStatus = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGGOTDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtGGPatientDoc = new javax.swing.JTextField();
        txtGGPatientName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        chkGGOpen = new javax.swing.JCheckBox();
        chkGGNull = new javax.swing.JCheckBox();
        chkGGClose = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        txtGGOTBalance = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtGGOTPay = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtGGOTTotal = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnGGCleanOT = new javax.swing.JButton();
        TabPanel = new javax.swing.JTabbedPane();
        TabCrystal = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGGODSpherical = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGGOISpherical = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtGGODCylindrical = new javax.swing.JTextField();
        txtGGOICylindrical = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGGODAxis = new javax.swing.JTextField();
        txtGGOIAxis = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGGODAddition = new javax.swing.JTextField();
        txtGGOIAddition = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtGGODQuantity = new javax.swing.JTextField();
        txtGGOIQuantity = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtGGDI = new javax.swing.JTextField();
        txtGGAltFoc = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtGGDND = new javax.swing.JTextField();
        txtGGDNI = new javax.swing.JTextField();
        txtGGCrystalType = new javax.swing.JTextField();
        txtGGCrystalDescr = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtGGCrystalTotal = new javax.swing.JTextField();
        txtGGUnitPrice = new javax.swing.JTextField();
        TabArticles = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tConsultOTDetail = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        lblGGImage = new javax.swing.JLabel();
        txtGGTotalArticle = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        TabPayMethod = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tConsultOTPay = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtGGUsername = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtGGObs = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PnlBtnsOT = new javax.swing.JPanel();
        btnOTNew = new javax.swing.JButton();
        btnOTEdit = new javax.swing.JButton();
        btnOTNull = new javax.swing.JButton();
        btnOTClose = new javax.swing.JButton();
        btnOTREOpen = new javax.swing.JButton();
        btnOTConsult = new javax.swing.JButton();
        PnlOTStats = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        dtOTDate = new com.toedter.calendar.JDateChooser();
        lblOt = new javax.swing.JLabel();
        txtOTNro = new javax.swing.JTextField();
        btnSearchOT = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        txtOTState = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        PnlCliente = new javax.swing.JPanel();
        lblSolicitante = new javax.swing.JLabel();
        txtCiSol = new javax.swing.JTextField();
        btnSearchSol = new javax.swing.JButton();
        btnClearSol = new javax.swing.JButton();
        btnCopy = new javax.swing.JButton();
        txtClienteSol = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblPaciente = new javax.swing.JLabel();
        lblCiPa = new javax.swing.JLabel();
        txtCiPa = new javax.swing.JTextField();
        btnSearchPa = new javax.swing.JButton();
        btnClearPa = new javax.swing.JButton();
        chboxMenor = new javax.swing.JCheckBox();
        lblMenor = new javax.swing.JLabel();
        lblCliPa = new javax.swing.JLabel();
        txtClientePa = new javax.swing.JTextField();
        btnConfirmarCli = new javax.swing.JButton();
        lblCliSol = new javax.swing.JLabel();
        lblCiSol = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        PnlCristal = new javax.swing.JPanel();
        lblVistaLejana = new javax.swing.JLabel();
        lblEsferico = new javax.swing.JLabel();
        lblCilindrico = new javax.swing.JLabel();
        lblEje = new javax.swing.JLabel();
        lblAdicion = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        lblOI = new javax.swing.JLabel();
        txtOIEsferico = new javax.swing.JTextField();
        txtOICilindrico = new javax.swing.JTextField();
        txtOIEje = new javax.swing.JTextField();
        lblOIEje = new javax.swing.JLabel();
        txtOIAdicion = new javax.swing.JTextField();
        spinOICantidad = new javax.swing.JSpinner();
        lblOD = new javax.swing.JLabel();
        txtODEsferico = new javax.swing.JTextField();
        txtODCilindrico = new javax.swing.JTextField();
        txtODEje = new javax.swing.JTextField();
        lblODEje = new javax.swing.JLabel();
        txtODAdicion = new javax.swing.JTextField();
        spinODCantidad = new javax.swing.JSpinner();
        lblDI = new javax.swing.JLabel();
        txtDI = new javax.swing.JTextField();
        lblDND = new javax.swing.JLabel();
        txtDND = new javax.swing.JTextField();
        lblDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        lblAlturaFocal = new javax.swing.JLabel();
        txtAlturaFocal = new javax.swing.JTextField();
        lblTipoCristal = new javax.swing.JLabel();
        txtCristalCode = new javax.swing.JTextField();
        btnCristalSearch = new javax.swing.JButton();
        btnCristClean = new javax.swing.JButton();
        txtCristalDescr = new javax.swing.JTextField();
        lblPrecioCristal = new javax.swing.JLabel();
        txtCristalPrice = new javax.swing.JTextField();
        lblCristalTotal = new javax.swing.JLabel();
        txtCristalTotal = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        PnlArticulo = new javax.swing.JPanel();
        TableDetailArticle = new javax.swing.JScrollPane();
        TSearcherDetalle = new javax.swing.JTable();
        btnArtRemove = new javax.swing.JButton();
        lblArtTitle = new javax.swing.JLabel();
        lblArtCode = new javax.swing.JLabel();
        txtArtCode = new javax.swing.JTextField();
        btnArtSearch = new javax.swing.JButton();
        lblArtDescr = new javax.swing.JLabel();
        txtArtDescr = new javax.swing.JTextField();
        lblArtUnitPrice = new javax.swing.JLabel();
        txtArtUnitPrice = new javax.swing.JTextField();
        lblArtQuantity = new javax.swing.JLabel();
        spinArtQuantity = new javax.swing.JSpinner();
        lblArtSubtotal = new javax.swing.JLabel();
        txtArtSubtotal = new javax.swing.JTextField();
        btnArtAdd = new javax.swing.JButton();
        btnArtClean = new javax.swing.JButton();
        lblArtTotal = new javax.swing.JLabel();
        txtArtTotal = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lblObservacion = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        PnlPayMethod = new javax.swing.JPanel();
        txtPayMethod = new javax.swing.JLabel();
        cboxPayMethod = new javax.swing.JComboBox<>();
        lblPayMount = new javax.swing.JLabel();
        txtPayMount = new javax.swing.JTextField();
        lblPayCompr = new javax.swing.JLabel();
        txtPayCompr = new javax.swing.JTextField();
        btnPayAdd = new javax.swing.JButton();
        btnPayDelete = new javax.swing.JButton();
        TablePayMethod = new javax.swing.JScrollPane();
        TSeacherPay = new javax.swing.JTable();
        lblOTSubtotal = new javax.swing.JLabel();
        txtOTSubtotal = new javax.swing.JTextField();
        lblSena = new javax.swing.JLabel();
        txtSena = new javax.swing.JTextField();
        lblSaldo = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        btnOTConfirm = new javax.swing.JButton();
        btnOTCancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        lblFiltrarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filtrar.png"))); // NOI18N
        lblFiltrarCli.setText("Filtrar por:");

        btnCerrarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrarCli.setText("Cerrar");
        btnCerrarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCliActionPerformed(evt);
            }
        });

        lblMensajeCli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeCli.setText("null");

        cboxFiltrarCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CI/RUC", "Nombres", "Apellidos", "Teléfono", "Ciudad" }));
        cboxFiltrarCli.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxFiltrarCliItemStateChanged(evt);
            }
        });

        btnCleanFilterCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnCleanFilterCli.setText("Limpiar Filtro");

        TSearcherCli.setModel(SearchCliente);
        TSearcherCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSearcherCliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TSearcherCli);

        txtFilterCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterCliKeyReleased(evt);
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
                        .addComponent(lblFiltrarCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFiltrarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilterCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCleanFilterCli, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMensajeCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearcherClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrarCli)))
                .addContainerGap())
        );
        SearcherClienteLayout.setVerticalGroup(
            SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensajeCli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SearcherClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilterCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltrarCli)
                    .addComponent(cboxFiltrarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCleanFilterCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarCli)
                .addContainerGap())
        );

        btnCerrarArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrarArt.setText("Cerrar");
        btnCerrarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarArtActionPerformed(evt);
            }
        });

        lblMensajeArt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeArt.setText("null");

        TSearcherArt.setModel(SearchArticulo);
        TSearcherArt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSearcherArtMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TSearcherArt);

        javax.swing.GroupLayout SearcherArticuloLayout = new javax.swing.GroupLayout(SearcherArticulo.getContentPane());
        SearcherArticulo.getContentPane().setLayout(SearcherArticuloLayout);
        SearcherArticuloLayout.setHorizontalGroup(
            SearcherArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                    .addComponent(lblMensajeArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearcherArticuloLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrarArt)))
                .addContainerGap())
        );
        SearcherArticuloLayout.setVerticalGroup(
            SearcherArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensajeArt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarArt)
                .addContainerGap())
        );

        TSearcherOT.setModel(SearchOT);
        TSearcherOT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSearcherOTMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TSearcherOT);

        btnCerrarOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrarOT.setText("Cerrar");
        btnCerrarOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarOTActionPerformed(evt);
            }
        });

        lblMensajeOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeOT.setText("null");

        javax.swing.GroupLayout SearcherOTLayout = new javax.swing.GroupLayout(SearcherOT.getContentPane());
        SearcherOT.getContentPane().setLayout(SearcherOTLayout);
        SearcherOTLayout.setHorizontalGroup(
            SearcherOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherOTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearcherOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
                    .addComponent(lblMensajeOT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearcherOTLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrarOT)))
                .addContainerGap())
        );
        SearcherOTLayout.setVerticalGroup(
            SearcherOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearcherOTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensajeOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarOT)
                .addContainerGap())
        );

        lblCOTTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        lblCOTTitle.setText("Cerrar OT N°");

        lblCOTNro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCOTNro.setText("null");

        lblCOTTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCOTTotal.setText("Total OT");

        txtCOTTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblCOTSena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCOTSena.setText("Seña");

        txtCOTSena.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblCOTSaldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCOTSaldo.setText("Saldo");

        txtCOTSaldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCOTConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnCOTConfirm.setText("Confirmar");
        btnCOTConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOTConfirmActionPerformed(evt);
            }
        });

        btnCOTCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCOTCancel.setText("Cancelar");
        btnCOTCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOTCancelActionPerformed(evt);
            }
        });

        TSeacherPayCOT.setModel(PayMethod);
        TablePayMethod1.setViewportView(TSeacherPayCOT);

        txtPayMethodCOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPayMethodCOT.setText("Método de Pago");

        cboxPayMethodCOT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxPayMethodCOTItemStateChanged(evt);
            }
        });

        lblPayMountCOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayMountCOT.setText("Monto");

        txtPayMountCOT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPayMountCOT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayMountCOTKeyPressed(evt);
            }
        });

        lblPayComprCOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayComprCOT.setText("Nro. Comprobante");

        txtPayComprCOT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPayComprCOT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayComprCOTKeyPressed(evt);
            }
        });

        btnPayAddCOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnPayAddCOT.setToolTipText("Añadir método de pago");
        btnPayAddCOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayAddCOTActionPerformed(evt);
            }
        });

        btnPayDeleteCOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnPayDeleteCOT.setToolTipText("Quitar método de pago seleccionado ya cargado");
        btnPayDeleteCOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayDeleteCOTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WindowCloseOTLayout = new javax.swing.GroupLayout(WindowCloseOT.getContentPane());
        WindowCloseOT.getContentPane().setLayout(WindowCloseOTLayout);
        WindowCloseOTLayout.setHorizontalGroup(
            WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WindowCloseOTLayout.createSequentialGroup()
                        .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                                .addComponent(lblCOTTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCOTNro))
                            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCOTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCOTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCOTSena, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCOTSena, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TablePayMethod1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WindowCloseOTLayout.createSequentialGroup()
                                        .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                                                .addComponent(cboxPayMethodCOT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPayMountCOT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                                                .addComponent(txtPayMethodCOT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblPayMountCOT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPayComprCOT)
                                            .addComponent(lblPayComprCOT, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPayAddCOT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPayDeleteCOT)))
                                .addGroup(WindowCloseOTLayout.createSequentialGroup()
                                    .addComponent(lblCOTSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCOTSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WindowCloseOTLayout.createSequentialGroup()
                        .addGap(0, 244, Short.MAX_VALUE)
                        .addComponent(btnCOTConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCOTCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        WindowCloseOTLayout.setVerticalGroup(
            WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WindowCloseOTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCOTTitle)
                    .addComponent(lblCOTNro))
                .addGap(18, 18, 18)
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WindowCloseOTLayout.createSequentialGroup()
                        .addComponent(lblCOTTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCOTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(WindowCloseOTLayout.createSequentialGroup()
                        .addComponent(lblCOTSena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCOTSena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPayComprCOT)
                    .addComponent(lblPayMountCOT)
                    .addComponent(txtPayMethodCOT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxPayMethodCOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPayMountCOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPayAddCOT)
                            .addComponent(txtPayComprCOT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPayDeleteCOT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TablePayMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCOTSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCOTSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(WindowCloseOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCOTConfirm)
                    .addComponent(btnCOTCancel))
                .addContainerGap())
        );

        lblSUSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSUSubtitle.setText("null");

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
                .addComponent(lblSUSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(lblSUSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Consulta de OTs");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/filtrar.png"))); // NOI18N
        jLabel2.setText("Filtrar por:");

        txtGGFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGGFilterKeyReleased(evt);
            }
        });

        cboxGGFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CI/RUC Paciente", "Nombre Paciente", "Nro OT" }));

        btnGGEraseFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N

        tConsultOT.setModel(ConsultOT);
        tConsultOT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tConsultOTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tConsultOT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("OT N°");

        txtGGOTNro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Estado");

        txtGGOTStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha");

        txtGGOTDate.setDateFormatString("dd/MM/yyyy");

        jLabel7.setText("Paciente");

        txtGGPatientDoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Estado:");

        chkGGOpen.setSelected(true);
        chkGGOpen.setText("Abierto");
        chkGGOpen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkGGOpenItemStateChanged(evt);
            }
        });

        chkGGNull.setSelected(true);
        chkGGNull.setText("Anulado");
        chkGGNull.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkGGNullItemStateChanged(evt);
            }
        });

        chkGGClose.setSelected(true);
        chkGGClose.setText("Cerrado");
        chkGGClose.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkGGCloseItemStateChanged(evt);
            }
        });

        txtGGOTBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Saldo");

        txtGGOTPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Pago");

        txtGGOTTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Total");

        btnGGCleanOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnGGCleanOT.setText("Limpiar Campos");
        btnGGCleanOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGGCleanOTActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Ojo Derecho");

        txtGGODSpherical.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Ojo Izquierdo");

        txtGGOISpherical.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Esferico");

        txtGGODCylindrical.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGOICylindrical.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cilíndrico");

        txtGGODAxis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGOIAxis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Eje");

        jLabel14.setText("°");

        jLabel15.setText("°");

        txtGGODAddition.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGOIAddition.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Adición");

        txtGGODQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGOIQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Cant.");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Alt. Focal");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("DI");

        txtGGDI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGAltFoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("DND");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("DNI");

        txtGGDND.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGDNI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGCrystalType.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Tipo Cristal");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Total Cristal");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Pr. Unit.");

        txtGGCrystalTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtGGUnitPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout TabCrystalLayout = new javax.swing.GroupLayout(TabCrystal);
        TabCrystal.setLayout(TabCrystalLayout);
        TabCrystalLayout.setHorizontalGroup(
            TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabCrystalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TabCrystalLayout.createSequentialGroup()
                        .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabCrystalLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGOISpherical, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TabCrystalLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGGODSpherical, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGGOICylindrical, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGGODCylindrical, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabCrystalLayout.createSequentialGroup()
                                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGGODAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TabCrystalLayout.createSequentialGroup()
                                .addComponent(txtGGOIAxis, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGGODAddition, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGGOIAddition, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGGOIQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(TabCrystalLayout.createSequentialGroup()
                                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGGODQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGDI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGDND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGAltFoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TabCrystalLayout.createSequentialGroup()
                            .addComponent(txtGGCrystalType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGCrystalDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TabCrystalLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGCrystalTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        TabCrystalLayout.setVerticalGroup(
            TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabCrystalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TabCrystalLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGODSpherical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGOISpherical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(TabCrystalLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGODCylindrical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGOICylindrical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(TabCrystalLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGGODAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGGOIAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(TabCrystalLayout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGODAddition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGOIAddition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(TabCrystalLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGGODQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGDI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGDND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGGAltFoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGGOIQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGGCrystalType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGGCrystalDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TabCrystalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGGUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGGCrystalTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        TabPanel.addTab("Cristal", TabCrystal);

        tConsultOTDetail.setModel(ConsultOTDetail);
        tConsultOTDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tConsultOTDetailMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tConsultOTDetail);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Artículos añadidos");

        lblGGImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtGGTotalArticle.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel32.setText("Total Artículos");

        javax.swing.GroupLayout TabArticlesLayout = new javax.swing.GroupLayout(TabArticles);
        TabArticles.setLayout(TabArticlesLayout);
        TabArticlesLayout.setHorizontalGroup(
            TabArticlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabArticlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabArticlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabArticlesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGTotalArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGGImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TabArticlesLayout.setVerticalGroup(
            TabArticlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabArticlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TabArticlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGGImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabArticlesLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TabArticlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGGTotalArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        TabPanel.addTab("Artículos", TabArticles);

        tConsultOTPay.setModel(ConsultOTPay);
        jScrollPane5.setViewportView(tConsultOTPay);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Métodos de Pago");

        javax.swing.GroupLayout TabPayMethodLayout = new javax.swing.GroupLayout(TabPayMethod);
        TabPayMethod.setLayout(TabPayMethodLayout);
        TabPayMethodLayout.setHorizontalGroup(
            TabPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabPayMethodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(556, Short.MAX_VALUE))
        );
        TabPayMethodLayout.setVerticalGroup(
            TabPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabPayMethodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabPanel.addTab("Métodos de Pago", TabPayMethod);

        txtGGUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Usuario");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Observación");

        javax.swing.GroupLayout ConsultOTDialogLayout = new javax.swing.GroupLayout(ConsultOTDialog.getContentPane());
        ConsultOTDialog.getContentPane().setLayout(ConsultOTDialogLayout);
        ConsultOTDialogLayout.setHorizontalGroup(
            ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator11)
                    .addComponent(jSeparator6)
                    .addComponent(TabPanel)
                    .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGOTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGOTPay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGOTBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGGCleanOT))
                    .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxGGFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGGEraseFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkGGOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkGGNull, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkGGClose, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                        .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGOTNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGOTStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGOTDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGPatientDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGGObs, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGGUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ConsultOTDialogLayout.setVerticalGroup(
            ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsultOTDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGGFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxGGFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGGEraseFilter))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkGGOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkGGNull, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkGGClose, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGGOTNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGGOTStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtGGOTDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGGPatientDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGGPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGGUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGGObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsultOTDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGGOTBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGGOTPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGGOTTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGGCleanOT))
                .addContainerGap())
        );

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnOTNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btnOTNew.setText("Nuevo OT");
        btnOTNew.setToolTipText("Realizar un nuevo Orden de Trabajo");
        btnOTNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTNewActionPerformed(evt);
            }
        });

        btnOTEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnOTEdit.setText("Editar OT");
        btnOTEdit.setToolTipText("Editar Orden de Trabajo existente");
        btnOTEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTEditActionPerformed(evt);
            }
        });

        btnOTNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disabled.png"))); // NOI18N
        btnOTNull.setText("Anular OT");
        btnOTNull.setToolTipText("Anular Orden de Trabajo existente");
        btnOTNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTNullActionPerformed(evt);
            }
        });

        btnOTClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        btnOTClose.setText("Cerrar OT");
        btnOTClose.setToolTipText("Cerrar Orden de Trabajo pendiente");
        btnOTClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTCloseActionPerformed(evt);
            }
        });

        btnOTREOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/complete.png"))); // NOI18N
        btnOTREOpen.setText("Reabrir OT");
        btnOTREOpen.setToolTipText("Cerrar Orden de Trabajo pendiente");
        btnOTREOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTREOpenActionPerformed(evt);
            }
        });

        btnOTConsult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnOTConsult.setText("Consulta OT");
        btnOTConsult.setToolTipText("Cerrar Orden de Trabajo pendiente");
        btnOTConsult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTConsultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlBtnsOTLayout = new javax.swing.GroupLayout(PnlBtnsOT);
        PnlBtnsOT.setLayout(PnlBtnsOTLayout);
        PnlBtnsOTLayout.setHorizontalGroup(
            PnlBtnsOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlBtnsOTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlBtnsOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlBtnsOTLayout.createSequentialGroup()
                        .addComponent(btnOTNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOTEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOTNull, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlBtnsOTLayout.createSequentialGroup()
                        .addComponent(btnOTREOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOTClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOTConsult, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlBtnsOTLayout.setVerticalGroup(
            PnlBtnsOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlBtnsOTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlBtnsOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOTNew)
                    .addComponent(btnOTNull)
                    .addComponent(btnOTEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlBtnsOTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOTClose)
                    .addComponent(btnOTREOpen)
                    .addComponent(btnOTConsult))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("Fecha");

        lblOt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOt.setText("OT N°");

        txtOTNro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOTNro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOTNroKeyPressed(evt);
            }
        });

        btnSearchOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearchOT.setToolTipText("Buscar Ordenes de Trabajo");
        btnSearchOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchOTActionPerformed(evt);
            }
        });

        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEstado.setText("Estado");

        txtOTState.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout PnlOTStatsLayout = new javax.swing.GroupLayout(PnlOTStats);
        PnlOTStats.setLayout(PnlOTStatsLayout);
        PnlOTStatsLayout.setHorizontalGroup(
            PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlOTStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlOTStatsLayout.createSequentialGroup()
                        .addComponent(lblOt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOTNro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchOT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtOTDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlOTStatsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOTState, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlOTStatsLayout.setVerticalGroup(
            PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlOTStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOTNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtOTDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchOT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlOTStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOTState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSolicitante.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSolicitante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSolicitante.setText("Solicitante");

        txtCiSol.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCiSol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCiSolFocusLost(evt);
            }
        });
        txtCiSol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiSolKeyPressed(evt);
            }
        });

        btnSearchSol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearchSol.setToolTipText("Buscar clientes");
        btnSearchSol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSolActionPerformed(evt);
            }
        });

        btnClearSol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/backspace.png"))); // NOI18N
        btnClearSol.setToolTipText("Borrar campo");
        btnClearSol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSolActionPerformed(evt);
            }
        });

        btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transfer.png"))); // NOI18N
        btnCopy.setToolTipText("Copiar datos del cliente Solicitante al Paciente");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblPaciente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente.setText("Paciente");

        lblCiPa.setText("CI/RUC");

        txtCiPa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCiPa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCiPaFocusLost(evt);
            }
        });
        txtCiPa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiPaKeyPressed(evt);
            }
        });

        btnSearchPa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnSearchPa.setToolTipText("Buscar clientes");
        btnSearchPa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPaActionPerformed(evt);
            }
        });

        btnClearPa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/backspace.png"))); // NOI18N
        btnClearPa.setToolTipText("Borrar campo");
        btnClearPa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearPaActionPerformed(evt);
            }
        });

        lblMenor.setText("Menor de edad");

        lblCliPa.setText("Cliente");

        btnConfirmarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirmarCli.setText("Confirmar");
        btnConfirmarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarCliActionPerformed(evt);
            }
        });

        lblCliSol.setText("Cliente");

        lblCiSol.setText("CI/RUC");

        javax.swing.GroupLayout PnlClienteLayout = new javax.swing.GroupLayout(PnlCliente);
        PnlCliente.setLayout(PnlClienteLayout);
        PnlClienteLayout.setHorizontalGroup(
            PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblCliSol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteSol, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addComponent(lblSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCiSol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCiSol, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchSol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearSol)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCopy)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblCliPa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClientePa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirmarCli))
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCiPa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCiPa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchPa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearPa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chboxMenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMenor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlClienteLayout.setVerticalGroup(
            PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(PnlClienteLayout.createSequentialGroup()
                        .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlClienteLayout.createSequentialGroup()
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCiPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCiPa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnSearchPa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClearPa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chboxMenor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtClientePa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCliPa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConfirmarCli)))
                            .addGroup(PnlClienteLayout.createSequentialGroup()
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCiSol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearchSol)
                                        .addComponent(lblCiSol, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSolicitante))
                                    .addComponent(btnCopy)
                                    .addComponent(btnClearSol))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtClienteSol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCliSol, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lblVistaLejana.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVistaLejana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVistaLejana.setText("Vista Lejana");

        lblEsferico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEsferico.setText("Esférico");

        lblCilindrico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCilindrico.setText("Cilindrico");

        lblEje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEje.setText("Eje");

        lblAdicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdicion.setText("Adición");

        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setText("Cantidad");

        lblOI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOI.setText("Ojo Izq.");

        txtOIEsferico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtOICilindrico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtOIEje.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblOIEje.setText("°");

        txtOIAdicion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        spinOICantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spinOICantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinOICantidadStateChanged(evt);
            }
        });

        lblOD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOD.setText("Ojo Der.");

        txtODEsferico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtODCilindrico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtODEje.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblODEje.setText("°");

        txtODAdicion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        spinODCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spinODCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinODCantidadStateChanged(evt);
            }
        });

        lblDI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDI.setText("DI");

        txtDI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblDND.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDND.setText("DND");

        txtDND.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblDNI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDNI.setText("DNI");

        txtDNI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblAlturaFocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlturaFocal.setText("Alt. focal");

        txtAlturaFocal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblTipoCristal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoCristal.setText("Tipo de Cristal");

        txtCristalCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCristalCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCristalCodeKeyPressed(evt);
            }
        });

        btnCristalSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnCristalSearch.setToolTipText("Buscar tipos de cristal");
        btnCristalSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCristalSearchActionPerformed(evt);
            }
        });

        btnCristClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnCristClean.setToolTipText("Limpiar campos del cristal");
        btnCristClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCristCleanActionPerformed(evt);
            }
        });

        lblPrecioCristal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioCristal.setText("Precio Unitario");

        txtCristalPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCristalPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCristalPriceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCristalPriceFocusLost(evt);
            }
        });

        lblCristalTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCristalTotal.setText("Total Cristal");

        txtCristalTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout PnlCristalLayout = new javax.swing.GroupLayout(PnlCristal);
        PnlCristal.setLayout(PnlCristalLayout);
        PnlCristalLayout.setHorizontalGroup(
            PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCristalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDND, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDND, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAlturaFocal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAlturaFocal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCristalCode)
                                    .addComponent(lblTipoCristal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCristalSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCristalDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioCristal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCristalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createSequentialGroup()
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblOI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtOIEsferico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtODEsferico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblEsferico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtOICilindrico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtODCilindrico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblCilindrico, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtOIEje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtODEje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PnlCristalLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(lblEje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblOIEje)
                                .addComponent(lblODEje))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtOIAdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblAdicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtODAdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(spinODCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinOICantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblVistaLejana, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createSequentialGroup()
                        .addComponent(btnCristClean)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCristalTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCristalTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        PnlCristalLayout.setVerticalGroup(
            PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlCristalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVistaLejana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addComponent(lblCantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinODCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinOICantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addComponent(lblAdicion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PnlCristalLayout.createSequentialGroup()
                                        .addComponent(txtODAdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtOIAdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PnlCristalLayout.createSequentialGroup()
                                        .addComponent(lblODEje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblOIEje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createSequentialGroup()
                            .addComponent(lblEje)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtODEje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtOIEje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createSequentialGroup()
                            .addComponent(lblCilindrico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtODCilindrico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtOICilindrico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblEsferico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addComponent(txtODEsferico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOIEsferico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlCristalLayout.createSequentialGroup()
                                .addComponent(lblOD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOI, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblDI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblDND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PnlCristalLayout.createSequentialGroup()
                            .addComponent(lblDNI)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PnlCristalLayout.createSequentialGroup()
                            .addComponent(lblAlturaFocal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAlturaFocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblTipoCristal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCristalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCristalSearch))
                            .addComponent(txtCristalDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblPrecioCristal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCristalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGroup(PnlCristalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnlCristalLayout.createSequentialGroup()
                        .addComponent(lblCristalTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCristalTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCristClean))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        TSearcherDetalle.setModel(DetalleArt);
        TSearcherDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableDetailArticle.setViewportView(TSearcherDetalle);

        btnArtRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnArtRemove.setToolTipText("Quitar artículo seleccionado ya cargado");
        btnArtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtRemoveActionPerformed(evt);
            }
        });

        lblArtTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblArtTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtTitle.setText("Productos/Servicios Adicionales");

        lblArtCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtCode.setText("Código");

        txtArtCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArtCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtArtCodeKeyPressed(evt);
            }
        });

        btnArtSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnArtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtSearchActionPerformed(evt);
            }
        });

        lblArtDescr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtDescr.setText("Descripción");

        lblArtUnitPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtUnitPrice.setText("Precio Unit.");

        txtArtUnitPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblArtQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtQuantity.setText("Cantidad");

        spinArtQuantity.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spinArtQuantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinArtQuantityStateChanged(evt);
            }
        });
        spinArtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spinArtQuantityFocusLost(evt);
            }
        });
        spinArtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spinArtQuantityKeyPressed(evt);
            }
        });

        lblArtSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtSubtotal.setText("SubTotal");

        txtArtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArtSubtotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtArtSubtotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtArtSubtotalFocusLost(evt);
            }
        });

        btnArtAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnArtAdd.setToolTipText("Añadir artículo cargado");
        btnArtAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtAddActionPerformed(evt);
            }
        });

        btnArtClean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnArtClean.setToolTipText("Limpiar campos del artículo");
        btnArtClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtCleanActionPerformed(evt);
            }
        });

        lblArtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblArtTotal.setText("Total Artículos");

        txtArtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblImage.setForeground(new java.awt.Color(0, 0, 0));
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout PnlArticuloLayout = new javax.swing.GroupLayout(PnlArticulo);
        PnlArticulo.setLayout(PnlArticuloLayout);
        PnlArticuloLayout.setHorizontalGroup(
            PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlArticuloLayout.createSequentialGroup()
                        .addComponent(lblArtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TableDetailArticle)
                    .addGroup(PnlArticuloLayout.createSequentialGroup()
                        .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtArtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblArtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArtSearch)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PnlArticuloLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblArtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PnlArticuloLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(txtArtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtArtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblArtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinArtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblArtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtArtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblArtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArtAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArtClean)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnArtRemove))
                            .addComponent(lblArtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlArticuloLayout.setVerticalGroup(
            PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlArticuloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlArticuloLayout.createSequentialGroup()
                        .addComponent(lblArtTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addComponent(lblArtDescr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtArtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addComponent(lblArtCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtArtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnArtSearch))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArtQuantity)
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addComponent(lblArtUnitPrice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtArtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinArtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PnlArticuloLayout.createSequentialGroup()
                                .addComponent(lblArtSubtotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtArtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnArtAdd)
                                    .addComponent(btnArtClean)
                                    .addComponent(btnArtRemove))))
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TableDetailArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblObservacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObservacion.setText("Observación");

        txtPayMethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPayMethod.setText("Método de Pago");

        cboxPayMethod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxPayMethodItemStateChanged(evt);
            }
        });

        lblPayMount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayMount.setText("Monto");

        txtPayMount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPayMount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayMountKeyPressed(evt);
            }
        });

        lblPayCompr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayCompr.setText("Nro. Comprobante");

        txtPayCompr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPayCompr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayComprKeyPressed(evt);
            }
        });

        btnPayAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnPayAdd.setToolTipText("Añadir método de pago");
        btnPayAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayAddActionPerformed(evt);
            }
        });

        btnPayDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnPayDelete.setToolTipText("Quitar método de pago seleccionado ya cargado");
        btnPayDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayDeleteActionPerformed(evt);
            }
        });

        TSeacherPay.setModel(PayMethod);
        TablePayMethod.setViewportView(TSeacherPay);

        lblOTSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOTSubtotal.setText("Total OT");
        lblOTSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtOTSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblSena.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSena.setText("Seña");

        txtSena.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenaKeyReleased(evt);
            }
        });

        lblSaldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldo.setText("Saldo");

        txtSaldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnOTConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/floppy-disk.png"))); // NOI18N
        btnOTConfirm.setText("Confirmar");
        btnOTConfirm.setToolTipText("");
        btnOTConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTConfirmActionPerformed(evt);
            }
        });

        btnOTCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disabled.png"))); // NOI18N
        btnOTCancel.setText("Cancelar");
        btnOTCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOTCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOTCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlPayMethodLayout = new javax.swing.GroupLayout(PnlPayMethod);
        PnlPayMethod.setLayout(PnlPayMethodLayout);
        PnlPayMethodLayout.setHorizontalGroup(
            PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlPayMethodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlPayMethodLayout.createSequentialGroup()
                        .addComponent(cboxPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPayMount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlPayMethodLayout.createSequentialGroup()
                        .addComponent(txtPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPayMount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlPayMethodLayout.createSequentialGroup()
                        .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPayCompr)
                            .addComponent(lblPayCompr, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPayAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TablePayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPayDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlPayMethodLayout.createSequentialGroup()
                        .addComponent(btnOTConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOTCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlPayMethodLayout.createSequentialGroup()
                        .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSena, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOTSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOTSubtotal)
                            .addComponent(txtSena)
                            .addComponent(jSeparator1)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlPayMethodLayout.setVerticalGroup(
            PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlPayMethodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TablePayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PnlPayMethodLayout.createSequentialGroup()
                        .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlPayMethodLayout.createSequentialGroup()
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtOTSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOTSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSena, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSaldo)
                                    .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnOTConfirm)
                                    .addComponent(btnOTCancel)))
                            .addGroup(PnlPayMethodLayout.createSequentialGroup()
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPayMount)
                                    .addComponent(txtPayMethod))
                                .addGap(7, 7, 7)
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboxPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPayMount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPayCompr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlPayMethodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPayCompr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPayAdd)))
                            .addComponent(btnPayDelete))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlBtnsOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PnlOTStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservacion))
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnlPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(PnlCristal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PnlArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(PnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlBtnsOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PnlOTStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnlArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlCristal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOTNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTNewActionPerformed
        try {
            Flag = 1;
            HabilitarBtn1();
            HabilitarTxt1();
            NuevoOT();
            btnSearchOT.setEnabled(false);
            txtCiSol.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOTNewActionPerformed

    private void btnOTEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTEditActionPerformed
        Flag = 2;
        lblSUSubtitle.setText("Ingrese un usuario administrativo para continuar con la operación.");
        SUConfirm.setTitle("Atención: Acción requerida - Modificación de OT");
        SUConfirm.setModal(true);
        SUConfirm.pack();
        SUConfirm.setResizable(false);
        SUConfirm.setLocationRelativeTo(null);
        SUConfirm.setVisible(true);
    }//GEN-LAST:event_btnOTEditActionPerformed

    private void btnOTNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTNullActionPerformed
        Flag = 3;
        lblSUSubtitle.setText("Ingrese un usuario administrativo para continuar con la operación.");
        SUConfirm.setTitle("Atención: Acción requerida - Anulación de OT");
        SUConfirm.setModal(true);
        SUConfirm.pack();
        SUConfirm.setResizable(false);
        SUConfirm.setLocationRelativeTo(null);
        SUConfirm.setVisible(true);
    }//GEN-LAST:event_btnOTNullActionPerformed

    private void btnConfirmarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarCliActionPerformed
        if (ValidarCliente() == true) {
            DeshabilitarClientPanel();
            HabilitarBtn2();
            HabilitarTxt2();
            txtODEsferico.requestFocus();
        }
    }//GEN-LAST:event_btnConfirmarCliActionPerformed

    private void btnOTCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTCancelActionPerformed
        try {
            Flag = 0;
            DeshabilitarBtn1();
            DeshabilitarBtn2();
            DeshabilitarTxt1();
            DeshabilitarTxt2();
            LimpiarTxt();
            LimpiarTxt2();
            CheckUserPermissions(Menu.permissions);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOTCancelActionPerformed

    private void btnOTConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTConfirmActionPerformed
        if (ValidarCliente() == true) {
            if (Validaciones() == true) {
                try {
                    ProcessData();
                    LimpiarTxt();
                    LimpiarTxt2();
                    DeshabilitarBtn1();
                    DeshabilitarBtn2();
                    DeshabilitarTxt1();
                    DeshabilitarTxt2();
                    CheckUserPermissions(Menu.permissions);
                    Flag = 0;
                } catch (SQLException ex) {
                    Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnOTConfirmActionPerformed

    private void btnCerrarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCliActionPerformed
        SearcherCliente.setVisible(false);
    }//GEN-LAST:event_btnCerrarCliActionPerformed

    private void cboxFiltrarCliItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxFiltrarCliItemStateChanged
        try {
            txtFilterCli.setText("");
            CargarTablaClientes();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboxFiltrarCliItemStateChanged

    private void TSearcherCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherCliMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcherCli.getSelectedRow();
                String ciCliente = TSearcherCli.getModel().getValueAt(row, 1).toString();
                SearcherCliente.setModal(false);
                SearcherCliente.setVisible(false);
                if (TagSearch == 1) {
                    txtCiSol.setText(ciCliente);
                    RecuperarCliente(txtCiSol.getText());
                } else if (TagSearch == 2) {
                    txtCiPa.setText(ciCliente);
                    RecuperarCliente(txtCiPa.getText());
                }
                if (Flag == 3) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        btnOTConfirm.doClick();
                    } else {
                        btnOTCancel.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherCliMouseClicked

    private void txtCiSolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiSolKeyPressed
        if (evt.getKeyCode() == 10 && !txtCiSol.getText().equals("")) {
            try {
                TagSearch = 1;
                RecuperarCliente(txtCiSol.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtCiSol.getText().equals("")) {
            try {
                CargarTablaClientes();
                if (Flag == 2) {
                    lblMensajeCli.setText("Selecciona un cliente");
                } else if (Flag == 3) {
                    lblMensajeCli.setText("Selecciona un cliente");
                }
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
    }//GEN-LAST:event_txtCiSolKeyPressed

    private void txtCiPaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiPaKeyPressed
        if (evt.getKeyCode() == 10 && !txtCiPa.getText().equals("")) {
            try {
                TagSearch = 2;
                RecuperarCliente(txtCiPa.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtCiPa.getText().equals("")) {
            try {
                CargarTablaClientes();
                if (Flag == 2) {
                    lblMensajeCli.setText("Selecciona un cliente");
                } else if (Flag == 3) {
                    lblMensajeCli.setText("Selecciona un cliente");
                }
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
    }//GEN-LAST:event_txtCiPaKeyPressed

    private void btnCerrarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarArtActionPerformed
        SearcherArticulo.setVisible(false);
    }//GEN-LAST:event_btnCerrarArtActionPerformed

    private void TSearcherArtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherArtMouseClicked
        if (TagSearch == 3) {
            try {
                int row = TSearcherArt.getSelectedRow();
                String id = TSearcherArt.getModel().getValueAt(row, 0).toString();
                SearcherArticulo.setModal(false);
                SearcherArticulo.setVisible(false);
                txtCristalCode.setText(id);
                RecuperarArticulo(txtCristalCode.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (TagSearch == 4) {
            try {
                int row = TSearcherArt.getSelectedRow();
                String id = TSearcherArt.getModel().getValueAt(row, 0).toString();
                SearcherArticulo.setModal(false);
                SearcherArticulo.setVisible(false);
                txtArtCode.setText(id);
                RecuperarArticulo(txtArtCode.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherArtMouseClicked

    private void TSearcherOTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherOTMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcherOT.getSelectedRow();
                String id = TSearcherOT.getModel().getValueAt(row, 0).toString();
                SearcherOT.setModal(false);
                SearcherOT.setVisible(false);
                txtOTNro.setText(id);
                RecuperarOT(txtOTNro.getText());
                if (Flag == 3) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar la OT N° " + id + "?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        btnOTConfirm.doClick();
                    } else {
                        btnOTCancel.doClick();
                    }
                } else if (Flag == 4) {
                    lblCOTNro.setText(txtOTNro.getText());
                    Integer OtTotal = Integer.parseInt(txtArtTotal.getText()) + Integer.parseInt(txtCristalTotal.getText());
                    txtCOTTotal.setText(OtTotal.toString());
                    txtCOTTotal.setEditable(false);
                    txtCOTSena.setText(txtSena.getText());
                    txtCOTSena.setEditable(false);
                    txtCOTSaldo.setText(txtSaldo.getText());
                    txtCOTSaldo.setEditable(false);
                    WindowCloseOT.setTitle("Cerrar OT");
                    WindowCloseOT.setModal(true);
                    WindowCloseOT.pack();
                    WindowCloseOT.setResizable(false);
                    WindowCloseOT.setLocationRelativeTo(null);
                    WindowCloseOT.setVisible(true);
                } else if (Flag == 5) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Desea reabrir la OT N° " + id + "?", "Atención", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        btnOTConfirm.doClick();
                    } else {
                        btnOTCancel.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherOTMouseClicked

    private void btnCerrarOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarOTActionPerformed
        SearcherOT.setVisible(false);
    }//GEN-LAST:event_btnCerrarOTActionPerformed

    private void btnClearSolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSolActionPerformed
        txtCiSol.setText("");
        txtClienteSol.setText("");
        txtCiSol.requestFocus();
        DeshabilitarTxt2();
        DeshabilitarBtn2();
    }//GEN-LAST:event_btnClearSolActionPerformed

    private void btnClearPaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPaActionPerformed
        txtCiPa.setText("");
        txtClientePa.setText("");
        chboxMenor.setSelected(false);
        txtCiPa.requestFocus();
        DeshabilitarTxt2();
        DeshabilitarBtn2();
    }//GEN-LAST:event_btnClearPaActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        txtCiPa.setText(txtCiSol.getText());
        txtClientePa.setText(txtClienteSol.getText());
        btnConfirmarCli.requestFocus();
    }//GEN-LAST:event_btnCopyActionPerformed

    private void txtCiSolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCiSolFocusLost
        /*if (txtCiSol.getText().equals("") && !txtClienteSol.getText().equals("")) {
            txtClienteSol.setText("");
        } else if (!txtCiSol.getText().equals("")) {
            try {
                TagSearch = 1;
                RecuperarCliente(txtCiSol.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_txtCiSolFocusLost

    private void txtCiPaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCiPaFocusLost
        /*if (txtCiPa.getText().equals("") && !txtClientePa.getText().equals("")) {
            txtClientePa.setText("");
            chboxMenor.setSelected(false);
        } else if (!txtCiPa.getText().equals("")) {
            try {
                TagSearch = 2;
                RecuperarCliente(txtCiPa.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_txtCiPaFocusLost

    private void btnSearchSolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSolActionPerformed
        try {
            TagSearch = 1;
            CargarTablaClientes();
            lblMensajeCli.setText("Selecciona un Cliente Solicitante");
            SearcherCliente.setTitle("Listado de Clientes");
            SearcherCliente.setModal(true);
            SearcherCliente.pack();
            SearcherCliente.setResizable(false);
            SearcherCliente.setLocationRelativeTo(null);
            SearcherCliente.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchSolActionPerformed

    private void btnSearchPaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPaActionPerformed
        try {
            TagSearch = 2;
            CargarTablaClientes();
            lblMensajeCli.setText("Selecciona un Cliente Paciente");
            SearcherCliente.setTitle("Listado de Clientes");
            SearcherCliente.setModal(true);
            SearcherCliente.pack();
            SearcherCliente.setResizable(false);
            SearcherCliente.setLocationRelativeTo(null);
            SearcherCliente.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchPaActionPerformed

    private void txtArtCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArtCodeKeyPressed
        if (evt.getKeyCode() == 10 && !txtArtCode.getText().equals("")) {
            try {
                TagSearch = 4;
                RecuperarArticulo(txtArtCode.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtArtCode.getText().equals("")) {
            try {
                TagSearch = 4;
                CargarTablaArticulos();
                lblMensajeArt.setText("Selecciona un artículo");
                SearcherArticulo.setTitle("Listado de Artículos");
                SearcherArticulo.setModal(true);
                SearcherArticulo.pack();
                SearcherArticulo.setResizable(false);
                SearcherArticulo.setLocationRelativeTo(null);
                SearcherArticulo.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtArtCodeKeyPressed

    private void btnArtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtSearchActionPerformed
        try {
            TagSearch = 4;
            CargarTablaArticulos();
            lblMensajeArt.setText("Selecciona un artículo");
            SearcherArticulo.setTitle("Listado de Artículos");
            SearcherArticulo.setModal(true);
            SearcherArticulo.pack();
            SearcherArticulo.setResizable(false);
            SearcherArticulo.setLocationRelativeTo(null);
            SearcherArticulo.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnArtSearchActionPerformed

    private void btnArtAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtAddActionPerformed
        int CantidadArt = (Integer) spinArtQuantity.getValue();
        if (CantidadArt != 0) {
            int cantidad = (Integer) spinArtQuantity.getValue();
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese solo valores positivos.");
                spinArtQuantity.setValue(0);
                spinArtQuantity.requestFocus();
            } else {
                String CodArt = txtArtCode.getText();
                if (Check(DetalleArt, 0, CodArt) == true) {
                    Object[] fila = new Object[5];
                    fila[0] = txtArtCode.getText();
                    fila[1] = txtArtDescr.getText();
                    fila[2] = txtArtUnitPrice.getText();
                    fila[3] = spinArtQuantity.getValue();
                    fila[4] = Integer.parseInt(txtArtUnitPrice.getText()) * (Integer) spinArtQuantity.getValue();
                    DetalleArt.addRow(fila);
                    txtArtTotal.setText(SumaTotal(DetalleArt, 4));
                    txtArtCode.setText("");
                    txtArtCode.requestFocus();
                    txtArtDescr.setText("");
                    txtArtUnitPrice.setText("0");
                    spinArtQuantity.setValue(0);
                    txtArtSubtotal.setText("0");
                    lblImage.setIcon(null);
                    SumaGeneral();
                    SumaSubTotal();
                } else {
                    int Option = JOptionPane.showConfirmDialog(null, "Este artículo ya ha sido insertado. Desea sumar a la lista?", "Atención", JOptionPane.YES_NO_OPTION);
                    if (Option == 0) {
                        int fila = 0;
                        for (int a = 0; a < DetalleArt.getRowCount(); a++) {
                            if (DetalleArt.getValueAt(a, 0).toString().equals(txtArtCode.getText())) {
                                fila = a;
                            }
                        }
                        int val_anter = Integer.parseInt(DetalleArt.getValueAt(fila, 3).toString());
                        int val_nuevo = (Integer) spinArtQuantity.getValue();
                        int precio = Integer.parseInt(DetalleArt.getValueAt(fila, 2).toString());
                        DetalleArt.setValueAt(String.valueOf(val_anter + val_nuevo), fila, 3);
                        DetalleArt.setValueAt(String.valueOf((val_anter + val_nuevo) * precio), fila, 4);
                        txtArtTotal.setText(SumaTotal(DetalleArt, 4));
                        txtArtCode.setText("");
                        txtArtDescr.setText("");
                        txtArtUnitPrice.setText("");
                        int CantArt = 0;
                        spinArtQuantity.setValue(Integer.valueOf(CantArt));
                        txtArtSubtotal.setText("");
                        SumaGeneral();
                        SumaSubTotal();
                    }
                }
            }
        } else if (CantidadArt <= 0) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida, ingrese solo números positivos o mayores a 0.");
        }
    }//GEN-LAST:event_btnArtAddActionPerformed

    private void spinArtQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spinArtQuantityFocusLost
        if (!txtArtUnitPrice.equals("")) {
            int Cantidad = (Integer) spinArtQuantity.getValue();
            Integer SubTotal = Integer.parseInt(txtArtUnitPrice.getText()) * Cantidad;
            txtArtSubtotal.setText(SubTotal.toString());
        }
    }//GEN-LAST:event_spinArtQuantityFocusLost

    private void txtArtSubtotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtArtSubtotalFocusGained
        if (!txtArtUnitPrice.equals("")) {
            int Cantidad = (Integer) spinArtQuantity.getValue();
            Integer SubTotal = Integer.parseInt(txtArtUnitPrice.getText()) * Cantidad;
            txtArtSubtotal.setText(SubTotal.toString());
        }
    }//GEN-LAST:event_txtArtSubtotalFocusGained

    private void txtArtSubtotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtArtSubtotalFocusLost
        if (!txtArtUnitPrice.equals("")) {
            int Cantidad = (Integer) spinArtQuantity.getValue();
            Integer SubTotal = Integer.parseInt(txtArtUnitPrice.getText()) * Cantidad;
            txtArtSubtotal.setText(SubTotal.toString());
        }
    }//GEN-LAST:event_txtArtSubtotalFocusLost

    private void txtCristalCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCristalCodeKeyPressed
        if (evt.getKeyCode() == 10 && !txtCristalCode.getText().equals("")) {
            try {
                TagSearch = 3;
                RecuperarArticulo(txtCristalCode.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtCristalCode.getText().equals("")) {
            try {
                TagSearch = 3;
                CargarTablaArticulos();
                lblMensajeArt.setText("Selecciona un cristal");
                SearcherArticulo.setTitle("Cristales - Listado de Artículos");
                SearcherArticulo.setModal(true);
                SearcherArticulo.pack();
                SearcherArticulo.setResizable(false);
                SearcherArticulo.setLocationRelativeTo(null);
                SearcherArticulo.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCristalCodeKeyPressed

    private void btnCristalSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCristalSearchActionPerformed
        try {
            TagSearch = 3;
            CargarTablaArticulos();
            lblMensajeArt.setText("Selecciona un cristal");
            SearcherArticulo.setTitle("Cristales - Listado de Artículos");
            SearcherArticulo.setModal(true);
            SearcherArticulo.pack();
            SearcherArticulo.setResizable(false);
            SearcherArticulo.setLocationRelativeTo(null);
            SearcherArticulo.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCristalSearchActionPerformed

    private void btnArtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtRemoveActionPerformed
        DetalleArt.removeRow(TSearcherDetalle.getSelectedRow());
        txtArtTotal.setText(SumaTotal(DetalleArt, 4));
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_btnArtRemoveActionPerformed

    private void txtSenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenaKeyReleased
        if (txtSena.getText() == "") {
            txtSena.setText("0");
        }
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_txtSenaKeyReleased

    private void txtCristalPriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCristalPriceFocusGained
        SumaCristal();
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_txtCristalPriceFocusGained

    private void txtCristalPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCristalPriceFocusLost
        SumaCristal();
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_txtCristalPriceFocusLost

    private void spinArtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spinArtQuantityKeyPressed
        if (evt.getKeyCode() == 10 && !spinArtQuantity.getValue().equals(0)) {
            btnArtAdd.requestFocus();
        }
    }//GEN-LAST:event_spinArtQuantityKeyPressed

    private void txtOTNroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOTNroKeyPressed
        if (evt.getKeyCode() == 10 && !txtOTNro.getText().equals("")) {
            try {
                RecuperarOT(txtOTNro.getText());
                txtCristalPrice.requestFocus();
                txtOTNro.requestFocus();
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de Anular esta OT?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnOTConfirm.setEnabled(true);
                        btnOTConfirm.doClick();
                    } else {
                        btnOTCancel.setEnabled(true);
                        btnOTCancel.doClick();
                    }
                } else if (Flag == 4) {
                    lblCOTNro.setText(txtOTNro.getText());
                    Integer OtTotal = Integer.parseInt(txtArtTotal.getText()) + Integer.parseInt(txtCristalTotal.getText());
                    String OTSubtotal = txtOTSubtotal.getText();
                    txtCOTTotal.setText(OTSubtotal);
                    txtCOTTotal.setEditable(false);
                    txtCOTSena.setText(txtSena.getText());
                    txtCOTSena.setEditable(false);
                    txtCOTSaldo.setText(txtSaldo.getText());
                    txtCOTSaldo.setEditable(false);
                    WindowCloseOT.setTitle("Cerrar OT");
                    WindowCloseOT.setModal(true);
                    WindowCloseOT.pack();
                    WindowCloseOT.setResizable(false);
                    WindowCloseOT.setLocationRelativeTo(null);
                    WindowCloseOT.setVisible(true);
                    txtCOTTotal.requestFocus();
                    txtCOTSena.requestFocus();
                    txtCOTSaldo.requestFocus();
                    btnOTConfirm.setEnabled(true);
                } else if (Flag == 5) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de reabrir esta OT?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnOTConfirm.setEnabled(true);
                        btnOTConfirm.doClick();
                    } else {
                        btnOTCancel.setEnabled(true);
                        btnOTCancel.doClick();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getKeyCode() == 10 && txtOTNro.getText().equals("")) {
            try {
                CargarTablaOT();
                if (Flag == 2) {
                    lblMensajeOT.setText("Seleccione un Orden de Trabajo que desee modificar");
                } else if (Flag == 3) {
                    lblMensajeOT.setText("Seleccione un Orden de Trabajo que desee anular");
                } else if (Flag == 4) {
                    lblMensajeOT.setText("Seleccione un Orden de Trabajo que desee cerrar");
                } else if (Flag == 5) {
                    lblMensajeOT.setText("Seleccione un Orden de Trabajo que desee cerrar");
                }
                SearcherOT.setTitle("Buscar OT");
                SearcherOT.setModal(true);
                SearcherOT.pack();
                SearcherOT.setResizable(false);
                SearcherOT.setLocationRelativeTo(null);
                SearcherOT.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtOTNroKeyPressed

    private void btnSearchOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchOTActionPerformed
        try {
            CargarTablaOT();
            lblMensajeOT.setText("Selecciona una OT");
            SearcherOT.setTitle("Listado de Órdenes de Trabajo");
            SearcherOT.setModal(true);
            SearcherOT.pack();
            SearcherOT.setResizable(false);
            SearcherOT.setLocationRelativeTo(null);
            SearcherOT.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchOTActionPerformed

    private void btnOTCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTCloseActionPerformed
        Flag = 4;
        DeshabilitarMainBtn();
        txtOTNro.setEnabled(true);
        btnSearchOT.setEnabled(true);
        txtOTNro.setEditable(true);
        txtOTNro.requestFocus();
        btnOTCancel.setEnabled(true);
    }//GEN-LAST:event_btnOTCloseActionPerformed

    private void txtFilterCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterCliKeyReleased
        try {
            String filtro = "";
            if (cboxFiltrarCli.getSelectedItem().equals("CI/RUC")) {
                filtro = "cl.ci_cliente";
            } else if (cboxFiltrarCli.getSelectedItem().equals("Nombres")) {
                filtro = "cl.nombre_cliente";
            } else if (cboxFiltrarCli.getSelectedItem().equals("Apellidos")) {
                filtro = "cl.apellido_cliente";
            } else if (cboxFiltrarCli.getSelectedItem().equals("Teléfono")) {
                filtro = "cl.telefono";
            } else if (cboxFiltrarCli.getSelectedItem().equals("Ciudad")) {
                filtro = "c.ciudad";
            }
            String SQL_Search = "select cl.*, c.ciudad from cliente cl, ciudad c where c.id_ciudad=cl.id_ciudad and " + filtro + " ilike '%" + txtFilterCli.getText() + "%'";
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
    }//GEN-LAST:event_txtFilterCliKeyReleased

    private void btnCOTCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOTCancelActionPerformed
        WindowCloseOT.dispose();
        btnOTCancel.doClick();
    }//GEN-LAST:event_btnCOTCancelActionPerformed

    private void btnCOTConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOTConfirmActionPerformed
        WindowCloseOT.dispose();
        btnOTConfirm.doClick();
    }//GEN-LAST:event_btnCOTConfirmActionPerformed

    private void cboxPayMethodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxPayMethodItemStateChanged
        String valorEfectivo = "Efectivo";
        String PaySelected = (String) cboxPayMethod.getSelectedItem();
        if (PaySelected != null && PaySelected.equals(valorEfectivo)) {
            txtPayMount.requestFocus();
            txtPayCompr.setText("0");
            txtPayCompr.setEditable(false);
        } else {
            txtPayMount.requestFocus();
            txtPayCompr.setText("");
            txtPayCompr.setEditable(true);
        }
    }//GEN-LAST:event_cboxPayMethodItemStateChanged

    private void btnOKUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKUserActionPerformed
        String UserName = txtUser.getText().toString();
        char[] passwordArray = txtPass.getPassword();
        String Password = new String(passwordArray);
        java.util.Arrays.fill(passwordArray, ' '); // Borra la contraseña del array para mayor seguridad
        if (Flag == 2) {
            try {
                UserCheck(UserName, Password);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Flag == 3) {
            try {
                UserCheck(UserName, Password);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Flag == 5) {
            try {
                UserCheck(UserName, Password);
            } catch (SQLException ex) {
                Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado.");
        }
    }//GEN-LAST:event_btnOKUserActionPerformed

    private void btnCancelUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUserActionPerformed
        SUConfirm.setVisible(false);
        txtUser.setText("");
        txtPass.setText("");
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

    private void btnPayAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayAddActionPerformed
        if (!txtPayMount.getText().equals("")) { //Comprueba de que el monto no esté vacio
            if (!txtPayCompr.getText().equals("")) { //Comprueba de que el nro de comprobante no esté vacio
                if (Integer.parseInt(txtPayMount.getText()) > 0) { //Comprueba de que el monto sea mayor a 0
                    if (Integer.parseInt(txtPayCompr.getText()) >= 0) { //Comprueba de que el nro de comprobante sea mayor a 0
                        String PayMethodSelected = (String) cboxPayMethod.getSelectedItem();
                        if (cboxPayMethod.getSelectedItem().equals("Efectivo")) {
                            System.out.println("Pay Method es Efectivo");
                            if (Check(PayMethod, 0, PayMethodSelected) == true) {
                                System.out.println("No había efectivo ya insertado");
                                Object[] fila = new Object[3];
                                fila[0] = cboxPayMethod.getSelectedItem();
                                fila[1] = txtPayMount.getText();
                                fila[2] = txtPayCompr.getText();
                                PayMethod.addRow(fila);
                                cboxPayMethod.setSelectedIndex(0);
                                cboxPayMethod.requestFocus();
                                CalcPayMethod();
                                txtPayMount.setText("");
                                txtPayCompr.setText("");
                            } else {
                                int Option = JOptionPane.showConfirmDialog(null, "Este método de pago ya ha sido insertado. Desea sumar a la lista?", "Atención", JOptionPane.YES_NO_OPTION);
                                if (Option == 0) {
                                    int row = 0;
                                    for (int a = 0; a < PayMethod.getRowCount(); a++) {
                                        if (PayMethod.getValueAt(a, 0).toString().equals(PayMethodSelected)) {
                                            row = a;
                                        }
                                    }
                                    int LastValue = Integer.parseInt(PayMethod.getValueAt(row, 1).toString());
                                    int NewValue = Integer.parseInt(txtPayMount.getText());
                                    PayMethod.setValueAt(String.valueOf(LastValue + NewValue), row, 1);
                                    CalcPayMethod();
                                    cboxPayMethod.setSelectedIndex(0);
                                    txtPayMount.setText("");
                                    txtPayCompr.setText("");
                                }
                            }
                        } else {
                            System.out.println("Pay Method no es Efectivo");
                            Object[] fila = new Object[3];
                            fila[0] = cboxPayMethod.getSelectedItem();
                            fila[1] = txtPayMount.getText();
                            fila[2] = txtPayCompr.getText();
                            PayMethod.addRow(fila);
                            cboxPayMethod.setSelectedIndex(0);
                            cboxPayMethod.requestFocus();
                            CalcPayMethod();
                            txtPayMount.setText("");
                            txtPayCompr.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nro. Comprobante no puede contener valores negativos. Vuelve a intentarlo.");
                        txtPayCompr.setText("");
                        txtPayCompr.requestFocus();
                    }
                } else if (Integer.parseInt(txtPayMount.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Monto inválido, ingrese solo valores positivos y mayores a 0.");
                    txtPayMount.setText("");
                    txtPayMount.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el número de comprobante antes de continuar.");
                txtPayCompr.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un monto antes de continuar.");
            txtPayMount.requestFocus();
        }
    }//GEN-LAST:event_btnPayAddActionPerformed

    private void txtPayMountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayMountKeyPressed
        if (evt.getKeyCode() == 10 && !txtPayMount.getText().equals("")) {
            if (!txtPayCompr.isEditable()) {
                btnPayAdd.requestFocus();
            } else {
                txtPayCompr.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPayMountKeyPressed

    private void txtPayComprKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayComprKeyPressed
        if (evt.getKeyCode() == 10 && !txtPayCompr.getText().equals("")) {
            btnPayAdd.requestFocus();
        }
    }//GEN-LAST:event_txtPayComprKeyPressed

    private void spinArtQuantityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinArtQuantityStateChanged
        if (!txtArtUnitPrice.equals("")) {
            int Cantidad = (Integer) spinArtQuantity.getValue();
            Integer SubTotal = Integer.parseInt(txtArtUnitPrice.getText()) * Cantidad;
            txtArtSubtotal.setText(SubTotal.toString());
        } else if (spinArtQuantity.equals(0)) {
            txtArtSubtotal.setText("0");
        }
    }//GEN-LAST:event_spinArtQuantityStateChanged

    private void btnArtCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtCleanActionPerformed
        spinArtQuantity.setValue(0);
        txtArtCode.setText("");
        txtArtDescr.setText("");
        txtArtUnitPrice.setText("");
        txtArtSubtotal.setText("");
        lblImage.setIcon(null);
    }//GEN-LAST:event_btnArtCleanActionPerformed

    private void btnPayDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayDeleteActionPerformed
        PayMethod.removeRow(TSeacherPay.getSelectedRow());
        txtSena.setText(SumaTotal(PayMethod, 1));
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_btnPayDeleteActionPerformed

    private void spinODCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinODCantidadStateChanged
        if (!txtCristalPrice.getText().isEmpty()) {
            SumaCristal();
            SumaGeneral();
            SumaSubTotal();
        }
    }//GEN-LAST:event_spinODCantidadStateChanged

    private void spinOICantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinOICantidadStateChanged
        if (!txtCristalPrice.getText().isEmpty()) {
            SumaCristal();
            SumaGeneral();
            SumaSubTotal();
        }
    }//GEN-LAST:event_spinOICantidadStateChanged

    private void btnCristCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCristCleanActionPerformed
        txtCristalCode.setText("");
        txtCristalDescr.setText("");
        spinODCantidad.setValue(0);
        spinOICantidad.setValue(0);
        txtCristalPrice.setText("");
        txtCristalTotal.setText("0");
    }//GEN-LAST:event_btnCristCleanActionPerformed

    private void btnPayDeleteCOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayDeleteCOTActionPerformed
        PayMethod.removeRow(TSeacherPayCOT.getSelectedRow());
        txtSena.setText(SumaTotal(PayMethod, 1));
        SumaGeneral();
        SumaSubTotal();
    }//GEN-LAST:event_btnPayDeleteCOTActionPerformed

    private void cboxPayMethodCOTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxPayMethodCOTItemStateChanged
        String valorEfectivo = "Efectivo";
        String PaySelected = (String) cboxPayMethodCOT.getSelectedItem();
        if (PaySelected != null && PaySelected.equals(valorEfectivo)) {
            txtPayMountCOT.requestFocus();
            txtPayComprCOT.setText("0");
            txtPayComprCOT.setEditable(false);
        } else {
            txtPayMountCOT.requestFocus();
            txtPayComprCOT.setText("");
            txtPayComprCOT.setEditable(true);
        }
    }//GEN-LAST:event_cboxPayMethodCOTItemStateChanged

    private void txtPayMountCOTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayMountCOTKeyPressed
        if (evt.getKeyCode() == 10 && !txtPayMountCOT.getText().equals("")) {
            if (!txtPayComprCOT.isEditable()) {
                btnPayAddCOT.requestFocus();
            } else {
                txtPayComprCOT.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPayMountCOTKeyPressed

    private void txtPayComprCOTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayComprCOTKeyPressed
        if (evt.getKeyCode() == 10 && !txtPayComprCOT.getText().equals("")) {
            btnPayAddCOT.requestFocus();
        }
    }//GEN-LAST:event_txtPayComprCOTKeyPressed

    private void btnPayAddCOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayAddCOTActionPerformed
        if (!txtPayMountCOT.getText().equals("")) {
            if (!txtPayComprCOT.getText().equals("")) {
                if (Integer.parseInt(txtPayMountCOT.getText()) > 0) {
                    if (Integer.parseInt(txtPayComprCOT.getText()) >= 0) {
                        String PayMethodSelected = (String) cboxPayMethodCOT.getSelectedItem();
                        if (Check(PayMethod, 0, PayMethodSelected) == true) {
                            Object[] fila = new Object[3];
                            fila[0] = cboxPayMethodCOT.getSelectedItem();
                            fila[1] = txtPayMountCOT.getText();
                            fila[2] = txtPayComprCOT.getText();
                            PayMethod.addRow(fila);
                            cboxPayMethodCOT.setSelectedIndex(0);
                            cboxPayMethodCOT.requestFocus();
                            CalcPayMethod();
                            txtPayMountCOT.setText("");
                            txtPayComprCOT.setText("");
                        } else {
                            int Option = JOptionPane.showConfirmDialog(null, "Este método de pago ya ha sido insertado. Desea sumar a la lista?", "Atención", JOptionPane.YES_NO_OPTION);
                            if (Option == 0) {
                                int row = 0;
                                for (int a = 0; a < PayMethod.getRowCount(); a++) {
                                    if (PayMethod.getValueAt(a, 0).toString().equals(PayMethodSelected)) {
                                        row = a;
                                    }
                                }
                                int LastValue = Integer.parseInt(PayMethod.getValueAt(row, 1).toString());
                                int NewValue = Integer.parseInt(txtPayMountCOT.getText());
                                PayMethod.setValueAt(String.valueOf(LastValue + NewValue), row, 1);
                                CalcPayMethod();
                                cboxPayMethodCOT.setSelectedIndex(0);
                                txtPayMountCOT.setText("");
                                txtPayComprCOT.setText("");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nro. Comprobante no puede contener valores negativos. Vuelve a intentarlo.");
                        txtPayComprCOT.setText("");
                        txtPayComprCOT.requestFocus();
                    }
                } else if (Integer.parseInt(txtPayMountCOT.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Monto inválido, ingrese solo valores positivos y mayores a 0.");
                    txtPayMountCOT.setText("");
                    txtPayMountCOT.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el número de comprobante antes de continuar.");
                txtPayComprCOT.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un monto antes de continuar.");
            txtPayMountCOT.requestFocus();
        }
    }//GEN-LAST:event_btnPayAddCOTActionPerformed

    private void btnOTREOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTREOpenActionPerformed
        Flag = 5;
        lblSUSubtitle.setText("Ingrese un usuario administrativo para continuar con la operación.");
        SUConfirm.setTitle("Atención: Acción requerida - Reapertura de OT");
        SUConfirm.setModal(true);
        SUConfirm.pack();
        SUConfirm.setResizable(false);
        SUConfirm.setLocationRelativeTo(null);
        SUConfirm.setVisible(true);
    }//GEN-LAST:event_btnOTREOpenActionPerformed

    private void btnOTConsultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOTConsultActionPerformed
        try {
            LoadOTConsult();
            ConsultOTDialog.setTitle("Consulta de OT");
            ConsultOTDialog.setModal(true);
            ConsultOTDialog.pack();
            ConsultOTDialog.setResizable(false);
            ConsultOTDialog.setLocationRelativeTo(null);
            ConsultOTDialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOTConsultActionPerformed

    private void tConsultOTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tConsultOTMouseClicked
        try {
            int row = tConsultOT.getSelectedRow();
            String OT_ID = tConsultOT.getModel().getValueAt(row, 0).toString();
            LoadConsultOT(OT_ID);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tConsultOTMouseClicked

    private void tConsultOTDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tConsultOTDetailMouseClicked
        try {
            int row = tConsultOTDetail.getSelectedRow();
            String ArtID = tConsultOTDetail.getModel().getValueAt(row, 0).toString();
            LoadConsultOTArtImage(ArtID);
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tConsultOTDetailMouseClicked

    private void btnGGCleanOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGGCleanOTActionPerformed
        CleanConsultOT();
    }//GEN-LAST:event_btnGGCleanOTActionPerformed

    private void txtGGFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGGFilterKeyReleased
        try {
            OTConsultFilter();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtGGFilterKeyReleased

    private void chkGGOpenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkGGOpenItemStateChanged
        try {
            if (chkGGOpen.isSelected() == false && chkGGNull.isSelected() == false && chkGGClose.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
                chkGGOpen.setSelected(true);
            }
            if (txtGGOTStatus.getText().equals("Abierto")) {
                CleanConsultOT();
            }
            OTConsultFilter();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_chkGGOpenItemStateChanged

    private void chkGGNullItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkGGNullItemStateChanged
        try {
            if (chkGGNull.isSelected() == false && chkGGOpen.isSelected() == false && chkGGClose.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
                chkGGNull.setSelected(true);
            }
            if (txtGGOTStatus.getText().equals("Anulado")) {
                CleanConsultOT();
            }
            OTConsultFilter();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_chkGGNullItemStateChanged

    private void chkGGCloseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkGGCloseItemStateChanged
        try {
            if (chkGGClose.isSelected() == false && chkGGOpen.isSelected() == false && chkGGNull.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Acción no permitida. Debe marcar al menos una opción.");
                chkGGClose.setSelected(true);
            }
            if (txtGGOTStatus.getText().equals("Cerrado")) {
                CleanConsultOT();
            }
            OTConsultFilter();
        } catch (SQLException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_chkGGCloseItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ConsultOTDialog;
    private javax.swing.JPanel PnlArticulo;
    private javax.swing.JPanel PnlBtnsOT;
    private javax.swing.JPanel PnlCliente;
    private javax.swing.JPanel PnlCristal;
    private javax.swing.JPanel PnlOTStats;
    private javax.swing.JPanel PnlPayMethod;
    private javax.swing.JDialog SUConfirm;
    private javax.swing.JDialog SearcherArticulo;
    private javax.swing.JDialog SearcherCliente;
    private javax.swing.JDialog SearcherOT;
    private javax.swing.JTable TSeacherPay;
    private javax.swing.JTable TSeacherPayCOT;
    private javax.swing.JTable TSearcherArt;
    private javax.swing.JTable TSearcherCli;
    private javax.swing.JTable TSearcherDetalle;
    private javax.swing.JTable TSearcherOT;
    private javax.swing.JPanel TabArticles;
    private javax.swing.JPanel TabCrystal;
    private javax.swing.JTabbedPane TabPanel;
    private javax.swing.JPanel TabPayMethod;
    private javax.swing.JScrollPane TableDetailArticle;
    private javax.swing.JScrollPane TablePayMethod;
    private javax.swing.JScrollPane TablePayMethod1;
    private javax.swing.JDialog WindowCloseOT;
    private javax.swing.JButton btnArtAdd;
    private javax.swing.JButton btnArtClean;
    private javax.swing.JButton btnArtRemove;
    private javax.swing.JButton btnArtSearch;
    private javax.swing.JButton btnCOTCancel;
    private javax.swing.JButton btnCOTConfirm;
    private javax.swing.JButton btnCancelUser;
    private javax.swing.JButton btnCerrarArt;
    private javax.swing.JButton btnCerrarCli;
    private javax.swing.JButton btnCerrarOT;
    private javax.swing.JButton btnCleanFilterCli;
    private javax.swing.JButton btnClearPa;
    private javax.swing.JButton btnClearSol;
    private javax.swing.JButton btnConfirmarCli;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnCristClean;
    private javax.swing.JButton btnCristalSearch;
    private javax.swing.JButton btnGGCleanOT;
    private javax.swing.JButton btnGGEraseFilter;
    private javax.swing.JButton btnOKUser;
    private javax.swing.JButton btnOTCancel;
    private javax.swing.JButton btnOTClose;
    private javax.swing.JButton btnOTConfirm;
    private javax.swing.JButton btnOTConsult;
    private javax.swing.JButton btnOTEdit;
    private javax.swing.JButton btnOTNew;
    private javax.swing.JButton btnOTNull;
    private javax.swing.JButton btnOTREOpen;
    private javax.swing.JButton btnPayAdd;
    private javax.swing.JButton btnPayAddCOT;
    private javax.swing.JButton btnPayDelete;
    private javax.swing.JButton btnPayDeleteCOT;
    private javax.swing.JButton btnSearchOT;
    private javax.swing.JButton btnSearchPa;
    private javax.swing.JButton btnSearchSol;
    private javax.swing.JComboBox<String> cboxFiltrarCli;
    private javax.swing.JComboBox<String> cboxGGFilter;
    private javax.swing.JComboBox<String> cboxPayMethod;
    private javax.swing.JComboBox<String> cboxPayMethodCOT;
    private javax.swing.JCheckBox chboxMenor;
    private javax.swing.JCheckBox chkGGClose;
    private javax.swing.JCheckBox chkGGNull;
    private javax.swing.JCheckBox chkGGOpen;
    private com.toedter.calendar.JDateChooser dtOTDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblAdicion;
    private javax.swing.JLabel lblAlturaFocal;
    private javax.swing.JLabel lblArtCode;
    private javax.swing.JLabel lblArtDescr;
    private javax.swing.JLabel lblArtQuantity;
    private javax.swing.JLabel lblArtSubtotal;
    private javax.swing.JLabel lblArtTitle;
    private javax.swing.JLabel lblArtTotal;
    private javax.swing.JLabel lblArtUnitPrice;
    private javax.swing.JLabel lblCOTNro;
    private javax.swing.JLabel lblCOTSaldo;
    private javax.swing.JLabel lblCOTSena;
    private javax.swing.JLabel lblCOTTitle;
    private javax.swing.JLabel lblCOTTotal;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCiPa;
    private javax.swing.JLabel lblCiSol;
    private javax.swing.JLabel lblCilindrico;
    private javax.swing.JLabel lblCliPa;
    private javax.swing.JLabel lblCliSol;
    private javax.swing.JLabel lblCristalTotal;
    private javax.swing.JLabel lblDI;
    private javax.swing.JLabel lblDND;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEje;
    private javax.swing.JLabel lblEsferico;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFiltrarCli;
    private javax.swing.JLabel lblGGImage;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMenor;
    private javax.swing.JLabel lblMensajeArt;
    private javax.swing.JLabel lblMensajeCli;
    private javax.swing.JLabel lblMensajeOT;
    private javax.swing.JLabel lblOD;
    private javax.swing.JLabel lblODEje;
    private javax.swing.JLabel lblOI;
    private javax.swing.JLabel lblOIEje;
    private javax.swing.JLabel lblOTSubtotal;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblOt;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPayCompr;
    private javax.swing.JLabel lblPayComprCOT;
    private javax.swing.JLabel lblPayMount;
    private javax.swing.JLabel lblPayMountCOT;
    private javax.swing.JLabel lblPrecioCristal;
    private javax.swing.JLabel lblSUSubtitle;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSena;
    private javax.swing.JLabel lblSolicitante;
    private javax.swing.JLabel lblTipoCristal;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblVistaLejana;
    private javax.swing.JSpinner spinArtQuantity;
    private javax.swing.JSpinner spinODCantidad;
    private javax.swing.JSpinner spinOICantidad;
    private javax.swing.JTable tConsultOT;
    private javax.swing.JTable tConsultOTDetail;
    private javax.swing.JTable tConsultOTPay;
    private javax.swing.JTextField txtAlturaFocal;
    private javax.swing.JTextField txtArtCode;
    private javax.swing.JTextField txtArtDescr;
    private javax.swing.JTextField txtArtSubtotal;
    private javax.swing.JTextField txtArtTotal;
    private javax.swing.JTextField txtArtUnitPrice;
    private javax.swing.JTextField txtCOTSaldo;
    private javax.swing.JTextField txtCOTSena;
    private javax.swing.JTextField txtCOTTotal;
    private javax.swing.JTextField txtCiPa;
    private javax.swing.JTextField txtCiSol;
    private javax.swing.JTextField txtClientePa;
    private javax.swing.JTextField txtClienteSol;
    private javax.swing.JTextField txtCristalCode;
    private javax.swing.JTextField txtCristalDescr;
    private javax.swing.JTextField txtCristalPrice;
    private javax.swing.JTextField txtCristalTotal;
    private javax.swing.JTextField txtDI;
    private javax.swing.JTextField txtDND;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtFilterCli;
    private javax.swing.JTextField txtGGAltFoc;
    private javax.swing.JTextField txtGGCrystalDescr;
    private javax.swing.JTextField txtGGCrystalTotal;
    private javax.swing.JTextField txtGGCrystalType;
    private javax.swing.JTextField txtGGDI;
    private javax.swing.JTextField txtGGDND;
    private javax.swing.JTextField txtGGDNI;
    private javax.swing.JTextField txtGGFilter;
    private javax.swing.JTextField txtGGODAddition;
    private javax.swing.JTextField txtGGODAxis;
    private javax.swing.JTextField txtGGODCylindrical;
    private javax.swing.JTextField txtGGODQuantity;
    private javax.swing.JTextField txtGGODSpherical;
    private javax.swing.JTextField txtGGOIAddition;
    private javax.swing.JTextField txtGGOIAxis;
    private javax.swing.JTextField txtGGOICylindrical;
    private javax.swing.JTextField txtGGOIQuantity;
    private javax.swing.JTextField txtGGOISpherical;
    private javax.swing.JTextField txtGGOTBalance;
    private com.toedter.calendar.JDateChooser txtGGOTDate;
    private javax.swing.JTextField txtGGOTNro;
    private javax.swing.JTextField txtGGOTPay;
    private javax.swing.JTextField txtGGOTStatus;
    private javax.swing.JTextField txtGGOTTotal;
    private javax.swing.JTextField txtGGObs;
    private javax.swing.JTextField txtGGPatientDoc;
    private javax.swing.JTextField txtGGPatientName;
    private javax.swing.JTextField txtGGTotalArticle;
    private javax.swing.JTextField txtGGUnitPrice;
    private javax.swing.JTextField txtGGUsername;
    private javax.swing.JTextField txtODAdicion;
    private javax.swing.JTextField txtODCilindrico;
    private javax.swing.JTextField txtODEje;
    private javax.swing.JTextField txtODEsferico;
    private javax.swing.JTextField txtOIAdicion;
    private javax.swing.JTextField txtOICilindrico;
    private javax.swing.JTextField txtOIEje;
    private javax.swing.JTextField txtOIEsferico;
    private javax.swing.JTextField txtOTNro;
    private javax.swing.JTextField txtOTState;
    private javax.swing.JTextField txtOTSubtotal;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPayCompr;
    private javax.swing.JTextField txtPayComprCOT;
    private javax.swing.JLabel txtPayMethod;
    private javax.swing.JLabel txtPayMethodCOT;
    private javax.swing.JTextField txtPayMount;
    private javax.swing.JTextField txtPayMountCOT;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSena;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
