import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;

public class ObservableImp implements gateway.Observable, Runnable {

    private List<ObserverPrx> observers; //Machines 
     private List<String> cacheRecipes;

    private Communicator communicator;
    
    public ObservableImp() {
        this.observers = new ArrayList<>();
        this.cacheRecipes = new ArrayList<>();
    }
        /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

            /**
     * @param recetas the communicator to set
     */
    public void setCacheRecipes(List<String> cacheRecipes) {
        this.cacheRecipes = cacheRecipes;
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
        run();
    }

    @Override
    public void run() {

        try {
            for (ObserverPrx observer : observers) {
                observer.update(cacheRecipes.toArray(new String[cacheRecipes.size()]), null);
            }
            Thread.yield(); 
        } catch (Exception e) {
            System.out.println("Something went wrong while sending the recipes to the machines");
        }

    }
    
}
