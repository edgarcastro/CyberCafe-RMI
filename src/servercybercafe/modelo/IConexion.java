/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author edgarcastro
 */
public interface IConexion extends Remote {
    boolean conectarse(String ip) throws RemoteException;
    boolean desconectarse(String ip) throws RemoteException;
}
