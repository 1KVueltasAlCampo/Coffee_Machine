import java.util.Queue;
import java.util.LinkedList;
import RM.ReliableMessagePrx;
import com.zeroc.Ice.Current;


public class ProxyReliableMessageImp implements RM.ReliableMessage, Runnable{
    private Queue<String> alarmas;
    private ReliableMessagePrx server;

    public ProxyReliableMessageImp(ReliableMessagePrx server) {
        this.server = server;
        alarmas = new LinkedList<>();
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
                //Nada xd
            }
        }
    }
}
