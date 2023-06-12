package gatewayCommunication;

import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;

public class GatewayCommunication implements gateway.Observable {

    private Communicator communicator;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }


    private List<ObserverPrx> observers;

    @Override
    public void addObserver(ObserverPrx o, Current current) {
        try {
            System.out.println("Observer = " + o.toString());
            o.update("CONECTADO");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void removeObserver(ObserverPrx o, Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObserver'");
    }

    @Override
    public void notifyObservers(Current current) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObservers'");
    }
    
}
