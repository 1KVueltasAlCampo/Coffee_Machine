package publisher_subscriber;
import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import pubsub.ObserverPrx;

public class ProxyCacheObservableImp implements pubsub.Observable {

    private List<ObserverPrx> observers;
    private Communicator communicator;

    public ProxyCacheObservableImp() {
        this.observers = new ArrayList<>();
    }
        /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
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
            
        }
        
        long endTime = System.nanoTime(); // Registro del tiempo final
        long elapsedTime = endTime - startTime; // Cálculo del tiempo transcurrido en nanosegundos
        
        // Imprimir el tiempo transcurrido en milisegundos
        System.out.println("Tiempo transcurrido proxy: " + elapsedTime / 1000000 + " milisegundos");
    }
    
}
