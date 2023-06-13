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
        long startTime = System.nanoTime(); // Registro del tiempo inicial
        
        for (ObserverPrx observer : observers) {
            observer.update();
        }
        
        long endTime = System.nanoTime(); // Registro del tiempo final
        long elapsedTime = endTime - startTime; // Cálculo del tiempo transcurrido en nanosegundos
        
        // Imprimir el tiempo transcurrido en milisegundos
        System.out.println("Tiempo transcurrido: " + elapsedTime / 1000000 + " milisegundos");
    }

}
