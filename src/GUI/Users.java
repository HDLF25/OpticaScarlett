package GUI;

import Otros.Conexion;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Users extends javax.swing.JPanel {

    Conexion con;
    ResultSet rs;
    int Flag = 0;
    int PassAnswer = 10;
    DefaultTableModel Search = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Users(int idUsuario, List<Menu.Permission> permissions) throws SQLException {
        initComponents();
        con = new Conexion();
        con.Login();
        EnableBtns();
        DisableActionBtns();
        DisableAllValues();
        CabeceraTabla();
        CheckUserPermissions(permissions);
        CheckUserLogged_Admin(idUsuario);
    }

    private void EnableBtns() {
        btnCreate.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnDisable.setEnabled(true);
        btnEnable.setEnabled(true);
        btnChangePass.setEnabled(true);
    }

    private void DisableBtns() {
        btnCreate.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisable.setEnabled(false);
        btnEnable.setEnabled(false);
        btnChangePass.setEnabled(false);
    }

    private void CleanAllTxts() {
        txtIDUser.setText("");
        txtUsername.setText("");
        txtFullname.setText("");
        txtCIUser.setText("");
        txtPassCurr.setText("");
        txtPassNew.setText("");
        txtPassRepeat.setText("");
        lblPassCurr.setText("Contraseña Actual");
        lblPassNew.setText("Nueva Contraseña");
        dlgUser.setText("");
        dlgPass.setText("");
    }

    private void EnableActionBtns() {
        btnConfirm.setEnabled(true);
        btnCancel.setEnabled(true);
    }

    private void DisableActionBtns() {
        btnConfirm.setEnabled(false);
        btnCancel.setEnabled(false);
    }

    private void RestartCheckBoxes() {
        chbFichaMain.setSelected(false);
        chbCliMain.setSelected(false);
        chbArtMain.setSelected(false);
        chbStockMain.setSelected(false);
        chbRepFicha.setSelected(false);
        chbResSales.setSelected(false);
        chbRepStock.setSelected(false);
        chbCityMain.setSelected(false);
        chbBrandMain.setSelected(false);
        chbMethodMain.setSelected(false);
        chbUserMain.setSelected(false);
    }

    private void EnableValuesCreate() {
        txtIDUser.setEnabled(true);
        txtIDUser.setEditable(false);
        txtUsername.setEnabled(true);
        txtFullname.setEnabled(true);
        txtCIUser.setEnabled(true);
        txtPassNew.setEnabled(true);
        txtPassRepeat.setEnabled(true);
        chbAdmin.setEnabled(true);
        chbFichaMain.setEnabled(true);
        chbCliMain.setEnabled(true);
        chbArtMain.setEnabled(true);
        chbStockMain.setEnabled(true);
        chbRepFicha.setEnabled(true);
        chbResSales.setEnabled(true);
        chbRepStock.setEnabled(true);
        chbCityMain.setEnabled(true);
        chbBrandMain.setEnabled(true);
        chbMethodMain.setEnabled(true);
        chbUserMain.setEnabled(true);
    }

    private void EnableValuesEditDelete() {
        txtIDUser.setEnabled(true);
        txtIDUser.setEditable(true);
    }

    private void EnableValuesEdit_UserLoaded() {
        txtFullname.setEnabled(true);
        txtCIUser.setEnabled(true);
        chbAdmin.setEnabled(true);
        chbFichaMain.setEnabled(true);
        chbCliMain.setEnabled(true);
        chbArtMain.setEnabled(true);
        chbStockMain.setEnabled(true);
        chbRepFicha.setEnabled(true);
        chbResSales.setEnabled(true);
        chbRepStock.setEnabled(true);
        chbCityMain.setEnabled(true);
        chbBrandMain.setEnabled(true);
        chbMethodMain.setEnabled(true);
        chbUserMain.setEnabled(true);
    }

    private void EnableAllValues() {
        txtIDUser.setEnabled(true);
        txtUsername.setEnabled(true);
        txtFullname.setEnabled(true);
        txtCIUser.setEnabled(true);
        txtPassCurr.setEnabled(true);
        txtPassNew.setEnabled(true);
        txtPassRepeat.setEnabled(true);
        chbAdmin.setEnabled(true);
        chbFichaMain.setEnabled(true);
        chbFicha1.setEnabled(true);
        chbFicha2.setEnabled(true);
        chbFicha3.setEnabled(true);
        chbFicha4.setEnabled(true);
        chbCliMain.setEnabled(true);
        chbCli1.setEnabled(true);
        chbCli2.setEnabled(true);
        chbCli3.setEnabled(true);
        chbCli4.setEnabled(true);
        chbArtMain.setEnabled(true);
        chbArt1.setEnabled(true);
        chbArt2.setEnabled(true);
        chbArt3.setEnabled(true);
        chbArt4.setEnabled(true);
        chbStockMain.setEnabled(true);
        chbStock1.setEnabled(true);
        chbStock2.setEnabled(true);
        chbStock3.setEnabled(true);
        chbRepFicha.setEnabled(true);
        chbResSales.setEnabled(true);
        chbRepStock.setEnabled(true);
        chbCityMain.setEnabled(true);
        chbCity1.setEnabled(true);
        chbCity2.setEnabled(true);
        chbCity3.setEnabled(true);
        chbCity4.setEnabled(true);
        chbBrandMain.setEnabled(true);
        chbBrand1.setEnabled(true);
        chbBrand2.setEnabled(true);
        chbBrand3.setEnabled(true);
        chbBrand4.setEnabled(true);
        chbMethodMain.setEnabled(true);
        chbMethod1.setEnabled(true);
        chbMethod2.setEnabled(true);
        chbMethod3.setEnabled(true);
        chbMethod4.setEnabled(true);
        chbUserMain.setEnabled(true);
        chbUser1.setEnabled(true);
        chbUser2.setEnabled(true);
        chbUser3.setEnabled(true);
        chbUser4.setEnabled(true);
        chbUser5.setEnabled(true);
    }

    private void DisableAllValuesPass() {
        txtIDUser.setEnabled(true);
        txtIDUser.setEditable(false);
        txtUsername.setEnabled(true);
        txtUsername.setEditable(false);
        txtFullname.setEnabled(true);
        txtFullname.setEditable(false);
        txtCIUser.setEnabled(true);
        txtCIUser.setEditable(false);
        chbAdmin.setEnabled(false);
        chbFichaMain.setEnabled(false);
        chbFicha1.setEnabled(false);
        chbFicha2.setEnabled(false);
        chbFicha3.setEnabled(false);
        chbFicha4.setEnabled(false);
        chbCliMain.setEnabled(false);
        chbCli1.setEnabled(false);
        chbCli2.setEnabled(false);
        chbCli3.setEnabled(false);
        chbCli4.setEnabled(false);
        chbArtMain.setEnabled(false);
        chbArt1.setEnabled(false);
        chbArt2.setEnabled(false);
        chbArt3.setEnabled(false);
        chbArt4.setEnabled(false);
        chbStockMain.setEnabled(false);
        chbStock1.setEnabled(false);
        chbStock2.setEnabled(false);
        chbStock3.setEnabled(false);
        chbRepFicha.setEnabled(false);
        chbResSales.setEnabled(false);
        chbRepStock.setEnabled(false);
        chbCityMain.setEnabled(false);
        chbCity1.setEnabled(false);
        chbCity2.setEnabled(false);
        chbCity3.setEnabled(false);
        chbCity4.setEnabled(false);
        chbBrandMain.setEnabled(false);
        chbBrand1.setEnabled(false);
        chbBrand2.setEnabled(false);
        chbBrand3.setEnabled(false);
        chbBrand4.setEnabled(false);
        chbMethodMain.setEnabled(false);
        chbMethod1.setEnabled(false);
        chbMethod2.setEnabled(false);
        chbMethod3.setEnabled(false);
        chbMethod4.setEnabled(false);
        chbUserMain.setEnabled(false);
        chbUser1.setEnabled(false);
        chbUser2.setEnabled(false);
        chbUser3.setEnabled(false);
        chbUser4.setEnabled(false);
        chbUser5.setEnabled(false);
    }

    private void DisableAllValues() {
        txtIDUser.setEnabled(false);
        txtUsername.setEnabled(false);
        txtFullname.setEnabled(false);
        txtCIUser.setEnabled(false);
        txtPassCurr.setEnabled(false);
        txtPassNew.setEnabled(false);
        txtPassRepeat.setEnabled(false);
        chbAdmin.setEnabled(false);
        chbAdmin.setSelected(false);
        chbFichaMain.setEnabled(false);
        chbFichaMain.setSelected(false);
        chbFicha1.setEnabled(false);
        chbFicha2.setEnabled(false);
        chbFicha3.setEnabled(false);
        chbFicha4.setEnabled(false);
        chbCliMain.setEnabled(false);
        chbCliMain.setSelected(false);
        chbCli1.setEnabled(false);
        chbCli2.setEnabled(false);
        chbCli3.setEnabled(false);
        chbCli4.setEnabled(false);
        chbArtMain.setEnabled(false);
        chbArtMain.setSelected(false);
        chbArt1.setEnabled(false);
        chbArt2.setEnabled(false);
        chbArt3.setEnabled(false);
        chbArt4.setEnabled(false);
        chbStockMain.setEnabled(false);
        chbStockMain.setSelected(false);
        chbStock1.setEnabled(false);
        chbStock2.setEnabled(false);
        chbStock3.setEnabled(false);
        chbRepFicha.setEnabled(false);
        chbResSales.setEnabled(false);
        chbRepStock.setEnabled(false);
        chbRepFicha.setSelected(false);
        chbResSales.setSelected(false);
        chbRepStock.setSelected(false);
        chbCityMain.setEnabled(false);
        chbCityMain.setSelected(false);
        chbCity1.setEnabled(false);
        chbCity2.setEnabled(false);
        chbCity3.setEnabled(false);
        chbCity4.setEnabled(false);
        chbBrandMain.setEnabled(false);
        chbBrandMain.setSelected(false);
        chbBrand1.setEnabled(false);
        chbBrand2.setEnabled(false);
        chbBrand3.setEnabled(false);
        chbBrand4.setEnabled(false);
        chbMethodMain.setEnabled(false);
        chbMethodMain.setSelected(false);
        chbMethod1.setEnabled(false);
        chbMethod2.setEnabled(false);
        chbMethod3.setEnabled(false);
        chbMethod4.setEnabled(false);
        chbUserMain.setEnabled(false);
        chbUserMain.setSelected(false);
        chbUser1.setEnabled(false);
        chbUser2.setEnabled(false);
        chbUser3.setEnabled(false);
        chbUser4.setEnabled(false);
        chbUser5.setEnabled(false);
    }

    private void CheckUserPermissions(List<Menu.Permission> permissions) throws SQLException {
        DisableBtns();
        for (Menu.Permission permission : permissions) {
            if (permission.isActivePerm()) {
                switch (permission.getNamePerm()) {
                    case "UserCreate":
                        btnCreate.setEnabled(true);
                        break;
                    case "UserEdit":
                        btnEdit.setEnabled(true);
                        break;
                    case "UserDelete":
                        btnDelete.setEnabled(true);
                        break;
                    case "UserOnOff":
                        btnDisable.setEnabled(true);
                        btnEnable.setEnabled(true);
                        break;
                    case "UserChangePass":
                        btnChangePass.setEnabled(true);
                        break;
                    default:
                        System.out.println("Permiso no utilizado o no concedido: " + permission.getPermID() + ", " + permission.getNamePerm());
                }
            }
        }
    }

    private void NewUser() throws SQLException {
        String UserFind = "Select * from usuario";
        rs = con.Results(UserFind);
        if (rs.next()) {
            String UserID = "Select last_value+1 as iduser from usuario_id_usuario_seq";
            rs = con.Results(UserID);
            if (rs.next()) {
                txtIDUser.setText(rs.getString("iduser"));
            }
        } else {
            String UserID = "Select last_value as iduser from usuario_id_usuario_seq";
            rs = con.Results(UserID);
            if (rs.next()) {
                txtIDUser.setText(rs.getString("iduser"));
            }
        }
    }

    /*private void CheckValidData() {
        if (Flag == 1) {
            if (dlgUser.equals("Este usuario ya existe. Por favor, ingrese otro nombre de usuario.")) {
                JOptionPane.showMessageDialog(null, "No puede crear un usuario ya existente, por favor ingrese otro usuario.");
                txtUsername.requestFocus();
                txtUsername.selectAll();
            } else {
                if (!txtPassNew.equals(txtPassConfirm.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden, intentelo de nuevo.");
                    txtPassNew.setText("");
                    txtPassConfirm.setText("");
                    txtPassNew.requestFocus();
                } else {
                    ProcessData();
                }
            }
        }
    }*/
    private boolean CheckUserLoaded(String username) throws SQLException {
        if (Flag == 1) {
            String SQLUser = "Select * from usuario where username = '" + username + "'";
            rs = con.Results(SQLUser);
            if (rs.next()) {
                dlgUser.setText("Este usuario ya existe. Por favor, ingrese otro nombre de usuario.");
            } else {
                return true;
            }
        } else if (Flag == 2) {
            return true;
        } else if (Flag == 3) {
            return true;
        } else if (Flag == 4) {
            return true;
        } else if (Flag == 5) {
            return true;
        } else if (Flag == 6) {
            return true;
        }
        return false;
    }

    private boolean CheckUserStatus(int idUser) throws SQLException {
        String SQL_UserStatus = "Select * from usuario where id_usuario = " + idUser;
        if (Flag == 4) {
            rs = con.Results(SQL_UserStatus);
            if (rs.next()) {
                int UserStatus = rs.getInt("id_estado");
                if (UserStatus == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (Flag == 5) {
            rs = con.Results(SQL_UserStatus);
            if (rs.next()) {
                int UserStatus = rs.getInt("id_estado");
                if (UserStatus == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean CheckUserLogged_Admin(int idUser) throws SQLException {
        String SQL_UserStatus = "Select * from usuario where id_usuario = " + idUser;
        if (Flag == 6) {
            rs = con.Results(SQL_UserStatus);
            if (rs.next()) {
                if (rs.getBoolean("admin")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (Flag == 0) {
            rs = con.Results(SQL_UserStatus);
            if (rs.next()) {
                if (rs.getBoolean("admin")) {
                    btnListUser.setEnabled(true);
                } else {
                    btnListUser.setEnabled(false);
                }
            }
        }
        return false;
    }

    private boolean CheckPassEquals() {
        char[] PassNew = txtPassNew.getPassword();
        char[] PassConf = txtPassRepeat.getPassword();
        if (Flag == 1) {
            if (Arrays.equals(PassNew, PassConf)) {
                Arrays.fill(PassNew, '0');
                Arrays.fill(PassConf, '0');
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden, intentelo de nuevo.");
                txtPassNew.setText("");
                txtPassRepeat.setText("");
                txtPassNew.requestFocus();
                Arrays.fill(PassNew, '0');
                Arrays.fill(PassConf, '0');
                return false;
            }
        } else if (Flag == 2) {
            return true;
        } else if (Flag == 3) {
            return true;
        } else if (Flag == 4) {
            return true;
        } else if (Flag == 5) {
            return true;
        } else if (Flag == 6) {
            if (Arrays.equals(PassNew, PassConf)) {
                Arrays.fill(PassNew, '0');
                Arrays.fill(PassConf, '0');
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden, intentelo de nuevo.");
                txtPassNew.setText("");
                txtPassRepeat.setText("");
                txtPassNew.requestFocus();
                Arrays.fill(PassNew, '0');
                Arrays.fill(PassConf, '0');
                return false;
            }
        }
        return false;
    }

    private boolean CheckPassSavedEquals(int idUser) throws SQLException {
        char[] PassCurr = txtPassCurr.getPassword();
        String PassCu = new String(PassCurr);
        if (Flag == 6) {
            String SQL_PassCheck = "Select * from usuario where id_usuario = " + idUser + " and password = md5('" + PassCu + "')";
            rs = con.Results(SQL_PassCheck);
            if (rs.next()) {
                dlgPass.setText("");
                return true;
            } else {
                dlgPass.setText("La contraseña actual ingresada es incorrecta.");
                return false;
            }
        }
        return false;
    }

    private void ProcessData() {
        String IDUser = txtIDUser.getText();
        String Username = txtUsername.getText();
        String Fullname = txtFullname.getText();
        String CIUser = txtCIUser.getText();
        char[] PassCurrent = txtPassCurr.getPassword();
        String PassCu = new String(PassCurrent);
        java.util.Arrays.fill(PassCurrent, ' ');
        char[] PassNew = txtPassNew.getPassword();
        String PassNe = new String(PassNew);
        java.util.Arrays.fill(PassNew, ' ');
        char[] PassConfirm = txtPassRepeat.getPassword();
        String PassCo = new String(PassConfirm);
        java.util.Arrays.fill(PassConfirm, ' ');
        Boolean Admin = chbAdmin.isSelected();

        Boolean FichaMain = chbFichaMain.isSelected();
        Boolean Ficha1 = chbFicha1.isSelected();
        Boolean Ficha2 = chbFicha2.isSelected();
        Boolean Ficha3 = chbFicha3.isSelected();
        Boolean Ficha4 = chbFicha4.isSelected();

        Boolean CliMain = chbCliMain.isSelected();
        Boolean Cli1 = chbCli1.isSelected();
        Boolean Cli2 = chbCli2.isSelected();
        Boolean Cli3 = chbCli3.isSelected();
        Boolean Cli4 = chbCli4.isSelected();

        Boolean ArtMain = chbArtMain.isSelected();
        Boolean Art1 = chbArt1.isSelected();
        Boolean Art2 = chbArt2.isSelected();
        Boolean Art3 = chbArt3.isSelected();
        Boolean Art4 = chbArt4.isSelected();

        Boolean StockMain = chbStockMain.isSelected();
        Boolean Stock1 = chbStock1.isSelected();
        Boolean Stock2 = chbStock2.isSelected();
        Boolean Stock3 = chbStock3.isSelected();

        Boolean RepFicha = chbRepFicha.isSelected();
        Boolean ResSales = chbResSales.isSelected();
        Boolean RepStock = chbRepStock.isSelected();

        Boolean CityMain = chbCityMain.isSelected();
        Boolean City1 = chbCity1.isSelected();
        Boolean City2 = chbCity2.isSelected();
        Boolean City3 = chbCity3.isSelected();
        Boolean City4 = chbCity4.isSelected();

        Boolean BrandMain = chbBrandMain.isSelected();
        Boolean Brand1 = chbBrand1.isSelected();
        Boolean Brand2 = chbBrand2.isSelected();
        Boolean Brand3 = chbBrand3.isSelected();
        Boolean Brand4 = chbBrand4.isSelected();

        Boolean MethodMain = chbMethodMain.isSelected();
        Boolean Method1 = chbMethod1.isSelected();
        Boolean Method2 = chbMethod2.isSelected();
        Boolean Method3 = chbMethod3.isSelected();
        Boolean Method4 = chbMethod4.isSelected();

        Boolean UserMain = chbUserMain.isSelected();
        Boolean User1 = chbUser1.isSelected();
        Boolean User2 = chbUser2.isSelected();
        Boolean User3 = chbUser3.isSelected();
        Boolean User4 = chbUser4.isSelected();
        Boolean User5 = chbUser5.isSelected();

        if (Flag == 1) {
            con.InsertarDatos("usuario", "id_estado, username, fullname, user_ci, password, admin", "1 ,'" + Username + "', '" + Fullname + "', '" + CIUser + "', md5('" + PassNe + "'), " + Admin);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "1, " + FichaMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "2, " + Ficha1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "3, " + Ficha2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "4, " + Ficha3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "5, " + Ficha4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "6, " + CliMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "7, " + Cli1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "8, " + Cli2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "9, " + Cli3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "10, " + Cli4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "11, " + ArtMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "12, " + Art1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "13, " + Art2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "14, " + Art3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "15, " + Art4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "16, " + StockMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "17, " + Stock1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "18, " + Stock2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "19, " + Stock3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "20, " + CityMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "21, " + City1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "22, " + City2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "23, " + City3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "24, " + City4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "25, " + BrandMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "26, " + Brand1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "27, " + Brand2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "28, " + Brand3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "29, " + Brand4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "30, " + MethodMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "31, " + Method1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "32, " + Method2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "33, " + Method3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "34, " + Method4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "35, " + UserMain);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "36, " + User1);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "37, " + User2);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "38, " + User3);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "39, " + User4);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "40, " + User5);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "41, " + RepFicha);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "42, " + ResSales);
            con.InsertarDatosDetalle("users_permissions", "id_usuario, id_permission, active", IDUser + ", " + "43, " + RepStock);
        } else if (Flag == 2) {
            con.EditarDatos("usuario", "username = '" + Username + "', fullname = '" + Fullname + "', user_ci = '" + CIUser + "', admin = " + Admin, "id_usuario = " + IDUser);
            con.EditarDatosDetalle("users_permissions", "active = " + FichaMain, "id_usuario = " + IDUser + " AND id_permission = 1");
            con.EditarDatosDetalle("users_permissions", "active = " + Ficha1, "id_usuario = " + IDUser + " AND id_permission = 2");
            con.EditarDatosDetalle("users_permissions", "active = " + Ficha2, "id_usuario = " + IDUser + " AND id_permission = 3");
            con.EditarDatosDetalle("users_permissions", "active = " + Ficha3, "id_usuario = " + IDUser + " AND id_permission = 4");
            con.EditarDatosDetalle("users_permissions", "active = " + Ficha4, "id_usuario = " + IDUser + " AND id_permission = 5");
            con.EditarDatosDetalle("users_permissions", "active = " + CliMain, "id_usuario = " + IDUser + " AND id_permission = 6");
            con.EditarDatosDetalle("users_permissions", "active = " + Cli1, "id_usuario = " + IDUser + " AND id_permission = 7");
            con.EditarDatosDetalle("users_permissions", "active = " + Cli2, "id_usuario = " + IDUser + " AND id_permission = 8");
            con.EditarDatosDetalle("users_permissions", "active = " + Cli3, "id_usuario = " + IDUser + " AND id_permission = 9");
            con.EditarDatosDetalle("users_permissions", "active = " + Cli4, "id_usuario = " + IDUser + " AND id_permission = 10");
            con.EditarDatosDetalle("users_permissions", "active = " + ArtMain, "id_usuario = " + IDUser + " AND id_permission = 11");
            con.EditarDatosDetalle("users_permissions", "active = " + Art1, "id_usuario = " + IDUser + " AND id_permission = 12");
            con.EditarDatosDetalle("users_permissions", "active = " + Art2, "id_usuario = " + IDUser + " AND id_permission = 13");
            con.EditarDatosDetalle("users_permissions", "active = " + Art3, "id_usuario = " + IDUser + " AND id_permission = 14");
            con.EditarDatosDetalle("users_permissions", "active = " + Art4, "id_usuario = " + IDUser + " AND id_permission = 15");
            con.EditarDatosDetalle("users_permissions", "active = " + StockMain, "id_usuario = " + IDUser + " AND id_permission = 16");
            con.EditarDatosDetalle("users_permissions", "active = " + Stock1, "id_usuario = " + IDUser + " AND id_permission = 17");
            con.EditarDatosDetalle("users_permissions", "active = " + Stock2, "id_usuario = " + IDUser + " AND id_permission = 18");
            con.EditarDatosDetalle("users_permissions", "active = " + Stock3, "id_usuario = " + IDUser + " AND id_permission = 19");
            con.EditarDatosDetalle("users_permissions", "active = " + CityMain, "id_usuario = " + IDUser + " AND id_permission = 20");
            con.EditarDatosDetalle("users_permissions", "active = " + City1, "id_usuario = " + IDUser + " AND id_permission = 21");
            con.EditarDatosDetalle("users_permissions", "active = " + City2, "id_usuario = " + IDUser + " AND id_permission = 22");
            con.EditarDatosDetalle("users_permissions", "active = " + City3, "id_usuario = " + IDUser + " AND id_permission = 23");
            con.EditarDatosDetalle("users_permissions", "active = " + City4, "id_usuario = " + IDUser + " AND id_permission = 24");
            con.EditarDatosDetalle("users_permissions", "active = " + BrandMain, "id_usuario = " + IDUser + " AND id_permission = 25");
            con.EditarDatosDetalle("users_permissions", "active = " + Brand1, "id_usuario = " + IDUser + " AND id_permission = 26");
            con.EditarDatosDetalle("users_permissions", "active = " + Brand2, "id_usuario = " + IDUser + " AND id_permission = 27");
            con.EditarDatosDetalle("users_permissions", "active = " + Brand3, "id_usuario = " + IDUser + " AND id_permission = 28");
            con.EditarDatosDetalle("users_permissions", "active = " + Brand4, "id_usuario = " + IDUser + " AND id_permission = 29");
            con.EditarDatosDetalle("users_permissions", "active = " + MethodMain, "id_usuario = " + IDUser + " AND id_permission = 30");
            con.EditarDatosDetalle("users_permissions", "active = " + Method1, "id_usuario = " + IDUser + " AND id_permission = 31");
            con.EditarDatosDetalle("users_permissions", "active = " + Method2, "id_usuario = " + IDUser + " AND id_permission = 32");
            con.EditarDatosDetalle("users_permissions", "active = " + Method3, "id_usuario = " + IDUser + " AND id_permission = 33");
            con.EditarDatosDetalle("users_permissions", "active = " + Method4, "id_usuario = " + IDUser + " AND id_permission = 34");
            con.EditarDatosDetalle("users_permissions", "active = " + UserMain, "id_usuario = " + IDUser + " AND id_permission = 35");
            con.EditarDatosDetalle("users_permissions", "active = " + User1, "id_usuario = " + IDUser + " AND id_permission = 36");
            con.EditarDatosDetalle("users_permissions", "active = " + User2, "id_usuario = " + IDUser + " AND id_permission = 37");
            con.EditarDatosDetalle("users_permissions", "active = " + User3, "id_usuario = " + IDUser + " AND id_permission = 38");
            con.EditarDatosDetalle("users_permissions", "active = " + User4, "id_usuario = " + IDUser + " AND id_permission = 39");
            con.EditarDatosDetalle("users_permissions", "active = " + User5, "id_usuario = " + IDUser + " AND id_permission = 40");
            con.EditarDatosDetalle("users_permissions", "active = " + RepFicha, "id_usuario = " + IDUser + " AND id_permission = 41");
            con.EditarDatosDetalle("users_permissions", "active = " + ResSales, "id_usuario = " + IDUser + " AND id_permission = 42");
            con.EditarDatosDetalle("users_permissions", "active = " + RepStock, "id_usuario = " + IDUser + " AND id_permission = 43");
        } else if (Flag == 3) {
            con.BorrarDatosDetalle("users_permissions", "id_usuario = " + IDUser);
            con.BorrarDatos("usuario", "id_usuario = " + IDUser);
        } else if (Flag == 4) {
            con.EditarDatos("usuario", "id_estado = 2", "id_usuario = " + IDUser);
        } else if (Flag == 5) {
            con.EditarDatos("usuario", "id_estado = 1", "id_usuario = " + IDUser);
        } else if (Flag == 6) {
            System.out.println("Entró en ProcessData - Flag 6");
            if (PassAnswer == 0) {
                try {
                    System.out.println("Se edita la clave de este usuario");
                    int idUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    if (CheckPassSavedEquals(idUsuario)) {
                        con.EditarDatos("usuario", "password = md5('" + PassNe + "')", "id_usuario = " + IDUser);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (PassAnswer == 1) {
                try {
                    if (CheckPassSavedEquals(Menu.idUsuario) == true) {
                        System.out.println("Se edita la clave del usuario " + Username);
                        con.EditarDatos("usuario", "password = md5('" + PassNe + "')", "id_usuario = " + IDUser);
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña actual ingresada es incorrecta. Por favor, corrige antes de continuar.");
                        txtPassCurr.requestFocus();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("No entró en ninguno");
                System.out.println(PassAnswer);
            }
        }
    }

    private void GetUserSQL(int id) throws SQLException {
        String SQL_GetUser = "SELECT * FROM usuario WHERE id_usuario =  " + String.valueOf(id);
        rs = con.Results(SQL_GetUser);
        if (rs.next()) {
            if (Flag != 6) {
                EnableValuesEdit_UserLoaded();
            }
            txtUsername.setText(rs.getString("username"));
            txtFullname.setText(rs.getString("fullname"));
            txtCIUser.setText(rs.getString("user_ci"));
            Boolean userAdmin = rs.getBoolean("admin");
            chbAdmin.setSelected(userAdmin);
            String SQLUserCheck = "SELECT up.id_permission, per.name_permission, up.active FROM users_permissions up, permissions per WHERE up.id_permission = per.id_permission AND id_usuario = " + id + ";";
            rs = con.Results(SQLUserCheck);
            while (rs.next()) {
                int idPermission = rs.getInt("id_permission");
                String namePermission = rs.getString("name_permission");
                Boolean isActive = rs.getBoolean("active");
                if (isActive) {
                    switch (namePermission) {
                        case "FichaMain": //1
                            chbFichaMain.setSelected(true);
                            chbFicha1.setSelected(false);
                            chbFicha2.setSelected(false);
                            chbFicha3.setSelected(false);
                            chbFicha4.setSelected(false);
                            break;
                        case "FichaCreate": //2
                            chbFicha1.setSelected(true);
                            break;
                        case "FichaEdit": //3
                            chbFicha2.setSelected(true);
                            break;
                        case "FichaNull": //4
                            chbFicha3.setSelected(true);
                            break;
                        case "FichaClose": //5
                            chbFicha4.setSelected(true);
                            break;
                        case "CliMain": //6
                            chbCliMain.setSelected(true);
                            chbCli1.setSelected(false);
                            chbCli2.setSelected(false);
                            chbCli3.setSelected(false);
                            chbCli4.setSelected(false);
                            break;
                        case "CliCreate": //7
                            chbCli1.setSelected(true);
                            break;
                        case "CliEdit": //8
                            chbCli2.setSelected(true);
                            break;
                        case "CliDelete": //9
                            chbCli3.setSelected(true);
                            break;
                        case "CliList": //10
                            chbCli4.setSelected(true);
                            break;
                        case "ArtMain": //11
                            chbArtMain.setSelected(true);
                            chbArt1.setSelected(false);
                            chbArt2.setSelected(false);
                            chbArt3.setSelected(false);
                            chbArt4.setSelected(false);
                            break;
                        case "ArtCreate": //12
                            chbArt1.setSelected(true);
                            break;
                        case "ArtEdit": //13
                            chbArt2.setSelected(true);
                            break;
                        case "ArtDelete": //14
                            chbArt3.setSelected(true);
                            break;
                        case "ArtList": //15
                            chbArt4.setSelected(true);
                            break;
                        case "StockMain": //16
                            chbStockMain.setSelected(true);
                            chbStock1.setSelected(false);
                            chbStock2.setSelected(false);
                            chbStock3.setSelected(false);
                            break;
                        case "StockAdd": //17
                            chbStock1.setSelected(true);
                            break;
                        case "StockRemove": //18
                            chbStock2.setSelected(true);
                            break;
                        case "StockList": //19
                            chbStock3.setSelected(true);
                            break;
                        case "CityMain": //20
                            chbCityMain.setSelected(true);
                            chbCity1.setSelected(false);
                            chbCity2.setSelected(false);
                            chbCity3.setSelected(false);
                            chbCity4.setSelected(false);
                            break;
                        case "CityCreate": //21
                            chbCity1.setSelected(true);
                            break;
                        case "CityEdit": //22
                            chbCity2.setSelected(true);
                            break;
                        case "CityDelete": //23
                            chbCity3.setSelected(true);
                            break;
                        case "CityList": //24
                            chbCity4.setSelected(true);
                            break;
                        case "BrandMain": //25
                            chbBrandMain.setSelected(true);
                            chbBrand1.setSelected(false);
                            chbBrand2.setSelected(false);
                            chbBrand3.setSelected(false);
                            chbBrand4.setSelected(false);
                            break;
                        case "BrandCreate": //26
                            chbBrand1.setSelected(true);
                            break;
                        case "BrandEdit": //27
                            chbBrand2.setSelected(true);
                            break;
                        case "BrandDelete": //28
                            chbBrand3.setSelected(true);
                            break;
                        case "BrandList": //29
                            chbBrand4.setSelected(true);
                            break;
                        case "PayMain": //30
                            chbMethodMain.setSelected(true);
                            chbMethod1.setSelected(false);
                            chbMethod2.setSelected(false);
                            chbMethod3.setSelected(false);
                            chbMethod4.setSelected(false);
                            break;
                        case "PayCreate": //31
                            chbMethod1.setSelected(true);
                            break;
                        case "PayEdit": //32
                            chbMethod2.setSelected(true);
                            break;
                        case "PayDelete": //33
                            chbMethod3.setSelected(true);
                            break;
                        case "PayList": //34
                            chbMethod4.setSelected(true);
                            break;
                        case "UserMain": //35
                            chbUserMain.setSelected(true);
                            chbUser1.setSelected(false);
                            chbUser2.setSelected(false);
                            chbUser3.setSelected(false);
                            chbUser4.setSelected(false);
                            chbUser5.setSelected(false);
                            break;
                        case "UserCreate": //36
                            chbUser1.setSelected(true);
                            break;
                        case "UserEdit": //37
                            chbUser2.setSelected(true);
                            break;
                        case "UserDelete": //39
                            chbUser3.setSelected(true);
                            break;
                        case "UserOnOff": //39
                            chbUser4.setSelected(true);
                            break;
                        case "UserChangePass": //40
                            chbUser5.setSelected(true);
                            break;
                        case "ReportFicha": //41
                            chbRepFicha.setSelected(true);
                            break;
                        case "ReportSales": //42
                            chbResSales.setSelected(true);
                            break;
                        case "ReportStock": //43
                            chbRepStock.setSelected(true);
                            break;
                        default:
                            System.out.println("Permiso no concedido: " + idPermission + ", " + namePermission);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados");
        }

    }

    private String GetUsernameSQL(int id) throws SQLException {
        String SQL_GetUser = "SELECT * FROM usuario WHERE id_usuario =  " + String.valueOf(id);
        rs = con.Results(SQL_GetUser);
        if (rs.next()) {
            String usernameSQL = rs.getString("username");
            return usernameSQL;
        }
        return null;
    }

    private void GetUserSQL_Pass(int id) throws SQLException {
        String SQL_GetUser = "SELECT * FROM usuario WHERE id_usuario =  " + String.valueOf(id);
        rs = con.Results(SQL_GetUser);
        if (rs.next()) {
            txtIDUser.setText(rs.getString("id_usuario"));
            txtUsername.setText(rs.getString("username"));
            txtFullname.setText(rs.getString("fullname"));
            txtCIUser.setText(rs.getString("user_ci"));
            txtPassNew.setEnabled(true);
            txtPassNew.setEditable(true);
            txtPassRepeat.setEnabled(true);
            txtPassRepeat.setEditable(true);
        }
    }

    private void LoadTable() throws SQLException {
        rs = con.Results("SELECT usr.id_usuario, usr.username, st.estado, usr.fullname, usr.user_ci, usr.admin FROM usuario usr, estado st WHERE usr.id_estado = st.id_estado ORDER BY usr.id_usuario ASC");
        Search.setRowCount(0);
        while (rs.next()) {
            Object[] row = new Object[6];
            row[0] = rs.getString("id_usuario");
            row[1] = rs.getString("username");
            row[2] = rs.getString("estado");
            row[3] = rs.getString("fullname");
            row[4] = rs.getString("user_ci");
            row[5] = rs.getBoolean("admin");
            Search.addRow(row);
        }
    }

    private void CabeceraTabla() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        Search.addColumn("ID");
        Search.addColumn("Usuario");
        Search.addColumn("Estado");
        Search.addColumn("Nombre Completo");
        Search.addColumn("CI N°");
        Search.addColumn("Admin");
        TSearcher.getColumnModel().getColumn(0).setPreferredWidth(20);
        TSearcher.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(1).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(2).setPreferredWidth(30);
        TSearcher.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TSearcher.getColumnModel().getColumn(3).setPreferredWidth(140);
        TSearcher.getColumnModel().getColumn(4).setPreferredWidth(80);
        TSearcher.getColumnModel().getColumn(5).setPreferredWidth(20);
        TSearcher.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UserList = new javax.swing.JDialog();
        lblMessage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TSearcher = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        lblFiltrar = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnDisable = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnEnable = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblUserInfo = new javax.swing.JLabel();
        lblIDUser = new javax.swing.JLabel();
        txtIDUser = new javax.swing.JTextField();
        btnListUser = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblFullname = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        lblCIUser = new javax.swing.JLabel();
        txtCIUser = new javax.swing.JTextField();
        lblPassCurr = new javax.swing.JLabel();
        txtPassCurr = new javax.swing.JPasswordField();
        lblPassNew = new javax.swing.JLabel();
        txtPassNew = new javax.swing.JPasswordField();
        lblPassRepeat = new javax.swing.JLabel();
        txtPassRepeat = new javax.swing.JPasswordField();
        lblAdmin = new javax.swing.JLabel();
        chbAdmin = new javax.swing.JCheckBox();
        dlgUser = new javax.swing.JLabel();
        dlgPass = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPermissions = new javax.swing.JLabel();
        chbFichaMain = new javax.swing.JCheckBox();
        chbFicha1 = new javax.swing.JCheckBox();
        chbFicha2 = new javax.swing.JCheckBox();
        chbFicha3 = new javax.swing.JCheckBox();
        chbFicha4 = new javax.swing.JCheckBox();
        chbCliMain = new javax.swing.JCheckBox();
        chbCli1 = new javax.swing.JCheckBox();
        chbCli2 = new javax.swing.JCheckBox();
        chbCli3 = new javax.swing.JCheckBox();
        chbCli4 = new javax.swing.JCheckBox();
        chbArtMain = new javax.swing.JCheckBox();
        chbArt1 = new javax.swing.JCheckBox();
        chbArt2 = new javax.swing.JCheckBox();
        chbArt3 = new javax.swing.JCheckBox();
        chbArt4 = new javax.swing.JCheckBox();
        chbStockMain = new javax.swing.JCheckBox();
        chbStock1 = new javax.swing.JCheckBox();
        chbStock2 = new javax.swing.JCheckBox();
        chbStock3 = new javax.swing.JCheckBox();
        chbRepFicha = new javax.swing.JCheckBox();
        chbResSales = new javax.swing.JCheckBox();
        chbRepStock = new javax.swing.JCheckBox();
        chbCityMain = new javax.swing.JCheckBox();
        chbCity1 = new javax.swing.JCheckBox();
        chbCity2 = new javax.swing.JCheckBox();
        chbCity3 = new javax.swing.JCheckBox();
        chbCity4 = new javax.swing.JCheckBox();
        chbBrandMain = new javax.swing.JCheckBox();
        chbBrand1 = new javax.swing.JCheckBox();
        chbBrand2 = new javax.swing.JCheckBox();
        chbBrand3 = new javax.swing.JCheckBox();
        chbBrand4 = new javax.swing.JCheckBox();
        chbMethodMain = new javax.swing.JCheckBox();
        chbMethod1 = new javax.swing.JCheckBox();
        chbMethod2 = new javax.swing.JCheckBox();
        chbMethod3 = new javax.swing.JCheckBox();
        chbMethod4 = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        chbUserMain = new javax.swing.JCheckBox();
        chbUser1 = new javax.swing.JCheckBox();
        chbUser2 = new javax.swing.JCheckBox();
        chbUser3 = new javax.swing.JCheckBox();
        chbUser4 = new javax.swing.JCheckBox();
        chbUser5 = new javax.swing.JCheckBox();

        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("null");

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
        lblFiltrar.setText("Buscar usuario:");

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserListLayout = new javax.swing.GroupLayout(UserList.getContentPane());
        UserList.getContentPane().setLayout(UserListLayout);
        UserListLayout.setHorizontalGroup(
            UserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(UserListLayout.createSequentialGroup()
                        .addComponent(lblFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 422, Short.MAX_VALUE))
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserListLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        UserListLayout.setVerticalGroup(
            UserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UserListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnCreate.setText("Crear");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDisable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnDisable.setText("Deshabilitar");
        btnDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisableActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/write.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnEnable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnEnable.setText("Habilitar");
        btnEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnableActionPerformed(evt);
            }
        });

        btnChangePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contrasena.png"))); // NOI18N
        btnChangePass.setText("Cambiar Contraseña");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangePass))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEnable))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnCreate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDisable))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUserInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUserInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserInfo.setText("Datos Generales");

        lblIDUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDUser.setText("ID Usuario");

        txtIDUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDUserKeyPressed(evt);
            }
        });

        btnListUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/searchlist.png"))); // NOI18N
        btnListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListUserActionPerformed(evt);
            }
        });

        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Nombre de Usuario");

        txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        lblFullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFullname.setText("Nombre Completo");

        lblCIUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCIUser.setText("Cédula N°");

        txtCIUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblPassCurr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassCurr.setText("Contraseña Actual");

        txtPassCurr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassCurrFocusLost(evt);
            }
        });
        txtPassCurr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassCurrKeyPressed(evt);
            }
        });

        lblPassNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassNew.setText("Nueva Contraseña");

        txtPassNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassNewKeyPressed(evt);
            }
        });

        lblPassRepeat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassRepeat.setText("Repetir Contraseña");

        txtPassRepeat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPassRepeatFocusLost(evt);
            }
        });
        txtPassRepeat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassRepeatKeyPressed(evt);
            }
        });

        lblAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmin.setText("Administrador");

        chbAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        dlgUser.setForeground(new java.awt.Color(255, 0, 0));
        dlgUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        dlgPass.setForeground(new java.awt.Color(255, 0, 0));
        dlgPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPermissions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPermissions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPermissions.setText("Permisos");

        chbFichaMain.setText("Ficha de Cliente");
        chbFichaMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbFichaMainItemStateChanged(evt);
            }
        });

        chbFicha1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha1.setText("Crear OT");

        chbFicha2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha2.setText("Editar OT");

        chbFicha3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha3.setText("Anular/Reabrir OT");

        chbFicha4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbFicha4.setText("Cerrar OT");

        chbCliMain.setText("Clientes");
        chbCliMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbCliMainItemStateChanged(evt);
            }
        });

        chbCli1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli1.setText("Crear Cliente");

        chbCli2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli2.setText("Editar Cliente");

        chbCli3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli3.setText("Eliminar Cliente");

        chbCli4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCli4.setText("Listar Clientes");

        chbArtMain.setText("Artículos");
        chbArtMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbArtMainItemStateChanged(evt);
            }
        });

        chbArt1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt1.setText("Crear Artículo");

        chbArt2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt2.setText("Editar Artículo");

        chbArt3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt3.setText("Eliminar Artículo");

        chbArt4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbArt4.setText("Listar Artículos");

        chbStockMain.setText("Stock de Artículos");
        chbStockMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbStockMainItemStateChanged(evt);
            }
        });

        chbStock1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock1.setText("Añadir Stock");

        chbStock2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock2.setText("Restar Stock");

        chbStock3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbStock3.setText("Listar Artículos");

        chbRepFicha.setText("Buscar Ficha/OT");

        chbResSales.setText("Resumen de Ventas");

        chbRepStock.setText("Reporte de Existencia");

        chbCityMain.setText("Ciudades");
        chbCityMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbCityMainItemStateChanged(evt);
            }
        });

        chbCity1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity1.setText("Añadir Ciudad");

        chbCity2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity2.setText("Editar Ciudad");

        chbCity3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity3.setText("Eliminar Ciudad");

        chbCity4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbCity4.setText("Listar Ciudades");

        chbBrandMain.setText("Marcas");
        chbBrandMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbBrandMainItemStateChanged(evt);
            }
        });

        chbBrand1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand1.setText("Añadir Marca");

        chbBrand2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand2.setText("Editar Marca");

        chbBrand3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand3.setText("Eliminar Marca");

        chbBrand4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbBrand4.setText("Listar Marcas");

        chbMethodMain.setText("Métodos de Pago");
        chbMethodMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbMethodMainItemStateChanged(evt);
            }
        });

        chbMethod1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod1.setText("Añadir Método");

        chbMethod2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod2.setText("Editar Método");

        chbMethod3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod3.setText("Eliminar Método");

        chbMethod4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbMethod4.setText("Listar Métodos");

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/check.png"))); // NOI18N
        btnConfirm.setText("Confirmar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        chbUserMain.setText("Usuarios");
        chbUserMain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbUserMainItemStateChanged(evt);
            }
        });

        chbUser1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbUser1.setText("Crear Usuario");

        chbUser2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbUser2.setText("Editar Usuario");

        chbUser3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbUser3.setText("Eliminar Usuario");

        chbUser4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbUser4.setText("Habilitar/Deshabilitar");

        chbUser5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        chbUser5.setText("Cambiar Contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(lblPermissions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbFicha1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFichaMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbFicha4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbCli1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCliMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCli4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbArt1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArtMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbArt4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbStock1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStockMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStock2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbStock3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbRepFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbResSales, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbRepStock, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbCity1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCity4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbCityMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbBrand1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrand4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbBrandMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethod4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbMethodMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbUserMain, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chbUser5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dlgUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblIDUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIDUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnListUser)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dlgPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPassRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(chbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblPassRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIDUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnListUser)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsername)
                            .addComponent(lblFullname)
                            .addComponent(lblCIUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCIUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dlgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassNew)
                            .addComponent(lblPassRepeat)
                            .addComponent(lblPassCurr)
                            .addComponent(lblAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassCurr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dlgPass, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPermissions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbFichaMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbFicha4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbCliMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCli3)
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbArtMain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbArt3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chbArt4)
                                    .addComponent(chbCli4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbStockMain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbStock3)
                        .addGap(26, 26, 26)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbRepFicha)
                    .addComponent(chbResSales)
                    .addComponent(chbRepStock))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chbCityMain)
                            .addComponent(chbMethodMain)
                            .addComponent(chbBrandMain))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbCity1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCity2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCity3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbCity4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(chbBrand4)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(chbBrand1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chbBrand2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chbBrand3)
                                    .addGap(26, 26, 26)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbMethod1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbMethod2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbMethod3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chbMethod4))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chbUserMain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbUser1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbUser2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbUser3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chbUser4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbUser5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnConfirm))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            Flag = 1;
            NewUser();
            EnableValuesCreate();
            DisableBtns();
            EnableActionBtns();
            txtUsername.requestFocus();
            lblUserInfo.setText("Datos Generales - Creación de Usuario");
            lblPassCurr.setText("...");
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Flag = 2;
        EnableValuesEditDelete();
        DisableBtns();
        EnableActionBtns();
        lblUserInfo.setText("Datos Generales - Edición de Usuario");
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Flag = 3;
        EnableValuesEditDelete();
        DisableBtns();
        EnableActionBtns();
        lblUserInfo.setText("Datos Generales - Eliminación de Usuario");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisableActionPerformed
        Flag = 4;
        EnableValuesEditDelete();
        DisableBtns();
        EnableActionBtns();
        lblUserInfo.setText("Datos Generales - Deshabilitación de Usuario");
    }//GEN-LAST:event_btnDisableActionPerformed

    private void btnEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnableActionPerformed
        Flag = 5;
        EnableValuesEditDelete();
        DisableBtns();
        EnableActionBtns();
        lblUserInfo.setText("Datos Generales - Habilitación de Usuario");
    }//GEN-LAST:event_btnEnableActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        try {
            Flag = 6;
            DisableBtns();
            EnableActionBtns();
            lblUserInfo.setText("Datos Generales - Cambio de Contraseña");
            if (CheckUserLogged_Admin(Menu.idUsuario)) { // Si es TRUE el usuario podrá cambiar contraseña de otros usuarios
                String[] opciones = {GetUsernameSQL(Menu.idUsuario), "Otro Usuario"};
                PassAnswer = JOptionPane.showOptionDialog(null, "¿A qué usuario desea cambiar la contraseña?", "Cambio de Contraseña", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (PassAnswer == 0) {
                    GetUserSQL_Pass(Menu.idUsuario);
                    txtPassCurr.setEnabled(true);
                    txtPassCurr.requestFocus();
                } else if (PassAnswer == 1) {
                    EnableValuesEditDelete();
                    txtIDUser.requestFocus();
                }
            } else { // Si no es Admin, podrá cambiar solo su contraseña
                PassAnswer = 1;
                btnListUser.setEnabled(false);
                txtPassCurr.setEnabled(true);
                txtPassCurr.setEditable(true);
                txtPassCurr.requestFocus();
                GetUserSQL_Pass(Menu.idUsuario);
                DisableAllValuesPass();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            Flag = 0;
            CleanAllTxts();
            DisableAllValues();
            EnableBtns();
            DisableActionBtns();
            CheckUserPermissions(Menu.permissions);
            lblUserInfo.setText("Datos Generales");
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        try {
            if (CheckUserLoaded(txtUsername.getText())) {
                if (CheckPassEquals()) {
                    ProcessData();
                    CleanAllTxts();
                    DisableAllValues();
                    EnableBtns();
                    DisableActionBtns();
                    CheckUserPermissions(Menu.permissions);
                    lblUserInfo.setText("Datos Generales");
                    Flag = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnListUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListUserActionPerformed
        try {
            LoadTable();
            if (Flag == 2) {
                lblMessage.setText("Seleccione un usuario que desee modificar");
            } else if (Flag == 3) {
                lblMessage.setText("Seleccione un usuario que desee eliminar");
            } else if (Flag == 4) {
                lblMessage.setText("Seleccione un usuario que desee inactivar");
            } else if (Flag == 5) {
                lblMessage.setText("Seleccione un usuario que desee activar");
            } else if (Flag == 6) {
                lblMessage.setText("Seleccione un usuario que desee cambiar contraseña");
            } else {
                lblMessage.setText("Listado de Usuarios del Sistema");
            }
            UserList.setTitle("Buscar Usuario");
            UserList.setModal(true);
            UserList.pack();
            UserList.setResizable(false);
            UserList.setLocationRelativeTo(null);
            UserList.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListUserActionPerformed

    private void chbFichaMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbFichaMainItemStateChanged
        if (chbFichaMain.isSelected()) {
            chbFicha1.setEnabled(true);
            chbFicha2.setEnabled(true);
            chbFicha3.setEnabled(true);
            chbFicha4.setEnabled(true);
            chbFicha1.setSelected(true);
            chbFicha2.setSelected(true);
            chbFicha3.setSelected(true);
            chbFicha4.setSelected(true);
        } else {
            chbFicha1.setEnabled(false);
            chbFicha2.setEnabled(false);
            chbFicha3.setEnabled(false);
            chbFicha4.setEnabled(false);
            chbFicha1.setSelected(false);
            chbFicha2.setSelected(false);
            chbFicha3.setSelected(false);
            chbFicha4.setSelected(false);
        }
    }//GEN-LAST:event_chbFichaMainItemStateChanged

    private void chbCliMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbCliMainItemStateChanged
        if (chbCliMain.isSelected()) {
            chbCli1.setEnabled(true);
            chbCli2.setEnabled(true);
            chbCli3.setEnabled(true);
            chbCli4.setEnabled(true);
            chbCli1.setSelected(true);
            chbCli2.setSelected(true);
            chbCli3.setSelected(true);
            chbCli4.setSelected(true);
        } else {
            chbCli1.setEnabled(false);
            chbCli2.setEnabled(false);
            chbCli3.setEnabled(false);
            chbCli4.setEnabled(false);
            chbCli1.setSelected(false);
            chbCli2.setSelected(false);
            chbCli3.setSelected(false);
            chbCli4.setSelected(false);
        }
    }//GEN-LAST:event_chbCliMainItemStateChanged

    private void chbArtMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbArtMainItemStateChanged
        if (chbArtMain.isSelected()) {
            chbArt1.setEnabled(true);
            chbArt2.setEnabled(true);
            chbArt3.setEnabled(true);
            chbArt4.setEnabled(true);
            chbArt1.setSelected(true);
            chbArt2.setSelected(true);
            chbArt3.setSelected(true);
            chbArt4.setSelected(true);
        } else {
            chbArt1.setEnabled(false);
            chbArt2.setEnabled(false);
            chbArt3.setEnabled(false);
            chbArt4.setEnabled(false);
            chbArt1.setSelected(false);
            chbArt2.setSelected(false);
            chbArt3.setSelected(false);
            chbArt4.setSelected(false);
        }
    }//GEN-LAST:event_chbArtMainItemStateChanged

    private void chbStockMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbStockMainItemStateChanged
        if (chbStockMain.isSelected()) {
            chbStock1.setEnabled(true);
            chbStock2.setEnabled(true);
            chbStock3.setEnabled(true);
            chbStock1.setSelected(true);
            chbStock2.setSelected(true);
            chbStock3.setSelected(true);
        } else {
            chbStock1.setEnabled(false);
            chbStock2.setEnabled(false);
            chbStock3.setEnabled(false);
            chbStock1.setSelected(false);
            chbStock2.setSelected(false);
            chbStock3.setSelected(false);
        }
    }//GEN-LAST:event_chbStockMainItemStateChanged

    private void chbCityMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbCityMainItemStateChanged
        if (chbCityMain.isSelected()) {
            chbCity1.setEnabled(true);
            chbCity2.setEnabled(true);
            chbCity3.setEnabled(true);
            chbCity4.setEnabled(true);
            chbCity1.setSelected(true);
            chbCity2.setSelected(true);
            chbCity3.setSelected(true);
            chbCity4.setSelected(true);
        } else {
            chbCity1.setEnabled(false);
            chbCity2.setEnabled(false);
            chbCity3.setEnabled(false);
            chbCity4.setEnabled(false);
            chbCity1.setSelected(false);
            chbCity2.setSelected(false);
            chbCity3.setSelected(false);
            chbCity4.setSelected(false);
        }
    }//GEN-LAST:event_chbCityMainItemStateChanged

    private void chbBrandMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbBrandMainItemStateChanged
        if (chbBrandMain.isSelected()) {
            chbBrand1.setEnabled(true);
            chbBrand2.setEnabled(true);
            chbBrand3.setEnabled(true);
            chbBrand4.setEnabled(true);
            chbBrand1.setSelected(true);
            chbBrand2.setSelected(true);
            chbBrand3.setSelected(true);
            chbBrand4.setSelected(true);
        } else {
            chbBrand1.setEnabled(false);
            chbBrand2.setEnabled(false);
            chbBrand3.setEnabled(false);
            chbBrand4.setEnabled(false);
            chbBrand1.setSelected(false);
            chbBrand2.setSelected(false);
            chbBrand3.setSelected(false);
            chbBrand4.setSelected(false);
        }
    }//GEN-LAST:event_chbBrandMainItemStateChanged

    private void chbMethodMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbMethodMainItemStateChanged
        if (chbMethodMain.isSelected()) {
            chbMethod1.setEnabled(true);
            chbMethod2.setEnabled(true);
            chbMethod3.setEnabled(true);
            chbMethod4.setEnabled(true);
            chbMethod1.setSelected(true);
            chbMethod2.setSelected(true);
            chbMethod3.setSelected(true);
            chbMethod4.setSelected(true);
        } else {
            chbMethod1.setEnabled(false);
            chbMethod2.setEnabled(false);
            chbMethod3.setEnabled(false);
            chbMethod4.setEnabled(false);
            chbMethod1.setSelected(false);
            chbMethod2.setSelected(false);
            chbMethod3.setSelected(false);
            chbMethod4.setSelected(false);
        }
    }//GEN-LAST:event_chbMethodMainItemStateChanged

    private void chbUserMainItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbUserMainItemStateChanged
        if (chbUserMain.isSelected()) {
            chbUser1.setEnabled(true);
            chbUser2.setEnabled(true);
            chbUser3.setEnabled(true);
            chbUser4.setEnabled(true);
            chbUser5.setEnabled(true);
            chbUser1.setSelected(true);
            chbUser2.setSelected(true);
            chbUser3.setSelected(true);
            chbUser4.setSelected(true);
            chbUser5.setSelected(true);
        } else {
            chbUser1.setEnabled(false);
            chbUser2.setEnabled(false);
            chbUser3.setEnabled(false);
            chbUser4.setEnabled(false);
            chbUser5.setEnabled(false);
            chbUser1.setSelected(false);
            chbUser2.setSelected(false);
            chbUser3.setSelected(false);
            chbUser4.setSelected(false);
            chbUser5.setSelected(false);
        }
    }//GEN-LAST:event_chbUserMainItemStateChanged

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        String username = txtUsername.getText();
        if (username.matches(".*[\'\"!@#$%^&*()].*")) {
            txtUsername.setText(username.substring(0, username.length() - 1));
            dlgUser.setText("Los nombre de usuario no puede contener los siguientes carácteres: [ \' \" ! @ # $ % ^ & * ( ) ]");
        } else {
            try {
                dlgUser.setText("");
                CheckUserLoaded(txtUsername.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void txtPassRepeatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassRepeatFocusLost
        char[] PassNew = txtPassNew.getPassword();
        char[] PassRepeat = txtPassRepeat.getPassword();
        if (!Arrays.equals(PassNew, PassRepeat)) {
            dlgPass.setText("La contraseña ingresada no coinciden. Por favor, intentelo nuevamente");
        } else {
            dlgPass.setText("");
        }
    }//GEN-LAST:event_txtPassRepeatFocusLost

    private void txtPassNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassNewKeyPressed
        if (evt.getKeyCode() == 10 && !txtPassNew.equals("")) {
            txtPassRepeat.requestFocus();
        }
    }//GEN-LAST:event_txtPassNewKeyPressed

    private void txtPassCurrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassCurrKeyPressed
        if (evt.getKeyCode() == 10 && !txtPassCurr.equals("")) {
            txtPassNew.requestFocus();
        }
    }//GEN-LAST:event_txtPassCurrKeyPressed

    private void txtPassRepeatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassRepeatKeyPressed
        if (evt.getKeyCode() == 10 && !txtPassRepeat.equals("")) {
            txtPassNew.requestFocus();
            txtPassRepeat.requestFocus();
        }
    }//GEN-LAST:event_txtPassRepeatKeyPressed

    private void txtIDUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDUserKeyPressed
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
            txtIDUser.setText("");
        }
        if (evt.getKeyCode() == 10 && !txtIDUser.getText().equals("")) {
            try {
                RestartCheckBoxes();
                GetUserSQL(Integer.parseInt(txtIDUser.getText()));
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de eliminar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnConfirm.doClick();
                    } else {
                        btnCancel.doClick();
                    }
                } else if (Flag == 4) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    if (CheckUserStatus(IDUsuario)) {
                        int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de inactivar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (resp == 0) {
                            btnConfirm.doClick();
                        } else {
                            btnCancel.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario se encuentra inactivo.");
                        btnCancel.doClick();
                    }
                } else if (Flag == 5) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    if (CheckUserStatus(IDUsuario)) {
                        int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de activar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (resp == 0) {
                            btnConfirm.doClick();
                        } else {
                            btnCancel.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario se encuentra activo.");
                        btnCancel.doClick();
                    }
                } else if (Flag == 6) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    GetUserSQL_Pass(IDUsuario);
                    DisableAllValuesPass();
                    txtPassNew.requestFocus();
                }
            } catch (SQLException ex) {
            }
        } else if (evt.getKeyCode() == 10 && txtIDUser.getText().equals("")) {
            try {
                LoadTable();
                if (Flag == 2) {
                    lblMessage.setText("Seleccione un usuario que desee modificar");
                } else if (Flag == 3) {
                    lblMessage.setText("Seleccione un usuario que desee eliminar");
                } else if (Flag == 4) {
                    lblMessage.setText("Seleccione un usuario que desee inactivar");
                } else if (Flag == 5) {
                    lblMessage.setText("Seleccione un usuario que desee activar");
                } else if (Flag == 6) {
                    lblMessage.setText("Seleccione un usuario que desee cambiar contraseña");
                }
                UserList.setTitle("Buscar Usuario");
                UserList.setModal(true);
                UserList.pack();
                UserList.setResizable(false);
                UserList.setLocationRelativeTo(null);
                UserList.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtIDUserKeyPressed

    private void TSearcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSearcherMouseClicked
        if (Flag != 0) {
            try {
                int row = TSearcher.getSelectedRow();
                String id = TSearcher.getModel().getValueAt(row, 0).toString();
                UserList.setModal(false);
                UserList.setVisible(false);
                txtIDUser.setText(id);
                GetUserSQL(Integer.parseInt(txtIDUser.getText()));
                if (Flag == 3) {
                    int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de eliminar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                    if (resp == 0) {
                        btnConfirm.doClick();
                    } else {
                        btnCancel.doClick();
                    }
                } else if (Flag == 4) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    if (CheckUserStatus(IDUsuario)) {
                        int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de inactivar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (resp == 0) {
                            btnConfirm.doClick();
                        } else {
                            btnCancel.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario se encuentra inactivo.");
                        btnCancel.doClick();
                    }
                } else if (Flag == 5) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    if (CheckUserStatus(IDUsuario)) {
                        int resp = JOptionPane.showConfirmDialog(null, "Estás seguro de activar el usuario " + txtIDUser.getText().toString() + ": " + txtUsername.getText().toString() + "?", "Atención!", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (resp == 0) {
                            btnConfirm.doClick();
                        } else {
                            btnCancel.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario se encuentra activo.");
                        btnCancel.doClick();
                    }
                } else if (Flag == 6) {
                    int IDUsuario = (Integer) Integer.parseInt(txtIDUser.getText());
                    GetUserSQL_Pass(IDUsuario);
                    DisableAllValuesPass();
                    txtPassNew.requestFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_TSearcherMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        try {
            rs = con.Results("SELECT usr.id_usuario, usr.username, st.estado, usr.fullname, usr.user_ci, usr.admin FROM usuario usr, estado st WHERE usr.id_estado = st.id_estado AND username ilike '%" + txtFilter.getText() + "%'");
            Search.setRowCount(0);
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("id_usuario");
                row[1] = rs.getString("username");
                row[2] = rs.getString("estado");
                row[3] = rs.getString("fullname");
                row[4] = rs.getString("user_ci");
                row[5] = rs.getBoolean("admin");
                Search.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ciudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        UserList.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtPassCurrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassCurrFocusLost
        try {
            CheckPassSavedEquals(Menu.idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtPassCurrFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TSearcher;
    private javax.swing.JDialog UserList;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDisable;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEnable;
    private javax.swing.JButton btnListUser;
    private javax.swing.JCheckBox chbAdmin;
    private javax.swing.JCheckBox chbArt1;
    private javax.swing.JCheckBox chbArt2;
    private javax.swing.JCheckBox chbArt3;
    private javax.swing.JCheckBox chbArt4;
    private javax.swing.JCheckBox chbArtMain;
    private javax.swing.JCheckBox chbBrand1;
    private javax.swing.JCheckBox chbBrand2;
    private javax.swing.JCheckBox chbBrand3;
    private javax.swing.JCheckBox chbBrand4;
    private javax.swing.JCheckBox chbBrandMain;
    private javax.swing.JCheckBox chbCity1;
    private javax.swing.JCheckBox chbCity2;
    private javax.swing.JCheckBox chbCity3;
    private javax.swing.JCheckBox chbCity4;
    private javax.swing.JCheckBox chbCityMain;
    private javax.swing.JCheckBox chbCli1;
    private javax.swing.JCheckBox chbCli2;
    private javax.swing.JCheckBox chbCli3;
    private javax.swing.JCheckBox chbCli4;
    private javax.swing.JCheckBox chbCliMain;
    private javax.swing.JCheckBox chbFicha1;
    private javax.swing.JCheckBox chbFicha2;
    private javax.swing.JCheckBox chbFicha3;
    private javax.swing.JCheckBox chbFicha4;
    private javax.swing.JCheckBox chbFichaMain;
    private javax.swing.JCheckBox chbMethod1;
    private javax.swing.JCheckBox chbMethod2;
    private javax.swing.JCheckBox chbMethod3;
    private javax.swing.JCheckBox chbMethod4;
    private javax.swing.JCheckBox chbMethodMain;
    private javax.swing.JCheckBox chbRepFicha;
    private javax.swing.JCheckBox chbRepStock;
    private javax.swing.JCheckBox chbResSales;
    private javax.swing.JCheckBox chbStock1;
    private javax.swing.JCheckBox chbStock2;
    private javax.swing.JCheckBox chbStock3;
    private javax.swing.JCheckBox chbStockMain;
    private javax.swing.JCheckBox chbUser1;
    private javax.swing.JCheckBox chbUser2;
    private javax.swing.JCheckBox chbUser3;
    private javax.swing.JCheckBox chbUser4;
    private javax.swing.JCheckBox chbUser5;
    private javax.swing.JCheckBox chbUserMain;
    private javax.swing.JLabel dlgPass;
    private javax.swing.JLabel dlgUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblCIUser;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblIDUser;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPassCurr;
    private javax.swing.JLabel lblPassNew;
    private javax.swing.JLabel lblPassRepeat;
    private javax.swing.JLabel lblPermissions;
    private javax.swing.JLabel lblUserInfo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtCIUser;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtIDUser;
    private javax.swing.JPasswordField txtPassCurr;
    private javax.swing.JPasswordField txtPassNew;
    private javax.swing.JPasswordField txtPassRepeat;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
