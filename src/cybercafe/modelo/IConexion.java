package cybercafe.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Edgar Castro, Luis Salas
 */
public interface IConexion extends Remote {
    /**
     * Método para realizar la conexión con el servidor.
     * 
     * @param ip Dirección IP del cliente que se conectará
     * @return estado verdadero si se conectó, falso en caso contrario.
     * @throws RemoteException 
     */
    boolean conectarse(String ip) throws RemoteException;
    /**
     * Método para realizar la desconexión con el servidor.
     * @param ip Dirección IP del cliente que se desconectará
     * @return estado verdadero si se desconectó, falso en caso contrario.
     * @throws RemoteException 
     */
    boolean desconectarse(String ip) throws RemoteException;
}
