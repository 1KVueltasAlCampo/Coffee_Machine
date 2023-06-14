package publisher_subscriber;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import pubsub.ObserverPrx;

public class ProxyCacheObservableImp implements pubsub.Observable, Runnable {

    private List<ObserverPrx> observers; //Observers
    private List<String> cacheRecipes;

    private Communicator communicator;
    
    public ProxyCacheObservableImp() {
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
        long startTime = System.nanoTime(); //Registro del tiempo inicial
       
        run();
        
        long endTime = System.nanoTime(); //Registro del tiempo final
        long elapsedTime = endTime - startTime; //Cálculo del tiempo transcurrido en nanosegundos
        
        //Tiempo transcurrido en milisegundos
        System.out.println("Tiempo transcurrido: " + elapsedTime / 1000000 + " milisegundos");
    }

    @Override
    public void run() {

        try {
            for (ObserverPrx o : observers) {
                //This converts the arraylist to an array of strings because update() was generated with Slice, it supports only primitive types
                o.update(cacheRecipes.toArray(new String[cacheRecipes.size()]), null);
            }
            Thread.yield(); 
        } catch (Exception e) {
            System.out.println("Something went wrong while sending the recipes to the observers");
        }

    }
    
}
