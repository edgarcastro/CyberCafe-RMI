package cybercafe.modelo;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author Edgar Castro, Luis Salas
 * @version 1.0.0
 */
public class Pc {
    private JFrame jframe;
    
    public Pc(JFrame jframe){
        this.jframe = jframe; //Se recibe una instacia de la clase JFrame y se trabaja con la misma instancia
    }
    
 /**
 * ejecuta una tarea cada "n" tiempo
 * Para evitar que el usuario utilice las teclas (WINDOWS + D)(TAB) y asi perder el foco
 * de la aplicación, cada 50 milisegundos se envia el JFrame al frente y se cambia su propiedad a maximizado
 */
    public void bloquear(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() 
            {
                @Override
                public void run() {                   
                    front(); 
                }
            }, 500, 50 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
        jframe.setVisible(true);
    }

    /**
     * 
     */
    private void front()
    {
        jframe.setExtendedState( JFrame.MAXIMIZED_BOTH );//maximizado
        jframe.toFront();
    }
    
    public void desbloquear(){
        jframe.dispose();
    }
    
    //Ejecuta los comandos (apagar, reiniciar) o cancecela los mismos
    //dependiendo de lo que resiva como parametro
    private void exec(String cmd){
        try { 
            Runtime.getRuntime().exec(cmd); 
        }catch (IOException e) { 
            System.out.println("Failed");         
        } 
    }
    
    public void apagar(){
        exec("shutdown -s -t 30");//apagar en 30 segundos
    }
    
    public void reiniciar(){
        exec("shutdown -r -t 30");//reinicar en 30 segundos
    }
    
    public void cancelar(){
        exec("shutdown -a");//cancela cualquier operacion de apagado o reinicio
    }
    
    
}
