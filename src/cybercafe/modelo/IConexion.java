package cybercafe.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Edgar Castro, Luis Salas
 */
public interface IConexion extends Remote {
    boolean conectarse(String ip) throws RemoteException;
    boolean desconectarse(String ip) throws RemoteException;
}
