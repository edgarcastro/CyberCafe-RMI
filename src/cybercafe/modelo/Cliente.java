package cybercafe.modelo;

import cybercafe.controlador.CCliente;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0
 */
public class Cliente {
    private String ipServidor;
    private int puertoServidor;
    private String ip;

    public Cliente(String ipServidor, int puertoServidor) throws RemoteException, NotBoundException {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        Registry registro = LocateRegistry.getRegistry(this.ipServidor, this.puertoServidor);
        IConexion servidorRemoto = (IConexion) registro.lookup("Servidor");
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
            servidorRemoto.conectarse(this.ip);
            CCliente.actualizarNombrePC(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método para implementar los métodos remotos que tendrá el cliente.
     * 
     * @throws RemoteException
     * @throws AlreadyBoundException 
     */
    public void iniciar() throws RemoteException, AlreadyBoundException{
        Remote stub = UnicastRemoteObject.exportObject(new IClienteRemoto() {
            @Override
            public boolean mensaje(String accion) throws RemoteException {
                boolean estado = false;
                System.out.println("DEBUG: "+accion);
                if(accion.equals("BLOQUEAR")) {
                    CCliente.bloquearPC();
                    estado = true;
                }
                if(accion.equals("DESBLOQUEAR")){
                    CCliente.desbloquearPC();
                    estado = true;
                }
                if(accion.equals("APAGAR")){
                    CCliente.apagarPC();
                    estado = true;
                }
                if(accion.equals("REINICIAR")){
                    CCliente.reiniciarPC();
                    estado = true;
                }
                if(accion.equals("CANCELAR")){
                    CCliente.cancelarPC();
                    estado = true;
                }
                return estado;
            }
        },0);
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registry.bind(this.ip, stub);
    }
    
    /**
     * Método que llama a método remoto para desconectarse.
     * 
     * @throws RemoteException
     * @throws NotBoundException 
     */
    public void desconectar() throws RemoteException, NotBoundException{
        Registry registro = LocateRegistry.getRegistry(this.ipServidor, this.puertoServidor);
        IConexion servidorRemoto = (IConexion) registro.lookup("Servidor");
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
            servidorRemoto.desconectarse(this.ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

