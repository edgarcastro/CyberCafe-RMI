/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.modelo;

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
import servercybercafe.controlador.masterController;

/**
 *
 * @author edgarcastro
 */
public class Servidor {
    private List<String> clientes;
    private int puerto;

    public Servidor(int puerto) {
        this.clientes = new ArrayList<>();
        this.puerto = puerto;
    }
    
    public void init() throws RemoteException, AlreadyBoundException{
        Remote stub = UnicastRemoteObject.exportObject(new IConexion() {
            @Override
            public boolean conectarse(String ip) throws RemoteException {
                System.out.println("DEBUG: Llegó un nuevo cliente: Ip:"+ip);
                boolean estado = clientes.add(ip);
                masterController.updateClients(clientes);
                return estado;
            }

            @Override
            public boolean desconectarse(String ip) throws RemoteException {
                System.out.println("DEBUG: Llegó un nuevo cliente: Ip:"+ip);
                boolean estado = clientes.remove(ip);
                masterController.updateClients(clientes);
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
    
    public void listarClientes(){
        System.out.println(this.clientes.toString());
    }
    
    public void apagarCliente(String ip) throws RemoteException, NotBoundException{
        //pass
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("APAGAR");
    }
    
    public void bloquearCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("BLOQUEAR");
    }
    
    public void reiniciarCliente(String ip) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
        IClienteRemoto clienteRemoto = (IClienteRemoto) registry.lookup(ip);
        clienteRemoto.mensaje("REINICIAR");
    }

    public List<String> getClientes() {
        return clientes;
    }
}
