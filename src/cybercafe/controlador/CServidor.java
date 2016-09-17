package cybercafe.controlador;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.List;
import cybercafe.modelo.Servidor;
import cybercafe.vista.VServidor;
import java.rmi.NotBoundException;

/**
 * Controlador para manejar los eventos ocurridos en el servidor.
 *
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0 
 */
public class CServidor {
    
    private static VServidor ventana;
    private static Servidor servidor;
    
    /**
     * Método para inicializar los componentes.
     * 
     */
    public static void iniciar(){
        ventana = new VServidor();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    /**
     * Método para inicializar el servidor.
     * 
     * @param port puerto donde se ejecutará el servidor.
     * @throws RemoteException
     * @throws AlreadyBoundException 
     */
    public static void iniciarServidor(int port) throws RemoteException, AlreadyBoundException{
        servidor = new Servidor(port);
        servidor.iniciar();
    }
    
    /**
     * Método para mostrat las actualizaciones de los clientes conectados.
     * 
     * @param clients lista de clientes conectados.
     */
    public static void actualizarClientes(List clients){
        ventana.updateNumberClients(clients.size());
        ventana.updateClients(clients);
    }
    
    /**
     * Método para bloquear PC.
     * 
     * @param ip dirección ip donde se encuentra el cliente a bloquear.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public static void bloquearCliente(String ip) throws RemoteException, NotBoundException{
        servidor.bloquearCliente(ip);
    }
    
    /**
     * Método para desbloquear PC.
     * 
     * @param ip dirección ip donde se encuentra el cliente a desbloquear.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public static void desbloquearCliente(String ip) throws RemoteException, NotBoundException{
        servidor.desbloquearCliente(ip);
    }
    
    /**
     * Método para apagar PC.
     * 
     * @param ip dirección ip donde se encuentra el cliente a apagar.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public static void apagarCliente(String ip) throws RemoteException, NotBoundException{
        servidor.apagarCliente(ip);
    }
    
    /**
     * Método para reiniciar PC.
     * 
     * @param ip dirección ip donde se encuentra el cliente a reiniciar.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public static void reiniciarCliente(String ip) throws RemoteException, NotBoundException{
        servidor.reiniciarCliente(ip);
    }
    
    /**
     * Método para cancelar cualquier operación.
     * 
     * @param ip dirección ip donde se encuentra el cliente que va a cancelar la operación.
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public static void cancelarOperacion(String ip) throws RemoteException, NotBoundException{
        servidor.cancelarCliente(ip);
    }
}
