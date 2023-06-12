package gatewayCommunication;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;

public class GatewayCommunication implements gateway.Observable {

    private Communicator communicator;
    private List<ObserverPrx> observers;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public GatewayCommunication() {
        this.observers = new ArrayList<>();
    }
    

    @Override
    public void addObserver(ObserverPrx o, Current current) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverPrx o, Current current) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Current current) {
        for (ObserverPrx observer : observers) {
            observer.update();
        }
    }
    
}
