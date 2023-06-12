package gatewayCommunication;

import com.zeroc.Ice.Current;

import gateway.ObservablePrx;

public class ObserverImp implements gateway.Observer {


    private ObservablePrx server;


    public ObserverImp(ObservablePrx server) {
        this.server = server;
    }

    @Override
    public void update(String message, Current current) {
        System.out.println("Message from server: " + message);
    }
    
}
