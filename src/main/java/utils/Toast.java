package utils;


import java.awt.Component;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Toast {
    
    public static void error(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void warn(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void info(Component c, String msg) {
        JOptionPane.showMessageDialog(c, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
