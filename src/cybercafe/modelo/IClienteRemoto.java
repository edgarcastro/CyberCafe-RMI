package cybercafe.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Edgar Castro, Luis Salas
 */
public interface IClienteRemoto extends Remote{
    public boolean mensaje(String accion) throws RemoteException; 
}
