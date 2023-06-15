import java.util.Queue;
import java.util.LinkedList;
import RM.ReliableMessagePrx;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Communicator;


public class ProxyReliableMessageImp extends Thread implements RM.ReliableMessage{
    private Queue<String> alarmas;
    private ReliableMessagePrx server;
    private Communicator communicator;

    public ProxyReliableMessageImp(ReliableMessagePrx server) {
        this.server = server;
        alarmas = new LinkedList<>();
        System.out.println("ProxyReliableMessageImp creado");
    }

    /**
     * @param communicator the communicator to set
     */
    public synchronized void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }


    @Override
    public void notifyAlarm(String alarma,Current current) {
        alarmas.add(alarma);
    }

    @Override
    public void run(){
        while(true){
            String alarm = "";
            try{
                while(!alarmas.isEmpty()){
                    alarm = alarmas.peek();
                    server.notifyAlarm(alarm);
                    alarmas.poll();
                }
                Thread.yield();
            }
            catch(Exception e){
                System.out.println("Error al enviar la alarma: ");
            }
        }
    }
}