package cybercafe.controlador;

import cybercafe.modelo.Pc;
import cybercafe.modelo.Cliente;
import cybercafe.vista.BloquedView;
import cybercafe.vista.VCliente;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Controlador para manejar los eventos ocurridos en el cliente.
 * 
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0 
 */
public class CCliente {
    public static Cliente cliente;
    public static VCliente ventana;
    public static BloquedView ventanaBloqueo;
    public static Pc pc;
    
    /**
     * Método para inicializar los componentes.
     * 
     */
    public static void iniciar(){
        ventana = new VCliente();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventanaBloqueo = new BloquedView();
        pc = new Pc(ventanaBloqueo);
    }
    
    /**
     * Método para inicializar el cliente.
     * 
     * @param ip dirección ip del servidor al cuál se va a conectar.
     * @param puerto puerto del servidor al cuál se va a conectar.
     * @throws RemoteException
     * @throws NotBoundException
     * @throws AlreadyBoundException 
     */
    public static void iniciarCliente(String ip, int puerto) throws RemoteException, NotBoundException, AlreadyBoundException{
        cliente = new Cliente(ip,puerto);
        cliente.iniciar();
    }
    
    public static void actualizarNombrePC(String namePC){
        ventana.updateNamePC(namePC);
    }
    
    /**
     * Método para bloquear el PC.
     */
    public static void bloquearPC(){
        pc.bloquear();
    }
    
    /**
     * Método para desbloquar el PC.
     */
    public static void desbloquearPC(){
        pc.desbloquear();
    }
    
    /**
     * Método para apagar el PC.
     */
    public static void apagarPC(){
        pc.apagar();
    }
    
    /**
     * Método para reiniciar el PC.
     */
    public static void reiniciarPC(){
        pc.reiniciar();
    }
    
    /**
     * Método para cancelar cualquier operación del PC.
     */
    public static void cancelarPC(){
        pc.cancelar();
    }
}
