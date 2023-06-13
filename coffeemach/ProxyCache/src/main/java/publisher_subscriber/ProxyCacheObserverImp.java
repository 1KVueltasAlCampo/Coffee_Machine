package publisher_subscriber;

import java.util.ArrayList;

import com.zeroc.Ice.Current;

import pubsub.ObservablePrx;

public class ProxyCacheObserverImp implements pubsub.Observer {


    private ObservablePrx server;

    private ProxyCacheObservableImp proxyCacheObservableImp;

    public ProxyCacheObserverImp(ObservablePrx server) {
        this.server = server;
    }

            /**
     * @param observableImp the observableImp to set
     */
    public void setObservableImp(ProxyCacheObservableImp proxyCacheObservableImp) {
        this.proxyCacheObservableImp = proxyCacheObservableImp;
    }

    @Override
    public void update(String[] recetasNuevas, Current current) {
        System.out.println("Proxy Actualizado");

        ArrayList<String> updatedRecipes = new ArrayList<>();

        for (String recipe : recetasNuevas) {
            if (!updatedRecipes.contains(recipe)) {
                updatedRecipes.add(recipe);
            }
            
        }

        proxyCacheObservableImp.setCacheRecipes(updatedRecipes);

        //Makes the observable proxy cache to notify all the machines
        proxyCacheObservableImp.notifyObservers(current);
        
    }

}
