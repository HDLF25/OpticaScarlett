package main;

import GUI.Login;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login().setVisible(true);
    }
    
}
