package gatewayCommunication;

import com.zeroc.Ice.Current;

import McControlador.ControladorMQ;
import gateway.ObservablePrx;
import gateway.ReliableMessage;
import gateway.ReliableMessagePrx;


public class ObserverImp implements gateway.Observer {


    private ObservablePrx server;
    private ReliableMessagePrx reliableMessage;
    private ControladorMQ controler; 

    public ObserverImp(ObservablePrx server, ControladorMQ controler) {
        this.server = server;
        this.controler = controler;
    }

    public void sendAlarm(String alarm){
        reliableMessage.notifyAlarm(alarm);
    }


    @Override
    public void update(String[] recetasCompletas, Current current) {
        controler.cargarRecetaMaquinas();
    }

    
    
}
