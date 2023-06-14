package publisher_subscriber;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import pubsub.ObserverPrx;
import receta.ProductoReceta;

public class ServerObservableImp implements pubsub.Observable, Runnable {

    private Communicator communicator;
    private ProductoReceta recetaService;
    private List<ObserverPrx> observers; //Proxy caches
    private boolean allObserversNotified = false;

    /**
     * @param communicator the communicator to set
     */
    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    	/**
	 * @param recetaService the recetaService to set
	 */
	public void setRecetaService(ProductoReceta recetaService) {
		this.recetaService = recetaService;
	}

    public ServerObservableImp() {
        this.observers = new ArrayList<>();
    }

    public boolean isAllObserversNotified() {
        return allObserversNotified;
    }

    @Override
    public void attach(ObserverPrx o, Current current) {
        //asignar id 
        observers.add(o);
    }

    @Override
    public void detach(ObserverPrx o, Current current) {
        observers.remove(o);
        //Buscar el o para eliminarlo
    }

    //In this case the observers are the proxy caches
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
             //This list is a return of a query which contains strings with the recipe and its ingredients
            String[] recetasCompletas = recetaService.consultarProductos(null);
        
            for (ObserverPrx o : observers) {
                o.update(recetasCompletas, null);
            }

            allObserversNotified = true;

        } catch (Exception e) {
            System.out.println("Something went wrong while sending the recipes to the observers");
        }
    }

}
