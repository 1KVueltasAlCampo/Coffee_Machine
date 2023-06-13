package gatewayCommunication;

import java.util.ArrayList;
import java.util.List;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import gateway.ObserverPrx;
import receta.ProductoReceta;

public class ObservableImp implements gateway.Observable,gateway.ReliableMessage {

    private Communicator communicator;
    private ProductoReceta recetaService;
    private List<ObserverPrx> observers;

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

        //This list is a return of a query which contains strings with the recipe and its ingredients
        String[] recetasCompletas = recetaService.consultarProductos(current);
        
        for (ObserverPrx observer : observers) {
            observer.update(recetasCompletas, null);
        }
        
        long endTime = System.nanoTime(); // Registro del tiempo final
        long elapsedTime = endTime - startTime; // Cálculo del tiempo transcurrido en nanosegundos
        
        // Imprimir el tiempo transcurrido en milisegundos
        System.out.println("Tiempo transcurrido: " + elapsedTime / 1000000 + " milisegundos");
    }

    @Override
    public void notifyAlarm(Alarm a){
        
    }

}
