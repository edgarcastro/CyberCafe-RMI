package cybercafe.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Edgar Castro, Luis Salas
 */
public interface IClienteRemoto extends Remote{
    /**
     * Método para mandar a realizar acción en los clientes
     * 
     * @param accion acción clave que se va a ejecutar en el cliente.
     * @return estado verdadero si se cumplió la acción, falso en caso contrario.
     * @throws RemoteException 
     */
    public boolean mensaje(String accion) throws RemoteException; 
}
