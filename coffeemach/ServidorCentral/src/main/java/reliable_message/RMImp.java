package reliable_message;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.AbstractDocument.BranchElement;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import alarma.Alarma;
import interfaz.ControladorRecetas;
import servicios.AlarmaServicePrx;

public class RMImp implements RM.ReliableMessage {

    private Communicator communicator;
    private final static int INGREDIENTES=1;
	private final static int MONEDAS=2;
	private final static int SUMINISTRO =3;
	private final static int ABASTECIMIENTO =4;
	private final static int MALFUN =5;
    private AlarmaServicePrx alarmaService; //No se cual de los dos es el que se usa :c
    private Queue<String> queue;
    private ControladorRecetas controladorRecetas;
    private Alarma alarma;
    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
        queue= new LinkedList<>();
        System.out.println("RMImp creado");

    }

    /**
     * @param controladorReceta controlador receta to set
     */
    public void setControladorReceta(ControladorRecetas controladorRecetas) {
        this.controladorRecetas = controladorRecetas;   
    }

    
    /**
     * @param alarma controlador receta to set
     */
    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;   
    }

    /* 
    public void notifyAlarm(String a, Current current) {
        System.out.println("#"+(queue.size()+1)+"Alarma recibida: "+a);
        queue.add(a);
    } */

    public String pollAlarm(){
        return queue.poll();
    }

    public String peekAlarm(){
        return queue.peek();
    }

    public Queue<String> getQueue(){
        return queue;
    }

    
    @Override
    public void notifyAlarm(String a, Current current) {
        String[] partsOfAlarm = a.split("@s");
        
        /*for (String string : partsOfAlarm) {
            System.out.println(string);
        }*/

        switch (Integer.parseInt(partsOfAlarm[0])) {
            case 1: 
                alarma.recibirNotificacionEscasezIngredientes(partsOfAlarm[1], Integer.parseInt(partsOfAlarm[2]), current);
            break;

            case 2: //Moneda is setted to 0 as default value, thats equivalent to 100 pesos
                alarma.recibirNotificacionInsuficienciaMoneda(servicios.Moneda.valueOf(0), Integer.parseInt(partsOfAlarm[1]), current);
            break;

            case 3:
                alarma.recibirNotificacionEscasezSuministro(partsOfAlarm[1], Integer.parseInt(partsOfAlarm[2]), current);
            break;

            case 4:
                alarma.recibirNotificacionAbastesimiento(Integer.parseInt(partsOfAlarm[1]), partsOfAlarm[2], Integer.parseInt(partsOfAlarm[3]), current);
            break;

            case 5:
                alarma.recibirNotificacionMalFuncionamiento(Integer.parseInt(partsOfAlarm[1]), partsOfAlarm[2], current);
            break;

        }

        
    }
    
}
