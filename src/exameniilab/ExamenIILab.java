/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exameniilab;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josuc
 */
public class ExamenIILab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            new GUI();
        } catch (IOException ex) {
            Logger.getLogger(ExamenIILab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
