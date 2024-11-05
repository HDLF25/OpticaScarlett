package Otros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    Statement stm = null;
    ResultSet rs = null;
    Connection con = null;
    String server = "localhost";
    String puerto = "5432";
    String db = "OpticaScarlettTest";
    String user = "postgres";
    String password = "serpiente25";
    
    public Connection Login(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://"+server+"/"+db,user,password);
            //System.out.println("La conexión se realizó con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return con;
    }
    public ResultSet Results(String sql){
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println(sql);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }
    public void InsertarDatos(String tabla, String atributos, String valores){
        try {
            stm = con.createStatement();
            stm.executeUpdate("Insert into "+tabla+" ("+atributos+") values("+valores+");");
            JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente.");
        } catch (Exception ex) {
            System.out.println("Insert into "+tabla+" ("+atributos+") values("+valores+");");
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void InsertarDatosDetalle(String tabla, String atributos, String valores){
        try {
            stm = con.createStatement();
            stm.executeUpdate("Insert into "+tabla+" ("+atributos+") values("+valores+");");
        } catch (Exception ex) {
            System.out.println("Insert into "+tabla+" ("+atributos+") values("+valores+");");
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void EditarDatos(String tabla, String valores, String condicion) {
        try {
            stm = con.createStatement();
            stm.executeUpdate("Update "+tabla+" set "+valores+" where "+condicion);
            JOptionPane.showMessageDialog(null, "Los datos se modificaron correctamente.");
        } catch (SQLException ex) {
            System.out.println("Update "+tabla+" set "+valores+" where "+condicion);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void EditarDatosDetalle(String tabla, String valores, String condicion) {
        try {
            stm = con.createStatement();
            stm.executeUpdate("Update "+tabla+" set "+valores+" where "+condicion);
        } catch (SQLException ex) {
            System.out.println("Update "+tabla+" set "+valores+" where "+condicion);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void BorrarDatos(String tabla, String condicion) {
        try {
            stm = con.createStatement();
            stm.executeUpdate("Delete from "+tabla+" where "+condicion);
            JOptionPane.showMessageDialog(null, "Los datos se eliminaron correctamente.");
        } catch (SQLException ex) {
            System.out.println("Delete from "+tabla+" where "+condicion);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void BorrarDatosDetalle(String tabla, String condicion) {
        try {
            stm = con.createStatement();
            stm.executeUpdate("Delete from "+tabla+" where "+condicion);
        } catch (SQLException ex) {
            System.out.println("Delete from "+tabla+" where "+condicion);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
