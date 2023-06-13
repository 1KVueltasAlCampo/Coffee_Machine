package gatewayCommunication;

import com.zeroc.Ice.Current;

import McControlador.ControladorMQ;
import gateway.ObservablePrx;
import gateway.ReliableMessage;
import gateway.Alarma;


public class ObserverImp implements gateway.Observer {


    private ObservablePrx server;
    private ReliableMessage reliableMessage;
    private ControladorMQ controler; 


    public ObserverImp(ObservablePrx server, ControladorMQ controler) {
        this.server = server;
        this.controler = controler;
    }

    public void sendAlarm(Alarma alarm){
        .notifyAlarm(alarm);
    }


    @Override
    public void update(String[] recetasCompletas, Current current) {
        controler.cargarRecetaMaquinas();
    }

    
    
}
