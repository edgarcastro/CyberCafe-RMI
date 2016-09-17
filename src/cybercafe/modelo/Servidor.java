package cybercafe.modelo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cybercafe.controlador.CServidor;

/**
 *
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0
 */
public class Servidor {
    private List<String> clientes;
    private int puerto;

    public Servidor(int puerto) {
        this.clientes = new ArrayList<>();
        this.puerto = puerto;
    }
    
    public void iniciar() throws RemoteException, AlreadyBoundException{
        Remote stub = UnicastRemoteObject.exportObject(new IConexion() {
            @Override
            public boolean conectarse(String ip) throws RemoteException {
                System.out.println("DEBUG: Llegó un nuevo cliente: Ip:"+ip);
                boolean estado = clientes.add(ip);
                CServidor.actualizarClientes(clientes);
                return estado;
            }

            @Override
            public boolean desconectarse(String ip) throws RemoteException {
                System.out.println("DEBUG: Llegó un nuevo cliente: Ip:"+ip);
                boolean estado = clientes.remove(ip);
                CServidor.actualizarClientes(clientes);
                return estado;
            }
        }, 0);
        Registry registry = LocateRegistry.createRegistry(puerto);
        registry.bind("Servidor", stub);
        try {
            System.out.println("DEBUG: Estoy corriendo en:"+InetAddress.getLocalHost().getHostAddress()+":"+this.puerto);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bloquearCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("BLOQUEAR");
    }
     
    public void desbloquearCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("DESBLOQUEAR");
    }
    public void apagarCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("APAGAR");
    }
    
    public void reiniciarCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("REINICIAR");
    }
    
    public void cancelarCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("CANCELAR");
    }

    public List<String> getClientes() {
        return clientes;
    }

    public void setClientes(List<String> clientes) {
        this.clientes = clientes;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
