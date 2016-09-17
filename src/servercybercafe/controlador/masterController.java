/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercybercafe.controlador;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.List;
import servercybercafe.modelo.Server;
import servercybercafe.modelo.Servidor;
import servercybercafe.vista.MainView;

/**
 *
 * @author edgar
 */
public class masterController {
    
    private static MainView mv;
    //private static Server sv;
    private static Servidor s;
    
    public static void init(){
        mv = new MainView();
        mv.setLocationRelativeTo(null);
        mv.setVisible(true);
    }
    
    public static void initServer(int port) throws RemoteException, AlreadyBoundException{
        s = new Servidor(port);
        s.init();
        //sv = new Server(port);
        //sv.start();
    }
    
    public static void updateClients(List clients){
        mv.updateNumberClients(clients.size());
        mv.updateClients(clients);
    }
    
    public static void blockClient(int idPC){
        //sv.blockOneClient(idPC);
    }
    
    public static void unBlockClient(int idPC){
        //sv.unBlockOneClient(idPC);
    }
    
    public static void apagarCliente(int idPC){
        //sv.apagarOneCliente(idPC);
    }
    
    public static void reiniciarCliente(int idPC){
        //sv.reiniciarOneCliente(idPC);
    }
    
    public static void cancelarOperacion(int idPC){
        //sv.cancelarOperacion(idPC);
    }
}
