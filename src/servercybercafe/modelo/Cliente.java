/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.modelo;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author edgarcastro
 */
public class Cliente {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("192.168.1.5", 7777);
        IConexion testRemote = (IConexion) registry.lookup("Servidor");
        try {
            System.out.println(testRemote.conectarse(InetAddress.getLocalHost().getHostAddress()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
