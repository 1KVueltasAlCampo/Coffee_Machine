package reliable_message;
import java.util.LinkedList;
import java.util.Queue;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import servicios.AlarmaServicePrx;

public class RMImp implements RM.ReliableMessage {

    private Communicator communicator;
    private final static int INGREDIENTES=1;
	private final static int MONEDAS=2;
	private final static int SUMINISTRO =3;
	private final static int ABASTECIMIENTO =4;
	private final static int MALFUN =5;
    //private Alarma alarmaService; //No se cual de los dos es el que se usa :c
    private Queue<String> queue;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
        queue= new LinkedList<>();
        System.out.println("RMImp creado");
    }

    @Override
    public void notifyAlarm(String a, Current current) {
        System.out.println("Alarma recibida: "+a);
        queue.add(a);
    }

    public String pollAlarm(){
        return queue.poll();
    }

    public String peekAlarm(){
        return queue.peek();
    }

    /*
    @Override
    public void notifyAlarm(String a, Current current) {
        String[] partsOfAlarm = a.split("@s");

        switch(Integer.parseInt(partsOfAlarm[0])){
            case INGREDIENTES:
                alarmaService.recibirNotificacionEscasezIngredientes(partsOfAlarm[1], Integer.parseInt(partsOfAlarm[2]));
                break;
            case MONEDAS:
                alarmaService.recibirNotificacionInsuficienciaMoneda(servicios.Moneda.valueOf(partsOfAlarm[1]), Integer.parseInt(partsOfAlarm[2]));
                break;
            case SUMINISTRO:
                break;
            case ABASTECIMIENTO:
                alarmaService.recibirNotificacionAbastesimiento(Integer.parseInt(partsOfAlarm[1]), partsOfAlarm[2], Integer.parseInt(partsOfAlarm[3]));
                break;
            case MALFUN:
                alarmaService.recibirNotificacionMalFuncionamiento(Integer.parseInt(partsOfAlarm[1]), partsOfAlarm[2]);
                break;
            default:
                break;
        }
    }
    */

}
