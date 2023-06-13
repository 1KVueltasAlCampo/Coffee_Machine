import gateway.Alarma;
public class ProxyReliableMessageImp implements gateway.ReliableMessage,Runnable{
    private Queue<Alarma> alarmas;
    private ObservablePrx server;

    public ProxyReliableMessageImp(ObservablePrx server) {
        this.server = server;
        alarmas = new Queue<>();
    }

    @Override
    public void notifyAlarm(Alarma alarma) {
        alarmas.add(alarma);
    }

    @Override
    public void run(){
        while(true){
            Alarma alarm = null;
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
