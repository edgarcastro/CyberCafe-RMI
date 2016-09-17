package cybercafe;

import cybercafe.controlador.CCliente;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Clase principal del cliente.
 * 
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0 
 */
public class ClienteMain {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try{
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        CCliente.iniciar();
    }
}
