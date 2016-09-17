/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import servercybercafe.controlador.masterController;
import servercybercafe.modelo.Servidor;


/**
 *
 * @author edgar
 */
public class Servercybercafe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException{
        try{
  
  JFrame.setDefaultLookAndFeelDecorated(true);
  JDialog.setDefaultLookAndFeelDecorated(true);
  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
}
catch (Exception e)
 {
  e.printStackTrace();
 }
        masterController.init();
        //Servidor s = new Servidor(7777);
        //s.init();
        
    }
    
}
