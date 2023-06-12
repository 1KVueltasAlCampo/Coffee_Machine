package gatewayCommunication;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;

public class ObservableImp implements gateway.Observable {

    private Communicator communicator;
    private List<ObserverPrx> observers;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public ObservableImp() {
        this.observers = new ArrayList<>();
    }

        @Override
    public void attach(ObserverPrx o, Current current) {
        observers.add(o);
    }

    @Override
    public void detach(ObserverPrx o, Current current) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Current current) {
        for (ObserverPrx observer : observers) {
            observer.update();
        }
    }

    
}
