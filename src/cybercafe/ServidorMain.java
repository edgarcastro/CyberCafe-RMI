package cybercafe;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import cybercafe.controlador.CServidor;
/**
 * Clase principal del servidor.
 * 
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0 
 */
public class ServidorMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException{
        try{
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        CServidor.iniciar();
    }
    
}
